package com.fantingame.game.mywar.logic.friend.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.friend.model.UserInviteFightInfo;

public interface UserInviteFightInfoDao {

	/**
	 * 获取用户邀请好友战斗信息列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserInviteFightInfo> getUserInviteFightInfoList(String userId);
	
	/**
	 * 删除用户邀请好友战斗信息
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteInviteFightList(String userId);
	
	/**
	 * 获取用户邀请好友战斗信息
	 * 
	 * @param userId
	 * @param userFriendId
	 * @return
	 */
	public UserInviteFightInfo getUserInviteFightInfo(String userId, String userFriendId);
	
	/**
	 * 添加用户邀请好友战斗信息
	 * 
	 * @param inviteFight
	 * @return
	 */
	public boolean addUserInviteFightInfo(UserInviteFightInfo inviteFight);
}
