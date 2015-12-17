package com.fantingame.game.mywar.logic.task.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.task.dao.UserDailyTaskInfoDao;
import com.fantingame.game.mywar.logic.task.model.UserDailyTaskInfo;

public class UserDailyTaskInfoDaoMysql implements UserDailyTaskInfoDao {
	
	@Autowired
	private Jdbc jdbcUser;

	private static final String table = "user_daily_task_info";
	
	public List<UserDailyTaskInfo> getUserDailyTaskInfoList() {
		String sql = "select * from " + table;
		return this.jdbcUser.getList(sql, UserDailyTaskInfo.class);
	}

	@Override
	public UserDailyTaskInfo getUserDailyTaskInfo(String userId) {
		String sql = "select * from " + table + " where user_id = ? limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.get(sql, UserDailyTaskInfo.class, param);
	}

	@Override
	public boolean addUserDailyTaskInfo(UserDailyTaskInfo userDailyTaskInfo) {
		return this.jdbcUser.insert(userDailyTaskInfo) > 0;
	}

	@Override
	public boolean updateUserDailyTaskInfo(String userId, int times, int systemTaskId) {
		String sql = "update " + table + " set times = ?, system_task_id = ?, updated_time = now() where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(times);
		param.setInt(systemTaskId);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
	
}
