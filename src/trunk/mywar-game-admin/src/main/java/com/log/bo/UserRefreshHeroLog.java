package com.log.bo;

import java.sql.Timestamp;

/**
 * UserRefreshHeroLog entity. @author MyEclipse Persistence Tools
 */

public class UserRefreshHeroLog implements java.io.Serializable {

	// Fields

	private Integer userRefreshHeroLogId;
	private Long userId;
	private Integer type;
	private Integer commonQualityNum;
	private Integer excellentQualityNum;
	private Integer eliteQualityNum;
	private Integer superQualityNum;
	private Timestamp refreshTime;

	// Constructors

	/** default constructor */
	public UserRefreshHeroLog() {
	}

	/** full constructor */
	public UserRefreshHeroLog(Long userId, Integer type,
			Integer commonQualityNum, Integer excellentQualityNum,
			Integer eliteQualityNum, Integer superQualityNum,
			Timestamp refreshTime) {
		this.userId = userId;
		this.type = type;
		this.commonQualityNum = commonQualityNum;
		this.excellentQualityNum = excellentQualityNum;
		this.eliteQualityNum = eliteQualityNum;
		this.superQualityNum = superQualityNum;
		this.refreshTime = refreshTime;
	}

	// Property accessors

	public Integer getUserRefreshHeroLogId() {
		return this.userRefreshHeroLogId;
	}

	public void setUserRefreshHeroLogId(Integer userRefreshHeroLogId) {
		this.userRefreshHeroLogId = userRefreshHeroLogId;
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

	public Integer getCommonQualityNum() {
		return this.commonQualityNum;
	}

	public void setCommonQualityNum(Integer commonQualityNum) {
		this.commonQualityNum = commonQualityNum;
	}

	public Integer getExcellentQualityNum() {
		return this.excellentQualityNum;
	}

	public void setExcellentQualityNum(Integer excellentQualityNum) {
		this.excellentQualityNum = excellentQualityNum;
	}

	public Integer getEliteQualityNum() {
		return this.eliteQualityNum;
	}

	public void setEliteQualityNum(Integer eliteQualityNum) {
		this.eliteQualityNum = eliteQualityNum;
	}

	public Integer getSuperQualityNum() {
		return this.superQualityNum;
	}

	public void setSuperQualityNum(Integer superQualityNum) {
		this.superQualityNum = superQualityNum;
	}

	public Timestamp getRefreshTime() {
		return this.refreshTime;
	}

	public void setRefreshTime(Timestamp refreshTime) {
		this.refreshTime = refreshTime;
	}

}