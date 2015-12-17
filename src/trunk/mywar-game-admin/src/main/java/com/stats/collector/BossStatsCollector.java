package com.stats.collector;

import java.util.Date;
import java.util.List;

import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.BattleLogService;
import com.log.service.UserGoldLogService;
import com.stats.bo.BossStats;
import com.stats.service.BossStatsService;

/**
 * boss战统计
 * @author Administrator
 *
 */
public class BossStatsCollector implements Collector {

	@Override
	public void execute(Date time) throws Exception {
		LogSystem.info("Boss战统计Collector开始");
		
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		Date date = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		
		BossStatsService bossService = ServiceCacheFactory.getServiceCache().getService(BossStatsService.class);
		BattleLogService battleLogService = ServiceCacheFactory.getServiceCache().getService(BattleLogService.class);
		UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
		
		List<Object> battleList = battleLogService.countBoss(dates);
		List<Object> guwuGoldList = userGoldLogService.countGuwu(dates);
		List<Object> relifeGoldList = userGoldLogService.countRelife(dates);
		
		BossStats stats = new BossStats();
		stats.setTime(date);
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		if (battleList != null) {
			for (int i = 0; i < battleList.size(); i++) {
				Object[] objArr = (Object[]) battleList.get(i);
				stats.setFightCount(Integer.valueOf(objArr[0].toString()));
			}
		}
		if (guwuGoldList != null) {
			for (int i = 0; i < guwuGoldList.size(); i++) {
				Object[] objArr = (Object[]) guwuGoldList.get(i);
				stats.setDiamondGuwuCount(Integer.valueOf(objArr[0].toString()));
				stats.setDiamondGuwuCiCount(Integer.valueOf(objArr[1].toString()));
			}
		}
		if (relifeGoldList != null) {
			for (int i = 0; i < relifeGoldList.size(); i++) {
				Object[] objArr = (Object[]) relifeGoldList.get(i);
				stats.setFuhuoCount(Integer.valueOf(objArr[0].toString()));
				stats.setFuhuoCiCount(Integer.valueOf(objArr[1].toString()));
			}
		}
		bossService.save(stats);
		LogSystem.info("Boss战统计Collector结束");
	}

}
