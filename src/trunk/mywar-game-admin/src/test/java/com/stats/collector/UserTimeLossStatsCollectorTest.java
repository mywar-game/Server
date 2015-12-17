package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserTimeLossStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserTimeLossStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserTimeLossStatsCollector.class);
		collector.execute(null);
	}
}
