package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserPayStats;
import com.stats.service.UserPayStatsService;

/**
 * 玩家充值统计
 * @author hzy
 * 2012-4-25
 */
public class UserPayStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 4818554560858440974L;
	
	private List<UserPayStats> statsList;

	public String execute() {
		UserPayStatsService userPayStatsService = ServiceCacheFactory.getServiceCache().getService(UserPayStatsService.class);
		
		IPage<UserPayStats> page = null;
		
		//判断是否是条件查询，按周显示 每页大小
		if (getStartDate() == null && getEndDate() == null) {
			page = userPayStatsService.findPageList(super.getPageSize(), super.getToPage());
		} else {
			page = userPayStatsService.findPageListInDate(super.getPageSize() , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<UserPayStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public List<UserPayStats> getStatsList() {
		return statsList;
	}

	public void setStatsList(List<UserPayStats> statsList) {
		this.statsList = statsList;
	}
}
