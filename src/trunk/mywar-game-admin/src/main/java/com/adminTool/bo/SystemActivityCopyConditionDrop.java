package com.adminTool.bo;

/**
 * 活动副本条件结算方式掉落
 * @author Administrator
 *
 */
public class SystemActivityCopyConditionDrop {

	private Integer id;
	private Integer activityId;
	private Integer conditions;
	private String rewards;
	
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

	public Integer getConditions() {
		return conditions;
	}

	public void setConditions(Integer conditions) {
		this.conditions = conditions;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public SystemActivityCopyConditionDrop() {}
	
}
