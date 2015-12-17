package com.fantingame.game.mywar.logic.task.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.task.model.UserTask;


public interface UserTaskDao {

	/**
	 * 获取用户任务列表(100表示所有状态)
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<UserTask> getList(String userId, int status);

	/**
	 * 更新用户任务完成次数，状态
	 * 
	 * @param userId
	 * @param systemTaskId
	 * @param finishTimes
	 * @param status
	 * @return
	 */
	public boolean update(String userId, int systemTaskId, int finishTimes, int status);

	/**
	 * 更新状态
	 * 
	 * @param userId
	 * @param systemTaskId
	 * @param status
	 * @return
	 */
	public boolean update(String userId, int systemTaskId, int status);

	/**
	 * 指增加用主呢任务
	 * 
	 * @param userTaskList
	 * @return
	 */
	public void add(List<UserTask> userTaskList);
	
	/**
	 * 添加任务
	 * 
	 * @param userTask
	 * @return
	 */
	public boolean addUserTask(UserTask userTask);

	/**
	 * 获取用户单个任务
	 * 
	 * @return
	 */
	public UserTask get(String userId, int systemTaskId);

	/**
	 * 删除任务
	 * 
	 * @param userId
	 * @param systemTaskId
	 * @return
	 */
	public boolean delete(String userId, int systemTaskId);

}
