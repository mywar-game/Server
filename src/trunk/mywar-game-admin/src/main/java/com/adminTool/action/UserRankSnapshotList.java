package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.UserRankSnapshot;
import com.adminTool.service.UserRankSnapshotService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;

public class UserRankSnapshotList extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = -5783800809574920322L;
	
	private List<UserRankSnapshot> userRankSnapshotList;
	
	public String execute(){
		if (super.getErroCodeNum() != SystemConstant.SUCCESS_CODE) {
			return SUCCESS;
		}
		UserRankSnapshotService userRankSnapshotService = ServiceCacheFactory.getServiceCache().getService(UserRankSnapshotService.class);
		userRankSnapshotList = userRankSnapshotService.findList();
		return SUCCESS;
	}

	public void setUserRankSnapshotList(List<UserRankSnapshot> userRankSnapshotList) {
		this.userRankSnapshotList = userRankSnapshotList;
	}

	public List<UserRankSnapshot> getUserRankSnapshotList() {
		return userRankSnapshotList;
	}

}
