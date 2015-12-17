package com.adminTool.bo;

public class GoodsLimitNumber {

	private Integer id;
	private Integer toolId;
	private Integer toolType;
	private String toolName;
	private Integer limitNumber;
	
	public GoodsLimitNumber() {
	
	}
	
	public GoodsLimitNumber(Integer toolId, Integer toolType, String toolName ,Integer limitNumber) {
		super();
		this.toolId = toolId;
		this.toolType = toolType;
		this.toolName = toolName;
		this.limitNumber = limitNumber;
	}
	
	public Integer getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(Integer limitNumber) {
		this.limitNumber = limitNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getToolId() {
		return toolId;
	}
	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}
	public Integer getToolType() {
		return toolType;
	}
	public void setToolType(Integer toolType) {
		this.toolType = toolType;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	
	
}