package com.fantingame.game.api.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.admin.AdminAction_reloadStaticDataReq;
import com.fantingame.game.server.callback.ICallBackHandler;
import com.fantingame.game.server.http.HttpUtil;
import com.fantingame.game.server.http.IHttpHandler;
import com.fantingame.game.server.http.Request;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.msg.ServerType;
/**
 * 重载静态数据
 * @author Administrator
 *
 */
public class ReloadStaticData implements IHttpHandler {
	@Override
	public Object handle(final Request request, final Channel nettyChannel) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final String className = request.getParameter("className");
		AdminAction_reloadStaticDataReq req = new AdminAction_reloadStaticDataReq();
		req.setClassName(className);
		MsgDispatchCenter.disPatchServerMsg(ServerType.LOGIC_SERVER, "AdminAction_reloadStaticData", req, new ICallBackHandler() {
			@Override
			public void onSuccess(Msg msg,
					com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("用户重载静态数据成功。className="+className);
				map.put("rc", SystemConstant.SUCCESS_CODE);
				HttpUtil.renderSuccess(nettyChannel, map);
			}
			@Override
			public void OnFail(Msg msg,
					com.fantingame.game.server.channel.Channel channel) {
				LogSystem.info("用户重载静态数据失败。className="+className);
				map.put("rc", msg.getMsgHead().getErrorCode());
				HttpUtil.renderSuccess(nettyChannel, map);
			}
		});
		
		return null;
	}
	
	@Override
	public String getMappingUrl() {
		return "/server/reload.do";
	}

}
