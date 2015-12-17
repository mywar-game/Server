package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserActiveStats;
import com.stats.dao.UserActiveStatsDao;

public class UserActiveStatsService {
	private UserActiveStatsDao userActiveStatsDao;
	
	/**
	 * 保存统计数据
	 * @param userActiveStats
	 */
	public void save(UserActiveStats userActiveStats) {
		userActiveStatsDao.save(userActiveStats, DBSource.ADMIN);
	}

	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserActiveStats> findList(int pageSize, int currentPage) {
		userActiveStatsDao.closeSession(DBSource.ADMIN);
		return userActiveStatsDao.findPage("from UserActiveStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserActiveStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userActiveStatsDao.closeSession(DBSource.ADMIN);
		return userActiveStatsDao.findPage("from UserActiveStats stats where stats.sysNum = ? and stats.date between ? and ?", list, pageSize, currentPage);
	}
	
	public void setUserActiveStatsDao(UserActiveStatsDao userActiveStatsDao) {
		this.userActiveStatsDao = userActiveStatsDao;
	}

	public UserActiveStatsDao getUserActiveStatsDao() {
		return userActiveStatsDao;
	}
}
