package com.fantingame.game.mywar.logic.user.dao.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.user.dao.UserMapInfoDao;
import com.fantingame.game.mywar.logic.user.model.UserMapInfo;

public class UserMapInfoDaoMysqlImpl implements UserMapInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private static final String table = "user_map_info";

	@Override
	public UserMapInfo getUserMapInfo(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.get(sql, UserMapInfo.class, param);
	}

	@Override
	public boolean addUserMapInfo(UserMapInfo userMapInfo) {
		return this.jdbcUser.insert(userMapInfo) > 0;
	}

	@Override
	public boolean updateUserMapInfo(String userId, String maps) {
		String sql = "update " + table + " set maps = ?, updated_time = now() where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(maps);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
	
}
