package com.fantingame.game.mywar.logic.equip.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.utils.TableUtils;
import com.fantingame.game.mywar.logic.equip.dao.UserEquipDao;
import com.fantingame.game.mywar.logic.equip.model.UserEquip;

public class UserEquipDaoMysqlImpl implements UserEquipDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private static final String tablePrex = "user_equip";
	private static final int tableCount = 128;
	
	private String getTableName(String userId) {
		return TableUtils.getTableName(userId, tablePrex, tableCount);
	}
	
	@Override
	public List<UserEquip> getUserEquipList(String userId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserEquip.class, param);
	}

	@Override
	public UserEquip getUserEquip(String userId, String userEquipId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and user_equip_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(userEquipId);
		
		return this.jdbcUser.get(sql, UserEquip.class, param);
	}

	@Override
	public boolean changeEquipUserHeroId(String userId, String userEquipId,
			String userHeroId, int pos) {
		String sql = "update " + getTableName(userId) + " set user_hero_id = ?, updated_time = now(), pos = ? where user_equip_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userHeroId);
		param.setInt(pos);
		param.setString(userEquipId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean addEquip(UserEquip userEquip) {
		return this.jdbcUser.insert(getTableName(userEquip.getUserId()), userEquip) > 0;
	}

	@Override
	public UserEquip getUserEquip(String userId, String userHeroId, int pos) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and user_hero_id = ? and pos = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(userHeroId);
		param.setInt(pos);
		
		return this.jdbcUser.get(sql, UserEquip.class, param);
	}

	@Override
	public List<UserEquip> getUserEquipList(String userId, String userHeroId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and user_hero_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(userHeroId);
		
		return this.jdbcUser.getList(sql, UserEquip.class, param);
	}

	@Override
	public boolean deleteUserEquip(String userId, String userEquipId) {
		String sql = "delete from " + getTableName(userId) + " where user_id = ? and user_equip_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(userEquipId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public int getUserEquipCount(String userId, int type) {
		throw new RuntimeException("没有实现，请调用cache中的方法");
	}

	@Override
	public List<UserEquip> getUserEquipList(String userId, int equipId) {
		String sql = "select * from " + getTableName(userId) + " where user_id = ? and equip_id = ? and storehouse_num == 0";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(equipId);
		
		return this.jdbcUser.getList(sql, UserEquip.class, param);
	}

	@Override
	public boolean equipMagic(String userId, String userEquipId, String equipMagicAttr) {
		String sql = "update " + getTableName(userId) + " set magic_equip_attr = ? where user_id = ? and user_equip_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(equipMagicAttr);
		param.setString(userId);
		param.setString(userEquipId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean storehouseInOrOut(String userId, String userEquipId, int storehouseNum) {
		String sql = "update " + getTableName(userId) + " set storehouse_num = ?, updated_time = now() where user_id = ? and user_equip_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(storehouseNum);
		param.setString(userId);
		param.setString(userEquipId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

}
