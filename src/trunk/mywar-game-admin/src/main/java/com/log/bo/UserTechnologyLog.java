package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserTechnologyLog entity. @author MyEclipse Persistence Tools
 */

public class UserTechnologyLog implements java.io.Serializable {

	// Fields

	private Integer userTechnologyLogId;
	private Integer technologyId;
	private Integer technologyLevel;
	private Long userId;
	private String name;
	private Integer type;
	private Integer costType;
	private Integer costNum;
	private Integer speedSeconds;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserTechnologyLog() {
	}

	/** full constructor */
	public UserTechnologyLog(Integer technologyId, Integer technologyLevel,
			Long userId, String name, Integer type, Integer costType, Integer costNum,
			Integer speedSeconds, Date createTime) {
		this.technologyId = technologyId;
		this.technologyLevel = technologyLevel;
		this.userId = userId;
		this.name = name;
		this.type = type;
		this.costType = costType;
		this.costNum = costNum;
		this.speedSeconds = speedSeconds;
		this.createTime = new Timestamp(createTime.getTime());
	}

	// Property accessors

	public Integer getUserTechnologyLogId() {
		return this.userTechnologyLogId;
	}

	public void setUserTechnologyLogId(Integer userTechnologyLogId) {
		this.userTechnologyLogId = userTechnologyLogId;
	}

	public Integer getTechnologyId() {
		return this.technologyId;
	}

	public void setTechnologyId(Integer technologyId) {
		this.technologyId = technologyId;
	}

	public Integer getTechnologyLevel() {
		return this.technologyLevel;
	}

	public void setTechnologyLevel(Integer technologyLevel) {
		this.technologyLevel = technologyLevel;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCostType() {
		return this.costType;
	}

	public void setCostType(Integer costType) {
		this.costType = costType;
	}

	public Integer getCostNum() {
		return this.costNum;
	}

	public void setCostNum(Integer costNum) {
		this.costNum = costNum;
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