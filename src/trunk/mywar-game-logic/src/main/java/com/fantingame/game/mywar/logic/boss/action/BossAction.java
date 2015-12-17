package com.fantingame.game.mywar.logic.boss.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.boss.BossAction_attackBossInfoReq;
import com.fantingame.game.msgbody.client.boss.BossAction_attackBossInfoRes;
import com.fantingame.game.msgbody.client.boss.BossAction_bornBossRes;
import com.fantingame.game.msgbody.client.boss.BossAction_leaveRes;
import com.fantingame.game.msgbody.client.boss.BossAction_reliveRes;
import com.fantingame.game.msgbody.client.boss.BossAction_startAttackBossReq;
import com.fantingame.game.msgbody.client.boss.BossAction_startAttackBossRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.boss.service.BossService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

/**
 * 世界boss
 * 
 * @author yezp
 */
@GameAction
public class BossAction {

	@Autowired
	private BossService bossService;
	
	/**
	 * 开始攻打boss
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble startAttackBoss(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		BossAction_startAttackBossReq req = msg.decodeBody(BossAction_startAttackBossReq.class);
		BossAction_startAttackBossRes res = this.bossService.startAttackBoss(userId, req.getMapId(), req.getX(), req.getY());
		return res;
	}
	
	/**
	 * 攻击世界Boss信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble attackBossInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		BossAction_attackBossInfoReq req = msg.decodeBody(BossAction_attackBossInfoReq.class);
		BossAction_attackBossInfoRes res = this.bossService.attackBossInfo(userId, req.getEffectList(), req.getSkillId(), req.getUserIdStr());
		return res;
	}
	
	/**
	 * 复活
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble relive(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		int money = this.bossService.relive(userId);
		BossAction_reliveRes res = new BossAction_reliveRes();
		res.setMoney(money);
		return res;
	}
	
	/**
	 * 生成世界boss（调试接口）
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble bornBoss(Msg msg, Channel channel) {
		BossAction_bornBossRes res = new BossAction_bornBossRes();
		this.bossService.bossBorn();
		return res;
	}
	
	/**
	 * 离开boss战
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble leave(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		this.bossService.userLeave(userId);
		BossAction_leaveRes res = new BossAction_leaveRes();
		return res;
	}
}
