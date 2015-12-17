package com.log.action;

import java.util.List;

import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserFirendLog;
import com.log.service.UserFirendLogService;

public class UserFirendLogList extends ALDAdminLogPageActionSupport{

	private static final long serialVersionUID = 5540125825034123911L;
	
	private List<UserFirendLog> userFirendLogList;

	public String execute() {
		UserFirendLogService userHeroLogService = ServiceCacheFactory.getServiceCache().getService(UserFirendLogService.class);

		String searchUserId = super.searchUser();
		//搜索玩家的时候出错了，返回
		if (super.getErroDescrip() != null) {
			return SUCCESS;
		}
		
		IPage<UserFirendLog> page = userHeroLogService.findUserFirendLogByCondition(searchUserId, super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (page != null) {
			userFirendLogList = (List<UserFirendLog>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getPageSize());
		}
		
		return SUCCESS;
	}

	public List<UserFirendLog> getUserFirendLogList() {
		return userFirendLogList;
	}

	public void setUserFirendLogList(List<UserFirendLog> userFirendLogList) {
		this.userFirendLogList = userFirendLogList;
	}

}