package com.fantingame.game.mywar.logic.mail.model;

public class SystemMailInternal {

	private int internalMailId;
	private String mailTitle;
	private String mailContent;
	private int logicType;
	private int showType;
	private String tools;
	private String param;
	private String description;

	public int getInternalMailId() {
		return internalMailId;
	}

	public void setInternalMailId(int internalMailId) {
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

	public int getLogicType() {
		return logicType;
	}

	public void setLogicType(int logicType) {
		this.logicType = logicType;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}
}
