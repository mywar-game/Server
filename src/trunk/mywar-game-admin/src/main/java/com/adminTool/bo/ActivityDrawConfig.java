package com.adminTool.bo;

/**
 * 抽奖次数配置
 * 
 * @author yezp
 */
public class ActivityDrawConfig implements java.io.Serializable {

	private int systemId;
	private int activityId;
	private int drawTimes;
	private int drawNeedDiamond;

	public ActivityDrawConfig() {
	}

	public ActivityDrawConfig(int systemId, int activityId, int drawTimes,
			int drawNeedDiamond) {
		this.systemId = systemId;
		this.activityId = activityId;
		this.drawTimes = drawTimes;
		this.drawNeedDiamond = drawNeedDiamond;
	}

	public int getSystemId() {
		return this.systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public int getActivityId() {
		return this.activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getDrawTimes() {
		return this.drawTimes;
	}

	public void setDrawTimes(int drawTimes) {
		this.drawTimes = drawTimes;
	}

	public int getDrawNeedDiamond() {
		return this.drawNeedDiamond;
	}

	public void setDrawNeedDiamond(int drawNeedDiamond) {
		this.drawNeedDiamond = drawNeedDiamond;
	}

}
