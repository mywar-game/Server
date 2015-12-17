package com.stats.bo;

import java.util.Date;

/**
 * SummaryStats entity. @author MyEclipse Persistence Tools
 */

public class SummaryStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private String channel;
	private Integer sysNum;
	private Integer currentOnlineAmount;
	private Double payNum;
	private Integer payUserNum;
	private Integer payTotalTimes;
	private String payUserStr;
	private Integer buyToolConsume;
	private Integer halfHourIndex;
	private Date time;

	// Constructors

	/** default constructor */
	public SummaryStats() {
	}

	/** full constructor */
	public SummaryStats(String channel, Integer sysNum,
			Integer currentOnlineAmount, Double payNum,Integer payUserNum,Integer payTotalTimes, String payUserStr,
			Integer buyToolConsume, Integer halfHourIndex, Date time) {
		this.channel = channel;
		this.sysNum = sysNum;
		this.currentOnlineAmount = currentOnlineAmount;
		this.payNum = payNum;
		this.payUserNum = payUserNum;
		this.payTotalTimes = payTotalTimes;
		this.payUserStr = payUserStr;
		this.buyToolConsume = buyToolConsume;
		this.halfHourIndex = halfHourIndex;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public Integer getCurrentOnlineAmount() {
		return this.currentOnlineAmount;
	}

	public void setCurrentOnlineAmount(Integer currentOnlineAmount) {
		this.currentOnlineAmount = currentOnlineAmount;
	}

	public Double getPayNum() {
		return this.payNum;
	}

	public void setPayNum(Double payNum) {
		this.payNum = payNum;
	}

	public Integer getPayUserNum() {
		return payUserNum;
	}

	public void setPayUserNum(Integer payUserNum) {
		this.payUserNum = payUserNum;
	}

	public Integer getPayTotalTimes() {
		return payTotalTimes;
	}

	public void setPayTotalTimes(Integer payTotalTimes) {
		this.payTotalTimes = payTotalTimes;
	}

	public String getPayUserStr() {
		return this.payUserStr;
	}

	public void setPayUserStr(String payUserStr) {
		this.payUserStr = payUserStr;
	}

	public Integer getBuyToolConsume() {
		return this.buyToolConsume;
	}

	public void setBuyToolConsume(Integer buyToolConsume) {
		this.buyToolConsume = buyToolConsume;
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

}