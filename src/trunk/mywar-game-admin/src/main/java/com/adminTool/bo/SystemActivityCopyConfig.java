package com.adminTool.bo;

/**
 * 活动副本条件配置表
 * @author Administrator
 *
 */
public class SystemActivityCopyConfig {

	private Integer activityId;
	private Integer forceId;
	private Integer limitTimes;
	private String enterNeedTools;
	private Integer countType;
	private Integer maxRound;
	private Integer imgId;
	private Integer monsterMultiple;
	
	// 添加字段
	private String vipBuyTimes;
	private String vipBuyTimesUsedDiamond;
	
	public String getVipBuyTimes() {
		return vipBuyTimes;
	}

	public void setVipBuyTimes(String vipBuyTimes) {
		this.vipBuyTimes = vipBuyTimes;
	}

	public String getVipBuyTimesUsedDiamond() {
		return vipBuyTimesUsedDiamond;
	}

	public void setVipBuyTimesUsedDiamond(String vipBuyTimesUsedDiamond) {
		this.vipBuyTimesUsedDiamond = vipBuyTimesUsedDiamond;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getForceId() {
		return forceId;
	}

	public void setForceId(Integer forceId) {
		this.forceId = forceId;
	}

	public Integer getLimitTimes() {
		return limitTimes;
	}

	public void setLimitTimes(Integer limitTimes) {
		this.limitTimes = limitTimes;
	}

	public String getEnterNeedTools() {
		return enterNeedTools;
	}

	public void setEnterNeedTools(String enterNeedTools) {
		this.enterNeedTools = enterNeedTools;
	}

	public Integer getCountType() {
		return countType;
	}

	public void setCountType(Integer countType) {
		this.countType = countType;
	}

	public Integer getMaxRound() {
		return maxRound;
	}

	public void setMaxRound(Integer maxRound) {
		this.maxRound = maxRound;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getMonsterMultiple() {
		return monsterMultiple;
	}

	public void setMonsterMultiple(Integer monsterMultiple) {
		this.monsterMultiple = monsterMultiple;
	}

	public SystemActivityCopyConfig() {}
	
}
