package com.fantingame.game.mywar.logic.life.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.life.dao.UserLifeInfoDao;
import com.fantingame.game.mywar.logic.life.model.UserLifeInfo;

public class UserLifeInfoDaoMysqlImpl implements UserLifeInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private static final String table = "user_life_info";
	
	public List<UserLifeInfo> getUserLifeInfoList(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserLifeInfo.class, param);
	}

	@Override
	public UserLifeInfo getUserLifeInfo(String userId, int category) {
		String sql = "select * from " + table + " where user_id = ? and category = ? limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(category);
		
		return this.jdbcUser.get(sql, UserLifeInfo.class, param);
	}

	@Override
	public boolean addUserLifeInfo(UserLifeInfo userLifeInfo) {
		return this.jdbcUser.insert(userLifeInfo) > 0;
	}

	@Override
	public boolean deleteUserLifeInfo(String userId, int category) {
		String sql = "delete from " + table + " where user_id = ? and category = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(category);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
}
