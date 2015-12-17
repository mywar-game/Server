package com.fantingame.game.mywar.logic.friend.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.friend.model.UserFriendInfo;

public interface UserFriendInfoDao {

	/**
	 * 获取用户好友列表
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<UserFriendInfo> getUserFriendList(String userId, int status);
	
	/**
	 * 获取用户好友数量
	 * 
	 * @param userId
	 * @return
	 */
	public int getUserFriendCount(String userId);
	
	/**
	 * 获取好友
	 * 
	 * @param userId
	 * @param targetUserId
	 * @return
	 */
	public UserFriendInfo getUserFriendInfo(String userId, String targetUserId);
	
	/**
	 * 添加用户好友信息
	 * 
	 * @param userFriendInfo
	 * @return
	 */
	public boolean add(UserFriendInfo userFriendInfo);
	
	/**
	 * 删除好友
	 * 
	 * @param userId
	 * @param targetUserId
	 * @return
	 */
	public boolean deleteFriend(String userId, String targetUserId);
	
	/**
	 * 更新好友信息状态
	 * 
	 * @param info
	 * @param status
	 * @return
	 */
	public boolean updateUserFriendStatus(UserFriendInfo info, int status);
}
