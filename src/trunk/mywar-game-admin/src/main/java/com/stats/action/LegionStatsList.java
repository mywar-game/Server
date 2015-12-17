package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.LegionStats;
import com.stats.service.LegionService;

/**
 * 军团统计
 * @author Administrator
 *
 */
public class LegionStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 1L;
	private List<LegionStats> statsList;
	
	public String execute() {

		LegionService legionService = ServiceCacheFactory.getServiceCache().getService(LegionService.class);
		IPage<LegionStats> page = null;
		//判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = legionService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = legionService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<LegionStats>) page.getData();
			
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
	public List<LegionStats> getStatsList() {
		return statsList;
	}
	public void setStatsList(List<LegionStats> statsList) {
		this.statsList = statsList;
	}
}
