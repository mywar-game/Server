package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemMall;
import com.adminTool.service.SystemMallService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class SystemMallList extends ALDAdminActionSupport {

	private static final long serialVersionUID = 7916796845937969915L;
	List<SystemMall> systemMallList = new ArrayList<SystemMall>();
	
	public List<SystemMall> getSystemMallList() {
		return systemMallList;
	}

	public void setSystemMallList(List<SystemMall> systemMallList) {
		this.systemMallList = systemMallList;
	}

	@Override
	public String execute() {
		
		SystemMallService systemMallService = ServiceCacheFactory.getServiceCache().getService(SystemMallService.class);
		systemMallList = systemMallService.getSystemMallList();
		
		return SUCCESS;
	}
}
