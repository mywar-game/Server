package com.adminTool.bo;

// 刮刮乐
public class SystemActivityScratchReward {

	private Integer id;
	private Integer activityId;
	private String rewards;
	private Integer lowerNum;
	private Integer upperNum;
	private String rewardsDesc;
	private Integer isAmend;
	
	public Integer getIsAmend() {
		return isAmend;
	}

	public void setIsAmend(Integer isAmend) {
		this.isAmend = isAmend;
	}

	public String getRewardsDesc() {
		return rewardsDesc;
	}

	public void setRewardsDesc(String rewardsDesc) {
		this.rewardsDesc = rewardsDesc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public Integer getLowerNum() {
		return lowerNum;
	}

	public void setLowerNum(Integer lowerNum) {
		this.lowerNum = lowerNum;
	}

	public Integer getUpperNum() {
		return upperNum;
	}

	public void setUpperNum(Integer upperNum) {
		this.upperNum = upperNum;
	}

	public SystemActivityScratchReward() {}
	
}
