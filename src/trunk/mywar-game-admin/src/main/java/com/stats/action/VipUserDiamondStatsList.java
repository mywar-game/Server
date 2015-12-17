package com.stats.action;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.VipUserDiamondStats;
import com.stats.service.VipUserDiamondStatsService;

public class VipUserDiamondStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 1L;
	private List<VipUserDiamondStats> statsList = new ArrayList<VipUserDiamondStats>();
	private String isCommit = "F";
	
	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public List<VipUserDiamondStats> getStatsList() {
		return statsList;
	}

	public void setStatsList(List<VipUserDiamondStats> statsList) {
		this.statsList = statsList;
	}

	@Override
	public String execute() {
		
		VipUserDiamondStatsService service =  ServiceCacheFactory.getServiceCache().getService(VipUserDiamondStatsService.class);
		int type = 2;
		if (isCommit.equals("T")) {
			type = 1;
		}
		IPage<VipUserDiamondStats> page = null;
		if (getStartDate() != null && getEndDate() != null) {
			page = service.findListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate(), type);
		} else {
			page = service.findList(super.getPageSize(), super.getToPage(), type);
		}
		if (page != null) {
			statsList = (List<VipUserDiamondStats>)page.getData();
		}
		super.setTotalPage(page.getTotalPage());
		super.setTotalSize(page.getTotalSize());
		return SUCCESS;
	}
}
