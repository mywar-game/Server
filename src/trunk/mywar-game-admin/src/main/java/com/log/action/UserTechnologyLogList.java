package com.log.action;

import java.util.List;
import java.util.Map;

import com.dataconfig.service.BTechnologyConstantService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserTechnologyLog;
import com.log.service.UserTechnologyLogService;

public class UserTechnologyLogList extends ALDAdminLogPageActionSupport {

	/** * */
	private static final long serialVersionUID = -8493541429336775691L;
	
	private List<UserTechnologyLog> userTechnologyLogList;
	
	private Integer searchId;
	
	private Integer searchType;
	
	private Map<Integer, String> technologyIdAndNameMap;

	public String execute(){
		String searchUserId = super.searchUser();
		//搜索玩家的时候出错了，返回
		if (super.getErroDescrip() != null) {
			return SUCCESS;
		}
		
		UserTechnologyLogService userTechnologyLogService = ServiceCacheFactory.getServiceCache().getService(UserTechnologyLogService.class);
		IPage<UserTechnologyLog> list = userTechnologyLogService.findUserTechnologyLogListByCondition(searchUserId, searchId, searchType, super.getStartDate(), super.getEndDate(), super.getToPage(), super.getPageSize());
		
		if (list != null) {
			userTechnologyLogList = (List<UserTechnologyLog>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
			BTechnologyConstantService technologyConstantService = ServiceCacheFactory.getServiceCache().getService(BTechnologyConstantService.class);
			technologyIdAndNameMap = technologyConstantService.findTechnologyIdAndNameMap();
		}
		return SUCCESS;
	}

	public void setUserTechnologyLogList(List<UserTechnologyLog> userTechnologyLogList) {
		this.userTechnologyLogList = userTechnologyLogList;
	}

	public List<UserTechnologyLog> getUserTechnologyLogList() {
		return userTechnologyLogList;
	}

	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}

	public Integer getSearchId() {
		return searchId;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setTechnologyIdAndNameMap(Map<Integer, String> technologyIdAndNameMap) {
		this.technologyIdAndNameMap = technologyIdAndNameMap;
	}

	public Map<Integer, String> getTechnologyIdAndNameMap() {
		return technologyIdAndNameMap;
	}
}
