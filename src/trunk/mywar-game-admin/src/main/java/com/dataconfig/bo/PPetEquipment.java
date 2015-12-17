package com.dataconfig.bo;

/**
 * PPetEquipment entity. @author MyEclipse Persistence Tools
 */

public class PPetEquipment implements java.io.Serializable {

	// Fields

	private Integer petEquipmentId;
	private Integer type;
	private Float lowAttackAdd;
	private Float highAttackAdd;
	private Float cirtRateAdd;
	private Float hitRateAdd;
	private Float attackSpeedAdd;
	private Integer petLevelMax;

	// Constructors

	/** default constructor */
	public PPetEquipment() {
	}

	/** full constructor */
	public PPetEquipment(Integer type, Float lowAttackAdd, Float highAttackAdd,
			Float cirtRateAdd, Float hitRateAdd, Float attackSpeedAdd,
			Integer petLevelMax) {
		this.type = type;
		this.lowAttackAdd = lowAttackAdd;
		this.highAttackAdd = highAttackAdd;
		this.cirtRateAdd = cirtRateAdd;
		this.hitRateAdd = hitRateAdd;
		this.attackSpeedAdd = attackSpeedAdd;
		this.petLevelMax = petLevelMax;
	}

	// Property accessors

	public Integer getPetEquipmentId() {
		return this.petEquipmentId;
	}

	public void setPetEquipmentId(Integer petEquipmentId) {
		this.petEquipmentId = petEquipmentId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Float getLowAttackAdd() {
		return this.lowAttackAdd;
	}

	public void setLowAttackAdd(Float lowAttackAdd) {
		this.lowAttackAdd = lowAttackAdd;
	}

	public Float getHighAttackAdd() {
		return this.highAttackAdd;
	}

	public void setHighAttackAdd(Float highAttackAdd) {
		this.highAttackAdd = highAttackAdd;
	}

	public Float getCirtRateAdd() {
		return this.cirtRateAdd;
	}

	public void setCirtRateAdd(Float cirtRateAdd) {
		this.cirtRateAdd = cirtRateAdd;
	}

	public Float getHitRateAdd() {
		return this.hitRateAdd;
	}

	public void setHitRateAdd(Float hitRateAdd) {
		this.hitRateAdd = hitRateAdd;
	}

	public Float getAttackSpeedAdd() {
		return this.attackSpeedAdd;
	}

	public void setAttackSpeedAdd(Float attackSpeedAdd) {
		this.attackSpeedAdd = attackSpeedAdd;
	}

	public Integer getPetLevelMax() {
		return this.petLevelMax;
	}

	public void setPetLevelMax(Integer petLevelMax) {
		this.petLevelMax = petLevelMax;
	}

}