package com.fandingame.game.giftbag.dao.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.giftbag.model.GiftbagTypeLimit;
import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;

public class GiftbagTypeLimitDaoMysqlImpl {

	@Autowired
	private Jdbc jdbc;

	private final String tableName = "giftbag_type_limit";
	
	/**
	 * 根据礼包类型的限制规则
	 * 
	 * @param type
	 * @return
	 */
	public GiftbagTypeLimit get(int type) {
		String sql = "select * from " + tableName + " where gift_bag_type = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(type);
		
		return jdbc.get(sql, GiftbagTypeLimit.class, param);
	}
	
}
