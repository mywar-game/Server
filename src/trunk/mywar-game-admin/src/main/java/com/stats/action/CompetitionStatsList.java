package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.CompetitionStats;
import com.stats.service.CompetitionService;

/**
 * 天界统计
 * @author Administrator
 *
 */
public class CompetitionStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 1L;
	private List<CompetitionStats> statsList;
	
	public String execute() {

		CompetitionService competitionService = ServiceCacheFactory.getServiceCache().getService(CompetitionService.class);
		IPage<CompetitionStats> page = null;
		//判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = competitionService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = competitionService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<CompetitionStats>) page.getData();
			
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
	public List<CompetitionStats> getStatsList() {
		return statsList;
	}
	public void setStatsList(List<CompetitionStats> statsList) {
		this.statsList = statsList;
	}
}
