package com.adminTool.bo;

import java.io.Serializable;

public class SystemDiamondcat implements Serializable {

	public SystemDiamondcat() {}
	public SystemDiamondcat(int id, int diamondUse, float diamondReceive,
			int activityId) {
		super();
		this.id = id;
		this.diamondUse = diamondUse;
		this.diamondReceive = diamondReceive;
		this.activityId = activityId;
	}
	private int id;
	private int diamondUse;
	private float diamondReceive;
	private int activityId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiamondUse() {
		return diamondUse;
	}
	public void setDiamondUse(int diamondUse) {
		this.diamondUse = diamondUse;
	}
	public float getDiamondReceive() {
		return diamondReceive;
	}
	public void setDiamondReceive(float diamondReceive) {
		this.diamondReceive = diamondReceive;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
}
