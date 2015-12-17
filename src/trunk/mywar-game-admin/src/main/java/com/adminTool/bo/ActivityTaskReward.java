package com.adminTool.bo;

/**
 * 活跃度奖励
 * @author Administrator
 *
 */
public class ActivityTaskReward implements java.io.Serializable {

	private Integer activityTaskRewardId;
	private Integer point;
	private String rewards;
	private String desc;
	
	public ActivityTaskReward(Integer activityTaskRewardId, Integer point,
			String rewards) {
		super();
		this.activityTaskRewardId = activityTaskRewardId;
		this.point = point;
		this.rewards = rewards;
	}
	
	
	// 注意这种情况下 我们需要重写equals和hashCode

    public boolean equals(Object object) {
    	return true;
    }

    public int hashCode() {
    	return 1;
    }
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public ActivityTaskReward() {
		super();
	}
	
	public Integer getActivityTaskRewardId() {
		return activityTaskRewardId;
	}
	public void setActivityTaskRewardId(Integer activityTaskRewardId) {
		this.activityTaskRewardId = activityTaskRewardId;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
}
