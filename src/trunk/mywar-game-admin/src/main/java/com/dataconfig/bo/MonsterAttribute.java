package com.dataconfig.bo;

/**
 * TMonsterAttribute entity. @author MyEclipse Persistence Tools
 */

public class MonsterAttribute implements java.io.Serializable {

	// Fields

	private Integer monsterId;
	private String name;
	private Integer attack;
	private Integer life;
	private Integer armor;
	private Integer attackSpeed;
	private Integer moveSpeed;
	private Integer attackType;
	private Integer attackRange;
	private String monsterDescription;
	private String skillIds;

	// Constructors

	/** default constructor */
	public MonsterAttribute() {
	}

	/** minimal constructor */
	public MonsterAttribute(String name, Integer attack, Integer life,
			Integer armor, Integer attackSpeed, Integer moveSpeed,
			Integer attackType, Integer attackRange, String monsterDescription) {
		this.name = name;
		this.attack = attack;
		this.life = life;
		this.armor = armor;
		this.attackSpeed = attackSpeed;
		this.moveSpeed = moveSpeed;
		this.attackType = attackType;
		this.attackRange = attackRange;
		this.monsterDescription = monsterDescription;
	}

	/** full constructor */
	public MonsterAttribute(String name, Integer attack, Integer life,
			Integer armor, Integer attackSpeed, Integer moveSpeed,
			Integer attackType, Integer attackRange, String monsterDescription,
			String skillIds) {
		this.name = name;
		this.attack = attack;
		this.life = life;
		this.armor = armor;
		this.attackSpeed = attackSpeed;
		this.moveSpeed = moveSpeed;
		this.attackType = attackType;
		this.attackRange = attackRange;
		this.monsterDescription = monsterDescription;
		this.skillIds = skillIds;
	}

	// Property accessors

	public Integer getMonsterId() {
		return this.monsterId;
	}

	public void setMonsterId(Integer monsterId) {
		this.monsterId = monsterId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAttack() {
		return this.attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
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

	public Integer getAttackSpeed() {
		return this.attackSpeed;
	}

	public void setAttackSpeed(Integer attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public Integer getMoveSpeed() {
		return this.moveSpeed;
	}

	public void setMoveSpeed(Integer moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public Integer getAttackType() {
		return this.attackType;
	}

	public void setAttackType(Integer attackType) {
		this.attackType = attackType;
	}

	public Integer getAttackRange() {
		return this.attackRange;
	}

	public void setAttackRange(Integer attackRange) {
		this.attackRange = attackRange;
	}

	public String getMonsterDescription() {
		return this.monsterDescription;
	}

	public void setMonsterDescription(String monsterDescription) {
		this.monsterDescription = monsterDescription;
	}

	public String getSkillIds() {
		return this.skillIds;
	}

	public void setSkillIds(String skillIds) {
		this.skillIds = skillIds;
	}

}