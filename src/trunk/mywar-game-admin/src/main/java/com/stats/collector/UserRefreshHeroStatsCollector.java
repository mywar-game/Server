package com.stats.collector;

import java.util.Date;

import com.framework.log.LogSystem;

public class UserRefreshHeroStatsCollector implements Collector {

	@Override
	public void execute(Date date) throws Exception {
		LogSystem.info("玩家刷新英雄统计  Collector开始");
//		
//		UserRefreshHeroLogService userRefreshHeroLogService = ServiceCacheFactory.getServiceCache().getService(UserRefreshHeroLogService.class);
//		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
//		//刷新英雄数据
//		UserRefreshHeroStats userRefreshHeroStats = new UserRefreshHeroStats();
//		userRefreshHeroLogService.collectUserRefreshHeroStatsDataInSomeDay(dates, userRefreshHeroStats);
//		//招募英雄数据
//		UserHeroLogService userHeroLogService = ServiceCacheFactory.getServiceCache().getService(UserHeroLogService.class);
//		userRefreshHeroStats.setRecruitHeroNum(userHeroLogService.findRecruitHeroNumInSomeDay(dates));
//
//		userRefreshHeroStats.setSysNum(CustomerContextHolder.getSystemNum());
//		userRefreshHeroStats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
//		
//		UserRefreshHeroStatsService userRefreshHeroStatsService = ServiceCacheFactory.getServiceCache().getService(UserRefreshHeroStatsService.class);
//		userRefreshHeroStatsService.save(userRefreshHeroStats);
//		
//		LogSystem.info("玩家刷新英雄统计  Collector完毕");
	}

}
