package com.stats.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.service.LoginStatsService;

public class LoginStatsList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private Map<String, String> statsMap = new HashMap<String, String>();
	public String execute() {
		LoginStatsService loginStatsService = ServiceCacheFactory.getServiceCache().getService(LoginStatsService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		List<Partner> partnerList = partnerService.findPartnerList();
		Map<String, String> partnerMap = new HashMap<String, String>();
		for (Partner p : partnerList) {
			partnerMap.put(p.getPNum(), p.getPName());
		}
		String[] dates = DateUtil.getTodayStrArr(null);
		Map<String, Object[]> map = loginStatsService.findLoginData(dates);
		if(map.size()>0){
			for(String channel : map.keySet()){
				Object[] arr = map.get(channel);
				StringBuilder sb = new StringBuilder();
				sb.append(arr[1]).append(",");//登陆账号数
				sb.append(arr[2]).append(",");//登陆ip数
				sb.append(arr[3]).append(",");//新注册
				sb.append(arr[4]).append(",");//新用户
				sb.append(arr[5]);//老账号登陆数
			//	statsMap.put(channel, sb.toString());
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
