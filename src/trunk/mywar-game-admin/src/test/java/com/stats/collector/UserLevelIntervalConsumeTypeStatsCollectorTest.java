package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserLevelIntervalConsumeTypeStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserLevelIntervalConsumeTypeStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserLevelIntervalConsumeTypeStatsCollector.class);
		collector.execute(null);
		}
	}
