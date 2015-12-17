package com.stats.collector;

import java.util.Date;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserOnlineLogService;
import com.stats.bo.UserOnlineStats;
import com.stats.service.UserOnlineStatsService;


/**
 * 在线人数统计Collector
 * @author hzy
 * 2012-4-17
 */
public class UserOnlineStatsCollector implements Collector {

	@Override
	public void execute(Date date) {
		LogSystem.info("在线人数统计Collector开始");
		
		UserOnlineLogService userOnlineLogService = ServiceCacheFactory.getServiceCache().getService(UserOnlineLogService.class);
		//找到前一天的日志
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		UserOnlineStats userOnlineStats = userOnlineLogService.findOnlineAmountInSomeTime(dates);
		userOnlineStats.setSysNum(CustomerContextHolder.getSystemNum());
		userOnlineStats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		
		UserOnlineStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserOnlineStatsService.class);
		statsService.save(userOnlineStats);
		
		LogSystem.info("在线人数统计Collector完毕");
	}
}
