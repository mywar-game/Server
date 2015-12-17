package com.dataconfig.bo;

/**
 * BTechnologyConstant entity. @author MyEclipse Persistence Tools
 */

public class BTechnologyConstant implements java.io.Serializable {

	// Fields

	private Integer technologyConstantId;
	private Integer technologyId;
	private String technologyName;
	private String technologyDesc;
	private Integer effectType;
	private Integer level;
	private Integer valueType;
	private Integer value;
	private Integer needGold;
	private Integer coolingTime;

	// Constructors

	/** default constructor */
	public BTechnologyConstant() {
	}

	/** full constructor */
	public BTechnologyConstant(Integer technologyId, String technologyName,
			String technologyDesc, Integer effectType, Integer level,
			Integer valueType, Integer value, Integer needGold,
			Integer coolingTime) {
		this.technologyId = technologyId;
		this.technologyName = technologyName;
		this.technologyDesc = technologyDesc;
		this.effectType = effectType;
		this.level = level;
		this.valueType = valueType;
		this.value = value;
		this.needGold = needGold;
		this.coolingTime = coolingTime;
	}

	// Property accessors

	public Integer getTechnologyConstantId() {
		return this.technologyConstantId;
	}

	public void setTechnologyConstantId(Integer technologyConstantId) {
		this.technologyConstantId = technologyConstantId;
	}

	public Integer getTechnologyId() {
		return this.technologyId;
	}

	public void setTechnologyId(Integer technologyId) {
		this.technologyId = technologyId;
	}

	public String getTechnologyName() {
		return this.technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public String getTechnologyDesc() {
		return this.technologyDesc;
	}

	public void setTechnologyDesc(String technologyDesc) {
		this.technologyDesc = technologyDesc;
	}

	public Integer getEffectType() {
		return this.effectType;
	}

	public void setEffectType(Integer effectType) {
		this.effectType = effectType;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getValueType() {
		return this.valueType;
	}

	public void setValueType(Integer valueType) {
		this.valueType = valueType;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getNeedGold() {
		return this.needGold;
	}

	public void setNeedGold(Integer needGold) {
		this.needGold = needGold;
	}

	public Integer getCoolingTime() {
		return this.coolingTime;
	}

	public void setCoolingTime(Integer coolingTime) {
		this.coolingTime = coolingTime;
	}

}