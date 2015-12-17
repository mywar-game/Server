package com.fantingame.game.mywar.logic.charge.dao.mysql;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.charge.dao.SystemGoldSetDao;
import com.fantingame.game.mywar.logic.charge.model.SystemGoldSet;

public class SystemGoldSetDaoMysqlImpl implements SystemGoldSetDao {

	@Autowired
	private Jdbc jdbcConfig;

	public final static String table = "system_gold_set";

	public final static String columns = "*";

	@Override
	public List<SystemGoldSet> getList() {
		String sql = "SELECT " + columns + " FROM " + table + " ORDER BY gold DESC";
		SqlParameter parameter = new SqlParameter();
		return jdbcConfig.getList(sql, SystemGoldSet.class, parameter);
	}

	@Override
	public SystemGoldSet getByPayAmount(double amount, String partnerId) {
		String sql = "SELECT " + columns + " FROM " + table + " WHERE money <= ? and partner_id = ? ORDER BY gold DESC LIMIT 1";

		SqlParameter parameter = new SqlParameter();
		parameter.setDouble(amount);
		parameter.setString(partnerId);

		return jdbcConfig.get(sql, SystemGoldSet.class, parameter);
	}

}
