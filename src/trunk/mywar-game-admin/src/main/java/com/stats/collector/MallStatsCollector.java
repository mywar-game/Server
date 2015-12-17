package com.stats.collector;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserMallLogService;
import com.stats.bo.MallStats;
import com.stats.service.MallStatsService;

public class MallStatsCollector implements Collector {

	@Override
	public void execute(Date date)
			throws Exception {
		// TODO Auto-generated method stub
		LogSystem.info("商城数据collector开始");
		String[] dates = DateUtil.getInSomeMinuteStrArr(date,SystemStatsDate.THIRTY_MINUTE_AGO);//统计30分钟之内的数据
		MallStatsService mallStatsService = ServiceCacheFactory.getServiceCache().getService(MallStatsService.class);
		UserMallLogService userMallLogService = ServiceCacheFactory.getServiceCache().getService(UserMallLogService.class);
		TTreasureConstantService treasureService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		List<Object> list = userMallLogService.findMallStatsDataByChannel(dates);//渠道分组的商城售卖数据
		Map<Integer, String> nameMap = new HashMap<Integer, String>();//道具名字map
		Map<Integer, Integer> priceMap = new HashMap<Integer, Integer>();//道具单价map
		treasureService.findMallTreasureIdNamePriceMap(nameMap, priceMap);
		if(list!=null && list.size()>0){
			List<MallStats> result = new ArrayList<MallStats>();
			for(int i=0;i<list.size();i++){
				Object[] arr = (Object[])list.get(i);
				MallStats stats = new MallStats();
				stats.setChannel(arr[0].toString());
				stats.setSysNum(CustomerContextHolder.getSystemNum());
				stats.setTreasureId(Integer.valueOf(arr[1].toString()));
				stats.setTreasureName(nameMap.containsKey(stats.getTreasureId())?nameMap.get(stats.getTreasureId()):"");
				stats.setPrice(priceMap.containsKey(stats.getTreasureId())?priceMap.get(stats.getTreasureId()):0);
				stats.setSaleNum(Integer.valueOf(arr[2].toString()));
				stats.setCostGold(Integer.valueOf(arr[3].toString()));
				stats.setBuyUserNum(Integer.valueOf(arr[4].toString()));
				stats.setTime(date);
				result.add(stats);
			}
			mallStatsService.saveBatch(result);
		}
		LogSystem.info("商城数据collector结束");
	}

}
