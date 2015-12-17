package com.dataconfig.bo;

/**
 * BBuildingconstraintConstant entity. @author MyEclipse Persistence Tools
 */

public class BBuildingconstraintConstant implements java.io.Serializable {

	// Fields

	private Integer buildingConstraintId;
	private Integer buildingId;
	private Integer buildingLevel;
	private String prebuildings;
	private String description;
	private Integer costGrain;
	private Integer costMineral;
	private Integer costMoney;
	private Integer coolTime;

	// Constructors

	/** default constructor */
	public BBuildingconstraintConstant() {
	}

	/** full constructor */
	public BBuildingconstraintConstant(Integer buildingId,
			Integer buildingLevel, String prebuildings, String description,
			Integer costGrain, Integer costMineral, Integer costMoney,
			Integer coolTime) {
		this.buildingId = buildingId;
		this.buildingLevel = buildingLevel;
		this.prebuildings = prebuildings;
		this.description = description;
		this.costGrain = costGrain;
		this.costMineral = costMineral;
		this.costMoney = costMoney;
		this.coolTime = coolTime;
	}

	// Property accessors

	public Integer getBuildingConstraintId() {
		return this.buildingConstraintId;
	}

	public void setBuildingConstraintId(Integer buildingConstraintId) {
		this.buildingConstraintId = buildingConstraintId;
	}

	public Integer getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getBuildingLevel() {
		return this.buildingLevel;
	}

	public void setBuildingLevel(Integer buildingLevel) {
		this.buildingLevel = buildingLevel;
	}

	public String getPrebuildings() {
		return this.prebuildings;
	}

	public void setPrebuildings(String prebuildings) {
		this.prebuildings = prebuildings;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCostGrain() {
		return this.costGrain;
	}

	public void setCostGrain(Integer costGrain) {
		this.costGrain = costGrain;
	}

	public Integer getCostMineral() {
		return this.costMineral;
	}

	public void setCostMineral(Integer costMineral) {
		this.costMineral = costMineral;
	}

	public Integer getCostMoney() {
		return this.costMoney;
	}

	public void setCostMoney(Integer costMoney) {
		this.costMoney = costMoney;
	}

	public Integer getCoolTime() {
		return this.coolTime;
	}

	public void setCoolTime(Integer coolTime) {
		this.coolTime = coolTime;
	}

}