package com.dataconfig.bo;

/**
 * GGuildMall entity. @author MyEclipse Persistence Tools
 */

public class GuildMall implements java.io.Serializable {

	// Fields

	private Integer guildMallId;
	private Integer targetId;
	private Integer category;
	private Integer type;
	private Integer costContribute;
	private Integer todayBuyMax;
	private Integer userBuyLevel;
	private Integer guildBuyLevel;
	private Integer quality;
	private Integer state;

	// Constructors

	/** default constructor */
	public GuildMall() {
	}

	/** full constructor */
	public GuildMall(Integer targetId, Integer category, Integer type,
			Integer costContribute, Integer todayBuyMax, Integer userBuyLevel,
			Integer guildBuyLevel, Integer quality, Integer state) {
		this.targetId = targetId;
		this.category = category;
		this.type = type;
		this.costContribute = costContribute;
		this.todayBuyMax = todayBuyMax;
		this.userBuyLevel = userBuyLevel;
		this.guildBuyLevel = guildBuyLevel;
		this.quality = quality;
		this.state = state;
	}

	// Property accessors

	public Integer getGuildMallId() {
		return this.guildMallId;
	}

	public void setGuildMallId(Integer guildMallId) {
		this.guildMallId = guildMallId;
	}

	public Integer getTargetId() {
		return this.targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCostContribute() {
		return this.costContribute;
	}

	public void setCostContribute(Integer costContribute) {
		this.costContribute = costContribute;
	}

	public Integer getTodayBuyMax() {
		return this.todayBuyMax;
	}

	public void setTodayBuyMax(Integer todayBuyMax) {
		this.todayBuyMax = todayBuyMax;
	}

	public Integer getUserBuyLevel() {
		return this.userBuyLevel;
	}

	public void setUserBuyLevel(Integer userBuyLevel) {
		this.userBuyLevel = userBuyLevel;
	}

	public Integer getGuildBuyLevel() {
		return this.guildBuyLevel;
	}

	public void setGuildBuyLevel(Integer guildBuyLevel) {
		this.guildBuyLevel = guildBuyLevel;
	}

	public Integer getQuality() {
		return this.quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}