package com.admin.action;

import java.util.List;
import java.util.Map;

import com.admin.bo.AdminPowerPhysics;
import com.admin.bo.AdminRole;
import com.admin.service.AdminPowerPhysicsService;
import com.admin.service.AdminRoleService;
import com.admin.service.AdminUserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateAdminRolePhysicsPower extends ALDAdminActionSupport implements ModelDriven<AdminRole> {

	/** * */
	private static final long serialVersionUID = 1L;

	private AdminRole adminRole = new AdminRole();
	
	private String isCommit = "F";
	
	private Map<String, List<AdminPowerPhysics>> map;
	
	private Integer[] powerIdArr;
	
	public String execute(){
		AdminRoleService adminRoleService = ServiceCacheFactory.getServiceCache().getService(AdminRoleService.class);
		adminRole = adminRoleService.findOneAdminRole(adminRole.getRoleId());
		if ("F".equals(isCommit)) {
			AdminPowerPhysicsService apps = ServiceCacheFactory.getServiceCache().getService(AdminPowerPhysicsService.class);
			map = apps.findAllPowerPhysicsMap();
			return INPUT;
		}
		String powerString = AdminUserService.INITAL_POWER;
		char[] a = powerString.toCharArray();
		int position = -1;
		if (powerIdArr != null) {
			for (int i = 0; i < powerIdArr.length; i++) {
				position = powerIdArr[i] - 1;
				a[position] = '1';
			}
		}
		powerString = String.valueOf(a);
		
		adminRole.setPowerString(powerString);
		adminRoleService.saveOrUpdateAdminRole(adminRole);
		System.out.println(powerString);
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

	public void setMap(Map<String, List<AdminPowerPhysics>> map) {
		this.map = map;
	}

	public Map<String, List<AdminPowerPhysics>> getMap() {
		return map;
	}

	public void setPowerIdArr(Integer[] powerIdArr) {
		this.powerIdArr = powerIdArr;
	}

	public Integer[] getPowerIdArr() {
		return powerIdArr;
	}

}
