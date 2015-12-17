package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserEverydayLoginStats;
import com.stats.dao.UserEverydayLoginStatsDao;

public class UserEverydayLoginStatsService {
	private UserEverydayLoginStatsDao userEverydayLoginStatsDao;
	
	/**
	 * 保存统计数据
	 * @param userEverydayLoginStats
	 */
	public void save(UserEverydayLoginStats userEverydayLoginStats) {
		userEverydayLoginStatsDao.save(userEverydayLoginStats, DBSource.ADMIN);
	}

	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserEverydayLoginStats> findList(int pageSize, int currentPage) {
		userEverydayLoginStatsDao.closeSession(DBSource.ADMIN);
		return userEverydayLoginStatsDao.findPage("from UserEverydayLoginStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserEverydayLoginStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userEverydayLoginStatsDao.closeSession(DBSource.ADMIN);
		return userEverydayLoginStatsDao.findPage("from UserEverydayLoginStats stats where stats.sysNum = ? and stats.date between ? and ?", list, pageSize, currentPage);
	}
	
	/**
	 * 查询总次数的最大值
	 * @return
	 */
	public int findMaxTotalTimes() {
		List<Object> list = userEverydayLoginStatsDao.findSQL_("select max(TOTAL_TIMES) from user_everyday_login_stats");
		return Integer.parseInt(list.get(0).toString()); 
	}
	
	/**
	 * 删除某天采集的数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserEverydayLoginStats where DATE = '");
		sql.append(dateStr);
		sql.append("'");
		sql.append(" and sysNum = ");
		sql.append(CustomerContextHolder.getSystemNum());
		userEverydayLoginStatsDao.closeSession(DBSource.ADMIN);
		userEverydayLoginStatsDao.execute(sql.toString());
	}
	
	public void setUserEverydayLoginStatsDao(UserEverydayLoginStatsDao userEverydayLoginStatsDao) {
		this.userEverydayLoginStatsDao = userEverydayLoginStatsDao;
	}

	public UserEverydayLoginStatsDao getUserEverydayLoginStatsDao() {
		return userEverydayLoginStatsDao;
	}
}
