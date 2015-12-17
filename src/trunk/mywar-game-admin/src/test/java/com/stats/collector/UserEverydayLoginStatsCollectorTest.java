package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserEverydayLoginStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserEverydayLoginStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserEverydayLoginStatsCollector.class);
		collector.execute(null);
	}
}
