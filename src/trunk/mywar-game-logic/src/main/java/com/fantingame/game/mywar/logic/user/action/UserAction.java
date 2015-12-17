package com.fantingame.game.mywar.logic.user.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.msgbody.client.user.UserAction_changeUserNameReq;
import com.fantingame.game.msgbody.client.user.UserAction_changeUserNameRes;
import com.fantingame.game.msgbody.client.user.UserAction_creatReq;
import com.fantingame.game.msgbody.client.user.UserAction_recordActionLogReq;
import com.fantingame.game.msgbody.client.user.UserAction_recordGuideStepReq;
import com.fantingame.game.msgbody.client.user.UserAction_recordOpenMapReq;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.user.UserAction_loginReq;
import com.fantingame.game.msgbody.server.user.UserAction_logoutReq;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.MsgBuilder;

@GameAction
public class UserAction {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登陆
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble login(Msg msg,Channel channel) {
		UserAction_loginReq req = msg.decodeBody(UserAction_loginReq.class);
		userService.userLogin(req.getUserId(), req.getUserIp());
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 注册
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble creat(Msg msg,Channel channel) {
		UserAction_creatReq creatReq = msg.decodeBody(UserAction_creatReq.class);
		userService.reg(msg.getMsgHead().getFromID(), creatReq.getRoleName(), creatReq.getRoleId(), "");
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 登出
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble logout(Msg msg,Channel channel) {
		UserAction_logoutReq logoutReq = msg.decodeBody(UserAction_logoutReq.class);
		LogSystem.debug("用户退出逻辑服，userId=" + logoutReq.getUserId());
		userService.userLogout(logoutReq.getUserId(), logoutReq.getUserIp(), logoutReq.getOnLineSeconds());
		return null;
	}
	
	/**
	 * 修改昵称
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble changeUserName(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		UserAction_changeUserNameReq req = msg.decodeBody(UserAction_changeUserNameReq.class);
		
		UserAction_changeUserNameRes res = this.userService.changeUserName(userId, req.getName());
		return res;
	}
	
	/**
	 * 记录新手引导步骤
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble recordGuideStep(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		UserAction_recordGuideStepReq req = msg.decodeBody(UserAction_recordGuideStepReq.class);
		this.userService.recordGuideStep(userId, req.getGuideStep());
		
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 记录用户打点信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble recordActionLog(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		UserAction_recordActionLogReq req = msg.decodeBody(UserAction_recordActionLogReq.class);
		this.userService.recordActionLog(userId, req.getActionId(), req.getIp());
		
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 记录用户地图开启的信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble recordOpenMap(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		UserAction_recordOpenMapReq req = msg.decodeBody(UserAction_recordOpenMapReq.class);
		this.userService.recordOpenMap(userId, req.getMapId());
		return MsgBuilder.EMPTY_BODY;
	}	
	
}
