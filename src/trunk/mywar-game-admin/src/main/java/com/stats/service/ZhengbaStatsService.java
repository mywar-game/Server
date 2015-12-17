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
import com.stats.bo.ZhengbaStats;
import com.stats.dao.ZhengbaStatsDao;

/**
 * 争霸
 * @author Administrator
 *
 */
public class ZhengbaStatsService {

	private ZhengbaStatsDao zhengbaStatsDao;
	
	public ZhengbaStatsDao getZhengbaStatsDao() {
		return zhengbaStatsDao;
	}

	public void setZhengbaStatsDao(ZhengbaStatsDao zhengbaStatsDao) {
		this.zhengbaStatsDao = zhengbaStatsDao;
	}

	public void save(ZhengbaStats stats) {
		// TODO
		zhengbaStatsDao.save(stats, DBSource.ADMIN);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param artifactType
	 * @return
	 */
	public IPage<ZhengbaStats> findList(int pageSize, int currentPage) {
		
		Date yesterday = DateUtil.getDiffDate(new Date(), SystemStatsDate.YESTERDAY);
		String yesterdayStr = DateUtil.dateToString(yesterday, DateUtil.LONG_DATE_FORMAT);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(yesterdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		zhengbaStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(date);
		return zhengbaStatsDao.findPage("from ZhengbaStats stats where stats.sysNum = ? and stats.time = ?", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<ZhengbaStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		zhengbaStatsDao.closeSession(DBSource.ADMIN);
		return zhengbaStatsDao.findPage("from ZhengbaStats stats where stats.sysNum = ? and stats.time between ? and ?", list, pageSize, currentPage);
	}
	

	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ZhengbaStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		zhengbaStatsDao.closeSession(DBSource.ADMIN);
		zhengbaStatsDao.execute(sql.toString());
	}
}
