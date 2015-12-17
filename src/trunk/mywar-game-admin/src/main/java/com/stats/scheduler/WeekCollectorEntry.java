/**
 * 
 */
package com.stats.scheduler;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.framework.log.LogSystem;
import com.framework.scheduler.SchedulerEntry;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.collector.InvocationCollector;

/**
 * @author huanglong
 * 
 *         2011-10-26
 */
public class WeekCollectorEntry extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		LogSystem.info("周统计开始"+jobExecutionContext.getJobDetail().getName());
		CronTrigger cronTrigger= (CronTrigger)jobExecutionContext.getTrigger();
		String[] serverIdsArr = ((String)cronTrigger.getJobDataMap().get("serverIds")).split(",");
		Date date = new Date();
		for (int i = 0; i < serverIdsArr.length; i++) {
			CustomerContextHolder.setSystemNum(Integer.valueOf(serverIdsArr[i]));
			LogSystem.info("serverId:"+serverIdsArr[i]);
			LogSystem.info("cronTrigger.timezone:"+cronTrigger.getTimeZone().getID());
			LogSystem.info("管理后台的时间：" + date.toString());
			LogSystem.info("游戏服务器时间：" + DateUtil.getNowDateBySystemNum().toString());
			InvocationCollector invocationCollector = (InvocationCollector) ServiceCacheFactory.getServiceCache().getBeanById("weekInvocationCollector");
			invocationCollector.invoke(date);
			LogSystem.info("周统计完毕 "+jobExecutionContext.getJobDetail().getName());
			LogSystem.info("一共执行完毕" + invocationCollector.getCount() + "类。");
		}
	}

}
