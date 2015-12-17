package com.fantingame.game.mywar.logic.setting.dao.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.setting.model.UserAdviceInfo;


public class UserAdviceInfoDaoMysql {
	
	@Autowired
	private Jdbc jdbcUser;

	private String table = "user_advice_info";
	
	/**
	 * 获取最后一条建议
	 * 
	 * @param userId
	 * @return
	 */
	public UserAdviceInfo getLastUserAdviceInfo(String userId) {
		String sql = "select * from " + table + " where user_id = ? order by updated_time desc limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.get(sql, UserAdviceInfo.class, param);
	}
	
	/**
	 * 添加一条建议
	 * 
	 * @param userAdviceInfo
	 * @return
	 */
	public boolean addUserAdviceInfo(UserAdviceInfo userAdviceInfo) {
		return this.jdbcUser.insert(userAdviceInfo) > 0;
	}
}
