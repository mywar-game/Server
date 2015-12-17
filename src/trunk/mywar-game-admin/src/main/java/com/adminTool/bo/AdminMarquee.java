package com.adminTool.bo;

import java.util.Date;

/**
 * 跑马灯
 * 
 * @author yezp
 */
public class AdminMarquee implements java.io.Serializable {

	private Integer id;
	private String content;
	private Date startTime;
	private Date endTime;
	private Date createdTime;
	private Date playTime;
	private int playInterval;
	private int isUse;
	private String serverIds;
	private String serverNames;
	private int loopTime; // 循环播放次数
	private int hasLoopTime; // 已经循环多少次数
	private String partnerIds;
	private String partnerNames;

	public int getHasLoopTime() {
		return hasLoopTime;
	}

	public void setHasLoopTime(int hasLoopTime) {
		this.hasLoopTime = hasLoopTime;
	}

	public AdminMarquee() {
	}

	public AdminMarquee(String content, Date startTime, Date endTime,
			Date createdTime, Date playTime, int playInterval, int isUse,
			String serverIds,String partnerIds) {
		this.content = content;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createdTime = createdTime;
		this.playTime = playTime;
		this.playInterval = playInterval;
		this.isUse = isUse;
		this.serverIds = serverIds;
		this.partnerIds = partnerIds;
	}
	
	public String getPartnerIds() {
		return partnerIds;
	}

	public void setPartnerIds(String partnerIds) {
		this.partnerIds = partnerIds;
	}

	public String getPartnerNames() {
		return partnerNames;
	}

	public void setPartnerNames(String partnerNames) {
		this.partnerNames = partnerNames;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}

	public int getPlayInterval() {
		return this.playInterval;
	}

	public void setPlayInterval(int playInterval) {
		this.playInterval = playInterval;
	}

	public int getIsUse() {
		return this.isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public String getServerIds() {
		return this.serverIds;
	}

	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}

	public String getServerNames() {
		return serverNames;
	}

	public void setServerNames(String serverNames) {
		this.serverNames = serverNames;
	}

	public int getLoopTime() {
		return loopTime;
	}

	public void setLoopTime(int loopTime) {
		this.loopTime = loopTime;
	}

}
