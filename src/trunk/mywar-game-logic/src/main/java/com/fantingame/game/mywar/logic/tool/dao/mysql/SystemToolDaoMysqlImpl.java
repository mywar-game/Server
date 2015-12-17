package com.fantingame.game.mywar.logic.tool.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.tool.dao.SystemToolDao;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.model.SystemToolOpen;

public class SystemToolDaoMysqlImpl implements SystemToolDao {

	public final static String table = "system_tool";
	
	public final static String open_table = "system_tool_open";

	public final static String columns = "*";

	@Autowired
	private Jdbc jdbcConfig;

	public SystemTool get(int toolId) {

		String sql = "SELECT " + columns + " FROM " + table + " WHERE tool_id = ? ";

		SqlParameter parameter = new SqlParameter();
		parameter.setInt(toolId);

		return this.jdbcConfig.get(sql, SystemTool.class, parameter);
	}
	public List<SystemTool> getSystemToolList() {

		String sql = "SELECT " + columns + " FROM " + table + "  ";

		return this.jdbcConfig.getList(sql, SystemTool.class);
	}
	public List<SystemToolOpen> getAllGiftBoxOpenKey(){
		String sql = "SELECT " + columns + " FROM " + open_table;
		return this.jdbcConfig.getList(sql, SystemToolOpen.class);
	}
	@Override
	public int getGiftBoxKey(int giftBoxId) {
		throw new RuntimeException("未实现该方法");
	}
}
