package com.fantingame.game.mywar.logic.life.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.life.model.UserLifeInfo;

public interface UserLifeInfoDao {

	/**
	 * 获取用户生活技能信息列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserLifeInfo> getUserLifeInfoList(String userId);
	
	/**
	 * 获取用户生活技能信息
	 * 
	 * @param userId
	 * @param category
	 * @return
	 */
	public UserLifeInfo getUserLifeInfo(String userId, int category);
	
	/**
	 * 添加用户生活信息
	 * 
	 * @param userLifeInfo
	 * @return
	 */
	public boolean addUserLifeInfo(UserLifeInfo userLifeInfo);
	
	/**
	 * 删除用户生活信息
	 * 
	 * @param userId
	 * @param category
	 * @return
	 */
	public boolean deleteUserLifeInfo(String userId, int category);
}
