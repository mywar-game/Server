package com.dataconfig.bo;

/**
 * EEquipmentConstant entity. @author MyEclipse Persistence Tools
 */

public class EEquipmentConstant implements java.io.Serializable {

	// Fields

	private Integer equipmentId;
	private String equipmentDescription;
	private String equipmentName;
	private Integer equipmentLevel;
	private Integer equipmentPrice;
	private Integer part;
	private Integer material;
	private Integer exclusiveHeroId;
	private Integer highAttack;
	private Integer lowerAttack;
	private Integer critRate;
	private Integer life;
	private Integer armor;
	private Integer moveSpeed;
	private Integer attackSpeed;
	private Integer dodgeRate;
	private Integer hitRate;
	private Integer skillCooling;
	private String skillPoints;
	private String pathWay;
	private Integer suitId;
	private String initSkillPoint;
	private Integer quality;

	// Constructors

	/** default constructor */
	public EEquipmentConstant() {
	}

	/** minimal constructor */
	public EEquipmentConstant(String equipmentName, Integer equipmentLevel,
			Integer equipmentPrice, Integer part, Integer material,
			Integer exclusiveHeroId, Integer highAttack, Integer lowerAttack,
			Integer critRate, Integer life, Integer armor, Integer moveSpeed,
			Integer attackSpeed, Integer dodgeRate, Integer hitRate,
			Integer skillCooling, Integer suitId) {
		this.equipmentName = equipmentName;
		this.equipmentLevel = equipmentLevel;
		this.equipmentPrice = equipmentPrice;
		this.part = part;
		this.material = material;
		this.exclusiveHeroId = exclusiveHeroId;
		this.highAttack = highAttack;
		this.lowerAttack = lowerAttack;
		this.critRate = critRate;
		this.life = life;
		this.armor = armor;
		this.moveSpeed = moveSpeed;
		this.attackSpeed = attackSpeed;
		this.dodgeRate = dodgeRate;
		this.hitRate = hitRate;
		this.skillCooling = skillCooling;
		this.suitId = suitId;
	}

	/** full constructor */
	public EEquipmentConstant(String equipmentDescription,
			String equipmentName, Integer equipmentLevel,
			Integer equipmentPrice, Integer part, Integer material,
			Integer exclusiveHeroId, Integer highAttack, Integer lowerAttack,
			Integer critRate, Integer life, Integer armor, Integer moveSpeed,
			Integer attackSpeed, Integer dodgeRate, Integer hitRate,
			Integer skillCooling, String skillPoints, String pathWay,
			Integer suitId, String initSkillPoint, Integer quality) {
		this.equipmentDescription = equipmentDescription;
		this.equipmentName = equipmentName;
		this.equipmentLevel = equipmentLevel;
		this.equipmentPrice = equipmentPrice;
		this.part = part;
		this.material = material;
		this.exclusiveHeroId = exclusiveHeroId;
		this.highAttack = highAttack;
		this.lowerAttack = lowerAttack;
		this.critRate = critRate;
		this.life = life;
		this.armor = armor;
		this.moveSpeed = moveSpeed;
		this.attackSpeed = attackSpeed;
		this.dodgeRate = dodgeRate;
		this.hitRate = hitRate;
		this.skillCooling = skillCooling;
		this.skillPoints = skillPoints;
		this.pathWay = pathWay;
		this.suitId = suitId;
		this.initSkillPoint = initSkillPoint;
		this.quality = quality;
	}

	// Property accessors

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentDescription() {
		return this.equipmentDescription;
	}

	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}

	public String getEquipmentName() {
		return this.equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Integer getEquipmentLevel() {
		return this.equipmentLevel;
	}

	public void setEquipmentLevel(Integer equipmentLevel) {
		this.equipmentLevel = equipmentLevel;
	}

	public Integer getEquipmentPrice() {
		return this.equipmentPrice;
	}

	public void setEquipmentPrice(Integer equipmentPrice) {
		this.equipmentPrice = equipmentPrice;
	}

	public Integer getPart() {
		return this.part;
	}

	public void setPart(Integer part) {
		this.part = part;
	}

	public Integer getMaterial() {
		return this.material;
	}

	public void setMaterial(Integer material) {
		this.material = material;
	}

	public Integer getExclusiveHeroId() {
		return this.exclusiveHeroId;
	}

	public void setExclusiveHeroId(Integer exclusiveHeroId) {
		this.exclusiveHeroId = exclusiveHeroId;
	}

	public Integer getHighAttack() {
		return this.highAttack;
	}

	public void setHighAttack(Integer highAttack) {
		this.highAttack = highAttack;
	}

	public Integer getLowerAttack() {
		return this.lowerAttack;
	}

	public void setLowerAttack(Integer lowerAttack) {
		this.lowerAttack = lowerAttack;
	}

	public Integer getCritRate() {
		return this.critRate;
	}

	public void setCritRate(Integer critRate) {
		this.critRate = critRate;
	}

	public Integer getLife() {
		return this.life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}

	public Integer getArmor() {
		return this.armor;
	}

	public void setArmor(Integer armor) {
		this.armor = armor;
	}

	public Integer getMoveSpeed() {
		return this.moveSpeed;
	}

	public void setMoveSpeed(Integer moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public Integer getAttackSpeed() {
		return this.attackSpeed;
	}

	public void setAttackSpeed(Integer attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public Integer getDodgeRate() {
		return this.dodgeRate;
	}

	public void setDodgeRate(Integer dodgeRate) {
		this.dodgeRate = dodgeRate;
	}

	public Integer getHitRate() {
		return this.hitRate;
	}

	public void setHitRate(Integer hitRate) {
		this.hitRate = hitRate;
	}

	public Integer getSkillCooling() {
		return this.skillCooling;
	}

	public void setSkillCooling(Integer skillCooling) {
		this.skillCooling = skillCooling;
	}

	public String getSkillPoints() {
		return this.skillPoints;
	}

	public void setSkillPoints(String skillPoints) {
		this.skillPoints = skillPoints;
	}

	public String getPathWay() {
		return this.pathWay;
	}

	public void setPathWay(String pathWay) {
		this.pathWay = pathWay;
	}

	public Integer getSuitId() {
		return this.suitId;
	}

	public void setSuitId(Integer suitId) {
		this.suitId = suitId;
	}

	public String getInitSkillPoint() {
		return this.initSkillPoint;
	}

	public void setInitSkillPoint(String initSkillPoint) {
		this.initSkillPoint = initSkillPoint;
	}

	public Integer getQuality() {
		return this.quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

}