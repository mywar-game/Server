package com.stats.collector;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserResourceLogService;
import com.stats.bo.PrestigeStats;
import com.stats.service.PrestigeService;

/**
 * 声望兑换采集
 * @author Administrator
 *
 */
public class PrestigeCollector implements Collector {

	@Override
	public void execute(Date date){
		LogSystem.info("声望兑换采集统计Collector开始");
		
		UserResourceLogService userResourceLogService = ServiceCacheFactory.getServiceCache().getService(UserResourceLogService.class);
		PrestigeService pretigeService = ServiceCacheFactory.getServiceCache().getService(PrestigeService.class);
		List<Object> list = userResourceLogService.findPrestige();
		Map<String, Integer> userMap = new HashMap<String, Integer>();
		Map<String, Integer> toolMap = new HashMap<String, Integer>();
		
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			String userId = (String) obj[0];
			Integer toolId = (Integer) obj[1];
			Integer toolType = (Integer) obj[2];
			
			if (userMap.get(toolType + "_" + toolId + "_" + userId) == null) {
				userMap.put(toolType + "_" + toolId + "_" + userId, 1);
			} else {
				//userMap.put(toolType + "_" + toolId + "_" + userId, userMap.get(toolType + "_" + toolId + "_" + userId) + 1); // +1
			}
			
			if (toolMap.get(toolType + "_" + toolId) == null) {
				toolMap.put(toolType + "_" + toolId, 1);
			} else {
				toolMap.put(toolType + "_" + toolId, toolMap.get(toolType + "_" + toolId) + 1); // +1
			}
		}
		StringBuilder toolSb = new StringBuilder();
		Iterator<String> toolIterator = toolMap.keySet().iterator();
		int toolIndex = 0;
		while (toolIterator.hasNext()) {
			String key = toolIterator.next();
			Integer value = toolMap.get(key);
			if (toolIndex != 0) {
				toolSb.append(",");
			}
			toolSb.append(key + "_" + value);
			toolIndex ++;
		}
		
		
		Map<String, Integer> userCountMap = new HashMap<String, Integer>();
		Iterator<String> userIterator = userMap.keySet().iterator();
		int userIndex = 0;
		while (userIterator.hasNext()) {
			String key = userIterator.next();
			String[] keyArr = key.split("_");
			if (userCountMap.get(keyArr[0] + "_" + keyArr[1]) == null) {
				userCountMap.put(keyArr[0] + "_" + keyArr[1], 1);
			} else {
				userCountMap.put(keyArr[0] + "_" + keyArr[1], userCountMap.get(keyArr[0] + "_" + keyArr[1]) + 1);
			}
		}
		
		Iterator<String> userCountIterator = userCountMap.keySet().iterator();
		StringBuilder userCountSb = new StringBuilder();
		while (userCountIterator.hasNext()) {
			String key = userCountIterator.next();
			Integer value = userCountMap.get(key);
			if (userIndex != 0) {
				userCountSb.append(",");
			}
			userCountSb.append(key + "_" + value);
			userIndex ++;
		}
		PrestigeStats stats = new PrestigeStats();
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setTime(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		stats.setToolDesc(toolSb.toString());
		stats.setUserDesc(userCountSb.toString());
		
		pretigeService.save(stats);
		LogSystem.info("声望兑换采集统计Collector结束");
	}
}
