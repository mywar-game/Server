package com.fantingame.game.api.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.user.ReqLoadUserMapper;
import com.fantingame.game.msgbody.user.UserToken;
import com.fantingame.game.server.callback.ICallBackHandler;
import com.fantingame.game.server.http.HttpUtil;
import com.fantingame.game.server.http.IHttpHandler;
import com.fantingame.game.server.http.Request;
import com.fantingame.game.server.monitor.MonitorService;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.msg.ServerType;

/**
 * 用户注册
 * 
 */
public class UserController implements IHttpHandler {

	@Override
	public Object handle(Request request, final Channel nettyChannel) {
		MonitorService.getInstance().markOneUserRequest();
		final Map<String, Object> map = new HashMap<String, Object>();
		LogSystem.info("用户开始创建token。");
		String partnerUserId = request.getParameter("partnerUserId");
		String partnerId = request.getParameter("partnerId");
		String serverId = request.getParameter("serverId");
		String qn = request.getParameter("qn");
		String imei = request.getParameter("imei");
		String mac = request.getParameter("mac");
		String idfa = request.getParameter("idfa");
		String ip = request.getParameter("ip");
		String mobile = request.getParameter("mobile");
		String extInfo = request.getParameter("extInfo");
		ReqLoadUserMapper reqLoadUserMapper = new ReqLoadUserMapper(partnerUserId, partnerId, serverId, qn, imei, mac, idfa, ip, mobile);
		reqLoadUserMapper.setExtInfo(extInfo);
		LogSystem.debug("api开始创建用户token,partnerUserId="+partnerUserId+",time="+System.currentTimeMillis());
		MsgDispatchCenter.disPatchServerMsg(ServerType.GATEWAY_SERVER, "UAction_ulogin", reqLoadUserMapper, new ICallBackHandler() {
				@Override
				public void onSuccess(Msg msg,
						com.fantingame.game.server.channel.Channel channel) {
					LogSystem.info("用户创建token成功。"+",time="+System.currentTimeMillis());
					UserToken userToken = msg.decodeBody(UserToken.class);
					map.put("userToken", userToken);
					HttpUtil.renderSuccess(nettyChannel, map);
				}
				@Override
				public void OnFail(Msg msg,
						com.fantingame.game.server.channel.Channel channel) {
					LogSystem.warn("用户创建token失败，code="+msg.getMsgHead().getErrorCode()+",time="+System.currentTimeMillis());
					map.put("rc", msg.getMsgHead().getErrorCode());
					HttpUtil.renderSuccess(nettyChannel, map);
				}
			});
		return null;
	}

	@Override
	public String getMappingUrl() {
		return "/gameApi/loadUserToken.do";
	}


}
