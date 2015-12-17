package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserFirendLog entity. @author MyEclipse Persistence Tools
 */

public class UserFirendLog implements java.io.Serializable {

	// Fields

	private Integer userFirendLogId;
	private Long operrationUserId;
	private Long beInvitedUserId;
	private Integer operationType;
	private Timestamp operationTime;
	private String userName;
	private String name;

	// Constructors

	/** default constructor */
	public UserFirendLog() {
	}

	/** full constructor */
	public UserFirendLog(Long operrationUserId, Long beInvitedUserId, 
			Integer operationType, Date operationTime, String userName , String name) {
		this.operrationUserId = operrationUserId;
		this.beInvitedUserId = beInvitedUserId;
		this.operationType = operationType;
		this.operationTime = new Timestamp(operationTime.getTime());
		this.userName = userName;
		this.name = name;
	}

	// Property accessors

	public Integer getUserFirendLogId() {
		return this.userFirendLogId;
	}

	public void setUserFirendLogId(Integer userFirendLogId) {
		this.userFirendLogId = userFirendLogId;
	}

	public Long getOperrationUserId() {
		return this.operrationUserId;
	}

	public void setOperrationUserId(Long operrationUserId) {
		this.operrationUserId = operrationUserId;
	}

	public Long getBeInvitedUserId() {
		return this.beInvitedUserId;
	}

	public void setBeInvitedUserId(Long beInvitedUserId) {
		this.beInvitedUserId = beInvitedUserId;
	}

	public Integer getOperationType() {
		return this.operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public Timestamp getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Timestamp operationTime) {
		this.operationTime = operationTime;
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