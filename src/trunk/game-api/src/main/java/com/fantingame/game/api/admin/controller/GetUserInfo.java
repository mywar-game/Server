package com.fantingame.game.api.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.netty.channel.Channel;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.api.common.util.ApiConstant;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.admin.AdminAction_getUserInfoReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_getUserInfoRes;
import com.fantingame.game.server.callback.ICallBackHandler;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.http.HttpUtil;
import com.fantingame.game.server.http.IHttpHandler;
import com.fantingame.game.server.http.Request;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.msg.ServerType;

public class GetUserInfo implements IHttpHandler {

	@Override
	public Object handle(Request request, final Channel nettyChannel) {
		String userId = request.getParameter("userId");
		if (StringUtils.isBlank(userId))
			throw new ServiceException(ApiConstant.PARAM_ERROR, "参数错误");
		
		AdminAction_getUserInfoReq req = new AdminAction_getUserInfoReq();
		req.setUserId(userId);
		
		final Map<String, Object> map = new HashMap<String, Object>();
		MsgDispatchCenter.disPatchServerMsg(ServerType.LOGIC_SERVER, "AdminAction_getUserInfo", req, new ICallBackHandler() {			
			@Override
			public void onSuccess(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("获取用户信息成功。");
				map.put("rc", SystemConstant.SUCCESS_CODE);
				AdminAction_getUserInfoRes res = msg.decodeBody(AdminAction_getUserInfoRes.class);
				map.put("result", res.getUser());
				HttpUtil.renderSuccess(nettyChannel, map);
			}
			
			@Override
			public void OnFail(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("获取用户信息失败。");
				map.put("rc", msg.getMsgHead().getErrorCode());
				AdminAction_getUserInfoRes res = msg.decodeBody(AdminAction_getUserInfoRes.class);
				map.put("result", res.getUser());
				HttpUtil.renderSuccess(nettyChannel, map);
			}
		});
		return null;
	}

	@Override
	public String getMappingUrl() {
		return "/gameAdminApi/getUserInfo.do";
	}
}
