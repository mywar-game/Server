package com.adminTool.bo;

/**
 * 登录抽奖活动
 * @author Administrator
 *
 */
public class SystemLoginDraw {

	private Integer pos;
	private String rewards;
	private String drawRation;
	private Integer activityId;
	private Integer posXer;
	private Integer poxYer;
	
	public Integer getPosXer() {
		return posXer;
	}

	public void setPosXer(Integer posXer) {
		this.posXer = posXer;
	}

	public Integer getPoxYer() {
		return poxYer;
	}

	public void setPoxYer(Integer poxYer) {
		this.poxYer = poxYer;
	}

	private Integer imgId;
	
	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public String getDrawRation() {
		return drawRation;
	}

	public void setDrawRation(String drawRation) {
		this.drawRation = drawRation;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public SystemLoginDraw() {}
	
}
