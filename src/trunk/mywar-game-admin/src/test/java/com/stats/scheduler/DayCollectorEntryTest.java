package com.stats.scheduler;

import org.quartz.JobExecutionException;

import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class DayCollectorEntryTest  extends SessionTest {
	public void testExecute() throws JobExecutionException {
		DayCollectorEntry puss = ServiceCacheFactory.getServiceCache()
		.getService(DayCollectorEntry.class);
		puss.executeJob(null);
	}
}

