package com.fantingame.game.log.scheduler;

import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.scheduler.SchedulerEntry;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.log.service.LogService;
/**
 * 建日志库后台线程
 * @author Administrator
 *
 */
public class CreatLogTableScheduler extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		LogService logService = ServiceCacheFactory.getServiceCache().getService(LogService.class);
		try {
			Date now = new Date();
			Date tommorrow = DateUtils.add(now, Calendar.DATE, 1);
			LogSystem.debug("建立明天的日志数据库,今日日期"+now+",明日日期"+tommorrow);
			logService.creatLogTable(tommorrow);
		} catch (Exception e) {
			LogSystem.error(e, "");
		}
	}
	
	@Override
	public void startup() {
		//启动的时候就建立当日 及明日的数据库
		LogService logService = ServiceCacheFactory.getServiceCache().getService(LogService.class);
		try {
			Date now = new Date();
			logService.creatLogTable(now);
			Date tommorrow = DateUtils.add(now, Calendar.DATE, 1);
			logService.creatLogTable(tommorrow);
		} catch (Exception e) {
			LogSystem.error(e,"");
		}
		super.startup();
	}
	

}
