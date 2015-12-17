package com.stats.scheduler;

import org.quartz.CronTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.scheduler.SchedulerEntry;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserActionLogService;
import com.log.service.UserGoldLogService;
import com.log.service.UserLoginLogService;
import com.log.service.UserLogoutLogService;
import com.log.service.UserMallLogService;

/**
 * 备份定时器 每天零点10分运行
 * @author Administrator
 *
 */
public class BackupScheduler extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		LogSystem.info("备份日志开始 "+jobExecutionContext.getJobDetail().getName());
		UserActionLogService userActionLogService = ServiceCacheFactory.getServiceCache().getService(UserActionLogService.class);
		UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		UserLogoutLogService userLogoutLogService = ServiceCacheFactory.getServiceCache().getService(UserLogoutLogService.class);
		UserMallLogService userMallLogService = ServiceCacheFactory.getServiceCache().getService(UserMallLogService.class);
		CronTrigger cronTrigger= (CronTrigger)jobExecutionContext.getTrigger();
		String[] serverIdsArr = ((String)cronTrigger.getJobDataMap().get("serverIds")).split(",");
		for (int i = 0; i < serverIdsArr.length; i++) {
			CustomerContextHolder.setSystemNum(Integer.valueOf(serverIdsArr[i]));
			String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.TWO_DAYS_AGO);
			userActionLogService.backup(dates[1]);//备份玩家节点日志
			userGoldLogService.backup(dates[1]);//备份玩家金币日志
			userLoginLogService.backup(dates[1]);//备份玩家登入日志
			userLogoutLogService.backup(dates[1]);//备份玩家登出日志
			userMallLogService.backup(dates[1]);//备份玩家商城购买日志
		}
		LogSystem.info("备份日志结束 "+jobExecutionContext.getJobDetail().getName());
	}

}
