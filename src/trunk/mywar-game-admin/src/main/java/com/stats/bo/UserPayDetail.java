package com.stats.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserPayDetail entity. @author MyEclipse Persistence Tools
 */

public class UserPayDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String channel;
	private String orderId;
	private String userId;
	private Double amount;
	private Timestamp payTime;
	private String userName;
	private Integer userLevel;
	private Timestamp lastLoginTime;
	private Date time;

	// Constructors

	/** default constructor */
	public UserPayDetail() {
	}

	/** full constructor */
	public UserPayDetail(Integer sysNum, String channel, String orderId,
			String userId, Double amount, Timestamp payTime, String userName,
			Integer userLevel, Timestamp lastLoginTime, Date time) {
		this.sysNum = sysNum;
		this.channel = channel;
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
		this.payTime = payTime;
		this.userName = userName;
		this.userLevel = userLevel;
		this.lastLoginTime = lastLoginTime;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Timestamp getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}