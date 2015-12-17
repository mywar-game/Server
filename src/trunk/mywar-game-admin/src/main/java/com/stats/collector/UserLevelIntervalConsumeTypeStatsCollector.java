package com.stats.collector;

import java.util.Date;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserGoldLogService;
import com.stats.bo.UserLevelIntervalConsumeTypeStats;
import com.stats.service.UserLevelIntervalConsumeTypeStatsService;

public class UserLevelIntervalConsumeTypeStatsCollector implements Collector {

	@Override
	public void execute(Date date) {
		LogSystem.info("玩家等级区间各类型消费统计Collector开始");
		UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
		UserLevelIntervalConsumeTypeStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserLevelIntervalConsumeTypeStatsService.class);
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		
		UserLevelIntervalConsumeTypeStats stats = new UserLevelIntervalConsumeTypeStats();
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		stats.setConsumeInfo(userGoldLogService.findUserLevelIntervalConsumeAmountInYesterday(false, dates));
		stats.setTestUserConsumeInfo("");
		statsService.save(stats);
		LogSystem.info("玩家等级区间各类型消费统计Collector完毕");
	}
	
}
