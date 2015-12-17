package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserFriendVisitStats;
import com.stats.dao.UserFriendVisitStatsDao;

public class UserFriendVisitStatsService {

	private UserFriendVisitStatsDao userFriendVisitStatsDao;
	
	public void save(UserFriendVisitStats userFriendVisitStats) {
		userFriendVisitStatsDao.save(userFriendVisitStats, DBSource.ADMIN);
	}

	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserFriendVisitStats> findList(int pageSize, int currentPage) {
		userFriendVisitStatsDao.closeSession(DBSource.ADMIN);
		return userFriendVisitStatsDao.findPage("from UserFriendVisitStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserFriendVisitStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userFriendVisitStatsDao.closeSession(DBSource.ADMIN);
		return userFriendVisitStatsDao.findPage("from UserFriendVisitStats stats where stats.sysNum = ? and stats.date between ? and ?", list, pageSize, currentPage);
	}
	
	public void setUserFriendVisitStatsDao(UserFriendVisitStatsDao userFriendVisitStatsDao) {
		this.userFriendVisitStatsDao = userFriendVisitStatsDao;
	}

	public UserFriendVisitStatsDao getUserFriendVisitStatsDao() {
		return userFriendVisitStatsDao;
	}
}
