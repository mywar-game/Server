package com.fantingame.game.mywar.logic.pk.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.pk.PkAction_changePosReq;
import com.fantingame.game.msgbody.client.pk.PkAction_endPkFightReq;
import com.fantingame.game.msgbody.client.pk.PkAction_endPkFightRes;
import com.fantingame.game.msgbody.client.pk.PkAction_enterPkRes;
import com.fantingame.game.msgbody.client.pk.PkAction_exchangeReq;
import com.fantingame.game.msgbody.client.pk.PkAction_exchangeRes;
import com.fantingame.game.msgbody.client.pk.PkAction_getPkFightLogRes;
import com.fantingame.game.msgbody.client.pk.PkAction_getPkRankRes;
import com.fantingame.game.msgbody.client.pk.PkAction_getUserPkInfoRes;
import com.fantingame.game.msgbody.client.pk.PkAction_getUserPkMallInfoRes;
import com.fantingame.game.msgbody.client.pk.PkAction_refreshChallengerRes;
import com.fantingame.game.msgbody.client.pk.PkAction_startPkFightReq;
import com.fantingame.game.msgbody.client.pk.PkAction_startPkFightRes;
import com.fantingame.game.msgbody.client.pk.PkMallBO;
import com.fantingame.game.msgbody.client.pk.PkRankBO;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.pk.service.PkService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.MsgBuilder;

/**
 * 竞技场相关Action
 * 
 * @author yezp
 */
@GameAction
public class PkAction {

	@Autowired
	private PkService pkService;
	
	/**
	 * 进入pk（第一次进入时）
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble enterPk(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		PkAction_enterPkRes res = this.pkService.enterPk(userId);
		return res;		
	}
	
	/**
	 * 获取用户竞技场信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserPkInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		PkAction_getUserPkInfoRes res = this.pkService.getUserPkInfo(userId);
		return res;
	}
	
	/**
	 * 上阵/下阵
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble changePos(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		PkAction_changePosReq req = msg.decodeBody(PkAction_changePosReq.class);
		this.pkService.changePos(userId, req.getUserHeroIdList());
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 换一批
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble refreshChallenger(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		PkAction_refreshChallengerRes res = this.pkService.refreshChallenger(userId);
		return res;
	}
	
	/**
	 * 重置挑战等待时间
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble resetWaitingTime(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		this.pkService.resetWaitingTime(userId);
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 获取竞技场排行榜列表
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getPkRank(Msg msg, Channel channel) {
		List<PkRankBO> rankList = this.pkService.getPkRankList();
		
		PkAction_getPkRankRes res = new PkAction_getPkRankRes();
		res.setRankList(rankList);
		return res;
	}
	
	/**
	 * 挑战玩家
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble startPkFight(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		PkAction_startPkFightReq req = msg.decodeBody(PkAction_startPkFightReq.class);
		PkAction_startPkFightRes res = this.pkService.startPkFight(userId, req.getTargetUserId());
		return res;
	}
	
	/**
	 * 挑战结束
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble endPkFight(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		PkAction_endPkFightReq req = msg.decodeBody(PkAction_endPkFightReq.class);
		PkAction_endPkFightRes res = this.pkService.endPkFight(userId, req.getFlag());
		return res;
	}
	
	/**
	 * 查看战斗日志
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getPkFightLog(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		PkAction_getPkFightLogRes res = this.pkService.getPkFightLog(userId);
		return res;
	}
	
	/**
	 * 获取用户竞技场兑换信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserPkMallInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		List<PkMallBO> pkMallList = this.pkService.getUserPkMallInfo(userId);
		PkAction_getUserPkMallInfoRes res = new PkAction_getUserPkMallInfoRes();
		res.setPkMallList(pkMallList);
		return res;
	}
	
	/**
	 * 兑换商品
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble exchange(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		PkAction_exchangeReq req = msg.decodeBody(PkAction_exchangeReq.class);
		Map<String, Object> map = this.pkService.exchange(userId, req.getMallId());
		 
		CommonGoodsBeanBO drop = (CommonGoodsBeanBO) map.get("dr");
		int honour = (int) map.get("hon");
		PkAction_exchangeRes res = new PkAction_exchangeRes();
		res.setDrop(drop);
		res.setHonour(honour);
		return res;
	}
	
	/**
	 * 刷新商品
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble refreshMall(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		this.pkService.refreshMall(userId);
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 购买挑战次数
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble buyChallengeTimes(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		this.pkService.buyChallengeTimes(userId);
		return MsgBuilder.EMPTY_BODY;
	}
}
