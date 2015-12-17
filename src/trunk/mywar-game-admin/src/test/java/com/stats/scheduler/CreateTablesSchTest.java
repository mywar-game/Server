package com.stats.scheduler;

import org.quartz.JobExecutionException;

import com.adminTool.scheduler.CreateTableSch;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.testcore.SessionTest;

public class CreateTablesSchTest extends SessionTest {
	public void testExecute() throws JobExecutionException {
		CreateTableSch puss = ServiceCacheFactory.getServiceCache()
		.getService(CreateTableSch.class);
		puss.executeJob(null);
	}
}
