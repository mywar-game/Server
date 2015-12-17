package com.fantingame.game.mywar.logic.achievement.dao;

import java.util.List;
import java.util.Map;

import com.fantingame.game.mywar.logic.achievement.model.UserAchievement;

public interface UserAchievementDao {

	/**
	 * 获取用户成就信息列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserAchievement> getUserAchievementList(String userId);
	
	/**
	 * 获取用户成就信息
	 * 
	 * @param userId
	 * @param achievementId
	 * @return
	 */
	public UserAchievement getUserAchievement(String userId, int achievementId);
	
	/**
	 * 更新用户成就信息
	 * 
	 * @param userId
	 * @param achievementId
	 * @param finishTimes
	 * @param status
	 * @return
	 */
	public boolean updateUserAchievement(String userId, int achievementId, int finishTimes, int status);

	/**
	 * 根据状态获取用户成就信息
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<UserAchievement> getUserAchievementList(String userId, int status);
	
	/**
	 * 批量添加用户成就信息
	 * 
	 * @param achievementList
	 * @return
	 */
	public boolean addList(String userId, List<UserAchievement> achievementList);
	
	/**
	 * 获取用户成就
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, UserAchievement> getUserAchievementMap(String userId);
}
