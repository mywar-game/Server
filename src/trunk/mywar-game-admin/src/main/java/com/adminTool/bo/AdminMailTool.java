package com.adminTool.bo;

/**
 * 通过邮件发送的道具
 * @author chengevo
 *
 */
public class AdminMailTool {

	private Integer id;
	private Integer toolId;
	private Integer toolType;
	private String toolName;
	
	public AdminMailTool() {
	
	}
	
	public AdminMailTool(Integer toolId, Integer toolType, String toolName) {
		super();
		this.toolId = toolId;
		this.toolType = toolType;
		this.toolName = toolName;
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
