package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserConsumeStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserConsumeStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserConsumeStatsCollector.class);
		collector.execute(null);
		}
}
