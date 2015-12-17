package com.stats.bo;

import java.util.Date;

/**
 * UserEverydayLoginStats entity. @author MyEclipse Persistence Tools
 */

public class UserEverydayLoginStats implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -3807992717066599607L;
	private Integer id;
	private Integer sysNum;
	private Date date;
	private Integer totalTimes;
	private Integer userNumber;
	private Integer ipNumber;

	// Constructors

	/** default constructor */
	public UserEverydayLoginStats() {
	}

	/** full constructor */
	public UserEverydayLoginStats(Integer sysNum, Date date,
			Integer totalTimes, Integer userNumber, Integer ipNumber) {
		this.sysNum = sysNum;
		this.date = date;
		this.totalTimes = totalTimes;
		this.userNumber = userNumber;
		this.ipNumber = ipNumber;
	}
	
	public String toString() {
		return sysNum + " " + date + " " + totalTimes + " " + userNumber + " " + ipNumber;
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

	public Integer getTotalTimes() {
		return this.totalTimes;
	}

	public void setTotalTimes(Integer totalTimes) {
		this.totalTimes = totalTimes;
	}

	public Integer getUserNumber() {
		return this.userNumber;
	}

	public void setUserNumber(Integer userNumber) {
		this.userNumber = userNumber;
	}

	public Integer getIpNumber() {
		return this.ipNumber;
	}

	public void setIpNumber(Integer ipNumber) {
		this.ipNumber = ipNumber;
	}

}