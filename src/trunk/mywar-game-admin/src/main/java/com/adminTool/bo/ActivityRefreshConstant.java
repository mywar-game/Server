package com.adminTool.bo;

public class ActivityRefreshConstant {

	private int id;
	
	private int activityId;
	private String refreshClassName;
	
	public ActivityRefreshConstant() {
		
	}
	public ActivityRefreshConstant(int id, int activityId, String refreshClassName) {
		super();
		this.id = id;
		this.activityId = activityId;
		this.refreshClassName = refreshClassName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getRefreshClassName() {
		return refreshClassName;
	}
	public void setRefreshClassName(String refreshClassName) {
		this.refreshClassName = refreshClassName;
	}
	
}
