package com.adminTool.bo;

/**
 * 抽奖轮盘配置
 * 
 * @author yezp
 */
public class ActivityDrawPos implements java.io.Serializable {

	private Integer id;
	private int activityId;
	private int pos;
	private int drawLowerNum;
	private int drawUpperNum;

	public ActivityDrawPos() {
	}

	public ActivityDrawPos(int activityId, int pos, int drawLowerNum,
			int drawUpperNum) {
		this.activityId = activityId;
		this.pos = pos;
		this.drawLowerNum = drawLowerNum;
		this.drawUpperNum = drawUpperNum;
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

	public int getPos() {
		return this.pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getDrawLowerNum() {
		return this.drawLowerNum;
	}

	public void setDrawLowerNum(int drawLowerNum) {
		this.drawLowerNum = drawLowerNum;
	}

	public int getDrawUpperNum() {
		return this.drawUpperNum;
	}

	public void setDrawUpperNum(int drawUpperNum) {
		this.drawUpperNum = drawUpperNum;
	}

}
