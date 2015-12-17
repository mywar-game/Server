package com.stats.collector;

import java.util.Date;

public class UserFriendVisitStatsCollector implements Collector {

	@Override
	public void execute(Date date) throws Exception {
//		LogSystem.info("好友拜访统计 Collector开始");
//		//记录昨天的活跃玩家数和截止到昨天的总注册玩家数
//		UserFirendLogService userFirendLogService = ServiceCacheFactory.getServiceCache().getService(UserFirendLogService.class);
//		UserFriendVisitStats userFriendVisitStats = new UserFriendVisitStats();
//		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
//		userFirendLogService.findUserFriendVisitStats(dates, userFriendVisitStats);
//		
//		
//		userFriendVisitStats.setSysNum(CustomerContextHolder.getSystemNum());
//		userFriendVisitStats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
//		
//		UserFriendVisitStatsService userFriendVisitStatsService = ServiceCacheFactory.getServiceCache().getService(UserFriendVisitStatsService.class);
//		userFriendVisitStatsService.save(userFriendVisitStats);
//		
//		LogSystem.info("好友拜访统计 Collector完毕");

	}

}
