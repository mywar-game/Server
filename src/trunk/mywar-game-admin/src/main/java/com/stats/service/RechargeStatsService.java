package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.RechargeStats;
import com.stats.dao.RechargeStatsDao;

public class RechargeStatsService {
	private RechargeStatsDao rechargeStatsDao;

	public RechargeStatsDao getRechargeStatsDao() {
		return rechargeStatsDao;
	}

	public void setRechargeStatsDao(RechargeStatsDao rechargeStatsDao) {
		this.rechargeStatsDao = rechargeStatsDao;
	}
	public void saveBatch(List<RechargeStats> list){
		rechargeStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<RechargeStats> findList(int pageSize, int currentPage) {
		rechargeStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum()); 
		return rechargeStatsDao.findPage("from RechargeStats stats where stats.sysNum = ? order by time desc,stats.partnerId asc", list, pageSize, currentPage);
	}
	
	/**
	 * 在给定日期内的分页列表
	 * @param pageSize
	 * @param currentPage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public IPage<RechargeStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		rechargeStatsDao.closeSession(DBSource.ADMIN);
		return rechargeStatsDao.findPage("from RechargeStats stats where stats.sysNum = ? and stats.time between ? and ? order by time desc,stats.partnerId asc", list, pageSize, currentPage);
	}
	
	/**
	 * 删除某天数据，用于重新采集数据
	 * @param date
	 */
	public void delete(Date date) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from RechargeStats stats where stats.sysNum = ? and stats.time = ?");
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(date);
		rechargeStatsDao.closeSession(DBSource.ADMIN);
		rechargeStatsDao.execute(sql.toString(), list);
	}
}
