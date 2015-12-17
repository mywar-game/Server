package com.stats.collector;

import java.util.Date;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLoginLogService;
import com.stats.bo.UserEverydayLoginStats;
import com.stats.service.UserEverydayLoginStatsService;

/**
 * 玩家每天登入统计Collector
 * @author hzy
 * 2012-4-10
 */
public class UserEverydayLoginStatsCollector implements Collector {

	@Override
	public void execute(Date date) {
		LogSystem.info("玩家每天登入统计 Collector开始");
		
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		UserEverydayLoginStatsService userEverydayLoginStatsService = ServiceCacheFactory.getServiceCache().getService(UserEverydayLoginStatsService.class);
		//找到前一天的日志
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		//System.out.println("list.size() "+list.size());
		UserEverydayLoginStats userEverydayLoginStats = userLoginLogService.findCounts(dates);
		userEverydayLoginStats.setSysNum(CustomerContextHolder.getSystemNum());
		userEverydayLoginStats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		userEverydayLoginStatsService.save(userEverydayLoginStats);
		
		LogSystem.info("玩家每天登入统计 Collector完毕");
	}
}
