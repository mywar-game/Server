package com.stats.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.PayUserInfoStats;
import com.stats.dao.PayUserInfoStatsDao;

public class PayUserInfoStatsService {

	private PayUserInfoStatsDao payUserInfoStatsDao;
	
	/**
	 * 删除之前的统计信息
	 */
	public void deleteBeforeStatsData(){
		payUserInfoStatsDao.closeSession(DBSource.ADMIN);
		String str = "delete from PayUserInfoStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum();
		payUserInfoStatsDao.execute(str);
	}
	
	/**
	 * 统计后保存数据
	 * @param payUserInfoStatsList
	 */
	public void savePayUserInfoStatsList(List<PayUserInfoStats> payUserInfoStatsList){
		if (payUserInfoStatsList != null && payUserInfoStatsList.size() > 0) {
			for (PayUserInfoStats payUserInfoStats : payUserInfoStatsList) {
				payUserInfoStatsDao.save(payUserInfoStats, DBSource.ADMIN);
			}
		}
	}
	
	/**
	 * 分页列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public IPage<PayUserInfoStats> findList(int pageSize, int currentPage) {
		payUserInfoStatsDao.closeSession(DBSource.ADMIN);
		return payUserInfoStatsDao.findPage("from PayUserInfoStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setPayUserInfoStatsDao(PayUserInfoStatsDao payUserInfoStatsDao) {
		this.payUserInfoStatsDao = payUserInfoStatsDao;
	}

	public PayUserInfoStatsDao getPayUserInfoStatsDao() {
		return payUserInfoStatsDao;
	}
}
