package com.adminTool.bo;

import java.util.Date;

public class SearchMailClass {

	private Integer ID;
	private String title;
	private String tool;
	private Integer systemMailId;
	private Date createTime;
	private Integer receiveStatus;
	private Integer status;
	
	private String receiveStatusName;
	private String statusName;
	
	public String getReceiveStatusName() {
		return receiveStatusName;
	}

	public void setReceiveStatusName(String receiveStatusName) {
		this.receiveStatusName = receiveStatusName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public SearchMailClass() {
		super();
	}
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTool() {
		return tool;
	}
	public void setTool(String tool) {
		this.tool = tool;
	}
	public Integer getSystemMailId() {
		return systemMailId;
	}
	public void setSystemMailId(Integer systemMailId) {
		this.systemMailId = systemMailId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
