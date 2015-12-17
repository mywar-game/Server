package com.stats.collector;

import java.util.Date;

import com.dataconfig.service.UserPayService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.WeekUserPayStats;
import com.stats.service.WeekUserPayStatsService;

public class WeekUserPayStatsCollector implements Collector {

	@Override
	public void execute(Date date) throws Exception {

		LogSystem.info("每周玩家充值统计  Collector开始");
		
		WeekUserPayStatsService weekUserPayStatsService = ServiceCacheFactory.getServiceCache().getService(WeekUserPayStatsService.class);
		UserPayService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		
		// 当前一个日期的上个礼拜
		String startTime = DateUtil.getOneDayStrArr(SystemStatsDate.LASTWEEK_YESTERDAY)[0]; 
		// 当前日期
		String endTime = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY)[1]; 
		String[] dates = {startTime, endTime};
		Integer payAmount = userPayHistoryLogService.getPayAmount(dates);
		
		WeekUserPayStats weekUserPayStats = new WeekUserPayStats();
		weekUserPayStats.setSysNum(CustomerContextHolder.getSystemNum());
		weekUserPayStats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		weekUserPayStats.setPayAmount(payAmount);
		
		weekUserPayStatsService.save(weekUserPayStats);
		
		LogSystem.info("每周玩家充值统计  Collector完毕");

	}
}