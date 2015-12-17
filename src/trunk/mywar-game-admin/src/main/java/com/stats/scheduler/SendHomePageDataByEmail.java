package com.stats.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.framework.scheduler.SchedulerEntry;
import com.tool.email.MailUtil;
public class SendHomePageDataByEmail extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		MailUtil mailUtil = new MailUtil("staff.easou.com", "allen_liu@staff.easou.com",
				"后台首页数据", "allen_liu@staff.easou.com", "easou8888");
		mailUtil.homePageinit();
	}

}
