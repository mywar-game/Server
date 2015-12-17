package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserCreateRoleLossStats;
import com.stats.dao.UserCreateRoleLossStatsDao;

public class UserCreateRoleLossStatsService {
	private UserCreateRoleLossStatsDao userCreateRoleLossStatsDao;
	
	/**
	 * 保存统计数据
	 * @param userCreateRoleLossStats
	 */
	public void save(UserCreateRoleLossStats userCreateRoleLossStats) {
		userCreateRoleLossStatsDao.save(userCreateRoleLossStats, DBSource.ADMIN);
	}

	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserCreateRoleLossStats> findList(int pageSize, int currentPage) {
		userCreateRoleLossStatsDao.closeSession(DBSource.ADMIN);
		return userCreateRoleLossStatsDao.findPage("from UserCreateRoleLossStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserCreateRoleLossStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userCreateRoleLossStatsDao.closeSession(DBSource.ADMIN);
		return userCreateRoleLossStatsDao.findPage("from UserCreateRoleLossStats stats where stats.sysNum = ? and stats.date between ? and ?", list, pageSize, currentPage);
	}
	
	public void setUserCreateRoleLossStatsDao(UserCreateRoleLossStatsDao userCreateRoleLossStatsDao) {
		this.userCreateRoleLossStatsDao = userCreateRoleLossStatsDao;
	}

	public UserCreateRoleLossStatsDao getUserCreateRoleLossStatsDao() {
		return userCreateRoleLossStatsDao;
	}
}
