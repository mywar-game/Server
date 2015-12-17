package com.fantingame.game.mywar.logic.task.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.task.model.UserDailyTaskList;


public interface UserDailyTaskListDao {

	/**
	 * 获取用户刷新任务的列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserDailyTaskList> getUserDailyTaskList(String userId);
	
	/**
	 * 获取日常任务
	 * 
	 * @param userId
	 * @param systemTaskId
	 * @return
	 */
	public UserDailyTaskList getUserDailyTaskList(String userId, int systemTaskId);
	
	/**
	 * 批量添加
	 * 
	 * @param list
	 * @return
	 */
	public boolean addList(String userId, List<UserDailyTaskList> list);
	
	/**
	 * 删除用户刷新的列表
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteUserDailyTaskList(String userId);
	
	/**
	 * 删除非这个任务的列表
	 * 
	 * @param userId
	 * @param systemTaskId
	 * @return
	 */
	public boolean deleteUserDailyTaskList(String userId, int systemTaskId);
	
	/**
	 * 更新日常信息的星级
	 * 
	 * @param userId
	 * @param systemTaskId
	 * @return
	 */
	public boolean updateUserDailyTaskList(String userId, int systemTaskId, int fiveStar);
}
