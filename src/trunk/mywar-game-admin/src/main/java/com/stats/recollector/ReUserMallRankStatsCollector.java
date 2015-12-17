package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserMallLogService;
import com.stats.bo.UserMallRankStats;
import com.stats.service.UserMallRankStatsService;

/**
 * 手动--商城TOP10统计
 * @author Administrator
 *
 */
public class ReUserMallRankStatsCollector {

	public void execute(String dateStr) {

		LogSystem.info("手动--商城TOP10统计开始-------------");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
//		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
//		Date date = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		String[] dates = DateUtil.getBeforeDateByDate(dateTemp, 0);
		Date date = DateUtil.getSomeDaysDiffDateByDate(dateTemp, 0);
		
		UserMallRankStatsService userMallRankStatsService = ServiceCacheFactory.getServiceCache().getService(UserMallRankStatsService.class);
		UserMallLogService userMallLogService = ServiceCacheFactory.getServiceCache().getService(UserMallLogService.class);
		
		// 先删除
		userMallRankStatsService.delete(dateStr);
		
		List<Object> costList = userMallLogService.findCostRank(dates, 10);//元宝花费排行前10名
		List<Object> buyNumList = userMallLogService.findBuyNumRank(dates, 10);//购买次数排行前10名
		List<Object> buyUserNumList = userMallLogService.findBuyUserNumRank(dates, 10);//购买人数排行前10名
		
		
		
		int rankNum = 0;//本次统计排到第几名
		if(costList!=null && costList.size()>0){
			rankNum = costList.size();
		}
		if(buyNumList!=null && buyNumList.size()>0){
			if(buyNumList.size()>rankNum){
				rankNum = buyNumList.size();
			}
		}
		if(buyUserNumList!=null && buyUserNumList.size()>0){
			if(buyUserNumList.size()>rankNum){
				rankNum = buyUserNumList.size();
			}
		}
		if(rankNum>0){
			List<UserMallRankStats> result = new ArrayList<UserMallRankStats>();
			for(int i=0;i<rankNum;i++){
				UserMallRankStats stats = new UserMallRankStats();
				stats.setRank(i+1);
				stats.setSysNum(CustomerContextHolder.getSystemNum());
				stats.setTime(date);
				if(costList!=null && costList.size()>i){//次排行元宝花费信息
					stats.setCostTreasureId(Integer.valueOf(((Object[])costList.get(i))[0].toString()));
					stats.setCostNum(Integer.valueOf(((Object[])costList.get(i))[1].toString()));
				}
				if(buyNumList!=null && buyNumList.size()>i){//次排行购买次数信息
					stats.setBuyNumTreasureId(Integer.valueOf(((Object[])buyNumList.get(i))[0].toString()));
					stats.setBuyNum(Integer.valueOf(((Object[])buyNumList.get(i))[1].toString()));
				}
				if(buyUserNumList!=null && buyUserNumList.size()>i){//次排行购买人数信息
					stats.setBuyUserNumTreasureId(Integer.valueOf(((Object[])buyUserNumList.get(i))[0].toString()));
					stats.setBuyUserNum(Integer.valueOf(((Object[])buyUserNumList.get(i))[1].toString()));
				}
				result.add(stats);
			}
			userMallRankStatsService.saveBatch(result);
		}
		LogSystem.info("手动--商城TOP10统计结束-------------");
	}
}
