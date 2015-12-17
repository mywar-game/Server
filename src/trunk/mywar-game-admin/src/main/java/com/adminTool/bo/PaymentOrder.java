package com.adminTool.bo;

import java.io.Serializable;
import java.sql.Timestamp;

public class PaymentOrder implements Serializable {
	
	private String userId;
	private String partnerOrderId;
	private String userName;
	private String orderId;
	private Double amount;
	private Integer status;
	private Timestamp createdTime;
	private Integer gameServerId;
	
	public PaymentOrder(String userId, String partnerOrderId, String userName, String orderId,
			Double amount, Integer status, Timestamp createdTime, Integer gameServerId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.orderId = orderId;
		this.amount = amount;
		this.status = status;
		this.createdTime = createdTime;
		this.gameServerId = gameServerId;
	}


	public String getPartnerOrderId() {
		return partnerOrderId;
	}


	public void setPartnerOrderId(String partnerOrderId) {
		this.partnerOrderId = partnerOrderId;
	}


	public PaymentOrder() {
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Timestamp getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}


	public Integer getGameServerId() {
		return gameServerId;
	}


	public void setGameServerId(Integer gameServerId) {
		this.gameServerId = gameServerId;
	}


}
