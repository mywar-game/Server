package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserFriendVisitStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserFriendVisitStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserFriendVisitStatsCollector.class);
		try {
			collector.execute(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
}
