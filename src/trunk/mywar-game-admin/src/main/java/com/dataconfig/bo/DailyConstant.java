package com.dataconfig.bo;

/**
 * MDailyConstant entity. @author MyEclipse Persistence Tools
 */

public class DailyConstant implements java.io.Serializable {

	// Fields

	private Integer dailyConstantId;
	private Integer missionId;
	private Integer level;
	private String missionPrize;

	// Constructors

	/** default constructor */
	public DailyConstant() {
	}

	/** full constructor */
	public DailyConstant(Integer missionId, Integer level, String missionPrize) {
		this.missionId = missionId;
		this.level = level;
		this.missionPrize = missionPrize;
	}

	// Property accessors

	public Integer getDailyConstantId() {
		return this.dailyConstantId;
	}

	public void setDailyConstantId(Integer dailyConstantId) {
		this.dailyConstantId = dailyConstantId;
	}

	public Integer getMissionId() {
		return this.missionId;
	}

	public void setMissionId(Integer missionId) {
		this.missionId = missionId;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getMissionPrize() {
		return this.missionPrize;
	}

	public void setMissionPrize(String missionPrize) {
		this.missionPrize = missionPrize;
	}

}