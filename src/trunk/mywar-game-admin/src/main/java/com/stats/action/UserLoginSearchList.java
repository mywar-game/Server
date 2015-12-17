package com.stats.action;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserEverydayLoginStats;
import com.stats.service.UserLoginSearchService;

/**
 * 玩家登入实时查询
 * @author Administrator
 *
 */
public class UserLoginSearchList extends ALDAdminStatsDatePageActionSupport  {

	private static final long serialVersionUID = -8307113441131099805L;

	private String beginDate = "";
	private String enDate = "";
	private UserEverydayLoginStats userLoginStats;
	
	@Override
	public String execute() {
		UserLoginSearchService service = ServiceCacheFactory.getServiceCache().getService(UserLoginSearchService.class);
		if (beginDate != "" && enDate != "") {
			userLoginStats = service.find(beginDate, enDate);
		}
		return SUCCESS;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEnDate() {
		return enDate;
	}
	public void setEnDate(String enDate) {
		this.enDate = enDate;
	}
	public UserEverydayLoginStats getUserLoginStats() {
		return userLoginStats;
	}
	public void setUserLoginStats(UserEverydayLoginStats userLoginStats) {
		this.userLoginStats = userLoginStats;
	}
}
