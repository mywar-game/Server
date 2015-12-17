package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserTimeLossStats;
import com.stats.dao.UserTimeLossStatsDao;

public class UserTimeLossStatsService {
	
	private UserTimeLossStatsDao userTimeLossStatsDao;

	/**
	 * 保存统计数据
	 * @param userTimeLossStats
	 */
	public void save(UserTimeLossStats userTimeLossStats) {
		userTimeLossStatsDao.save(userTimeLossStats, DBSource.ADMIN);
	}

	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserTimeLossStats> findPageList(int pageSize, int currentPage) {
		userTimeLossStatsDao.closeSession(DBSource.ADMIN);
		return userTimeLossStatsDao.findPage("from UserTimeLossStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserTimeLossStats> findPageListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userTimeLossStatsDao.closeSession(DBSource.ADMIN);
		return userTimeLossStatsDao.findPage("from UserTimeLossStats stats where stats.sysNum = ? and stats.date between ? and ?", list, pageSize, currentPage);
	}
	
	/**
	 * 删除某天采集的数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserTimeLossStats where DATE = '");
		sql.append(dateStr);
		sql.append("'");
		sql.append(" and SYS_NUM = ");
		sql.append(CustomerContextHolder.getSystemNum());
		userTimeLossStatsDao.closeSession(DBSource.ADMIN);
		userTimeLossStatsDao.execute(sql.toString());
	}
	
	public void setUserTimeLossStatsDao(UserTimeLossStatsDao userTimeLossStatsDao) {
		this.userTimeLossStatsDao = userTimeLossStatsDao;
	}

	public UserTimeLossStatsDao getUserTimeLossStatsDao() {
		return userTimeLossStatsDao;
	}
}
