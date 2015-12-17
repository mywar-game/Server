package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.EquipStats;
import com.stats.service.EquipService;

/**
 * 战魂统计
 * @author Administrator
 *
 */
public class EquipStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 1L;
	private List<EquipStats> statsList;
	
	public String execute() {

		EquipService equipService = ServiceCacheFactory.getServiceCache().getService(EquipService.class);
		IPage<EquipStats> page = null;
		//判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = equipService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = equipService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<EquipStats>) page.getData();
			
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
	public List<EquipStats> getStatsList() {
		return statsList;
	}
	public void setStatsList(List<EquipStats> statsList) {
		this.statsList = statsList;
	}
}
