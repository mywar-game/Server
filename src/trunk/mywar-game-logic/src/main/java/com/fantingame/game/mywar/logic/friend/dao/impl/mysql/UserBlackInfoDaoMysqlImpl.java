package com.fantingame.game.mywar.logic.friend.dao.impl.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.friend.dao.UserBlackInfoDao;
import com.fantingame.game.mywar.logic.friend.model.UserBlackInfo;

public class UserBlackInfoDaoMysqlImpl implements UserBlackInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	private static final String table = "user_black_info";
	
	public List<UserBlackInfo> getUserBlackInfoList(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserBlackInfo.class, param);
	}

	@Override
	public UserBlackInfo getUserBlackInfo(String userId, String userBlackId) {
		String sql = "select * from " + table + " where user_id = ? and user_black_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(userBlackId);
		
		return this.jdbcUser.get(sql, UserBlackInfo.class, param);
	}

	@Override
	public int getUserBlackListCount(String userId) {
		String sql = "select count(*) from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getInt(sql, param);
	}

	@Override
	public boolean add(UserBlackInfo userBlackInfo) {
		return this.jdbcUser.insert(userBlackInfo) > 0;
	}

	@Override
	public boolean deleteBlackInfo(String userId, String userBlackId) {
		String sql = "delete from " + table + " where user_id = ? and user_black_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(userBlackId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserBlackInfo> getInTargetBlackList(String userId) {
		String sql = "select * from " + table + " where user_black_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserBlackInfo.class, param);
	}

//	@Override
//	public List<String> getInTargetBlackIds(String userId) {
//		String sql = "select user_id from " + table + " where user_black_id = ?";
//		SqlParameter param = new SqlParameter();
//		param.setString(userId);
//		
//		return this.jdbcUser.getList(sql, String.class, param);
//	}
	
}
