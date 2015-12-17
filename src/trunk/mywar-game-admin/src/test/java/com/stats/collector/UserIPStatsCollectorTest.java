package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserIPStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserIPStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserIPStatsCollector.class);
		collector.execute(null);
		}
}
