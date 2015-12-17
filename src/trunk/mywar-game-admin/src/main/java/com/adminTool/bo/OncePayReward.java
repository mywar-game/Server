package com.adminTool.bo;

/**
 * 单笔充值奖励
 * 
 * @author yezp
 */
public class OncePayReward implements java.io.Serializable {

	private int id;
	private int activityId;
	private int payMoney;
	private int nextPayMoney;
	private String rewards;
	private int timesLimit;
	private String description;
	private String showRewards;

	public OncePayReward() {
	}

	public OncePayReward(int id, int activityId, int payMoney,
			int nextPayMoney, String rewards, int timesLimit, String description) {
		this.id = id;
		this.activityId = activityId;
		this.payMoney = payMoney;
		this.nextPayMoney = nextPayMoney;
		this.rewards = rewards;
		this.timesLimit = timesLimit;
		this.description = description;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public int getNextPayMoney() {
		return this.nextPayMoney;
	}

	public void setNextPayMoney(int nextPayMoney) {
		this.nextPayMoney = nextPayMoney;
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
