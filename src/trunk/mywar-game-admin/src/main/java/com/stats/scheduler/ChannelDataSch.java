package com.stats.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.framework.scheduler.SchedulerEntry;
import com.stats.thread.StatsThread;
public class ChannelDataSch extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		new StatsThread().start();
	}

}
