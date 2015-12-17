package com.adminTool.bo;

/**
 * 连续登录7天奖励
 * @author Administrator
 *
 */
public class System7LoginReward {
	private Integer id;
	private Integer day;
	private String toolIds;
	private String desc;
	
	public System7LoginReward() {
		
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

	public String getToolIds() {
		return toolIds;
	}

	public void setToolIds(String toolIds) {
		this.toolIds = toolIds;
	}
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
