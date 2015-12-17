package com.fantingame.game.mywar.logic.pawnshop.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.pawnshop.PawnshopAction_buyInReq;
import com.fantingame.game.msgbody.client.pawnshop.PawnshopAction_buyInRes;
import com.fantingame.game.msgbody.client.pawnshop.PawnshopAction_getPawnshopInfoReq;
import com.fantingame.game.msgbody.client.pawnshop.PawnshopAction_getPawnshopInfoRes;
import com.fantingame.game.msgbody.client.pawnshop.PawnshopAction_sellReq;
import com.fantingame.game.msgbody.client.pawnshop.PawnshopAction_sellRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.pawnshop.service.PawnshopService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

/**
 * 当铺Action
 * 
 * @author yezp
 */
@GameAction
public class PawnshopAction {

	@Autowired
	private PawnshopService pawnshopService; 
	
	/**
	 * 获取当铺信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getPawnshopInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		// PawnshopAction_getPawnshopInfoReq req = (PawnshopAction_getPawnshopInfoReq) msg.getMsgBody();
		PawnshopAction_getPawnshopInfoReq req = msg.decodeBody(PawnshopAction_getPawnshopInfoReq.class);
		
		PawnshopAction_getPawnshopInfoRes res = this.pawnshopService.getPawnshopInfo(userId, req.getCamp());
		return res;
	}
	
	/**
	 * 买入
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble buyIn(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		PawnshopAction_buyInReq req = (PawnshopAction_buyInReq) msg.getMsgBody();
		// PawnshopAction_buyInReq req = msg.decodeBody(PawnshopAction_buyInReq.class);
	
		PawnshopAction_buyInRes res = this.pawnshopService.buyIn(userId, req.getMallId(), req.getNum(), req.getCamp());
		return res;
	}
	
	/**
	 * 卖出
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble sell(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		PawnshopAction_sellReq req = (PawnshopAction_sellReq) msg.getMsgBody();
		// PawnshopAction_sellReq req = msg.decodeBody(PawnshopAction_sellReq.class);
		
		PawnshopAction_sellRes res = this.pawnshopService.sell(userId, req.getMallId(), req.getNum(), req.getCamp());
		return res;
	}
	
}
