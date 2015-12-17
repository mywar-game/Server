package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserPayStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserPayStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserPayStatsCollector.class);
		collector.execute(null);
		}
}
