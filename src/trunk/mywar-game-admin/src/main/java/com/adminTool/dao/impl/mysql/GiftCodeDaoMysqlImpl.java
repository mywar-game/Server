package com.adminTool.dao.impl.mysql;

import java.util.List;

import com.adminTool.bo.GiftCode;
import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.JdbcImpl;
import com.fantingame.common.jdbc.SqlParameter;
import com.framework.servicemanager.ServiceCacheFactory;

public class GiftCodeDaoMysqlImpl {

	/**
	 * 批量插入礼包码
	 * 
	 * @param sql
	 */
	public void addGiftCodeBatch(String tableName, List<GiftCode> codeList) {
		Jdbc jdbc = ServiceCacheFactory.getServiceCache().getService(JdbcImpl.class);
		jdbc.insert(tableName, codeList);
	}
	
	
	/**
	 * 获取礼包id对应的礼包码数量
	 * 
	 * @param tableName
	 * @param giftbagId
	 * @return
	 */
	public int getGiftCodeCount(String tableName, int giftbagId) {
		Jdbc jdbc = ServiceCacheFactory.getServiceCache().getService(JdbcImpl.class);
		String sql = "select count(*) from " + tableName + " where gift_bag_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(giftbagId);
		
		return jdbc.getInt(sql, param);
	}
	
	/**
	 * 根据礼包id获取礼包码列表
	 * 
	 * @param tableName
	 * @param giftbagId
	 * @return
	 */
	public List<GiftCode> getGiftCodeListById(String tableName, int giftbagId) {
		Jdbc jdbc = ServiceCacheFactory.getServiceCache().getService(JdbcImpl.class);
		String sql = "select * from " + tableName + " where gift_bag_id = ? order by created_time";
		SqlParameter param = new SqlParameter();
		param.setInt(giftbagId);
		
		return jdbc.getList(sql, GiftCode.class, param);
	}
	
	/**
	 * 删除礼包码
	 * 
	 * @param tableName
	 * @param giftbagId
	 * @return
	 */
	public boolean delGiftCodeById(String tableName, int giftbagId) {
		Jdbc jdbc = ServiceCacheFactory.getServiceCache().getService(JdbcImpl.class);
		String sql = "delete from " + tableName + " where gift_bag_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(giftbagId);
		
		return jdbc.update(sql, param) > 0;
	}

} 
