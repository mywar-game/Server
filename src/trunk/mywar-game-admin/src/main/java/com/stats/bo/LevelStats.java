package com.stats.bo;

import java.util.Date;

/**
 * LevelStats entity. @author MyEclipse Persistence Tools
 */

public class LevelStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String channel;
	private Integer userLevel;
	private Integer regNum;
	private Integer userNum;
	private Integer payUserNum;
	private Double payAmount;
	private Integer payTimes;
	private Integer buyToolConsume;
	private Date time;

	// Constructors

	/** default constructor */
	public LevelStats() {
	}

	/** full constructor */
	public LevelStats(Integer sysNum, String channel, Integer userLevel,
			Integer regNum, Integer userNum, Integer payUserNum,
			Double payAmount, Integer payTimes, Integer buyToolConsume,
			Date time) {
		this.sysNum = sysNum;
		this.channel = channel;
		this.userLevel = userLevel;
		this.regNum = regNum;
		this.userNum = userNum;
		this.payUserNum = payUserNum;
		this.payAmount = payAmount;
		this.payTimes = payTimes;
		this.buyToolConsume = buyToolConsume;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getRegNum() {
		return this.regNum;
	}

	public void setRegNum(Integer regNum) {
		this.regNum = regNum;
	}

	public Integer getUserNum() {
		return this.userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public Integer getPayUserNum() {
		return this.payUserNum;
	}

	public void setPayUserNum(Integer payUserNum) {
		this.payUserNum = payUserNum;
	}

	public Double getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getPayTimes() {
		return this.payTimes;
	}

	public void setPayTimes(Integer payTimes) {
		this.payTimes = payTimes;
	}

	public Integer getBuyToolConsume() {
		return this.buyToolConsume;
	}

	public void setBuyToolConsume(Integer buyToolConsume) {
		this.buyToolConsume = buyToolConsume;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}