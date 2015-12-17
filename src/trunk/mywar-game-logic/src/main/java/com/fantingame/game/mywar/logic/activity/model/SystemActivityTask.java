package com.fantingame.game.mywar.logic.activity.model;

public class SystemActivityTask {

	private int activityTaskId;

	private int targetType;

	private String targetDesc;

	private int needFinishTimes;

	private int point;

	public int getActivityTaskId() {
		return activityTaskId;
	}

	public void setActivityTaskId(int activityTaskId) {
		this.activityTaskId = activityTaskId;
	}

	public int getTargetType() {
		return targetType;
	}

	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}

	public String getTargetDesc() {
		return targetDesc;
	}

	public void setTargetDesc(String targetDesc) {
		this.targetDesc = targetDesc;
	}

	public int getNeedFinishTimes() {
		return needFinishTimes;
	}

	public void setNeedFinishTimes(int needFinishTimes) {
		this.needFinishTimes = needFinishTimes;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
