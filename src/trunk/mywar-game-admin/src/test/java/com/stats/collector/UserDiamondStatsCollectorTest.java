package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserDiamondStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserDiamondStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserDiamondStatsCollector.class);
		collector.execute(null);
		}
}
