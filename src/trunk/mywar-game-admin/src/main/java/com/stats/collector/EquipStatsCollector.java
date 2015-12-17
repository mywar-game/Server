package com.stats.collector;

import java.util.Date;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.EquipStats;
import com.stats.service.EquipService;

/**
 * 战魂统计
 * @author Administrator
 *
 */
public class EquipStatsCollector implements Collector {

	@Override
	public void execute(Date date) throws Exception {
		
		LogSystem.info("战魂统计Collector开始");
		EquipService equipService = ServiceCacheFactory.getServiceCache().getService(EquipService.class);
		
		String[] dates = DateUtil.getOneDayStrArr(-1);
		
		int total1 = equipService.getDiamondUseCountTotal(dates);
		int total2 = equipService.getCoinUseCountTotal(dates);
		int total3 = equipService.getLingUse(dates);
		int allTotal = total1 + total2 + total3;
		
		int diamondUseTotal = equipService.getDiamondUseTotal(dates);
		int diamondUse = equipService.getDiamondUse(dates);
		int coinUse = equipService.getCoinUse(dates);
		int coinUseTotal = equipService.getCoinUseTotal(dates);
		
		EquipStats stats = new EquipStats();
		stats.setAllTotal(allTotal);
		stats.setDiamondJoinTotal(diamondUseTotal);
		stats.setDiamondTotal(diamondUse);
		stats.setCoinJoinTotal(coinUseTotal);
		stats.setCoinTotal(coinUse);
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setTime(new Date());
		
		equipService.saveStats(stats);
		
		LogSystem.info("战魂统计Collector结束");
	}
}
