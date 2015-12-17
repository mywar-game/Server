package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserDiamondStats;
import com.stats.dao.UserDiamondStatsDao;

public class UserDiamondStatsService {

	private UserDiamondStatsDao userDiamondStatsDao;
	
	/**
	 * 保存统计数据
	 * @param userDiamondStats
	 */
	public void save(UserDiamondStats userDiamondStats) {
		userDiamondStatsDao.save(userDiamondStats, DBSource.ADMIN);
	}

	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<UserDiamondStats> findList(int pageSize, int currentPage) {
		userDiamondStatsDao.closeSession(DBSource.ADMIN);
		Integer sysNum = CustomerContextHolder.getSystemNum();
		List<Object> list = new ArrayList<Object>();
		list.add(sysNum);
		return userDiamondStatsDao.findPage("from UserDiamondStats stats where stats.sysNum = ? order by stats.time desc", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserDiamondStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userDiamondStatsDao.closeSession(DBSource.ADMIN);
		return userDiamondStatsDao.findPage("from UserDiamondStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	public long findLeftDiamond(String dateStr) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select LEFT_DIAMOND, ID from user_diamond_stats where TIME = '");
		sb.append(dateStr);
		sb.append("'");
		sb.append(" and SYS_NUM = ");
		sb.append(CustomerContextHolder.getSystemNum());
		userDiamondStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = userDiamondStatsDao.findSQL_(sb.toString());
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = (Object[]) list.get(i);
				return Long.parseLong(objArr[0].toString());
			}
		}
		return 0;
	}
	
	public void delete(String dateStr) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from user_diamond_stats where TIME = '");
		sb.append(dateStr);
		sb.append("'");
		sb.append(" and SYS_NUM = ");
		sb.append(CustomerContextHolder.getSystemNum());
		userDiamondStatsDao.closeSession(DBSource.ADMIN);
		userDiamondStatsDao.executeSQL_(sb.toString());
	}
	
	public void setUserDiamondStatsDao(UserDiamondStatsDao userDiamondStatsDao) {
		this.userDiamondStatsDao = userDiamondStatsDao;
	}

	public UserDiamondStatsDao getUserDiamondStatsDao() {
		return userDiamondStatsDao;
	}
}
