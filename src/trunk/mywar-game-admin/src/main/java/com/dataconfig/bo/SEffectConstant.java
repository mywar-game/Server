package com.dataconfig.bo;

/**
 * SEffectConstant entity. @author MyEclipse Persistence Tools
 */

public class SEffectConstant implements java.io.Serializable {

	// Fields

	private Integer effectId;
	private String effectDesc;
	private Integer useFormula;
	private Integer valueType;
	private Integer value;
	private Integer effectType;
	private Integer continueType;
	private Integer period;
	private Integer continueTime;
	private Integer target;

	// Constructors

	/** default constructor */
	public SEffectConstant() {
	}

	/** full constructor */
	public SEffectConstant(String effectDesc, Integer useFormula,
			Integer valueType, Integer value, Integer effectType,
			Integer continueType, Integer period, Integer continueTime,
			Integer target) {
		this.effectDesc = effectDesc;
		this.useFormula = useFormula;
		this.valueType = valueType;
		this.value = value;
		this.effectType = effectType;
		this.continueType = continueType;
		this.period = period;
		this.continueTime = continueTime;
		this.target = target;
	}

	// Property accessors

	public Integer getEffectId() {
		return this.effectId;
	}

	public void setEffectId(Integer effectId) {
		this.effectId = effectId;
	}

	public String getEffectDesc() {
		return this.effectDesc;
	}

	public void setEffectDesc(String effectDesc) {
		this.effectDesc = effectDesc;
	}

	public Integer getUseFormula() {
		return this.useFormula;
	}

	public void setUseFormula(Integer useFormula) {
		this.useFormula = useFormula;
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

	public Integer getEffectType() {
		return this.effectType;
	}

	public void setEffectType(Integer effectType) {
		this.effectType = effectType;
	}

	public Integer getContinueType() {
		return this.continueType;
	}

	public void setContinueType(Integer continueType) {
		this.continueType = continueType;
	}

	public Integer getPeriod() {
		return this.period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getContinueTime() {
		return this.continueTime;
	}

	public void setContinueTime(Integer continueTime) {
		this.continueTime = continueTime;
	}

	public Integer getTarget() {
		return this.target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

}