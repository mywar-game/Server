package com.fantingame.game.gamecenter.model;

import java.util.Date;


public class ServerConfig {
	/**
	 * 服务器id
	 */
	private String serverId;
	/**
	 * 服务器名称
	 */
	private String serverName;
    /**
     * 服务器地址
     */
	private String serverUrl;
	/**
	 * 服务器端口号
	 */
	private int serverPort;
	/**
	 * api开放的端口号
	 */
	private int apiPort;
	/**
	 * 服务器状态
	 */
	private int status;
	/**
	 * 开放时间
	 */
	private Date openTime;
	
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public int getApiPort() {
		return apiPort;
	}
	public void setApiPort(int apiPort) {
		this.apiPort = apiPort;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	
}
