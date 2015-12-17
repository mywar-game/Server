package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserCopperStats;
import com.stats.service.UserCopperStatsService;

public class UserCopperStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 6017932378499671516L;

	private List<UserCopperStats> statsList;
	
	private String isCommit = "F";

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String execute() {
//		System.out.println("DEFAULT_PAGESIZE   "+DEFAULT_PAGESIZE);
		UserCopperStatsService userCopperStatsService = ServiceCacheFactory.getServiceCache().getService(UserCopperStatsService.class);
		IPage<UserCopperStats> page = null;
		
		if (isCommit != null && isCommit == "TF" || isCommit.equalsIgnoreCase("TF")) {
			isCommit = "F";
		}
		//判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = userCopperStatsService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = userCopperStatsService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<UserCopperStats>) page.getData();
//			for (UserCopperStats s : statsList) {
//				
//			}
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public void setStatsList(List<UserCopperStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserCopperStats> getStatsList() {
		return statsList;
	}

}
