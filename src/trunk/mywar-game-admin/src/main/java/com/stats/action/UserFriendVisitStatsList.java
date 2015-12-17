package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserFriendVisitStats;
import com.stats.service.UserFriendVisitStatsService;

public class UserFriendVisitStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = -9126031535877424479L;

	private List<UserFriendVisitStats> statsList;

	public String execute() {
		UserFriendVisitStatsService userFriendVisitStatsService = ServiceCacheFactory.getServiceCache().getService(UserFriendVisitStatsService.class);
		
		IPage<UserFriendVisitStats> page = null;
		
		//判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = userFriendVisitStatsService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = userFriendVisitStatsService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<UserFriendVisitStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public void setStatsList(List<UserFriendVisitStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserFriendVisitStats> getStatsList() {
		return statsList;
	}
}
