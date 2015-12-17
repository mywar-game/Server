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
import com.stats.bo.ZhengbaStats;
import com.stats.service.ZhengbaStatsService;


/**
 * 重新采集--争霸统计
 * @author Administrator
 *
 */
public class ReZhengbaCollector {

	public void execute(String dateStr, int index){
		
		LogSystem.info("重新采集--争霸统计Collector开始");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String[] dates = DateUtil.getBeforeDateByDate(dateTemp, 0);
		// String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		BattleLogService battleLogService = ServiceCacheFactory.getServiceCache().getService(BattleLogService.class);
		UserTreasureLogService userTreasureLogService= ServiceCacheFactory.getServiceCache().getService(UserTreasureLogService.class);
		ZhengbaStatsService zhengbaService = ServiceCacheFactory.getServiceCache().getService(ZhengbaStatsService.class);
		
		if (index == 0) {
			// 先删除
			zhengbaService.delete(dateStr);
		}
		
		List<Object> zhengbaList = battleLogService.countZhengba(dates);
		List<Object> endranceList = userTreasureLogService.countZhengba(dates);
		
		ZhengbaStats stats = new ZhengbaStats();
		
		//Date time = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		Date time = DateUtil.getSomeDaysDiffDateByDate(dateTemp, 0);
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setTime(time);
		if (zhengbaList != null) {
			for (int i = 0; i < zhengbaList.size(); i++) {
				Object[] objArr = (Object[]) zhengbaList.get(i);
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
		zhengbaService.save(stats);
		
		LogSystem.info("重新采集--争霸统计Collector结束");
	}
}
