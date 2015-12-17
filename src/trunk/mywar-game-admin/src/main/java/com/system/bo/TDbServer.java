package com.system.bo;

/**
 * TDbServer entity. @author MyEclipse Persistence Tools
 */

public class TDbServer implements java.io.Serializable {

	// Fields

	private Integer dbServerId;
	private Integer serverType;
	private String serverName;
	private Integer serverPort;
	private String serverIp;
	private String dbName;
	private String userName;
	private String password;

	// Constructors

	/** default constructor */
	public TDbServer() {
	}

	/** full constructor */
	public TDbServer(Integer serverType, String serverName, Integer serverPort,
			String serverIp, String dbName, String userName, String password) {
		this.serverType = serverType;
		this.serverName = serverName;
		this.serverPort = serverPort;
		this.serverIp = serverIp;
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
	}

	// Property accessors

	public Integer getDbServerId() {
		return this.dbServerId;
	}

	public void setDbServerId(Integer dbServerId) {
		this.dbServerId = dbServerId;
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

	public Integer getServerPort() {
		return this.serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getDbName() {
		return this.dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}