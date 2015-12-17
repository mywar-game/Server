package com.fantingame.game.mywar.logic.pk.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.pk.dao.UserPkLogDao;
import com.fantingame.game.mywar.logic.pk.model.UserPkLog;

public class UserPkLogDaoMysqlImpl implements UserPkLogDao {

	@Autowired
	private Jdbc jdbcUser;

	private final static String table = "user_pk_log";
	
	@Override
	public boolean addUserPkLog(UserPkLog userPkLog) {
		return this.jdbcUser.insert(userPkLog) > 0;
	}

	@Override
	public List<UserPkLog> getUserPkLogList(String userId) {
		String sql = "select * from " + table + " where user_id = ? or target_user_id = ? order by created_time desc";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserPkLog.class, param);
	}
	
}
