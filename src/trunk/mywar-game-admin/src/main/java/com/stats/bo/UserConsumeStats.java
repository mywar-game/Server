package com.stats.bo;

import java.util.Date;

/**
 * UserConsumeStats entity. @author MyEclipse Persistence Tools
 */

public class UserConsumeStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Date date;
	private Integer consumeAmount;
	private Integer testConsumeAmount;

	// Constructors

	/** default constructor */
	public UserConsumeStats() {
	}

	/** full constructor */
	public UserConsumeStats(Integer sysNum, Date date, Integer consumeAmount,
			Integer testConsumeAmount) {
		this.sysNum = sysNum;
		this.date = date;
		this.consumeAmount = consumeAmount;
		this.testConsumeAmount = testConsumeAmount;
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

	public Integer getConsumeAmount() {
		return this.consumeAmount;
	}

	public void setConsumeAmount(Integer consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public Integer getTestConsumeAmount() {
		return this.testConsumeAmount;
	}

	public void setTestConsumeAmount(Integer testConsumeAmount) {
		this.testConsumeAmount = testConsumeAmount;
	}

}