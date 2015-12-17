package com.fantingame.game.mywar.logic.task.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.task.dao.UserDailyTaskListDao;
import com.fantingame.game.mywar.logic.task.model.UserDailyTaskList;

public class UserDailyTaskListDaoMysql implements UserDailyTaskListDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private static final String table = "user_daily_task_list";

	@Override
	public List<UserDailyTaskList> getUserDailyTaskList(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserDailyTaskList.class, param);
	}

	@Override
	public boolean addList(String userId, List<UserDailyTaskList> list) {
		return this.jdbcUser.insert(list).length > 0;
	}

	@Override
	public boolean deleteUserDailyTaskList(String userId) {
		String sql = "delete from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean deleteUserDailyTaskList(String userId, int systemTaskId) {
		String sql = "delete from " + table + " where user_id = ? and system_task_id <> ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(systemTaskId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateUserDailyTaskList(String userId, int systemTaskId, int fiveStar) {
		String sql = "update " + table + " set star = ?, updated_time = now() where user_id = ? and system_task_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(fiveStar);
		param.setString(userId);
		param.setInt(systemTaskId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public UserDailyTaskList getUserDailyTaskList(String userId, int systemTaskId) {
		String sql = "select * from " + table + " user_id = ? and system_task_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(systemTaskId);
		
		return this.jdbcUser.get(sql, UserDailyTaskList.class, param);
	}
	
}
