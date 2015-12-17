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
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class SummaryDataAoList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 0l;
	private Integer channel;
	private List<Partner> partnerList;
	private String isCommit = "F";
	private Map<String, String> statsMap = new HashMap<String, String>();
	public String execute() {
		SummaryStatsService summaryStatsService = ServiceCacheFactory.getServiceCache().getService(SummaryStatsService.class);
		OnlineDetailStatsService onlineDetailStatsService = ServiceCacheFactory.getServiceCache().getService(OnlineDetailStatsService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		partnerList = partnerService.findPartnerList();
		
		List<Partner> partnerList = partnerService.findPartnerList();
		Map<String, String> partnerMap = new HashMap<String, String>();
		for (Partner p : partnerList) {
			partnerMap.put(p.getPNum(), p.getPName());
		}
		if(isCommit.equals("T")){
			String[] dates = DateUtil.getTodayStrArr(null);
			Map<String,Object[]> payMap = summaryStatsService.aoFindSummaryData(dates,channel+"");//联运当前在线人数、充值总数、充值总人数、购买道具消耗
			Map<String, Object[]> onlineMap = onlineDetailStatsService.aoFindOnlineData(dates, channel+"");//联运在线数据
			Map<String, Integer> map = new HashMap<String, Integer>();
			if(payMap.size()>0){
				for(String str : payMap.keySet()){
					map.put(str, 1);
				}
			}
			if(onlineMap.size()>0){
				for(String str : onlineMap.keySet()){
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
					if(onlineMap.containsKey(channel)){
						Object[] arr = onlineMap.get(channel);
						sb.append(arr[4]).append(",");
						sb.append(arr[3]).append(",");
						Date date = DateUtil.stringtoDate(arr[1].toString(), DateUtil.LONG_DATE_FORMAT);
						String str = DateUtil.formatTime(date.getTime()+Integer.valueOf(arr[2].toString())*SystemStatsDate.FIVE_MINUTE_INDEX);
						sb.append(str);
					}else{
						sb.append(0).append(",");
						sb.append(0).append(",");
						sb.append("*");
					}
					TGameServer tGameServer = DataSourceManager.getInstatnce().getGameServerMap().get(Integer.parseInt(channel));
					statsMap.put(tGameServer.getServerAlias(), sb.toString());
				}
			}
		}
		return SUCCESS;
	}
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public Map<String, String> getStatsMap() {
		return statsMap;
	}
	public void setStatsMap(Map<String, String> statsMap) {
		this.statsMap = statsMap;
	}
	public List<Partner> getPartnerList() {
		return partnerList;
	}
	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}
	public String getIsCommit() {
		return isCommit;
	}
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	
}
