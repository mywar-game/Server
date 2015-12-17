package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserChatLog entity. @author MyEclipse Persistence Tools
 */

public class UserChatLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8075825415346307261L;
	private Integer userChatId;
	private Integer chatType;
	private String chatMessage;
	private Long chatObject;
	private Timestamp chatTime;
	private Integer systemNum;
	private String userName;
	private String name;

	// Constructors

	/** default constructor */
	public UserChatLog() {
	}

	/** minimal constructor */
	public UserChatLog(Integer chatType, Date chatTime, Integer systemNum) {
		this.chatType = chatType;
		this.chatTime = new Timestamp(chatTime.getTime());
		this.systemNum = systemNum;
	}

	/** full constructor */
	public UserChatLog(Integer chatType, String chatMessage, Long chatObject,
			Date chatTime, Integer systemNum,String userName ,String name) {
		this.chatType = chatType;
		this.chatMessage = chatMessage;
		this.chatObject = chatObject;
		this.chatTime = new Timestamp(chatTime.getTime());
		this.systemNum = systemNum;
		this.userName = userName;
		this.name = name;
	}

	// Property accessors

	public Integer getUserChatId() {
		return this.userChatId;
	}

	public void setUserChatId(Integer userChatId) {
		this.userChatId = userChatId;
	}

	public Integer getChatType() {
		return this.chatType;
	}

	public void setChatType(Integer chatType) {
		this.chatType = chatType;
	}

	public String getChatMessage() {
		return this.chatMessage;
	}

	public void setChatMessage(String chatMessage) {
		this.chatMessage = chatMessage;
	}

	public Long getChatObject() {
		return this.chatObject;
	}

	public void setChatObject(Long chatObject) {
		this.chatObject = chatObject;
	}

	public Timestamp getChatTime() {
		return this.chatTime;
	}

	public void setChatTime(Timestamp chatTime) {
		this.chatTime = chatTime;
	}

	public Integer getSystemNum() {
		return this.systemNum;
	}

	public void setSystemNum(Integer systemNum) {
		this.systemNum = systemNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}