package com.fantingame.game.mywar.logic.pk.schduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.scheduler.SchedulerEntry;
import com.fantingame.game.mywar.logic.pk.service.PkService;

/**
 * 21点开始方法竞技场排名奖励
 * 
 * @author yezp
 */
public class RankRewardScheduler extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		PkService pkService = ServiceCacheFactory.getServiceCache().getService(PkService.class);
		LogSystem.info("开始发放竞技场排名奖励：time:" + System.currentTimeMillis());
		pkService.sendRankReward();
		LogSystem.info("发放竞技场排名奖励完毕：time:" + System.currentTimeMillis());
	}

}
