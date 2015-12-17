package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserOnlineStats;
import com.stats.dao.UserOnlineStatsDao;

public class UserOnlineStatsService {
	private UserOnlineStatsDao userOnlineStatsDao;
	
	/**
	 * 保存统计数据
	 * @param userOnlineStats
	 */
	public void save(UserOnlineStats userOnlineStats) {
		userOnlineStatsDao.save(userOnlineStats, DBSource.ADMIN);
	}

	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserOnlineStats> findPageList(int pageSize, int currentPage) {
		userOnlineStatsDao.closeSession(DBSource.ADMIN);
		return userOnlineStatsDao.findPage("from UserOnlineStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserOnlineStats> findPageListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userOnlineStatsDao.closeSession(DBSource.ADMIN);
		return userOnlineStatsDao.findPage("from UserOnlineStats stats where stats.sysNum = ? and stats.date between ? and ?", list, pageSize, currentPage);
	}
	
	/**
	 * 删除某天采集的数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserOnlineStats where DATE = '");
		sql.append(dateStr);
		sql.append("'");
		sql.append(" and SYS_NUM = ");
		sql.append(CustomerContextHolder.getSystemNum());
		userOnlineStatsDao.closeSession(DBSource.ADMIN);
		userOnlineStatsDao.execute(sql.toString());
	}
	
	public void setUserOnlineStatsDao(UserOnlineStatsDao userOnlineStatsDao) {
		this.userOnlineStatsDao = userOnlineStatsDao;
	}

	public UserOnlineStatsDao getUserOnlineStatsDao() {
		return userOnlineStatsDao;
	}
}
