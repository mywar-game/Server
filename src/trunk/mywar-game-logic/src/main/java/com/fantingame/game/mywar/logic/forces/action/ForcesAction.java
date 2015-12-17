package com.fantingame.game.mywar.logic.forces.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.forces.ForcesAction_attackReq;
import com.fantingame.game.msgbody.client.forces.ForcesAction_attackRes;
import com.fantingame.game.msgbody.client.forces.ForcesAction_endAttackReq;
import com.fantingame.game.msgbody.client.forces.ForcesAction_endAttackRes;
import com.fantingame.game.msgbody.client.forces.ForcesAction_endCollectReq;
import com.fantingame.game.msgbody.client.forces.ForcesAction_endCollectRes;
import com.fantingame.game.msgbody.client.forces.ForcesAction_getCollectFightRewardReq;
import com.fantingame.game.msgbody.client.forces.ForcesAction_getCollectFightRewardRes;
import com.fantingame.game.msgbody.client.forces.ForcesAction_getCopyForcesInfoReq;
import com.fantingame.game.msgbody.client.forces.ForcesAction_getCopyForcesInfoRes;
import com.fantingame.game.msgbody.client.forces.ForcesAction_getMapCollectionInfoReq;
import com.fantingame.game.msgbody.client.forces.ForcesAction_getMapCollectionInfoRes;
import com.fantingame.game.msgbody.client.forces.ForcesAction_getMapForcesReq;
import com.fantingame.game.msgbody.client.forces.ForcesAction_getMapForcesRes;
import com.fantingame.game.msgbody.client.forces.ForcesAction_openBattleBoxReq;
import com.fantingame.game.msgbody.client.forces.ForcesAction_openBattleBoxRes;
import com.fantingame.game.msgbody.client.forces.ForcesAction_reliveReq;
import com.fantingame.game.msgbody.client.forces.ForcesAction_reliveRes;
import com.fantingame.game.msgbody.client.forces.ForcesAction_startCollectReq;
import com.fantingame.game.msgbody.client.forces.UserForcesBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.forces.service.ForcesService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.MsgBuilder;

@GameAction
public class ForcesAction {
	
	@Autowired
	private ForcesService forcesService;
	
    /**
     * 获取地图下用户关卡信息列表
     * 
     * @param msg
     * @param channel
     * @return
     */
	public ICodeAble getMapForces(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		ForcesAction_getMapForcesReq req = msg.decodeBody(ForcesAction_getMapForcesReq.class);		
		List<UserForcesBO> result = forcesService.getUserForcesBOAtMap(userId, req.getMapId());
		
		ForcesAction_getMapForcesRes res = new ForcesAction_getMapForcesRes();
		res.setUserForcesList(result);
		return res;
	}
	
	/**
     * 获取副本下用户关卡信息列表
     * 
     * @param msg
     * @param channel
     * @return
     */
	public ICodeAble getCopyForcesInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		ForcesAction_getCopyForcesInfoReq req = msg.decodeBody(ForcesAction_getCopyForcesInfoReq.class);
		List<UserForcesBO> result = this.forcesService.getCopyForcesInfo(userId, req.getMapId(), req.getBigForcesId());
		
