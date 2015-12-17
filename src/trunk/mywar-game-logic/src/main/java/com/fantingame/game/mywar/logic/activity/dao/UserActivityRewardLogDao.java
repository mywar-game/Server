package com.fantingame.game.mywar.logic.activity.dao;

import java.util.List;
import java.util.Map;

import com.fantingame.game.mywar.logic.activity.model.UserActivityRewardLog;

public interface UserActivityRewardLogDao {

	/**
	 * 获取用户活跃度领奖列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserActivityRewardLog> getUserActivityRewardLogList(String userId);
	
	/**
	 * 删除用户奖励日志
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteRewardLog(String userId);
	
	/**
	 * 添加
	 * 
	 * @param log
	 * @return
	 */
	public boolean addUserActivityRewardLog(UserActivityRewardLog log);
	
	/**
	 * 批量添加
	 * 
	 * @param userId
	 * @param logList
	 * @return
	 */
	public boolean addUserActivityRewardLogList(List<UserActivityRewardLog> logList);
	
	/**
	 * 获取map
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, UserActivityRewardLog> getUserActivityRewardLogMap(String userId);
}
