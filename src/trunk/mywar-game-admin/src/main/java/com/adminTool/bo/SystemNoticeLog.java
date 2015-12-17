package com.adminTool.bo;

import java.sql.Time;
import java.util.Date;

/**
 * SystemNoticeLog entity. @author MyEclipse Persistence Tools
 */

public class SystemNoticeLog implements java.io.Serializable {

	// Fields

	private Integer noticeLogId;
	private Date startDate;
	private Date endDate;
	private Time startTime;
	private Time endTime;
	private Integer period;
	private String content;
	private Integer type;

	// Constructors

	/** default constructor */
	public SystemNoticeLog() {
	}

	/** full constructor */
	public SystemNoticeLog(Date startDate, Date endDate, Time startTime,
			Time endTime, Integer period, String content, Integer type) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.period = period;
		this.content = content;
		this.type = type;
	}

	// Property accessors

	public Integer getNoticeLogId() {
		return this.noticeLogId;
	}

	public void setNoticeLogId(Integer noticeLogId) {
		this.noticeLogId = noticeLogId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Integer getPeriod() {
		return this.period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}