package com.fantingame.game.mywar.logic.pawnshop.action;

import com.fantingame.game.msgbody.client.pawnshop.PawnshopAction_sellReq;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.server.annotation.ISingleThreadRuler;

/**
 * 当铺卖出
 * 
 * @author yezp
 */
public class SellThread implements ISingleThreadRuler {

	private Long key;

	@Override
	public String getCmdCode() {
		return "PawnshopAction_sell";
	}

	@Override
	public long getRulerKey(Msg msg) {
		PawnshopAction_sellReq req = msg.decodeBody(PawnshopAction_sellReq.class);
		key = Long.valueOf(req.getCamp());
		return key;
	}

}
