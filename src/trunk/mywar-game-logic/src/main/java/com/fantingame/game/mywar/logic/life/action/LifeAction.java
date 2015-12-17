package com.fantingame.game.mywar.logic.life.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.life.LifeAction_cancelHangupReq;
import com.fantingame.game.msgbody.client.life.LifeAction_cancelHangupRes;
import com.fantingame.game.msgbody.client.life.LifeAction_createLifeBuilderReq;
import com.fantingame.game.msgbody.client.life.LifeAction_createLifeBuilderRes;
import com.fantingame.game.msgbody.client.life.LifeAction_getHangupRewardListReq;
import com.fantingame.game.msgbody.client.life.LifeAction_getHangupRewardListRes;
import com.fantingame.game.msgbody.client.life.LifeAction_getUserLifeInfoRes;
import com.fantingame.game.msgbody.client.life.LifeAction_hangupReq;
import com.fantingame.game.msgbody.client.life.LifeAction_hangupRes;
import com.fantingame.game.msgbody.client.life.LifeAction_reCreateLifeBuilderReq;
import com.fantingame.game.msgbody.client.life.LifeAction_reCreateLifeBuilderRes;
import com.fantingame.game.msgbody.client.life.LifeAction_receiveRewardReq;
import com.fantingame.game.msgbody.client.life.LifeAction_receiveRewardRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.life.service.LifeService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

/**
 * 生活技能系统
 * 
 * @author yezp
 */
@GameAction
public class LifeAction {

	@Autowired
	private LifeService lifeService;
	
	/**
	 * 获取用户生活技能信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserLifeInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		LifeAction_getUserLifeInfoRes res = this.lifeService.getUserLifeInfo(userId);
		
		return res;
	}
	
	/**
	 * 建造矿场
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble createLifeBuilder(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		LifeAction_createLifeBuilderReq req = msg.decodeBody(LifeAction_createLifeBuilderReq.class);
		LifeAction_createLifeBuilderRes res = this.lifeService.createLifeBuilder(userId, req.getCategory());
		
		return res;
	}
	
	/**
	 * 重新建造
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble reCreateLifeBuilder(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		LifeAction_reCreateLifeBuilderReq req = msg.decodeBody(LifeAction_reCreateLifeBuilderReq.class);
		LifeAction_reCreateLifeBuilderRes res = this.lifeService.reCreateLifeBuilder(userId, req.getCategory());
		
		return res;
	}
	
	/**
	 * 挂机
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble hangup(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		LifeAction_hangupReq req = msg.decodeBody(LifeAction_hangupReq.class);
		LifeAction_hangupRes res = this.lifeService.hangup(userId, req.getCategory(), req.getUserHeroIdList(), req.getUserFriendId());
		
		return res;
	}
	
	/**
	 * 领取挂机奖励
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble receiveReward(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		LifeAction_receiveRewardReq req = msg.decodeBody(LifeAction_receiveRewardReq.class);
		LifeAction_receiveRewardRes res = this.lifeService.receiveReward(userId, req.getCategory());
		
		return res;
	}
	
	/**
	 * 取消挂机
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble cancelHangup(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		LifeAction_cancelHangupReq req = msg.decodeBody(LifeAction_cancelHangupReq.class);
		LifeAction_cancelHangupRes res = this.lifeService.cancelHangup(userId, req.getCategory());
		
		return res;
	}
	
	@Deprecated
	public ICodeAble getHangupRewardList(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		LifeAction_getHangupRewardListReq req = msg.decodeBody(LifeAction_getHangupRewardListReq.class);
		LifeAction_getHangupRewardListRes res = this.lifeService.getHangupRewardList(userId, req.getCategory(), req.getUserHeroId(), req.getUserFriendId());
		
		return res;
	}
	
}
