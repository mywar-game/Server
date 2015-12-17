package com.stats.collector;

import java.util.Date;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.LegionStats;
import com.stats.service.LegionService;

/**
 * 军团统计
 * @author Administrator
 *
 */
public class LegionStatsCollector implements Collector {

	@Override
	public void execute(Date date) throws Exception {
		LogSystem.info("军团统计Collector开始");
		LegionService legionService = ServiceCacheFactory.getServiceCache().getService(LegionService.class);
		String[] dates = DateUtil.getOneDayStrArr(-1);
		
		int legionJoin = legionService.getLegionJoin();
		int legionTotal = legionService.getLegionTotal();
		int diamondUse = legionService.getDiamondUse(dates);
		int coinUse = legionService.getCoinUse(dates);
		int diamondUseTotal = legionService.getDiamondUseTotal(dates);
		
		LegionStats stats = new LegionStats();
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setTime(new Date());
		stats.setLegionJoin(legionJoin);
		stats.setLegionTotal(legionTotal);
		stats.setDiamondUse(diamondUse);
		stats.setCoinUse(coinUse);
		stats.setDiamondUseTotal(diamondUseTotal);
		
		legionService.saveStats(stats);
		LogSystem.info("军团统计Collector结束");
	}
}
