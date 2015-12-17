package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserNpcLog entity. @author MyEclipse Persistence Tools
 */

public class UserNpcLog implements java.io.Serializable {

	// Fields

	private Integer userNpcLogId;
	private Long userId;
	private Integer npcId;
	private Integer buildingId;
	private Long userBuildingId;
	private Integer position;
	private String operation;
	private Timestamp createTime;
	private String userName;
	private String name;
	private String buildingName;
	private String npcName;

	// Constructors

	/** default constructor */
	public UserNpcLog() {
	}

	/** full constructor */
	public UserNpcLog(Long userId, Integer npcId, Integer buildingId,
			Long userBuildingId, Integer position, String operation,
			Date createTime, String userName, String name) {
		this.userId = userId;
		this.npcId = npcId;
		this.buildingId = buildingId;
		this.userBuildingId = userBuildingId;
		this.position = position;
		this.operation = operation;
		this.createTime = new Timestamp(createTime.getTime());
		this.userName = userName;
		this.name = name;
	}

	// Property accessors

	public Integer getUserNpcLogId() {
		return this.userNpcLogId;
	}

	public void setUserNpcLogId(Integer userNpcLogId) {
		this.userNpcLogId = userNpcLogId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getNpcId() {
		return this.npcId;
	}

	public void setNpcId(Integer npcId) {
		this.npcId = npcId;
	}

	public Integer getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Long getUserBuildingId() {
		return this.userBuildingId;
	}

	public void setUserBuildingId(Long userBuildingId) {
		this.userBuildingId = userBuildingId;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setNpcName(String npcName) {
		this.npcName = npcName;
	}

	public String getNpcName() {
		return npcName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingName() {
		return buildingName;
	}

}