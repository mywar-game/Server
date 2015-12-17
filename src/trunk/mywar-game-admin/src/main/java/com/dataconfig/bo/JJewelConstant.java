package com.dataconfig.bo;

/**
 * JJewjelConstant entity. @author MyEclipse Persistence Tools
 */

public class JJewelConstant implements java.io.Serializable {

	// Fields

	private Integer jewjelId;
	private String name;
	private String description;
	private Integer level;
	private Integer quality;
	private Integer type;
	private Integer value;
	private Integer targetId;
	private Integer composeValue;
	private Integer composeCostMoney;

	// Constructors

	/** default constructor */
	public JJewelConstant() {
	}

	/** full constructor */
	public JJewelConstant(String name, String description, Integer level,
			Integer quality, Integer type, Integer value, Integer targetId,
			Integer composeValue, Integer composeCostMoney) {
		this.name = name;
		this.description = description;
		this.level = level;
		this.quality = quality;
		this.type = type;
		this.value = value;
		this.targetId = targetId;
		this.composeValue = composeValue;
		this.composeCostMoney = composeCostMoney;
	}

	// Property accessors

	public Integer getJewjelId() {
		return this.jewjelId;
	}

	public void setJewjelId(Integer jewjelId) {
		this.jewjelId = jewjelId;
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

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getQuality() {
		return this.quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getTargetId() {
		return this.targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getComposeValue() {
		return this.composeValue;
	}

	public void setComposeValue(Integer composeValue) {
		this.composeValue = composeValue;
	}

	public Integer getComposeCostMoney() {
		return this.composeCostMoney;
	}

	public void setComposeCostMoney(Integer composeCostMoney) {
		this.composeCostMoney = composeCostMoney;
	}

}