package com.dataconfig.bo;

/**
 * UContinueLoginConstant entity. @author MyEclipse Persistence Tools
 */

public class UContinueLoginConstant implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer type;
	private Integer continueDays;
	private String rewardInfo;

	// Constructors

	/** default constructor */
	public UContinueLoginConstant() {
	}

	/** full constructor */
	public UContinueLoginConstant(Integer type, Integer continueDays,
			String rewardInfo) {
		this.type = type;
		this.continueDays = continueDays;
		this.rewardInfo = rewardInfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getContinueDays() {
		return this.continueDays;
	}

	public void setContinueDays(Integer continueDays) {
		this.continueDays = continueDays;
	}

	public String getRewardInfo() {
		return this.rewardInfo;
	}

	public void setRewardInfo(String rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

}