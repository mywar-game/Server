package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserEquipStats;
import com.stats.service.UserEquipStatsService;

public class UserEquipStatsList extends ALDAdminStatsDatePageActionSupport {
	
	private static final long serialVersionUID = 0L;
	
	private List<UserEquipStats> statsList;
	private Integer equipType = 0;
	private Integer total = 0;
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String execute() {
		UserEquipStatsService userEquipStatsService = ServiceCacheFactory.getServiceCache().getService(UserEquipStatsService.class);
		IPage<UserEquipStats> page = null;
		if (total == 1) {
			if (getStartDate() == null && getEndDate() == null) {
				statsList = userEquipStatsService.findListTotal(equipType);
			} else {
				statsList = userEquipStatsService.findListInDateTotal(getStartDate(), getEndDate(), equipType);
			}

		} else {
			// 判断是否是条件查询
			if (getStartDate() == null && getEndDate() == null) {
				page = userEquipStatsService.findList(DEFAULT_PAGESIZE, super.getToPage(), equipType);
			} else {
				page = userEquipStatsService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate(), equipType);
			}

			if (page != null) {
				statsList = (List<UserEquipStats>) page.getData();
				super.setTotalPage(page.getTotalPage());
				super.setTotalSize(page.getTotalSize());
			}
		}
		return SUCCESS;
	}

	public void setStatsList(List<UserEquipStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserEquipStats> getStatsList() {
		return statsList;
	}

	public Integer getEquipType() {
		return equipType;
	}

	public void setEquipType(Integer equipType) {
		this.equipType = equipType;
	}

}
