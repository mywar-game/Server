package com.fantingame.game.mywar.logic.mall.dao.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.mall.dao.UserBuyBackInfoDao;
import com.fantingame.game.mywar.logic.mall.model.UserBuyBackInfo;

public class UserBuyBackInfoDaoMysql implements UserBuyBackInfoDao {

	@Autowired
	private Jdbc jdbcUser;

	private static final String table = "user_buy_back_info";
	
	@Override
	public List<UserBuyBackInfo> getUserBuyBackInfoList(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserBuyBackInfo.class, param);
	}

	@Override
	public boolean addUserBuyBackInfo(UserBuyBackInfo userBuyBackInfo) {
		return this.jdbcUser.insert(table, userBuyBackInfo) > 0;
	}

	@Override
	public UserBuyBackInfo getUserBuyBackInfo(String userId, String buyBackId) {
		String sql = "select * from " + table + " where user_id = ? and buy_back_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(buyBackId);
		
		return this.jdbcUser.get(sql, UserBuyBackInfo.class, param);
	}

	@Override
	public boolean deleteUserBuyBackInfo(String userId, String buyBackId) {
		String sql = "delete from " + table + " where user_id = ? and buy_back_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(buyBackId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean deleteUserBuyBackInfo(String userId, Date endTime) {
		String sql = "delete from " + table + " where user_id = ? and updated_time <= ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setObject(endTime);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
}
