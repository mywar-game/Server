package com.fantingame.game.mywar.logic.task.dao;

import com.fantingame.game.mywar.logic.task.model.UserDailyTaskInfo;

public interface UserDailyTaskInfoDao {

	/**
	 * 获取用户日常任务信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserDailyTaskInfo getUserDailyTaskInfo(String userId);
	
	/**
	 * 添加用户日常任务信息
	 * 
	 * @param userDailyTaskInfo
	 * @return
	 */
	public boolean addUserDailyTaskInfo(UserDailyTaskInfo userDailyTaskInfo);
	
	/**
	 * 更新次数
	 * 
	 * @param userId
	 * @param times
	 * @return
	 */
	public boolean updateUserDailyTaskInfo(String userId, int times, int systemTaskId);
}
