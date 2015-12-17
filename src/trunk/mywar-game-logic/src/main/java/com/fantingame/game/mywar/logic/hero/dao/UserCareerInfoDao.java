package com.fantingame.game.mywar.logic.hero.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.hero.model.UserCareerInfo;

public interface UserCareerInfoDao {

	/**
	 * 获取用户职业信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserCareerInfo> getUserCareerInfoList(String userId);
	
	/**
	 * 批量添加用户职业信息
	 * 
	 * @param userId
	 * @param infoList
	 * @return
	 */
	public boolean addList(String userId, List<UserCareerInfo> infoList);
	
	/**
	 * 获取用户职业解锁信息
	 * 
	 * @param userId
	 * @param detailedCareerId
	 * @return
	 */
	public UserCareerInfo getUserCareerInfo(String userId, int detailedCareerId);
	
	/**
	 * 更新用户职业信息
	 * 
	 * @param userId
	 * @param detailedCareerId
	 * @param addLevel
	 * @return
	 */
	public boolean updateUserCareerInfo(String userId, int detailedCareerId, int jobLevel);
}
