package com.system.bo;

import java.sql.Timestamp;

/**
 * TGameServer entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TGameServer implements java.io.Serializable {

	// Fields

	private Integer serverId;
	private String serverName;
	private String serverAlias;
	private String serverIp;
	private Integer serverPoint;
	private Integer webOpenPort;
	private String webServerPath;
	private String serverCommunicationKey;
	private Timestamp serverOpernTime;
	private String gameServerUrl;
	private Integer chatServerId;
	private Integer battleServerId;
	private Integer orderServerId;
	private Integer logDbServerCode;
	private Integer configDbServerCode;
	private Integer userDbServerCode;
	private Integer cacheDbServerCode;
	private Integer mongoServerCode;
	private String loginEncryption;
	private String timeZoneId;
	private Integer official;
	private Integer gameWebServerId;

	@Override
	public String toString() {
		return "serverId = " + this.serverId + " serverName = "
				+ this.serverName + " serverAlias = " + this.serverAlias;
	}

	// Constructors

	/** default constructor */
	public TGameServer() {
	}

	/** minimal constructor */
	public TGameServer(String serverName, String serverAlias, String serverIp,
			Integer serverPoint, Integer webOpenPort, String webServerPath,
			String serverCommunicationKey, Timestamp serverOpernTime,
			String gameServerUrl, Integer chatServerId, Integer battleServerId,
			Integer orderServerId, Integer configDbServerCode,
			Integer mongoServerCode, String loginEncryption, String timeZoneId,
			Integer official, Integer userDbServerCode) {
		this.serverName = serverName;
		this.serverAlias = serverAlias;
		this.serverIp = serverIp;
		this.serverPoint = serverPoint;
		this.webOpenPort = webOpenPort;
		this.webServerPath = webServerPath;
		this.serverCommunicationKey = serverCommunicationKey;
		this.serverOpernTime = serverOpernTime;
		this.gameServerUrl = gameServerUrl;
		this.chatServerId = chatServerId;
		this.battleServerId = battleServerId;
		this.orderServerId = orderServerId;
		this.configDbServerCode = configDbServerCode;
		this.mongoServerCode = mongoServerCode;
		this.loginEncryption = loginEncryption;
		this.timeZoneId = timeZoneId;
		this.official = official;
		this.userDbServerCode = userDbServerCode;
	}

	/** full constructor */
	public TGameServer(String serverName, String serverAlias, String serverIp,
			Integer serverPoint, Integer webOpenPort, String webServerPath,
			String serverCommunicationKey, Timestamp serverOpernTime,
			String gameServerUrl, Integer chatServerId, Integer battleServerId,
			Integer orderServerId, Integer logDbServerCode,
			Integer configDbServerCode, Integer cacheDbServerCode,
			Integer mongoServerCode, String loginEncryption, String timeZoneId,
			Integer official, Integer gameWebServerId, Integer userDbServerCode) {
		this.serverName = serverName;
		this.serverAlias = serverAlias;
		this.serverIp = serverIp;
		this.serverPoint = serverPoint;
		this.webOpenPort = webOpenPort;
		this.webServerPath = webServerPath;
		this.serverCommunicationKey = serverCommunicationKey;
		this.serverOpernTime = serverOpernTime;
		this.gameServerUrl = gameServerUrl;
		this.chatServerId = chatServerId;
		this.battleServerId = battleServerId;
		this.orderServerId = orderServerId;
		this.logDbServerCode = logDbServerCode;
		this.configDbServerCode = configDbServerCode;
		this.cacheDbServerCode = cacheDbServerCode;
		this.mongoServerCode = mongoServerCode;
		this.loginEncryption = loginEncryption;
		this.timeZoneId = timeZoneId;
		this.official = official;
		this.gameWebServerId = gameWebServerId;
		this.userDbServerCode = userDbServerCode;
	}

	// Property accessors

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

	public String getServerAlias() {
		return this.serverAlias;
	}

	public void setServerAlias(String serverAlias) {
		this.serverAlias = serverAlias;
	}

	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Integer getServerPoint() {
		return this.serverPoint;
	}

	public void setServerPoint(Integer serverPoint) {
		this.serverPoint = serverPoint;
	}

	public Integer getWebOpenPort() {
		return this.webOpenPort;
	}

	public void setWebOpenPort(Integer webOpenPort) {
		this.webOpenPort = webOpenPort;
	}

	public String getWebServerPath() {
		return this.webServerPath;
	}

	public void setWebServerPath(String webServerPath) {
		this.webServerPath = webServerPath;
	}

	public String getServerCommunicationKey() {
		return this.serverCommunicationKey;
	}

	public void setServerCommunicationKey(String serverCommunicationKey) {
		this.serverCommunicationKey = serverCommunicationKey;
	}

	public Timestamp getServerOpernTime() {
		return this.serverOpernTime;
	}

	public void setServerOpernTime(Timestamp serverOpernTime) {
		this.serverOpernTime = serverOpernTime;
	}

	public String getGameServerUrl() {
		return this.gameServerUrl;
	}

	public void setGameServerUrl(String gameServerUrl) {
		this.gameServerUrl = gameServerUrl;
	}

	public Integer getChatServerId() {
		return this.chatServerId;
	}

	public void setChatServerId(Integer chatServerId) {
		this.chatServerId = chatServerId;
	}

	public Integer getBattleServerId() {
		return this.battleServerId;
	}

	public void setBattleServerId(Integer battleServerId) {
		this.battleServerId = battleServerId;
	}

	public Integer getOrderServerId() {
		return this.orderServerId;
	}

	public void setOrderServerId(Integer orderServerId) {
		this.orderServerId = orderServerId;
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

	public Integer getMongoServerCode() {
		return this.mongoServerCode;
	}

	public void setMongoServerCode(Integer mongoServerCode) {
		this.mongoServerCode = mongoServerCode;
	}

	public String getLoginEncryption() {
		return this.loginEncryption;
	}

	public void setLoginEncryption(String loginEncryption) {
		this.loginEncryption = loginEncryption;
	}

	public String getTimeZoneId() {
		return this.timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public Integer getOfficial() {
		return official;
	}

	public void setOfficial(Integer official) {
		this.official = official;
	}

	public Integer getGameWebServerId() {
		return gameWebServerId;
	}

	public void setGameWebServerId(Integer gameWebServerId) {
		this.gameWebServerId = gameWebServerId;
	}

	public Integer getUserDbServerCode() {
		return userDbServerCode;
	}

	public void setUserDbServerCode(Integer userDbServerCode) {
		this.userDbServerCode = userDbServerCode;
	}

	public String getGameServerHttpUrl() {
		return "http://" + serverIp + ":" + webOpenPort + "/" + webServerPath
				+ "/";
	}

}