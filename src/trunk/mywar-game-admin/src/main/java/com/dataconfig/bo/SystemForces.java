package com.dataconfig.bo;

/**
 * SystemForces entity. @author MyEclipse Persistence Tools
 */

public class SystemForces implements java.io.Serializable {

	// Fields

	private Integer forcesId;
	private Integer sceneId;
	private Integer preForcesId;
	private Integer preForcesIdb;
	private String forcesName;
	private Short forcesLevel;
	private Short forcesType;
	private Short needPower;
	private Short timesLimit;
	private Integer imgId;

	// Constructors

	/** default constructor */
	public SystemForces() {
	}

	/** minimal constructor */
	public SystemForces(Integer sceneId, Integer preForcesId,
			String forcesName, Short forcesLevel, Integer imgId) {
		this.sceneId = sceneId;
		this.preForcesId = preForcesId;
		this.forcesName = forcesName;
		this.forcesLevel = forcesLevel;
		this.imgId = imgId;
	}

	/** full constructor */
	public SystemForces(Integer sceneId, Integer preForcesId,
			Integer preForcesIdb, String forcesName, Short forcesLevel,
			Short forcesType, Short needPower, Short timesLimit, Integer imgId) {
		this.sceneId = sceneId;
		this.preForcesId = preForcesId;
		this.preForcesIdb = preForcesIdb;
		this.forcesName = forcesName;
		this.forcesLevel = forcesLevel;
		this.forcesType = forcesType;
		this.needPower = needPower;
		this.timesLimit = timesLimit;
		this.imgId = imgId;
	}

	// Property accessors

	public Integer getForcesId() {
		return this.forcesId;
	}

	public void setForcesId(Integer forcesId) {
		this.forcesId = forcesId;
	}

	public Integer getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public Integer getPreForcesId() {
		return this.preForcesId;
	}

	public void setPreForcesId(Integer preForcesId) {
		this.preForcesId = preForcesId;
	}

	public Integer getPreForcesIdb() {
		return this.preForcesIdb;
	}

	public void setPreForcesIdb(Integer preForcesIdb) {
		this.preForcesIdb = preForcesIdb;
	}

	public String getForcesName() {
		return this.forcesName;
	}

	public void setForcesName(String forcesName) {
		this.forcesName = forcesName;
	}

	public Short getForcesLevel() {
		return this.forcesLevel;
	}

	public void setForcesLevel(Short forcesLevel) {
		this.forcesLevel = forcesLevel;
	}

	public Short getForcesType() {
		return this.forcesType;
	}

	public void setForcesType(Short forcesType) {
		this.forcesType = forcesType;
	}

	public Short getNeedPower() {
		return this.needPower;
	}

	public void setNeedPower(Short needPower) {
		this.needPower = needPower;
	}

	public Short getTimesLimit() {
		return this.timesLimit;
	}

	public void setTimesLimit(Short timesLimit) {
		this.timesLimit = timesLimit;
	}

	public Integer getImgId() {
		return this.imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

}