package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.NormalDataStats;
import com.stats.dao.NormalDataStatsDao;

public class NormalDataStatsService {
	private NormalDataStatsDao normalDataStatsDao;

	public NormalDataStatsDao getNormalDataStatsDao() {
		return normalDataStatsDao;
	}

	public void setNormalDataStatsDao(NormalDataStatsDao normalDataStatsDao) {
		this.normalDataStatsDao = normalDataStatsDao;
	}
	
	/**
	 * 保存统计数据
	 * @param stats
	 */
	public void save(NormalDataStats stats){
		normalDataStatsDao.save(stats, DBSource.ADMIN);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<NormalDataStats> findList(int pageSize, int currentPage) {
		normalDataStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		return normalDataStatsDao.findPage("from NormalDataStats stats where stats.sysNum = ? order by time desc", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<NormalDataStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		normalDataStatsDao.closeSession(DBSource.ADMIN);
		return normalDataStatsDao.findPage("from NormalDataStats stats where stats.sysNum = ? and stats.time between ? and ? order by time desc", list, pageSize, currentPage);
	}
	
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from NormalDataStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		normalDataStatsDao.closeSession(DBSource.ADMIN);
		normalDataStatsDao.execute(sql.toString());
	}
	
}
