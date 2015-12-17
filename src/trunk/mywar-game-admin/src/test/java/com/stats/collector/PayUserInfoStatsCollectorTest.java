package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class PayUserInfoStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		PayUserInfoStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(PayUserInfoStatsCollector.class);
		collector.execute(null);
		}


}
