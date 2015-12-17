package com.stats.recollector;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.constant.UserGoldLogCategory;
import com.log.service.UserGoldLogService;
import com.stats.bo.UserConsumeStats;
import com.stats.service.UserConsumeStatsService;

public class ReUserConsumeStatsCollector {

	// TODO 需要修改日期即可
	public void execute(String dateStr) {
		LogSystem.info("玩家消费统计  Collector开始");
		//记录昨天的消费总额
//		UserTypeService userTypeService = ServiceCacheFactory.getServiceCache().getService(UserTypeService.class);
//		String testUserIds = userTypeService.findUserIdsBySysNum(CustomerContextHolder.getSystemNum());
		
		UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		Map<String, Integer> userIdAndCategoryAmountMap = userGoldLogService.findUserIdAndCategoryAmountMap(UserGoldLogCategory.CONSUME, dates);
		Iterator<Entry<String, Integer>> ite = userIdAndCategoryAmountMap.entrySet().iterator();
		int totalAmount = 0;
		int testAmount = 0;
		while (ite.hasNext()) {
			Entry<String, Integer> entry = ite.next();
//			long userId = entry.getKey();
			int amount = entry.getValue();
			//如果是测试号
//			if (testUserIds.indexOf(userId+"") != -1) {
//				testAmount += amount;
//			}
			totalAmount += amount;
		}
		
		UserConsumeStats userConsumeStats = new UserConsumeStats();
		userConsumeStats.setSysNum(CustomerContextHolder.getSystemNum());
		userConsumeStats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		userConsumeStats.setConsumeAmount(totalAmount);
		userConsumeStats.setTestConsumeAmount(testAmount);
		
		UserConsumeStatsService userConsumeStatsService = ServiceCacheFactory.getServiceCache().getService(UserConsumeStatsService.class);
		userConsumeStatsService.save(userConsumeStats);
		
		LogSystem.info("玩家消费统计  Collector完毕");
	}
}
