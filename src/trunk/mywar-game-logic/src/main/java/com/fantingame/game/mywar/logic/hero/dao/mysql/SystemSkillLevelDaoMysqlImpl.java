package com.fantingame.game.mywar.logic.hero.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.hero.dao.SystemSkillLevelDao;
import com.fantingame.game.mywar.logic.hero.model.SystemSkillLevel;

public class SystemSkillLevelDaoMysqlImpl implements SystemSkillLevelDao {

	public final static String table = "system_skill_level";
	
	@Autowired
	private Jdbc jdbcConfig;
	
	@Override
	public List<SystemSkillLevel> getSystemSkillLevelList() {
		String sql = "select * from " + table + " order by level asc";
		return this.jdbcConfig.getList(sql, SystemSkillLevel.class);
	}

	@Override
	public SystemSkillLevel getSystemSkillLevel(int exp, int color) {
		String sql = "select * from " + table + " where exp < ? and color = ? order by level desc limit 1";
		SqlParameter param = new SqlParameter();
		param.setInt(exp);
		param.setInt(color);
		
		return this.jdbcConfig.get(sql, SystemSkillLevel.class, param);
	}

	@Override
	public SystemSkillLevel getSkillExp(int level, int color) {
		String sql = "select * from " + table + " where level = ? and color = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(level);
		param.setInt(color);
		
		return this.jdbcConfig.get(sql, SystemSkillLevel.class, param);
	}

}
