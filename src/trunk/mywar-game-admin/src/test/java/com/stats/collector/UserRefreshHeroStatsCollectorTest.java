package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserRefreshHeroStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserRefreshHeroStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserRefreshHeroStatsCollector.class);
		try {
			collector.execute(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
