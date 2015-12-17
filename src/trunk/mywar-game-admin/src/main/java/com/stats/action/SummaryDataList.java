package com.stats.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.SystemStatsDate;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.service.OnlineDetailStatsService;
import com.stats.service.SummaryStatsService;

public class SummaryDataList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 3475276590317692485L;
	private Map<String, String> statsMap = new HashMap<String, String>();
	public String execute() {
		SummaryStatsService summaryStatsService = ServiceCacheFactory.getServiceCache().getService(SummaryStatsService.class);
		OnlineDetailStatsService onlineDetailStatsService = ServiceCacheFactory.getServiceCache().getService(OnlineDetailStatsService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		List<Partner> partnerList = partnerService.findPartnerList();
		Map<String, String> partnerMap = new HashMap<String, String>();
		for (Partner p : partnerList) {
			partnerMap.put(p.getPNum(), p.getPName());
		}
		String[] dates = DateUtil.getTodayStrArr(null);
		Map<String,Object[]> payMap = summaryStatsService.findSummaryData(dates);//根据渠道分组的当前在线人数、充值总数、充值总人数、购买道具消耗
		Map<String, Integer> avgMap = onlineDetailStatsService.findAvgOnlineAmount(dates);//平均在线人数
		Map<String, Double> maxMap = onlineDetailStatsService.findMaxOnlineAmount(dates);//最高在线人数
		Map<String, Object[]> maxTimeMap = onlineDetailStatsService.findMaxOnlineTimeAmount(dates);//最高在线时间
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(payMap.size()>0){
			for(String str : payMap.keySet()){
				map.put(str, 1);
			}
		}
		if(avgMap.size()>0){
			for(String str : avgMap.keySet()){
				map.put(str, 1);
			}
		}
		if(maxMap.size()>0){
			for(String str : maxMap.keySet()){
				map.put(str, 1);
			}
		}
		if(maxTimeMap.size()>0){
			for(String str : maxTimeMap.keySet()){
				map.put(str, 1);
			}
		}
		if(map.size()>0){
			for(String channel : map.keySet()){
				StringBuilder sb = new StringBuilder();
				if(payMap.containsKey(channel)){//设置当前在线人数、充值总数、充值总人数、购买道具消耗
					Object[] arr = payMap.get(channel);
					sb.append(arr[1]).append(",").append(arr[2]).append(",").append(arr[3]).append(",").append(arr[4]).append(",");
				}else{
					sb.append(0).append(",").append(0).append(",").append(0).append(",").append(0).append(",");
				}
				if(avgMap.containsKey(channel)){//设置平均在线人数
					sb.append(avgMap.get(channel)).append(",");
				}else{
					sb.append(0).append(",");
				}
				if(maxMap.containsKey(channel)){//设置最高在线人数
					sb.append(maxMap.get(channel)).append(",");
				}else{
					sb.append(0).append(",");
				}
				if(maxTimeMap.containsKey(channel)){//设置最高在线时间
					Object[] arr = maxTimeMap.get(channel);
					Date date = DateUtil.stringtoDate(arr[1].toString(), DateUtil.LONG_DATE_FORMAT);
					String str = DateUtil.formatTime(date.getTime()+Integer.valueOf(arr[2].toString())*SystemStatsDate.FIVE_MINUTE_INDEX);
					sb.append(str);
				}else{
					sb.append("*");
				}
				statsMap.put(partnerMap.get(channel), sb.toString());
			}
		}
		return SUCCESS;
	}
	public Map<String, String> getStatsMap() {
		return statsMap;
	}
	public void setStatsMap(Map<String, String> statsMap) {
		this.statsMap = statsMap;
	}
	
}
