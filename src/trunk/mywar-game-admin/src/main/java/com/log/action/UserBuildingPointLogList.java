package com.log.action;

import java.util.List;

import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserBuildingPointLog;
import com.log.service.UserBuildingPointLogService;

public class UserBuildingPointLogList extends ALDAdminLogPageActionSupport {

	/** * */
	private static final long serialVersionUID = -1616183839362978919L;
	
	private List<UserBuildingPointLog> logList;
	
	private Integer searchCategory;
	
	private Integer searchType;
	
	public String execute() {
		UserBuildingPointLogService logService = ServiceCacheFactory.getServiceCache().getService(UserBuildingPointLogService.class);
		String searchUserId = super.searchUser();
		//搜索玩家的时候出错了，返回
		if (super.getErroDescrip() != null) {
			return SUCCESS;
		}
		IPage<UserBuildingPointLog> pageList = logService.findPageList(searchUserId, searchCategory, searchType, super.getStartDate(), super.getEndDate(), super.getPageSize(), super.getToPage());
		if (pageList != null) {
			logList = (List<UserBuildingPointLog>)pageList.getData();
			super.setTotalPage(pageList.getTotalPage());
			super.setTotalSize(pageList.getTotalSize());
		}
		return SUCCESS;
	}

	public void setLogList(List<UserBuildingPointLog> logList) {
		this.logList = logList;
	}

	public List<UserBuildingPointLog> getLogList() {
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
