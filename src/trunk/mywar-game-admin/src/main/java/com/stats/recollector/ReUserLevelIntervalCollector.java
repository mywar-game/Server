package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserGoldLogService;
import com.stats.bo.UserLevelIntervalConsumeTypeStats;
import com.stats.service.UserLevelIntervalConsumeTypeStatsService;

/**
 * 手动--玩家等级区间类型消费统计
 * @author Administrator
 *
 */
public class ReUserLevelIntervalCollector {

	public void execute(String dateStr) {
		LogSystem.info("手动--玩家等级区间各类型消费统计Collector开始");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
		UserLevelIntervalConsumeTypeStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserLevelIntervalConsumeTypeStatsService.class);
//		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		String[] dates = DateUtil.getBeforeDateByDate(dateTemp, 0);
		
		UserLevelIntervalConsumeTypeStats stats = new UserLevelIntervalConsumeTypeStats();
		stats.setSysNum(CustomerContextHolder.getSystemNum());
//		stats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		stats.setDate(DateUtil.getSomeDaysDiffDateByDate(dateTemp, 0));
		stats.setConsumeInfo(userGoldLogService.findUserLevelIntervalConsumeAmountInYesterday(false, dates));
		stats.setTestUserConsumeInfo("");
		
		// 先删除
		statsService.delete(dateStr);
		
		statsService.save(stats);
		LogSystem.info("手动--玩家等级区间各类型消费统计Collector完毕");
	}
}
