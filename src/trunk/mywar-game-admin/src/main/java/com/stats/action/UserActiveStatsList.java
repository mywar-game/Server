package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserActiveStats;
import com.stats.service.UserActiveStatsService;

/**
 * 用户活跃统计列表
 * @author hzy
 * 2012-4-13
 */
public class UserActiveStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 8512255406577231020L;

	private List<UserActiveStats> statsList;
	
	public String execute() {
		UserActiveStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserActiveStatsService.class);
		IPage<UserActiveStats> page = null;
		
		//判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = statsService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = statsService.findListInDate(DEFAULT_PAGESIZE, super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<UserActiveStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
	
	public void setStatsList(List<UserActiveStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserActiveStats> getStatsList() {
		return statsList;
	}
}
