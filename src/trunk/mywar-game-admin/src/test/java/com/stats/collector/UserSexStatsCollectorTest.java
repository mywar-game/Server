package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class UserSexStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		UserSexStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(UserSexStatsCollector.class);
		try {
			collector.execute(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}


}
