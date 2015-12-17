package com.dataconfig.bo;

/**
 * MMapArea entity. @author MyEclipse Persistence Tools
 */

public class MMapArea implements java.io.Serializable {

	// Fields

	private Integer areaId;
	private String areaName;
	private String areaDesc;
	private Integer minLevel;
	private Integer maxLevel;
	private String ppveIds;
	private String bigPveIds;
	private String trimIds;
	private String pveIds;
	private String rewardInfo;

	// Constructors

	/** default constructor */
	public MMapArea() {
	}

	/** full constructor */
	public MMapArea(String areaName, String areaDesc, Integer minLevel,
			Integer maxLevel, String ppveIds, String bigPveIds, String trimIds,
			String pveIds, String rewardInfo) {
		this.areaName = areaName;
		this.areaDesc = areaDesc;
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		this.ppveIds = ppveIds;
		this.bigPveIds = bigPveIds;
		this.trimIds = trimIds;
		this.pveIds = pveIds;
		this.rewardInfo = rewardInfo;
	}

	// Property accessors

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaDesc() {
		return this.areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public Integer getMinLevel() {
		return this.minLevel;
	}

	public void setMinLevel(Integer minLevel) {
		this.minLevel = minLevel;
	}

	public Integer getMaxLevel() {
		return this.maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}

	public String getPpveIds() {
		return this.ppveIds;
	}

	public void setPpveIds(String ppveIds) {
		this.ppveIds = ppveIds;
	}

	public String getBigPveIds() {
		return this.bigPveIds;
	}

	public void setBigPveIds(String bigPveIds) {
		this.bigPveIds = bigPveIds;
	}

	public String getTrimIds() {
		return this.trimIds;
	}

	public void setTrimIds(String trimIds) {
		this.trimIds = trimIds;
	}

	public String getPveIds() {
		return this.pveIds;
	}

	public void setPveIds(String pveIds) {
		this.pveIds = pveIds;
	}

	public String getRewardInfo() {
		return this.rewardInfo;
	}

	public void setRewardInfo(String rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

}