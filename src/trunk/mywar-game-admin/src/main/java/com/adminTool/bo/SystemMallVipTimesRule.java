package com.adminTool.bo;

public class SystemMallVipTimesRule {

	public SystemMallVipTimesRule() {}
	
	private int id;
	private int vipLevel;
	private int mallId;
	private int dailyMaxTimes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}
	public int getMallId() {
		return mallId;
	}
	public void setMallId(int mallId) {
		this.mallId = mallId;
	}
	public int getDailyMaxTimes() {
		return dailyMaxTimes;
	}
	public void setDailyMaxTimes(int dailyMaxTimes) {
		this.dailyMaxTimes = dailyMaxTimes;
	}
}
