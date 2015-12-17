package com.stats.bo;

import java.util.Date;

/**
 * UserPveStats entity. @author MyEclipse Persistence Tools
 */

public class UserPveStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Integer forcesId;
	private Integer atkUserNum;
	private Integer atkNum;
	private Integer winNum;
	private Integer failNum;
	private Integer drawNum;
	private Date time;

	// Constructors

	/** default constructor */
	public UserPveStats() {
	}

	/** full constructor */
	public UserPveStats(Integer sysNum, Integer forcesId, Integer atkUserNum,
			Integer atkNum, Integer winNum, Integer failNum, Integer drawNum,
			Date time) {
		this.sysNum = sysNum;
		this.forcesId = forcesId;
		this.atkUserNum = atkUserNum;
		this.atkNum = atkNum;
		this.winNum = winNum;
		this.failNum = failNum;
		this.drawNum = drawNum;
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

	public Integer getForcesId() {
		return this.forcesId;
	}

	public void setForcesId(Integer forcesId) {
		this.forcesId = forcesId;
	}

	public Integer getAtkUserNum() {
		return this.atkUserNum;
	}

	public void setAtkUserNum(Integer atkUserNum) {
		this.atkUserNum = atkUserNum;
	}

	public Integer getAtkNum() {
		return this.atkNum;
	}

	public void setAtkNum(Integer atkNum) {
		this.atkNum = atkNum;
	}

	public Integer getWinNum() {
		return this.winNum;
	}

	public void setWinNum(Integer winNum) {
		this.winNum = winNum;
	}

	public Integer getFailNum() {
		return this.failNum;
	}

	public void setFailNum(Integer failNum) {
		this.failNum = failNum;
	}

	public Integer getDrawNum() {
		return this.drawNum;
	}

	public void setDrawNum(Integer drawNum) {
		this.drawNum = drawNum;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}