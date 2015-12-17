package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserConsumeStats;
import com.stats.dao.UserConsumeStatsDao;

public class UserConsumeStatsService {

	private UserConsumeStatsDao userConsumeStatsDao;

	/**
	 * 保存统计数据
	 * @param userConsumeStats
	 */
	public void save(UserConsumeStats userConsumeStats) {
		userConsumeStatsDao.save(userConsumeStats, DBSource.ADMIN);
	}


	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserConsumeStats> findPageList(int pageSize, int currentPage) {
		userConsumeStatsDao.closeSession(DBSource.ADMIN);
		return userConsumeStatsDao.findPage("from UserConsumeStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserConsumeStats> findPageListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userConsumeStatsDao.closeSession(DBSource.ADMIN);
		return userConsumeStatsDao.findPage("from UserConsumeStats stats where stats.sysNum = ? and stats.date between ? and ?", list, pageSize, currentPage);
	}
	
	/**
	 * 删除某天采集的数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserConsumeStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		userConsumeStatsDao.closeSession(DBSource.ADMIN);
		userConsumeStatsDao.execute(sql.toString());
	}

	public void setUserConsumeStatsDao(UserConsumeStatsDao userConsumeStatsDao) {
		this.userConsumeStatsDao = userConsumeStatsDao;
	}

	public UserConsumeStatsDao getUserConsumeStatsDao() {
		return userConsumeStatsDao;
	}
}
