package com.fantingame.game.mywar.logic.explore.dao.mysql;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.explore.dao.UserExploreInfoDao;
import com.fantingame.game.mywar.logic.explore.model.UserExploreInfo;

public class UserExploreInfoDaoMysqlImpl implements UserExploreInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String table = "user_explore_info";
	
	@Override
	public UserExploreInfo getUserExploreInfo(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.get(sql, UserExploreInfo.class, param);
	}

	@Override
	public boolean addUserExploreInfo(UserExploreInfo info) {
		return this.jdbcUser.insert(info) > 0;
	}

	@Override
	public boolean updateUserExploreInfo(String userId, int mapId, int exploreTimes, Date date) {
		String sql = "update " + table + " set map_id = ?, explore_times = ?, updated_time = ? where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(mapId);
		param.setInt(exploreTimes);
		param.setObject(date);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean reduceIntegral(String userId, int integral) {
		if (integral < 0)
			throw new RuntimeException("减的积分必须大于0，integral[" + integral + "]");
		
		String sql = "update " + table + " set integral = integral - ? where user_id = ? and integral - ? >= 0 limit 1";
		SqlParameter param = new SqlParameter();
		param.setInt(integral);
		param.setString(userId);
		param.setInt(integral);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateExploreMap(String userId, int mapId, int score) {
		String sql = "update " + table + " set map_id = ?, integral = integral + ? where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(mapId);
		param.setInt(score);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
}
