package com.admin.action;

import java.util.List;
import java.util.Map;

import com.admin.bo.AdminUser;
import com.admin.service.AdminRoleService;
import com.admin.service.AdminUserService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class AdminUserList extends ALDAdminPageActionSupport {
	
	/** * */
	private static final long serialVersionUID = 1L;
	
	private List<AdminUser> adminList;
	
	private Map<Integer, String> roleIdAndNameMap;

	public String execute() {
		AdminRoleService adminRoleService = ServiceCacheFactory.getServiceCache().getService(AdminRoleService.class);
		roleIdAndNameMap = adminRoleService.findIdAndNameMap();
		
		AdminUserService aus = ServiceCacheFactory.getServiceCache().getService(AdminUserService.class);
		IPage<AdminUser> page = aus.findAdminUserList(super.getToPage(), 10);
		if (page != null) {
			adminList = (List<AdminUser>) page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}

	public void setAdminList(List<AdminUser> adminList) {
		this.adminList = adminList;
	}

	public List<AdminUser> getAdminList() {
		return adminList;
	}

	public void setRoleIdAndNameMap(Map<Integer, String> roleIdAndNameMap) {
		this.roleIdAndNameMap = roleIdAndNameMap;
	}

	public Map<Integer, String> getRoleIdAndNameMap() {
		return roleIdAndNameMap;
	}
}
