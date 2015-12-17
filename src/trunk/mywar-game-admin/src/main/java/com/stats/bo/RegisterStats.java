package com.stats.bo;

import java.util.Date;

/**
 * RegisterStats entity. @author MyEclipse Persistence Tools
 */

public class RegisterStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private String channel;
	private Integer sysNum;
	private Integer newReg;
	private Integer newUser;
	private Integer newRegPayUserNum;
	private Double newRegPayAmount;
	private Integer halfHourIndex;
	private Date time;

	// Constructors

	/** default constructor */
	public RegisterStats() {
	}

	/** full constructor */
	public RegisterStats(String channel, Integer sysNum, Integer newReg,
			Integer newUser, Integer newRegPayUserNum, Double newRegPayAmount,
			Integer halfHourIndex, Date time) {
		this.channel = channel;
		this.sysNum = sysNum;
		this.newReg = newReg;
		this.newUser = newUser;
		this.newRegPayUserNum = newRegPayUserNum;
		this.newRegPayAmount = newRegPayAmount;
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

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
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

	public Integer getNewRegPayUserNum() {
		return this.newRegPayUserNum;
	}

	public void setNewRegPayUserNum(Integer newRegPayUserNum) {
		this.newRegPayUserNum = newRegPayUserNum;
	}

	public Double getNewRegPayAmount() {
		return this.newRegPayAmount;
	}

	public void setNewRegPayAmount(Double newRegPayAmount) {
		this.newRegPayAmount = newRegPayAmount;
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