package com.adminTool.action;

import com.admin.util.Tools;
import com.adminTool.service.UserRankSnapshotService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class DelUserRankSnapshot extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = -6499762598837582465L;
	
	private String ids;
	
	public void executeDel() {
		UserRankSnapshotService userRankSnapshotService = ServiceCacheFactory.getServiceCache().getService(UserRankSnapshotService.class);
		if (!Tools.isEmpty(ids)) {
			userRankSnapshotService.delUserRankSnapshot(ids);
		}
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

}
