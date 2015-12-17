package com.fantingame.game.mywar.logic.mall.schduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.scheduler.SchedulerEntry;
import com.fantingame.game.mywar.logic.mall.service.MallService;

public class RefreshMysteriousMallScheduler extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		MallService mallService = ServiceCacheFactory.getServiceCache().getService(MallService.class);
		LogSystem.info("开始刷新神秘商店：time:" + System.currentTimeMillis());
		
		mallService.refreshMysteriousMall();
		LogSystem.info("刷新神秘商店完毕：time:" + System.currentTimeMillis());
	}

}
