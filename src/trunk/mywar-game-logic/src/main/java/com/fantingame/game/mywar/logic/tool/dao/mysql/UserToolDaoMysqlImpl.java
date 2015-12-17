package com.fantingame.game.mywar.logic.tool.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.utils.TableUtils;
import com.fantingame.game.mywar.logic.tool.dao.UserToolDao;
import com.fantingame.game.mywar.logic.tool.model.UserTool;

public class UserToolDaoMysqlImpl implements UserToolDao {

	public final static String columns = "tool_id, tool_num, storehouse_num";

	@Autowired
	private Jdbc jdbcUser;

	private static final String tablePrex = "user_tool";
	private static final int tableCount = 128;

	private String getTableName(String userId) {
		return TableUtils.getTableName(userId, tablePrex, tableCount);
	}

	@Override
	public int getUserToolNum(String userId, int toolId) {
		UserTool userTool = this.get(userId, toolId);
		if (userTool != null) {
			return userTool.getToolNum();
		}
		return 0;
	}

	@Override
	public int getUserToolCount(String userId) {
		List<UserTool> list = this.getList(userId);
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	@Override
	public boolean reduceUserTool(String userId, int toolId, int num) {
		if (num < 0) {
			return false;
		}
		String sql = "UPDATE "
				+ getTableName(userId)
				+ " SET tool_num = tool_num - ? WHERE user_id = ? AND tool_id = ? AND tool_num >= ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(num);
		parameter.setString(userId);
		parameter.setInt(toolId);
		parameter.setInt(num);
		return this.jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public UserTool get(String userId, int toolId) {
		String sql = "SELECT " + columns + " FROM " + getTableName(userId)
				+ " WHERE user_id = ? AND tool_id = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		parameter.setInt(toolId);
		return this.jdbcUser.get(sql, UserTool.class, parameter);
	}

	@Override
	public List<UserTool> getList(String userId) {
		String sql = "SELECT " + columns + " FROM " + getTableName(userId)
				+ " WHERE user_id = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		return this.jdbcUser.getList(sql, UserTool.class, parameter);
	}

	@Override
	public boolean addUserTool(String userId, int toolId, int num, int storehouseNum) {
		String sql = "INSERT INTO "
				+ getTableName(userId)
				+ " (user_id, tool_id, tool_num, storehouse_num, created_time, updated_time) VALUES(?, ?, ?, ?, now(), now()) ";
		sql += "ON DUPLICATE KEY update tool_num = tool_num + VALUES(tool_num), storehouse_num = storehouse_num + VALUES(storehouse_num), updated_time = now()";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		parameter.setInt(toolId);
		parameter.setInt(num);
		parameter.setInt(storehouseNum);
		return this.jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean storehouseInOrOut(String userId, int toolId, int toolNum, int storehouseNum) {
		String sql = "update " + getTableName(userId) + " set tool_num = ?, storehouse_num = ?, updated_time = now() "
				+ " where user_id = ? and tool_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(toolNum);
		param.setInt(storehouseNum);
		param.setString(userId);
		param.setInt(toolId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
}
