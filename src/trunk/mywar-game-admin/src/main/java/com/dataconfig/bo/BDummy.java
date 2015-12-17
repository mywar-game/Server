package com.dataconfig.bo;

/**
 * BDummy entity. @author MyEclipse Persistence Tools
 */

public class BDummy implements java.io.Serializable {

	// Fields

	private Integer dummyId;
	private String dummyName;
	private Integer dummyDuration;
	private Integer buyPrice;
	private Float factor1;
	private Integer factor2;
	private Float moneyFactor1;
	private Integer moneyFactor2;

	// Constructors

	/** default constructor */
	public BDummy() {
	}

	/** full constructor */
	public BDummy(String dummyName, Integer dummyDuration, Integer buyPrice,
			Float factor1, Integer factor2, Float moneyFactor1,
			Integer moneyFactor2) {
		this.dummyName = dummyName;
		this.dummyDuration = dummyDuration;
		this.buyPrice = buyPrice;
		this.factor1 = factor1;
		this.factor2 = factor2;
		this.moneyFactor1 = moneyFactor1;
		this.moneyFactor2 = moneyFactor2;
	}

	// Property accessors

	public Integer getDummyId() {
		return this.dummyId;
	}

	public void setDummyId(Integer dummyId) {
		this.dummyId = dummyId;
	}

	public String getDummyName() {
		return this.dummyName;
	}

	public void setDummyName(String dummyName) {
		this.dummyName = dummyName;
	}

	public Integer getDummyDuration() {
		return this.dummyDuration;
	}

	public void setDummyDuration(Integer dummyDuration) {
		this.dummyDuration = dummyDuration;
	}

	public Integer getBuyPrice() {
		return this.buyPrice;
	}

	public void setBuyPrice(Integer buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Float getFactor1() {
		return this.factor1;
	}

	public void setFactor1(Float factor1) {
		this.factor1 = factor1;
	}

	public Integer getFactor2() {
		return this.factor2;
	}

	public void setFactor2(Integer factor2) {
		this.factor2 = factor2;
	}

	public Float getMoneyFactor1() {
		return this.moneyFactor1;
	}

	public void setMoneyFactor1(Float moneyFactor1) {
		this.moneyFactor1 = moneyFactor1;
	}

	public Integer getMoneyFactor2() {
		return this.moneyFactor2;
	}

	public void setMoneyFactor2(Integer moneyFactor2) {
		this.moneyFactor2 = moneyFactor2;
	}

}