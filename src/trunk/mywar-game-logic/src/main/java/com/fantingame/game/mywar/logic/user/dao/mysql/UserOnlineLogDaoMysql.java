package com.fantingame.game.mywar.logic.user.dao.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.TableUtils;
import com.fantingame.game.mywar.logic.user.model.UserOnlineLog;

public class UserOnlineLogDaoMysql {

	@Autowired
	private Jdbc jdbcUser;

	public final static String columns = "*";

	public boolean add(UserOnlineLog userOnlineLog) {

		String table = TableUtils.getUserOnlineLogTable(userOnlineLog.getUserId());

		String sql = "INSERT INTO " + table + "(user_id, login_time, logout_time, user_ip) VALUES(?, ?, ?, ?)";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(userOnlineLog.getUserId());
		parameter.setObject(userOnlineLog.getLoginTime());
		parameter.setObject(userOnlineLog.getLogoutTime());
		parameter.setString(userOnlineLog.getUserIp());

		return this.jdbcUser.update(sql, parameter) > 0;
	}

	public UserOnlineLog getLastOnlineLog(String userId) {

		String table = TableUtils.getUserOnlineLogTable(userId);

		String sql = "SELECT " + columns + " FROM " + table + " WHERE user_id = ? ORDER BY log_id DESC LIMIT 1";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);

		return this.jdbcUser.get(sql, UserOnlineLog.class, parameter);
	}

	public boolean updateLogoutTime(String userId, int logId, Date logoutTime) {

		String table = TableUtils.getUserOnlineLogTable(userId);

		String sql = "UPDATE " + table + " SET logout_time = ? WHERE user_id = ? AND log_id = ? ";

		SqlParameter parameter = new SqlParameter();
		parameter.setObject(logoutTime);
		parameter.setString(userId);
		parameter.setInt(logId);

		return this.jdbcUser.update(sql, parameter) > 0;

	}

	public long getUserOnline(String userId) {

		String table = TableUtils.getUserOnlineLogTable(userId);

		String sql = "SELECT SUM(unix_timestamp(logout_time) - unix_timestamp(login_time)) FROM " + table + " WHERE user_id = ? ";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);

		return this.jdbcUser.getInt(sql, parameter);
	}

	public long getUserOnline(String userId, Date startTime) {

		String table = TableUtils.getUserOnlineLogTable(userId);

		String sql = "SELECT SUM(unix_timestamp(logout_time) - unix_timestamp(CASE WHEN login_time > ? THEN login_time ELSE ? END)) FROM " + table + " WHERE user_id = ? AND logout_time >= ? ";

		SqlParameter parameter = new SqlParameter();
		parameter.setObject(startTime);
		parameter.setObject(startTime);
		parameter.setString(userId);
		parameter.setObject(startTime);

		return this.jdbcUser.getInt(sql, parameter);
	}
	
	public int getLoginDays(String userId, Date startTime,Date endTime) {

		String table = TableUtils.getUserOnlineLogTable(userId);

		String sql = "select count(1) from (select count(1) from "+table+" where login_time between ? and ? and user_id = ? group by date(login_time)) a";

		SqlParameter parameter = new SqlParameter();
		parameter.setObject(startTime);
		parameter.setObject(endTime);
		parameter.setString(userId);
		return this.jdbcUser.getInt(sql, parameter);
	}

	public long getLastOnline(String userId) {

		String table = TableUtils.getUserOnlineLogTable(userId);

		String sql = "SELECT unix_timestamp(now()) - unix_timestamp(login_time) FROM " + table + " WHERE user_id = ? ORDER BY log_id DESC LIMIT 1";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);

		return this.jdbcUser.getInt(sql, parameter);
	}

	public UserOnlineLog isLogin(String userId, String startTime, String endTime) {

		String table = TableUtils.getUserOnlineLogTable(userId);
		String sql = "select * from " + table + " where user_id = ? and login_time >= ? and login_time <= ? LIMIT 1";
		SqlParameter params = new SqlParameter();
		params.setString(userId);
		params.setString(startTime);
		params.setString(endTime);

		return this.jdbcUser.get(sql, UserOnlineLog.class, params);
	}
	
	public boolean isLoginTwoTimes(String userId, Date date) {

		String table = TableUtils.getUserOnlineLogTable(userId);
		String sql = "select * from " + table + " where user_id = ? and login_time >= ? and login_time <= ? LIMIT 2";
		SqlParameter params = new SqlParameter();
		params.setString(userId);
		params.setString(DateUtils.getDate(date) + " 00:00:00");
		params.setString(DateUtils.getDate(date) + " 23:59:59");

		List<UserOnlineLog> log = this.jdbcUser.getList(sql, UserOnlineLog.class, params);
		if (log!=null&&log.size()>=2) {
			return true;
		}
		return false;
	}
}
