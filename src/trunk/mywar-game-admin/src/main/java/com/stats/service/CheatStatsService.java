package com.stats.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.CheatStats;
import com.stats.dao.CheatStatsDao;

/**
 * 作弊记录
 * @author Administrator
 *
 */
public class CheatStatsService {

	public CheatStatsDao cheatStatsDao;
	
	/**
	 * 批量保存
	 * @param list
	 */
	public void save(List<CheatStats> list) {
		cheatStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	public IPage<CheatStats> findListInDate(int pageSize, int currentPage, Date startDate, Date endDate) {
		List<Object> list = new ArrayList<Object>();
		list.add(CustomerContextHolder.getSystemNum());
		list.add(startDate);
		list.add(endDate);
		cheatStatsDao.closeSession(DBSource.ADMIN);
		return cheatStatsDao.findPage("from CheatStats stats where stats.sysNum = ? and stats.createTime between ? and ?", list, pageSize, currentPage);
	}
	
	public IPage<CheatStats> findList(int pageSize, int currentPage) {
		cheatStatsDao.closeSession(DBSource.ADMIN);
		return cheatStatsDao.findPage("from CheatStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}
	
	public CheatStatsDao getCheatStatsDao() {
		return cheatStatsDao;
	}

	public void setCheatStatsDao(CheatStatsDao cheatStatsDao) {
		this.cheatStatsDao = cheatStatsDao;
	}
}
