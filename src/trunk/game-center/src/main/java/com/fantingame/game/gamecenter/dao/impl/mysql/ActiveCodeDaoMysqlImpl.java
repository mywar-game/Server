package com.fantingame.game.gamecenter.dao.impl.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.gamecenter.model.ActiveCode;
import com.fantingame.game.gamecenter.model.GameServerStatus;
import com.fantingame.game.gamecenter.model.LoginServerStatus;

public class ActiveCodeDaoMysqlImpl implements
		com.fantingame.game.gamecenter.dao.ActiveCodeDao {

	@Autowired
	private Jdbc jdbc;

	public final static String table = "active_code";

	public final static String columns = "*";

	@Override
	public boolean isActive(String uuid, String partnerId) {

		String sql = "SELECT count(1) FROM " + table
				+ " WHERE uuid = ? and partner_id = ? LIMIT 1";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(uuid);
		parameter.setString(partnerId);
		return jdbc.getInt(sql, parameter) > 0;
	}

	@Override
	public boolean isInBlackList(String imei, String mac, String ip,
			int blackType) {
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
		String sql = "UPDATE "
				+ table
				+ " SET uuid = ?, partner_id = ?, updated_time = now() WHERE code = ? AND uuid IS NULL  LIMIT 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(uuid);
		parameter.setString(partnerId);
		parameter.setString(code);
		return jdbc.update(sql, parameter) > 0;
	}

	@Override
	public LoginServerStatus getLoginServerBean() {
		String sql = "select * from login_server_status limit 1";
		SqlParameter parameter = new SqlParameter();
		return jdbc.get(sql, LoginServerStatus.class, parameter);
	}

	@Override
	public GameServerStatus getGameServerBean(String serverId) {
		String sql = "select * from game_server_status where server_id=? limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(serverId);
		return jdbc.get(sql, GameServerStatus.class, parameter);
	}

	@Override
	public void addActive(List<ActiveCode> activeList) {
		this.jdbc.insert(activeList);
	}

	@Override
	public boolean addActive(String uuid, String code, String partnerId) {
		ActiveCode activeCode = new ActiveCode();
		activeCode.setCode(code);
		activeCode.setPartnerId(partnerId);
		activeCode.setUuid(uuid);
		activeCode.setUpdatedTime(new Date());
		
		return this.jdbc.insert(activeCode) > 0;
	}

	@Override
	public List<ActiveCode> getAllActiveList() {
		String sql = "SELECT * FROM " + table;
		return this.jdbc.getList(sql, ActiveCode.class);
	}

}
