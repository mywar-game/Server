package com.stats.collector;

import java.util.Date;
import java.util.List;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.BattleLogService;
import com.log.service.UserTreasureLogService;
import com.stats.bo.PlunderStats;
import com.stats.service.PlunderStatsService;

/**
 * 夺宝统计
 * @author Administrator
 *
 */
public class PlunderStatsCollector implements Collector {

	@Override
	public void execute(Date date){
		LogSystem.info("夺宝统计Collector开始");
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		BattleLogService battleLogService = ServiceCacheFactory.getServiceCache().getService(BattleLogService.class);
		UserTreasureLogService userTreasureLogService= ServiceCacheFactory.getServiceCache().getService(UserTreasureLogService.class);
		PlunderStatsService plunderService = ServiceCacheFactory.getServiceCache().getService(PlunderStatsService.class);
		
		List<Object> plunderList = battleLogService.countPlunder(dates);
		List<Object> endranceList = userTreasureLogService.countEndrance(dates);
		
		PlunderStats stats = new PlunderStats();
		Date time = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setTime(time);
		if (plunderList != null) {
			for (int i = 0; i < plunderList.size(); i++) {
				Object[] objArr = (Object[]) plunderList.get(i);
				if (objArr != null && objArr.length == 2) {
					stats.setFightUserCount(Integer.valueOf(objArr[0].toString()));
					stats.setFightTimes(Integer.valueOf(objArr[1].toString()));
				}
			}
		}
		if (endranceList != null) {
			for (int i = 0; i < endranceList.size(); i++) {
				Object[] objArr = (Object[]) endranceList.get(i);
				if (objArr != null && objArr.length == 2) {
					stats.setEndrance(objArr[0]==null?0:Integer.valueOf(objArr[0].toString()));
				}
			}
		}
		plunderService.save(stats);
		
		LogSystem.info("夺宝统计Collector结束");
	}
}
