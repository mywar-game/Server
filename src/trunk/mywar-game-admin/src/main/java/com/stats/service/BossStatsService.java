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
import com.stats.bo.BossStats;
import com.stats.dao.BossStatsDao;

public class BossStatsService {

	private BossStatsDao bossStatsDao;

	public BossStatsDao getBossStatsDao() {
		return bossStatsDao;
	}

	public void setBossStatsDao(BossStatsDao bossStatsDao) {
		this.bossStatsDao = bossStatsDao;
	}
	
	public void save(BossStats stats) {
		this.bossStatsDao.save(stats, DBSource.ADMIN);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param artifactType
	 * @return
	 */
	public IPage<BossStats> findList(int pageSize, int currentPage) {
		
		Date yesterday = DateUtil.getDiffDate(new Date(), SystemStatsDate.YESTERDAY);
		String yesterdayStr = DateUtil.dateToString(yesterday, DateUtil.LONG_DATE_FORMAT);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(yesterdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		bossStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(date);
		return bossStatsDao.findPage("from BossStats stats where stats.sysNum = ? and stats.time = ?", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<BossStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		bossStatsDao.closeSession(DBSource.ADMIN);
		return bossStatsDao.findPage("from BossStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
}
