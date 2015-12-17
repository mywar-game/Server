package com.fantingame.game.mywar.logic.scene.schduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.scheduler.SchedulerEntry;
import com.fantingame.game.mywar.logic.scene.service.SceneService;

public class CheckSceneAdd extends SchedulerEntry{
	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		SceneService sceneService = ServiceCacheFactory.getServiceCache().getService(SceneService.class);
		sceneService.checkSceneNeedAddNewLine();
	}
}
