package com.stats.collector;

import java.util.Date;
import java.util.Map;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLevelupLogService;
import com.stats.bo.UserLevelStats;
import com.stats.service.UserLevelStatsService;
/**
 * 等级区间采集器
 * levelIndex-等级区间(0:1-5,1:6-10,2:11-15....23:116-120)
 * @author Administrator
 *
 */
public class UserLevelStatsCollector implements Collector {

	@Override
	public void execute(Date date)
			throws Exception {
		// TODO Auto-generated method stub
		LogSystem.info("玩家等级统计Collector开始");
		UserLevelupLogService userLevelupLogService = ServiceCacheFactory.getServiceCache().getService(UserLevelupLogService.class);
		UserLevelStatsService userLevelStatsService = ServiceCacheFactory.getServiceCache().getService(UserLevelStatsService.class);
		Map<Integer, Integer> map = userLevelupLogService.findUserLevelIndexCount();//等级区间对应人数map
		StringBuilder sb = new StringBuilder();
		if(map!=null && map.size()>0){
			for(Integer key : map.keySet()){
				sb.append(key).append("_").append(map.get(key)).append(",");
			}
		}
		UserLevelStats stats = new UserLevelStats();
		stats.setLevelIndexInfo("");
		String indexinfo = sb.toString();
		if(!indexinfo.equals("")){
			stats.setLevelIndexInfo(indexinfo.substring(0, indexinfo.length()-1));
		}
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setTime(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		userLevelStatsService.save(stats);
		LogSystem.info("玩家等级统计Collector完毕");
	}
}
