package com.adminTool.bo;

import java.sql.Timestamp;

/**
 * Partner entity. @author MyEclipse Persistence Tools
 */

public class Partner implements java.io.Serializable {

	// Fields

	private Integer pid;
	private String PNum;
	private String PName;
	private Timestamp createTime;
	private Timestamp editTime;
	private Timestamp removeTime;
	private Integer removeStatus;

	// Constructors

	/** default constructor */
	public Partner() {
	}

	/** minimal constructor */
	public Partner(Timestamp createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public Partner(String PNum, String PName, Timestamp createTime,
			Timestamp editTime, Timestamp removeTime, Integer removeStatus) {
		this.PNum = PNum;
		this.PName = PName;
		this.createTime = createTime;
		this.editTime = editTime;
		this.removeTime = removeTime;
		this.removeStatus = removeStatus;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPNum() {
		return this.PNum;
	}

	public void setPNum(String PNum) {
		this.PNum = PNum;
	}

	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getEditTime() {
		return this.editTime;
	}

	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}

	public Timestamp getRemoveTime() {
		return this.removeTime;
	}

	public void setRemoveTime(Timestamp removeTime) {
		this.removeTime = removeTime;
	}

	public Integer getRemoveStatus() {
		return this.removeStatus;
	}

	public void setRemoveStatus(Integer removeStatus) {
		this.removeStatus = removeStatus;
	}

}