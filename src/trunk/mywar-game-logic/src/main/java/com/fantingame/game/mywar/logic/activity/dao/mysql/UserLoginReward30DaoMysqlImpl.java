package com.fantingame.game.mywar.logic.activity.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.activity.dao.UserLoginReward30Dao;
import com.fantingame.game.mywar.logic.activity.model.UserLoginReward30;

public class UserLoginReward30DaoMysqlImpl implements UserLoginReward30Dao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String tableName = "user_login_reward30";

	@Override
	public List<UserLoginReward30> getUserLoginReward30List(String userId) {
		String sql = "select * from " + tableName + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserLoginReward30.class, param);
	}

	@Override
	public UserLoginReward30 getLastLoginReward30(String userId) {
		String sql = "select * from " + tableName + " where user_id = ?  order by day desc limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.get(sql, UserLoginReward30.class, param);
	}

	@Override
	public boolean addUserLoginReward30(UserLoginReward30 userLoginReward30) {
		return this.jdbcUser.insert(userLoginReward30) > 0;
	}

	@Override
	public boolean deleteAll(String userId) {
		String sql = "delete from " + tableName + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public UserLoginReward30 getUserLoginReward30(String userId, int day) {
		String sql = "select * from " + tableName + " where user_id = ? and day = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(day);
		
		return this.jdbcUser.get(sql, UserLoginReward30.class, param);
	}

	@Override
	public boolean updateUserLoginReward30(String userId, int day, int status) {
		String sql = "update " + tableName + " set status = ?, updated_time = now() where user_id = ? and day = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(status);
		param.setString(userId);
		param.setInt(day);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
}
