package com.admin.action;

import com.admin.service.AdminUserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateLock extends ALDAdminActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer Id;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String execute() {
		AdminUserService aus = ServiceCacheFactory.getServiceCache()
				.getService(AdminUserService.class);
		aus.updateLock(Id,0);
		return SUCCESS;
	}
}
