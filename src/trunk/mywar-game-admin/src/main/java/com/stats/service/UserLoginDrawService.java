package com.stats.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.common.SystemStatsDate;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.util.DateUtil;
import com.stats.bo.UserLoginDrawStats;
import com.stats.dao.UserLoginDrawDao;

public class UserLoginDrawService {

	private UserLoginDrawDao userLoginDrawDao;

	public void save(UserLoginDrawStats stats) {
		userLoginDrawDao.save(stats, DBSource.ADMIN);
	}
	
	public UserLoginDrawDao getUserLoginDrawDao() {
		return userLoginDrawDao;
	}

	public void setUserLoginDrawDao(UserLoginDrawDao userLoginDrawDao) {
		this.userLoginDrawDao = userLoginDrawDao;
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param artifactType
	 * @return
	 */
	public IPage<UserLoginDrawStats> findList(int pageSize, int currentPage) {
		
		Date yesterday = DateUtil.getDiffDate(new Date(), SystemStatsDate.YESTERDAY);
		String yesterdayStr = DateUtil.dateToString(yesterday, DateUtil.LONG_DATE_FORMAT);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(yesterdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		userLoginDrawDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(date);
		return userLoginDrawDao.findPage("from UserLoginDrawStats stats where stats.sysNum = ? and stats.time = ?", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<UserLoginDrawStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		userLoginDrawDao.closeSession(DBSource.ADMIN);
		return userLoginDrawDao.findPage("from UserLoginDrawStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from UserLoginDrawStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		sql.append(" and SYS_NUM = ");
		sql.append(CustomerContextHolder.getSystemNum());
		userLoginDrawDao.closeSession(DBSource.ADMIN);
		userLoginDrawDao.execute(sql.toString());	
	}
}
