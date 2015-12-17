package com.fantingame.game.mywar.logic.activity.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.activity.ActivityAction_getActivityTaskInfoRes;
import com.fantingame.game.msgbody.client.activity.ActivityAction_getLoginReward30InfoRes;
import com.fantingame.game.msgbody.client.activity.ActivityAction_receiveActivityTaskRewardRes;
import com.fantingame.game.msgbody.client.activity.ActivityAction_receiveGiftBagReq;
import com.fantingame.game.msgbody.client.activity.ActivityAction_receiveGiftBagRes;
import com.fantingame.game.msgbody.client.activity.ActivityAction_receiveLoginReward30Req;
import com.fantingame.game.msgbody.client.activity.ActivityAction_receiveLoginReward30Res;
import com.fantingame.game.msgbody.client.activity.UserLoginRewardBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.activity.service.ActivityService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

/**
 * 活动模块接口
 * 
 * @author yezp
 */
@GameAction
public class ActivityAction {
	
	@Autowired
	private ActivityService activityService;

	/**
	 * 领取礼包码
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble receiveGiftBag(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		ActivityAction_receiveGiftBagReq req = msg.decodeBody(ActivityAction_receiveGiftBagReq.class);
		CommonGoodsBeanBO drop = activityService.receiveGiftBag(userId, req.getCode());
		ActivityAction_receiveGiftBagRes res = new ActivityAction_receiveGiftBagRes();
		res.setDrop(drop);
		
		return res;
	}
	
	/**
	 * 获取每月签到的信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getLoginReward30Info(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		List<UserLoginRewardBO> userLoginRewardList = this.activityService.getLoginReward30Info(userId);
		ActivityAction_getLoginReward30InfoRes res = new ActivityAction_getLoginReward30InfoRes();
		res.setUserLoginRewardList(userLoginRewardList);
		return res;
	}
	
	/**
	 * 领取30天签到奖励
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble receiveLoginReward30(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		ActivityAction_receiveLoginReward30Req req = msg.decodeBody(ActivityAction_receiveLoginReward30Req.class);
		ActivityAction_receiveLoginReward30Res res = this.activityService.receiveLoginReward30(userId, req.getDay());
		
		return res;
	}
	
	/**
	 * 获取用户活跃度信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getActivityTaskInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		ActivityAction_getActivityTaskInfoRes res = this.activityService.getActivityTaskInfo(userId);
		return res;
	}
	
	/**
	 * 领取活跃度奖励
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble receiveActivityTaskReward(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
//		ActivityAction_receiveActivityTaskRewardReq req = new ActivityAction_receiveActivityTaskRewardReq();
		ActivityAction_receiveActivityTaskRewardRes res = this.activityService.receiveActivityTaskReward(userId);
		return res;
	}
}
