package com.fantingame.game.mywar.logic.user.model;

import java.util.Date;

public class User {
	private String userId;
	private long ftId;
	private String name;
	private int level;
	private int exp;
	private int money;
	private int gold;
	private int camp;
	private int power;
	private Date powerResumTime;
	private int activity;
	private Date activityResumTime;
	private int prestige;
	private int prestigeLevel;
	private int honour;
	private int vipLevel;
	private int vipExp;
	private int jobExp;
	private Date regTime;
	/**
	 * 封号截止日期，过了这个日期以后，自动解除封号 为空 则没有被封号
	 * 
	 * @return
	 */
	private Date dueTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public long getFtId() {
		return ftId;
	}

	public void setFtId(long ftId) {
		this.ftId = ftId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}

	public Date getPowerResumTime() {
		return powerResumTime;
	}

	public void setPowerResumTime(Date powerResumTime) {
		this.powerResumTime = powerResumTime;
	}

	public Date getActivityResumTime() {
		return activityResumTime;
	}

	public void setActivityResumTime(Date activityResumTime) {
		this.activityResumTime = activityResumTime;
	}

	public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public int getPrestige() {
		return prestige;
	}

	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}

	public int getHonour() {
		return honour;
	}

	public void setHonour(int honour) {
		this.honour = honour;
	}

	public int getVipExp() {
		return vipExp;
	}

	public void setVipExp(int vipExp) {
		this.vipExp = vipExp;
	}

	public int getPrestigeLevel() {
		return prestigeLevel;
	}

	public void setPrestigeLevel(int prestigeLevel) {
		this.prestigeLevel = prestigeLevel;
	}

	public int getCamp() {
		return camp;
	}

	public void setCamp(int camp) {
		this.camp = camp;
	}

	public int getJobExp() {
		return jobExp;
	}

	public void setJobExp(int jobExp) {
		this.jobExp = jobExp;
	}
}
