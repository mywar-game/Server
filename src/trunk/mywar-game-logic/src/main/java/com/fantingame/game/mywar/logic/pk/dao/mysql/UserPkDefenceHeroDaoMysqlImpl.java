package com.fantingame.game.mywar.logic.pk.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.pk.dao.UserPkDefenceHeroDao;
import com.fantingame.game.mywar.logic.pk.model.UserPkDefenceHero;

public class UserPkDefenceHeroDaoMysqlImpl implements UserPkDefenceHeroDao {
	
	@Autowired
	private Jdbc jdbcUser;

	private final static String table = "user_pk_defence_hero";
	
	@Override
	public List<UserPkDefenceHero> getUserPkDefenceHeroList(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserPkDefenceHero.class, param);
	}

	@Override
	public boolean deleteUserPkDefenceHero(String userId) {
		String sql = "delete from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean addUserPkDefenceHero(List<UserPkDefenceHero> defenceList) {
		return this.jdbcUser.insert(defenceList).length > 0;
	}

}
