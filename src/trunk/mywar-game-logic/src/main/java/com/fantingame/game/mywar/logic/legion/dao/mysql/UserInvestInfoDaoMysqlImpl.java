package com.fantingame.game.mywar.logic.legion.dao.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.legion.dao.UserInvestInfoDao;
import com.fantingame.game.mywar.logic.legion.model.UserInvestInfo;

public class UserInvestInfoDaoMysqlImpl implements UserInvestInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String tableName = "user_invest_info";

	@Override
	public UserInvestInfo getUserInvestInfo(String userId) {
		String sql = "select * from " + tableName + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.get(sql, UserInvestInfo.class, param);
	}
	
}
