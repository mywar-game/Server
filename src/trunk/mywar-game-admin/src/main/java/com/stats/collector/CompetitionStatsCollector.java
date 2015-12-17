package com.stats.collector;

import java.util.Date;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.CompetitionStats;
import com.stats.service.CompetitionService;

/**
 * 天界统计
 * @author Administrator
 *
 */
public class CompetitionStatsCollector implements Collector  {
	@Override
	public void execute(Date date) throws Exception {
		
		LogSystem.info("天界统计Collector开始");
		CompetitionService competitionService = ServiceCacheFactory.getServiceCache().getService(CompetitionService.class);
		
		String[] dates = DateUtil.getOneDayStrArr(-1);
		
		int joinTotal = competitionService.getCoinUseTotal(dates);
		int diamondUse = competitionService.getDiamondUse(dates);
		int diamondJoin = competitionService.getDiamondJoin(dates);
		int coinUse = competitionService.getCoinUse(dates);
		
		CompetitionStats stats = new CompetitionStats();
		stats.setJoinTotal(joinTotal);
		stats.setDiamondUse(diamondUse);
		stats.setDiamondJoin(diamondJoin);
		stats.setCoinUse(coinUse);
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setTime(new Date());
		
		competitionService.save(stats);
		
		LogSystem.info("天界统计Collector结束");
		
	}
}
