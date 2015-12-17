package com.admin.action;

import com.admin.bo.AdminRole;
import com.admin.service.AdminRoleService;
import com.admin.service.AdminUserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateAdminUserRole extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = -3298563992637559191L;
	
	private Integer roleId;
	
	private Integer adminUserId;
	
	public String execute(){
		AdminRoleService adminRoleService = ServiceCacheFactory.getServiceCache().getService(AdminRoleService.class);
		AdminRole adminRole = adminRoleService.findOneAdminRole(roleId);
		AdminUserService adminUserService = ServiceCacheFactory.getServiceCache().getService(AdminUserService.class);
		adminUserService.updateAdminUser(adminUserId, adminRole.getMenuPowerString(), adminRole.getPowerString(), roleId);
		super.setErroDescrip("修改管理员角色成功！");
		return SUCCESS;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

}
