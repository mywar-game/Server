package com.fantingame.game.mywar.logic.life.dao.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.life.dao.UserHangupInfoDao;
import com.fantingame.game.mywar.logic.life.model.UserHangupInfo;

public class UserHangupInfoDaoMysqlImpl implements UserHangupInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private static final String table = "user_hangup_info";
	
	public List<UserHangupInfo> getUserHangupInfoList(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserHangupInfo.class, param);
	}

	@Override
	public UserHangupInfo getUserHangupInfo(String userId, int category) {
		String sql = "select * from " + table + " where user_id = ? and category = ? limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(category);
		
		return this.jdbcUser.get(sql, UserHangupInfo.class, param);
	}

	@Override
	public boolean addUserHangupInfo(UserHangupInfo userHangupInfo) {		
		return this.jdbcUser.insert(userHangupInfo) > 0;
	}

	@Override
	public boolean deleteUserHangupInfo(String userId, int category) {
		String sql = "delete from " + table + " where user_id = ? and category = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(category);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateUserHangupInfo(String userId, int category, Date updatedTime, String rewards) {
		String sql = "update " + table + " set rewards = ?, updated_time = ? where user_id = ? and category = ?";
		SqlParameter param = new SqlParameter();
		param.setString(rewards);
		param.setObject(updatedTime);
		param.setString(userId);
		param.setInt(category);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
}
