package com.stats.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserRemainByChannelStats;
import com.stats.service.UserRemainByChannelStatsService;

public class UserRemainStatsByChannelList extends ALDAdminStatsDatePageActionSupport {
	
	private static final long serialVersionUID = 8569365383878290273L;
	private List<UserRemainByChannelStats> statsList;
	
	private String channel;
	
	@Override
	public String execute(){
		UserRemainByChannelStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserRemainByChannelStatsService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		partnerList = partnerService.findPartnerList();
		Map<String, String> partnerMap = new HashMap<String, String>();
		for (Partner p : partnerList) {
			partnerMap.put(p.getPNum(), p.getPName());
		}
		
		if (getStartDate() != null && getEndDate() != null) {
			statsList = statsService.findListInDate(getStartDate(), getEndDate(), channel);
		} else {
			statsList = statsService.findList(super.getPageSize(), super.getToPage(), channel);
		}
		if (statsList != null) {
			for (UserRemainByChannelStats sts : statsList) {
				sts.setChannelName(partnerMap.get(sts.getChannel()));
			}
		}
		return SUCCESS;
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	private List<Partner> partnerList;

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	public List<UserRemainByChannelStats> getStatsList() {
		return statsList;
	}

	public void setStatsList(List<UserRemainByChannelStats> statsList) {
		this.statsList = statsList;
	}


}
