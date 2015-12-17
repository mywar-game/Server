package com.adminTool.bo;

/**
 * 抽奖等级替换配置
 * 
 * @author yezp
 */
public class ActivityDrawLevelGoods implements java.io.Serializable {

	private Integer id;
	private int activityId;
	private int level;
	private int replacePos;
	private int toolType;
	private int toolId;
	private int toolNum;
	private int refreshLowerNum;
	private int refreshUpperNum;

	private String toolTypeString;
	private String toolString;

	public ActivityDrawLevelGoods() {
	}

	public ActivityDrawLevelGoods(int activityId, int level, int replacePos,
			int toolType, int toolId, int toolNum, int refreshLowerNum,
			int refreshUpperNum) {
		this.activityId = activityId;
		this.level = level;
		this.replacePos = replacePos;
		this.toolType = toolType;
		this.toolId = toolId;
		this.toolNum = toolNum;
		this.refreshLowerNum = refreshLowerNum;
		this.refreshUpperNum = refreshUpperNum;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getActivityId() {
		return this.activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getReplacePos() {
		return this.replacePos;
	}

	public void setReplacePos(int replacePos) {
		this.replacePos = replacePos;
	}

	public int getToolType() {
		return this.toolType;
	}

	public void setToolType(int toolType) {
		this.toolType = toolType;
	}

	public String getToolTypeString() {
		return toolTypeString;
	}

	public void setToolTypeString(String toolTypeString) {
		this.toolTypeString = toolTypeString;
	}

	public String getToolString() {
		return toolString;
	}

	public void setToolString(String toolString) {
		this.toolString = toolString;
	}

	public int getToolId() {
		return this.toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

	public int getToolNum() {
		return this.toolNum;
	}

	public void setToolNum(int toolNum) {
		this.toolNum = toolNum;
	}

	public int getRefreshLowerNum() {
		return this.refreshLowerNum;
	}

	public void setRefreshLowerNum(int refreshLowerNum) {
		this.refreshLowerNum = refreshLowerNum;
	}

	public int getRefreshUpperNum() {
		return this.refreshUpperNum;
	}

	public void setRefreshUpperNum(int refreshUpperNum) {
		this.refreshUpperNum = refreshUpperNum;
	}

}
