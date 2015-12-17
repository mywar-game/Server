package com.adminTool.bo;

/**
 * 坐享其成
 * @author Administrator
 *
 */
public class SystemGrowthPlanReward {

	private Integer id;
	private Integer activityId;
	private Integer userLevel;
	private String rewards;
	private String description;
	
	public SystemGrowthPlanReward() {}
	
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
	public Integer getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	public String getRewards() {
		return rewards;
	}
	public void setRewards(String rewards) {
		this.rewards = rewards;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
