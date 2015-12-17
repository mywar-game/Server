package com.adminTool.bo;

/**
 * 累积消费
 * @author Administrator
 *
 */
public class SystemTotalConsumeReward {

	private Integer id;
	private Integer diamond;
	private String rewards;
	private Integer activityId;
	private String rewardsDesc;
	
	public String getRewardsDesc() {
		return rewardsDesc;
	}

	public void setRewardsDesc(String rewardsDesc) {
		this.rewardsDesc = rewardsDesc;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public SystemTotalConsumeReward() {}
	
}
