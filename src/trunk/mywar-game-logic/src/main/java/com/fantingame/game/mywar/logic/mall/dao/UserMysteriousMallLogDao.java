package com.fantingame.game.mywar.logic.mall.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.mall.model.UserMysteriousMallLog;

public interface UserMysteriousMallLogDao {

	/**
	 * 获取用户神秘商店的日志列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserMysteriousMallLog> getUserMysteriousMallLogList(String userId);
	
	/**
	 * 用户神秘商店日志列表
	 * 
	 * @param mallLogList
	 * @return
	 */
	public boolean addUserMysteriousLogList(List<UserMysteriousMallLog> mallLogList);
	
	/**
	 * 删除用户神秘商店购买信息
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteUserMysteriousLogList(String userId);
	
	/**
	 * 删除用户神秘商店购买日志
	 * 
	 * @param userId
	 * @param mallId
	 * @return
	 */
	public boolean deleteUserMysteriousLog(String userId, int mallId);
	
	/**
	 * 添加用户神秘商店日志
	 * 
	 * @param mallLog
	 * @return
	 */
	public boolean addUserMysteriousLog(UserMysteriousMallLog mallLog);
	
	/**
	 * 获取用户神秘商店的商品日志
	 * 
	 * @param userId
	 * @param mallId
	 * @return
	 */
	public UserMysteriousMallLog getUserMysteriousMallLog(String userId, int mallId);
}
