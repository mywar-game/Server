package com.admin.action;

import java.util.List;
import java.util.Map;

import com.admin.bo.AdminPowerPhysics;
import com.admin.service.AdminPowerPhysicsService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class PowerPhysicsList extends ALDAdminActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, List<AdminPowerPhysics>> map;
	private String isCommit = "F";

	public Map<String, List<AdminPowerPhysics>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<AdminPowerPhysics>> map) {
		this.map = map;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String execute() {
		if (isCommit.equals("F")) {
			AdminPowerPhysicsService apps = ServiceCacheFactory
					.getServiceCache().getService(
							AdminPowerPhysicsService.class);
			map = apps.findAllPowerPhysicsMap();
		}
		return SUCCESS;
	}
}
