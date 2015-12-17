package com.log.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dataconfig.service.BBuildingConstantService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserBuildingLog;
import com.log.service.UserBuildingLogService;

public class UserBuildingLogList extends ALDAdminLogPageActionSupport {
	
	private static final long serialVersionUID = 4233539363568281308L;

	private List<UserBuildingLog> userBuildingLogList = new ArrayList<UserBuildingLog>();
	
	/**   */
	private String searchOperation;
	
	private Map<Integer, String> buildingIDNameMap;
	
	private Integer searchBuildingId;

	@Override
	public String execute() throws Exception {
		UserBuildingLogService userBuildingLogService = ServiceCacheFactory.getServiceCache().getService(UserBuildingLogService.class);
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);

		String searchUserId = super.searchUser();
		//搜索玩家的时候出错了，返回
		if (super.getErroDescrip() != null) {
			return SUCCESS;
		}
		
		IPage<UserBuildingLog> list = userBuildingLogService.getUserBuildingLogPageList(searchUserId, searchBuildingId, searchOperation, super.getToPage(), super.getPageSize());
		
		if (list != null) {
			userBuildingLogList = (List<UserBuildingLog>)list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
			buildingIDNameMap = buildingConstantService.findBuildingIDNameMap();
		}
		return SUCCESS;
	}

	public List<UserBuildingLog> getUserBuildingLogList() {
		return userBuildingLogList;
	}

	public void setUserBuildingLogList(List<UserBuildingLog> userBuildingLogList) {
		this.userBuildingLogList = userBuildingLogList;
	}

	/**
	 * @return the searchOperation
	 */
	public String getSearchOperation() {
		return searchOperation;
	}

	/**
	 * @param searchOperation the searchOperation to set
	 */
	public void setSearchOperation(String searchOperation) {
		this.searchOperation = searchOperation;
	}

	public void setBuildingIDNameMap(Map<Integer, String> buildingIDNameMap) {
		this.buildingIDNameMap = buildingIDNameMap;
	}

	public Map<Integer, String> getBuildingIDNameMap() {
		return buildingIDNameMap;
	}

	public void setSearchBuildingId(Integer searchBuildingId) {
		this.searchBuildingId = searchBuildingId;
	}

	public Integer getSearchBuildingId() {
		return searchBuildingId;
	}
}