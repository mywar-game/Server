package com.system.bo;

import java.util.Date;

/**
 * 命令
 * 
 * @author huangwx
 */
public class Command implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int gameWebId;
	private String serverId;
	private int currentCmd;
	private int currentCmdStatus;
	private String execInfo;
	private Date createdTime;

	public Command() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(int gameWebId) {
		this.gameWebId = gameWebId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public int getCurrentCmd() {
		return currentCmd;
	}

	public void setCurrentCmd(int currentCmd) {
		this.currentCmd = currentCmd;
	}

	public int getCurrentCmdStatus() {
		return currentCmdStatus;
	}

	public void setCurrentCmdStatus(int currentCmdStatus) {
		this.currentCmdStatus = currentCmdStatus;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getExecInfo() {
		return execInfo;
	}

	public void setExecInfo(String execInfo) {
		this.execInfo = execInfo;
	}



}
