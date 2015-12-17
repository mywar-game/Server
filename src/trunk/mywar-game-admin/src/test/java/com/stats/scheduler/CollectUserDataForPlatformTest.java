package com.stats.scheduler;

import org.quartz.JobExecutionException;

import com.adminTool.scheduler.CollectUserDataForPlatform;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class CollectUserDataForPlatformTest  extends SessionTest {
	public void testExecute() throws JobExecutionException {
		CollectUserDataForPlatform xxx = ServiceCacheFactory.getServiceCache().getService(CollectUserDataForPlatform.class);
		xxx.executeJob(null);
	}
}
