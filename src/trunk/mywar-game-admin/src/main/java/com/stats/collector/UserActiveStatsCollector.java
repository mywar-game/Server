package com.stats.collector;

import java.util.Date;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLoginLogService;
import com.log.service.UserRegLogService;
import com.stats.bo.UserActiveStats;
import com.stats.service.UserActiveStatsService;


/**
 * 用户活跃统计
 * @author hzy
 * 2012-4-13
 */
public class UserActiveStatsCollector implements Collector {

	@Override
	public void execute(Date date) {
		LogSystem.info("用户活跃统计Collector开始");
		//记录昨天的活跃玩家数和截止到昨天的总注册玩家数
		UserActiveStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserActiveStatsService.class);
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
		
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		int regAmount = userRegLogService.findTotalRegUserNumBeforeDate(dates[1]);
		
		UserActiveStats stats = new UserActiveStats();
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		stats.setActiveUserAmount(userLoginLogService.findNowActiveUserAmount());
		stats.setRegAmount(regAmount);
		statsService.save(stats);
		
		LogSystem.info("用户活跃统计Collector完毕");

	}
	
}