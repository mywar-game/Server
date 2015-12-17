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

public class LoginStatsHistoryList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private String isCommit = "F";
	private Map<String, String> statsMap = new HashMap<String, String>();
	public String execute() {
		if(isCommit.equals("T")){
			LoginStatsService loginStatsService = ServiceCacheFactory.getServiceCache().getService(LoginStatsService.class);
			PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
			List<Partner> partnerList = partnerService.findPartnerList();
			Map<String, String> partnerMap = new HashMap<String, String>();
			for (Partner p : partnerList) {
				partnerMap.put(p.getPNum(), p.getPName());
			}
			String[] dates = new String[2];
			dates[0] = DateUtil.dateToString(super.getStartDate(),DateUtil.LONG_DATE_FORMAT);
			dates[1] = DateUtil.dateToString(super.getEndDate(),DateUtil.LONG_DATE_FORMAT);
			Map<String, Object[]> map = loginStatsService.findLoginDataHistory(dates);
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
