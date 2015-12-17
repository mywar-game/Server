package com.admin.action;

import java.util.List;

import com.admin.bo.AdminPowerPhysics;
import com.admin.bo.AdminPysicsModule;
import com.admin.service.AdminPowerPhysicsService;
import com.admin.service.AdminPysicsModuleService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.ErrorCode;
import com.framework.common.SystemCode;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 添加原子操作
 * @author hzy
 * 2012-7-17
 */
public class AddAdminPowerPhysics extends ALDAdminActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String actionName;
	private Integer menuId;
	private String isCommit = "F";
	private String rootName;
	private Integer orderId = 100000;
	private List<AdminPysicsModule> rootList;

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public String execute() {
		AdminPysicsModuleService ams = ServiceCacheFactory.getServiceCache().getService(AdminPysicsModuleService.class);

		rootList = ams.findAllModuleList();
		if (isCommit.equals("T")) {
			AdminPowerPhysicsService app = ServiceCacheFactory.getServiceCache().getService(AdminPowerPhysicsService.class);

			Integer powerId = app.findMaxPowerId() + 1;
			String[] str = rootName.split(",");

			String parentId = str[0];
			String parentName = str[1];
			Integer moduleId = Integer.valueOf(parentId);
			AdminPowerPhysics adminPowerPhysics = new AdminPowerPhysics(name, actionName, powerId, orderId, moduleId, parentName, null);
			ErrorCode error = new ErrorCode();
			app.addPowerPhysicsService(adminPowerPhysics, error);
			if (error.getErrorCode() != SystemCode.SYS_SUCESS) {
				super.setErroDescrip(this.getText(error.getErrorDisc()));
			} else {
				return "results";
			}
		}
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public List<AdminPysicsModule> getRootList() {
		return rootList;
	}

	public void setRootList(List<AdminPysicsModule> rootList) {
		this.rootList = rootList;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}
