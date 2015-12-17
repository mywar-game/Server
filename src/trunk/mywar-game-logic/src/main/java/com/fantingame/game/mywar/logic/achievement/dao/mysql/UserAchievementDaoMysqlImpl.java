package com.fantingame.game.mywar.logic.achievement.dao.mysql;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.utils.TableUtils;
import com.fantingame.game.mywar.logic.achievement.dao.UserAchievementDao;
import com.fantingame.game.mywar.logic.achievement.model.UserAchievement;

public class UserAchievementDaoMysqlImpl implements UserAchievementDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private static final String tablePrex = "user_achievement";
	private static final int tableCount = 128;
	
	private String getTableName(String userId) {
		return TableUtils.getTableName(userId, tablePrex, tableCount);
	}

	@Override
	public List<UserAchievement> getUserAchievementList(String userId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserAchievement.class, param);
	}

	@Override
	public UserAchievement getUserAchievement(String userId, int achievementId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and achievement_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(achievementId);
		
		return this.jdbcUser.get(sql, UserAchievement.class, param);
	}

	@Override
	public boolean updateUserAchievement(String userId, int achievementId, int finishTimes, int status) {
		String sql = "update " + getTableName(userId) + " set finish_times = ?, status = ?, updated_time = now() where user_id = ? and achievement_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(finishTimes);
		param.setInt(status);
		param.setString(userId);
		param.setInt(achievementId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserAchievement> getUserAchievementList(String userId, int status) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and status = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(status);
		
		return this.jdbcUser.getList(sql, UserAchievement.class, param);
	}

	@Override
	public boolean addList(String userId, List<UserAchievement> achievementList) {
		return this.jdbcUser.insert(getTableName(userId), achievementList).length > 0;
	}

	@Override
	public Map<String, UserAchievement> getUserAchievementMap(String userId) {
		throw new RuntimeException("没有实现，请调用cache中的方法");
	}
	
}
