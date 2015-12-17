package com.fantingame.game.api.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.api.common.util.ApiConstant;
import com.fantingame.game.api.common.util.Assert;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.admin.AdminAction_sendSystemMsgReq;
import com.fantingame.game.server.callback.ICallBackHandler;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.http.HttpUtil;
import com.fantingame.game.server.http.IHttpHandler;
import com.fantingame.game.server.http.Request;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.msg.ServerType;

/**
 * 发送跑马灯
 * 
 * @author yezp
 */
public class SendSystemMsg implements IHttpHandler {

	@Override
	public Object handle(Request request, final Channel nettyChannel) {
		String content = request.getParameter("content");
		String partnerId = request.getParameter("partnerId");
		
		Assert.notEmtpy(content, ApiConstant.PARAM_ERROR, "跑马灯内容不能为空.content[" + content + "]");
		AdminAction_sendSystemMsgReq req = new AdminAction_sendSystemMsgReq();
		req.setContent(content);
		req.setPartnerId(partnerId);
		final Map<String, Object> map = new HashMap<String, Object>();
		MsgDispatchCenter.disPatchServerMsg(ServerType.LOGIC_SERVER, "AdminAction_sendSystemMsg", req, new ICallBackHandler() {
			@Override
			public void onSuccess(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("成功发送跑马灯");
				map.put("rc", SystemConstant.SUCCESS_CODE);
				HttpUtil.renderSuccess(nettyChannel, map);
			}

			@Override
			public void OnFail(Msg msg, com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("发送跑马灯失败。");
				map.put("rc", msg.getMsgHead().getErrorCode());
				HttpUtil.renderSuccess(nettyChannel, map);	
			}			
		});
		
		return null;
	}

	@Override
	public String getMappingUrl() {
		return "/gameAdminApi/sendSystemMsg.do";
	}

}
