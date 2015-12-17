package com.fantingame.game.mywar.logic.legion.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.legion.dao.LegionInfoDao;
import com.fantingame.game.mywar.logic.legion.model.LegionInfo;

public class LegionInfoDaoMysqlImpl implements LegionInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String tableName = "legion_info";

	@Override
	public LegionInfo getLegionInfo(String legionId) {
		String sql = "select * from " + tableName + " where legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(legionId);
		
		return this.jdbcUser.get(sql, LegionInfo.class, param);
	}

	@Override
	public boolean isExitLegionName(String legionName) {
		String sql = "select * from " + tableName + " where legion_name = ? limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(legionName);
		
		return this.jdbcUser.get(sql, LegionInfo.class, param) != null;
	}

	@Override
	public boolean addLegionInfo(LegionInfo legionInfo) {
		return this.jdbcUser.insert(legionInfo) > 0;
	}

	@Override
	public List<LegionInfo> getLegionInfoList() {
		String sql = "select * from " + tableName;
		
		return this.jdbcUser.getList(sql, LegionInfo.class);
	}

	@Override
	public boolean updateLegionNotice(String legionId, String notice) {
		String sql = "update " + tableName + " set notice = ?, updated_time = now() where legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(notice);
		param.setString(legionId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateLegionDeclaration(String legionId, String declaration) {
		String sql = "update " + tableName + " set declaration = ?, updated_time = now() where legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(declaration);
		param.setString(legionId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateLegionPower(String legionId, int power) {
		String sql = "update " + tableName + " set power = ?, updated_time = now() where legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(legionId);
		param.setInt(power);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean deleteLegionInfo(String legionId) {
		String sql = "delete from " + tableName + " where legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(legionId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateLevelAndExp(String legionId, int level, int addExp) {
		String sql = "update " + tableName + " set level = ?, exp = exp + ?, updated_time = now() where legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(level);
		param.setInt(addExp);
		param.setString(legionId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
}
