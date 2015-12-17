package com.fantingame.game.mywar.logic.friend.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.friend.BattleUserInfoBO;
import com.fantingame.game.msgbody.client.friend.FriendAction_addBlackReq;
import com.fantingame.game.msgbody.client.friend.FriendAction_applyFriendReq;
import com.fantingame.game.msgbody.client.friend.FriendAction_auditApplyReq;
import com.fantingame.game.msgbody.client.friend.FriendAction_auditApplyRes;
import com.fantingame.game.msgbody.client.friend.FriendAction_deleteBlackReq;
import com.fantingame.game.msgbody.client.friend.FriendAction_deleteBlackRes;
import com.fantingame.game.msgbody.client.friend.FriendAction_getJoinBattleUserListRes;
import com.fantingame.game.msgbody.client.friend.FriendAction_getUserBlackListRes;
import com.fantingame.game.msgbody.client.friend.FriendAction_getUserFriendListRes;
import com.fantingame.game.msgbody.client.friend.FriendAction_getUserFriendRankListRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.friend.service.FriendService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.MsgBuilder;

/**
 * 好友 
 * 
 * @author yezp
 */
@GameAction
public class FriendAction {

	@Autowired
	private FriendService friendService;
	
	/**
	 * 获取好友列表
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserFriendList(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		FriendAction_getUserFriendListRes res = this.friendService.getUserFriendList(userId);
		return res;
	}
	
	/**
	 * 获取好友排行榜
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserFriendRankList(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		FriendAction_getUserFriendRankListRes res = this.friendService.getUserFriendRankList(userId);
		return res;		
	}
	
	/**
	 * 获取用户黑名单列表
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserBlackList(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		FriendAction_getUserBlackListRes res = this.friendService.getUserBlackList(userId);
		return res;
	}
	
	/**
	 * 申请添加好友
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble applyFriend(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		FriendAction_applyFriendReq req = msg.decodeBody(FriendAction_applyFriendReq.class);
		
		this.friendService.applyFriend(userId, req.getTargetUserId(), req.getName());
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 审核好友申请
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble auditApply(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		FriendAction_auditApplyReq req = msg.decodeBody(FriendAction_auditApplyReq.class);
		
		FriendAction_auditApplyRes res = this.friendService.auditApply(userId, req.getType(), req.getUserMailId());
		return res;
	}
	
	/**
	 * 添加黑名单
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble addBlack(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		FriendAction_addBlackReq req = msg.decodeBody(FriendAction_addBlackReq.class);
		
		this.friendService.addBlack(userId, req.getTargetUserId());
		return MsgBuilder.EMPTY_BODY;
	}	
	
	/**
	 * 删除黑名单
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble deleteBlack(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		FriendAction_deleteBlackReq req = msg.decodeBody(FriendAction_deleteBlackReq.class);
		
		FriendAction_deleteBlackRes res = this.friendService.deleteBlack(userId, req.getUserBlackId());
		return res;
	}
	
	/**
	 * 获取可参战的用户
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getJoinBattleUserList(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		List<BattleUserInfoBO> battleList = this.friendService.getJoinBattleUserList(userId);
		
		FriendAction_getJoinBattleUserListRes res = new FriendAction_getJoinBattleUserListRes();
		res.setBattleUserList(battleList);
		return res;		
	}

} 
