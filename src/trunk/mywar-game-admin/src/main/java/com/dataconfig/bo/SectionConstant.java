package com.dataconfig.bo;

/**
 * MSectionConstant entity. @author MyEclipse Persistence Tools
 */

public class SectionConstant implements java.io.Serializable {

	// Fields

	private Integer sectionId;
	private String sectionName;
	private String missions;
	private String reward;

	// Constructors

	/** default constructor */
	public SectionConstant() {
	}

	/** full constructor */
	public SectionConstant(String sectionName, String missions, String reward) {
		this.sectionName = sectionName;
		this.missions = missions;
		this.reward = reward;
	}

	// Property accessors

	public Integer getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getMissions() {
		return this.missions;
	}

	public void setMissions(String missions) {
		this.missions = missions;
	}

	public String getReward() {
		return this.reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

}