package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserTechnologyPointLog entity. @author MyEclipse Persistence Tools
 */

public class UserTechnologyPointLog implements java.io.Serializable {

	// Fields

	private Integer logId;
	private Long userId;
	private String name;
	private Integer category;
	private Integer type;
	private String note;
	private Integer changeNum;
	private Integer finalNum;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserTechnologyPointLog() {
	}

	/** minimal constructor */
	public UserTechnologyPointLog(Long userId, Integer category, Integer type,
			Integer changeNum, Integer finalNum, Timestamp createTime) {
		this.userId = userId;
		this.category = category;
		this.type = type;
		this.changeNum = changeNum;
		this.finalNum = finalNum;
		this.createTime = createTime;
	}

	/** full constructor */
	public UserTechnologyPointLog(Long userId, String name, Integer category, Integer type,
			String note, Integer changeNum, Integer finalNum,
			Date createTime) {
		this.userId = userId;
		this.name = name;
		this.category = category;
		this.type = type;
		this.note = note;
		this.changeNum = changeNum;
		this.finalNum = finalNum;
		this.createTime = new Timestamp(createTime.getTime());
	}

	// Property accessors

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getChangeNum() {
		return this.changeNum;
	}

	public void setChangeNum(Integer changeNum) {
		this.changeNum = changeNum;
	}

	public Integer getFinalNum() {
		return this.finalNum;
	}

	public void setFinalNum(Integer finalNum) {
		this.finalNum = finalNum;
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