package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserRemainByIdfaStats;
import com.stats.service.UserRemainByIdfaStatsService;


public class UserRemainStatsByIdfaList extends ALDAdminStatsDatePageActionSupport {
	
	/** * */
	private static final long serialVersionUID = 1L;
	
	private List<UserRemainByIdfaStats> statsList;
	
	public List<UserRemainByIdfaStats> getStatsList() {
		return statsList;
	}

	public void setStatsList(List<UserRemainByIdfaStats> statsList) {
		this.statsList = statsList;
	}

	@Override
	public String execute(){
		UserRemainByIdfaStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserRemainByIdfaStatsService.class);
		IPage<UserRemainByIdfaStats> page = null;
		if (getStartDate() != null && getEndDate() != null) {
			page = statsService.findListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate());
		} else {
			page = statsService.findList(super.getPageSize(), super.getToPage());
		}
		if (page != null) {
			statsList = (List<UserRemainByIdfaStats>)page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
}
