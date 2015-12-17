package com.stats.bo;

import java.util.Date;

/**
 * UserOnlineStats entity. @author MyEclipse Persistence Tools
 */

public class UserOnlineStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Date date;
	private Integer onlineAmount;
	private Integer onlinePeak;
	private Integer payPlayerOnlinePeak;

	// Constructors

	/** default constructor */
	public UserOnlineStats() {
	}

	/** minimal constructor */
	public UserOnlineStats(Integer sysNum, Date date,
			Integer payPlayerOnlinePeak) {
		this.sysNum = sysNum;
		this.date = date;
		this.payPlayerOnlinePeak = payPlayerOnlinePeak;
	}

	/** full constructor */
	public UserOnlineStats(Integer sysNum, Date date, Integer onlineAmount,
			Integer onlinePeak, Integer payPlayerOnlinePeak) {
		this.sysNum = sysNum;
		this.date = date;
		this.onlineAmount = onlineAmount;
		this.onlinePeak = onlinePeak;
		this.payPlayerOnlinePeak = payPlayerOnlinePeak;
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getOnlineAmount() {
		return this.onlineAmount;
	}

	public void setOnlineAmount(Integer onlineAmount) {
		this.onlineAmount = onlineAmount;
	}

	public Integer getOnlinePeak() {
		return this.onlinePeak;
	}

	public void setOnlinePeak(Integer onlinePeak) {
		this.onlinePeak = onlinePeak;
	}

	public Integer getPayPlayerOnlinePeak() {
		return this.payPlayerOnlinePeak;
	}

	public void setPayPlayerOnlinePeak(Integer payPlayerOnlinePeak) {
		this.payPlayerOnlinePeak = payPlayerOnlinePeak;
	}

}