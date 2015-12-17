package com.stats.bo;

import java.util.Date;

/**
 * UserPayStats entity. @author MyEclipse Persistence Tools
 */

public class UserPayStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Date date;
	private Integer payAmount;

	// Constructors

	/** default constructor */
	public UserPayStats() {
	}

	/** full constructor */
	public UserPayStats(Integer sysNum, Date date, Integer payAmount) {
		this.sysNum = sysNum;
		this.date = date;
		this.payAmount = payAmount;
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

	public Integer getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}

}