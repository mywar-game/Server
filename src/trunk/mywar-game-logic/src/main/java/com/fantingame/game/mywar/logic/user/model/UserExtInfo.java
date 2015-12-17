package com.fantingame.game.mywar.logic.user.model;

import java.util.Date;

public class UserExtInfo {
	private String userId;
	// 总充值的现实货币数量
	private double allChargeMoney;
	// 在线时长
	private int allOnlineTime;
	// 已扩展背包次数
	private int packExtendTimes;
	// 仓库扩展次数
	private int storehouseExtendTimes;
	// 战斗力
	private int effective;
	// 上次所处场景位置
	private String prePosition = "";
	// 目前引导的步奏
	private int guideStep;
	// 记录已引导的步奏
	private String recordGuideStep;
	// 创建时间
	private Date createdTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getAllChargeMoney() {
		return allChargeMoney;
	}

	public void setAllChargeMoney(double allChargeMoney) {
		this.allChargeMoney = allChargeMoney;
	}

	public int getAllOnlineTime() {
		return allOnlineTime;
	}

	public void setAllOnlineTime(int allOnlineTime) {
		this.allOnlineTime = allOnlineTime;
	}

	public int getPackExtendTimes() {
		return packExtendTimes;
	}

	public void setPackExtendTimes(int packExtendTimes) {
		this.packExtendTimes = packExtendTimes;
	}

	public int getStorehouseExtendTimes() {
		return storehouseExtendTimes;
	}

	public void setStorehouseExtendTimes(int storehouseExtendTimes) {
		this.storehouseExtendTimes = storehouseExtendTimes;
	}

	public int getEffective() {
		return effective;
	}

	public void setEffective(int effective) {
		this.effective = effective;
	}

	public int getGuideStep() {
		return guideStep;
	}

	public void setGuideStep(int guideStep) {
		this.guideStep = guideStep;
	}

	public String getRecordGuideStep() {
		return recordGuideStep;
	}

	public void setRecordGuideStep(String recordGuideStep) {
		this.recordGuideStep = recordGuideStep;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getPrePosition() {
		return prePosition;
	}

	public void setPrePosition(String prePosition) {
		this.prePosition = prePosition;
	}
}
