package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLevelupLogService;
import com.stats.bo.UserLevelStats;
import com.stats.service.UserLevelStatsService;

/**
 * 手动--玩家等级统计
 * @author Administrator
 *
 */
public class ReUserLevelStatsCollector {

	// TODO
	public void execute(String dateStr) {
		// TODO Auto-generated method stub
		LogSystem.info("手动--玩家等级统计Collector开始");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String[] dateArr = DateUtil.getBeforeDateByDate(dateTemp, 0);
		UserLevelupLogService userLevelupLogService = ServiceCacheFactory.getServiceCache().getService(UserLevelupLogService.class);
		UserLevelStatsService userLevelStatsService = ServiceCacheFactory.getServiceCache().getService(UserLevelStatsService.class);
		//Map<Integer, Integer> map = userLevelupLogService.findUserLevelIndexCount();//等级区间对应人数map
		Map<Integer, Integer> map = userLevelupLogService.findUserLevelIndexCountByDate(dateArr[1]);
		
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
//		stats.setTime(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		stats.setTime(DateUtil.getSomeDaysDiffDateByDate(dateTemp, 0));
		
		userLevelStatsService.delete(dateStr);
		userLevelStatsService.save(stats);
		LogSystem.info("手动--结束玩家等级统计Collector完毕");
	}
}
