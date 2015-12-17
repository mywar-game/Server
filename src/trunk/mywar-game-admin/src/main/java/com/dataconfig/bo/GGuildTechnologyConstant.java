package com.dataconfig.bo;

/**
 * GGuildTechnologyConstant entity. @author MyEclipse Persistence Tools
 */

public class GGuildTechnologyConstant implements java.io.Serializable {

	// Fields

	private Integer guildTechnologyId;
	private Integer technologyId;
	private String technologyName;
	private Integer technologyLevel;
	private Integer vaue;
	private Integer valueType;
	private Integer costGuildGrain;
	private Integer costGuildMineral;
	private Integer costGuildMoney;

	// Constructors

	/** default constructor */
	public GGuildTechnologyConstant() {
	}

	/** full constructor */
	public GGuildTechnologyConstant(Integer technologyId,
			String technologyName, Integer technologyLevel, Integer vaue,
			Integer valueType, Integer costGuildGrain,
			Integer costGuildMineral, Integer costGuildMoney) {
		this.technologyId = technologyId;
		this.technologyName = technologyName;
		this.technologyLevel = technologyLevel;
		this.vaue = vaue;
		this.valueType = valueType;
		this.costGuildGrain = costGuildGrain;
		this.costGuildMineral = costGuildMineral;
		this.costGuildMoney = costGuildMoney;
	}

	// Property accessors

	public Integer getGuildTechnologyId() {
		return this.guildTechnologyId;
	}

	public void setGuildTechnologyId(Integer guildTechnologyId) {
		this.guildTechnologyId = guildTechnologyId;
	}

	public Integer getTechnologyId() {
		return this.technologyId;
	}

	public void setTechnologyId(Integer technologyId) {
		this.technologyId = technologyId;
	}

	public String getTechnologyName() {
		return this.technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public Integer getTechnologyLevel() {
		return this.technologyLevel;
	}

	public void setTechnologyLevel(Integer technologyLevel) {
		this.technologyLevel = technologyLevel;
	}

	public Integer getVaue() {
		return this.vaue;
	}

	public void setVaue(Integer vaue) {
		this.vaue = vaue;
	}

	public Integer getValueType() {
		return this.valueType;
	}

	public void setValueType(Integer valueType) {
		this.valueType = valueType;
	}

	public Integer getCostGuildGrain() {
		return this.costGuildGrain;
	}

	public void setCostGuildGrain(Integer costGuildGrain) {
		this.costGuildGrain = costGuildGrain;
	}

	public Integer getCostGuildMineral() {
		return this.costGuildMineral;
	}

	public void setCostGuildMineral(Integer costGuildMineral) {
		this.costGuildMineral = costGuildMineral;
	}

	public Integer getCostGuildMoney() {
		return this.costGuildMoney;
	}

	public void setCostGuildMoney(Integer costGuildMoney) {
		this.costGuildMoney = costGuildMoney;
	}

}