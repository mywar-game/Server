package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserPayHistoryLog entity. @author MyEclipse Persistence Tools
 */

public class UserPayHistoryLog implements java.io.Serializable {

	// Fields

	private Integer payHistoryId;
	private String userId;
	private Integer userLevel;
	private String orderId;
	private String orderNum;
	private Integer amount;
	private Timestamp payTime;
	
	/**
	 * 玩家角色名
	 */
	private String name;

	// Constructors

	/** default constructor */
	public UserPayHistoryLog() {
	}

	/** full constructor */
	public UserPayHistoryLog(String userId, Integer userLevel, String orderId, String orderNum, Integer amount, Date payTime, String name) {
		this.userId = userId;
		this.userLevel = userLevel;
		this.orderId = orderId;
		this.orderNum = orderNum;
		this.amount = amount;
		this.payTime = new Timestamp(payTime.getTime());
		this.name = name;
	}

	// Property accessors

	public Integer getPayHistoryId() {
		return this.payHistoryId;
	}

	public void setPayHistoryId(Integer payHistoryId) {
		this.payHistoryId = payHistoryId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Timestamp getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	/**
	 * 获取 玩家角色名 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 玩家角色名 
	 */
	public void setName(String name) {
		this.name = name;
	}

}