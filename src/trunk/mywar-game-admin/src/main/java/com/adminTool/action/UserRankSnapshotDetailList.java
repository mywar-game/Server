package com.adminTool.action;

import java.util.List;

import com.admin.util.Tools;
import com.adminTool.bo.UserRankSnapshot;
import com.adminTool.service.UserRankSnapshotService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UserRankSnapshotDetailList extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = -861569923885955281L;

	private String ids;
	
	private List<UserRankSnapshot> userRankSnapshotList;

	public String execute(){
		if (!Tools.isEmpty(ids)) {
			UserRankSnapshotService userRankSnapshotService = ServiceCacheFactory.getServiceCache().getService(UserRankSnapshotService.class);
			userRankSnapshotList = userRankSnapshotService.findListInIds(ids);
		}
		return SUCCESS;
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setUserRankSnapshotList(List<UserRankSnapshot> userRankSnapshotList) {
		this.userRankSnapshotList = userRankSnapshotList;
	}

	public List<UserRankSnapshot> getUserRankSnapshotList() {
		return userRankSnapshotList;
	}
}
