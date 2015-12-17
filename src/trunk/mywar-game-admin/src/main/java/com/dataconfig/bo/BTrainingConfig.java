package com.dataconfig.bo;

/**
 * BTrainingConfig entity. @author MyEclipse Persistence Tools
 */

public class BTrainingConfig implements java.io.Serializable {

	// Fields

	private Integer level;
	private String trainingTime;
	private String costMoney;
	private Integer moneyGetExp;
	private String costGolden;
	private Integer goldenGetExp;
	private Integer devilTrainingGetExp;

	// Constructors

	/** default constructor */
	public BTrainingConfig() {
	}

	/** full constructor */
	public BTrainingConfig(String trainingTime, String costMoney,
			Integer moneyGetExp, String costGolden, Integer goldenGetExp,
			Integer devilTrainingGetExp) {
		this.trainingTime = trainingTime;
		this.costMoney = costMoney;
		this.moneyGetExp = moneyGetExp;
		this.costGolden = costGolden;
		this.goldenGetExp = goldenGetExp;
		this.devilTrainingGetExp = devilTrainingGetExp;
	}

	// Property accessors

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getTrainingTime() {
		return this.trainingTime;
	}

	public void setTrainingTime(String trainingTime) {
		this.trainingTime = trainingTime;
	}

	public String getCostMoney() {
		return this.costMoney;
	}

	public void setCostMoney(String costMoney) {
		this.costMoney = costMoney;
	}

	public Integer getMoneyGetExp() {
		return this.moneyGetExp;
	}

	public void setMoneyGetExp(Integer moneyGetExp) {
		this.moneyGetExp = moneyGetExp;
	}

	public String getCostGolden() {
		return this.costGolden;
	}

	public void setCostGolden(String costGolden) {
		this.costGolden = costGolden;
	}

	public Integer getGoldenGetExp() {
		return this.goldenGetExp;
	}

	public void setGoldenGetExp(Integer goldenGetExp) {
		this.goldenGetExp = goldenGetExp;
	}

	public Integer getDevilTrainingGetExp() {
		return this.devilTrainingGetExp;
	}

	public void setDevilTrainingGetExp(Integer devilTrainingGetExp) {
		this.devilTrainingGetExp = devilTrainingGetExp;
	}

}