package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserLevelupLogService;
import com.stats.bo.LevelStats;
import com.stats.service.UserLevelStatsService;

/**
 * 手动--玩家等级分布统计
 * @author Administrator
 *
 */
public class ReLevelStatsCollector {

	private ReLevelStatsCollector() {}
	private static ReLevelStatsCollector instance;
	
	public synchronized static ReLevelStatsCollector getInstance() {
		if (instance == null) {
			instance = new ReLevelStatsCollector();
		}
		return instance;
	}
	
	public void execute(String dateStr) {
		
		LogSystem.info("手动--渠道等级统计Collector开始");
		
		UserLevelStatsService userLevelStatsService = ServiceCacheFactory.getServiceCache().getService(UserLevelStatsService.class);
		UserLevelupLogService userLevelupLogService = ServiceCacheFactory.getServiceCache().getService(UserLevelupLogService.class);
		
		userLevelStatsService.delete(dateStr); // 先删除某天的数据
		
		// TODO 改成采集当前之前的数据
		List<Object> list = userLevelupLogService.findLevelDataByChannel(); // 等级分布数据, 重新采集
		if (list != null && list.size() > 0) {
			List<LevelStats> result = new ArrayList<LevelStats>();
			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[])list.get(i);
				LevelStats stats = new LevelStats();
				stats.setUserLevel(Integer.valueOf(arr[0].toString()));
				stats.setChannel(arr[1].toString());
				stats.setRegNum(Integer.valueOf(arr[2].toString()));
				stats.setUserNum(Integer.valueOf(arr[3].toString()));
				stats.setPayUserNum(Integer.valueOf(arr[4].toString()));
				stats.setPayAmount(Double.valueOf(arr[5].toString()));
				stats.setPayTimes(Integer.valueOf(arr[6].toString()));
				stats.setBuyToolConsume(Integer.valueOf(arr[7].toString()));
				stats.setSysNum(CustomerContextHolder.getSystemNum());
				// stats.setTime(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
				java.util.Date date = null;
				try {
					date = sdf.parse(dateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}  
				stats.setTime(date);
				result.add(stats);
			}
			userLevelStatsService.saveBatch(result);
		}
		LogSystem.info("手动--渠道等级统计Collector完毕");
	}
}
