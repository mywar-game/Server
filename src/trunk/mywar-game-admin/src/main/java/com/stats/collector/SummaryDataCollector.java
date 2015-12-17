package com.stats.collector;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.service.UserPayService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserMallLogService;
import com.log.service.UserOnlineLogService;
import com.stats.bo.SummaryStats;
import com.stats.service.SummaryStatsService;

public class SummaryDataCollector implements Collector {

	@Override
	public void execute(Date date)
			throws Exception {
		// TODO Auto-generated method stub
		LogSystem.info("汇总数据collector开始");
		String[] today = DateUtil.getTodayStrArr(date);
		String[] dates = DateUtil.getInSomeMinuteStrArr(date,SystemStatsDate.THIRTY_MINUTE_AGO);//统计30分钟之内的数据
		UserOnlineLogService userOnlineLogService = ServiceCacheFactory.getServiceCache().getService(UserOnlineLogService.class);
		UserPayService userPayService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		UserMallLogService userMallLogService = ServiceCacheFactory.getServiceCache().getService(UserMallLogService.class);
		SummaryStatsService summaryStatsService = ServiceCacheFactory.getServiceCache().getService(SummaryStatsService.class);
		userOnlineLogService.findOnlineChannelSummaryData(dates);//采集渠道对应的玩家在线数据
		Map<String, Integer> currentAmountMap = userOnlineLogService.findChannelCurrentUserAmount();//渠道对应的当前在线人数
		Map<String, Object[]> payMap = userPayService.findTotalPayUserStrAndTotalAmount(today);//玩家当天的充值玩家编号str和充值总额
		Map<String, Integer> consumeMap = userMallLogService.findBuyToolConsumeByChannel(today);
		int timeIndex = DateUtil.getIndex(date,SystemStatsDate.HALF_HOUR_INDEX);//获得当前时间半小时的维度
		Map<String, Integer> channelMap = new HashMap<String, Integer>();//存在数据的渠道
		if(currentAmountMap.size()>0){
			for(String key : currentAmountMap.keySet()){
				channelMap.put(key, 1);
			}
		}
		if(payMap.size()>0){
			for(String key : payMap.keySet()){
				channelMap.put(key, 1);
			}
		}
		if(consumeMap.size()>0){
			for(String key : consumeMap.keySet()){
				channelMap.put(key, 1);
			}
		}
		if(channelMap.size()>0){
			List<SummaryStats> list = new ArrayList<SummaryStats>();
			for(String key:channelMap.keySet()){
				SummaryStats stats = new SummaryStats();
				stats.setBuyToolConsume(consumeMap.get(key)==null?0:consumeMap.get(key));
				stats.setChannel(key);
				stats.setSysNum(CustomerContextHolder.getSystemNum());
				stats.setCurrentOnlineAmount(currentAmountMap.get(key)==null?0:currentAmountMap.get(key));
				stats.setHalfHourIndex(timeIndex);
				if(payMap.get(key)==null){
					stats.setPayNum(0d);
					stats.setPayUserNum(0);
					stats.setPayTotalTimes(0);
					stats.setPayUserStr("");
				}else{
					stats.setPayNum(Double.valueOf(payMap.get(key)[1].toString()));
					stats.setPayUserNum(payMap.get(key)[0].toString().split(",").length);
					stats.setPayTotalTimes(Integer.valueOf(payMap.get(key)[2].toString()));
					stats.setPayUserStr(payMap.get(key)[0].toString());
				}
				stats.setTime(date);
				list.add(stats);
			}
			summaryStatsService.saveBatch(list);
		}
		
		LogSystem.info("汇总数据collector结束");
	}

}
