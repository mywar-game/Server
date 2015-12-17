package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserRemainStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserRemainStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserRemainStatsCollector.class);
		try {
			collector.execute(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}


}
