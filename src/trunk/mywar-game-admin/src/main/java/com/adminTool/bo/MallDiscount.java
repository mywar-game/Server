package com.adminTool.bo;

import java.io.Serializable;
import java.sql.Timestamp;

public class MallDiscount implements Serializable{

	
	private static final long serialVersionUID = 5691384743175441410L;
	private Integer id;
	private String activityId;
	private Timestamp startTime;
	private Timestamp endTime;
	private String description;
	private String servers;
	
	/**
	 * 提交时间，显示在审批列表中
	 */
	private Timestamp submitTime;
	
	/**
	 * 活动审批状态，-1表示拒绝，0表示等待审批，1表示审批通过，2 表示在活动中途删除，3表示活动时间结束
	 */
	private Integer status;
	private String countdown;
	
	public MallDiscount() {
		
	}
	
	
	public MallDiscount(String activityId, Timestamp startTime, Timestamp endTime,
			String countdown) {
		super();
		this.activityId = activityId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.countdown = countdown;
	}


	public String getServers() {
		return servers;
	}


	public void setServers(String servers) {
		this.servers = servers;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Timestamp getSubmitTime() {
		return submitTime;
	}


	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}


	public Integer getStatus() {
		return status;
	}

	/**
	 * 活动审批状态，-1表示拒绝，0表示等待审批，1表示审批通过，2 表示在活动中途删除，3表示活动时间结束
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Timestamp getStartTime() {
		return startTime;
	}


	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}


	public Timestamp getEndTime() {
		return endTime;
	}


	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}


	public String getActivityId() {
		return activityId;
	}


	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}


	public String getCountdown() {
		return countdown;
	}

	public void setCountdown(String countdown) {
		this.countdown = countdown;
	}
	
}
