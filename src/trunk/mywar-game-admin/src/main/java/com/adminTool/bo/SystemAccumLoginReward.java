package com.adminTool.bo;
/**
 * 累计登录奖励
 * @author yezp
 *
 */
public class SystemAccumLoginReward {
	private Integer id;
	private Integer day;
	private String tools;
	private String toolsDesc;
	private Integer activityId;
	public String getToolsDesc() {
		return toolsDesc;
	}
	public void setToolsDesc(String toolsDesc) {
		this.toolsDesc = toolsDesc;
	}
	
	public SystemAccumLoginReward(Integer id, Integer day, String tools,
			 Integer activityId) {
		this.id = id;
		this.day = day;
		this.tools = tools;
		this.activityId = activityId;
	}
	
	public SystemAccumLoginReward() {
		
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getTools() {
		return tools;
	}
	public void setTools(String tools) {
		this.tools = tools;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	} 
}
