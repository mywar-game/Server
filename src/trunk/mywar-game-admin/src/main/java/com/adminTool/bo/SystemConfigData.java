package com.adminTool.bo;

/**
 * 系统常量配置表
 * 
 * @author Administrator
 *
 */
public class SystemConfigData {

	private Integer configDataId;
	private String configKey;
	private String configValue;
	private int isSendClient;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SystemConfigData(Integer configDataId, String configKey,
			String configValue, int isSendClient, String description) {
		super();
		this.configDataId = configDataId;
		this.configKey = configKey;
		this.configValue = configValue;
		this.isSendClient = isSendClient;
		this.description = description;
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

	public int getIsSendClient() {
		return isSendClient;
	}

	public void setIsSendClient(int isSendClient) {
		this.isSendClient = isSendClient;
	}

	public SystemConfigData() {

	}

}
