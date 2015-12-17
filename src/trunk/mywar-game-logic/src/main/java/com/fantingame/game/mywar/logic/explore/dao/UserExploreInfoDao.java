package com.fantingame.game.mywar.logic.explore.dao;

import java.util.Date;

import com.fantingame.game.mywar.logic.explore.model.UserExploreInfo;

public interface UserExploreInfoDao {

	/**
	 * 获取用户探索信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserExploreInfo getUserExploreInfo(String userId);
	
	/**
	 * 添加用户探索信息
	 * 
	 * @param info
	 * @return
	 */
	public boolean addUserExploreInfo(UserExploreInfo info);
	
	/**
	 * 更新用户探索信息
	 * 
	 * @param mapId
	 * @param exploreTimes
	 * @param date
	 * @return
	 */
	public boolean updateUserExploreInfo(String userId, int mapId, int exploreTimes, Date date);

	/**
	 * 扣除用户探索积分
	 * 
	 * @param userId
	 * @param integral
	 * @return
	 */
	public boolean reduceIntegral(String userId, int integral);
	
	/**
	 * 更新用户的探索地图
	 * 
	 * @param userId
	 * @param mapId
	 * @return
	 */
	public boolean updateExploreMap(String userId, int mapId, int score);
}
