package com.fantingame.game.mywar.logic.hero.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.hero.dao.UserHeroSkillDao;
import com.fantingame.game.mywar.logic.hero.model.UserHeroSkill;

public class UserHeroSkillDaoMysqlImpl implements UserHeroSkillDao{
    @Autowired
	private Jdbc jdbcUser;
    
    private static final String table = "user_hero_skill";
    
	@Override
	public List<UserHeroSkill> getUserHeroSkillList(String userId) {
		String sql = "select * from " + table + " where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		return jdbcUser.getList(sql, UserHeroSkill.class, parameter);
	}

	@Override
	public List<UserHeroSkill> getUserHeroSkillList(String userId,
			String userHeroId) {
		throw new RuntimeException("未实现的方法！请使用cache中的查询方法");
	}

	@Override
	public boolean updateUserHeroSkillPos(String userId,String userHeroSkillId, int pos) {
		String sql = "update "+table+" set pos = ?, updated_time = now() where user_hero_skill_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(pos);
		parameter.setString(userHeroSkillId);
		return jdbcUser.update(sql, parameter)>0;
	}

	@Override
	public boolean updateUserHeroSkillLevel(String userId,String userHeroSkillId, int level) {
		String sql = "update "+table+" set skill_level = ?, updated_time = now() where user_hero_skill_id = ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(level);
		parameter.setString(userHeroSkillId);
		return jdbcUser.update(sql, parameter)>0;
	}

	@Override
	public boolean updateUserHeroId(String userId, String userHeroSkillId,
			String userHeroId) {
		String sql = "update "+table+" set user_hero_id =?, updated_time=now() where user_hero_skill_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userHeroId);
		parameter.setString(userHeroSkillId);
		return jdbcUser.update(sql, parameter)>0;
	}
	@Override
	public boolean addUserHeroSkill(UserHeroSkill userHeroSkill) {
		return jdbcUser.insert(userHeroSkill)>0;
	}

	@Override
	public boolean addUserHeroSkillList(String userId,
			List<UserHeroSkill> userHeroSkillList) {
		 jdbcUser.insert(userHeroSkillList);
		 return true;
	}

	@Override
	public UserHeroSkill getUserHeroSkill(String userId, int systemHeroSkillId) {
		throw new RuntimeException("未实现的方法！请使用cache中的查询方法");
	}

	@Override
	public boolean updateUserHeroSkill(String userId, String userHeroSkillId, int level, int exp) {
		String sql = "update " + table + " set skill_level = ?, skill_exp = ?, updated_time = now() where user_hero_skill_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(level);
		param.setInt(exp);
		param.setString(userHeroSkillId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public UserHeroSkill getUserHeroSkill(String userId, String userHeroSkillId) {
		throw new RuntimeException("未实现的方法！请使用cache中的查询方法");
	}
}
