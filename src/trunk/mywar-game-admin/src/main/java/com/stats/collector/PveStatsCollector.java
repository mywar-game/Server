package com.stats.collector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.BattleLogService;
import com.stats.bo.UserPveStats;
import com.stats.service.UserPveStatsService;

public class PveStatsCollector implements Collector {

	@Override
	public void execute(Date time) {
		LogSystem.info("pve统计Collector开始");
		BattleLogService battleLogService = ServiceCacheFactory
				.getServiceCache().getService(BattleLogService.class);
		UserPveStatsService userBattleStatsService = ServiceCacheFactory
				.getServiceCache().getService(UserPveStatsService.class);
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		List<Object> list = battleLogService.collectUserBattleStats(dates);
		Date date = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		List<UserPveStats> statsList = new ArrayList<UserPveStats>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				UserPveStats stats = new UserPveStats();
				stats.setForcesId(Integer.valueOf(((Object[]) list.get(i))[0]
						.toString()));
				stats.setSysNum(CustomerContextHolder.getSystemNum());
				stats.setAtkUserNum(Integer.valueOf(((Object[]) list.get(i))[1]
						.toString()));
				stats.setAtkNum(Integer.valueOf(((Object[]) list.get(i))[2]
						.toString()));
				stats.setWinNum(Integer.valueOf(((Object[]) list.get(i))[3]
						.toString()));
				stats.setDrawNum(Integer.valueOf(((Object[]) list.get(i))[4]
						.toString()));
				stats.setFailNum(stats.getAtkNum() - stats.getWinNum()
						- stats.getDrawNum());
				stats.setTime(date);
				statsList.add(stats);
			}
			userBattleStatsService.saveBatch(statsList);
		}
		LogSystem.info("pve统计Collector完毕");
	}

}
