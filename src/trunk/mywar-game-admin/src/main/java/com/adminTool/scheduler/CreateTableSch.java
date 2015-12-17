package com.adminTool.scheduler;

import org.quartz.CronTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.framework.log.LogSystem;
import com.framework.scheduler.SchedulerEntry;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.BattleLogService;
import com.log.service.UserEquipmentLogService;
import com.log.service.UserHeroLogService;
import com.log.service.UserResourceLogService;
import com.log.service.UserTreasureLogService;

public class CreateTableSch extends SchedulerEntry {
	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		LogSystem.info("创建备份日志库开始 "+jobExecutionContext.getJobDetail().getName());
		UserResourceLogService userResourceLogService = ServiceCacheFactory.getServiceCache().getService(UserResourceLogService.class);
		BattleLogService battleLogService = ServiceCacheFactory.getServiceCache().getService(BattleLogService.class);
		UserEquipmentLogService userEquipmentLogService = ServiceCacheFactory.getServiceCache().getService(UserEquipmentLogService.class);
		UserHeroLogService userHeroLogService = ServiceCacheFactory.getServiceCache().getService(UserHeroLogService.class);
		UserTreasureLogService userTreasureLogService = ServiceCacheFactory.getServiceCache().getService(UserTreasureLogService.class);
		CronTrigger cronTrigger= (CronTrigger)jobExecutionContext.getTrigger();
		String[] serverIdsArr = ((String)cronTrigger.getJobDataMap().get("serverIds")).split(",");
		for (int i = 0; i < serverIdsArr.length; i++) {
			CustomerContextHolder.setSystemNum(Integer.valueOf(serverIdsArr[i]));
			String timeStamp = DateUtil.dateToString(DateUtil.getSomeDaysDiffDate(1), DateUtil.FORMAT_FOUR);
			userResourceLogService.createTable(timeStamp);
			battleLogService.createTable(timeStamp);
			userEquipmentLogService.createTable(timeStamp);
			userHeroLogService.createTable(timeStamp);
			userTreasureLogService.createTable(timeStamp);
		}
		LogSystem.info("创建备份日志库结束 "+jobExecutionContext.getJobDetail().getName());
	}

}
