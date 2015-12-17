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
import com.stats.bean.LevelAnalyze;
import com.stats.service.UserLevelStatsService;
import com.system.service.TGameServerService;

public class LevelStatsList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private Integer channel;
	private Integer sysNum;
	private String order;
	private Map<Integer,String> serverMap;
	private List<Partner> partnerList;
	private List<LevelAnalyze> statsList = new ArrayList<LevelAnalyze>();
	@Override
	public String execute() {
		UserLevelStatsService userLevelStatsService = ServiceCacheFactory.getServiceCache().getService(UserLevelStatsService.class);
		TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		serverMap = gameServerService.findServerIdNameMap();
		partnerList = partnerService.findPartnerList();
		String queryDate = "";
		if(super.getStartDate()==null){
			queryDate = DateUtil.getTime(-1);
		}else{
			queryDate = DateUtil.dateToString(super.getStartDate(), DateUtil.LONG_DATE_FORMAT);
		}
		IPage<Object> page = userLevelStatsService.findLevelStats(channel, sysNum, order, queryDate, super.getPageSize(), super.getToPage());
		if(page!=null){
			List<Object> list = (List<Object>)page.getData();
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					Object[] arr = (Object[])list.get(i);
					LevelAnalyze stats = new LevelAnalyze();
					stats.setUserLevel(Integer.valueOf(arr[0].toString()));
					stats.setRegNum(Integer.valueOf(arr[1].toString()));
					stats.setUserNum(Integer.valueOf(arr[2].toString()));
					stats.setPayUserNum(Integer.valueOf(arr[3].toString()));
					stats.setPayAmount(Double.valueOf(arr[4].toString()));
					stats.setPayTimes(Integer.valueOf(arr[5].toString()));
					stats.setBuyToolConsume(arr[6].toString());
					statsList.add(stats);
				}
				super.setTotalPage(page.getTotalPage());
				super.setTotalSize(page.getPageSize());
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
	public List<LevelAnalyze> getStatsList() {
		return statsList;
	}
	public void setStatsList(List<LevelAnalyze> statsList) {
		this.statsList = statsList;
	}
	
}
