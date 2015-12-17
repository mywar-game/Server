package com.stats.bo;

import java.util.Date;

/**
 * UserIpStats entity. @author MyEclipse Persistence Tools
 */

public class UserIpStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String ip;
	private Integer roleNum;
	private Date firstDate;
	private Date lastDate;

	// Constructors

	/** default constructor */
	public UserIpStats() {
	}

	/** full constructor */
	public UserIpStats(Integer sysNum, String ip, Integer roleNum,
			Date firstDate, Date lastDate) {
		this.sysNum = sysNum;
		this.ip = ip;
		this.roleNum = roleNum;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getRoleNum() {
		return this.roleNum;
	}

	public void setRoleNum(Integer roleNum) {
		this.roleNum = roleNum;
	}

	public Date getFirstDate() {
		return this.firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

}