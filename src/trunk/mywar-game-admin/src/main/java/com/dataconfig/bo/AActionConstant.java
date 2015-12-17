package com.dataconfig.bo;

/**
 * AActionConstant entity. @author MyEclipse Persistence Tools
 */

public class AActionConstant implements java.io.Serializable {

	// Fields

	private Integer actionId;
	private String actionDesc;
	private Integer actionType;

	// Constructors

	/** default constructor */
	public AActionConstant() {
	}

	/** full constructor */
	public AActionConstant(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	// Property accessors

	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public String getActionDesc() {
		return this.actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

}