		ForcesAction_getCopyForcesInfoRes res = new ForcesAction_getCopyForcesInfoRes();
		res.setUserForcesList(result);
		return res;
	}
	
	/**
	 * 攻击或采集关卡
	 * @param msg
	 * @param channel
	 * @return
	 */
 	public ICodeAble attack(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		ForcesAction_attackReq req = msg.decodeBody(ForcesAction_attackReq.class);
		
		ForcesAction_attackRes res = forcesService.startAttackForces(userId, req.getMapId(), req.getForcesId(), req.getForcesType(), req.getUserFriendId());
		return res;
 	}
 	
 	/**
 	 * 结束攻击或采集
 	 * @param msg
 	 * @param channel
 	 * @return
 	 */
 	public ICodeAble endAttack(Msg msg,Channel channel) {
		String userId = msg.getMsgHead().getFromID();
        ForcesAction_endAttackReq req = msg.decodeBody(ForcesAction_endAttackReq.class);
        CommonGoodsBeanBO drop = forcesService.endAttackForces(userId, req.getFlag());
        ForcesAction_endAttackRes res = new ForcesAction_endAttackRes();
        res.setDrop(drop);
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
 		ForcesAction_reliveReq req = msg.decodeBody(ForcesAction_reliveReq.class);
 		ForcesAction_reliveRes res = this.forcesService.relive(userId, req.getUserHeroId());
 		
 		return res;
 	}
 	
 	/**
 	 * 获取野外地图的采集信息
 	 * 
 	 * @param msg
 	 * @param channel
 	 * @return
 	 */
 	public ICodeAble getMapCollectionInfo(Msg msg, Channel channel) {
 		String userId = msg.getMsgHead().getFromID();
 		ForcesAction_getMapCollectionInfoReq req = msg.decodeBody(ForcesAction_getMapCollectionInfoReq.class);
 		List<UserForcesBO> userForcesBOList = this.forcesService.getMapCollectionInfo(userId, req.getMapId());
 		
 		ForcesAction_getMapCollectionInfoRes res = new ForcesAction_getMapCollectionInfoRes();
 		res.setUserCollectList(userForcesBOList);
 		return res;
 	}
 	
 	/**
 	 * 开始采集
 	 * 
 	 * @param msg
 	 * @param channel
 	 * @return
 	 */
 	public ICodeAble startCollect(Msg msg, Channel channel) {
 		String userId = msg.getMsgHead().getFromID();
 		ForcesAction_startCollectReq req = msg.decodeBody(ForcesAction_startCollectReq.class);
 		this.forcesService.startCollect(userId, req.getMapId(), req.getForcesId());
 		
 		return MsgBuilder.EMPTY_BODY;
 	}
 	
 	/**
 	 * 取消采集
 	 * 
 	 * @param msg
 	 * @param channel
 	 * @return
 	 */
 	public ICodeAble cancelCollect(Msg msg, Channel channel) {
 		String userId = msg.getMsgHead().getFromID();
 		
 		this.forcesService.cancelCollect(userId);
 		return MsgBuilder.EMPTY_BODY;
 	}
 	
 	/**
 	 * 结束采集
 	 * 
 	 * @param msg
 	 * @param channel
 	 * @return
 	 */
 	public ICodeAble endCollect(Msg msg, Channel channel) {
 		String userId = msg.getMsgHead().getFromID();
 		ForcesAction_endCollectReq req = msg.decodeBody(ForcesAction_endCollectReq.class);
 		Map<String, Object> map = this.forcesService.endCollect(userId, req.getMapId(), req.getForcesId());
 		
 		CommonGoodsBeanBO drop = (CommonGoodsBeanBO) map.get("drop");
 		int isFightAgain = (int) map.get("isFightAgain");
 		ForcesAction_endCollectRes res = new ForcesAction_endCollectRes(); 		
 		res.setDrop(drop);
 		res.setIsFightAgain(isFightAgain);
 		return res;
 	}
 	
 	/**
 	 * 获取采集以及遇到野怪的奖励
 	 * 
 	 * @param msg
 	 * @param channel
 	 * @return
 	 */
 	public ICodeAble getCollectFightReward(Msg msg, Channel channel) {
 		String userId = msg.getMsgHead().getFromID();
 		ForcesAction_getCollectFightRewardReq req = msg.decodeBody(ForcesAction_getCollectFightRewardReq.class);
 		CommonGoodsBeanBO drop = this.forcesService.getCollectFightReward(userId, req.getMapId(), req.getForcesId(), req.getFlag());
 		
 		ForcesAction_getCollectFightRewardRes res = new ForcesAction_getCollectFightRewardRes();
 		res.setDrop(drop);
 		return res;
 	}
 	
 	/**
 	 * 开启战斗宝箱
 	 * 
 	 * @param msg
 	 * @param channel
 	 * @return
 	 */
 	public ICodeAble openBattleBox(Msg msg, Channel channel) {
 		String userId = msg.getMsgHead().getFromID();
 		
 		ForcesAction_openBattleBoxReq req = msg.decodeBody(ForcesAction_openBattleBoxReq.class);
 		ForcesAction_openBattleBoxRes res = this.forcesService.openBattleBox(userId, req.getStatus());
 		return res;
 	}
}
