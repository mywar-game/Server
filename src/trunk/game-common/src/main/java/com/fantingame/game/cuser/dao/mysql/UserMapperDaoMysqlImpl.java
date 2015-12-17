package com.fantingame.game.cuser.dao.mysql;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.cuser.dao.UserMapperDao;
import com.fantingame.game.cuser.model.UserMapper;

public class UserMapperDaoMysqlImpl implements UserMapperDao {

	@Autowired
	private Jdbc jdbcUser;

	public final static String table = "user_mapper";

	public final static String columns = "*";

	@Override
	public boolean save(UserMapper userMapper) {
		jdbcUser.insert(userMapper);
		return true;
	}

	@Override
	public UserMapper getByPartnerUserId(String partnerUserId, String partnerId, String serverId) {
		String sql = "SELECT " + columns + " FROM " + table + " WHERE partner_user_id = ? AND partner_id = ? AND server_id = ? limit 1;";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(partnerUserId);
		parameter.setString(partnerId);
		parameter.setString(serverId);

		return this.jdbcUser.get(sql, UserMapper.class, parameter);
	}

	@Override
	public UserMapper get(String userId) {

		String sql = "SELECT " + columns + " FROM " + table + " WHERE user_id = ? ;";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);

		return this.jdbcUser.get(sql, UserMapper.class, parameter);
	}

	@Override
	public List<UserMapper> getUserMapperByPartner(String partnerId) {
		String sql = "select * from " + table + " where partner_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(partnerId);
		
		return this.jdbcUser.getList(sql, UserMapper.class, param);
	}
}
