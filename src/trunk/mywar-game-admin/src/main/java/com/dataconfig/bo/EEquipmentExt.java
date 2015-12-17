package com.dataconfig.bo;

/**
 * EEquipmentExt entity. @author MyEclipse Persistence Tools
 */

public class EEquipmentExt implements java.io.Serializable {

	// Fields

	private Integer equipmentId;
	private Float strengthValue;
	private Float strengthDeviant;
	private Float strengthCostMoney;
	private Integer qualityLevelUp;
	private String qualityCostMaterials;
	private Float qualityWreck;
	private Integer rankUp;
	private Integer rankCostMoney;
	private Integer buildingLevelLimit;
	private Integer changHeroWeapon;

	// Constructors

	/** default constructor */
	public EEquipmentExt() {
	}

	/** full constructor */
	public EEquipmentExt(Float strengthValue, Float strengthDeviant,
			Float strengthCostMoney, Integer qualityLevelUp,
			String qualityCostMaterials, Float qualityWreck, Integer rankUp,
			Integer rankCostMoney, Integer buildingLevelLimit,
			Integer changHeroWeapon) {
		this.strengthValue = strengthValue;
		this.strengthDeviant = strengthDeviant;
		this.strengthCostMoney = strengthCostMoney;
		this.qualityLevelUp = qualityLevelUp;
		this.qualityCostMaterials = qualityCostMaterials;
		this.qualityWreck = qualityWreck;
		this.rankUp = rankUp;
		this.rankCostMoney = rankCostMoney;
		this.buildingLevelLimit = buildingLevelLimit;
		this.changHeroWeapon = changHeroWeapon;
	}

	// Property accessors

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Float getStrengthValue() {
		return this.strengthValue;
	}

	public void setStrengthValue(Float strengthValue) {
		this.strengthValue = strengthValue;
	}

	public Float getStrengthDeviant() {
		return this.strengthDeviant;
	}

	public void setStrengthDeviant(Float strengthDeviant) {
		this.strengthDeviant = strengthDeviant;
	}

	public Float getStrengthCostMoney() {
		return this.strengthCostMoney;
	}

	public void setStrengthCostMoney(Float strengthCostMoney) {
		this.strengthCostMoney = strengthCostMoney;
	}

	public Integer getQualityLevelUp() {
		return this.qualityLevelUp;
	}

	public void setQualityLevelUp(Integer qualityLevelUp) {
		this.qualityLevelUp = qualityLevelUp;
	}

	public String getQualityCostMaterials() {
		return this.qualityCostMaterials;
	}

	public void setQualityCostMaterials(String qualityCostMaterials) {
		this.qualityCostMaterials = qualityCostMaterials;
	}

	public Float getQualityWreck() {
		return this.qualityWreck;
	}

	public void setQualityWreck(Float qualityWreck) {
		this.qualityWreck = qualityWreck;
	}

	public Integer getRankUp() {
		return this.rankUp;
	}

	public void setRankUp(Integer rankUp) {
		this.rankUp = rankUp;
	}

	public Integer getRankCostMoney() {
		return this.rankCostMoney;
	}

	public void setRankCostMoney(Integer rankCostMoney) {
		this.rankCostMoney = rankCostMoney;
	}

	public Integer getBuildingLevelLimit() {
		return this.buildingLevelLimit;
	}

	public void setBuildingLevelLimit(Integer buildingLevelLimit) {
		this.buildingLevelLimit = buildingLevelLimit;
	}

	public Integer getChangHeroWeapon() {
		return this.changHeroWeapon;
	}

	public void setChangHeroWeapon(Integer changHeroWeapon) {
		this.changHeroWeapon = changHeroWeapon;
	}

}