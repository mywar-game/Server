package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserIpStats;
import com.stats.service.UserIPStatsService;

public class UserIpStatsList extends ALDAdminPageActionSupport {

	/**  */
	private static final long serialVersionUID = -9212979589370839032L;
	
	private List<UserIpStats> statsList;
	
	public String execute() {
		UserIPStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserIPStatsService.class);
		IPage<UserIpStats> page = statsService.findList(DEFAULT_PAGESIZE, super.getToPage());

		if (page != null) {
			statsList = (List<UserIpStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public void setStatsList(List<UserIpStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserIpStats> getStatsList() {
		return statsList;
	}

}
