package com.adminTool.bo;

import java.io.Serializable;
import java.util.Date;

public class UserMapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userMapperId;
	private String userId;
	private String partnerUserId;
	private String partnerId;
	private String serverId;
	private String qn;
	private Date createdTime;
	private Date updatedTime;
	
	
	public UserMapper(int userMapperId, String userId, String partnerUserId,
			String partnerId, String serverId, String qn, Date createdTime,
			Date updatedTime) {
		super();
		this.userMapperId = userMapperId;
		this.userId = userId;
		this.partnerUserId = partnerUserId;
		this.partnerId = partnerId;
		this.serverId = serverId;
		this.qn = qn;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public UserMapper() {
		
	}
	
	public int getUserMapperId() {
		return userMapperId;
	}
	public void setUserMapperId(int userMapperId) {
		this.userMapperId = userMapperId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPartnerUserId() {
		return partnerUserId;
	}
	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getQn() {
		return qn;
	}
	public void setQn(String qn) {
		this.qn = qn;
	}
}
