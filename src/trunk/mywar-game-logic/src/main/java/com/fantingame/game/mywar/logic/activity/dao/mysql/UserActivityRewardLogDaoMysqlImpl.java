package com.fantingame.game.mywar.logic.activity.dao.mysql;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.activity.dao.UserActivityRewardLogDao;
import com.fantingame.game.mywar.logic.activity.model.UserActivityRewardLog;

public class UserActivityRewardLogDaoMysqlImpl implements UserActivityRewardLogDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String tableName = "user_activity_reward_log";
	
	@Override
	public List<UserActivityRewardLog> getUserActivityRewardLogList(String userId) {
		String sql = "select * from " + tableName + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserActivityRewardLog.class, param);
	}

	@Override
	public boolean deleteRewardLog(String userId) {
		String sql = "delete from " + tableName + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean addUserActivityRewardLog(UserActivityRewardLog log) {
		return this.jdbcUser.insert(log) > 0;
	}

	@Override
	public Map<String, UserActivityRewardLog> getUserActivityRewardLogMap(String userId) {
		throw new RuntimeException("没有实现，请调用cache中的方法");
	}

	@Override
	public boolean addUserActivityRewardLogList(List<UserActivityRewardLog> logList) {
		return this.jdbcUser.insert(logList).length > 0;
	}

}
