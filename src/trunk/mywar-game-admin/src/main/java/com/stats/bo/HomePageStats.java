package com.stats.bo;

import java.util.Date;

/**
 * HomePageStats entity. @author MyEclipse Persistence Tools
 */

public class HomePageStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String channel;
	private String qn;
	private Integer totalNum;
	private Integer dayActive;
	private Integer monthActive;
	private Integer newUser;
	private Integer payUserNum;
	private Double payAmount;
	private Integer halfHourIndex;
	private Date time;
	private Integer newRegPayUserNum;
	
	private Double diamondUsed;

	// Constructors

	/** default constructor */
	public HomePageStats() {
	}

	/** full constructor */
	public HomePageStats(Integer sysNum, String channel, String qn, Integer totalNum,
			Integer dayActive, Integer monthActive, Integer newUser,
			Integer payUserNum, Double payAmount, Integer halfHourIndex,
			Date time,Integer newRegPayUserNum, Double diamondUsed) {
		this.sysNum = sysNum;
		this.channel = channel;
		this.qn = qn;
		this.totalNum = totalNum;
		this.dayActive = dayActive;
		this.monthActive = monthActive;
		this.newUser = newUser;
		this.payUserNum = payUserNum;
		this.payAmount = payAmount;
		this.halfHourIndex = halfHourIndex;
		this.time = time;
		this.newRegPayUserNum = newRegPayUserNum;
		diamondUsed = diamondUsed;
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

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getDayActive() {
		return this.dayActive;
	}

	public void setDayActive(Integer dayActive) {
		this.dayActive = dayActive;
	}

	public Integer getMonthActive() {
		return this.monthActive;
	}

	public void setMonthActive(Integer monthActive) {
		this.monthActive = monthActive;
	}

	public Integer getNewUser() {
		return this.newUser;
	}

	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
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

	public Integer getHalfHourIndex() {
		return this.halfHourIndex;
	}

	public void setHalfHourIndex(Integer halfHourIndex) {
		this.halfHourIndex = halfHourIndex;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getNewRegPayUserNum() {
		return newRegPayUserNum;
	}

	public void setNewRegPayUserNum(Integer newRegPayUserNum) {
		this.newRegPayUserNum = newRegPayUserNum;
	}
	
	public Double getDiamondUsed() {
		return diamondUsed;
	}

	public void setDiamondUsed(Double diamondUsed) {
		this.diamondUsed = diamondUsed;
	}
}