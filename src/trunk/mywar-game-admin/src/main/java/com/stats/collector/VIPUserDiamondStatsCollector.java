package com.stats.collector;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.adminTool.service.UserService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.constant.UserGoldLogCategory;
import com.log.service.UserGoldLogService;
import com.stats.bo.VipUserDiamondStats;
import com.stats.service.VipUserDiamondStatsService;

public class VIPUserDiamondStatsCollector implements Collector {

	@Override
	public void execute(Date date) throws Exception {
		LogSystem.info("VIP 玩家元宝统计 Collector开始");
		UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		VipUserDiamondStatsService vipUserDiamondStatsService = ServiceCacheFactory.getServiceCache().getService(VipUserDiamondStatsService.class);
				
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		List<Object> tempList = userService.findVipUser();
		List<String> userList = new ArrayList<String>();
		for (int i = 0; i < tempList.size(); i++) {
			Object objArr = (Object) tempList.get(i);
			Character[] cc = (Character[]) objArr;
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < cc.length; j++) {
				Character c = cc[j];
				sb.append(c);
			}
			userList.add(sb.toString());
		}
		int actityCount = userService.findActityVipUser(dates, userList);
		Map<Integer, Integer> map1 = userGoldLogService.findEveryTypeAmountVIP(UserGoldLogCategory.RECEIVE, dates, userList);
		Map<Integer, Integer> map2 = userGoldLogService.findEveryTypeAmountVIP(UserGoldLogCategory.CONSUME, dates, userList);
		List<VipUserDiamondStats> resultList = new ArrayList<VipUserDiamondStats>();
		
		Iterator<Integer> key1 = map1.keySet().iterator();
		while (key1.hasNext()) {
			Integer k = key1.next();
			Integer value = map1.get(k);
			VipUserDiamondStats stats = new VipUserDiamondStats();
			stats.setSysNum(CustomerContextHolder.getSystemNum());
			stats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
			stats.setCount(tempList.size());
			stats.setType(k);
			stats.setCaterory(UserGoldLogCategory.RECEIVE);
			stats.setDiamond(value);
			stats.setActityCount(actityCount);
			
			resultList.add(stats);
		}
		Iterator<Integer> key2 = map2.keySet().iterator();
		while (key2.hasNext()) {
			Integer k = key2.next();
			Integer value = map2.get(k);
			VipUserDiamondStats stats = new VipUserDiamondStats();
			stats.setSysNum(CustomerContextHolder.getSystemNum());
			stats.setDate(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
			stats.setCount(tempList.size());
			stats.setType(k);
			stats.setDiamond(value);
			stats.setCaterory(UserGoldLogCategory.CONSUME);
			stats.setActityCount(actityCount);
			
			resultList.add(stats);
		}
		vipUserDiamondStatsService.saveBatch(resultList);
		LogSystem.info("VIP 玩家元宝统计 Collector结束");
	}
}
