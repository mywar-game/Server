package com.fantingame.game.mywar.logic.friend.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.friend.model.UserBlackInfo;

public interface UserBlackInfoDao {

	/**
	 * 获取用户黑名单列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserBlackInfo> getUserBlackInfoList(String userId);
	
	/**
	 * 获取黑名单
	 * 
	 * @param userId
	 * @param userBlackId
	 * @return
	 */
	public UserBlackInfo getUserBlackInfo(String userId, String userBlackId);
	
	/**
	 * 获取用户黑名单数量
	 * 
	 * @param userId
	 * @return
	 */
	public int getUserBlackListCount(String userId);
	
	/**
	 * 添加黑名单
	 * 
	 * @param userBlackInfo
	 * @return
	 */
	public boolean add(UserBlackInfo userBlackInfo);
	
	/**
	 * 删除黑名单
	 * 
	 * @param userId
	 * @param userBlackId
	 * @return
	 */
	public boolean deleteBlackInfo(String userId, String userBlackId);
	
	/**
	 * 获取你被谁拉黑列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserBlackInfo> getInTargetBlackList(String userId);
	
	/**
	 * 获取你被谁拉黑列表
	 * 
	 * @param userId
	 * @return
	 */
//	public List<String> getInTargetBlackIds(String userId);
}
