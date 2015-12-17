package com.fantingame.game.mywar.logic.legion.dao.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.legion.dao.UserApplyLegionInfoDao;
import com.fantingame.game.mywar.logic.legion.model.UserApplyLegionInfo;

public class UserApplyLegionInfoDaoMysqlImpl implements UserApplyLegionInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String tableName = "user_apply_legion_info";

	@Override
	public List<UserApplyLegionInfo> getUserApplyLegionInfoList(String userId) {
		String sql = "select * from " + tableName + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserApplyLegionInfo.class, param);
	}

	@Override
	public boolean addUserApplyLegionInfo(UserApplyLegionInfo userApplyLegionInfo) {
		return this.jdbcUser.insert(userApplyLegionInfo) > 0;
	}

	@Override
	public UserApplyLegionInfo getUserApplyLegionInfo(String userId, String legionId) {
		String sql = "select * from " + tableName + " where user_id = ? and legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(legionId);
		
		return this.jdbcUser.get(sql, UserApplyLegionInfo.class, param);
	}

	@Override
	public boolean deleteUserApplyLegionInfo(String userId, String legionId) {
		String sql = "delete from " + tableName + " where user_id = ? and legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(legionId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean deleteUserApplyLegionInfo(String userId, Date endTime) {
		String sql = "delete from " + tableName + " where user_id = ? and updated_time <= ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setObject(endTime);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserApplyLegionInfo> getUserApplyLegionInfoList(String legionId, Date endTime) {
		String sql = "select * from " + tableName + " where legion_id = ? and updated_time > ?";
		SqlParameter param = new SqlParameter();
		param.setString(legionId);
		param.setObject(endTime);
		
		return this.jdbcUser.getList(sql, UserApplyLegionInfo.class, param);
	}

	@Override
	public boolean deleteUserApplyLegionInfo(String legionId, List<UserApplyLegionInfo> applyList) {
		String sql = "delete from " + tableName + " where legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(legionId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserApplyLegionInfo> getListByLegionId(String legionId) {
		String sql = "select * from " + tableName + " where legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(legionId);
		
		return this.jdbcUser.getList(sql, UserApplyLegionInfo.class, param);
	}

	@Override
	public boolean deleteUserApplyLegionInfo(String userId) {
		String sql = "delete from " + tableName + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserApplyLegionInfo> getUserApplyLegionInfoListByUserId(String userId, Date endTime) {
		String sql = "select * from " + tableName + " where user_id = ? and updated_time <= ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setObject(endTime);
		
		return this.jdbcUser.getList(sql, UserApplyLegionInfo.class, param);
	}
	
}
