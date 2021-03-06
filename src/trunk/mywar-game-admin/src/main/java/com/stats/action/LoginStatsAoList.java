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
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class LoginStatsAoList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private Integer channel;
	private List<Partner> partnerList;
	private String isCommit = "F";
	private Map<String, String> statsMap = new HashMap<String, String>();
	public String execute() {
		LoginStatsService loginStatsService = ServiceCacheFactory.getServiceCache().getService(LoginStatsService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		partnerList = partnerService.findPartnerList();
		List<Partner> partnerList = partnerService.findPartnerList();
		Map<String, String> partnerMap = new HashMap<String, String>();
		for (Partner p : partnerList) {
			partnerMap.put(p.getPNum(), p.getPName());
		}
		if(isCommit.equals("T")){
			String[] dates = DateUtil.getTodayStrArr(null);
			Map<String, Object[]> map = loginStatsService.aoFindLoginData(dates,channel+"");
			if(map.size()>0){
				for(String channel : map.keySet()){
					Object[] arr = map.get(channel);
					StringBuilder sb = new StringBuilder();
					sb.append(arr[1]).append(",");//登陆账号数
					sb.append(arr[2]).append(",");//登陆ip数
					sb.append(arr[3]).append(",");//新注册
					sb.append(arr[4]).append(",");//新用户
					sb.append(arr[5]);//老账号登陆数
					TGameServer tGameServer = DataSourceManager.getInstatnce().getGameServerMap().get(Integer.parseInt(channel));
					statsMap.put(tGameServer.getServerAlias(), sb.toString());
				}
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
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
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
