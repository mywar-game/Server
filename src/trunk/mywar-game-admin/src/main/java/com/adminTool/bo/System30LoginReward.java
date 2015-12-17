package com.adminTool.bo;

/**
 * 30天登录奖励
 * @author Administrator
 *
 */
public class System30LoginReward {

	private Integer system30LoginRewardId;
	private Integer day;
	private String toolIds;
	private String toolDesc;
	
	public System30LoginReward(Integer system30LoginRewardId, Integer day,
			String toolIds, String toolDesc) {
		super();
		this.system30LoginRewardId = system30LoginRewardId;
		this.day = day;
		this.toolIds = toolIds;
		this.toolDesc = toolDesc;
	}
	
	public System30LoginReward() {
		super();
	}
	
	public Integer getSystem30LoginRewardId() {
		return system30LoginRewardId;
	}
	public void setSystem30LoginRewardId(Integer system30LoginRewardId) {
		this.system30LoginRewardId = system30LoginRewardId;
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
	public String getToolDesc() {
		return toolDesc;
	}
	public void setToolDesc(String toolDesc) {
		this.toolDesc = toolDesc;
	}
}
