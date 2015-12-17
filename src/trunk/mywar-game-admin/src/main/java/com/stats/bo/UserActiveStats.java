package com.stats.bo;

import java.util.Date;

/**
 * UserActiveStats entity. @author MyEclipse Persistence Tools
 */

public class UserActiveStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Date date;
	private Integer activeUserAmount;
	private Integer regAmount;

	// Constructors

	/** default constructor */
	public UserActiveStats() {
	}

	/** full constructor */
	public UserActiveStats(Integer sysNum, Date date, Integer activeUserAmount,
			Integer regAmount) {
		this.sysNum = sysNum;
		this.date = date;
		this.activeUserAmount = activeUserAmount;
		this.regAmount = regAmount;
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

	public Integer getActiveUserAmount() {
		return this.activeUserAmount;
	}

	public void setActiveUserAmount(Integer activeUserAmount) {
		this.activeUserAmount = activeUserAmount;
	}

	public Integer getRegAmount() {
		return this.regAmount;
	}

	public void setRegAmount(Integer regAmount) {
		this.regAmount = regAmount;
	}

}