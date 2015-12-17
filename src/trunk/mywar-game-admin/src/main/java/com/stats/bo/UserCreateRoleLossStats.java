package com.stats.bo;

import java.util.Date;

/**
 * UserCreateRoleLossStats entity. @author MyEclipse Persistence Tools
 */

public class UserCreateRoleLossStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Date date;
	private Integer regAmount;
	private Integer createAmount;

	// Constructors

	/** default constructor */
	public UserCreateRoleLossStats() {
	}

	/** full constructor */
	public UserCreateRoleLossStats(Integer sysNum, Date date,
			Integer regAmount, Integer createAmount) {
		this.sysNum = sysNum;
		this.date = date;
		this.regAmount = regAmount;
		this.createAmount = createAmount;
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

	public Integer getRegAmount() {
		return this.regAmount;
	}

	public void setRegAmount(Integer regAmount) {
		this.regAmount = regAmount;
	}

	public Integer getCreateAmount() {
		return this.createAmount;
	}

	public void setCreateAmount(Integer createAmount) {
		this.createAmount = createAmount;
	}

}