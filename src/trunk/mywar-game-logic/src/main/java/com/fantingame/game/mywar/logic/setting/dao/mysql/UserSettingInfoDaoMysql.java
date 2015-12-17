package com.fantingame.game.mywar.logic.setting.dao.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.setting.dao.UserSettingInfoDao;
import com.fantingame.game.mywar.logic.setting.model.UserSettingInfo;

public class UserSettingInfoDaoMysql implements UserSettingInfoDao {
	
	@Autowired
	private Jdbc jdbcUser;
	
	private static final String table = "user_setting_info";

	@Override
	public UserSettingInfo getUserSettingInfo(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.get(sql, UserSettingInfo.class, param);
	}

	@Override
	public boolean addUserSettingInfo(UserSettingInfo userSettingInfo) {
		return this.jdbcUser.insert(userSettingInfo) > 0;
	}

	@Override
	public boolean updateUserSettingInfo(String userId, int num) {
		String sql = "update " + table + " set display_num = ? where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(num);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateUserDailyTips(String userId, String tips) {
		String sql = "update " + table + " set daily_tips = ?, updated_time = now() where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(tips);
		param.setString(userId);		
		
 		return this.jdbcUser.update(sql, param) > 0;
	}

}
