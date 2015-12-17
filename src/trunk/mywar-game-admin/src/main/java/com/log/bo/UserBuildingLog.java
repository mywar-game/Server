package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserBuildingLog entity. @author MyEclipse Persistence Tools
 */

public class UserBuildingLog implements java.io.Serializable {

	// Fields

	private Integer userBuildingLogId;
	private Long userBuildingId;
	private Long userId;
	private String name;
	private Integer buildingId;
	private String operation;
	private Integer lastLevel;
	private Integer cost;
	private Integer speedSeconds;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserBuildingLog() {
	}

	/** full constructor */
	public UserBuildingLog(Long userBuildingId, Long userId, String name, 
			Integer buildingId, String operation, Integer lastLevel,
			Integer cost, Integer speedSeconds, Date createTime) {
		this.userBuildingId = userBuildingId;
		this.userId = userId;
		this.name = name;
		this.buildingId = buildingId;
		this.operation = operation;
		this.lastLevel = lastLevel;
		this.cost = cost;
		this.speedSeconds = speedSeconds;
		this.createTime = new Timestamp(createTime.getTime());
	}

	// Property accessors

	public Integer getUserBuildingLogId() {
		return this.userBuildingLogId;
	}

	public void setUserBuildingLogId(Integer userBuildingLogId) {
		this.userBuildingLogId = userBuildingLogId;
	}

	public Long getUserBuildingId() {
		return this.userBuildingId;
	}

	public void setUserBuildingId(Long userBuildingId) {
		this.userBuildingId = userBuildingId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getLastLevel() {
		return this.lastLevel;
	}

	public void setLastLevel(Integer lastLevel) {
		this.lastLevel = lastLevel;
	}

	public Integer getCost() {
		return this.cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getSpeedSeconds() {
		return this.speedSeconds;
	}

	public void setSpeedSeconds(Integer speedSeconds) {
		this.speedSeconds = speedSeconds;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}