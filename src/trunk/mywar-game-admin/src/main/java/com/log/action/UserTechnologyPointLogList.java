package com.log.action;

import java.util.List;

import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserTechnologyPointLog;
import com.log.service.UserTechnologyPointLogService;

public class UserTechnologyPointLogList extends ALDAdminLogPageActionSupport {

	/** * */
	private static final long serialVersionUID = -2188775296581445226L;

	private List<UserTechnologyPointLog> logList;
	
	private Integer searchCategory;
	
	private Integer searchType;
	
	public String execute() {
		UserTechnologyPointLogService logService = ServiceCacheFactory.getServiceCache().getService(UserTechnologyPointLogService.class);
		String searchUserId = super.searchUser();
		//搜索玩家的时候出错了，返回
		if (super.getErroDescrip() != null) {
			return SUCCESS;
		}
		IPage<UserTechnologyPointLog> pageList = logService.findPageList(searchUserId, searchCategory, searchType, super.getStartDate(), super.getEndDate(), super.getPageSize(), super.getToPage());
		if (pageList != null) {
			logList = (List<UserTechnologyPointLog>)pageList.getData();
			super.setTotalPage(pageList.getTotalPage());
			super.setTotalSize(pageList.getTotalSize());
		}
		return SUCCESS;
	}

	public void setLogList(List<UserTechnologyPointLog> logList) {
		this.logList = logList;
	}

	public List<UserTechnologyPointLog> getLogList() {
		return logList;
	}

	public void setSearchCategory(Integer searchCategory) {
		this.searchCategory = searchCategory;
	}

	public Integer getSearchCategory() {
		return searchCategory;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public Integer getSearchType() {
		return searchType;
	}
}
