package com.stats.bo;

import java.util.Date;

/**
 * LoginStats entity. @author MyEclipse Persistence Tools
 */

public class LoginStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String channel;
	private Integer loginNum;
	private Integer loginIpNum;
	private Integer newReg;
	private Integer newUser;
	private Integer oldLoginNum;
	private Integer halfHourIndex;
	private Date time;

	// Constructors

	/** default constructor */
	public LoginStats() {
	}

	/** full constructor */
	public LoginStats(Integer sysNum, String channel, Integer loginNum,
			Integer loginIpNum, Integer newReg, Integer newUser,
			Integer oldLoginNum, Integer halfHourIndex, Date time) {
		this.sysNum = sysNum;
		this.channel = channel;
		this.loginNum = loginNum;
		this.loginIpNum = loginIpNum;
		this.newReg = newReg;
		this.newUser = newUser;
		this.oldLoginNum = oldLoginNum;
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

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getLoginNum() {
		return this.loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public Integer getLoginIpNum() {
		return this.loginIpNum;
	}

	public void setLoginIpNum(Integer loginIpNum) {
		this.loginIpNum = loginIpNum;
	}

	public Integer getNewReg() {
		return this.newReg;
	}

	public void setNewReg(Integer newReg) {
		this.newReg = newReg;
	}

	public Integer getNewUser() {
		return this.newUser;
	}

	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}

	public Integer getOldLoginNum() {
		return this.oldLoginNum;
	}

	public void setOldLoginNum(Integer oldLoginNum) {
		this.oldLoginNum = oldLoginNum;
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