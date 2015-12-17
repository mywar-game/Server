package com.adminTool.bo;

/**
 * 活跃度
 * @author Administrator
 *
 */
public class ActivityTask {

	private Integer activityTaskId;
	private Integer targetType;
	private String targetDesc;
	private Integer needFinishTimes;
	private Integer point;
	
	public ActivityTask() {
		
	}
	public ActivityTask(Integer activityTaskId, Integer targetType,
			String targetDesc, Integer needFinishTimes, Integer point) {
		super();
		this.activityTaskId = activityTaskId;
		this.targetType = targetType;
		this.targetDesc = targetDesc;
		this.needFinishTimes = needFinishTimes;
		this.point = point;
	}
	public Integer getActivityTaskId() {
		return activityTaskId;
	}
	public void setActivityTaskId(Integer activityTaskId) {
		this.activityTaskId = activityTaskId;
	}
	public Integer getTargetType() {
		return targetType;
	}
	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}
	public String getTargetDesc() {
		return targetDesc;
	}
	public void setTargetDesc(String targetDesc) {
		this.targetDesc = targetDesc;
	}
	public Integer getNeedFinishTimes() {
		return needFinishTimes;
	}
	public void setNeedFinishTimes(Integer needFinishTimes) {
		this.needFinishTimes = needFinishTimes;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
}
