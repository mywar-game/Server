package com.fantingame.game.mywar.logic.hero.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.utils.TableUtils;
import com.fantingame.game.mywar.logic.hero.dao.UserHeroDao;
import com.fantingame.game.mywar.logic.hero.model.UserHero;

public class UserHeroDaoMysqlImpl implements UserHeroDao{
	@Autowired
	private Jdbc jdbcUser;
	
	private static final String tablePrex = "user_hero";
	private static final int tableCount = 128;
	
	private String getTableName(String userId) {
		return TableUtils.getTableName(userId, tablePrex, tableCount);
	}
	
	@Override
	public List<UserHero> getUserHeroList(String userId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		return jdbcUser.getList(sql, UserHero.class, parameter);
	}

	@Override
	public int getUserHeroCount(String userId, int status) {
		String sql = "select count(*) from " + getTableName(userId) + " where user_id = ? and status = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(status);
		
		return jdbcUser.getInt(sql, param);
	}
	
	@Override
	public boolean addUserHero(UserHero userHero) {
		return jdbcUser.insert(getTableName(userHero.getUserId()), userHero)>0;
	}

	@Override
	public boolean addBatchUserHero(String userId, List<UserHero> userHero) {		
		return jdbcUser.insert(getTableName(userId), userHero).length > 0;
	}

	@Override
	public boolean updateHeroSystemHeroId(String userId, String userHeroId,
			int systemHeroId) {
		String sql = "update " + getTableName(userId) + " set system_hero_id = ?, updated_time = now() where user_hero_id = ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(systemHeroId);
		parameter.setString(userHeroId);
		return jdbcUser.update(sql, parameter)>0;
	}

	@Override
	public boolean updateHeroExpAndLevel(String userId, String userHeroId,
			int exp, int level) {
		String sql = "update " + getTableName(userId) + " set level = ?,exp = ?, updated_time=now() where user_hero_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(level);
		parameter.setInt(exp);
		parameter.setString(userHeroId);
		return jdbcUser.update(sql, parameter)>0;
	}

	@Override
	public UserHero getUserHero(String userId, String userHeroId) {
		throw new RuntimeException("没有实现，请调用cache中的方法");
	}

	@Override
	public boolean updateHeroEffective(String userId, String userHeroId,
			int effective) {
		String sql = "update "+ getTableName(userId) +" set effective=?,updated_time=now() where user_hero_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(effective);
		parameter.setString(userHeroId);
		return jdbcUser.update(sql, parameter)>0;
	}

	@Override
	public UserHero getUserHeroByPos(String userId, int pos) {
		throw new RuntimeException("没有实现，请调用cache中的方法");
	}
	
	@Override
	public boolean changePos(String userId, String userHeroId, int pos) {
		String sql = "UPDATE " + getTableName(userId) + " SET pos = ? WHERE user_hero_id = ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(pos);
		parameter.setString(userHeroId);
		return this.jdbcUser.update(sql, parameter) > 0;
	}
	
	@Override
	public boolean changeSystemHeroId(String userId, String userHeroId, int systemHeroId) {
		String sql = "UPDATE " + getTableName(userId) + " SET system_hero_id = ? WHERE user_hero_id = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(systemHeroId);
		parameter.setString(userHeroId);
		return this.jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public UserHero getUserHero(String userId, int systemHeroId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and system_hero_id = ? limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(systemHeroId);
		
		return jdbcUser.get(sql, UserHero.class, param);
	}

	@Override
	public UserHero getTeamLeaderHero(String userId, int teamLeader) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and is_scene = ? limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(teamLeader);
		
		return jdbcUser.get(sql, UserHero.class, param);
	}

	@Override
	public boolean changeTeamLeader(String userId, String userHeroId, int teamLeader) {
		String sql = "update " + getTableName(userId) + " set is_scene = ? where user_id = ? and user_hero_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(teamLeader);
		param.setString(userId);
		param.setString(userHeroId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateHeroStatus(String userId, String userHeroId, int status) {
		String sql = "update " + getTableName(userId) + " set status = ? where user_id = ? and user_hero_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(status);
		param.setString(userId);
		param.setString(userHeroId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateHeroStar(String userId, String userHeroId, int star) {
		String sql = "update " + getTableName(userId) + " set star = ? where user_id = ? and user_hero_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(star);
		param.setString(userId);
		param.setString(userHeroId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

}
