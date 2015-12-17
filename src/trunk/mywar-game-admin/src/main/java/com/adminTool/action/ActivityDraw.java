package com.adminTool.action;

import com.framework.common.ALDAdminActionSupport;

/**
 * 活动抽奖
 * 
 * @author yezp
 */
public class ActivityDraw extends ALDAdminActionSupport {

	private static final long serialVersionUID = -3998887706169054374L;

	private String activityId;

	public String execute() {
		return SUCCESS;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

}
