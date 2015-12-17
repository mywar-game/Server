package com.adminTool.bo;

/**
 * StatisticsInfo entity. @author MyEclipse Persistence Tools
 */

public class StatisticsInfo implements java.io.Serializable {

	// Fields

	private Integer statisticsInfoId;
	private Long totalGoldenNum;
	private Integer totalRegisterNum;
	private Integer totalPayNum;
	private Integer totalOnlineTime;
	private Integer averageOnlineTime;
	private Integer sysNum;

	// Constructors

	/** default constructor */
	public StatisticsInfo() {
	}

	/** full constructor */
	public StatisticsInfo(Long totalGoldenNum, Integer totalRegisterNum,
			Integer totalPayNum, Integer totalOnlineTime,
			Integer averageOnlineTime, Integer sysNum) {
		this.totalGoldenNum = totalGoldenNum;
		this.totalRegisterNum = totalRegisterNum;
		this.totalPayNum = totalPayNum;
		this.totalOnlineTime = totalOnlineTime;
		this.averageOnlineTime = averageOnlineTime;
		this.sysNum = sysNum;
	}

	// Property accessors

	public Integer getStatisticsInfoId() {
		return this.statisticsInfoId;
	}

	public void setStatisticsInfoId(Integer statisticsInfoId) {
		this.statisticsInfoId = statisticsInfoId;
	}

	public Long getTotalGoldenNum() {
		return this.totalGoldenNum;
	}

	public void setTotalGoldenNum(Long totalGoldenNum) {
		this.totalGoldenNum = totalGoldenNum;
	}

	public Integer getTotalRegisterNum() {
		return this.totalRegisterNum;
	}

	public void setTotalRegisterNum(Integer totalRegisterNum) {
		this.totalRegisterNum = totalRegisterNum;
	}

	public Integer getTotalPayNum() {
		return this.totalPayNum;
	}

	public void setTotalPayNum(Integer totalPayNum) {
		this.totalPayNum = totalPayNum;
	}

	public Integer getTotalOnlineTime() {
		return this.totalOnlineTime;
	}

	public void setTotalOnlineTime(Integer totalOnlineTime) {
		this.totalOnlineTime = totalOnlineTime;
	}

	public Integer getAverageOnlineTime() {
		return this.averageOnlineTime;
	}

	public void setAverageOnlineTime(Integer averageOnlineTime) {
		this.averageOnlineTime = averageOnlineTime;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

}