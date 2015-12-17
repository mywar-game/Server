package com.stats.collector;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.dataconfig.service.UserPayService;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.PayIntervalStats;
import com.stats.service.PayIntervalStatsService;

public class PayIntervalStatsCollector implements Collector {

	@Override
	public void execute(Date date) throws Exception {
		LogSystem.info("充值区间统计Collector开始");
		PayIntervalStatsService statsService = ServiceCacheFactory.getServiceCache().getService(PayIntervalStatsService.class);
		statsService.deleteBeforeStatsData();
		
		//key:充值区间索引，value：人数
		Map<Long, Integer> statsMap = new LinkedHashMap<Long, Integer>();
		UserPayService userPayService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		//所有玩家的充值数
		Map<String, String> allUserPayAmountMap = userPayService.getEveryUserPayAmount("ASC");
		if (allUserPayAmountMap != null && allUserPayAmountMap.size() > 0) {
			Set<Entry<String, String>> entrySet = allUserPayAmountMap.entrySet();
			//充值数
			long payAmount;
			//充值区间索引
			long payIntervalIndex;
			for (Entry<String, String> entry : entrySet) {
				payAmount = Integer.valueOf(entry.getValue().split("_")[1]);
				payIntervalIndex = payAmount/100;
				//如果当前区间没人，设为0；否则的话当前区间人数加一
				if (statsMap.get(payIntervalIndex) == null) {
					statsMap.put(payIntervalIndex, 1);
				} else {
					statsMap.put(payIntervalIndex, statsMap.get(payIntervalIndex)+1);
				}
			}
		}
		//保存到数据库
		if (statsMap != null && statsMap.size() > 0) {
			Set<Entry<Long, Integer>> entrySet = statsMap.entrySet();
			//充值区间索引
			long payIntervalIndex;
			PayIntervalStats payIntervalStats = new PayIntervalStats();
			payIntervalStats.setSysNum(CustomerContextHolder.getSystemNum());
			for (Entry<Long, Integer> entry : entrySet) {
				payIntervalIndex = entry.getKey();
				String key = payIntervalIndex*100+"-"+((payIntervalIndex + 1)*100 -1);
				
				payIntervalStats.setPayInterval(key);
				payIntervalStats.setUserNum(entry.getValue());
				statsService.save(payIntervalStats);
			}
		}
		
		LogSystem.info("充值区间统计Collector完毕");
	}

}
