package com.stats.action;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.PlunderStats;
import com.stats.service.PlunderStatsService;


public class PlunderStatsList extends ALDAdminStatsDatePageActionSupport {

	private List<PlunderStats> statsList = new ArrayList<PlunderStats>();
	
	public List<PlunderStats> getStatsList() {
		return statsList;
	}

	public void setStatsList(List<PlunderStats> statsList) {
		this.statsList = statsList;
	}

	public String execute() {
		IPage<PlunderStats> page = null;
		
		PlunderStatsService plunderStatsListService = ServiceCacheFactory.getServiceCache().getService(PlunderStatsService.class);
		
		// 判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = plunderStatsListService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
				page = plunderStatsListService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<PlunderStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
}
