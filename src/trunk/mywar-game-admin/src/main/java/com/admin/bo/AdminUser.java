package com.admin.bo;

import java.sql.Timestamp;

/**
 * AdminUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AdminUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String adminName;
	private String password;
	private String powerString;
	private String menuPowerString;
	private Integer roleId;
	private Integer groupId;
	private String description;
	private String exp1;
	private String exp2;
	private Timestamp dueTime;
	private Integer loginFailTimes;

	// Constructors

	/** default constructor */
	public AdminUser() {
	}

	/** full constructor */
	public AdminUser(String adminName, String password, String powerString,
			String menuPowerString, Integer roleId, Integer groupId,
			String description, String exp1, String exp2) {
		this.adminName = adminName;
		this.password = password;
		this.powerString = powerString;
		this.menuPowerString = menuPowerString;
		this.roleId = roleId;
		this.groupId = groupId;
		this.description = description;
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	// Property accessors

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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPowerString() {
		return this.powerString;
	}

	public void setPowerString(String powerString) {
		this.powerString = powerString;
	}

	public String getMenuPowerString() {
		return this.menuPowerString;
	}

	public void setMenuPowerString(String menuPowerString) {
		this.menuPowerString = menuPowerString;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExp1() {
		return this.exp1;
	}

	public void setExp1(String exp1) {
		this.exp1 = exp1;
	}

	public String getExp2() {
		return this.exp2;
	}

	public void setExp2(String exp2) {
		this.exp2 = exp2;
	}

	public Timestamp getDueTime() {
		return dueTime;
	}

	public void setDueTime(Timestamp dueTime) {
		this.dueTime = dueTime;
	}

	public Integer getLoginFailTimes() {
		return loginFailTimes;
	}

	public void setLoginFailTimes(Integer loginFailTimes) {
		this.loginFailTimes = loginFailTimes;
	}

}