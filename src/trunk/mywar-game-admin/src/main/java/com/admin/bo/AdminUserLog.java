package com.admin.bo;

// Generated 2014-1-3 13:44:09 by Hibernate Tools 3.3.0.GA

import java.util.Date;

/**
 * AdminUserLog generated by hbm2java
 */
public class AdminUserLog implements java.io.Serializable {

	private Integer id;
	private String adminName;
	private int state;
	private Date time;

	public AdminUserLog() {
	}

	public AdminUserLog(String adminName, int state, Date time) {
		this.adminName = adminName;
		this.state = state;
		this.time = time;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
