package com.dataconfig.bo;

/**
 * EEquipmentStrength entity. @author MyEclipse Persistence Tools
 */

public class EEquipmentStrength implements java.io.Serializable {

	// Fields

	private Integer strengthLevel;
	private Integer strengthCost;
	private Integer strengthCoolingTime;
	private Integer strengthCsotCount;

	// Constructors

	/** default constructor */
	public EEquipmentStrength() {
	}

	/** full constructor */
	public EEquipmentStrength(Integer strengthCost,
			Integer strengthCoolingTime, Integer strengthCsotCount) {
		this.strengthCost = strengthCost;
		this.strengthCoolingTime = strengthCoolingTime;
		this.strengthCsotCount = strengthCsotCount;
	}

	// Property accessors

	public Integer getStrengthLevel() {
		return this.strengthLevel;
	}

	public void setStrengthLevel(Integer strengthLevel) {
		this.strengthLevel = strengthLevel;
	}

	public Integer getStrengthCost() {
		return this.strengthCost;
	}

	public void setStrengthCost(Integer strengthCost) {
		this.strengthCost = strengthCost;
	}

	public Integer getStrengthCoolingTime() {
		return this.strengthCoolingTime;
	}

	public void setStrengthCoolingTime(Integer strengthCoolingTime) {
		this.strengthCoolingTime = strengthCoolingTime;
	}

	public Integer getStrengthCsotCount() {
		return this.strengthCsotCount;
	}

	public void setStrengthCsotCount(Integer strengthCsotCount) {
		this.strengthCsotCount = strengthCsotCount;
	}

}