package com.fantingame.game.common.model;



public class SystemConfigData{

	/**
	 * 配置key
	 */
	private String configKey;

	/**
	 * 配置值
	 */
	private String configValue;
	
	private int isSendClient;

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

}
