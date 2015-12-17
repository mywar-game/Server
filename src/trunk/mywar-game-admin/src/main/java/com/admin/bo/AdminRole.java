package com.admin.bo;

/**
 * AdminRole entity. @author MyEclipse Persistence Tools
 */

public class AdminRole implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String description;
	private String powerString;
	private String menuPowerString;

	// Constructors

	/** default constructor */
	public AdminRole() {
	}

	/** full constructor */
	public AdminRole(String roleName, String description, String powerString,
			String menuPowerString) {
		this.roleName = roleName;
		this.description = description;
		this.powerString = powerString;
		this.menuPowerString = menuPowerString;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}