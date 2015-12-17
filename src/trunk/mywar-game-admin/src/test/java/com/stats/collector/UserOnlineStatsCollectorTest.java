package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserOnlineStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserOnlineStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserOnlineStatsCollector.class);
		collector.execute(null);
	}
}
