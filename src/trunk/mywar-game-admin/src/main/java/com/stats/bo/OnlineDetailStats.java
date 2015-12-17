package com.stats.bo;

import java.util.Date;

/**
 * OnlineDetailStats entity. @author MyEclipse Persistence Tools
 */

public class OnlineDetailStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private String channel;
	private Integer sysNum;
	private Integer onlineAmount;
	private String onlineUserStr;
	private Integer ipNum;
	private Integer fiveMinuteIndex;
	private Date time;
	
	
	private String statsTime;
	// Constructors

	/** default constructor */
	public OnlineDetailStats() {
	}

	/** full constructor */
	public OnlineDetailStats(String channel, Integer sysNum,
			Integer onlineAmount, String onlineUserStr,Integer ipNum,
			Integer fiveMinuteIndex, Date time) {
		this.channel = channel;
		this.sysNum = sysNum;
		this.onlineAmount = onlineAmount;
		this.onlineUserStr = onlineUserStr;
		this.ipNum = ipNum;
		this.fiveMinuteIndex = fiveMinuteIndex;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public Integer getOnlineAmount() {
		return this.onlineAmount;
	}

	public void setOnlineAmount(Integer onlineAmount) {
		this.onlineAmount = onlineAmount;
	}

	public String getOnlineUserStr() {
		return this.onlineUserStr;
	}

	public void setOnlineUserStr(String onlineUserStr) {
		this.onlineUserStr = onlineUserStr;
	}

	public Integer getFiveMinuteIndex() {
		return this.fiveMinuteIndex;
	}

	public void setFiveMinuteIndex(Integer fiveMinuteIndex) {
		this.fiveMinuteIndex = fiveMinuteIndex;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getIpNum() {
		return ipNum;
	}

	public void setIpNum(Integer ipNum) {
		this.ipNum = ipNum;
	}

	public String getStatsTime() {
		return statsTime;
	}

	public void setStatsTime(String statsTime) {
		this.statsTime = statsTime;
	}

}