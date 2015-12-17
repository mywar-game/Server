package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserMallRankStats;
import com.stats.dao.UserMallRankStatsDao;

public class UserMallRankStatsService {
	private UserMallRankStatsDao userMallRankStatsDao;

	public UserMallRankStatsDao getUserMallRankStatsDao() {
		return userMallRankStatsDao;
	}

	public void setUserMallRankStatsDao(UserMallRankStatsDao userMallRankStatsDao) {
		this.userMallRankStatsDao = userMallRankStatsDao;
	}
	/**
	 * 批量保存
	 * @param list
	 */
	public void saveBatch(List<UserMallRankStats> list){
		userMallRankStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserMallRankStats> findList(int pageSize, int currentPage) {
		userMallRankStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		return userMallRankStatsDao.findPage("from UserMallRankStats stats where stats.sysNum = ? order by time desc", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserMallRankStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userMallRankStatsDao.closeSession(DBSource.ADMIN);
		return userMallRankStatsDao.findPage("from UserMallRankStats stats where stats.sysNum = ? and stats.time between ? and ? order by time desc", list, pageSize, currentPage);
	}
	
	/**
	 * 删除某天采集的数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserMallRankStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		sql.append(" AND sysNum = ");
		sql.append(CustomerContextHolder.getSystemNum());
		userMallRankStatsDao.closeSession(DBSource.ADMIN);
		userMallRankStatsDao.execute(sql.toString());
	}
}
