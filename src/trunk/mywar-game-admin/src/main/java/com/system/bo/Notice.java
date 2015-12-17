package com.system.bo;

import java.util.Date;

/**
 * 通知
 * 
 * @author yezp
 */
public class Notice implements java.io.Serializable {

	private String serverId;
	private String title;
	private String content;
	private int isEnable;
	private Date createdTime;
	private Date updatedTime;
	private String content2;

	public Notice() {
	}

	public Notice(String serverId, String title, String content, int isEnable,
			Date createdTime) {
		this.serverId = serverId;
		this.title = title;
		this.content = content;
		this.isEnable = isEnable;
		this.createdTime = createdTime;
	}

	public Notice(String serverId, String title, String content, int isEnable,
			Date createdTime, Date updatedTime) {
		this.serverId = serverId;
		this.title = title;
		this.content = content;
		this.isEnable = isEnable;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

}
