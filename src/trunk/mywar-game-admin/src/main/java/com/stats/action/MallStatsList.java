package com.stats.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.MallStats;
import com.stats.service.MallStatsService;
import com.system.service.TGameServerService;

public class MallStatsList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private Integer channel;
	private Integer sysNum;
	private String order;
	private Map<Integer,String> serverMap;
	private List<Partner> partnerList;
	private List<MallStats> statsList = new ArrayList<MallStats>();
	public String execute() {
		String[] dates = DateUtil.getTodayStrArr(null);
		MallStatsService mallStatsService = ServiceCacheFactory.getServiceCache().getService(MallStatsService.class);
		TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		serverMap = gameServerService.findServerIdNameMap();
		partnerList = partnerService.findPartnerList();
		IPage<Object> page = mallStatsService.findMallStats(channel, sysNum, order, dates, super.getPageSize(), super.getToPage());//商城售卖统计信息
		if(page!=null){
			List<Object> list = (List<Object>)page.getData();
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					Object[] arr = (Object[])list.get(i);
					MallStats stats = new MallStats();
					stats.setTreasureId(Integer.valueOf(arr[0].toString()));
					stats.setTreasureName(arr[1].toString());
					stats.setPrice(Integer.valueOf(arr[2].toString()));
					stats.setSaleNum(Integer.valueOf(arr[3].toString()));
					stats.setCostGold(Integer.valueOf(arr[4].toString()));
					stats.setBuyUserNum(Integer.valueOf(arr[5].toString()));
					statsList.add(stats);
				}
				super.setTotalPage(page.getTotalPage());
				super.setTotalSize(page.getTotalSize());
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
	public Integer getSysNum() {
		return sysNum;
	}
	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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
	public List<MallStats> getStatsList() {
		return statsList;
	}
	public void setStatsList(List<MallStats> statsList) {
		this.statsList = statsList;
	}
}
