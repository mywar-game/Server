package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserActionLogService;
import com.stats.bo.UserNodeLossStats;
import com.stats.service.UserNodeLossStatsService;

/**
 * 手动--玩家节点统计
 * @author Administrator
 *
 */
public class ReUserNodeLossStatsCollector {

	// TODO
	public void execute(String dateStr) {
		// TODO Auto-generated method stub
		LogSystem.info("重新采集--玩家节点统计Collector开始");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		UserNodeLossStatsService userNodeLossStatsService = ServiceCacheFactory.getServiceCache().getService(UserNodeLossStatsService.class);
		UserActionLogService userActionLogService = ServiceCacheFactory.getServiceCache().getService(UserActionLogService.class);
		
//		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		String[] dates = DateUtil.getBeforeDateByDate(dateTemp, 0);
		
		// 先删除
		userNodeLossStatsService.delete(dateStr);
		
		List<Object> list = userActionLogService.collectUserActionData(dates);
		
//		Date date = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		Date date = DateUtil.getSomeDaysDiffDateByDate(dateTemp, 0);
		
		List<UserNodeLossStats> statsList = new ArrayList<UserNodeLossStats>();
		
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				UserNodeLossStats stats = new UserNodeLossStats();
				stats.setActionId(Integer.valueOf(((Object[])list.get(i))[0].toString()));
				stats.setNum(Integer.valueOf(((Object[])list.get(i))[1].toString()));
				stats.setSysNum(CustomerContextHolder.getSystemNum());
				stats.setTime(date);
				statsList.add(stats);
			}
			userNodeLossStatsService.saveBatch(statsList);
		}
		LogSystem.info("重新采集--玩家节点统计Collector开始");
	}
}
