package com.dataconfig.bo;

/**
 * PPetExtra entity. @author MyEclipse Persistence Tools
 */

public class PPetExtra implements java.io.Serializable {

	// Fields

	private Integer type;
	private Float attackSpeedAdd;
	private Float lowAttackAdd;
	private Float highAttackAdd;
	private Float cirtRateAdd;
	private Float hitRateAdd;

	// Constructors

	/** default constructor */
	public PPetExtra() {
	}

	/** full constructor */
	public PPetExtra(Float attackSpeedAdd, Float lowAttackAdd,
			Float highAttackAdd, Float cirtRateAdd, Float hitRateAdd) {
		this.attackSpeedAdd = attackSpeedAdd;
		this.lowAttackAdd = lowAttackAdd;
		this.highAttackAdd = highAttackAdd;
		this.cirtRateAdd = cirtRateAdd;
		this.hitRateAdd = hitRateAdd;
	}

	// Property accessors

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Float getAttackSpeedAdd() {
		return this.attackSpeedAdd;
	}

	public void setAttackSpeedAdd(Float attackSpeedAdd) {
		this.attackSpeedAdd = attackSpeedAdd;
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

}