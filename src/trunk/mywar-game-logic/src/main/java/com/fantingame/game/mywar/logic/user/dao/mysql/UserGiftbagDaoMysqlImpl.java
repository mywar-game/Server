package com.fantingame.game.mywar.logic.user.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.utils.TableUtils;
import com.fantingame.game.mywar.logic.user.model.UserGiftbag;
import com.fantingame.game.mywar.logic.user.model.UserGiftcode;

/**
 * 用户礼包领取记录(礼包码与游戏礼包分开存储)
 * 
 * @author yezp
 */
public class UserGiftbagDaoMysqlImpl {

	@Autowired
	private Jdbc jdbcUser;
	
	private static final String tablePrex = "user_giftbag";
	private static final int tableCount = 20;
	
	private static final String table = "user_giftcode";
	
	/**
	 * 根据类型获取用户领取礼包记录
	 * 
	 * @param userId
	 * @param type
	 * @return
	 */
	public List<UserGiftbag> getListByType(String userId, int type) {
		String sql = "select * from " + TableUtils.getTableName(userId, tablePrex, tableCount) + " where user_id = ? AND type = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(type);
		
		return jdbcUser.getList(sql, UserGiftbag.class, param);
	}
	
	/**
	 * 更新用户记录
	 * 
	 * @param userId
	 * @param type
	 * @param giftBagId
	 * @return
	 */
	public boolean addOrUpdateUserGiftbag(String userId, int type, int giftBagId) {
		String sql = " INSERT INTO " + TableUtils.getTableName(userId, tablePrex, tableCount) + "(user_id, type, giftbag_id, total_num, created_time, updated_time)";
		sql += " VALUES(?, ?, ?, 1, now(), now()) ON DUPLICATE KEY UPDATE ";
		sql += " total_num = total_num + VALUES(total_num), updated_time = VALUES(updated_time)";
		
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		parameter.setInt(type);
		parameter.setInt(giftBagId);

		return this.jdbcUser.update(sql, parameter) > 0;
	}
	
	/**
	 * 更新用户领取礼包码的记录
	 * 
	 * @param userId
	 * @param type
	 * @param giftBagId
	 * @return
	 */
	public boolean addOrUpdateUserGiftcode(String userId, int type, int giftBagId) {
		String sql = " INSERT INTO " + table + "(user_id, type, giftbag_id, total_num, created_time, updated_time)";
		sql += " VALUES(?, ?, ?, 1, now(), now()) ON DUPLICATE KEY UPDATE ";
		sql += " total_num = total_num + VALUES(total_num), updated_time = VALUES(updated_time)";
		
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		parameter.setInt(type);
		parameter.setInt(giftBagId);

		return this.jdbcUser.update(sql, parameter) > 0;
	}
	
	/**
	 * 根据类型获取用户领取礼包码的记录
	 * 
	 * @param userId
	 * @param type
	 * @return
	 */
	public List<UserGiftcode> getGiftcodeListByType(String userId, int type) {
		String sql = "select * from " + table + " where user_id = ? AND type = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(type);
		
		return jdbcUser.getList(sql, UserGiftcode.class, param);
	}
	
}
