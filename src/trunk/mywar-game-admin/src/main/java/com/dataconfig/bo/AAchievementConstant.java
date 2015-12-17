package com.dataconfig.bo;

/**
 * AAchievementConstant entity. @author MyEclipse Persistence Tools
 */

public class AAchievementConstant implements java.io.Serializable {

	// Fields

	private Integer achievementId;
	private String achievementName;
	private String conditionDesc;
	private String effectDesc;
	private Integer conditionType;
	private Integer conditionNum;
	private Integer attackAdd;
	private Integer lifeAdd;
	private Integer armorAdd;
	private Integer critAdd;
	private Integer dodgeAdd;
	private Integer speedAdd;
	private Integer hitAdd;
	private Integer achievementType;
	private Integer isSendNotice;
	private Integer valueType;

	// Constructors

	/** default constructor */
	public AAchievementConstant() {
	}

	/** full constructor */
	public AAchievementConstant(String achievementName, String conditionDesc,
			String effectDesc, Integer conditionType, Integer conditionNum,
			Integer attackAdd, Integer lifeAdd, Integer armorAdd,
			Integer critAdd, Integer dodgeAdd, Integer speedAdd,
			Integer hitAdd, Integer achievementType, Integer isSendNotice,
			Integer valueType) {
		this.achievementName = achievementName;
		this.conditionDesc = conditionDesc;
		this.effectDesc = effectDesc;
		this.conditionType = conditionType;
		this.conditionNum = conditionNum;
		this.attackAdd = attackAdd;
		this.lifeAdd = lifeAdd;
		this.armorAdd = armorAdd;
		this.critAdd = critAdd;
		this.dodgeAdd = dodgeAdd;
		this.speedAdd = speedAdd;
		this.hitAdd = hitAdd;
		this.achievementType = achievementType;
		this.isSendNotice = isSendNotice;
		this.valueType = valueType;
	}

	// Property accessors

	public Integer getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(Integer achievementId) {
		this.achievementId = achievementId;
	}

	public String getAchievementName() {
		return this.achievementName;
	}

	public void setAchievementName(String achievementName) {
		this.achievementName = achievementName;
	}

	public String getConditionDesc() {
		return this.conditionDesc;
	}

	public void setConditionDesc(String conditionDesc) {
		this.conditionDesc = conditionDesc;
	}

	public String getEffectDesc() {
		return this.effectDesc;
	}

	public void setEffectDesc(String effectDesc) {
		this.effectDesc = effectDesc;
	}

	public Integer getConditionType() {
		return this.conditionType;
	}

	public void setConditionType(Integer conditionType) {
		this.conditionType = conditionType;
	}

	public Integer getConditionNum() {
		return this.conditionNum;
	}

	public void setConditionNum(Integer conditionNum) {
		this.conditionNum = conditionNum;
	}

	public Integer getAttackAdd() {
		return this.attackAdd;
	}

	public void setAttackAdd(Integer attackAdd) {
		this.attackAdd = attackAdd;
	}

	public Integer getLifeAdd() {
		return this.lifeAdd;
	}

	public void setLifeAdd(Integer lifeAdd) {
		this.lifeAdd = lifeAdd;
	}

	public Integer getArmorAdd() {
		return this.armorAdd;
	}

	public void setArmorAdd(Integer armorAdd) {
		this.armorAdd = armorAdd;
	}

	public Integer getCritAdd() {
		return this.critAdd;
	}

	public void setCritAdd(Integer critAdd) {
		this.critAdd = critAdd;
	}

	public Integer getDodgeAdd() {
		return this.dodgeAdd;
	}

	public void setDodgeAdd(Integer dodgeAdd) {
		this.dodgeAdd = dodgeAdd;
	}

	public Integer getSpeedAdd() {
		return this.speedAdd;
	}

	public void setSpeedAdd(Integer speedAdd) {
		this.speedAdd = speedAdd;
	}

	public Integer getHitAdd() {
		return this.hitAdd;
	}

	public void setHitAdd(Integer hitAdd) {
		this.hitAdd = hitAdd;
	}

	public Integer getAchievementType() {
		return this.achievementType;
	}

	public void setAchievementType(Integer achievementType) {
		this.achievementType = achievementType;
	}

	public Integer getIsSendNotice() {
		return this.isSendNotice;
	}

	public void setIsSendNotice(Integer isSendNotice) {
		this.isSendNotice = isSendNotice;
	}

	public Integer getValueType() {
		return this.valueType;
	}

	public void setValueType(Integer valueType) {
		this.valueType = valueType;
	}

}