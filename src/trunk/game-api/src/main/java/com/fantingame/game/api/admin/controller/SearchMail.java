package com.fantingame.game.api.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.api.common.util.ApiConstant;
import com.fantingame.game.api.common.util.Assert;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.admin.AdminAction_searchMailReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_searchMailRes;
import com.fantingame.game.server.callback.ICallBackHandler;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.http.HttpUtil;
import com.fantingame.game.server.http.IHttpHandler;
import com.fantingame.game.server.http.Request;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.msg.ServerType;

public class SearchMail implements IHttpHandler {

	@Override
	public Object handle(Request request, final Channel nettyChannel) {
		String userId = request.getParameter("userId");
		Assert.notEmtpy(userId, ApiConstant.PARAM_ERROR, "用户id为空");
		
		AdminAction_searchMailReq req = new AdminAction_searchMailReq();
		req.setUserId(userId);
		
		final Map<String, Object> map = new HashMap<String, Object>();
		MsgDispatchCenter.disPatchServerMsg(ServerType.LOGIC_SERVER, "AdminAction_searchMail", req, new ICallBackHandler() {			
			@Override
			public void onSuccess(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("搜索邮件成功。");
				map.put("rc", SystemConstant.SUCCESS_CODE);
				AdminAction_searchMailRes res = msg.decodeBody(AdminAction_searchMailRes.class);
				map.put("result", res.getResUserMailBOList());
				HttpUtil.renderSuccess(nettyChannel, map);
			}
			
			@Override
			public void OnFail(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("搜索邮件失败。。。。。。。。。。。。。");
				map.put("rc", msg.getMsgHead().getErrorCode());
				HttpUtil.renderSuccess(nettyChannel, map);
			}
		});
		
		return null;
	}

	@Override
	public String getMappingUrl() {
		return "/gameAdminApi/searchMail.do";
	}

}
