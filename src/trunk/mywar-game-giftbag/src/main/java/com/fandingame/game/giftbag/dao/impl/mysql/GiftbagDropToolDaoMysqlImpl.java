package com.fandingame.game.giftbag.dao.impl.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.giftbag.model.GiftbagDropTool;
import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;

public class GiftbagDropToolDaoMysqlImpl {

	@Autowired
	private Jdbc jdbc;
	
	private final String tableName = "giftbag_drop_tool";
	
	/**
	 * 获取礼包掉落列表
	 * 
	 * @param giftbagId
	 * @return
	 */
	public List<GiftbagDropTool> getGiftbagDropToolList(int giftbagId) {
		String sql = "select * from " + tableName + " where giftbag_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(giftbagId);
		
		return jdbc.getList(sql, GiftbagDropTool.class, param);
	}
	
}
