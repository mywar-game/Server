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

public class SummaryDataHistoryList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 3475276590317692485L;
	private Map<String, String> statsMap = new HashMap<String, String>();
	private String isCommit = "F";
	public String execute() {
		SummaryStatsService summaryStatsService = ServiceCacheFactory.getServiceCache().getService(SummaryStatsService.class);
		OnlineDetailStatsService onlineDetailStatsService = ServiceCacheFactory.getServiceCache().getService(OnlineDetailStatsService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		List<Partner> partnerList = partnerService.findPartnerList();
		Map<String, String> partnerMap = new HashMap<String, String>();
		for (Partner p : partnerList) {
			partnerMap.put(p.getPNum(), p.getPName());
		}
		if(isCommit.equals("T")){
			String[] dates = new String[2];
			dates[0] = DateUtil.dateToString(super.getStartDate(), DateUtil.LONG_DATE_FORMAT);
			dates[1] = DateUtil.dateToString(super.getEndDate(), DateUtil.LONG_DATE_FORMAT);
			Map<String,Object[]> payMap = summaryStatsService.findSummaryDataHistory(dates);//根据渠道分组的充值总数、购买道具消耗
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
					if(payMap.containsKey(channel)){//设置充值总数、充值总人数、购买道具消耗
						Object[] arr = payMap.get(channel);
						Map<String, Integer> userMap = new HashMap<String, Integer>();
						if(!arr[3].toString().equals("")){//将组装起来的玩家编号遍历得出不重复的充值玩家数量
							String[] userArr = arr[3].toString().split(",");
							if(userArr!=null && userArr.length>0){
								for(int i=0;i<userArr.length;i++){
									if(!userArr[i].equals("")){
										userMap.put(userArr[i], 1);
									}
								}
							}
						}
						sb.append(arr[1]).append(",").append(userMap.size()).append(",").append(arr[2]).append(",");
					}else{
						sb.append(0).append(",").append(0).append(",").append(0).append(",");
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
		}
		return SUCCESS;
	}
	
	public String getIsCommit() {
		return isCommit;
	}
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	public Map<String, String> getStatsMap() {
		return statsMap;
	}
	public void setStatsMap(Map<String, String> statsMap) {
		this.statsMap = statsMap;
	}
	
}
