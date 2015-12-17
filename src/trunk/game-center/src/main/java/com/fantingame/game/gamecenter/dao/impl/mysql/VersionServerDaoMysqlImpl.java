package com.fantingame.game.gamecenter.dao.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.gamecenter.dao.VersionServerDao;
import com.fantingame.game.gamecenter.model.VersionServer;
import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
public class VersionServerDaoMysqlImpl implements VersionServerDao {

	private String table = "version_server";
	
	@Autowired
	private Jdbc jdbc;
	
	@Override
	public VersionServer getVersionServer(String version) {
		
		String sql = "SELECT * FROM "+table+" WHERE version = ?";
		
		SqlParameter parameter = new SqlParameter();
		parameter.setString(version);
		
		return jdbc.get(sql, VersionServer.class, parameter);
	}
	
}
