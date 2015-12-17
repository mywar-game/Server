package com.fantingame.game.mywar.logic.pk.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.pk.dao.UserPkMallLogDao;
import com.fantingame.game.mywar.logic.pk.model.UserPkMallLog;

public class UserPkMallLogDaoMysqlImpl implements UserPkMallLogDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String table = "user_pk_mall_log";

	@Override
	public List<UserPkMallLog> getUserPkMallLogList(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserPkMallLog.class, param);
	}

	@Override
	public boolean addList(List<UserPkMallLog> logList) {
		return this.jdbcUser.insert(logList).length > 0;
	}

	@Override
	public boolean updateAllList(String userId) {
		String sql = "update " + table + " day_buy_num = 0, updated_time = now() where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public UserPkMallLog getUserPkMallLog(String userId, int mallId) {
		String sql = "select * from " + table + " where user_id = ? and mall_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(mallId);
		
		return this.jdbcUser.get(sql, UserPkMallLog.class, param);
	}

	@Override
	public boolean update(String userId, int mallId, int dayBuyNum, int totalBuyNum) {
		String sql = "update " + table + " set day_buy_num = ?, total_buy_num = ?, updated_time = now() where user_id = ? and mall_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(dayBuyNum);
		param.setInt(totalBuyNum);
		param.setString(userId);
		param.setInt(mallId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
}
