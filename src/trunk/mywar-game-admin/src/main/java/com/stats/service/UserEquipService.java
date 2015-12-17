package com.stats.service;

import java.util.List;

import com.framework.common.DBSource;
import com.stats.dao.UserEquipDao;

/**
 * 装备操作
 * @author Administrator
 *
 */
public class UserEquipService {

	private UserEquipDao userEquipDao;

	public List<Object> getList(String tableName, String[] dates) {
		StringBuilder sb = new StringBuilder();
		sb.append("select equip_id from ");
		sb.append(tableName);
		sb.append(" where created_time between '");
		sb.append(dates[0]);
		sb.append("'");
		sb.append(" and '");
		sb.append(dates[1]);
		sb.append("'");
		userEquipDao.closeSession(DBSource.USER);
		return userEquipDao.findSQL_(sb.toString());
	}
	
	public List<Object> getSystemEquip() {
		StringBuilder sb = new StringBuilder();
		sb.append("select equip_id, name, quality from system_equip");
		userEquipDao.closeSession(DBSource.CFG);
		return userEquipDao.findSQL_(sb.toString());
	}
	
	public UserEquipDao getUserEquipDao() {
		return userEquipDao;
	}

	public void setUserEquipDao(UserEquipDao userEquipDao) {
		this.userEquipDao = userEquipDao;
	}
	
}
