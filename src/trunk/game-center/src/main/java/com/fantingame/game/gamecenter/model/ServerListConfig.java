package com.fantingame.game.gamecenter.model;

import java.util.Date;

public class ServerListConfig {
	/**
	 * 与game_server表关联的字段
	 */
	private String serverId;
	/**
	 * 服务器id别名 服务器id别名(有可能s2服为91渠道的s1服) 用于客户端显示的字段
	 */
	private String aliaServerId;
    /**
     * 合作商id
     */
	private String partenerId;
	/**
	 * 服务器名称
	 */
	private String serverName;
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
	public String getPartenerId() {
		return partenerId;
	}
	public void setPartenerId(String partenerId) {
		this.partenerId = partenerId;
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
	public String getAliaServerId() {
		return aliaServerId;
	}
	public void setAliaServerId(String aliaServerId) {
		this.aliaServerId = aliaServerId;
	}
	
}
