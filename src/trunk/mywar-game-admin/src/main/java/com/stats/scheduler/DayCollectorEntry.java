package com.stats.scheduler;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.framework.log.LogSystem;
import com.framework.scheduler.SchedulerEntry;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.collector.InvocationCollector;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class DayCollectorEntry extends SchedulerEntry {
	/**
	 * 采集数据调度入口
	 */
	@Override
	public void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		LogSystem.info("日统计开始 "+jobExecutionContext.getJobDetail().getName());
		
//		CronTrigger cronTrigger= (CronTrigger)jobExecutionContext.getTrigger();
//		String[] serverIdsArr = ((String)cronTrigger.getJobDataMap().get("serverIds")).split(","); // 已经修正
		Map<Integer, TGameServer> gameServerMap = DataSourceManager.getInstatnce().getGameServerMap();
		
		StringBuilder serverName = new StringBuilder();

		Iterator<Integer> iter = gameServerMap.keySet().iterator();
		while (iter.hasNext()) {
			Integer key = iter.next();
			TGameServer value = gameServerMap.get(key);
			LogSystem.info("print key = " + key + " value = " + value);
			serverName.append(value.getServerId() + ",");
		}
		String[] serverIdsArr = serverName.toString().split(",");
		
		Date date = new Date();
		for (int i = 0; i < serverIdsArr.length; i++) {
			CustomerContextHolder.setSystemNum(Integer.valueOf(serverIdsArr[i]));
			LogSystem.info("serverId:"+serverIdsArr[i]);
//			LogSystem.info("cronTrigger.timezone:"+cronTrigger.getTimeZone().getID());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			//开服过后一天才统计数据(已经修正)
			Date serverOpernTime = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum()).getServerOpernTime();
//			Date d = new Date(serverOpernTime.getTime()+24*60*60*1000);
			Date d = new Date(serverOpernTime.getTime());
			if (nowServerTime.getTime() < d.getTime()) {
				LogSystem.info("还木有开服，不统计!");
				continue;
			}
//			InvocationCollector invocationCollector = (InvocationCollector) ServiceCacheFactory.getServiceCache().getBeanById("dayInvocationCollector");
//			invocationCollector.invoke(date);
			executeOneServer(date,Integer.valueOf(serverIdsArr[i]));
			LogSystem.info("日统计完毕 "+jobExecutionContext.getJobDetail().getName());
		}
	}
	
	/**
	 * 执行一个服务器的调度
	 */
	public void executeOneServer(Date date,Integer sysNum){
		ServerTask task = new ServerTask(date,sysNum);
		ThreadPoolTaskExecutor executer = (ThreadPoolTaskExecutor) ServiceCacheFactory.getServiceCache().getBeanById("dayTaskExecutor");
		executer.execute(task);
	}
	
	class ServerTask implements Runnable{
		private Date date;
		private Integer sysNum;
		public ServerTask(Date date,Integer sysNum) {
			super();
			this.date=date;
			this.sysNum=sysNum;
		}

		@Override
		public void run() {
			LogSystem.info("服务器 "+sysNum+"的日统计线程开始-----------------------------------");
			CustomerContextHolder.setSystemNum(sysNum);
			InvocationCollector invocationCollector = (InvocationCollector) ServiceCacheFactory.getServiceCache().getBeanById("dayInvocationCollector");
			invocationCollector.invoke(date);
			LogSystem.info("服务器 "+sysNum+"的日统计线程结束-----------------------------------");
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Integer getSysNum() {
			return sysNum;
		}

		public void setSysNum(Integer sysNum) {
			this.sysNum = sysNum;
		}
		
	}

}
