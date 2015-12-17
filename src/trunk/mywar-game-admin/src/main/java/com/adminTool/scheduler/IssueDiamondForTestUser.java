package com.adminTool.scheduler;

import org.quartz.CronTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.adminTool.service.UserTypeService;
import com.framework.log.LogSystem;
import com.framework.scheduler.SchedulerEntry;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;

public class IssueDiamondForTestUser extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		LogSystem.info("定时发放钻石给升到整十级的测试号开始");
		
		UserTypeService userTypeService = ServiceCacheFactory.getServiceCache().getService(UserTypeService.class);
		
		CronTrigger cronTrigger= (CronTrigger)jobExecutionContext.getTrigger();
		LogSystem.info("cronTrigger.timezone:"+cronTrigger.getTimeZone().getID());
		
		String[] serverIdsArr = ((String)cronTrigger.getJobDataMap().get("serverIds")).split(",");
		for (int i = 0; i < serverIdsArr.length; i++) {
			CustomerContextHolder.setSystemNum(Integer.valueOf(serverIdsArr[i]));
			userTypeService.issueDiamondForTestUser();
		}

		LogSystem.info("定时发放钻石给升到整十级的测试号结束");
	}

}
