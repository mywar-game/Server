package com.fantingame.game.common.dao.mysql;


import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.dao.RuntimeDataDao;
import com.fantingame.game.common.model.RuntimeData;

public class RunTimeDataDaoMysqlImpl implements RuntimeDataDao {

	@Autowired
	private Jdbc jdbcUser;

	public final static String table = "runtime_data";

	public final static String columns = "*";

	@Override
	public boolean add(RuntimeData runtimeData) {
		return this.jdbcUser.insert(runtimeData) > 0;
	}

	@Override
	public RuntimeData get(String key) {

		String sql = "SELECT " + columns + " FROM " + table + " WHERE value_key = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(key);

		return this.jdbcUser.get(sql, RuntimeData.class, parameter);
	}

	@Override
	public boolean inc(String key) {

		String sql = "UPDATE " + table + " SET value = value + 1 WHERE value_key = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(key);

		return this.jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean set(String key, long value) {

		String sql = "UPDATE " + table + " SET value = ? WHERE value_key = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setLong(value);
		parameter.setString(key);

		return this.jdbcUser.update(sql, parameter) > 0;
	}

}
