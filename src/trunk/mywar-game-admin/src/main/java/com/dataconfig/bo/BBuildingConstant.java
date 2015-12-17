package com.dataconfig.bo;

/**
 * BBuildingConstant entity. @author MyEclipse Persistence Tools
 */

public class BBuildingConstant implements java.io.Serializable {

	// Fields

	private Integer buildingId;
	private String buildingName;
	private String buildingDesc;
	private Integer maxLevel;
	private Integer displayLevel;

	// Constructors

	/** default constructor */
	public BBuildingConstant() {
	}

	/** full constructor */
	public BBuildingConstant(String buildingName, String buildingDesc,
			Integer maxLevel, Integer displayLevel) {
		this.buildingName = buildingName;
		this.buildingDesc = buildingDesc;
		this.maxLevel = maxLevel;
		this.displayLevel = displayLevel;
	}

	// Property accessors

	public Integer getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return this.buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingDesc() {
		return this.buildingDesc;
	}

	public void setBuildingDesc(String buildingDesc) {
		this.buildingDesc = buildingDesc;
	}

	public Integer getMaxLevel() {
		return this.maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}

	public Integer getDisplayLevel() {
		return this.displayLevel;
	}

	public void setDisplayLevel(Integer displayLevel) {
		this.displayLevel = displayLevel;
	}

}