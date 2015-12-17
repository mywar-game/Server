package com.dataconfig.action;

import java.util.List;

import com.dataconfig.bo.AActionConstant;
import com.dataconfig.service.AActionConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class AActionConstantList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -3206582330819883237L;

	private List<AActionConstant> actionConstantList;
	
	public String execute() {
		AActionConstantService actionConstantService = ServiceCacheFactory.getServiceCache().getService(AActionConstantService.class);
		IPage<AActionConstant> list = actionConstantService.findAActionConstantPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			actionConstantList = (List<AActionConstant>) list.getData();
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public void setActionConstantList(List<AActionConstant> actionConstantList) {
		this.actionConstantList = actionConstantList;
	}

	public List<AActionConstant> getActionConstantList() {
		return actionConstantList;
	}
}
