package com.dataconfig.bo;

import java.sql.Time;
import java.util.Date;

/**
 * MMultipleActivityConfig entity. @author MyEclipse Persistence Tools
 */

public class MMultipleActivityConfig implements java.io.Serializable {

	// Fields

	private Integer configId;
	private Integer activityType;
	private Integer multiple;
	private Date startDate;
	private Date endDate;
	private Time startTime;
	private Time endTime;
	private Integer userLevelLimit;

	// Constructors

	/** default constructor */
	public MMultipleActivityConfig() {
	}

	/** full constructor */
	public MMultipleActivityConfig(Integer activityType, Integer multiple,
			Date startDate, Date endDate, Time startTime, Time endTime,
			Integer userLevelLimit) {
		this.activityType = activityType;
		this.multiple = multiple;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.userLevelLimit = userLevelLimit;
	}

	// Property accessors

	public Integer getConfigId() {
		return this.configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public Integer getActivityType() {
		return this.activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Integer getMultiple() {
		return this.multiple;
	}

	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Integer getUserLevelLimit() {
		return this.userLevelLimit;
	}

	public void setUserLevelLimit(Integer userLevelLimit) {
		this.userLevelLimit = userLevelLimit;
	}

}