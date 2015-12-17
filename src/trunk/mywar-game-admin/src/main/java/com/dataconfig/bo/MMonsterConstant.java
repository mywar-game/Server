package com.dataconfig.bo;

/**
 * MMonsterConstant entity. @author MyEclipse Persistence Tools
 */

public class MMonsterConstant implements java.io.Serializable {

	// Fields

	private Integer monsterId;
	private String monsterName;
	private Integer type;
	private Integer attribute;
	private Integer quality;
	private Integer monsterLevel;
	private Integer basicLife;
	private Integer basicArmor;
	private Integer basicHighAttack;
	private Integer baseicLowerAttack;
	private Integer lifeAdd;
	private Integer armorAdd;
	private Integer attackAdd;
	private Integer attackSpeed;
	private Integer critRate;
	private Integer dodgeRate;
	private Integer hitRate;
	private Integer skillCooling;
	private Integer moveSpeed;
	private Integer attackScope;
	private Integer activeAttackScope;
	private String baseSkill;
	private Integer modleId;
	private Integer color;
	private Integer arenaId;
	private Integer battleModel;

	// Constructors

	/** default constructor */
	public MMonsterConstant() {
	}

	/** minimal constructor */
	public MMonsterConstant(String monsterName, Integer type,
			Integer attribute, Integer quality, Integer monsterLevel,
			Integer basicLife, Integer basicArmor, Integer basicHighAttack,
			Integer baseicLowerAttack, Integer lifeAdd, Integer armorAdd,
			Integer attackAdd, Integer attackSpeed, Integer critRate,
			Integer dodgeRate, Integer hitRate, Integer skillCooling,
			Integer moveSpeed, Integer attackScope, Integer activeAttackScope,
			Integer color, Integer arenaId, Integer battleModel) {
		this.monsterName = monsterName;
		this.type = type;
		this.attribute = attribute;
		this.quality = quality;
		this.monsterLevel = monsterLevel;
		this.basicLife = basicLife;
		this.basicArmor = basicArmor;
		this.basicHighAttack = basicHighAttack;
		this.baseicLowerAttack = baseicLowerAttack;
		this.lifeAdd = lifeAdd;
		this.armorAdd = armorAdd;
		this.attackAdd = attackAdd;
		this.attackSpeed = attackSpeed;
		this.critRate = critRate;
		this.dodgeRate = dodgeRate;
		this.hitRate = hitRate;
		this.skillCooling = skillCooling;
		this.moveSpeed = moveSpeed;
		this.attackScope = attackScope;
		this.activeAttackScope = activeAttackScope;
		this.color = color;
		this.arenaId = arenaId;
		this.battleModel = battleModel;
	}

	/** full constructor */
	public MMonsterConstant(String monsterName, Integer type,
			Integer attribute, Integer quality, Integer monsterLevel,
			Integer basicLife, Integer basicArmor, Integer basicHighAttack,
			Integer baseicLowerAttack, Integer lifeAdd, Integer armorAdd,
			Integer attackAdd, Integer attackSpeed, Integer critRate,
			Integer dodgeRate, Integer hitRate, Integer skillCooling,
			Integer moveSpeed, Integer attackScope, Integer activeAttackScope,
			String baseSkill, Integer modleId, Integer color, Integer arenaId,
			Integer battleModel) {
		this.monsterName = monsterName;
		this.type = type;
		this.attribute = attribute;
		this.quality = quality;
		this.monsterLevel = monsterLevel;
		this.basicLife = basicLife;
		this.basicArmor = basicArmor;
		this.basicHighAttack = basicHighAttack;
		this.baseicLowerAttack = baseicLowerAttack;
		this.lifeAdd = lifeAdd;
		this.armorAdd = armorAdd;
		this.attackAdd = attackAdd;
		this.attackSpeed = attackSpeed;
		this.critRate = critRate;
		this.dodgeRate = dodgeRate;
		this.hitRate = hitRate;
		this.skillCooling = skillCooling;
		this.moveSpeed = moveSpeed;
		this.attackScope = attackScope;
		this.activeAttackScope = activeAttackScope;
		this.baseSkill = baseSkill;
		this.modleId = modleId;
		this.color = color;
		this.arenaId = arenaId;
		this.battleModel = battleModel;
	}

	// Property accessors

	public Integer getMonsterId() {
		return this.monsterId;
	}

	public void setMonsterId(Integer monsterId) {
		this.monsterId = monsterId;
	}

	public String getMonsterName() {
		return this.monsterName;
	}

	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Integer attribute) {
		this.attribute = attribute;
	}

	public Integer getQuality() {
		return this.quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getMonsterLevel() {
		return this.monsterLevel;
	}

	public void setMonsterLevel(Integer monsterLevel) {
		this.monsterLevel = monsterLevel;
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

	public Integer getBaseicLowerAttack() {
		return this.baseicLowerAttack;
	}

	public void setBaseicLowerAttack(Integer baseicLowerAttack) {
		this.baseicLowerAttack = baseicLowerAttack;
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

	public Integer getAttackAdd() {
		return this.attackAdd;
	}

	public void setAttackAdd(Integer attackAdd) {
		this.attackAdd = attackAdd;
	}

	public Integer getAttackSpeed() {
		return this.attackSpeed;
	}

	public void setAttackSpeed(Integer attackSpeed) {
		this.attackSpeed = attackSpeed;
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

	public String getBaseSkill() {
		return this.baseSkill;
	}

	public void setBaseSkill(String baseSkill) {
		this.baseSkill = baseSkill;
	}

	public Integer getModleId() {
		return this.modleId;
	}

	public void setModleId(Integer modleId) {
		this.modleId = modleId;
	}

	public Integer getColor() {
		return this.color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getArenaId() {
		return this.arenaId;
	}

	public void setArenaId(Integer arenaId) {
		this.arenaId = arenaId;
	}

	public Integer getBattleModel() {
		return this.battleModel;
	}

	public void setBattleModel(Integer battleModel) {
		this.battleModel = battleModel;
	}

}