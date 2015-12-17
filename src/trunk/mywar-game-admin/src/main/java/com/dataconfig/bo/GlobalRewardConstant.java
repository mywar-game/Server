package com.dataconfig.bo;

/**
 * GlobalRewardConstant entity. @author MyEclipse Persistence Tools
 */

public class GlobalRewardConstant implements java.io.Serializable {

	// Fields

	private Integer globalRewardId;
	private Integer targetId;
	private Integer minLevel;
	private Integer maxLevel;
	private String reward;
	private String extraReward;
	private Integer percent;
	private Integer type;

	// Constructors

	/** default constructor */
	public GlobalRewardConstant() {
	}

	/** minimal constructor */
	public GlobalRewardConstant(Integer targetId, Integer minLevel,
			Integer maxLevel, Integer percent, Integer type) {
		this.targetId = targetId;
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		this.percent = percent;
		this.type = type;
	}

	/** full constructor */
	public GlobalRewardConstant(Integer targetId, Integer minLevel,
			Integer maxLevel, String reward, String extraReward,
			Integer percent, Integer type) {
		this.targetId = targetId;
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		this.reward = reward;
		this.extraReward = extraReward;
		this.percent = percent;
		this.type = type;
	}

	// Property accessors

	public Integer getGlobalRewardId() {
		return this.globalRewardId;
	}

	public void setGlobalRewardId(Integer globalRewardId) {
		this.globalRewardId = globalRewardId;
	}

	public Integer getTargetId() {
		return this.targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getMinLevel() {
		return this.minLevel;
	}

	public void setMinLevel(Integer minLevel) {
		this.minLevel = minLevel;
	}

	public Integer getMaxLevel() {
		return this.maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}

	public String getReward() {
		return this.reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public String getExtraReward() {
		return this.extraReward;
	}

	public void setExtraReward(String extraReward) {
		this.extraReward = extraReward;
	}

	public Integer getPercent() {
		return this.percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}