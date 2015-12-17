package com.dataconfig.bo;

import java.sql.Timestamp;

/**
 * AActivationCode entity. @author MyEclipse Persistence Tools
 */

public class AActivationCode implements java.io.Serializable {

	// Fields

	private String activationCode;
	private Timestamp createTime;
	private Timestamp effectiveTime;
	private String platform;
	private String reward;

	// Constructors

	/** default constructor */
	public AActivationCode() {
	}

	/** minimal constructor */
	public AActivationCode(Timestamp createTime, Timestamp effectiveTime) {
		this.createTime = createTime;
		this.effectiveTime = effectiveTime;
	}

	/** full constructor */
	public AActivationCode(Timestamp createTime, Timestamp effectiveTime,
			String platform, String reward) {
		this.createTime = createTime;
		this.effectiveTime = effectiveTime;
		this.platform = platform;
		this.reward = reward;
	}

	// Property accessors

	public String getActivationCode() {
		return this.activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getEffectiveTime() {
		return this.effectiveTime;
	}

	public void setEffectiveTime(Timestamp effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getReward() {
		return this.reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

}