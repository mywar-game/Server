package com.fantingame.game.mywar.logic.boss.schduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.scheduler.SchedulerEntry;
import com.fantingame.game.mywar.logic.boss.service.BossService;

public class BossBornScheduler extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		BossService bossService = ServiceCacheFactory.getServiceCache().getService(BossService.class);
		LogSystem.info("准备诞生世界Boss：time:" + System.currentTimeMillis());
		bossService.bossBorn();
		LogSystem.info("世界Boss已经出世，哈哈哈......兽人们你们颤抖吧：time:" + System.currentTimeMillis());
	}

}
