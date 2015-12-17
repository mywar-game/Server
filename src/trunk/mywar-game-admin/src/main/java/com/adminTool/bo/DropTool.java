package com.adminTool.bo;

public class DropTool {

	private Integer toolId;
	private Integer toolType;
	private Integer number;
	
	public DropTool(){}

	public DropTool(Integer toolType, Integer toolId, Integer number) {
		super();
		this.toolId = toolId;
		this.toolType = toolType;
		this.number = number;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	
}
