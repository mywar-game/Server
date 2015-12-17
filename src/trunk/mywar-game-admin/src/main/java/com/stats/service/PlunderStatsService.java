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
import com.stats.bo.PlunderStats;
import com.stats.dao.PlunderStatsDao;

/**
 * 夺宝统计
 * @author Administrator
 *
 */
public class PlunderStatsService {

	private PlunderStatsDao plunderStatsDao;
	
	public PlunderStatsDao getPlunderStatsDao() {
		return plunderStatsDao;
	}

	public void setPlunderStatsDao(PlunderStatsDao plunderStatsDao) {
		this.plunderStatsDao = plunderStatsDao;
	}

	public void save(PlunderStats stats) {
		// TODO
		plunderStatsDao.save(stats, DBSource.ADMIN);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param artifactType
	 * @return
	 */
	public IPage<PlunderStats> findList(int pageSize, int currentPage) {
		
		Date yesterday = DateUtil.getDiffDate(new Date(), SystemStatsDate.YESTERDAY);
		String yesterdayStr = DateUtil.dateToString(yesterday, DateUtil.LONG_DATE_FORMAT);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(yesterdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		plunderStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(date);
		return plunderStatsDao.findPage("from PlunderStats stats where stats.sysNum = ? and stats.time = ?", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<PlunderStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		plunderStatsDao.closeSession(DBSource.ADMIN);
		return plunderStatsDao.findPage("from PlunderStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from PlunderStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		plunderStatsDao.closeSession(DBSource.ADMIN);
		plunderStatsDao.execute(sql.toString());
	}
}
