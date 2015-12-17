package com.admin.action;

import com.admin.bo.AdminRole;
import com.admin.service.AdminRoleService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateAdminRole extends ALDAdminActionSupport implements ModelDriven<AdminRole> {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private AdminRole adminRole = new AdminRole();
	
	private String isCommit = "F";
	
	public String execute(){
		AdminRoleService adminRoleService = ServiceCacheFactory.getServiceCache().getService(AdminRoleService.class);
		if ("F".equals(isCommit)) {
			adminRole = adminRoleService.findOneAdminRole(adminRole.getRoleId());
			return INPUT;
		}
		adminRoleService.saveOrUpdateAdminRole(adminRole);
		return SUCCESS;
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

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}
	

}
