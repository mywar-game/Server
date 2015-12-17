package com.fantingame.game.mywar.logic.mall.dao.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.mall.dao.UserMysteriousMallMapDao;
import com.fantingame.game.mywar.logic.mall.model.UserMysteriousMallMap;

public class UserMysteriousMallMapDaoMysqlImpl implements UserMysteriousMallMapDao {

	@Autowired
	private Jdbc jdbcUser;

	private final static String table = "user_mysterious_mall_map";
	
	@Override
	public UserMysteriousMallMap getUserMysteriousMallMap(int mapId) {
		String sql = "select * from " + table + " where map_id = ?";		
		SqlParameter param = new SqlParameter();
		param.setInt(mapId);
		
		return this.jdbcUser.get(sql, UserMysteriousMallMap.class, param);
	}

	@Override
	public UserMysteriousMallMap getUserMysteriousMallMap() {
		String sql = "select * from " + table + " order by updated_time desc limit 1";
		SqlParameter param = new SqlParameter();
		
		return this.jdbcUser.get(sql, UserMysteriousMallMap.class, param);
	}

	@Override
	public boolean delete(int mapId) {
		String sql = "delete from " + table;
		return this.jdbcUser.update(sql, new SqlParameter()) > 0;
	}

	@Override
	public boolean save(UserMysteriousMallMap userMysteriousMallMap) {
		return this.jdbcUser.insert(userMysteriousMallMap) > 0;
	}
	
}
