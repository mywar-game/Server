package com.fantingame.game.gateway.server.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserInfo {
	// 用户ID
	private String userId;
	// 对应的socket管道id
	private String sessionId;
	// 用户键值对信息
	private Map<String, Object> userAttributeMap = new HashMap<String, Object>();
	// 用户类型（0普通用户，1匿名拥护，2机器人)
	private UserType userType;
	// 用户角色名称 不是账号
	public static final String USER_NAME = "USER_NAME";
	// 用户性别
	public static final String USER_SEX = "USER_SEX";
	// 用户区域id
	public static final String USER_AREA = "USER_AREA";
	// 用户头像id
	public static final String USER_IMAGE = "USER_IMAGE";
	// 用户上次登出时间
	public static final String USER_LAST_LOGINOUT_TIME = "USER_LAST_LOGINOUT_TIME";
	// 用户的消息序列
	private AtomicInteger sequense;

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 添加属性
	 * 
	 * @param key
	 * @param value
	 */
	public void addAttribute(String key, Object value) {
		userAttributeMap.put(key, value);
	}

	/**
	 * 获取属性
	 * 
	 * @param key
	 * @return
	 */
	public Object getAttribute(String key) {
		return userAttributeMap.get(key);
	}

	public UserInfo(String sessionId, String userId, UserType userType) {
		super();
		this.sessionId = sessionId;
		this.userId = userId;
		this.userType = userType;
		this.sequense = new AtomicInteger(0);
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public AtomicInteger getSequense() {
		return sequense;
	}

	public void setSequense(AtomicInteger sequense) {
		this.sequense = sequense;
	}

	public UserInfo() {
		super();
	}

}
