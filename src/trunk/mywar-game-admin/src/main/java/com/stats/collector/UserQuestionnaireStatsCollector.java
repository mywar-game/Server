package com.stats.collector;

import java.util.Date;
import com.framework.log.LogSystem;

public class UserQuestionnaireStatsCollector implements Collector {

	@Override
	public void execute(Date date) throws Exception {
		LogSystem.info("玩家问卷统计  Collector开始");
//		UserQuestionnaireLogService logService = ServiceCacheFactory.getServiceCache().getService(UserQuestionnaireLogService.class);
//		List<UserQuestionnaireStats> statsList = logService.collectData();
//		UserQuestionnaireStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserQuestionnaireStatsService.class);
//		statsService.save(statsList);
		LogSystem.info("玩家问卷统计  Collector完毕");
	}

}
