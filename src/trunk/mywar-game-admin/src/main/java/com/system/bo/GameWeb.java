package com.system.bo;

/**
 * 区服务器
 * 
 * @author yezp
 */
public class GameWeb implements java.io.Serializable {

	private Integer serverId;
	private String serverName;
	private String serverIp;
	private int serverPort;
	private String dbIp;
	private int dbPort;
	private String dbName;
	private String userName;
	private String password;
	private String giftDbIp;
	private int giftDbPort;
	private String giftDbName;
	private String giftUserName;
	private String giftPassword;

	private Integer openStatus;

	public GameWeb() {
	}

	public GameWeb(String serverName, String serverIp, int serverPort,
			String dbIp, int dbPort, String dbName, String userName,
			String password) {
		this.serverName = serverName;
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.dbIp = dbIp;
		this.dbPort = dbPort;
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
	}

	public Integer getServerId() {
		return this.serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
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

	public int getServerPort() {
		return this.serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getDbIp() {
		return this.dbIp;
	}

	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}

	public int getDbPort() {
		return this.dbPort;
	}

	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
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

	public String getGiftDbIp() {
		return giftDbIp;
	}

	public void setGiftDbIp(String giftDbIp) {
		this.giftDbIp = giftDbIp;
	}

	public int getGiftDbPort() {
		return giftDbPort;
	}

	public void setGiftDbPort(int giftDbPort) {
		this.giftDbPort = giftDbPort;
	}

	public String getGiftDbName() {
		return giftDbName;
	}

	public void setGiftDbName(String giftDbName) {
		this.giftDbName = giftDbName;
	}

	public String getGiftUserName() {
		return giftUserName;
	}

	public void setGiftUserName(String giftUserName) {
		this.giftUserName = giftUserName;
	}

	public String getGiftPassword() {
		return giftPassword;
	}

	public void setGiftPassword(String giftPassword) {
		this.giftPassword = giftPassword;
	}

	public Integer getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}

}
