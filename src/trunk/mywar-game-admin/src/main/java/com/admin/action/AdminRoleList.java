package com.admin.action;

import java.util.List;

import com.admin.bo.AdminRole;
import com.admin.service.AdminRoleService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class AdminRoleList extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = 1216940349219468681L;

	private List<AdminRole> adminRoleList;
	
	public String execute(){
		AdminRoleService adminRoleService = ServiceCacheFactory.getServiceCache().getService(AdminRoleService.class);
		adminRoleList = adminRoleService.findAdminRoleList();
		return SUCCESS;
	}
	
	public void setAdminRoleList(List<AdminRole> adminRoleList) {
		this.adminRoleList = adminRoleList;
	}

	public List<AdminRole> getAdminRoleList() {
		return adminRoleList;
	}

}
