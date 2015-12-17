package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserSexStats;
import com.stats.service.UserSexStatsService;

public class UserSexStatsList extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = 4936046129995788031L;

	private List<UserSexStats> statsList;
	
	public String execute() {
		UserSexStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserSexStatsService.class);
		statsList = statsService.findList();
		return SUCCESS;
	}

	public void setStatsList(List<UserSexStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserSexStats> getStatsList() {
		return statsList;
	}
}
