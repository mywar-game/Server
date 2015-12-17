package com.fantingame.game.mywar.logic.legion.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.legion.dao.UserLegionInfoDao;
import com.fantingame.game.mywar.logic.legion.model.UserLegionInfo;

public class UserLegionInfoDaoMysqlImpl implements UserLegionInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String tableName = "user_legion_info";

	@Override
	public UserLegionInfo getUserLegionInfo(String userId) {
		String sql = "select * from " + tableName + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.get(sql, UserLegionInfo.class, param);
	}

	@Override
	public boolean addUserLegionInfo(UserLegionInfo userLegionInfo) {
		return this.jdbcUser.insert(userLegionInfo) > 0;
	}

	@Override
	public int getLegionCurrentNum(String legionId) {
		String sql = "select count(*) from " + tableName + " where legion_id = ? and status = 1";
		SqlParameter param = new SqlParameter();
		param.setString(legionId);
		
		return this.jdbcUser.getInt(sql, param);
	}

	@Override
	public boolean updateUserlegionInfo(String userId, String legionId, int status, int identity, int contribution) {
		String sql = "update " + tableName + " set status = ?, identity = ?, legion_id = ?, contribution = ?, updated_time = now() where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(status);
		param.setInt(identity);
		param.setString(legionId);
		param.setInt(contribution);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserLegionInfo> getUserLegionInfoList(String legionId) {
		String sql = "select * from " + tableName + " where legion_id = ? and status = 1";
		SqlParameter param = new SqlParameter();
		param.setString(legionId);		
		
		return this.jdbcUser.getList(sql, UserLegionInfo.class, param);
	}

	@Override
	public int getLegionCurrentIdentityNum(String legionId, int identity) {
		String sql = "select count(*) " + tableName + " where legion_id = ? and identity = ? and status = 1";
		SqlParameter param = new SqlParameter();
		param.setString(legionId);
		param.setInt(identity);
		
		return this.jdbcUser.getInt(sql, param);
	}

	@Override
	public boolean updateUserlegionInfo(String userId, int identity) {
		String sql = "update " + tableName + " set identity = ?, updated_time = now() where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(identity);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateUserlegionInfoStatus(String userId, int status) {
		String sql = "update " + tableName + " set status = ?, updated_time = now() where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(status);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateUserlegionInfoStatus(String legionId, int status, List<UserLegionInfo> userLegionInfoList) {
		String sql = "update " + tableName + " set status = ?, updated_time = now() where legion_id = ? and status = 1";
		SqlParameter param = new SqlParameter();
		param.setInt(status);
		param.setString(legionId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateUserContribution(String userId, int contribution) {
		String sql = "update " + tableName + " set contribution = ? where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(contribution);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
}
