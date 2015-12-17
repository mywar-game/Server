package com.fantingame.game.mywar.logic.lucky.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.lucky.LuckyAction_getBattleLuckyRewardRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.lucky.service.LuckyService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

@GameAction
public class LuckyAction {

	@Autowired
	private LuckyService luckyService;
	
	/**
	 * 获取战斗随机事件
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getBattleLuckyReward(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		CommonGoodsBeanBO drop = luckyService.getBattleLuckyReward(userId);
		
		LuckyAction_getBattleLuckyRewardRes res = new LuckyAction_getBattleLuckyRewardRes();
		res.setDrop(drop);
		return res;
	}
	
}
