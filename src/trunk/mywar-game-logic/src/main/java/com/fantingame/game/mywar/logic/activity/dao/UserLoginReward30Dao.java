package com.fantingame.game.mywar.logic.activity.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.activity.model.UserLoginReward30;

public interface UserLoginReward30Dao {

	/**
	 * 获取用户的签到信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserLoginReward30> getUserLoginReward30List(String userId);
	
	/**
	 * 获取用户本月最后一次签到信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserLoginReward30 getLastLoginReward30(String userId);
	
	/**
	 * 添加用户签到信息
	 * 
	 * @param userLoginReward30
	 * @return
	 */
	public boolean addUserLoginReward30(UserLoginReward30 userLoginReward30);
	
	/**
	 * 删除所有
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteAll(String userId);
	
	/**
	 * 根据天数获取签到信息
	 * 
	 * @param userId
	 * @param day
	 * @return
	 */
	public UserLoginReward30 getUserLoginReward30(String userId, int day);
	
	/**
	 * 更新用户领取状态
	 * 
	 * @param userId
	 * @param day
	 * @param status
	 * @return
	 */
	public boolean updateUserLoginReward30(String userId, int day, int status);
}
