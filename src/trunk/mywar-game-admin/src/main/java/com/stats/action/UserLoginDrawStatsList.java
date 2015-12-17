package com.stats.action;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserLoginDrawStats;
import com.stats.service.UserLoginDrawService;

public class UserLoginDrawStatsList extends ALDAdminStatsDatePageActionSupport {

	private List<UserLoginDrawStats> userLoginDrawStatsList = new ArrayList<UserLoginDrawStats>();
	
	public List<UserLoginDrawStats> getUserLoginDrawStatsList() {
		return userLoginDrawStatsList;
	}

	public void setUserLoginDrawStatsList(
			List<UserLoginDrawStats> userLoginDrawStatsList) {
		this.userLoginDrawStatsList = userLoginDrawStatsList;
	}

	public String execute() {
		IPage<UserLoginDrawStats> page = null;
		
		UserLoginDrawService service = ServiceCacheFactory.getServiceCache().getService(UserLoginDrawService.class);
		
		// 判断是否是条件查询
		if (getStartDate() == null && getEndDate() == null) {
			page = service.findList(DEFAULT_PAGESIZE, super.getToPage());
		} else {
			page = service.findListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
		}

		if (page != null) {
			userLoginDrawStatsList = (List<UserLoginDrawStats>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
}
