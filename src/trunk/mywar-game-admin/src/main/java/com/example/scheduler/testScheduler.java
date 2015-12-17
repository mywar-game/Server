package com.example.scheduler;



import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.framework.scheduler.SchedulerEntry;

public class testScheduler extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		// TODO Auto-generated method stub
//         System.out.println(new Date());
//         try {
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
