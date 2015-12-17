package com.stats.collector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLevelupLogService;
import com.stats.bo.LevelStats;
import com.stats.service.UserLevelStatsService;

public class LevelStatsCollector implements Collector {

	@Override
	public void execute(Date date)
			throws Exception {
		// TODO Auto-generated method stub
		LogSystem.info("渠道等级统计Collector开始");
		UserLevelStatsService userLevelStatsService = ServiceCacheFactory.getServiceCache().getService(UserLevelStatsService.class);
		UserLevelupLogService userLevelupLogService = ServiceCacheFactory.getServiceCache().getService(UserLevelupLogService.class);
		List<Object> list = userLevelupLogService.findLevelDataByChannel();//等级分布数据
		if(list!=null && list.size()>0){
			List<LevelStats> result = new ArrayList<LevelStats>();
			for(int i=0;i<list.size();i++){
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
				stats.setTime(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
				result.add(stats);
			}
			userLevelStatsService.saveBatch(result);
		}
		LogSystem.info("渠道等级统计Collector完毕");
	}

}
