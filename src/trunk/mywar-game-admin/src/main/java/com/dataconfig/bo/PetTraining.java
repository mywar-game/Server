package com.dataconfig.bo;

/**
 * PPetTraining entity. @author MyEclipse Persistence Tools
 */

public class PetTraining implements java.io.Serializable {

	// Fields

	private Integer petTrainingId;
	private Integer petTrainingLevel;
	private Integer petTrainingStar;
	private Integer needExp;
	private Integer commonSmallCritPercent;
	private Integer highSmallCritPercent;
	private Integer smallCritMax;
	private Integer commonBigCritPercent;
	private Integer highBigCritPercent;
	private Integer bigCritMax;
	private Float lowAttackAdd;
	private Float highAttackAdd;
	private Float attackSpeedAdd;
	private Float cirtRateAdd;
	private Float hitRateAdd;

	// Constructors

	/** default constructor */
	public PetTraining() {
	}

	/** full constructor */
	public PetTraining(Integer petTrainingLevel, Integer petTrainingStar,
			Integer needExp, Integer commonSmallCritPercent,
			Integer highSmallCritPercent, Integer smallCritMax,
			Integer commonBigCritPercent, Integer highBigCritPercent,
			Integer bigCritMax, Float lowAttackAdd, Float highAttackAdd,
			Float attackSpeedAdd, Float cirtRateAdd, Float hitRateAdd) {
		this.petTrainingLevel = petTrainingLevel;
		this.petTrainingStar = petTrainingStar;
		this.needExp = needExp;
		this.commonSmallCritPercent = commonSmallCritPercent;
		this.highSmallCritPercent = highSmallCritPercent;
		this.smallCritMax = smallCritMax;
		this.commonBigCritPercent = commonBigCritPercent;
		this.highBigCritPercent = highBigCritPercent;
		this.bigCritMax = bigCritMax;
		this.lowAttackAdd = lowAttackAdd;
		this.highAttackAdd = highAttackAdd;
		this.attackSpeedAdd = attackSpeedAdd;
		this.cirtRateAdd = cirtRateAdd;
		this.hitRateAdd = hitRateAdd;
	}

	// Property accessors

	public Integer getPetTrainingId() {
		return this.petTrainingId;
	}

	public void setPetTrainingId(Integer petTrainingId) {
		this.petTrainingId = petTrainingId;
	}

	public Integer getPetTrainingLevel() {
		return this.petTrainingLevel;
	}

	public void setPetTrainingLevel(Integer petTrainingLevel) {
		this.petTrainingLevel = petTrainingLevel;
	}

	public Integer getPetTrainingStar() {
		return this.petTrainingStar;
	}

	public void setPetTrainingStar(Integer petTrainingStar) {
		this.petTrainingStar = petTrainingStar;
	}

	public Integer getNeedExp() {
		return this.needExp;
	}

	public void setNeedExp(Integer needExp) {
		this.needExp = needExp;
	}

	public Integer getCommonSmallCritPercent() {
		return this.commonSmallCritPercent;
	}

	public void setCommonSmallCritPercent(Integer commonSmallCritPercent) {
		this.commonSmallCritPercent = commonSmallCritPercent;
	}

	public Integer getHighSmallCritPercent() {
		return this.highSmallCritPercent;
	}

	public void setHighSmallCritPercent(Integer highSmallCritPercent) {
		this.highSmallCritPercent = highSmallCritPercent;
	}

	public Integer getSmallCritMax() {
		return this.smallCritMax;
	}

	public void setSmallCritMax(Integer smallCritMax) {
		this.smallCritMax = smallCritMax;
	}

	public Integer getCommonBigCritPercent() {
		return this.commonBigCritPercent;
	}

	public void setCommonBigCritPercent(Integer commonBigCritPercent) {
		this.commonBigCritPercent = commonBigCritPercent;
	}

	public Integer getHighBigCritPercent() {
		return this.highBigCritPercent;
	}

	public void setHighBigCritPercent(Integer highBigCritPercent) {
		this.highBigCritPercent = highBigCritPercent;
	}

	public Integer getBigCritMax() {
		return this.bigCritMax;
	}

	public void setBigCritMax(Integer bigCritMax) {
		this.bigCritMax = bigCritMax;
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

	public Float getAttackSpeedAdd() {
		return this.attackSpeedAdd;
	}

	public void setAttackSpeedAdd(Float attackSpeedAdd) {
		this.attackSpeedAdd = attackSpeedAdd;
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