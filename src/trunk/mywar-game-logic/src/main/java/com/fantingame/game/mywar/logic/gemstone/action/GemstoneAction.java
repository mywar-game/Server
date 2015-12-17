package com.fantingame.game.mywar.logic.gemstone.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.gemstone.GemstoneAction_gemstoneForgeReq;
import com.fantingame.game.msgbody.client.gemstone.GemstoneAction_gemstoneForgeRes;
import com.fantingame.game.msgbody.client.gemstone.GemstoneAction_gemstoneResolveReq;
import com.fantingame.game.msgbody.client.gemstone.GemstoneAction_gemstoneResolveRes;
import com.fantingame.game.msgbody.client.gemstone.GemstoneAction_gemstoneUpgradeReq;
import com.fantingame.game.msgbody.client.gemstone.GemstoneAction_gemstoneUpgradeRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.gemstone.service.GemstoneService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

@GameAction
public class GemstoneAction {

	@Autowired
	private GemstoneService gemstoneService;
	
	/**
	 * 宝石合成
	 * 
	 * @param msg
	 * @param Channel
	 * @return
	 */
	public ICodeAble gemstoneForge(Msg msg, Channel Channel) {
		String userId = msg.getMsgHead().getFromID();
		
		GemstoneAction_gemstoneForgeReq req = msg.decodeBody(GemstoneAction_gemstoneForgeReq.class);
		GemstoneAction_gemstoneForgeRes res = this.gemstoneService.gemstoneForge(userId, req.getForgeType(), req.getToolId(), req.getToolType(), req.getStatus(), req.getMaterial(), req.getNum());
		return res;
	}
	
	/**
	 * 宝石分解
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble gemstoneResolve(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		GemstoneAction_gemstoneResolveReq req = msg.decodeBody(GemstoneAction_gemstoneResolveReq.class);
		GemstoneAction_gemstoneResolveRes res = this.gemstoneService.gemstoneResolve(userId, req.getUserGemstoneIdList(), req.getStatus());	
		return res;
	}
	
	/**
	 * 宝石升级 
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble gemstoneUpgrade(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		GemstoneAction_gemstoneUpgradeReq req = msg.decodeBody(GemstoneAction_gemstoneUpgradeReq.class);
		GemstoneAction_gemstoneUpgradeRes res = this.gemstoneService.gemstoneUpgrade(userId, req.getUserGemstoneId(), req.getStatus());
		return res;
	}
	
}
