package com.dataconfig.bo;

/**
 * PPetConstant entity. @author MyEclipse Persistence Tools
 */

public class PPetConstant implements java.io.Serializable {

	// Fields

	private Integer petId;
	private String name;
	private String description;
	private Integer quality;
	private Integer stamina;
	private Integer attackSpeed;
	private Float attackSpeedAdd;
	private Integer lowAttack;
	private Float lowAttackAdd;
	private Integer highAttack;
	private Float highAttackAdd;
	private Integer cirtRate;
	private Float cirtRateAdd;
	private Integer hitRate;
	private Float hitRateAdd;
	private Integer moveSpeed;
	private Integer attackScope;
	private Integer activeAttackScope;
	private Integer life;
	private Integer buyType;
	private Integer type;
	private Integer price;
	private Integer unlockGolden;
	private Integer unlockLevel;

	// Constructors

	/** default constructor */
	public PPetConstant() {
	}

	/** full constructor */
	public PPetConstant(String name, String description, Integer quality,
			Integer stamina, Integer attackSpeed, Float attackSpeedAdd,
			Integer lowAttack, Float lowAttackAdd, Integer highAttack,
			Float highAttackAdd, Integer cirtRate, Float cirtRateAdd,
			Integer hitRate, Float hitRateAdd, Integer moveSpeed,
			Integer attackScope, Integer activeAttackScope, Integer life,
			Integer buyType, Integer type, Integer price, Integer unlockGolden,
			Integer unlockLevel) {
		this.name = name;
		this.description = description;
		this.quality = quality;
		this.stamina = stamina;
		this.attackSpeed = attackSpeed;
		this.attackSpeedAdd = attackSpeedAdd;
		this.lowAttack = lowAttack;
		this.lowAttackAdd = lowAttackAdd;
		this.highAttack = highAttack;
		this.highAttackAdd = highAttackAdd;
		this.cirtRate = cirtRate;
		this.cirtRateAdd = cirtRateAdd;
		this.hitRate = hitRate;
		this.hitRateAdd = hitRateAdd;
		this.moveSpeed = moveSpeed;
		this.attackScope = attackScope;
		this.activeAttackScope = activeAttackScope;
		this.life = life;
		this.buyType = buyType;
		this.type = type;
		this.price = price;
		this.unlockGolden = unlockGolden;
		this.unlockLevel = unlockLevel;
	}

	// Property accessors

	public Integer getPetId() {
		return this.petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuality() {
		return this.quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getStamina() {
		return this.stamina;
	}

	public void setStamina(Integer stamina) {
		this.stamina = stamina;
	}

	public Integer getAttackSpeed() {
		return this.attackSpeed;
	}

	public void setAttackSpeed(Integer attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public Float getAttackSpeedAdd() {
		return this.attackSpeedAdd;
	}

	public void setAttackSpeedAdd(Float attackSpeedAdd) {
		this.attackSpeedAdd = attackSpeedAdd;
	}

	public Integer getLowAttack() {
		return this.lowAttack;
	}

	public void setLowAttack(Integer lowAttack) {
		this.lowAttack = lowAttack;
	}

	public Float getLowAttackAdd() {
		return this.lowAttackAdd;
	}

	public void setLowAttackAdd(Float lowAttackAdd) {
		this.lowAttackAdd = lowAttackAdd;
	}

	public Integer getHighAttack() {
		return this.highAttack;
	}

	public void setHighAttack(Integer highAttack) {
		this.highAttack = highAttack;
	}

	public Float getHighAttackAdd() {
		return this.highAttackAdd;
	}

	public void setHighAttackAdd(Float highAttackAdd) {
		this.highAttackAdd = highAttackAdd;
	}

	public Integer getCirtRate() {
		return this.cirtRate;
	}

	public void setCirtRate(Integer cirtRate) {
		this.cirtRate = cirtRate;
	}

	public Float getCirtRateAdd() {
		return this.cirtRateAdd;
	}

	public void setCirtRateAdd(Float cirtRateAdd) {
		this.cirtRateAdd = cirtRateAdd;
	}

	public Integer getHitRate() {
		return this.hitRate;
	}

	public void setHitRate(Integer hitRate) {
		this.hitRate = hitRate;
	}

	public Float getHitRateAdd() {
		return this.hitRateAdd;
	}

	public void setHitRateAdd(Float hitRateAdd) {
		this.hitRateAdd = hitRateAdd;
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

	public Integer getLife() {
		return this.life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}

	public Integer getBuyType() {
		return this.buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getUnlockGolden() {
		return this.unlockGolden;
	}

	public void setUnlockGolden(Integer unlockGolden) {
		this.unlockGolden = unlockGolden;
	}

	public Integer getUnlockLevel() {
		return this.unlockLevel;
	}

	public void setUnlockLevel(Integer unlockLevel) {
		this.unlockLevel = unlockLevel;
	}

}