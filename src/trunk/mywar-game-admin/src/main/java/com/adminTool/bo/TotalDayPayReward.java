package com.adminTool.bo;

/**
 * 充值累积天数
 * 
 * @author yezp
 */
public class TotalDayPayReward implements java.io.Serializable {

	private Integer id;
	private int day;
	private String dropTool;
	private String toolDesc;

	private String showDropTool;
	private Integer activityId;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public TotalDayPayReward() {
	}

	public TotalDayPayReward(int day, String dropTool, String toolDesc, int activityId) {
		this.day = day;
		this.dropTool = dropTool;
		this.toolDesc = toolDesc;
		this.activityId = activityId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getDay() {
		return this.day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getDropTool() {
		return this.dropTool;
	}

	public void setDropTool(String dropTool) {
		this.dropTool = dropTool;
	}

	public String getToolDesc() {
		return this.toolDesc;
	}

	public void setToolDesc(String toolDesc) {
		this.toolDesc = toolDesc;
	}

	public String getShowDropTool() {
		return showDropTool;
	}

	public void setShowDropTool(String showDropTool) {
		this.showDropTool = showDropTool;
	}

}
