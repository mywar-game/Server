package com.fantingame.game.mywar.logic.task.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.utils.TableUtils;
import com.fantingame.game.mywar.logic.task.dao.UserTaskDao;
import com.fantingame.game.mywar.logic.task.model.UserTask;

public class UserTaskDaoMysqlImpl implements UserTaskDao{
    @Autowired
	private Jdbc jdbcUser;
    
    /**
	 * 表名
	 */
	public final static String tablePrex = "user_task";
	public final static int tableCount = 128;

	/**
	 * 字段列表
	 */
	public final static String columns = "*";
	
	private String getTableName(String userId) {
		return TableUtils.getTableName(userId, tablePrex, tableCount);
	}
	
	public List<UserTask> getList(String userId, int status) {
		SqlParameter parameter = new SqlParameter();
		String sql = "SELECT " + columns + " FROM " + getTableName(userId) + " WHERE user_id = ?";
		parameter.setString(userId);
		if (status != 100) {
			sql += " AND status = ? ";
			parameter.setInt(status);
		}

		return this.jdbcUser.getList(sql, UserTask.class, parameter);
	}

	public boolean update(String userId, int systemTaskId, int finishTimes, int status) {

		String sql = "UPDATE " + getTableName(userId) + " SET finish_times = ? , status = ?, updated_time = now()  WHERE user_id = ? AND system_task_id = ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(finishTimes);
		parameter.setInt(status);
		parameter.setString(userId);
		parameter.setInt(systemTaskId);

		return this.jdbcUser.update(sql, parameter) > 0;
	}

	public boolean update(String userId, int systemTaskId, int status) {

		String sql = "UPDATE " + getTableName(userId) + " SET status = ?, updated_time = now() WHERE user_id = ? AND system_task_id = ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(status);
		parameter.setString(userId);
		parameter.setInt(systemTaskId);

		return this.jdbcUser.update(sql, parameter) > 0;

	}

	@Override
	public boolean delete(String userId, int systemTaskId) {

		/*
		 * String sql = "DELETE FROM " + table +
		 * " WHERE user_id = ? AND system_task_id = ?"; SqlParameter parameter =
		 * new SqlParameter(); parameter.setString(userId);
		 * parameter.setInt(systemTaskId);
		 * 
		 * return this.jdbc.update(sql, parameter) > 0;
		 */

		String sql = "UPDATE " + getTableName(userId) + " SET status = ?, updated_time = now() WHERE user_id = ? AND system_task_id = ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(4);
		parameter.setString(userId);
		parameter.setInt(systemTaskId);

		return this.jdbcUser.update(sql, parameter) > 0;

	}

	public UserTask get(String userId, int systemTaskId) {

		SqlParameter parameter = new SqlParameter();

		String sql = "SELECT " + columns + " FROM " + getTableName(userId) + " WHERE user_id = ? AND system_task_id = ? ";
		parameter.setString(userId);
		parameter.setInt(systemTaskId);

		return this.jdbcUser.get(sql, UserTask.class, parameter);
	}

	public void add(List<UserTask> userTaskList) {
		if (userTaskList == null || userTaskList.size() == 0)
			return;
		
		UserTask userTask = userTaskList.get(0);
		this.jdbcUser.insert(getTableName(userTask.getUserId()), userTaskList);
	}

	@Override
	public boolean addUserTask(UserTask userTask) {
		return this.jdbcUser.insert(getTableName(userTask.getUserId()), userTask) > 0;
	}
}
