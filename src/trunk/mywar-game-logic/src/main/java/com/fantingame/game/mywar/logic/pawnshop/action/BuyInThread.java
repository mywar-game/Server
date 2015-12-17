package com.fantingame.game.mywar.logic.pawnshop.action;

import com.fantingame.game.msgbody.client.pawnshop.PawnshopAction_buyInReq;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.server.annotation.ISingleThreadRuler;

/**
 * 当铺买入
 * 
 * @author yezp
 */
public class BuyInThread implements ISingleThreadRuler {

	private Long key;
	
	@Override
	public String getCmdCode() {
		return "PawnshopAction_buyIn";
	}

	@Override
	public long getRulerKey(Msg msg) {
		PawnshopAction_buyInReq req = msg.decodeBody(PawnshopAction_buyInReq.class);
		key = Long.valueOf(req.getCamp());
		return key;
	}

}
