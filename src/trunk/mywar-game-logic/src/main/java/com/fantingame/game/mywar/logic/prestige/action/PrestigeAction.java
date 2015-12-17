package com.fantingame.game.mywar.logic.prestige.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.prestige.InviteHeroBO;
import com.fantingame.game.msgbody.client.prestige.PrestigeAction_getInviteHeroInfoRes;
import com.fantingame.game.msgbody.client.prestige.PrestigeAction_inviteHeroReq;
import com.fantingame.game.msgbody.client.prestige.PrestigeAction_inviteHeroRes;
import com.fantingame.game.msgbody.client.prestige.PrestigeAction_rewardReq;
import com.fantingame.game.msgbody.client.prestige.PrestigeAction_rewardRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.prestige.service.PrestigeService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;


@GameAction
public class PrestigeAction {
	
	@Autowired
	private PrestigeService prestigeService;

	/**
	 * 声望奖励领取
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble reward(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		PrestigeAction_rewardReq req = msg.decodeBody(PrestigeAction_rewardReq.class);
		CommonGoodsBeanBO drop = prestigeService.rewardPrestige(userId,
				req.getId());
		PrestigeAction_rewardRes res = new PrestigeAction_rewardRes();
		res.setDrop(drop);
		return res;
	}
	
	/**
	 * 查看用户酒馆信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getInviteHeroInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		List<InviteHeroBO> list = this.prestigeService.getInviteHeroInfo(userId);
		PrestigeAction_getInviteHeroInfoRes res = new PrestigeAction_getInviteHeroInfoRes();
		res.setInviteHeroList(list);
		return res;
	}
	
	/**
	 * 邀请英雄
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble inviteHero(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		PrestigeAction_inviteHeroReq req = msg.decodeBody(PrestigeAction_inviteHeroReq.class);
		Map<String, Object> map = prestigeService.inviteHero(userId, req.getSystemHeroId(), req.getHeroName());
		
		PrestigeAction_inviteHeroRes res = new PrestigeAction_inviteHeroRes();
		res.setDrop((CommonGoodsBeanBO) map.get("dr"));
		res.setGold((Integer) map.get("gd"));
		res.setMoney((Integer) map.get("mon"));
		return res;
	}
}
