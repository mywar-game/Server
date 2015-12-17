package com.fandingame.game.giftbag.dao.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.giftbag.model.SystemGiftbag;
import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;

public class SystemGiftbagDaoMysqlImpl {

	@Autowired
	private Jdbc jdbc;
	
	private final String tableName = "system_giftbag";
	
	/**
	 * 获取系统礼包
	 * 
	 * @param giftbagId
	 * @return
	 */
	public SystemGiftbag get(int giftbagId) {
		String sql = "select * from " + tableName + " where giftbag_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(giftbagId);
		
		return jdbc.get(sql, SystemGiftbag.class, param);
	}
	
}
