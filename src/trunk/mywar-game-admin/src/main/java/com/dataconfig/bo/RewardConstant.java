package com.dataconfig.bo;

/**
 * RRewardConstant entity. @author MyEclipse Persistence Tools
 */

public class RewardConstant implements java.io.Serializable {

	// Fields

	private Integer areaId;
	private Integer sceneId;
	private String monsters;
	private String normalDrop;
	private String highDrop;
	private String drops;

	// Constructors

	/** default constructor */
	public RewardConstant() {
	}

	/** full constructor */
	public RewardConstant(Integer sceneId, String monsters, String normalDrop,
			String highDrop, String drops) {
		this.sceneId = sceneId;
		this.monsters = monsters;
		this.normalDrop = normalDrop;
		this.highDrop = highDrop;
		this.drops = drops;
	}

	// Property accessors

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String getMonsters() {
		return this.monsters;
	}

	public void setMonsters(String monsters) {
		this.monsters = monsters;
	}

	public String getNormalDrop() {
		return this.normalDrop;
	}

	public void setNormalDrop(String normalDrop) {
		this.normalDrop = normalDrop;
	}

	public String getHighDrop() {
		return this.highDrop;
	}

	public void setHighDrop(String highDrop) {
		this.highDrop = highDrop;
	}

	public String getDrops() {
		return this.drops;
	}

	public void setDrops(String drops) {
		this.drops = drops;
	}

}