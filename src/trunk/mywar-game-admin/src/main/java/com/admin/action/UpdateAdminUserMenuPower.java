package com.admin.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.admin.bo.AdminMenu;
import com.admin.bo.AdminPowerPhysics;
import com.admin.bo.AdminUser;
import com.admin.service.AdminMenuService;
import com.admin.service.AdminUserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateAdminUserMenuPower extends ALDAdminActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	
	private AdminUser adminUser;
	
	private String isCommit = "F";
	
	private Map<String, List<AdminPowerPhysics>> map;
	
	private Integer[] powerList;
	
	private List<AdminMenu> menuList;
	
	private String treeStr = "";
	
	private String checked = "";
	
	private String powerListChecked;
	
	private String powerListUnChecked;

	public String execute() {
		AdminUserService aus = ServiceCacheFactory.getServiceCache().getService(AdminUserService.class);
		if (isCommit.equals("F")) {
			AdminMenuService adminMenuService = ServiceCacheFactory.getServiceCache().getService(AdminMenuService.class);
			menuList = adminMenuService.findAllList();
			adminUser = aus.findOneAdmin(Id);
			makeTree(0);
		}
		if (isCommit.equals("T")) {
			
			String[] powerListCheckedListStr = powerListChecked.split(",");
			String[] powerListUnCheckedListStr = powerListUnChecked.split(",");
			
			String power = AdminUserService.INITAL_MENU_POWER;
			char[] a = power.toCharArray();
			int position = -1;
//			if (powerList != null) {
//				for (int i = 0; i < powerList.length; i++) {
//					position = powerList[i] - 1;
//					a[position] = '1';
//				}
//			}
			
			if (powerListCheckedListStr != null) {
				for (int i = 0; i < powerListCheckedListStr.length; i++) {
					position = Integer.valueOf(powerListCheckedListStr[i]) - 1;
					a[position] = '1';
				}
			}
			
			if (powerListUnCheckedListStr != null) {
				for (int i = 0; i < powerListUnCheckedListStr.length; i++) {
					position = Integer.valueOf(powerListUnCheckedListStr[i]) - 1;
					a[position] = '0';
				}
			}
			
			// 
			if (powerListCheckedListStr == null && powerListUnCheckedListStr != null) {
				return "result";
			}
			power = String.valueOf(a);
			aus.updateAdminUserMenuPowerService(Id, power);
			return "result";
		}
		System.out.println(treeStr);
		return SUCCESS;
	}

	public void makeTree(int parentId) {
		List<AdminMenu> childList = findChild(parentId);
		treeStr = treeStr + "<table>";
		for (AdminMenu tempg : childList) {
			checked = "";
			if (adminUser.getMenuPowerString().charAt(tempg.getPowerId() - 1) == '1') {
				checked = "checked";
			}
			treeStr = treeStr + "<tr>";
			treeStr = treeStr + "<td>--" + tempg.getMenuName()
					+ "<input name = 'powerList'"
					+ " type = 'checkbox' class = 'checkbox' value = '"
					+ tempg.getPowerId() + "' " + checked + "/>";
			if (!tempg.getIfLeaf().equals("T")) {
				treeStr = treeStr + ("<td>");
				makeTree(tempg.getId());
				treeStr = treeStr + ("</td>");
			}
			treeStr = treeStr + ("</tr>");
		}
		treeStr = treeStr + ("</table>");
	}

	public List<AdminMenu> findChild(int parentId) {
		List<AdminMenu> tempList = new ArrayList<AdminMenu>();
		for (AdminMenu tempg : menuList) {
			if (tempg.getParentId() == parentId) {
				tempList.add(tempg);
			}
		}
		return tempList;
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

	public List<AdminMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<AdminMenu> menuList) {
		this.menuList = menuList;
	}

	public String getTreeStr() {
		return treeStr;
	}

	public void setTreeStr(String treeStr) {
		this.treeStr = treeStr;
	}

	public String getPowerListChecked() {
		return powerListChecked;
	}

	public void setPowerListChecked(String powerListChecked) {
		this.powerListChecked = powerListChecked;
	}

	public String getPowerListUnChecked() {
		return powerListUnChecked;
	}

	public void setPowerListUnChecked(String powerListUnChecked) {
		this.powerListUnChecked = powerListUnChecked;
	}

}
