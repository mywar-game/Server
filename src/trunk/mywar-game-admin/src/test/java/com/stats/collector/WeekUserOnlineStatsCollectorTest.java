package com.stats.collector;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class WeekUserOnlineStatsCollectorTest extends SessionTest {
	public void testExecute() throws Exception {
		WeekUserOnlineStatsCollector collector = ServiceCacheFactory.getServiceCache().getService(WeekUserOnlineStatsCollector.class);
		collector.execute(null);
	}
}
