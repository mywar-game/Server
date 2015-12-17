
package com.adminTool.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class AdminMail implements Serializable{

	private static final long serialVersionUID = -9142902604822377924L;
	
	private Integer adminMailId;
	private String title;
	private String lodoIds;
	private Integer target;
	private String partner;
	// 1.选择登录用户请输入登录时间 2.发送全服请输入注册时间 3.充值用户请输入充值时间
	private Date date;
	private String content;
	private String createdUser;
	private String approveUser;
	private Integer status;
	private String toolIds;
	private String toolList;
	private String serverIds; // 要发送到那些服务器
	private String sendServerIds;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private Timestamp approveTime;
	
	private String senderm; // 发送者
	
	private String auditer; // 审核者

	public AdminMail() {
		
	}
	
	public AdminMail(String partner, Integer adminMailId, String title, String lodoIds,
			Integer target, Date date, Integer status, String content, String createdUser,
			String approveUser, String toolList, String toolIds, String serverIds,
			String sendServerIds, Timestamp createdTime, Timestamp updatedTime,
			Timestamp approveTime) {
		super();
		this.adminMailId = adminMailId;
		this.title = title;
		this.lodoIds = lodoIds;
		this.target = target;
		this.date = date;
		this.content = content;
		this.createdUser = createdUser;
		this.approveUser = approveUser;
		this.toolIds = toolIds;
		this.serverIds = serverIds;
		this.sendServerIds = sendServerIds;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.approveTime = approveTime;
		this.status = status;
		this.partner = partner;
	}

	public AdminMail(Integer adminMailId, String title, String lodoIds,
			Integer target, String partner, Date date, String content,
			String createdUser, String approveUser, Integer status,
			String toolIds, String toolList, String serverIds,
			String sendServerIds, Timestamp createdTime, Timestamp updatedTime,
			Timestamp approveTime, String senderm, String auditer) {
		super();
		this.adminMailId = adminMailId;
		this.title = title;
		this.lodoIds = lodoIds;
		this.target = target;
		this.partner = partner;
		this.date = date;
		this.content = content;
		this.createdUser = createdUser;
		this.approveUser = approveUser;
		this.status = status;
		this.toolIds = toolIds;
		this.toolList = toolList;
		this.serverIds = serverIds;
		this.sendServerIds = sendServerIds;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.approveTime = approveTime;
		this.senderm = senderm;
		this.auditer = auditer;
	}

	public String getSenderm() {
		return senderm;
	}

	public void setSenderm(String senderm) {
		this.senderm = senderm;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getToolList() {
		return toolList;
	}

	public void setToolList(String toolList) {
		this.toolList = toolList;
	}

	public Integer getStatus() {
		return status;
	}

	/**
	 * 邮件状态，-1：拒绝，0：正在审批，1：通过
	 * @param status
	 */

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAdminMailId() {
		return adminMailId;
	}
	public void setAdminMailId(Integer adminMailId) {
		this.adminMailId = adminMailId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLodoIds() {
		return lodoIds;
	}
	public void setLodoIds(String lodoIds) {
		this.lodoIds = lodoIds;
	}
	public Integer getTarget() {
		return target;
	}
	
	/**
	 * target 属性：1表示全服发送，0为发送到指定用户
	 * @param target
	 */
	public void setTarget(Integer target) {
		this.target = target;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getApproveUser() {
		return approveUser;
	}
	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
	}
	public String getToolIds() {
		return toolIds;
	}
	public void setToolIds(String toolIds) {
		this.toolIds = toolIds;
	}
	public String getServerIds() {
		return serverIds;
	}
	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}
	public String getSendServerIds() {
		return sendServerIds;
	}
	public void setSendServerIds(String sendServerIds) {
		this.sendServerIds = sendServerIds;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Timestamp getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Timestamp approveTime) {
		this.approveTime = approveTime;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}
}
