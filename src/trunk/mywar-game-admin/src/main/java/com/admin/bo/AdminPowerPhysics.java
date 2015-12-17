package com.admin.bo;

/**
 * AdminPowerPhysics entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AdminPowerPhysics implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String actionName;
	private Integer powerId;
	private Integer orderId;
	private Integer moduleId;
	private String moduleName;
	private String exp1;

	// Constructors

	/** default constructor */
	public AdminPowerPhysics() {
	}

	/** full constructor */
	public AdminPowerPhysics(String name, String actionName, Integer powerId,
			Integer orderId, Integer moduleId, String moduleName, String exp1) {
		this.name = name;
		this.actionName = actionName;
		this.powerId = powerId;
		this.orderId = orderId;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.exp1 = exp1;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Integer getPowerId() {
		return this.powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getExp1() {
		return this.exp1;
	}

	public void setExp1(String exp1) {
		this.exp1 = exp1;
	}

}