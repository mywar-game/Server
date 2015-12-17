package com.dataconfig.bo;

/**
 * BaPveConstant entity. @author MyEclipse Persistence Tools
 */

public class BaPveConstant implements java.io.Serializable {

	// Fields

	private BaPveConstantId id;
	private String pveBigName;
	private String pveSmallName;
	private String pveDesc;
	private Integer enterLevel;
	private Integer maxRewardNum;
	private Integer minRewardNum;
	private Integer vipRewardAdd;
	private Integer maxNum;
	private Integer minNum;
	private Integer waveNum;
	private String monsterInfo;
	private String monsterAppearType;
	private Integer addExp;
	private Integer addRenow;
	private String reward;
	private String vipReward;
	private Integer bossType;
	private String dropTaskTreasureInfo;
	private String modelId;
	private Integer checkPointType;
	private Integer failNum;
	private Integer arenaId;
	private String firstReward;
	private Integer startMaxTime;
	private String rewardShow;

	// Constructors

	/** default constructor */
	public BaPveConstant() {
	}

	/** minimal constructor */
	public BaPveConstant(BaPveConstantId id, String pveBigName,
			String pveSmallName, String pveDesc, Integer enterLevel,
			Integer maxRewardNum, Integer minRewardNum, Integer vipRewardAdd,
			Integer maxNum, Integer minNum, Integer waveNum,
			String monsterInfo, String monsterAppearType, Integer addExp,
			Integer addRenow, String reward, String vipReward,
			Integer bossType, String rewardShow) {
		this.id = id;
		this.pveBigName = pveBigName;
		this.pveSmallName = pveSmallName;
		this.pveDesc = pveDesc;
		this.enterLevel = enterLevel;
		this.maxRewardNum = maxRewardNum;
		this.minRewardNum = minRewardNum;
		this.vipRewardAdd = vipRewardAdd;
		this.maxNum = maxNum;
		this.minNum = minNum;
		this.waveNum = waveNum;
		this.monsterInfo = monsterInfo;
		this.monsterAppearType = monsterAppearType;
		this.addExp = addExp;
		this.addRenow = addRenow;
		this.reward = reward;
		this.vipReward = vipReward;
		this.bossType = bossType;
		this.rewardShow = rewardShow;
	}

	/** full constructor */
	public BaPveConstant(BaPveConstantId id, String pveBigName,
			String pveSmallName, String pveDesc, Integer enterLevel,
			Integer maxRewardNum, Integer minRewardNum, Integer vipRewardAdd,
			Integer maxNum, Integer minNum, Integer waveNum,
			String monsterInfo, String monsterAppearType, Integer addExp,
			Integer addRenow, String reward, String vipReward,
			Integer bossType, String dropTaskTreasureInfo, String modelId,
			Integer checkPointType, Integer failNum, Integer arenaId,
			String firstReward, Integer startMaxTime, String rewardShow) {
		this.id = id;
		this.pveBigName = pveBigName;
		this.pveSmallName = pveSmallName;
		this.pveDesc = pveDesc;
		this.enterLevel = enterLevel;
		this.maxRewardNum = maxRewardNum;
		this.minRewardNum = minRewardNum;
		this.vipRewardAdd = vipRewardAdd;
		this.maxNum = maxNum;
		this.minNum = minNum;
		this.waveNum = waveNum;
		this.monsterInfo = monsterInfo;
		this.monsterAppearType = monsterAppearType;
		this.addExp = addExp;
		this.addRenow = addRenow;
		this.reward = reward;
		this.vipReward = vipReward;
		this.bossType = bossType;
		this.dropTaskTreasureInfo = dropTaskTreasureInfo;
		this.modelId = modelId;
		this.checkPointType = checkPointType;
		this.failNum = failNum;
		this.arenaId = arenaId;
		this.firstReward = firstReward;
		this.startMaxTime = startMaxTime;
		this.rewardShow = rewardShow;
	}

	// Property accessors

	public BaPveConstantId getId() {
		return this.id;
	}

	public void setId(BaPveConstantId id) {
		this.id = id;
	}

	public String getPveBigName() {
		return this.pveBigName;
	}

	public void setPveBigName(String pveBigName) {
		this.pveBigName = pveBigName;
	}

	public String getPveSmallName() {
		return this.pveSmallName;
	}

	public void setPveSmallName(String pveSmallName) {
		this.pveSmallName = pveSmallName;
	}

	public String getPveDesc() {
		return this.pveDesc;
	}

	public void setPveDesc(String pveDesc) {
		this.pveDesc = pveDesc;
	}

	public Integer getEnterLevel() {
		return this.enterLevel;
	}

	public void setEnterLevel(Integer enterLevel) {
		this.enterLevel = enterLevel;
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

	public Integer getMaxNum() {
		return this.maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public Integer getMinNum() {
		return this.minNum;
	}

	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
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

	public Integer getAddExp() {
		return this.addExp;
	}

	public void setAddExp(Integer addExp) {
		this.addExp = addExp;
	}

	public Integer getAddRenow() {
		return this.addRenow;
	}

	public void setAddRenow(Integer addRenow) {
		this.addRenow = addRenow;
	}

	public String getReward() {
		return this.reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public String getVipReward() {
		return this.vipReward;
	}

	public void setVipReward(String vipReward) {
		this.vipReward = vipReward;
	}

	public Integer getBossType() {
		return this.bossType;
	}

	public void setBossType(Integer bossType) {
		this.bossType = bossType;
	}

	public String getDropTaskTreasureInfo() {
		return this.dropTaskTreasureInfo;
	}

	public void setDropTaskTreasureInfo(String dropTaskTreasureInfo) {
		this.dropTaskTreasureInfo = dropTaskTreasureInfo;
	}

	public String getModelId() {
		return this.modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
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

	public Integer getArenaId() {
		return this.arenaId;
	}

	public void setArenaId(Integer arenaId) {
		this.arenaId = arenaId;
	}

	public String getFirstReward() {
		return this.firstReward;
	}

	public void setFirstReward(String firstReward) {
		this.firstReward = firstReward;
	}

	public Integer getStartMaxTime() {
		return this.startMaxTime;
	}

	public void setStartMaxTime(Integer startMaxTime) {
		this.startMaxTime = startMaxTime;
	}

	public String getRewardShow() {
		return this.rewardShow;
	}

	public void setRewardShow(String rewardShow) {
		this.rewardShow = rewardShow;
	}

}