package com.fantingame.game.mywar.logic.mall.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.mall.dao.UserMysteriousMallLogDao;
import com.fantingame.game.mywar.logic.mall.model.UserMysteriousMallLog;

public class UserMysteriousMallLogDaoMysqlImpl implements UserMysteriousMallLogDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String table = "user_mysterious_mall_log";
	
	@Override
	public List<UserMysteriousMallLog> getUserMysteriousMallLogList(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserMysteriousMallLog.class, param);
	}

	@Override
	public boolean addUserMysteriousLogList(List<UserMysteriousMallLog> mallLogList) {
		return this.jdbcUser.insert(mallLogList).length > 0;
	}

	@Override
	public boolean deleteUserMysteriousLogList(String userId) {
		String sql = "delete from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean deleteUserMysteriousLog(String userId, int mallId) {
		String sql = "delete from " + table + " where user_id = ? and mall_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(mallId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean addUserMysteriousLog(UserMysteriousMallLog mallLog) {
		return this.jdbcUser.insert(mallLog) > 0;
	}

	@Override
	public UserMysteriousMallLog getUserMysteriousMallLog(String userId, int mallId) {
		String sql = "select * from " + table + " where user_id = ? and mall_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(mallId);
		
		return this.jdbcUser.get(sql, UserMysteriousMallLog.class, param);
	}

}
