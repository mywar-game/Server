package com.system.bo;

/**
 * TSecondaryServer entity. @author MyEclipse Persistence Tools
 */

public class TSecondaryServer implements java.io.Serializable {

	// Fields

	private Integer serverId;
	private Integer serverType;
	private String serverName;
	private String serverIp;
	private Integer serverPort;
	private Integer logDbServerCode;
	private Integer configDbServerCode;
	private Integer cacheDbServerCode;

	private int isBattleServer;
	// Constructors

	/** default constructor */
	public TSecondaryServer() {
	}

	/** minimal constructor */
	public TSecondaryServer(Integer serverType, String serverName,
			String serverIp, Integer serverPort, Integer configDbServerCode) {
		this.serverType = serverType;
		this.serverName = serverName;
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.configDbServerCode = configDbServerCode;
	}

	/** full constructor */
	public TSecondaryServer(Integer serverType, String serverName,
			String serverIp, Integer serverPort, Integer logDbServerCode,
			Integer configDbServerCode, Integer cacheDbServerCode) {
		this.serverType = serverType;
		this.serverName = serverName;
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.logDbServerCode = logDbServerCode;
		this.configDbServerCode = configDbServerCode;
		this.cacheDbServerCode = cacheDbServerCode;
	}

	// Property accessors

	public Integer getServerId() {
		return this.serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public Integer getServerType() {
		return this.serverType;
	}

	public void setServerType(Integer serverType) {
		this.serverType = serverType;
	}

	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Integer getServerPort() {
		return this.serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public Integer getLogDbServerCode() {
		return this.logDbServerCode;
	}

	public void setLogDbServerCode(Integer logDbServerCode) {
		this.logDbServerCode = logDbServerCode;
	}

	public Integer getConfigDbServerCode() {
		return this.configDbServerCode;
	}

	public void setConfigDbServerCode(Integer configDbServerCode) {
		this.configDbServerCode = configDbServerCode;
	}

	public Integer getCacheDbServerCode() {
		return this.cacheDbServerCode;
	}

	public void setCacheDbServerCode(Integer cacheDbServerCode) {
		this.cacheDbServerCode = cacheDbServerCode;
	}

	public int getIsBattleServer() {
		return isBattleServer;
	}

	public void setIsBattleServer(int isBattleServer) {
		this.isBattleServer = isBattleServer;
	}

}