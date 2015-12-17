package com.fantingame.game.mywar.logic.activity.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.activity.model.UserActivityTask;

public interface UserActivityTaskDao {
	
	/**
	 * 获取用户活跃度
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserActivityTask> getUserActivityTaskList(String userId);

	/**
	 * 删除用户活跃度信息
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteUserActivityTaskList(String userId);
	
	/**
	 * 获取用户活跃度
	 * 
	 * @param userId
	 * @param activityTaskId
	 * @return
	 */
	public UserActivityTask getUserActivityTask(String userId, int activityTaskId);
	
	/**
	 * 批量添加
	 * 
	 * @param userId
	 * @param userTaskList
	 * @return
	 */
	public boolean addUserActivityTaskList(String userId, List<UserActivityTask> userTaskList);
	
	/**
	 * 更新用户活跃度信息
	 * 
	 * @param userId
	 * @param activityTaskId
	 * @param finishTimes
	 * @param status
	 * @return
	 */
	public boolean updateUserActivityTask(String userId, int activityTaskId, int finishTimes, int status);
}
