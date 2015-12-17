package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.EquipStats;
import com.stats.dao.EquipDao;
import com.system.manager.DataSourceManager;

public class EquipService {

	private EquipDao equipDao;

	public EquipDao getEquipDao() {
		return equipDao;
	}

	public void setEquipDao(EquipDao equipDao) {
		this.equipDao = equipDao;
	}
	
	/**
	 * 战魂钻石消耗人数
	 * @param dates
	 * @return
	 */
	public int getDiamondUseTotal(String[] dates) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_ID,count(distinct(user_id)) FROM (select * from user_gold_log union all select * from user_gold_log_bak) log ");
		sql.append(" where ");
		if (dates != null) {
			sql.append(" log.TIME BETWEEN '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		// others
		sql.append(" AND type = 20078");
		equipDao.closeSession(DBSource.LOG);
		List<Object> list = equipDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				if (arr[1] == null) {
					return 0;
				}
				return Integer.valueOf(arr[1].toString());
			}
		}
		return 0;
	}
	
	/**
	 * 战魂钻石消耗次数
	 * @param dates
	 * @return
	 */
	public int getDiamondUseCountTotal(String[] dates) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_ID,count(*) FROM (select * from user_gold_log union all select * from user_gold_log_bak) log ");
		sql.append(" where log.category = ").append(2);
		if (dates != null) {
			sql.append(" AND log.TIME BETWEEN '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		// others
		sql.append(" AND type = 20078");
		equipDao.closeSession(DBSource.LOG);
		List<Object> list = equipDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				if (arr[1] == null) {
					return 0;
				}
				return Integer.valueOf(arr[1].toString());
			}
		}
		return 0;
	}
	
	/**
	 * 战魂钻石消耗
	 * @param dates
	 * @return
	 */
	public int getDiamondUse(String[] dates) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_ID,sum(change_num) FROM (select * from user_gold_log union all select * from user_gold_log_bak) log ");
		sql.append(" where log.category = ").append(2);
		if (dates != null) {
			sql.append(" AND log.TIME BETWEEN '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		// others
		sql.append(" AND type = 20078");
		equipDao.closeSession(DBSource.LOG);
		List<Object> list = equipDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				if (arr[1] == null) {
					return 0;
				}
				return Integer.valueOf(arr[1].toString());
			}
		}
		return 0;
	}
	
	/**
	 * 战魂金币消耗
	 * @param dates
	 * @return
	 */
	public int getCoinUse(String[] dates) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_resource_log", dates);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select user_id, SUM(MONEY) from ");
		sql.append(tableName);
		sql.append(" where create_time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		// others
		sql.append(" AND OPERATION = 20071 or OPERATION = 20072");
		equipDao.closeSession(DBSource.LOG);
		List<Object> list = equipDao.findSQL_(sql.toString()); 
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				if (arr[1] == null) {
					return 0;
				}
				return Integer.valueOf(arr[1].toString());
			}
		}
		return 0;
	}
	
	/**
	 * 战魂金币消耗人数
	 * @param dates
	 * @return
	 */
	public int getCoinUseTotal(String[] dates) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_resource_log", dates);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select user_id, count(distinct(user_id)) from ");
		sql.append(tableName);
		sql.append(" where create_time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		// others
		sql.append(" AND OPERATION = 20071 or OPERATION = 20072");
		equipDao.closeSession(DBSource.LOG);
		List<Object> list = equipDao.findSQL_(sql.toString()); 
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				if (arr[1] == null) {
					return 0;
				}
				return Integer.valueOf(arr[1].toString());
			}
		}
		return 0;
	}
	
	/**
	 * 战魂金币消耗次数
	 * @param dates
	 * @return
	 */
	public int getCoinUseCountTotal(String[] dates) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_resource_log", dates);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select user_id, count(*) from ");
		sql.append(tableName);
		sql.append(" where create_time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		// others
		sql.append(" AND OPERATION = 20071 or OPERATION = 20072");
		equipDao.closeSession(DBSource.LOG);
		List<Object> list = equipDao.findSQL_(sql.toString()); 
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				if (arr[1] == null) {
					return 0;
				}
				return Integer.valueOf(arr[1].toString());
			}
		}
		return 0;
	}
	
	/**
	 * 战魂令牌消耗
	 * @param dates
	 * @return
	 */
	public int getLingUse(String[] dates) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		String tableName = manager.getTableIndex("user_treasure_log", dates);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select user_id, count(*) from ");
		sql.append(tableName);
		sql.append(" where create_time between '");
		sql.append(dates[0]);
		sql.append("' AND '");
		sql.append(dates[1]);
		sql.append("'");
		// others
		sql.append(" AND OPERATION = 20078");
		equipDao.closeSession(DBSource.LOG);
		List<Object> list = equipDao.findSQL_(sql.toString()); 
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				if (arr[1] == null) {
					return 0;
				}
				return Integer.valueOf(arr[1].toString());
			}
		}
		return 0;
	}
	

	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<EquipStats> findList(int pageSize, int currentPage) {
		equipDao.closeSession(DBSource.ADMIN);
		Integer sysNum = CustomerContextHolder.getSystemNum();
		List<Object> list = new ArrayList<Object>();
		list.add(sysNum);
		return equipDao.findPage("from EquipStats stats where stats.sysNum = ? order by stats.time desc", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<EquipStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		equipDao.closeSession(DBSource.ADMIN);
		return equipDao.findPage("from EquipStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	public void saveStats(EquipStats stats) {
		equipDao.save(stats, DBSource.ADMIN);
	}
}
