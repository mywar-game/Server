package com.dataconfig.bo;

/**
 * MonsterGeneConstant entity. @author MyEclipse Persistence Tools
 */

public class MonsterGeneConstant implements java.io.Serializable {

	// Fields

	private Integer monsterGeneId;
	private String monsterGeneName;
	private String monsterGeneDescription;
	private String monsterGeneSkill;
	private Float monsterGeneLowAttackAdd;
	private Float monsterGeneHighAttackAdd;
	private Float monsterGeneAttackSpeedAdd;
	private Float monsterGeneHitRateAdd;
	private Float monsterGeneCirtAdd;
	private Integer monsterId;
	private Integer costMoney;
	private Integer fakeLastTime;
	private Integer goldenFakeLastTime;

	// Constructors

	/** default constructor */
	public MonsterGeneConstant() {
	}

	/** full constructor */
	public MonsterGeneConstant(String monsterGeneName,
			String monsterGeneDescription, String monsterGeneSkill,
			Float monsterGeneLowAttackAdd, Float monsterGeneHighAttackAdd,
			Float monsterGeneAttackSpeedAdd, Float monsterGeneHitRateAdd,
			Float monsterGeneCirtAdd, Integer monsterId, Integer costMoney,
			Integer fakeLastTime, Integer goldenFakeLastTime) {
		this.monsterGeneName = monsterGeneName;
		this.monsterGeneDescription = monsterGeneDescription;
		this.monsterGeneSkill = monsterGeneSkill;
		this.monsterGeneLowAttackAdd = monsterGeneLowAttackAdd;
		this.monsterGeneHighAttackAdd = monsterGeneHighAttackAdd;
		this.monsterGeneAttackSpeedAdd = monsterGeneAttackSpeedAdd;
		this.monsterGeneHitRateAdd = monsterGeneHitRateAdd;
		this.monsterGeneCirtAdd = monsterGeneCirtAdd;
		this.monsterId = monsterId;
		this.costMoney = costMoney;
		this.fakeLastTime = fakeLastTime;
		this.goldenFakeLastTime = goldenFakeLastTime;
	}

	// Property accessors

	public Integer getMonsterGeneId() {
		return this.monsterGeneId;
	}

	public void setMonsterGeneId(Integer monsterGeneId) {
		this.monsterGeneId = monsterGeneId;
	}

	public String getMonsterGeneName() {
		return this.monsterGeneName;
	}

	public void setMonsterGeneName(String monsterGeneName) {
		this.monsterGeneName = monsterGeneName;
	}

	public String getMonsterGeneDescription() {
		return this.monsterGeneDescription;
	}

	public void setMonsterGeneDescription(String monsterGeneDescription) {
		this.monsterGeneDescription = monsterGeneDescription;
	}

	public String getMonsterGeneSkill() {
		return this.monsterGeneSkill;
	}

	public void setMonsterGeneSkill(String monsterGeneSkill) {
		this.monsterGeneSkill = monsterGeneSkill;
	}

	public Float getMonsterGeneLowAttackAdd() {
		return this.monsterGeneLowAttackAdd;
	}

	public void setMonsterGeneLowAttackAdd(Float monsterGeneLowAttackAdd) {
		this.monsterGeneLowAttackAdd = monsterGeneLowAttackAdd;
	}

	public Float getMonsterGeneHighAttackAdd() {
		return this.monsterGeneHighAttackAdd;
	}

	public void setMonsterGeneHighAttackAdd(Float monsterGeneHighAttackAdd) {
		this.monsterGeneHighAttackAdd = monsterGeneHighAttackAdd;
	}

	public Float getMonsterGeneAttackSpeedAdd() {
		return this.monsterGeneAttackSpeedAdd;
	}

	public void setMonsterGeneAttackSpeedAdd(Float monsterGeneAttackSpeedAdd) {
		this.monsterGeneAttackSpeedAdd = monsterGeneAttackSpeedAdd;
	}

	public Float getMonsterGeneHitRateAdd() {
		return this.monsterGeneHitRateAdd;
	}

	public void setMonsterGeneHitRateAdd(Float monsterGeneHitRateAdd) {
		this.monsterGeneHitRateAdd = monsterGeneHitRateAdd;
	}

	public Float getMonsterGeneCirtAdd() {
		return this.monsterGeneCirtAdd;
	}

	public void setMonsterGeneCirtAdd(Float monsterGeneCirtAdd) {
		this.monsterGeneCirtAdd = monsterGeneCirtAdd;
	}

	public Integer getMonsterId() {
		return this.monsterId;
	}

	public void setMonsterId(Integer monsterId) {
		this.monsterId = monsterId;
	}

	public Integer getCostMoney() {
		return this.costMoney;
	}

	public void setCostMoney(Integer costMoney) {
		this.costMoney = costMoney;
	}

	public Integer getFakeLastTime() {
		return this.fakeLastTime;
	}

	public void setFakeLastTime(Integer fakeLastTime) {
		this.fakeLastTime = fakeLastTime;
	}

	public Integer getGoldenFakeLastTime() {
		return this.goldenFakeLastTime;
	}

	public void setGoldenFakeLastTime(Integer goldenFakeLastTime) {
		this.goldenFakeLastTime = goldenFakeLastTime;
	}

}