package com.stats.bo;

import java.util.Date;

/**
 * UserFriendVisitStats entity. @author MyEclipse Persistence Tools
 */

public class UserFriendVisitStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Date date;
	private Integer visitTimes;
	private Integer visitUserNum;
	private Integer visitPeak;

	// Constructors

	/** default constructor */
	public UserFriendVisitStats() {
	}

	/** full constructor */
	public UserFriendVisitStats(Integer sysNum, Date date, Integer visitTimes,
			Integer visitUserNum, Integer visitPeak) {
		this.sysNum = sysNum;
		this.date = date;
		this.visitTimes = visitTimes;
		this.visitUserNum = visitUserNum;
		this.visitPeak = visitPeak;
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

	public Integer getVisitTimes() {
		return this.visitTimes;
	}

	public void setVisitTimes(Integer visitTimes) {
		this.visitTimes = visitTimes;
	}

	public Integer getVisitUserNum() {
		return this.visitUserNum;
	}

	public void setVisitUserNum(Integer visitUserNum) {
		this.visitUserNum = visitUserNum;
	}

	public Integer getVisitPeak() {
		return this.visitPeak;
	}

	public void setVisitPeak(Integer visitPeak) {
		this.visitPeak = visitPeak;
	}

}