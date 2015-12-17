package com.fandingame.game.version.dao.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.version.dao.ActiveCodeDao;
import com.fandingame.game.version.model.GameServerStatus;
import com.fandingame.game.version.model.LoginServerStatus;
import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;

public class ActiveCodeDaoMysqlImpl implements ActiveCodeDao {

	@Autowired
	private Jdbc jdbc;

	public final static String table = "active_code";

	public final static String columns = "*";

	@Override
	public boolean isActive(String uuid, String partnerId) {

		String sql = "SELECT count(1) FROM " + table + " WHERE uuid = ? and partner_id = ? LIMIT 1";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(uuid);
		parameter.setString(partnerId);
		return jdbc.getInt(sql, parameter) > 0;
	}

	@Override
	public boolean isInBlackList(String imei,String mac,String ip, int blackType) {
		String sql = "SELECT count(1) FROM black_list WHERE ((value_type=1 and value=?) or (value_type=2 and value=?) or (value_type=3 and value=?)) and (black_type = ? or black_type=0) LIMIT 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(imei);
		parameter.setString(mac);
		parameter.setString(ip);
		parameter.setInt(blackType);
		return jdbc.getInt(sql, parameter) > 0;
	}
	@Override
	public boolean active(String uuid, String code, String partnerId) {
		String sql = "UPDATE " + table + " SET uuid = ?, updated_time = now() WHERE code = ? AND partner_id = ? AND uuid IS NULL  LIMIT 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(uuid);
		parameter.setString(code);
		parameter.setString(partnerId);
		return jdbc.update(sql, parameter) > 0;
	}
	@Override
	public LoginServerStatus getLoginServerBean() {
		String sql = "select * from login_server_status limit 1";
		SqlParameter parameter = new SqlParameter();
		return jdbc.get(sql,LoginServerStatus.class,parameter);
	}
	@Override
	public GameServerStatus getGameServerBean(String serverId) {
		String sql = "select * from game_server_status where server_id=? limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(serverId);
		return jdbc.get(sql,GameServerStatus.class,parameter);
	}

}
