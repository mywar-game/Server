package com.stats.bean;

public class PayRank {
	/**排名**/
	private int rank;
	/**玩家编号**/
	private String userId;
	/**玩家名**/
	private String userName;
	/**服务器编号**/
	private int sysNum;
	/**玩家等级**/
	private int userLevel;
	/**充值总额**/
	private double amount;
	/**充值次数**/
	private int payTimes;
	/**最后登录时间**/
	private String lastLoginTime;
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getSysNum() {
		return sysNum;
	}
	public void setSysNum(int sysNum) {
		this.sysNum = sysNum;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getPayTimes() {
		return payTimes;
	}
	public void setPayTimes(int payTimes) {
		this.payTimes = payTimes;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
}
