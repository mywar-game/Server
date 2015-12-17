package com.adminTool.action;

import com.framework.common.ALDAdminActionSupport;

public class AddSystemDayTotalPayReward extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1L;

	private int activityId = -1;
	
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	@Override
	public String execute() {
		return SUCCESS;
	}
}
