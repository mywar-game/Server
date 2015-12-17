package com.fandingame.game.giftbag.dao.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.giftbag.model.GiftCode;
import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;

public class GiftCodeDaoMysqlImpl {
	
	@Autowired
	private Jdbc jdbc;

	private final String tableName = "gift_code_";
	
	/**
	 * 根据分表获取礼包码
	 * 
	 * @param tableName
	 * @param code
	 * @return
	 */
	public GiftCode get(String tablePostfix, String code) {
		String sql = "select * from " + tableName +  tablePostfix + " where code = ?";
		SqlParameter param = new SqlParameter();
		param.setString(code);
		
		return jdbc.get(sql, GiftCode.class, param);
	}
	
}
