package com.log.bo;

import java.sql.Timestamp;

/**
 * UserMallLogBak entity. @author MyEclipse Persistence Tools
 */

public class UserMallLogBak implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private Integer userLevel;
	private Integer treasureId;
	private Integer buyNum;
	private Integer cost;
	private Integer costcopper;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public UserMallLogBak() {
	}

	/** full constructor */
	public UserMallLogBak(String userId, Integer userLevel, Integer treasureId,
			Integer buyNum, Integer cost, Integer costcopper, Timestamp time) {
		this.userId = userId;
		this.userLevel = userLevel;
		this.treasureId = treasureId;
		this.buyNum = buyNum;
		this.cost = cost;
		this.costcopper = costcopper;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getTreasureId() {
		return this.treasureId;
	}

	public void setTreasureId(Integer treasureId) {
		this.treasureId = treasureId;
	}

	public Integer getBuyNum() {
		return this.buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public Integer getCost() {
		return this.cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getCostcopper() {
		return this.costcopper;
	}

	public void setCostcopper(Integer costcopper) {
		this.costcopper = costcopper;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}