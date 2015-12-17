package com.log.action;

import java.util.List;

import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserGenePointLog;
import com.log.service.UserGenePointLogService;

public class UserGenePointLogList extends ALDAdminLogPageActionSupport {

	/** **/
	private static final long serialVersionUID = 1966174943969039935L;
	
	private List<UserGenePointLog> logList;
	
	private Integer searchCategory;
	
	private Integer searchType;
	
	public String execute() {
		UserGenePointLogService logService = ServiceCacheFactory.getServiceCache().getService(UserGenePointLogService.class);
		String searchUserId = super.searchUser();
		//搜索玩家的时候出错了，返回
		if (super.getErroDescrip() != null) {
			return SUCCESS;
		}
		IPage<UserGenePointLog> pageList = logService.findPageList(searchUserId, searchCategory, searchType, super.getStartDate(), super.getEndDate(), super.getPageSize(), super.getToPage());
		if (pageList != null) {
			logList = (List<UserGenePointLog>)pageList.getData();
			super.setTotalPage(pageList.getTotalPage());
			super.setTotalSize(pageList.getTotalSize());
		}
		return SUCCESS;
	}

	public void setLogList(List<UserGenePointLog> logList) {
		this.logList = logList;
	}

	public List<UserGenePointLog> getLogList() {
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
