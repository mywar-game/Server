package com.fantingame.game.mywar.logic.tool.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.tool.dao.SystemToolDropDao;
import com.fantingame.game.mywar.logic.tool.model.SystemToolDrop;

public class SystemToolDropDaoMysqlImpl implements SystemToolDropDao {

	private final static String table = "system_tool_drop";
	

	@Autowired
	private Jdbc jdbcConfig;
	
	@Override
	public List<SystemToolDrop> getSystemToolDropList(int toolId) {
		String sql = "SELECT * FROM " + table + " WHERE tool_id = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(toolId);
		return this.jdbcConfig.getList(sql, SystemToolDrop.class, parameter);
	}
	
	public List<SystemToolDrop> getAll(){
		String sql = "SELECT * FROM " + table;
		return this.jdbcConfig.getList(sql, SystemToolDrop.class);
	}
}
