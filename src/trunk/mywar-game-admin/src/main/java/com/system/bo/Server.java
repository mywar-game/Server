package com.system.bo;

// default package
// Generated 2014-3-5 11:29:04 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;

/**
 * 修正服务器
 * @author yezp
 */
public class Server implements java.io.Serializable {

	/**
	 * 服务器ID
	 */
	private String serverId;
	
	/**
	 * 服务器名称
	 */
	private String serverName;
	
	/**
	 * 状态
	 */
	private int status;
	
	/**
	 * 合作方id
	 */
	private String partenerId;
	
	/**
	 * 开服时间
	 */
	private Timestamp openTime;
	
	/**
	 * 服务器别名
	 */
	private String aliaServerId;

	public Server() {
	}

	public Server(String serverId, String serverName, int status,
			String partenerId, Timestamp openTime, String aliaServerId) {
		this.serverId = serverId;
		this.serverName = serverName;
		this.status = status;
		this.partenerId = partenerId;
		this.openTime = openTime;
		this.aliaServerId = aliaServerId;
	}

	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPartenerId() {
		return this.partenerId;
	}

	public void setPartenerId(String partenerId) {
		this.partenerId = partenerId;
	}

	public Timestamp getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Timestamp openTime) {
		this.openTime = openTime;
	}

	public String getAliaServerId() {
		return this.aliaServerId;
	}

	public void setAliaServerId(String aliaServerId) {
		this.aliaServerId = aliaServerId;
	}

}
