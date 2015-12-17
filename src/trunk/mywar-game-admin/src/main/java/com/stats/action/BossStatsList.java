package com.stats.action;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.BossStats;
import com.stats.service.BossStatsService;

/**
 * boss 战统计
 * @author Administrator
 *
 */
public class BossStatsList  extends ALDAdminStatsDatePageActionSupport {

	List<BossStats> statsList = new ArrayList<BossStats>();
	
	public List<BossStats> getStatsList() {
		return statsList;
	}

	public void setStatsList(List<BossStats> statsList) {
		this.statsList = statsList;
	}

	public String execute() {
		
		IPage<BossStats> page = null;
		
		BossStatsService bossStatsService = ServiceCacheFactory.getServiceCache().getService(BossStatsService.class);
		
		// 判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = bossStatsService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
				page = bossStatsService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<BossStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
}
