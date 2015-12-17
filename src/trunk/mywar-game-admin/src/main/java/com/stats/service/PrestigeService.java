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
import com.stats.bo.PrestigeStats;
import com.stats.dao.PrestigeDao;

/**
 * 声望兑换
 * @author Administrator
 *
 */
public class PrestigeService {

	PrestigeDao prestigeDao;
	
	public PrestigeDao getPrestigeDao() {
		return prestigeDao;
	}

	public void setPrestigeDao(PrestigeDao prestigeDao) {
		this.prestigeDao = prestigeDao;
	}

	public void save(PrestigeStats stats) {
		prestigeDao.save(stats, DBSource.ADMIN);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<PrestigeStats> findList() {
		
		Date yesterday = DateUtil.getDiffDate(new Date(), SystemStatsDate.YESTERDAY);
		String yesterdayStr = DateUtil.dateToString(yesterday, DateUtil.LONG_DATE_FORMAT);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(yesterdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Integer sysNum = CustomerContextHolder.getSystemNum();
		List<Object> list = new ArrayList<Object>();
		list.add(sysNum);
		list.add(date);
		prestigeDao.closeSession(DBSource.ADMIN);
		return prestigeDao.find("from PrestigeStats stats where stats.sysNum = ? and stats.time = ? order by stats.time desc", list);
		
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<PrestigeStats> findListInDate(Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		prestigeDao.closeSession(DBSource.ADMIN);
		return prestigeDao.find("from PrestigeStats stats where stats.sysNum = ? and stats.time between ? and ?", list);
	}
}
