package com.log.bo;

import java.sql.Timestamp;

/**
 * UserRegLog entity. @author MyEclipse Persistence Tools
 */

public class UserRegLog implements java.io.Serializable {

	// Fields

	private String userId;
	private String userName;
	private String channel;
	private String smallChannel;
	private Timestamp regTime;
	
	private String regIp;
	private String regMac;
	private String regImei;
	private String regIdfa;
	private String regMobile;
	
	private String channelName;

	// Constructors

	public UserRegLog(String userId, String userName, String channel,
			String smallChannel, Timestamp regTime, String regIp,
			String regMac, String regImei, String regIdfa, String regMobile) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.channel = channel;
		this.smallChannel = smallChannel;
		this.regTime = regTime;
		this.regIp = regIp;
		this.regMac = regMac;
		this.regImei = regImei;
		this.regIdfa = regIdfa;
		this.regMobile = regMobile;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String getRegMac() {
		return regMac;
	}

	public void setRegMac(String regMac) {
		this.regMac = regMac;
	}

	public String getRegImei() {
		return regImei;
	}

	public void setRegImei(String regImei) {
		this.regImei = regImei;
	}

	public String getRegIdfa() {
		return regIdfa;
	}

	public void setRegIdfa(String regIdfa) {
		this.regIdfa = regIdfa;
	}

	public String getRegMobile() {
		return regMobile;
	}

	public void setRegMobile(String regMobile) {
		this.regMobile = regMobile;
	}

	/** default constructor */
	public UserRegLog() {
	}

	/** minimal constructor */
	public UserRegLog(Timestamp regTime) {
		this.regTime = regTime;
	}

	/** full constructor */
	public UserRegLog(String userName, String channel, String smallChannel,
			Timestamp regTime) {
		this.userName = userName;
		this.channel = channel;
		this.smallChannel = smallChannel;
		this.regTime = regTime;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSmallChannel() {
		return this.smallChannel;
	}

	public void setSmallChannel(String smallChannel) {
		this.smallChannel = smallChannel;
	}

	public Timestamp getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
}