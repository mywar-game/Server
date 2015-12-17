package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserRefreshHeroStats;
import com.stats.dao.UserRefreshHeroStatsDao;

public class UserRefreshHeroStatsService {

	private UserRefreshHeroStatsDao userRefreshHeroStatsDao;

	public void save(UserRefreshHeroStats userRefreshHeroStats) {
		userRefreshHeroStatsDao.save(userRefreshHeroStats, DBSource.ADMIN);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserRefreshHeroStats> findList(int pageSize, int currentPage) {
		userRefreshHeroStatsDao.closeSession(DBSource.ADMIN);
		return userRefreshHeroStatsDao.findPage("from UserRefreshHeroStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return 
	 */
	public IPage<UserRefreshHeroStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userRefreshHeroStatsDao.closeSession(DBSource.ADMIN);
		return userRefreshHeroStatsDao.findPage("from UserRefreshHeroStats stats where stats.sysNum = ? and stats.date between ? and ?", list, pageSize, currentPage);
	}

	/**
	 * @return the userRefreshHeroStatsDao
	 */
	public UserRefreshHeroStatsDao getUserRefreshHeroStatsDao() {
		return userRefreshHeroStatsDao;
	}

	/**
	 * @param userRefreshHeroStatsDao the userRefreshHeroStatsDao to set
	 */
	public void setUserRefreshHeroStatsDao(
			UserRefreshHeroStatsDao userRefreshHeroStatsDao) {
		this.userRefreshHeroStatsDao = userRefreshHeroStatsDao;
	}
}
