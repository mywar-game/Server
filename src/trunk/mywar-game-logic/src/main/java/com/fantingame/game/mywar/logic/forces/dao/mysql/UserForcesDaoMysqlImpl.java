package com.fantingame.game.mywar.logic.forces.dao.mysql;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.utils.TableUtils;
import com.fantingame.game.mywar.logic.forces.dao.UserForcesDao;
import com.fantingame.game.mywar.logic.forces.model.UserForces;

public class UserForcesDaoMysqlImpl implements UserForcesDao {
	
	@Autowired
	private Jdbc jdbcUser;

	public final static String tablePrex = "user_forces";
	public final static int tableCount = 256;
	
	private String getTableName(String userId) {
		return TableUtils.getTableName(userId, tablePrex, tableCount);
	}
	
	@Override
	public List<UserForces> getUserForcesList(String userId) {
		String sql = "select * from " + getTableName(userId) + " where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		return jdbcUser.getList(sql, UserForces.class, parameter);
	}

	@Override
	public boolean updateForcesTimes(String userId, int mapId, int forcesId, int times, int forcesType) {
		String sql = "update " + getTableName(userId) + " set times=?, updated_time=now() where user_id=? and forces_id=? and forces_type = ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(times);
		parameter.setString(userId);
		parameter.setInt(forcesId);
		parameter.setInt(forcesType);
		return this.jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean addUserForces(UserForces userForces) {
		return jdbcUser.insert(getTableName(userForces.getUserId()), userForces) > 0;
	}

	@Override
	public UserForces getUserForces(String userId, int mapId, int forcesId, int forcesType) {
		String sql = "select * from " + getTableName(userId) + " where user_id =? and forces_id = ? and forces_type = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(forcesId);
		param.setInt(forcesType);
		
		return jdbcUser.get(sql, UserForces.class, param);
	}

	@Override
	public List<UserForces> getUserForcesListByMapId(String userId, int mapId) {
		throw new RuntimeException("未实现的方法！！！");
	}

	@Override
	public boolean updateUserForcesStatus(String userId, int mapId, int forcesId, int status, int attackTimes, int forcesType) {
		String sql = "update " + getTableName(userId) + " set status = ?, times = ?, updated_time = now() where user_id = ? and forces_id = ? and forces_type = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(status);
		param.setInt(attackTimes);
		param.setString(userId);
		param.setInt(forcesId);
		param.setInt(forcesType);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserForces> getUserForcesListByBigForcesId(String userId, int mapId, int bigForcesId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and big_forces_id and mapId = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(bigForcesId);		
		
		return this.jdbcUser.getList(sql, UserForces.class, param);
	}

	@Override
	public Map<Integer, UserForces> getUserForcesMapByMapId(String userId, int mapId) {
		throw new RuntimeException("未实现的方法！！！");
	}

}
