package com.dataconfig.bo;

/**
 * HHeroConstant entity. @author MyEclipse Persistence Tools
 */

public class HHeroConstant implements java.io.Serializable {

	// Fields

	private Integer heroId;
	private String heroName;
	private String heroDesc;
	private Integer basicLife;
	private Integer basicArmor;
	private Integer basicHighAttack;
	private Integer basicLowerAttack;
	private Float lifeAdd;
	private Float armorAdd;
	private Float highAttackAdd;
	private Float lowerAttackAdd;
	private Integer attackSpeed;
	private Integer skillResist;
	private Integer critRate;
	private Integer dodgeRate;
	private Integer hitRate;
	private Integer skillCooling;
	private Integer moveSpeed;
	private Integer attackScope;
	private Integer activeAttackScope;
	private Integer type;
	private String baseSkill;
	private Integer quality;
	private String generateHeroName;

	// Constructors

	/** default constructor */
	public HHeroConstant() {
	}

	/** full constructor */
	public HHeroConstant(String heroName, String heroDesc, Integer basicLife,
			Integer basicArmor, Integer basicHighAttack,
			Integer basicLowerAttack, Float lifeAdd, Float armorAdd,
			Float highAttackAdd, Float lowerAttackAdd, Integer attackSpeed,
			Integer skillResist, Integer critRate, Integer dodgeRate,
			Integer hitRate, Integer skillCooling, Integer moveSpeed,
			Integer attackScope, Integer activeAttackScope, Integer type,
			String baseSkill, Integer quality, String generateHeroName) {
		this.heroName = heroName;
		this.heroDesc = heroDesc;
		this.basicLife = basicLife;
		this.basicArmor = basicArmor;
		this.basicHighAttack = basicHighAttack;
		this.basicLowerAttack = basicLowerAttack;
		this.lifeAdd = lifeAdd;
		this.armorAdd = armorAdd;
		this.highAttackAdd = highAttackAdd;
		this.lowerAttackAdd = lowerAttackAdd;
		this.attackSpeed = attackSpeed;
		this.skillResist = skillResist;
		this.critRate = critRate;
		this.dodgeRate = dodgeRate;
		this.hitRate = hitRate;
		this.skillCooling = skillCooling;
		this.moveSpeed = moveSpeed;
		this.attackScope = attackScope;
		this.activeAttackScope = activeAttackScope;
		this.type = type;
		this.baseSkill = baseSkill;
		this.quality = quality;
		this.generateHeroName = generateHeroName;
	}

	// Property accessors

	public Integer getHeroId() {
		return this.heroId;
	}

	public void setHeroId(Integer heroId) {
		this.heroId = heroId;
	}

	public String getHeroName() {
		return this.heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	public String getHeroDesc() {
		return this.heroDesc;
	}

	public void setHeroDesc(String heroDesc) {
		this.heroDesc = heroDesc;
	}

	public Integer getBasicLife() {
		return this.basicLife;
	}

	public void setBasicLife(Integer basicLife) {
		this.basicLife = basicLife;
	}

	public Integer getBasicArmor() {
		return this.basicArmor;
	}

	public void setBasicArmor(Integer basicArmor) {
		this.basicArmor = basicArmor;
	}

	public Integer getBasicHighAttack() {
		return this.basicHighAttack;
	}

	public void setBasicHighAttack(Integer basicHighAttack) {
		this.basicHighAttack = basicHighAttack;
	}

	public Integer getBasicLowerAttack() {
		return this.basicLowerAttack;
	}

	public void setBasicLowerAttack(Integer basicLowerAttack) {
		this.basicLowerAttack = basicLowerAttack;
	}

	public Float getLifeAdd() {
		return this.lifeAdd;
	}

	public void setLifeAdd(Float lifeAdd) {
		this.lifeAdd = lifeAdd;
	}

	public Float getArmorAdd() {
		return this.armorAdd;
	}

	public void setArmorAdd(Float armorAdd) {
		this.armorAdd = armorAdd;
	}

	public Float getHighAttackAdd() {
		return this.highAttackAdd;
	}

	public void setHighAttackAdd(Float highAttackAdd) {
		this.highAttackAdd = highAttackAdd;
	}

	public Float getLowerAttackAdd() {
		return this.lowerAttackAdd;
	}

	public void setLowerAttackAdd(Float lowerAttackAdd) {
		this.lowerAttackAdd = lowerAttackAdd;
	}

	public Integer getAttackSpeed() {
		return this.attackSpeed;
	}

	public void setAttackSpeed(Integer attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public Integer getSkillResist() {
		return this.skillResist;
	}

	public void setSkillResist(Integer skillResist) {
		this.skillResist = skillResist;
	}

	public Integer getCritRate() {
		return this.critRate;
	}

	public void setCritRate(Integer critRate) {
		this.critRate = critRate;
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

	public Integer getMoveSpeed() {
		return this.moveSpeed;
	}

	public void setMoveSpeed(Integer moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public Integer getAttackScope() {
		return this.attackScope;
	}

	public void setAttackScope(Integer attackScope) {
		this.attackScope = attackScope;
	}

	public Integer getActiveAttackScope() {
		return this.activeAttackScope;
	}

	public void setActiveAttackScope(Integer activeAttackScope) {
		this.activeAttackScope = activeAttackScope;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBaseSkill() {
		return this.baseSkill;
	}

	public void setBaseSkill(String baseSkill) {
		this.baseSkill = baseSkill;
	}

	public Integer getQuality() {
		return this.quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public String getGenerateHeroName() {
		return this.generateHeroName;
	}

	public void setGenerateHeroName(String generateHeroName) {
		this.generateHeroName = generateHeroName;
	}

}