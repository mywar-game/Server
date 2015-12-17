package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemConfigData;
import com.adminTool.service.SystemConfigDataService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 系统常量表
 * @author Administrator
 *
 */
public class UpdateSystemConfigData extends ALDAdminActionSupport {

	private static final long serialVersionUID = 8319061490035038278L;
	private List<SystemConfigData> systemConfigDataList = new ArrayList<SystemConfigData>();
	private SystemConfigData configData = new SystemConfigData();
	
	private String isCommit = "F";
	private Integer configDataId;
	
	@Override
	public String execute() {
		
		SystemConfigDataService service = ServiceCacheFactory.getServiceCache().getService(SystemConfigDataService.class);
		if (isCommit == "T" || isCommit.equalsIgnoreCase("T")) {
			configData = service.findByConfigDataId(configDataId);
			return INPUT;
		} else {
			systemConfigDataList = service.getList();
			return SUCCESS;
		}
	}
	
	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Integer getConfigDataId() {
		return configDataId;
	}

	public void setConfigDataId(Integer configDataId) {
		this.configDataId = configDataId;
	}

	public List<SystemConfigData> getSystemConfigDataList() {
		return systemConfigDataList;
	}

	public void setSystemConfigDataList(List<SystemConfigData> systemConfigDataList) {
		this.systemConfigDataList = systemConfigDataList;
	}
	public SystemConfigData getConfigData() {
		return configData;
	}

	public void setConfigData(SystemConfigData configData) {
		this.configData = configData;
	}

}
