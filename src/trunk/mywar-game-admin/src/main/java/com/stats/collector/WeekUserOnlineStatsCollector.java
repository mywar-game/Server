package com.stats.collector;

import java.util.Date;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserOnlineLogService;
import com.stats.bo.UserOnlineStats;
import com.stats.bo.WeekUserOnlineStats;
import com.stats.service.WeekUserOnlineStatsService;

public class WeekUserOnlineStatsCollector implements Collector {

	@Override
	public void execute(Date date) throws Exception {
		LogSystem.info("每周在线人数统计  Collector开始");
		
		WeekUserOnlineStatsService weekUserOnlineStatsService = ServiceCacheFactory.getServiceCache().getService(WeekUserOnlineStatsService.class);
		UserOnlineLogService userOnlineLogService = ServiceCacheFactory.getServiceCache().getService(UserOnlineLogService.class);
		
		// 当前一个日期的上个礼拜
		String startTime = DateUtil.getOneDayStrArr(SystemStatsDate.LASTWEEK_YESTERDAY)[0]; 
		// 当前日期
		String endTime = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY)[1]; 
		String[] dates = {startTime, endTime};
		
		UserOnlineStats userOnlineStats = userOnlineLogService.findOnlineAmountInSomeTime(dates);
		
		WeekUserOnlineStats weekUserOnlineStats = new WeekUserOnlineStats();
		weekUserOnlineStats.setOnlineAmount(userOnlineStats.getOnlineAmount());
		weekUserOnlineStats.setOnlinePeak(userOnlineStats.getOnlinePeak());
		weekUserOnlineStats.setPayPlayerOnlinePeak(userOnlineStats.getPayPlayerOnlinePeak());
		weekUserOnlineStats.setSysNum(CustomerContextHolder.getSystemNum());
		weekUserOnlineStats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		
		weekUserOnlineStatsService.save(weekUserOnlineStats);
		
		LogSystem.info("每周在线人数统计  Collector完毕");
	}

}
