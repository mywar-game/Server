package com.dataconfig.bo;

/**
 * LActiveEvent entity. @author MyEclipse Persistence Tools
 */

public class ActiveEvent implements java.io.Serializable {

	// Fields

	private Integer activeEventId;
	private String activeEventName;
	private Integer finishNeedTimes;
	private Integer rewardType;
	private Integer rewardNumPerTime;
	private Integer rewardTotalNum;
	private Integer targetType;
	private Integer targetNum;
	private Integer canGoto;

	// Constructors

	/** default constructor */
	public ActiveEvent() {
	}

	/** full constructor */
	public ActiveEvent(String activeEventName, Integer finishNeedTimes,
			Integer rewardType, Integer rewardNumPerTime,
			Integer rewardTotalNum, Integer targetType, Integer targetNum,
			Integer canGoto) {
		this.activeEventName = activeEventName;
		this.finishNeedTimes = finishNeedTimes;
		this.rewardType = rewardType;
		this.rewardNumPerTime = rewardNumPerTime;
		this.rewardTotalNum = rewardTotalNum;
		this.targetType = targetType;
		this.targetNum = targetNum;
		this.canGoto = canGoto;
	}

	// Property accessors

	public Integer getActiveEventId() {
		return this.activeEventId;
	}

	public void setActiveEventId(Integer activeEventId) {
		this.activeEventId = activeEventId;
	}

	public String getActiveEventName() {
		return this.activeEventName;
	}

	public void setActiveEventName(String activeEventName) {
		this.activeEventName = activeEventName;
	}

	public Integer getFinishNeedTimes() {
		return this.finishNeedTimes;
	}

	public void setFinishNeedTimes(Integer finishNeedTimes) {
		this.finishNeedTimes = finishNeedTimes;
	}

	public Integer getRewardType() {
		return this.rewardType;
	}

	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}

	public Integer getRewardNumPerTime() {
		return this.rewardNumPerTime;
	}

	public void setRewardNumPerTime(Integer rewardNumPerTime) {
		this.rewardNumPerTime = rewardNumPerTime;
	}

	public Integer getRewardTotalNum() {
		return this.rewardTotalNum;
	}

	public void setRewardTotalNum(Integer rewardTotalNum) {
		this.rewardTotalNum = rewardTotalNum;
	}

	public Integer getTargetType() {
		return this.targetType;
	}

	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}

	public Integer getTargetNum() {
		return this.targetNum;
	}

	public void setTargetNum(Integer targetNum) {
		this.targetNum = targetNum;
	}

	public Integer getCanGoto() {
		return this.canGoto;
	}

	public void setCanGoto(Integer canGoto) {
		this.canGoto = canGoto;
	}

}