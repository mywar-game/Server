package com.admin.action;

import java.util.List;

import com.admin.bo.AdminPowerPhysics;
import com.admin.bo.AdminPysicsModule;
import com.admin.service.AdminPowerPhysicsService;
import com.admin.service.AdminPysicsModuleService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class EditAdminPowerPhysics extends ALDAdminActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String actionName;
	private String isCommit = "F";
	private String moduleName;
	private Integer moduleId;
	private String rootName;
	private Integer orderId = 100000;
	private List<AdminPysicsModule> rootList;

	public String execute() {
		AdminPowerPhysicsService aps = ServiceCacheFactory.getServiceCache()
				.getService(AdminPowerPhysicsService.class);
		AdminPysicsModuleService ams = ServiceCacheFactory.getServiceCache()
				.getService(AdminPysicsModuleService.class);
		rootList = ams.findAllModuleList();
		if (id == null)
			return ERROR;
		AdminPowerPhysics ap = aps.findOneAdminPowerPhysics(id);
		if (isCommit.equals("F")) {
			name = ap.getName();
			actionName = ap.getActionName();
			moduleId = ap.getModuleId();
			moduleName = ap.getModuleName();
			orderId = ap.getOrderId();
		}
		if (isCommit.equals("T")) {
			String[] str = rootName.split(",");

			String parentId = str[0];
			String parentName = str[1];
			Integer moduleId = Integer.valueOf(parentId);
			AdminPowerPhysics updateAp = new AdminPowerPhysics(name,
					actionName, 0, orderId, moduleId, parentName, null);
			updateAp.setId(id);
			aps.updatePowerPhysicsService(updateAp);

			return "results";
		}
		return SUCCESS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<AdminPysicsModule> getRootList() {
		return rootList;
	}

	public void setRootList(List<AdminPysicsModule> rootList) {
		this.rootList = rootList;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}
}
