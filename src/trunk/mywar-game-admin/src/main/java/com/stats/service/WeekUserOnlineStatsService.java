package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.WeekUserOnlineStats;
import com.stats.dao.WeekUserOnlineStatsDao;

public class WeekUserOnlineStatsService {

	private WeekUserOnlineStatsDao weekUserOnlineStatsDao;
	
	/**
	 * 保存统计数据
	 * @param userActiveStats
	 */
	public void save(WeekUserOnlineStats weekUserOnlineStats) {
		weekUserOnlineStatsDao.save(weekUserOnlineStats, DBSource.ADMIN);
	}

	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<WeekUserOnlineStats> findPageList(int pageSize, int currentPage) {
		weekUserOnlineStatsDao.closeSession(DBSource.ADMIN);
		return weekUserOnlineStatsDao.findPage("from WeekUserOnlineStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<WeekUserOnlineStats> findPageListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		weekUserOnlineStatsDao.closeSession(DBSource.ADMIN);
		return weekUserOnlineStatsDao.findPage("from WeekUserOnlineStats stats where stats.sysNum = ? and stats.date between ? and ?", list, pageSize, currentPage);
	}

	public void setWeekUserOnlineStatsDao(WeekUserOnlineStatsDao weekUserOnlineStatsDao) {
		this.weekUserOnlineStatsDao = weekUserOnlineStatsDao;
	}

	public WeekUserOnlineStatsDao getWeekUserOnlineStatsDao() {
		return weekUserOnlineStatsDao;
	}
}
