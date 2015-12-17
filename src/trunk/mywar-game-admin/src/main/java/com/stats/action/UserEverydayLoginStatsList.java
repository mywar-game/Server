package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserEverydayLoginStats;
import com.stats.service.UserEverydayLoginStatsService;

/**
 * 玩家每天登入统计列表
 * @author hzy
 * 2012-4-12
 */
public class UserEverydayLoginStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 7858449551882224646L;
	
	private List<UserEverydayLoginStats> statsList;
	
	public String execute() {
		UserEverydayLoginStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserEverydayLoginStatsService.class);
		
		IPage<UserEverydayLoginStats> page = null;
		
		//判断是否是条件查询
		if (getStartDate() != null && getEndDate() != null) {
			page = statsService.findListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate());
		} else {
			page = statsService.findList(super.getPageSize(), super.getToPage());
		}

		if (page != null) {
			statsList = (List<UserEverydayLoginStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public void setStatsList(List<UserEverydayLoginStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserEverydayLoginStats> getStatsList() {
		return statsList;
	}
}
