package com.dataconfig.bo;

/**
 * BTechnologyOpenConstant entity. @author MyEclipse Persistence Tools
 */

public class BTechnologyOpenConstant implements java.io.Serializable {

	// Fields

	private Integer buildingLevel;
	private String openTechnologyIds;

	// Constructors

	/** default constructor */
	public BTechnologyOpenConstant() {
	}

	/** full constructor */
	public BTechnologyOpenConstant(String openTechnologyIds) {
		this.openTechnologyIds = openTechnologyIds;
	}

	// Property accessors

	public Integer getBuildingLevel() {
		return this.buildingLevel;
	}

	public void setBuildingLevel(Integer buildingLevel) {
		this.buildingLevel = buildingLevel;
	}

	public String getOpenTechnologyIds() {
		return this.openTechnologyIds;
	}

	public void setOpenTechnologyIds(String openTechnologyIds) {
		this.openTechnologyIds = openTechnologyIds;
	}

}