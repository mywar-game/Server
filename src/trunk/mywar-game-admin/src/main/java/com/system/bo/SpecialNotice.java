package com.system.bo;

import java.util.Date;

/**
 * 特殊通知
 * 
 * @author lin
 */
public class SpecialNotice implements java.io.Serializable {

	private String serverId;
	private String title;
	private String content;
	private int isEnable;
	private Date createdTime;
	private Date updatedTime;
	private String content2;
	private String partnerId;
	private String partnerName;

	public SpecialNotice() {
	}

	public SpecialNotice(String serverId, String title, String content, int isEnable,
			Date createdTime, String partnerId) {
		this.serverId = serverId;
		this.title = title;
		this.content = content;
		this.isEnable = isEnable;
		this.createdTime = createdTime;
		this.partnerId = partnerId;
	}

	public SpecialNotice(String serverId, String title, String content, int isEnable,
			Date createdTime, Date updatedTime, String partnerId) {
		this.serverId = serverId;
		this.title = title;
		this.content = content;
		this.isEnable = isEnable;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.partnerId = partnerId;
	}

	
	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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
