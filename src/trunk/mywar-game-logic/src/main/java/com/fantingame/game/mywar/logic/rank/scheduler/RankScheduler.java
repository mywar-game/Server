package com.fantingame.game.mywar.logic.rank.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.scheduler.SchedulerEntry;
import com.fantingame.game.mywar.logic.rank.service.RankService;

public class RankScheduler extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		RankService rankService = ServiceCacheFactory.getServiceCache().getService(RankService.class);
		LogSystem.info("又开始排名更新了" + System.currentTimeMillis());
		rankService.updateUserRank();
		LogSystem.info("排行榜更新结束" + System.currentTimeMillis());
	}

}
