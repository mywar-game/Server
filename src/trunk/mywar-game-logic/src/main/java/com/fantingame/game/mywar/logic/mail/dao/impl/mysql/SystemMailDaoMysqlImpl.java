package com.fantingame.game.mywar.logic.mail.dao.impl.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.mail.dao.SystemMailDao;
import com.fantingame.game.mywar.logic.mail.model.SystemMail;

public class SystemMailDaoMysqlImpl implements SystemMailDao {

	@Autowired
	private Jdbc jdbcUser;	
	private static final String table = "system_mail";
	
	public List<SystemMail> getListAll() {
		String sql = "select * from " + table;
		return this.jdbcUser.getList(sql, SystemMail.class);
	}
	
	public SystemMail getSystemMail(String systemMailId) {
		String sql = "select * from " + table + " where system_mail_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(systemMailId);
		
		return this.jdbcUser.get(sql, SystemMail.class, param);
	}

	@Override
	public boolean add(SystemMail systemMail) {
		return this.jdbcUser.insert(systemMail) > 0;
	}

	@Override
	public List<SystemMail> getSystemMailByTime(Date date) {
		String sql = "select * from " + table + " where created_time >= ?";
		SqlParameter param = new SqlParameter();
		param.setObject(date);
		
		return this.jdbcUser.getList(sql, SystemMail.class, param);
	}
	
}
