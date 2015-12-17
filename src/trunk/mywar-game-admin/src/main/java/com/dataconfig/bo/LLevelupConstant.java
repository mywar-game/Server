package com.dataconfig.bo;

/**
 * LLevelupConstant entity. @author MyEclipse Persistence Tools
 */

public class LLevelupConstant implements java.io.Serializable {

	// Fields

	private Integer level;
	private Integer heroNeedExp;
	private Integer userNeedRenown;
	private Integer petLevelupMoney;
	private Integer levelUpExps;
	private Integer skillNeedExp;

	// Constructors

	/** default constructor */
	public LLevelupConstant() {
	}

	/** full constructor */
	public LLevelupConstant(Integer heroNeedExp, Integer userNeedRenown,
			Integer petLevelupMoney, Integer levelUpExps, Integer skillNeedExp) {
		this.heroNeedExp = heroNeedExp;
		this.userNeedRenown = userNeedRenown;
		this.petLevelupMoney = petLevelupMoney;
		this.levelUpExps = levelUpExps;
		this.skillNeedExp = skillNeedExp;
	}

	// Property accessors

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getHeroNeedExp() {
		return this.heroNeedExp;
	}

	public void setHeroNeedExp(Integer heroNeedExp) {
		this.heroNeedExp = heroNeedExp;
	}

	public Integer getUserNeedRenown() {
		return this.userNeedRenown;
	}

	public void setUserNeedRenown(Integer userNeedRenown) {
		this.userNeedRenown = userNeedRenown;
	}

	public Integer getPetLevelupMoney() {
		return this.petLevelupMoney;
	}

	public void setPetLevelupMoney(Integer petLevelupMoney) {
		this.petLevelupMoney = petLevelupMoney;
	}

	public Integer getLevelUpExps() {
		return this.levelUpExps;
	}

	public void setLevelUpExps(Integer levelUpExps) {
		this.levelUpExps = levelUpExps;
	}

	public Integer getSkillNeedExp() {
		return this.skillNeedExp;
	}

	public void setSkillNeedExp(Integer skillNeedExp) {
		this.skillNeedExp = skillNeedExp;
	}

}