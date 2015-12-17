package com.adminTool.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.bo.SystemConfigData;
import com.adminTool.service.AdminChangeConstantLogService;
import com.adminTool.service.SystemConfigDataService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class ModifySystemConfigData extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1801514521907542532L;

	private Integer configDataId;

	private String configKey;

	private String configValue;

	private int isSendClient;

	private String description;

	private String isCommit1 = "F";

	private SystemConfigData configData = new SystemConfigData();

	public SystemConfigData getConfigData() {
		return configData;
	}

	public void setConfigData(SystemConfigData configData) {
		this.configData = configData;
	}

	public String getIsCommit1() {
		return isCommit1;
	}

	public void setIsCommit1(String isCommit1) {
		this.isCommit1 = isCommit1;
	}

	public int getIsSendClient() {
		return isSendClient;
	}

	public void setIsSendClient(int isSendClient) {
		this.isSendClient = isSendClient;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String execute() {
		SystemConfigDataService service = ServiceCacheFactory.getServiceCache()
				.getService(SystemConfigDataService.class);
		SystemConfigData s = new SystemConfigData();
		s.setConfigDataId(configDataId);
		s.setConfigKey(configKey);
		s.setConfigValue(configValue);
		s.setIsSendClient(isSendClient);
		s.setDescription(description);

		service.update(s);
		configData = service.findByConfigDataId(configDataId);
		//
		isCommit1 = "T";

		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(Integer.valueOf(super.getAdminUser()
				.getExp1()));
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName(configData.getConfigKey());
		adminChangeConstantLog.setChangeType(3);
		adminChangeConstantLog.setChangeDetail("修改"
				+ super.getText("配置键:" + configData.getConfigKey())
				+ " 配置值(修改为):" + configData.getConfigValue() + " 记录<br/>");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
				.getServiceCache().getService(
						AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);

		return SUCCESS;
	}

	public Integer getConfigDataId() {
		return configDataId;
	}

	public void setConfigDataId(Integer configDataId) {
		this.configDataId = configDataId;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
}
