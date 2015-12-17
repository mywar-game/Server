package com.system.bo;

/**
 * 服务器列表状态
 * 
 * @author yezp
 */
public class GameServerStatus implements java.io.Serializable {

	private String serverId;
	private int status;
	private String notice;

	public GameServerStatus() {
	}

	public GameServerStatus(String serverId, int status, String notice) {
		this.serverId = serverId;
		this.status = status;
		this.notice = notice;
	}

	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

}
