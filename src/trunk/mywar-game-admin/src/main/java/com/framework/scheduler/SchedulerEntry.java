package com.framework.scheduler;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.framework.log.LogSystem;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;
/**
 * 调度实现基类
 * @author mengchao
 *
 */
public abstract class SchedulerEntry implements IScheduler {

	private Scheduler sched;
	
	private String cronExpression;
	
	private boolean isRunning = false;
	
	/** 定时器的是否是时区的区别 */
	private Boolean isTimeZoneDiff;
	
	public void startup() {
		
		LogSystem.info("应用程序启动");
		
		String simpleName = this.getClass().getSimpleName();
		if (cronExpression == null || cronExpression.equals("")) {
			throw new NullPointerException(simpleName + " Parama 'cronExpression' is NULL!please set it!");
		}
		if (isTimeZoneDiff == null) {
			throw new NullPointerException(simpleName + " Parama 'isTimeZoneDiff' is NULL!please set it!");
		}
		LogSystem.info("==============================启动后台线程"+simpleName+",规则"+cronExpression);
		//定点的定时器要添加时区
		if (isTimeZoneDiff) {
			//时区id和 时区拥有的服务器编号集合 的map
			Map<String, String> timeZoneIdAndServerIdsMap = new HashMap<String, String>();
			Map<Integer, TGameServer> gameServerMap = DataSourceManager.getInstatnce().getGameServerMap();
			
			LogSystem.info("打印SchedulerEntry gameServerMap信息开始"); 
			Iterator<Integer> iter = gameServerMap.keySet().iterator();
			while (iter.hasNext()) {
				Integer key = iter.next();
				TGameServer value = gameServerMap.get(key);
				LogSystem.info("key = " + key + " value = " + value);
			}
			LogSystem.info("打印SchedulerEntry gameServerMap信息结束");
			
			Iterator<Entry<Integer, TGameServer>> gameServerIterator = gameServerMap.entrySet().iterator();
			while (gameServerIterator.hasNext()) {
				Entry<Integer, TGameServer> entry = gameServerIterator.next();
				Integer serverId = entry.getKey();
				String timeZoneId = entry.getValue().getTimeZoneId();
				if (timeZoneIdAndServerIdsMap.get(timeZoneId) == null) {
					timeZoneIdAndServerIdsMap.put(timeZoneId, serverId+"");
				} else {
					timeZoneIdAndServerIdsMap.put(timeZoneId, timeZoneIdAndServerIdsMap.get(timeZoneId) + "," + serverId);
				}
			}
			Iterator<Entry<String, String>> ite = timeZoneIdAndServerIdsMap.entrySet().iterator();
			while (ite.hasNext()) {
				Entry<String, String> entry = ite.next();
				String timeZoneId = entry.getKey();
				String serverIds = entry.getValue();
				
				LogSystem.info("打印SchedulerEntry serverIds信息开始");
				LogSystem.info("serverIds = " + serverIds);
				LogSystem.info("打印SchedulerEntry serverIds信息结束");
				
		        JobDetail jobDetail = new JobDetail(simpleName + "job for server(" + serverIds+ ") " + timeZoneId, simpleName + "jobgroup", this.getClass());
		        CronTrigger cronTrigger = new CronTrigger(simpleName + "cron for server(" + serverIds+ ") " + timeZoneId, simpleName + "crongroup");
		        try {
					cronTrigger.setTimeZone(TimeZone.getTimeZone(timeZoneId));
					cronTrigger.getJobDataMap().put("serverIds", serverIds); // 启动应用程序时
					cronTrigger.setCronExpression(cronExpression);
					
				} catch (ParseException e1) {
					LogSystem.error(e1, "cronExpression error!");
				}
				sched = MySchedulerFactory.getSchedulerInstance();
		        try {
					sched.scheduleJob(jobDetail, cronTrigger);
			        sched.start();
			        isRunning = true;
			        LogSystem.info(jobDetail.getName() + " startup!");
				} catch (SchedulerException e) {
					LogSystem.error(e, "QUARTZ Error!");
				}
			}
		} else {
			//每隔多久的定时器不需要区分时区
			JobDetail jobDetail = new JobDetail(simpleName + "job", simpleName + "jobgroup", this.getClass());
			CronTrigger cronTrigger = new CronTrigger(simpleName + "cron", simpleName + "crongroup");
			try {
				cronTrigger.setCronExpression(cronExpression);
			} catch (ParseException e1) {
				LogSystem.error(e1, "cronExpression error!");
			}
			sched = MySchedulerFactory.getSchedulerInstance();
		    try {
				sched.scheduleJob(jobDetail, cronTrigger);
		        sched.start();
		        isRunning = true;
		        LogSystem.info(jobDetail.getName() + " startup!");
			} catch (SchedulerException e) {
				LogSystem.error(e, "QUARTZ Error!");
			}
		}
	}
	
	public void shutdown() {
		if (sched != null && isRunning) {
			try {
				sched.shutdown();
			} catch (SchedulerException e) {
				LogSystem.error(e, "ERROR IN QUARTZ!");
			}
		} else {
			LogSystem.error(new NullPointerException(), "QUARTZ IS NEVER START!CAN NOT BE SHUTDOWN!");
		}
   }

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			executeJob(jobExecutionContext);
		} catch (Exception e) {
			LogSystem.error(e, e.getMessage());
		}
	}
	
	/**
	 * 调度执行的内容 需要具体实现
	 */
	public abstract void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException;

	public String getCronExpression() {
		return cronExpression;
	}
	
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public void setIsTimeZoneDiff(Boolean isTimeZoneDiff) {
		this.isTimeZoneDiff = isTimeZoneDiff;
	}

	public Boolean getIsTimeZoneDiff() {
		return isTimeZoneDiff;
	}
	
}
