package com.admin.action;

import java.util.List;
import java.util.Map;

import com.admin.bo.AdminMenu;
import com.admin.bo.AdminRole;
import com.admin.service.AdminMenuService;
import com.admin.service.AdminRoleService;
import com.admin.service.AdminUserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateAdminRoleMenuPower extends ALDAdminActionSupport implements ModelDriven<AdminRole> {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private AdminRole adminRole = new AdminRole();
	
	private String isCommit = "F";
	
	private Map<Integer,List<AdminMenu>> map;
	
	private Integer[] powerIdArr;
	
	public String execute(){
		AdminRoleService adminRoleService = ServiceCacheFactory.getServiceCache().getService(AdminRoleService.class);
		adminRole = adminRoleService.findOneAdminRole(adminRole.getRoleId());
		if ("F".equals(isCommit)) {
			AdminMenuService adminMenuService = ServiceCacheFactory.getServiceCache().getService(AdminMenuService.class);
			map = adminMenuService.findOneToTwoMap();
			return INPUT;
		}
		
		String menuPowerString = AdminUserService.INITAL_MENU_POWER;
		char[] a = menuPowerString.toCharArray();
		int position = -1;
		if (powerIdArr != null) {
			for (int i = 0; i < powerIdArr.length; i++) {
				position = powerIdArr[i] - 1;
				a[position] = '1';
			}
		}
		menuPowerString = String.valueOf(a);
		
		adminRole.setMenuPowerString(menuPowerString);
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

	public void setMap(Map<Integer,List<AdminMenu>> map) {
		this.map = map;
	}

	public Map<Integer,List<AdminMenu>> getMap() {
		return map;
	}

	public void setPowerIdArr(Integer[] powerIdArr) {
		this.powerIdArr = powerIdArr;
	}

	public Integer[] getPowerIdArr() {
		return powerIdArr;
	}

}
