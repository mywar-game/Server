package com.fantingame.game.mywar.logic.hero.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.hero.dao.UserCareerInfoDao;
import com.fantingame.game.mywar.logic.hero.model.UserCareerInfo;

public class UserCareerInfoDaoMysqlImpl implements UserCareerInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String tableName = "user_career_info";

	@Override
	public List<UserCareerInfo> getUserCareerInfoList(String userId) {
		String sql = "select * from " + tableName + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserCareerInfo.class, param);
	}

	@Override
	public boolean addList(String userId, List<UserCareerInfo> infoList) {
		return this.jdbcUser.insert(tableName, infoList).length > 0;
	}

	@Override
	public UserCareerInfo getUserCareerInfo(String userId, int detailedCareerId) {
		String sql = "select * from " + tableName + " user_id = ? and detailed_career_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(detailedCareerId);
		
		return this.jdbcUser.get(sql, UserCareerInfo.class, param);
	}

	@Override
	public boolean updateUserCareerInfo(String userId, int detailedCareerId, int jobLevel) {
		String sql = "update " + tableName + " set level = ?, updated_time = now() where user_id = ? and detailed_career_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(jobLevel);
		param.setString(userId);
		param.setInt(detailedCareerId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
}
