package com.stats.service;

import java.util.ArrayList;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.PayIntervalStats;
import com.stats.dao.PayIntervalStatsDao;

public class PayIntervalStatsService {
	
	private PayIntervalStatsDao payIntervalStatsDao;
	
	/**
	 * 删除之前的统计信息
	 */
	public void deleteBeforeStatsData(){
		payIntervalStatsDao.closeSession(DBSource.ADMIN);
		String str = "delete from PayIntervalStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum();
		payIntervalStatsDao.execute(str);
	} 

	/**
	 * 保存统计数据
	 * @param userActiveStats
	 */
	public void save(PayIntervalStats payIntervalStats) {
		payIntervalStatsDao.save(payIntervalStats, DBSource.ADMIN);
	}
	
	/**
	 * 查询分页列表
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public IPage<PayIntervalStats> findPageList(Integer pageSize, Integer pageIndex){
		payIntervalStatsDao.closeSession(DBSource.ADMIN);
		return payIntervalStatsDao.findPage("from PayIntervalStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, pageIndex);
	}

	public void setPayIntervalStatsDao(PayIntervalStatsDao payIntervalStatsDao) {
		this.payIntervalStatsDao = payIntervalStatsDao;
	}

	public PayIntervalStatsDao getPayIntervalStatsDao() {
		return payIntervalStatsDao;
	}

}
