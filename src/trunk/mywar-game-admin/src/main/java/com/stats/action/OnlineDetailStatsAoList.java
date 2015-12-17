package com.stats.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.common.SystemStatsDate;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.OnlineDetailStats;
import com.stats.service.OnlineDetailStatsService;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;
import com.system.service.TGameServerService;

public class OnlineDetailStatsAoList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private String isCommit = "F";
	private Integer sysNum;
	private Map<Integer,String> serverMap;
	private List<OnlineDetailStats> statsList = new ArrayList<OnlineDetailStats>();
	private Integer maxOnlineAmount = 0;
	private String avgOnlineAmount = "0";
	private Integer maxIpNum = 0;
	private String avgIpNum = "0";
	@Override
	public String execute() {
		OnlineDetailStatsService onlineDetailStatsService = ServiceCacheFactory.getServiceCache().getService(OnlineDetailStatsService.class);
		TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		serverMap = gameServerService.findServerIdNameMap();
		IPage<Object> page = null;
		if(isCommit.equals("T")){
			if(super.getStartDate()==null){
				super.setStartDate(DateUtil.getZeroTime(new Date()));
			}
			page = onlineDetailStatsService.aoFindList(DEFAULT_PAGESIZE, super.getToPage(), DateUtil.dateToString(getStartDate(), DateUtil.LONG_DATE_FORMAT), sysNum);
			if(page!=null){
				List<Object> list = (List<Object>)page.getData();
				if(list!=null && list.size()>0){
					for(int i=0;i<list.size();i++){
						Object[] arr = (Object[])list.get(i);
						OnlineDetailStats stats = new OnlineDetailStats();
						//TGameServer tGameServer = DataSourceManager.getInstatnce().getGameServerMap().get(sysNum);
						//statsMap.put(tGameServer.getServerAlias(), sb.toString());
						stats.setSysNum(sysNum);
						String str = DateUtil.formatTime(getStartDate().getTime()+Integer.valueOf(arr[1].toString())*SystemStatsDate.FIVE_MINUTE_INDEX);
						stats.setStatsTime(str);
						stats.setOnlineAmount(Integer.valueOf(arr[2].toString()));
						stats.setIpNum(Integer.valueOf(arr[3].toString()));
						statsList.add(stats);
					}
					List<Object> list1 = onlineDetailStatsService.aoFindOnlineStats(super.getStartDate(), sysNum);
					if(list1!=null && list1.size()>0){
						Object[] arr = (Object[])list1.get(0);
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

	public List<OnlineDetailStats> getStatsList() {
		return statsList;
	}
	public void setStatsList(List<OnlineDetailStats> statsList) {
		this.statsList = statsList;
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
	public Integer getSysNum() {
		return sysNum;
	}
	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
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

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	
}
