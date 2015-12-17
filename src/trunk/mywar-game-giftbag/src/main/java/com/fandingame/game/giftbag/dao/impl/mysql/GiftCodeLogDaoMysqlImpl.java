package com.fandingame.game.giftbag.dao.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.giftbag.model.GiftCodeLog;
import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;

public class GiftCodeLogDaoMysqlImpl {

	@Autowired
	private Jdbc jdbc;
	
	private final String tableName = "gift_code_log_";

	/**
	 * 获取礼包码日志
	 * 
	 * @param tableName
	 * @param code
	 * @return
	 */
	public GiftCodeLog get(String tablePostfix, String code) {
		String sql = "select * from " + tableName + tablePostfix + " where code = ?";
		SqlParameter param = new SqlParameter();
		param.setString(code);
		
		return jdbc.get(sql, GiftCodeLog.class, param);
	}

	public boolean addGiftCodeLog(GiftCodeLog codeLog, String tablePostfix) {		
		return jdbc.insert(tableName + tablePostfix, codeLog) > 0;
	}
}
