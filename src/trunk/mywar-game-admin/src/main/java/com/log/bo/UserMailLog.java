package com.log.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.log.bean.MailAttach;

/**
 * UserMailLog entity. @author MyEclipse Persistence Tools
 */

public class UserMailLog implements java.io.Serializable {

	// Fields

	private Integer userMailLogId;
	private Long userMailId;
	private Long senderId;
	private String senderName;
	private Long receiveId;
	private String receiveName;
	private Integer mailType;
	private String mailTheme;
	private String mailContent;
	private String mailAttach;
	private Timestamp sendTime;
	private Date getAttachTime;
	private Date delTime;
	private List<MailAttach> mailAttachList;
	

	// Constructors

	/** default constructor */
	public UserMailLog() {
	}

	/** minimal constructor */
	public UserMailLog(Long userMailId, Long senderId, String senderName,
			Long receiveId, String receiveName, Integer mailType,
			String mailTheme, String mailContent, Timestamp sendTime) {
		this.userMailId = userMailId;
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiveId = receiveId;
		this.receiveName = receiveName;
		this.mailType = mailType;
		this.mailTheme = mailTheme;
		this.mailContent = mailContent;
		this.sendTime = sendTime;
	}

	/** full constructor */
	public UserMailLog(Long userMailId, Long senderId, String senderName,
			Long receiveId, String receiveName, Integer mailType,
			String mailTheme, String mailContent, String mailAttach,
			Timestamp sendTime) {
		this.userMailId = userMailId;
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiveId = receiveId;
		this.receiveName = receiveName;
		this.mailType = mailType;
		this.mailTheme = mailTheme;
		this.mailContent = mailContent;
		this.mailAttach = mailAttach;
		this.sendTime = sendTime;
	}

	// Property accessors

	public Integer getUserMailLogId() {
		return this.userMailLogId;
	}

	public void setUserMailLogId(Integer userMailLogId) {
		this.userMailLogId = userMailLogId;
	}

	public Long getUserMailId() {
		return this.userMailId;
	}

	public void setUserMailId(Long userMailId) {
		this.userMailId = userMailId;
	}

	public Long getSenderId() {
		return this.senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return this.senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Long getReceiveId() {
		return this.receiveId;
	}

	public void setReceiveId(Long receiveId) {
		this.receiveId = receiveId;
	}

	public String getReceiveName() {
		return this.receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public Integer getMailType() {
		return this.mailType;
	}

	public void setMailType(Integer mailType) {
		this.mailType = mailType;
	}

	public String getMailTheme() {
		return this.mailTheme;
	}

	public void setMailTheme(String mailTheme) {
		this.mailTheme = mailTheme;
	}

	public String getMailContent() {
		return this.mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getMailAttach() {
		return this.mailAttach;
	}

	public void setMailAttach(String mailAttach) {
		this.mailAttach = mailAttach;
	}

	public Timestamp getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public void setGetAttachTime(Date getAttachTime) {
		this.getAttachTime = getAttachTime;
	}

	public Date getGetAttachTime() {
		return getAttachTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public Date getDelTime() {
		return delTime;
	}

	public void setMailAttachList(List<MailAttach> mailAttachList) {
		this.mailAttachList = mailAttachList;
	}

	public List<MailAttach> getMailAttachList() {
		return mailAttachList;
	}

}