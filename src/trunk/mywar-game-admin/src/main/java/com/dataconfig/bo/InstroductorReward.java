package com.dataconfig.bo;

/**
 * InstroductorReward entity. @author MyEclipse Persistence Tools
 */

public class InstroductorReward implements java.io.Serializable {

	// Fields

	private Integer instroductorRewardId;
	private Integer beInvitedUserLevel;
	private String beInvitedUserReward;
	private String invitedUserReward;

	// Constructors

	/** default constructor */
	public InstroductorReward() {
	}

	/** full constructor */
	public InstroductorReward(Integer beInvitedUserLevel,
			String beInvitedUserReward, String invitedUserReward) {
		this.beInvitedUserLevel = beInvitedUserLevel;
		this.beInvitedUserReward = beInvitedUserReward;
		this.invitedUserReward = invitedUserReward;
	}

	// Property accessors

	public Integer getInstroductorRewardId() {
		return this.instroductorRewardId;
	}

	public void setInstroductorRewardId(Integer instroductorRewardId) {
		this.instroductorRewardId = instroductorRewardId;
	}

	public Integer getBeInvitedUserLevel() {
		return this.beInvitedUserLevel;
	}

	public void setBeInvitedUserLevel(Integer beInvitedUserLevel) {
		this.beInvitedUserLevel = beInvitedUserLevel;
	}

	public String getBeInvitedUserReward() {
		return this.beInvitedUserReward;
	}

	public void setBeInvitedUserReward(String beInvitedUserReward) {
		this.beInvitedUserReward = beInvitedUserReward;
	}

	public String getInvitedUserReward() {
		return this.invitedUserReward;
	}

	public void setInvitedUserReward(String invitedUserReward) {
		this.invitedUserReward = invitedUserReward;
	}

}