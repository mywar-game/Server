package com.fandingame.game.version.model;

import java.io.Serializable;
import java.util.Date;

public class GameServer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 服务器ID
	 */
	private String serverId;
    /**
     * 合作商id
     */
	private String partenerId;
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
	/**
	 * 真实服务器id
	 */
	private String realServerId;

	public GameServer(String serverId, String serverName, int status) {
		super();
		this.serverId = serverId;
		this.serverName = serverName;
		this.status = status;
	}

	public GameServer(String serverId, String serverName, String serverUrl,
			int serverPort, int status) {
		super();
		this.serverId = serverId;
		this.serverName = serverName;
		this.serverUrl = serverUrl;
		this.serverPort = serverPort;
		this.status = status;
	}

	public GameServer() {
		super();
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
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

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public int getApiPort() {
		return apiPort;
	}
	public void setApiPort(int apiPort) {
		this.apiPort = apiPort;
	}
	public String getPartenerId() {
		return partenerId;
	}

	public void setPartenerId(String partenerId) {
		this.partenerId = partenerId;
	}

	public String getRealServerId() {
		return realServerId;
	}

	public void setRealServerId(String realServerId) {
		this.realServerId = realServerId;
	}
	
}
