package com.fantingame.game.mywar.logic.achievement.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.achievement.AchievementAction_getUserAchievementInfoRes;
import com.fantingame.game.msgbody.client.achievement.AchievementAction_receiveAchievementRewardReq;
import com.fantingame.game.msgbody.client.achievement.AchievementAction_receiveAchievementRewardRes;
import com.fantingame.game.msgbody.client.achievement.UserAchievementBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.achievement.service.AchievementService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

/**
 * 成就系统
 * 
 * @author yezp
 */
@GameAction
public class AchievementAction {

	@Autowired
	public AchievementService achievementService;
	
	/**
	 * 查看用户成就系统信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserAchievementInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		List<UserAchievementBO> userAchievementList = this.achievementService.getUserAchievementInfo(userId);
		AchievementAction_getUserAchievementInfoRes res = new AchievementAction_getUserAchievementInfoRes();
		res.setUserAchievementList(userAchievementList);
		return res;
	}
	
	/**
	 * 领取成就奖励
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble receiveAchievementReward(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		AchievementAction_receiveAchievementRewardReq req = msg.decodeBody(AchievementAction_receiveAchievementRewardReq.class);
		CommonGoodsBeanBO drop = this.achievementService.receiveAchievementReward(userId, req.getAchievementId());
		AchievementAction_receiveAchievementRewardRes res = new AchievementAction_receiveAchievementRewardRes();
		
		res.setDrop(drop);
		return res;
	}
	
}
