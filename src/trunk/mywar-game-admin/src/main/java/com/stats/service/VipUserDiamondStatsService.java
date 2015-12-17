package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.VipUserDiamondStats;
import com.stats.dao.VipUserDiamondStatsDao;

public class VipUserDiamondStatsService {

	private VipUserDiamondStatsDao vipUserDiamondStatsDao;

	public void saveBatch(List<VipUserDiamondStats> list) {
		vipUserDiamondStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	public IPage<VipUserDiamondStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate, int type) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		list.add(type);
		vipUserDiamondStatsDao.closeSession(DBSource.ADMIN);
		return vipUserDiamondStatsDao.findPage("from VipUserDiamondStats stats where stats.sysNum = ? and stats.date between ? and ? and stats.caterory = ?", list, pageSize, currentPage);
	}
	
	public IPage<VipUserDiamondStats> findList(int pageSize, int currentPage, int type) {
		vipUserDiamondStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		
		list.add(type);
		return vipUserDiamondStatsDao.findPage("from VipUserDiamondStats stats where stats.sysNum = ? and stats.caterory = ?", list, pageSize, currentPage);
	}
	
	public VipUserDiamondStatsDao getVipUserDiamondStatsDao() {
		return vipUserDiamondStatsDao;
	}

	public void setVipUserDiamondStatsDao(
			VipUserDiamondStatsDao vipUserDiamondStatsDao) {
		this.vipUserDiamondStatsDao = vipUserDiamondStatsDao;
	}
}
