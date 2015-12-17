package com.adminTool.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.adminTool.service.GlobalService;
import com.framework.log.LogSystem;
import com.framework.scheduler.SchedulerEntry;
import com.framework.servicemanager.ServiceCacheFactory;

public class DealGlobalInfo extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		LogSystem.info("DealGlobalInfo开始");
		GlobalService globalService = ServiceCacheFactory.getServiceCache().getService(GlobalService.class);
		globalService.dealGlobalInfo();
		LogSystem.info("DealGlobalInfo完毕");
	}

}
