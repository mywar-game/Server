package com.stats.bo;

import java.sql.Timestamp;

public class PaymentLogStats implements java.io.Serializable,Comparable<PaymentLogStats>{

	private Integer id;
	private String userId;
	private String userName;
	private Timestamp lastCreateTime;
	private Double totalAmount;
	private String partnerId;
	private String partnerName;
	private String serverId;
	
	public PaymentLogStats(){

	}

	public PaymentLogStats(Integer id, String userId, String userName,
			Timestamp lastCreateTime, Double totalAmount, String partnerId,
			String partnerName,String serverId) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.lastCreateTime = lastCreateTime;
		this.totalAmount = totalAmount;
		this.partnerId = partnerId;
		this.partnerName = partnerName;
		this.serverId = serverId;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
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

	public Timestamp getLastCreateTime() {
		return lastCreateTime;
	}

	public void setLastCreateTime(Timestamp lastCreateTime) {
		this.lastCreateTime = lastCreateTime;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	@Override
	public int compareTo(PaymentLogStats pOrder) {
		
		if(this.totalAmount>pOrder.totalAmount)
			return -1;
		else if(this.totalAmount<pOrder.totalAmount)
			return 1;
		return 0;
	}
	
	
	
}
