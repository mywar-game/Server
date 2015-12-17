package com.stats.action;

import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserDiamondStats;
import com.stats.service.UserDiamondStatsService;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class UserDiamondStatsList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 6017932378499671516L;

	private List<UserDiamondStats> statsList;
	//private java.math.BigDecimal leftDiamond; // 当前剩余钻石数
	private String isCommit = "F";

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String execute() {
//		System.out.println("DEFAULT_PAGESIZE   "+DEFAULT_PAGESIZE);
		UserDiamondStatsService userDiamondStatsService = ServiceCacheFactory.getServiceCache().getService(UserDiamondStatsService.class);
		IPage<UserDiamondStats> page = null;
		
		if (isCommit != null && isCommit == "TF" || isCommit.equalsIgnoreCase("TF")) {
			isCommit = "F";
		}
		//判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = userDiamondStatsService.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = userDiamondStatsService.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			statsList = (List<UserDiamondStats>) page.getData();
			for (UserDiamondStats stats : statsList) {
				int sysNum = stats.getSysNum();
				TGameServer tGameServer = DataSourceManager.getInstatnce().getGameServerMap().get(sysNum);
				stats.setServerName(tGameServer.getServerAlias());
			}
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public void setStatsList(List<UserDiamondStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserDiamondStats> getStatsList() {
		return statsList;
	}

//	public java.math.BigDecimal getLeftDiamond() {
//		return leftDiamond;
//	}
//
//	public void setLeftDiamond(java.math.BigDecimal leftDiamond) {
//		this.leftDiamond = leftDiamond;
//	}


}
