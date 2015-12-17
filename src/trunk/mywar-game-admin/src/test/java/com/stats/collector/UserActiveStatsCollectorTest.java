package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserActiveStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
	UserActiveStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserActiveStatsCollector.class);
	collector.execute(null);
	}
}
