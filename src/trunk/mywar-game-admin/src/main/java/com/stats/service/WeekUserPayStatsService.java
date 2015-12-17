package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.WeekUserPayStats;
import com.stats.dao.WeekUserPayStatsDao;

public class WeekUserPayStatsService {
	
	private WeekUserPayStatsDao weekUserPayStatsDao;
	
	/**
	 * 保存统计数据
	 * @param userConsumeStats
	 */
	public void save(WeekUserPayStats weekUserPayStats) {
		weekUserPayStatsDao.save(weekUserPayStats, DBSource.ADMIN);
	}


	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<WeekUserPayStats> findPageList(int pageSize, int currentPage) {
		weekUserPayStatsDao.closeSession(DBSource.ADMIN);
		return weekUserPayStatsDao.findPage("from WeekUserPayStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<WeekUserPayStats> findPageListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		weekUserPayStatsDao.closeSession(DBSource.ADMIN);
		return weekUserPayStatsDao.findPage("from WeekUserPayStats stats where stats.sysNum = ? and stats.date between ? and ?", list, pageSize, currentPage);
	}

	public void setWeekUserPayStatsDao(WeekUserPayStatsDao weekUserPayStatsDao) {
		this.weekUserPayStatsDao = weekUserPayStatsDao;
	}

	public WeekUserPayStatsDao getWeekUserPayStatsDao() {
		return weekUserPayStatsDao;
	}
}
