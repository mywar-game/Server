package com.fandingame.game.version.dao.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.version.dao.VersionManagerDao;
import com.fandingame.game.version.model.VersionManagerApk;
import com.fandingame.game.version.model.VersionManagerRes;
import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;

public class VersionManagerDaoMysqlImpl implements VersionManagerDao {
	@Autowired
	private Jdbc jdbc;

	public final static String table_apk = "version_manager_apk";
	public final static String table_res = "version_manager_res";

	public final static String columns = "*";
	
	@Override
	public VersionManagerApk getLastApkVersion(String partnerId,String qn) {
		String sql = "SELECT " + columns + " FROM " + table_apk + " WHERE partner_id = ? and qn = ? ORDER BY ID DESC LIMIT 1";
		SqlParameter paramter = new SqlParameter();
		paramter.setString(partnerId);
		paramter.setString(qn);
		return this.jdbc.get(sql, VersionManagerApk.class, paramter);
	}
	
	@Override
	public VersionManagerApk getOfficialApkVersion(String partnerId,String qn) {
		String sql = "SELECT " + columns + " FROM " + table_apk + " WHERE partner_id = ? and qn = ? and is_test=0 ORDER BY ID DESC LIMIT 1";
		SqlParameter paramter = new SqlParameter();
		paramter.setString(partnerId);
		paramter.setString(qn);
		return this.jdbc.get(sql, VersionManagerApk.class, paramter);
	}

	@Override
	public VersionManagerRes getCurrentResVersion(int currentResBigVersion,
			int currentResSmallVersion, String partnerId) {
		String sql = "SELECT " + columns + " FROM " + table_res + " WHERE (partner_id = ? or partner_id = '0' ) and be_update_big_version = ? and be_update_small_version = ? ORDER BY ID DESC LIMIT 1";
		SqlParameter paramter = new SqlParameter();
		paramter.setString(partnerId);
		paramter.setInt(currentResBigVersion);
		paramter.setInt(currentResSmallVersion);
		return this.jdbc.get(sql, VersionManagerRes.class, paramter);
	}

}
