package com.fantingame.game.mywar.logic.rank.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.rank.RankAction_getUserRankInfoReq;
import com.fantingame.game.msgbody.client.rank.RankAction_getUserRankInfoRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.rank.service.RankService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

@GameAction
public class RankAction {

	@Autowired
	private RankService rankService;
	
	public ICodeAble getUserRankInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		RankAction_getUserRankInfoReq req = msg.decodeBody(RankAction_getUserRankInfoReq.class);
		RankAction_getUserRankInfoRes res = this.rankService.getUserRankInfo(userId, req.getType());
		return res;		
	}
	
}
