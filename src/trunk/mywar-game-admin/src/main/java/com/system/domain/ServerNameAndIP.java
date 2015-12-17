package com.system.domain;

import java.io.Serializable;

public class ServerNameAndIP implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String serverName;
	
	private String serverIP;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

}
