package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.BattleLogService;
import com.log.service.UserTreasureLogService;
import com.stats.bo.PlunderStats;
import com.stats.service.PlunderStatsService;

/**
 * 重新采集--夺宝统计
 * @author Administrator
 *
 */
public class RePlunderStatsCollector {

	public void execute(String dateStr, int index){
		LogSystem.info("重新采集--夺宝统计Collector开始");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String[] dates = DateUtil.getBeforeDateByDate(dateTemp, 0);
		//String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		BattleLogService battleLogService = ServiceCacheFactory.getServiceCache().getService(BattleLogService.class);
		UserTreasureLogService userTreasureLogService= ServiceCacheFactory.getServiceCache().getService(UserTreasureLogService.class);
		PlunderStatsService plunderService = ServiceCacheFactory.getServiceCache().getService(PlunderStatsService.class);
		
		if (index == 0) {
			// 先删除
			plunderService.delete(dateStr);
		}
		
		List<Object> plunderList = battleLogService.countPlunder(dates);
		List<Object> endranceList = userTreasureLogService.countEndrance(dates);
		
		PlunderStats stats = new PlunderStats();
//		Date time = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		Date time = DateUtil.getSomeDaysDiffDateByDate(dateTemp, 0);
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setTime(time);
		if (plunderList != null) {
			for (int i = 0; i < plunderList.size(); i++) {
				Object[] objArr = (Object[]) plunderList.get(i);
				if (objArr != null && objArr.length == 2) {
					if (objArr[0] == null) {
						stats.setFightUserCount(0);
					} else {
						stats.setFightUserCount(Integer.valueOf(objArr[0].toString()));
					}
					if (objArr[1] == null) {
						stats.setFightTimes(0);
					} else {
						stats.setFightTimes(Integer.valueOf(objArr[1].toString()));
					}
				}
			}
		}
		if (endranceList != null) {
			for (int i = 0; i < endranceList.size(); i++) {
				Object[] objArr = (Object[]) endranceList.get(i);
				if (objArr != null && objArr.length == 2) {
					if (objArr[0] == null) {
						stats.setEndrance(0);
					} else {
						stats.setEndrance(Integer.valueOf(objArr[0].toString()));
					}				
				}
			}
		}
		plunderService.save(stats);
		
		LogSystem.info("重新采集--夺宝统计Collector结束");
	}
}
