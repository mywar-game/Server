package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class PveStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		PveStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(PveStatsCollector.class);
		collector.execute(null);
		}


}