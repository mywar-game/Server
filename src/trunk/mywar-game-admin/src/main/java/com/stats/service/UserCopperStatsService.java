package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserCopperStats;
import com.stats.dao.UserCopperStatsDao;

public class UserCopperStatsService {
	private UserCopperStatsDao userCopperStatsDao;

	public UserCopperStatsDao getUserCopperStatsDao() {
		return userCopperStatsDao;
	}

	public void setUserCopperStatsDao(UserCopperStatsDao userCopperStatsDao) {
		this.userCopperStatsDao = userCopperStatsDao;
	}
	
	/**
	 * 保存统计数据
	 * @param userDiamondStats
	 */
	public void save(UserCopperStats userCopperStats) {
		userCopperStatsDao.save(userCopperStats, DBSource.ADMIN);
	}

	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserCopperStats> findList(int pageSize, int currentPage) {
		userCopperStatsDao.closeSession(DBSource.ADMIN);
		Integer sysNum = CustomerContextHolder.getSystemNum();
		List<Object> list = new ArrayList<Object>();
		list.add(sysNum);
		return userCopperStatsDao.findPage("from UserCopperStats stats where stats.sysNum = ? order by stats.time desc", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserCopperStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userCopperStatsDao.closeSession(DBSource.ADMIN);
		return userCopperStatsDao.findPage("from UserCopperStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	public void delete(String dateStr) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from user_copper_stats where TIME = '");
		sb.append(dateStr);
		sb.append("'");
		sb.append(" and SYS_NUM = ");
		sb.append(CustomerContextHolder.getSystemNum());
		userCopperStatsDao.closeSession(DBSource.ADMIN);
		userCopperStatsDao.executeSQL_(sb.toString());
	}
}
