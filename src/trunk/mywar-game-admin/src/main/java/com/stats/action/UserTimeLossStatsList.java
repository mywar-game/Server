package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserTimeLossStats;
import com.stats.service.UserTimeLossStatsService;

/**
 * 
 * @author hzy
 * 2012-4-18
 */
public class UserTimeLossStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = -6391272896986436045L;
	
	private List<UserTimeLossStats> statsList;

	public String execute() {
		UserTimeLossStatsService userTimeLossStatsService = ServiceCacheFactory.getServiceCache().getService(UserTimeLossStatsService.class);
		IPage<UserTimeLossStats> page = null;
		//判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = userTimeLossStatsService.findPageList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = userTimeLossStatsService.findPageListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<UserTimeLossStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public void setStatsList(List<UserTimeLossStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserTimeLossStats> getStatsList() {
		return statsList;
	}
}
