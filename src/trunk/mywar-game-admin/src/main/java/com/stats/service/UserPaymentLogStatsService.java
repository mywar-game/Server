package com.stats.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.framework.common.DBSource; 
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.PaymentLogStats;
import com.stats.dao.PaymentLogStatsDao;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class UserPaymentLogStatsService {

	private PaymentLogStatsDao paymentLogStatsDao;

	/**
	 * 查询充值排序列表
	 * 
	 * 
	 */
	public IPage<PaymentLogStats> findPaymentLogStatsPageList(int toPage,
			int defaultPagesize) {
		paymentLogStatsDao.closeSession(DBSource.ADMIN);
		List<Object> list1 = new ArrayList<Object>();
//		int sysNum = CustomerContextHolder.getSystemNum();
//		TGameServer tGameServer = DataSourceManager.getInstatnce().getGameServerMap().get(sysNum);
//		String serverName = tGameServer.getServerAlias();
//		list1.add(serverName.split("-")[0]);
//		IPage<PaymentLogStats> list = paymentLogStatsDao.findPage(
//				"from PaymentLogStats sts where sts.serverId = ?", list1,
//				defaultPagesize, toPage);
		IPage<PaymentLogStats> list = paymentLogStatsDao.findPage("from PaymentLogStats", list1, defaultPagesize, toPage);
		return list;
	}

	/**
	 * 添加用户综合充值记录
	 * 
	 * 
	 */
	public void addPaymentLogStats(PaymentLogStats paymentLogStats) {
		paymentLogStatsDao.save(paymentLogStats, DBSource.ADMIN);
	}
	
	public void addPaymentLogStatsBatch(Collection<PaymentLogStats> E){
		paymentLogStatsDao.saveBatch(E, DBSource.ADMIN);
	}

	public PaymentLogStatsDao getPaymentLogStatsDao() {
		return paymentLogStatsDao;
	}

	public void setPaymentLogStatsDao(PaymentLogStatsDao paymentLogStatsDao) {
		this.paymentLogStatsDao = paymentLogStatsDao;
	}

	public void deleteAll() {
		
		paymentLogStatsDao.closeSession(DBSource.ADMIN);
		int sysNum = CustomerContextHolder.getSystemNum();
		TGameServer tGameServer = DataSourceManager.getInstatnce().getGameServerMap().get(sysNum);
		String serverName = tGameServer.getServerAlias();
		StringBuilder sb = new StringBuilder();
		sb.append("delete from payment_log_stats where server_id = '");
		sb.append(serverName.split("-")[0]);
		sb.append("'");
		paymentLogStatsDao.executeSQL_(sb.toString());
	}
}
