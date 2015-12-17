package com.fantingame.game.mywar.logic.activity.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.utils.TableUtils;
import com.fantingame.game.mywar.logic.activity.dao.UserActivityTaskDao;
import com.fantingame.game.mywar.logic.activity.model.UserActivityTask;

public class UserActivityTaskDaoMysqlImpl implements UserActivityTaskDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private static final String tablePrex = "user_activity_task";
	private static final int tableCount = 20;
	
	private String getTableName(String userId) {
		return TableUtils.getTableName(userId, tablePrex, tableCount);
	}

	@Override
	public List<UserActivityTask> getUserActivityTaskList(String userId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserActivityTask.class, param);
	}

	@Override
	public boolean deleteUserActivityTaskList(String userId) {
		String sql = "delete from " + getTableName(userId) + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public UserActivityTask getUserActivityTask(String userId, int activityTaskId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and activity_task_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(activityTaskId);
		
		return this.jdbcUser.get(sql, UserActivityTask.class, param);
	}

	@Override
	public boolean addUserActivityTaskList(String userId, List<UserActivityTask> userTaskList) {
		return this.jdbcUser.insert(getTableName(userId), userTaskList).length > 0;
	}

	@Override
	public boolean updateUserActivityTask(String userId, int activityTaskId, int finishTimes, int status) {
		String sql = "update " + getTableName(userId) + " set finish_times = ?, status = ?, updated_time = now() where user_id = ? and activity_task_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(finishTimes);
		param.setInt(status);
		param.setString(userId);
		param.setInt(activityTaskId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
}
