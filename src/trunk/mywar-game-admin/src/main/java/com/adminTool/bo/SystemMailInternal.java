package com.adminTool.bo;

public class SystemMailInternal {

	private Integer internalMailId;
	private String mailTitle;
	private String mailContent;
	private Integer logicType;
	private Integer showType;
	private String tools;
	private String param;
	private String description;
	private String toolsDesc;
	
	public String getToolsDesc() {
		return toolsDesc;
	}

	public void setToolsDesc(String toolsDesc) {
		this.toolsDesc = toolsDesc;
	}

	public Integer getInternalMailId() {
		return internalMailId;
	}

	public void setInternalMailId(Integer internalMailId) {
		this.internalMailId = internalMailId;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public Integer getLogicType() {
		return logicType;
	}

	public void setLogicType(Integer logicType) {
		this.logicType = logicType;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SystemMailInternal() {}
}
