package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserCreateRoleLossStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserCreateRoleLossStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserCreateRoleLossStatsCollector.class);
		collector.execute(null);
	}
}
