package com.dataconfig.bo;

/**
 * HHeroIntimacyConstant entity. @author MyEclipse Persistence Tools
 */

public class HHeroIntimacyConstant implements java.io.Serializable {

	// Fields

	private Integer heroIntimacyId;
	private Integer intimacyValue;
	private Integer openLevel;
	private Integer costMoney;
	private Integer costGolden;
	private Integer goldenAdditionalValue;
	private Integer canBuyForDay;

	// Constructors

	/** default constructor */
	public HHeroIntimacyConstant() {
	}

	/** full constructor */
	public HHeroIntimacyConstant(Integer intimacyValue, Integer openLevel,
			Integer costMoney, Integer costGolden,
			Integer goldenAdditionalValue, Integer canBuyForDay) {
		this.intimacyValue = intimacyValue;
		this.openLevel = openLevel;
		this.costMoney = costMoney;
		this.costGolden = costGolden;
		this.goldenAdditionalValue = goldenAdditionalValue;
		this.canBuyForDay = canBuyForDay;
	}

	// Property accessors

	public Integer getHeroIntimacyId() {
		return this.heroIntimacyId;
	}

	public void setHeroIntimacyId(Integer heroIntimacyId) {
		this.heroIntimacyId = heroIntimacyId;
	}

	public Integer getIntimacyValue() {
		return this.intimacyValue;
	}

	public void setIntimacyValue(Integer intimacyValue) {
		this.intimacyValue = intimacyValue;
	}

	public Integer getOpenLevel() {
		return this.openLevel;
	}

	public void setOpenLevel(Integer openLevel) {
		this.openLevel = openLevel;
	}

	public Integer getCostMoney() {
		return this.costMoney;
	}

	public void setCostMoney(Integer costMoney) {
		this.costMoney = costMoney;
	}

	public Integer getCostGolden() {
		return this.costGolden;
	}

	public void setCostGolden(Integer costGolden) {
		this.costGolden = costGolden;
	}

	public Integer getGoldenAdditionalValue() {
		return this.goldenAdditionalValue;
	}

	public void setGoldenAdditionalValue(Integer goldenAdditionalValue) {
		this.goldenAdditionalValue = goldenAdditionalValue;
	}

	public Integer getCanBuyForDay() {
		return this.canBuyForDay;
	}

	public void setCanBuyForDay(Integer canBuyForDay) {
		this.canBuyForDay = canBuyForDay;
	}

}