package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserOnlineLogService;
import com.stats.bo.UserOnlineStats;
import com.stats.service.UserOnlineStatsService;

/**
 * 手动采集数据--在线人数统计
 * @author Administrator
 *
 */
public class ReUserOnlineStatsCollector {

	public void execute(String dateStr) {
		LogSystem.info("手动--在线人数统计Collector开始");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		UserOnlineLogService userOnlineLogService = ServiceCacheFactory.getServiceCache().getService(UserOnlineLogService.class);
		//找到前一天的日志
//		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		String[] dates = DateUtil.getBeforeDateByDate(dateTemp, 0);
		
		UserOnlineStats userOnlineStats = userOnlineLogService.findOnlineAmountInSomeTime(dates);
		userOnlineStats.setSysNum(CustomerContextHolder.getSystemNum());
//		userOnlineStats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		userOnlineStats.setDate(DateUtil.getSomeDaysDiffDateByDate(dateTemp, 0));
		
		UserOnlineStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserOnlineStatsService.class);
		// 先删除
		statsService.delete(dateStr);
		
		statsService.save(userOnlineStats);
		
		LogSystem.info("手动--结束在线人数统计Collector完毕");
	}
}
