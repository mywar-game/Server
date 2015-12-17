package com.admin.action;

import java.util.List;
import java.util.Map;

import com.admin.bo.AdminPowerPhysics;
import com.admin.bo.AdminUser;
import com.admin.service.AdminPowerPhysicsService;
import com.admin.service.AdminUserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateAdminUserPower extends ALDAdminActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	
	private AdminUser adminUser;
	
	private String isCommit = "F";
	
	private Map<String, List<AdminPowerPhysics>> map;
	
	private Integer[] powerList;

	public String execute() {
		AdminUserService aus = ServiceCacheFactory.getServiceCache().getService(AdminUserService.class);
		if (isCommit.equals("F")) {
			adminUser = aus.findOneAdmin(Id);
			AdminPowerPhysicsService apps = ServiceCacheFactory.getServiceCache().getService(AdminPowerPhysicsService.class);
			map = apps.findAllPowerPhysicsMap();
		}

		if (isCommit.equals("T")) {
			String power = AdminUserService.INITAL_POWER;
			char[] a = power.toCharArray();
			int position = -1;
			if (powerList != null) {
				for (int i = 0; i < powerList.length; i++) {
					position = powerList[i] - 1;
					a[position] = '1';
				}
			}
			power = String.valueOf(a);
			aus.updateAdminUserPowerService(Id, power);
			return "result";
		}
		return SUCCESS;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Integer[] getPowerList() {
		return powerList;
	}

	public void setPowerList(Integer[] powerList) {
		this.powerList = powerList;
	}

	public Map<String, List<AdminPowerPhysics>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<AdminPowerPhysics>> map) {
		this.map = map;
	}
}
