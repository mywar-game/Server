package com.admin.action;

import com.admin.bo.AdminRole;
import com.admin.service.AdminRoleService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelAdminRole extends ALDAdminActionSupport implements ModelDriven<AdminRole> {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private AdminRole adminRole = new AdminRole();
	
	public void executeDel(){
		AdminRoleService adminRoleService = ServiceCacheFactory.getServiceCache().getService(AdminRoleService.class);
		adminRoleService.delOneAdminRole(adminRole);
	}

	@Override
	public AdminRole getModel() {
		return adminRole;
	}

	public void setAdminRole(AdminRole adminRole) {
		this.adminRole = adminRole;
	}

	public AdminRole getAdminRole() {
		return adminRole;
	}

}
