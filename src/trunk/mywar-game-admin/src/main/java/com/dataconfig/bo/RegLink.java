package com.dataconfig.bo;

import java.sql.Timestamp;

/**
 * RegLink entity. @author MyEclipse Persistence Tools
 */

public class RegLink implements java.io.Serializable {

	// Fields

	private String regLinkId;
	private Timestamp createTime;
	private Integer effectiveNum;
	private Timestamp effectiveTime;
	private String reward;
	private Integer useNum;

	// Constructors

	/** default constructor */
	public RegLink() {
	}

	/** full constructor */
	public RegLink(Timestamp createTime, Integer effectiveNum,
			Timestamp effectiveTime, String reward) {
		this.createTime = createTime;
		this.effectiveNum = effectiveNum;
		this.effectiveTime = effectiveTime;
		this.reward = reward;
	}

	// Property accessors

	public String getRegLinkId() {
		return this.regLinkId;
	}

	public void setRegLinkId(String regLinkId) {
		this.regLinkId = regLinkId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getEffectiveNum() {
		return this.effectiveNum;
	}

	public void setEffectiveNum(Integer effectiveNum) {
		this.effectiveNum = effectiveNum;
	}

	public Timestamp getEffectiveTime() {
		return this.effectiveTime;
	}

	public void setEffectiveTime(Timestamp effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getReward() {
		return this.reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	/**
	 * 获取 useNum 
	 */
	public Integer getUseNum() {
		return useNum;
	}

	/**
	 * 设置 useNum 
	 */
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

}