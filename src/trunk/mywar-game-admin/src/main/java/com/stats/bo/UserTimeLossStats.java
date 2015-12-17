package com.stats.bo;

import java.util.Date;

/**
 * UserTimeLossStats entity. @author MyEclipse Persistence Tools
 */

public class UserTimeLossStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Date date;
	private Integer lossAmount;
	private Integer lossPayerAmount;

	// Constructors

	/** default constructor */
	public UserTimeLossStats() {
	}

	/** full constructor */
	public UserTimeLossStats(Integer sysNum, Date date, Integer lossAmount,
			Integer lossPayerAmount) {
		this.sysNum = sysNum;
		this.date = date;
		this.lossAmount = lossAmount;
		this.lossPayerAmount = lossPayerAmount;
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

	public Integer getLossAmount() {
		return this.lossAmount;
	}

	public void setLossAmount(Integer lossAmount) {
		this.lossAmount = lossAmount;
	}

	public Integer getLossPayerAmount() {
		return this.lossPayerAmount;
	}

	public void setLossPayerAmount(Integer lossPayerAmount) {
		this.lossPayerAmount = lossPayerAmount;
	}

}