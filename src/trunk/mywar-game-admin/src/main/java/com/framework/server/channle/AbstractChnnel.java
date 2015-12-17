package com.framework.server.channle;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractChnnel implements Channel {

	// 用户系列号
	public static final String USER_SEQUENSE = "USER_SEQUENSE";

	// 用户id
	public static final String USER_ID = "USER_ID";
	
	private Map<String, Object> attrMap = new HashMap<String, Object>();

	private int protocolType;

	private int clientType;
	
	public int getClientType() {
		return clientType;
	}

	public void setClientType(int clientType) {
		this.clientType = clientType;
	}

	public int getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(int protocolType) {
		this.protocolType = protocolType;
	}

	public void addAttribute(String key, Object value) {
		attrMap.put(key, value);
	}

	public Object getAttribute(String key) {
		return attrMap.get(key);
	}

}
