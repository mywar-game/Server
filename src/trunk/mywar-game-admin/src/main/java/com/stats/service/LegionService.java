package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.LegionStats;
import com.stats.dao.UserLegionDao;
import com.system.manager.DataSourceManager;

/**
 * 
 * @author Administrator
 *
 */
public class LegionService {

	private UserLegionDao userLegionDao;

	public UserLegionDao getUserLegionDao() {
		return userLegionDao;
	}

	public void setUserLegionDao(UserLegionDao userLegionDao) {
		this.userLegionDao = userLegionDao;
	}
	
	/**
	 * 军团参与人数
	 * @return
	 */
	public int getLegionJoin() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*),id from user_legion_info");
		userLegionDao.closeSession(DBSource.CFG);
		List<Object> list = userLegionDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				return Integer.valueOf(arr[0].toString());
			}
		}
		return 0;
	}
	
	/**
	 * 军团总数
	 * @return
	 */
	public int getLegionTotal() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*),id from legion_info");
		userLegionDao.closeSession(DBSource.CFG);
		List<Object> list = userLegionDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				return Integer.valueOf(arr[0].toString());
			}
		}
		return 0;
	}
	
	/**
	 * 军团钻石消费
	 * @param dates
	 * @return
	 */
	public int getDiamondUse(String[] dates) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_ID,SUM(CHANGE_NUM) FROM (select * from user_gold_log union all select * from user_gold_log_bak) log ");
		sql.append(" where ");
		if (dates != null) {
			sql.append(" log.TIME BETWEEN '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		// others
		sql.append(" AND type = 20073 or type = 20074 or type = 20075");
		userLegionDao.closeSession(DBSource.LOG);
		List<Object> list = userLegionDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				return Integer.valueOf(arr[1].toString());
			}
		}
		return 0;
	}
	
	/**
	 * 军团金币花费
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
		sql.append(" AND OPERATION = 20073 or OPERATION = 20074");
		userLegionDao.closeSession(DBSource.LOG);
		List<Object> list = userLegionDao.findSQL_(sql.toString()); 
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
	 * 军团钻石消费人数
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
		sql.append(" AND type = 20073 or type = 20074 or type = 20075");
		userLegionDao.closeSession(DBSource.LOG);
		List<Object> list = userLegionDao.findSQL_(sql.toString());
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
	public IPage<LegionStats> findList(int pageSize, int currentPage) {
		userLegionDao.closeSession(DBSource.ADMIN);
		Integer sysNum = CustomerContextHolder.getSystemNum();
		List<Object> list = new ArrayList<Object>();
		list.add(sysNum);
		return userLegionDao.findPage("from LegionStats stats where stats.sysNum = ? order by stats.time desc", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<LegionStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userLegionDao.closeSession(DBSource.ADMIN);
		return userLegionDao.findPage("from LegionStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	/**
	 * 保存
	 * @param stats
	 */
	public void saveStats(LegionStats stats) {
		userLegionDao.save(stats, DBSource.ADMIN);
	}
}
