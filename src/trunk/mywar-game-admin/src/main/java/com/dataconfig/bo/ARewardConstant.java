package com.dataconfig.bo;

/**
 * ARewardConstant entity. @author MyEclipse Persistence Tools
 */

/**
 * 活动中心奖励
 * @author hzy
 * 2013-6-5
 */
public class ARewardConstant implements java.io.Serializable {

	// Fields

	private Integer rewrdId;
	private String targetId;
	private String rewardContent;
	private Integer type;
	private String rewardDesc;

	// Constructors

	/** default constructor */
	public ARewardConstant() {
	}

	/** minimal constructor */
	public ARewardConstant(String rewardContent, Integer type) {
		this.rewardContent = rewardContent;
		this.type = type;
	}

	/** full constructor */
	public ARewardConstant(String targetId, String rewardContent, Integer type,
			String rewardDesc) {
		this.targetId = targetId;
		this.rewardContent = rewardContent;
		this.type = type;
		this.rewardDesc = rewardDesc;
	}

	// Property accessors

	public Integer getRewrdId() {
		return this.rewrdId;
	}

	public void setRewrdId(Integer rewrdId) {
		this.rewrdId = rewrdId;
	}

	public String getTargetId() {
		return this.targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getRewardContent() {
		return this.rewardContent;
	}

	public void setRewardContent(String rewardContent) {
		this.rewardContent = rewardContent;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRewardDesc() {
		return this.rewardDesc;
	}

	public void setRewardDesc(String rewardDesc) {
		this.rewardDesc = rewardDesc;
	}

}