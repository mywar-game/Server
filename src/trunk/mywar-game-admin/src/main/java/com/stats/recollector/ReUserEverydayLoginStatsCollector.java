package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLoginLogService;
import com.stats.bo.UserEverydayLoginStats;
import com.stats.service.UserEverydayLoginStatsService;

/**
 * 手动--玩家每天登入统计
 * @author Administrator
 *
 */
public class ReUserEverydayLoginStatsCollector {

	// 需要修改日期
	public void execute(String dateStr) {
		LogSystem.info("手动--玩家每天登入统计 Collector开始");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		UserEverydayLoginStatsService userEverydayLoginStatsService = ServiceCacheFactory.getServiceCache().getService(UserEverydayLoginStatsService.class);
		
		//找到前一天的日志
		/*String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);*/
		String[] dates = DateUtil.getBeforeDateByDate(dateTemp, 0);
		
		//System.out.println("list.size() "+list.size());
		UserEverydayLoginStats userEverydayLoginStats = userLoginLogService.findCounts(dates);
		userEverydayLoginStats.setSysNum(CustomerContextHolder.getSystemNum());
//		userEverydayLoginStats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		userEverydayLoginStats.setDate(DateUtil.getDiffDate(dateTemp, 0));
		
		// 先删除
		userEverydayLoginStatsService.delete(dateStr);
		
		userEverydayLoginStatsService.save(userEverydayLoginStats);
		
		LogSystem.info("手动--玩家每天登入统计 Collector完毕");
	}
}
