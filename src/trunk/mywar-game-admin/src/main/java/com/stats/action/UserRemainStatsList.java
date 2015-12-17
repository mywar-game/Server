package com.stats.action;

import java.util.List;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserRemainStats;
import com.stats.service.UserRemainStatsService;

public class UserRemainStatsList extends ALDAdminStatsDatePageActionSupport {
	
	/** * */
	private static final long serialVersionUID = 1L;
	
	private List<UserRemainStats> statsList;
	
	@Override
	public String execute(){
		UserRemainStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserRemainStatsService.class);
		IPage<UserRemainStats> page = null;
		if (getStartDate() != null && getEndDate() != null) {
			page = statsService.findListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate());
		} else {
			page = statsService.findList(super.getPageSize(), super.getToPage());
		}
		if (page != null) {
			statsList = (List<UserRemainStats>)page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public void setStatsList(List<UserRemainStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserRemainStats> getStatsList() {
		return statsList;
	}

}
