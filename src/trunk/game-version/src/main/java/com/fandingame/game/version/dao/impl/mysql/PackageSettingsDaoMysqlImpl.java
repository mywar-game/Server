package com.fandingame.game.version.dao.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.version.dao.PackageSettingsDao;
import com.fandingame.game.version.model.PackageSettings;
import com.fantingame.common.jdbc.Jdbc;

public class PackageSettingsDaoMysqlImpl implements PackageSettingsDao {
	@Autowired
	private Jdbc jdbc;

	public final static String table = "package_settings";

	@Override
	public PackageSettings get() {
		String sql = "select * from " + table + " where id = 1";
		return this.jdbc.get(sql, PackageSettings.class, null);
	}

}
