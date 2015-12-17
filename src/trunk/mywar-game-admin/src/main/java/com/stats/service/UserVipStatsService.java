package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserVipStats;
import com.stats.dao.UserVipStatsDao;

public class UserVipStatsService {
	private UserVipStatsDao userVipStatsDao;

	public UserVipStatsDao getUserVipStatsDao() {
		return userVipStatsDao;
	}

	public void setUserVipStatsDao(UserVipStatsDao userVipStatsDao) {
		this.userVipStatsDao = userVipStatsDao;
	}
	/**
	 * 批量保存
	 * @param list
	 */
	public void saveBatch(List<UserVipStats> list){
		userVipStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserVipStats> findList(int pageSize, int currentPage) {
		userVipStatsDao.closeSession(DBSource.ADMIN);
		return userVipStatsDao.findPage("from UserVipStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserVipStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userVipStatsDao.closeSession(DBSource.ADMIN);
		return userVipStatsDao.findPage("from UserVipStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	/**
	 * 删除某天采集的数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserVipStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		sql.append(" and SYS_NUM = ");
		sql.append(CustomerContextHolder.getSystemNum());
		userVipStatsDao.closeSession(DBSource.ADMIN);
		userVipStatsDao.execute(sql.toString());
	}
}
