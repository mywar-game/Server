package com.dataconfig.bo;

/**
 * FHelpConstant entity. @author MyEclipse Persistence Tools
 */

public class FHelpConstant implements java.io.Serializable {

	// Fields

	private Integer helpConstantId;
	private Integer level;
	private Integer buildingId;
	private String rewardInfo;

	// Constructors

	/** default constructor */
	public FHelpConstant() {
	}

	/** full constructor */
	public FHelpConstant(Integer level, Integer buildingId, String rewardInfo) {
		this.level = level;
		this.buildingId = buildingId;
		this.rewardInfo = rewardInfo;
	}

	// Property accessors

	public Integer getHelpConstantId() {
		return this.helpConstantId;
	}

	public void setHelpConstantId(Integer helpConstantId) {
		this.helpConstantId = helpConstantId;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getRewardInfo() {
		return this.rewardInfo;
	}

	public void setRewardInfo(String rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

}