package com.fantingame.game.mywar.logic.mail.model;

import java.util.Date;

public class SystemMail {

	private String systemMailId;

	private String title;

	private String content;

	private String toolIds;

	private int target;

	private String partner;

	private int sourceId;

	private String lodoIds;

	private Date createdTime;

	private Date updatedTime;

	private Date mailTime;

	public String getSystemMailId() {
		return systemMailId;
	}

	public void setSystemMailId(String systemMailId) {
		this.systemMailId = systemMailId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getToolIds() {
		return toolIds;
	}

	public void setToolIds(String toolIds) {
		this.toolIds = toolIds;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public String getLodoIds() {
		return lodoIds;
	}

	public void setLodoIds(String lodoIds) {
		this.lodoIds = lodoIds;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public Date getMailTime() {
		return mailTime;
	}

	public void setMailTime(Date mailTime) {
		this.mailTime = mailTime;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}
}
