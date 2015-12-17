package com.stats.collector;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class PayIntervalStatsCollectorTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		PayIntervalStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(PayIntervalStatsCollector.class);
		try {
			collector.execute(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}


}
