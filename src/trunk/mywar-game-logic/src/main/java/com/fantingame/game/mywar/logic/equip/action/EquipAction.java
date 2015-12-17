package com.fantingame.game.mywar.logic.equip.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.equip.EquipAction_equipFillInGemstoneReq;
import com.fantingame.game.msgbody.client.equip.EquipAction_equipFillInGemstoneRes;
import com.fantingame.game.msgbody.client.equip.EquipAction_equipForgeReq;
import com.fantingame.game.msgbody.client.equip.EquipAction_equipForgeRes;
import com.fantingame.game.msgbody.client.equip.EquipAction_equipMagicReq;
import com.fantingame.game.msgbody.client.equip.EquipAction_equipMagicRes;
import com.fantingame.game.msgbody.client.equip.EquipAction_equipRecycleReq;
import com.fantingame.game.msgbody.client.equip.EquipAction_equipRecycleRes;
import com.fantingame.game.msgbody.client.equip.EquipAction_unWearEquipReq;
import com.fantingame.game.msgbody.client.equip.EquipAction_unWearEquipRes;
import com.fantingame.game.msgbody.client.equip.EquipAction_wearEquipReq;
import com.fantingame.game.msgbody.client.equip.EquipAction_wearEquipRes;
import com.fantingame.game.msgbody.client.gemstone.UserGemstoneBO;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.equip.service.EquipService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

@GameAction
public class EquipAction {

	@Autowired
	private EquipService equipService;
	
	/**
	 * 穿戴装备
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble wearEquip(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		EquipAction_wearEquipReq req = msg.decodeBody(EquipAction_wearEquipReq.class);
		
		EquipAction_wearEquipRes res = equipService.wearEquip(userId, req.getUserEquipId(), req.getUserHeroId(), req.getPos());
		return res;
	}
	
	/**
	 * 装备卸下
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble unWearEquip(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		EquipAction_unWearEquipReq req = msg.decodeBody(EquipAction_unWearEquipReq.class);
		
		EquipAction_unWearEquipRes res = this.equipService.unWearEquip(userId, req.getUserEquipId());
		return res;
	}
	
	/**
	 * 锻造
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble equipForge(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		EquipAction_equipForgeReq req = msg.decodeBody(EquipAction_equipForgeReq.class);
		
		EquipAction_equipForgeRes res = this.equipService.toolForge(userId, req.getForgeType(), req.getToolType(), req.getToolId(), req.getStatus(), req.getMaterial(), req.getNum());
		return res;
	}

	/**
	 * 回收
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble equipRecycle(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		EquipAction_equipRecycleReq req = msg.decodeBody(EquipAction_equipRecycleReq.class);
		
		EquipAction_equipRecycleRes res = this.equipService.equipRecycle(userId, req.getToolType(), req.getToolId(), req.getUserEquipId(), req.getStatus());
		return res;
	}
	
	/**
	 * 装备镶嵌宝石
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble equipFillInGemstone(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		EquipAction_equipFillInGemstoneReq req = msg.decodeBody(EquipAction_equipFillInGemstoneReq.class);		
		List<UserGemstoneBO> userGemstoneBOList = this.equipService.equipFillInGemstone(userId, req.getUserEquipId(), req.getUserGemstoneId(), req.getPos());
		
		EquipAction_equipFillInGemstoneRes res = new EquipAction_equipFillInGemstoneRes();
		res.setUserGemstoneBOList(userGemstoneBOList);
		return res;
	}

	/**
	 * 装备附魔
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble equipMagic(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		EquipAction_equipMagicReq req = msg.decodeBody(EquipAction_equipMagicReq.class);
		EquipAction_equipMagicRes res = this.equipService.equipMagic(userId, req.getUserEquipId(), req.getReelId(), req.getStatus());	
		return res;
	}
	
}
