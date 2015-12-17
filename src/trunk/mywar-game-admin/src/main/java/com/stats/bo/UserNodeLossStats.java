package com.stats.bo;

import java.util.Date;

/**
 * UserNodeLossStats entity. @author MyEclipse Persistence Tools
 */

public class UserNodeLossStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer actionId;
	private Integer sysNum;
	private Integer num;
	private Date time;

	// Constructors

	/** default constructor */
	public UserNodeLossStats() {
	}

	/** full constructor */
	public UserNodeLossStats(Integer actionId, Integer sysNum, Integer num,
			Date time) {
		this.actionId = actionId;
		this.sysNum = sysNum;
		this.num = num;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}