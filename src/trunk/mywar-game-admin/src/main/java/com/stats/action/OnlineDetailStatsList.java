package com.stats.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.common.SystemStatsDate;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.OnlineDetailStats;
import com.stats.service.OnlineDetailStatsService;
import com.system.service.TGameServerService;

public class OnlineDetailStatsList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private Integer pid;
	private Integer serverId;
	private Map<Integer,String> serverMap;
	private List<Partner> partnerList;
	private List<OnlineDetailStats> statsList;
	private Integer maxOnlineAmount = 0;
	private String avgOnlineAmount = "0";
	private Integer maxIpNum = 0;
	private String avgIpNum = "0";
	@Override
	public String execute() {
		OnlineDetailStatsService onlineDetailStatsService = ServiceCacheFactory.getServiceCache().getService(OnlineDetailStatsService.class);
		TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		serverMap = gameServerService.findServerIdNameMap();
		partnerList = partnerService.findPartnerList();
		IPage<OnlineDetailStats> page = null;
		if(pid!=null || serverId!=null){
			if(super.getStartDate()==null){
				super.setStartDate(DateUtil.getZeroTime(new Date()));
			}
			page = onlineDetailStatsService.findListInDate(super.getPageSize(), super.getToPage(), super.getStartDate(), serverId, pid+"");
			if(page!=null){
				statsList = (List<OnlineDetailStats>)page.getData();
				if(statsList!=null && statsList.size()>0){
					for(OnlineDetailStats stats : statsList){
						String str = DateUtil.formatTime(stats.getTime().getTime()+stats.getFiveMinuteIndex()*SystemStatsDate.FIVE_MINUTE_INDEX);
						stats.setStatsTime(str);
					}
					List<Object> list = onlineDetailStatsService.findOnlineStats(super.getStartDate(), serverId, pid+"");
					if(list!=null && list.size()>0){
						Object[] arr = (Object[])list.get(0);
						maxOnlineAmount = Integer.valueOf(arr[0].toString());
						avgOnlineAmount = arr[1].toString();
						maxIpNum = Integer.valueOf(arr[2].toString());
						avgIpNum = arr[3].toString();
					}
				}
				super.setTotalPage(page.getTotalPage());
				super.setTotalSize(page.getPageSize());
			}
		}
		return SUCCESS;
	}
	public Map<Integer, String> getServerMap() {
		return serverMap;
	}
	public void setServerMap(Map<Integer, String> serverMap) {
		this.serverMap = serverMap;
	}
	public List<Partner> getPartnerList() {
		return partnerList;
	}
	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}
	public List<OnlineDetailStats> getStatsList() {
		return statsList;
	}
	public void setStatsList(List<OnlineDetailStats> statsList) {
		this.statsList = statsList;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getServerId() {
		return serverId;
	}
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
	public Integer getMaxOnlineAmount() {
		return maxOnlineAmount;
	}
	public void setMaxOnlineAmount(Integer maxOnlineAmount) {
		this.maxOnlineAmount = maxOnlineAmount;
	}
	public String getAvgOnlineAmount() {
		return avgOnlineAmount;
	}
	public void setAvgOnlineAmount(String avgOnlineAmount) {
		this.avgOnlineAmount = avgOnlineAmount;
	}
	public Integer getMaxIpNum() {
		return maxIpNum;
	}
	public void setMaxIpNum(Integer maxIpNum) {
		this.maxIpNum = maxIpNum;
	}
	public String getAvgIpNum() {
		return avgIpNum;
	}
	public void setAvgIpNum(String avgIpNum) {
		this.avgIpNum = avgIpNum;
	}
	
}
