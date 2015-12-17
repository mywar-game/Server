package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserRefreshHeroStats;
import com.stats.service.UserRefreshHeroStatsService;

public class UserRefreshHeroStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 1L;

	private List<UserRefreshHeroStats> statsList;

	public String execute() {
		UserRefreshHeroStatsService userRefreshHeroStatsService = ServiceCacheFactory.getServiceCache().getService(UserRefreshHeroStatsService.class);
		
		IPage<UserRefreshHeroStats> page = null;
		
		//判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = userRefreshHeroStatsService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = userRefreshHeroStatsService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<UserRefreshHeroStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public void setStatsList(List<UserRefreshHeroStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserRefreshHeroStats> getStatsList() {
		return statsList;
	}
}
