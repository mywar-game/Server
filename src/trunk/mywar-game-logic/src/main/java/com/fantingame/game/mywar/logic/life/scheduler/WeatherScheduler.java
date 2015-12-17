package com.fantingame.game.mywar.logic.life.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.scheduler.SchedulerEntry;
import com.fantingame.game.mywar.logic.life.service.LifeService;

public class WeatherScheduler extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		LifeService lifeService = ServiceCacheFactory.getServiceCache().getService(LifeService.class);
		LogSystem.info("诸葛亮开始召唤东风...." + System.currentTimeMillis());
		lifeService.updateWeather();
		LogSystem.info("诸葛亮开始召唤东风...." + System.currentTimeMillis());
	}

}
