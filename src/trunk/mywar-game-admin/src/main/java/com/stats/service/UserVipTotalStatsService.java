package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserVipTotalStats;
import com.stats.dao.UserVipTotalStatsDao;


public class UserVipTotalStatsService {
	private UserVipTotalStatsDao userVipTotalStatsDao;

	public UserVipTotalStatsDao getUserVipTotalStatsDao() {
		return userVipTotalStatsDao;
	}

	public void setUserVipTotalStatsDao(UserVipTotalStatsDao userVipTotalStatsDao) {
		this.userVipTotalStatsDao = userVipTotalStatsDao;
	}

	/**
	 * 批量保存
	 * @param list
	 */
	public void saveBatch(List<UserVipTotalStats> list){
		userVipTotalStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserVipTotalStats> findList(int pageSize, int currentPage) {
		userVipTotalStatsDao.closeSession(DBSource.ADMIN);
		return userVipTotalStatsDao.findPage("from UserVipTotalStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserVipTotalStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userVipTotalStatsDao.closeSession(DBSource.ADMIN);
		return userVipTotalStatsDao.findPage("from UserVipTotalStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	/**
	 * 删除某天采集的数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserVipTotalStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		userVipTotalStatsDao.closeSession(DBSource.ADMIN);
		userVipTotalStatsDao.execute(sql.toString());
	}
}
