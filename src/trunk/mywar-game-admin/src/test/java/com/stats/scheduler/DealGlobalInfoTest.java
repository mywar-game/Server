package com.stats.scheduler;

import org.quartz.JobExecutionException;

import com.adminTool.scheduler.DealGlobalInfo;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class DealGlobalInfoTest  extends SessionTest {
	public void testExecute() throws JobExecutionException {
		DealGlobalInfo xxx = ServiceCacheFactory.getServiceCache().getService(DealGlobalInfo.class);
		xxx.executeJob(null);
	}
}
