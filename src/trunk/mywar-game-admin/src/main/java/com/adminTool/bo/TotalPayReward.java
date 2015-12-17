package com.adminTool.bo;

/**
 * 累积充值
 * 
 * @author yezp
 */
public class TotalPayReward implements java.io.Serializable {

	private Integer id;
	private int activityId;
	private int payMoney;
	private String rewards;
	private int timesLimit;
	private String description;
	private String showRewards;

	public TotalPayReward() {
	}

	public TotalPayReward(int activityId, int payMoney, String rewards,
			int timesLimit, String description) {
		this.activityId = activityId;
		this.payMoney = payMoney;
		this.rewards = rewards;
		this.timesLimit = timesLimit;
		this.description = description;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getActivityId() {
		return this.activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getPayMoney() {
		return this.payMoney;
	}

	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}

	public String getRewards() {
		return this.rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public int getTimesLimit() {
		return this.timesLimit;
	}

	public void setTimesLimit(int timesLimit) {
		this.timesLimit = timesLimit;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShowRewards() {
		return showRewards;
	}

	public void setShowRewards(String showRewards) {
		this.showRewards = showRewards;
	}

}
