package com.fantingame.game.mywar.logic.task.model;

import java.util.Date;

public class SystemTask {
	private int systemTaskId;
	private String taskName;
	private String taskDesc;
	private int taskType;
	private int premiseTask;
	private int needFinishTimes;
	private int limitMinLevel;
	private int limitMaxLevel;
	private int taskLibrary;
	private String taskPara;
	private int receiveTaskNpc;
	private int handinTaskNpc;
	private String rewards;
	private int imgId;
	private int sort;
	private Date effectBeginTime;
	private Date effectEndTime;
	private int camp;

	public int getSystemTaskId() {
		return systemTaskId;
	}

	public void setSystemTaskId(int systemTaskId) {
		this.systemTaskId = systemTaskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public int getPremiseTask() {
		return premiseTask;
	}

	public void setPremiseTask(int premiseTask) {
		this.premiseTask = premiseTask;
	}

	public int getNeedFinishTimes() {
		return needFinishTimes;
	}

	public void setNeedFinishTimes(int needFinishTimes) {
		this.needFinishTimes = needFinishTimes;
	}

	public int getLimitMinLevel() {
		return limitMinLevel;
	}

	public void setLimitMinLevel(int limitMinLevel) {
		this.limitMinLevel = limitMinLevel;
	}

	public int getLimitMaxLevel() {
		return limitMaxLevel;
	}

	public void setLimitMaxLevel(int limitMaxLevel) {
		this.limitMaxLevel = limitMaxLevel;
	}

	public int getTaskLibrary() {
		return taskLibrary;
	}

	public void setTaskLibrary(int taskLibrary) {
		this.taskLibrary = taskLibrary;
	}

	public String getTaskPara() {
		return taskPara;
	}

	public void setTaskPara(String taskPara) {
		this.taskPara = taskPara;
	}

	public int getReceiveTaskNpc() {
		return receiveTaskNpc;
	}

	public void setReceiveTaskNpc(int receiveTaskNpc) {
		this.receiveTaskNpc = receiveTaskNpc;
	}

	public int getHandinTaskNpc() {
		return handinTaskNpc;
	}

	public void setHandinTaskNpc(int handinTaskNpc) {
		this.handinTaskNpc = handinTaskNpc;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Date getEffectBeginTime() {
		return effectBeginTime;
	}

	public void setEffectBeginTime(Date effectBeginTime) {
		this.effectBeginTime = effectBeginTime;
	}

	public Date getEffectEndTime() {
		return effectEndTime;
	}

	public void setEffectEndTime(Date effectEndTime) {
		this.effectEndTime = effectEndTime;
	}

	public int getCamp() {
		return camp;
	}

	public void setCamp(int camp) {
		this.camp = camp;
	}
}
