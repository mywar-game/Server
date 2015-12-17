package com.dataconfig.bo;

/**
 * MMissionConstant entity. @author MyEclipse Persistence Tools
 */

public class MMissionConstant implements java.io.Serializable {

	// Fields

	private Integer missionId;
	private String missionName;
	private String missionDesc;
	private String missionCondition;
	private String missionPrize;
	private Integer type;
	private String nextMissionIds;
	private String isTheFirst;
	private Integer missionAreaLimit;
	private String needTaskTreasure;
	private Integer npcId;
	private Integer viewType;
	private String pveIds;
	private String openFunctionId;
	private Integer rate;
	private Integer quality;

	// Constructors

	/** default constructor */
	public MMissionConstant() {
	}

	/** minimal constructor */
	public MMissionConstant(String missionName, String missionDesc,
			Integer type, String nextMissionIds, String isTheFirst,
			Integer missionAreaLimit, Integer npcId, Integer viewType,
			String openFunctionId, Integer rate, Integer quality) {
		this.missionName = missionName;
		this.missionDesc = missionDesc;
		this.type = type;
		this.nextMissionIds = nextMissionIds;
		this.isTheFirst = isTheFirst;
		this.missionAreaLimit = missionAreaLimit;
		this.npcId = npcId;
		this.viewType = viewType;
		this.openFunctionId = openFunctionId;
		this.rate = rate;
		this.quality = quality;
	}

	/** full constructor */
	public MMissionConstant(String missionName, String missionDesc,
			String missionCondition, String missionPrize, Integer type,
			String nextMissionIds, String isTheFirst, Integer missionAreaLimit,
			String needTaskTreasure, Integer npcId, Integer viewType,
			String pveIds, String openFunctionId, Integer rate, Integer quality) {
		this.missionName = missionName;
		this.missionDesc = missionDesc;
		this.missionCondition = missionCondition;
		this.missionPrize = missionPrize;
		this.type = type;
		this.nextMissionIds = nextMissionIds;
		this.isTheFirst = isTheFirst;
		this.missionAreaLimit = missionAreaLimit;
		this.needTaskTreasure = needTaskTreasure;
		this.npcId = npcId;
		this.viewType = viewType;
		this.pveIds = pveIds;
		this.openFunctionId = openFunctionId;
		this.rate = rate;
		this.quality = quality;
	}

	// Property accessors

	public Integer getMissionId() {
		return this.missionId;
	}

	public void setMissionId(Integer missionId) {
		this.missionId = missionId;
	}

	public String getMissionName() {
		return this.missionName;
	}

	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}

	public String getMissionDesc() {
		return this.missionDesc;
	}

	public void setMissionDesc(String missionDesc) {
		this.missionDesc = missionDesc;
	}

	public String getMissionCondition() {
		return this.missionCondition;
	}

	public void setMissionCondition(String missionCondition) {
		this.missionCondition = missionCondition;
	}

	public String getMissionPrize() {
		return this.missionPrize;
	}

	public void setMissionPrize(String missionPrize) {
		this.missionPrize = missionPrize;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNextMissionIds() {
		return this.nextMissionIds;
	}

	public void setNextMissionIds(String nextMissionIds) {
		this.nextMissionIds = nextMissionIds;
	}

	public String getIsTheFirst() {
		return this.isTheFirst;
	}

	public void setIsTheFirst(String isTheFirst) {
		this.isTheFirst = isTheFirst;
	}

	public Integer getMissionAreaLimit() {
		return this.missionAreaLimit;
	}

	public void setMissionAreaLimit(Integer missionAreaLimit) {
		this.missionAreaLimit = missionAreaLimit;
	}

	public String getNeedTaskTreasure() {
		return this.needTaskTreasure;
	}

	public void setNeedTaskTreasure(String needTaskTreasure) {
		this.needTaskTreasure = needTaskTreasure;
	}

	public Integer getNpcId() {
		return this.npcId;
	}

	public void setNpcId(Integer npcId) {
		this.npcId = npcId;
	}

	public Integer getViewType() {
		return this.viewType;
	}

	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}

	public String getPveIds() {
		return this.pveIds;
	}

	public void setPveIds(String pveIds) {
		this.pveIds = pveIds;
	}

	public String getOpenFunctionId() {
		return this.openFunctionId;
	}

	public void setOpenFunctionId(String openFunctionId) {
		this.openFunctionId = openFunctionId;
	}

	public Integer getRate() {
		return this.rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getQuality() {
		return this.quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

}