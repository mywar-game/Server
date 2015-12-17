package com.fantingame.game.mywar.logic.gemstone.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.utils.TableUtils;
import com.fantingame.game.mywar.logic.gemstone.dao.UserGemstoneDao;
import com.fantingame.game.mywar.logic.gemstone.model.UserGemstone;

public class UserGemstoneDaoMysqlImpl implements UserGemstoneDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private static final String tablePrex = "user_gemstone";
	private static final int tableCount = 128;
	
	private String getTableName(String userId) {
		return TableUtils.getTableName(userId, tablePrex, tableCount);
	}

	@Override
	public List<UserGemstone> getUserGemstoneList(String userId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserGemstone.class, param);
	}

	@Override
	public boolean addUserGemstone(UserGemstone userGemstone) {
		return this.jdbcUser.insert(getTableName(userGemstone.getUserId()), userGemstone) > 0;
	}

	@Override
	public UserGemstone getUserGemstone(String userId, String userGemstoneId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and user_gemstone_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(userGemstoneId);
		
		return this.jdbcUser.get(sql, UserGemstone.class, param);
	}

	@Override
	public boolean deleteUserGemstone(String userId, String userGemstoneId) {
		String sql = "delete from " + getTableName(userId) + " where user_id = ? and user_gemstone_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(userGemstoneId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserGemstone> getUserGemstoneListInEquip(String userId, String userEquipId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and user_equip_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(userEquipId);
		
		return this.jdbcUser.getList(sql, UserGemstone.class, param);
	}

	@Override
	public boolean fillInEquip(String userId, String userGemstoneId, String userEquipId, int pos) {
		String sql = "update " + getTableName(userId) + " set user_equip_id = ?, pos = ?, updated_time = now() where user_id = ? and user_gemstone_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userEquipId);
		param.setInt(pos);
		param.setString(userId);
		param.setString(userGemstoneId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserGemstone> getUserGemstoneList(String userId, int gemstoneId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and gemstone_id = ? and pos == 0";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(gemstoneId);
		
		return this.jdbcUser.getList(sql, UserGemstone.class, param);
	}

	@Override
	public boolean upgradeUserGemstone(String userId, String userGemstoneId, int gemstoneId, String attr) {
		String sql = "update " + getTableName(userId) + " set gemstone_id = ?, gemstone_attr = ? where user_id = ? and user_gemstone_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(gemstoneId);
		param.setString(attr);
		param.setString(userId);
		param.setString(userGemstoneId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public int getUserGemstoneCount(String userId, int type) {
		throw new RuntimeException("没有实现，请调用cache中的方法");
	}

	@Override
	public List<UserGemstone> getUnFillGemstoneList(String userId, int gemstoneId) {
		throw new RuntimeException("没有实现，请调用cache中的方法");
	}

	@Override
	public boolean storehouseInOrOut(String userId, String userGemstoneId, int storehouseNum) {
		String sql = "update " + getTableName(userId) + " set storehouse_num = ? updated_time = now() where user_id = ? and user_gemstone_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(storehouseNum);
		param.setString(userId);
		param.setString(userGemstoneId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
}
