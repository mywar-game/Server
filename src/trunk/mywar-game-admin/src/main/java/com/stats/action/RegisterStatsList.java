package com.stats.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.service.RegisterStatsService;

public class RegisterStatsList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, String> statsMap = new HashMap<String, String>();
	public String execute() {
		RegisterStatsService registerStatsService = ServiceCacheFactory.getServiceCache().getService(RegisterStatsService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		List<Partner> partnerList = partnerService.findPartnerList();
		Map<String, String> partnerMap = new HashMap<String, String>();
		for (Partner p : partnerList) {
			partnerMap.put(p.getPNum(), p.getPName());
		}
		String[] dates = DateUtil.getTodayStrArr(null);
		Map<String, Object[]> map = registerStatsService.findRegisterData(dates);//当天各渠道的注册信息
		if(map.size()>0){
			for(String channel : map.keySet()){
				Object[] arr = map.get(channel);
				StringBuilder sb = new StringBuilder();
				sb.append(arr[1]).append(",");//新注册
				sb.append(arr[2]).append(",");//新用户
				sb.append(Integer.valueOf(arr[1].toString())==0?0:Integer.valueOf(arr[2].toString())*100/Integer.valueOf(arr[1].toString())).append(",");//激活率
				sb.append(arr[3]).append(",");//新增账号充值人数
				sb.append(arr[4]).append(",");//新增账号充值数
				sb.append(Integer.valueOf(arr[3].toString())==0?0:Double.valueOf(arr[4].toString())/Integer.valueOf(arr[3].toString())).append(",");//新建账号ARPU
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
