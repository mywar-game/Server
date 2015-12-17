package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.CompetitionStats;
import com.stats.dao.CompetitionDao;
import com.system.manager.DataSourceManager;

/**
 * 天界统计
 * @author Administrator
 *
 */
public class CompetitionService {
	
	private CompetitionDao competitionDao;
	
	public CompetitionDao getCompetitionDao() {
		return competitionDao;
	}

	public void setCompetitionDao(CompetitionDao competitionDao) {
		this.competitionDao = competitionDao;
	}

	/**
	 * 天界金币消耗人数
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
		sql.append(" AND OPERATION = 20069");
		competitionDao.closeSession(DBSource.LOG);
		List<Object> list = competitionDao.findSQL_(sql.toString()); 
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
	 * 天界钻石消费
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
		sql.append(" AND type = 20069 or type = 20077");
		competitionDao.closeSession(DBSource.LOG);
		List<Object> list = competitionDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				return Integer.valueOf(arr[1].toString());
			}
		}
		return 0;
	}
	
	/**
	 * 天界钻石消费人数
	 * @param dates
	 * @return
	 */
	public int getDiamondJoin(String[] dates) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_ID,COUNT(DISTINCT(USER_ID)) FROM (select * from user_gold_log union all select * from user_gold_log_bak) log ");
		sql.append(" where ");
		if (dates != null) {
			sql.append(" log.TIME BETWEEN '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		// others
		sql.append(" AND type = 20069 or type = 20077");
		competitionDao.closeSession(DBSource.LOG);
		List<Object> list = competitionDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size();) {
				Object[] arr = (Object[]) list.get(i);
				return Integer.valueOf(arr[1].toString());
			}
		}
		return 0;
	}
	
	/**
	 * 天界金币消耗总数
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
		sql.append(" AND OPERATION = 20069");
		competitionDao.closeSession(DBSource.LOG);
		List<Object> list = competitionDao.findSQL_(sql.toString()); 
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
	public IPage<CompetitionStats> findList(int pageSize, int currentPage) {
		competitionDao.closeSession(DBSource.ADMIN);
		Integer sysNum = CustomerContextHolder.getSystemNum();
		List<Object> list = new ArrayList<Object>();
		list.add(sysNum);
		return competitionDao.findPage("from CompetitionStats stats where stats.sysNum = ? order by stats.time desc", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<CompetitionStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		competitionDao.closeSession(DBSource.ADMIN);
		return competitionDao.findPage("from CompetitionStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	public void save(CompetitionStats stats) {
		competitionDao.save(stats, DBSource.ADMIN);
		
	}
}
