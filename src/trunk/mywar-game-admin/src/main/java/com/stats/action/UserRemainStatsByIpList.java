package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserRemainByIpStats;
import com.stats.service.UserRemainByIpStatsService;

public class UserRemainStatsByIpList extends ALDAdminStatsDatePageActionSupport {
	
	/** * */
	private static final long serialVersionUID = 1L;
	
	private List<UserRemainByIpStats> statsList;

	public List<UserRemainByIpStats> getStatsList() {
		return statsList;
	}

	public void setStatsList(List<UserRemainByIpStats> statsList) {
		this.statsList = statsList;
	}

	@Override
	public String execute(){
		UserRemainByIpStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserRemainByIpStatsService.class);
		IPage<UserRemainByIpStats> page = null;
		if (getStartDate() != null && getEndDate() != null) {
			page = statsService.findListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate());
		} else {
			page = statsService.findList(super.getPageSize(), super.getToPage());
		}
		if (page != null) {
			statsList = (List<UserRemainByIpStats>)page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
}
