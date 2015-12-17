package com.adminTool.bo;

/**
 * 抽奖物品
 * 
 * @author yezp
 */
public class ActivityDrawGoods implements java.io.Serializable {

	private int goodsId;
	private int activityId;
	private int pos;
	private int toolType;
	private int toolId;
	private int toolNum;
	private int refreshLowerNum;
	private int refreshUpperNum;

	private String toolName;
	private String toolTypeName;

	public ActivityDrawGoods() {
	}

	public ActivityDrawGoods(int goodsId, int activityId, int pos,
			int toolType, int toolId, int toolNum, int refreshLowerNum,
			int refreshUpperNum) {
		this.goodsId = goodsId;
		this.activityId = activityId;
		this.pos = pos;
		this.toolType = toolType;
		this.toolId = toolId;
		this.toolNum = toolNum;
		this.refreshLowerNum = refreshLowerNum;
		this.refreshUpperNum = refreshUpperNum;
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getActivityId() {
		return this.activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getPos() {
		return this.pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getToolType() {
		return this.toolType;
	}

	public void setToolType(int toolType) {
		this.toolType = toolType;
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

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getToolTypeName() {
		return toolTypeName;
	}

	public void setToolTypeName(String toolTypeName) {
		this.toolTypeName = toolTypeName;
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
