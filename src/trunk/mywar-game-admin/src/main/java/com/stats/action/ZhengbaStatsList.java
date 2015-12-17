package com.stats.action;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.ZhengbaStats;
import com.stats.service.ZhengbaStatsService;

public class ZhengbaStatsList extends ALDAdminStatsDatePageActionSupport {

	private List<ZhengbaStats> statsList = new ArrayList<ZhengbaStats>();
	
	public List<ZhengbaStats> getStatsList() {
		return statsList;
	}

	public void setStatsList(List<ZhengbaStats> statsList) {
		this.statsList = statsList;
	}

	public String execute() {
		IPage<ZhengbaStats> page = null;
		
		ZhengbaStatsService zhengbaStatsListService = ServiceCacheFactory.getServiceCache().getService(ZhengbaStatsService.class);
		
		// 判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = zhengbaStatsListService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
				page = zhengbaStatsListService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<ZhengbaStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
}
