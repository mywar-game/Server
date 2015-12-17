package com.dataconfig.bo;

/**
 * SSkillConstant entity. @author MyEclipse Persistence Tools
 */

public class SSkillConstant implements java.io.Serializable {

	// Fields

	private Integer skillConstantId;
	private Integer skillId;
	private String skillName;
	private String skillDesc;
	private Integer level;
	private Integer skillType;
	private Integer target;
	private Integer rangeCenter;
	private Integer skillRange;
	private Integer castRange;
	private Integer coolingTime;
	private String skillEffect;
	private Integer isSpecial;
	private String numString;
	private Float skillTiggerCondition;
	private Float skillEndCondition;

	// Constructors

	/** default constructor */
	public SSkillConstant() {
	}

	/** full constructor */
	public SSkillConstant(Integer skillId, String skillName, String skillDesc,
			Integer level, Integer skillType, Integer target,
			Integer rangeCenter, Integer skillRange, Integer castRange,
			Integer coolingTime, String skillEffect, Integer isSpecial,
			String numString, Float skillTiggerCondition,
			Float skillEndCondition) {
		this.skillId = skillId;
		this.skillName = skillName;
		this.skillDesc = skillDesc;
		this.level = level;
		this.skillType = skillType;
		this.target = target;
		this.rangeCenter = rangeCenter;
		this.skillRange = skillRange;
		this.castRange = castRange;
		this.coolingTime = coolingTime;
		this.skillEffect = skillEffect;
		this.isSpecial = isSpecial;
		this.numString = numString;
		this.skillTiggerCondition = skillTiggerCondition;
		this.skillEndCondition = skillEndCondition;
	}

	// Property accessors

	public Integer getSkillConstantId() {
		return this.skillConstantId;
	}

	public void setSkillConstantId(Integer skillConstantId) {
		this.skillConstantId = skillConstantId;
	}

	public Integer getSkillId() {
		return this.skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return this.skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillDesc() {
		return this.skillDesc;
	}

	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSkillType() {
		return this.skillType;
	}

	public void setSkillType(Integer skillType) {
		this.skillType = skillType;
	}

	public Integer getTarget() {
		return this.target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	public Integer getRangeCenter() {
		return this.rangeCenter;
	}

	public void setRangeCenter(Integer rangeCenter) {
		this.rangeCenter = rangeCenter;
	}

	public Integer getSkillRange() {
		return this.skillRange;
	}

	public void setSkillRange(Integer skillRange) {
		this.skillRange = skillRange;
	}

	public Integer getCastRange() {
		return this.castRange;
	}

	public void setCastRange(Integer castRange) {
		this.castRange = castRange;
	}

	public Integer getCoolingTime() {
		return this.coolingTime;
	}

	public void setCoolingTime(Integer coolingTime) {
		this.coolingTime = coolingTime;
	}

	public String getSkillEffect() {
		return this.skillEffect;
	}

	public void setSkillEffect(String skillEffect) {
		this.skillEffect = skillEffect;
	}

	public Integer getIsSpecial() {
		return this.isSpecial;
	}

	public void setIsSpecial(Integer isSpecial) {
		this.isSpecial = isSpecial;
	}

	public String getNumString() {
		return this.numString;
	}

	public void setNumString(String numString) {
		this.numString = numString;
	}

	public Float getSkillTiggerCondition() {
		return this.skillTiggerCondition;
	}

	public void setSkillTiggerCondition(Float skillTiggerCondition) {
		this.skillTiggerCondition = skillTiggerCondition;
	}

	public Float getSkillEndCondition() {
		return this.skillEndCondition;
	}

	public void setSkillEndCondition(Float skillEndCondition) {
		this.skillEndCondition = skillEndCondition;
	}

}