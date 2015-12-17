package com.stats.bo;

/**
 * PayIntervalStats entity. @author MyEclipse Persistence Tools
 */

public class PayIntervalStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String payInterval;
	private Integer userNum;

	// Constructors

	/** default constructor */
	public PayIntervalStats() {
	}

	/** full constructor */
	public PayIntervalStats(Integer sysNum, String payInterval, Integer userNum) {
		this.sysNum = sysNum;
		this.payInterval = payInterval;
		this.userNum = userNum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public String getPayInterval() {
		return this.payInterval;
	}

	public void setPayInterval(String payInterval) {
		this.payInterval = payInterval;
	}

	public Integer getUserNum() {
		return this.userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

}