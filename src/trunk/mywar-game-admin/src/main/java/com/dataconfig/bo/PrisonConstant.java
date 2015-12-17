package com.dataconfig.bo;

/**
 * PPrisonConstant entity. @author MyEclipse Persistence Tools
 */

public class PrisonConstant implements java.io.Serializable {

	// Fields

	private Integer prisonConstantId;
	private Integer areaId;
	private Integer prisonId;
	private String prisonName;
	private String prisonDesc;
	private Integer waveNum;
	private String monsterInfo;
	private String monsterAppearType;
	private Integer bossType;
	private Integer checkPointType;
	private Integer failNum;
	private String firstReward;
	private String normalReward;
	private String watchReward;
	private Double petLoseSpeed;
	private Integer startMaxTime;
	private String modelId;
	private Integer canWatch;
	private Integer maxRewardNum;
	private Integer minRewardNum;
	private Integer vipRewardAdd;

	// Constructors

	/** default constructor */
	public PrisonConstant() {
	}

	/** minimal constructor */
	public PrisonConstant(Integer areaId, Integer prisonId, String prisonName,
			String prisonDesc, Integer waveNum, String monsterInfo,
			String monsterAppearType, Integer bossType, Integer checkPointType,
			Integer failNum, String firstReward, String normalReward,
			String watchReward, Integer startMaxTime, String modelId,
			Integer canWatch, Integer maxRewardNum, Integer minRewardNum,
			Integer vipRewardAdd) {
		this.areaId = areaId;
		this.prisonId = prisonId;
		this.prisonName = prisonName;
		this.prisonDesc = prisonDesc;
		this.waveNum = waveNum;
		this.monsterInfo = monsterInfo;
		this.monsterAppearType = monsterAppearType;
		this.bossType = bossType;
		this.checkPointType = checkPointType;
		this.failNum = failNum;
		this.firstReward = firstReward;
		this.normalReward = normalReward;
		this.watchReward = watchReward;
		this.startMaxTime = startMaxTime;
		this.modelId = modelId;
		this.canWatch = canWatch;
		this.maxRewardNum = maxRewardNum;
		this.minRewardNum = minRewardNum;
		this.vipRewardAdd = vipRewardAdd;
	}

	/** full constructor */
	public PrisonConstant(Integer areaId, Integer prisonId, String prisonName,
			String prisonDesc, Integer waveNum, String monsterInfo,
			String monsterAppearType, Integer bossType, Integer checkPointType,
			Integer failNum, String firstReward, String normalReward,
			String watchReward, Double petLoseSpeed, Integer startMaxTime,
			String modelId, Integer canWatch, Integer maxRewardNum,
			Integer minRewardNum, Integer vipRewardAdd) {
		this.areaId = areaId;
		this.prisonId = prisonId;
		this.prisonName = prisonName;
		this.prisonDesc = prisonDesc;
		this.waveNum = waveNum;
		this.monsterInfo = monsterInfo;
		this.monsterAppearType = monsterAppearType;
		this.bossType = bossType;
		this.checkPointType = checkPointType;
		this.failNum = failNum;
		this.firstReward = firstReward;
		this.normalReward = normalReward;
		this.watchReward = watchReward;
		this.petLoseSpeed = petLoseSpeed;
		this.startMaxTime = startMaxTime;
		this.modelId = modelId;
		this.canWatch = canWatch;
		this.maxRewardNum = maxRewardNum;
		this.minRewardNum = minRewardNum;
		this.vipRewardAdd = vipRewardAdd;
	}

	// Property accessors

	public Integer getPrisonConstantId() {
		return this.prisonConstantId;
	}

	public void setPrisonConstantId(Integer prisonConstantId) {
		this.prisonConstantId = prisonConstantId;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getPrisonId() {
		return this.prisonId;
	}

	public void setPrisonId(Integer prisonId) {
		this.prisonId = prisonId;
	}

	public String getPrisonName() {
		return this.prisonName;
	}

	public void setPrisonName(String prisonName) {
		this.prisonName = prisonName;
	}

	public String getPrisonDesc() {
		return this.prisonDesc;
	}

	public void setPrisonDesc(String prisonDesc) {
		this.prisonDesc = prisonDesc;
	}

	public Integer getWaveNum() {
		return this.waveNum;
	}

	public void setWaveNum(Integer waveNum) {
		this.waveNum = waveNum;
	}

	public String getMonsterInfo() {
		return this.monsterInfo;
	}

	public void setMonsterInfo(String monsterInfo) {
		this.monsterInfo = monsterInfo;
	}

	public String getMonsterAppearType() {
		return this.monsterAppearType;
	}

	public void setMonsterAppearType(String monsterAppearType) {
		this.monsterAppearType = monsterAppearType;
	}

	public Integer getBossType() {
		return this.bossType;
	}

	public void setBossType(Integer bossType) {
		this.bossType = bossType;
	}

	public Integer getCheckPointType() {
		return this.checkPointType;
	}

	public void setCheckPointType(Integer checkPointType) {
		this.checkPointType = checkPointType;
	}

	public Integer getFailNum() {
		return this.failNum;
	}

	public void setFailNum(Integer failNum) {
		this.failNum = failNum;
	}

	public String getFirstReward() {
		return this.firstReward;
	}

	public void setFirstReward(String firstReward) {
		this.firstReward = firstReward;
	}

	public String getNormalReward() {
		return this.normalReward;
	}

	public void setNormalReward(String normalReward) {
		this.normalReward = normalReward;
	}

	public String getWatchReward() {
		return this.watchReward;
	}

	public void setWatchReward(String watchReward) {
		this.watchReward = watchReward;
	}

	public Double getPetLoseSpeed() {
		return this.petLoseSpeed;
	}

	public void setPetLoseSpeed(Double petLoseSpeed) {
		this.petLoseSpeed = petLoseSpeed;
	}

	public Integer getStartMaxTime() {
		return this.startMaxTime;
	}

	public void setStartMaxTime(Integer startMaxTime) {
		this.startMaxTime = startMaxTime;
	}

	public String getModelId() {
		return this.modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public Integer getCanWatch() {
		return this.canWatch;
	}

	public void setCanWatch(Integer canWatch) {
		this.canWatch = canWatch;
	}

	public Integer getMaxRewardNum() {
		return this.maxRewardNum;
	}

	public void setMaxRewardNum(Integer maxRewardNum) {
		this.maxRewardNum = maxRewardNum;
	}

	public Integer getMinRewardNum() {
		return this.minRewardNum;
	}

	public void setMinRewardNum(Integer minRewardNum) {
		this.minRewardNum = minRewardNum;
	}

	public Integer getVipRewardAdd() {
		return this.vipRewardAdd;
	}

	public void setVipRewardAdd(Integer vipRewardAdd) {
		this.vipRewardAdd = vipRewardAdd;
	}

}