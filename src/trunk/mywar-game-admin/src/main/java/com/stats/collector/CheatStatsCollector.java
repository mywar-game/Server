package com.stats.collector;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserTreasureLogService;
import com.stats.bo.CheatStats;
import com.stats.service.CheatStatsService;

/**
 * 作弊记录采集
 * @author Administrator
 *
 */
public class CheatStatsCollector implements Collector {

	@Override
	public void execute(Date date) throws Exception {
		
		LogSystem.info("作弊记录采集Collector开始");
		
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		UserTreasureLogService userTreasureLogService = ServiceCacheFactory.getServiceCache().getService(UserTreasureLogService.class);
		CheatStatsService cheatStatsService = ServiceCacheFactory.getServiceCache().getService(CheatStatsService.class);
//		UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
//		
		List<Object> list = userTreasureLogService.computeCheatLog(dates);
		List<CheatStats> treasureLogList = new ArrayList<CheatStats>();
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = (Object[]) list.get(i);
				CheatStats stats = new CheatStats();
				stats.setUserId((String) objArr[0]);
				stats.setOperation((Integer.valueOf((String) objArr[1])));
				stats.setTreasureId((Integer.valueOf((Integer) objArr[2])));
				stats.setTreasureType((Integer.valueOf((Integer) objArr[3])));
				stats.setCreateTime((Date) objArr[4]);
				stats.setTimes(((BigInteger) objArr[5]).intValue());
				stats.setSysNum(CustomerContextHolder.getSystemNum());
				stats.setDate(new Date());
				treasureLogList.add(stats);
			}
		}
		if (treasureLogList.size() > 0) {
			cheatStatsService.save(treasureLogList);
		}
		
		LogSystem.info("作弊记录采集Collector结束");
	}
}
