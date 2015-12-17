package com.dataconfig.bo;

/**
 * AlchemyConstant entity. @author MyEclipse Persistence Tools
 */

public class AlchemyConstant implements java.io.Serializable {

	// Fields

	private Integer alchemyLevel;
	private Integer alchemyMaxExp;
	private Integer friendAlchemyMaxMoney;
	private Integer alchemyRewardMoney;
	private Integer friendAlchemyRewardMoney;
	private Integer alchemyTransferRewardMoney;

	// Constructors

	/** default constructor */
	public AlchemyConstant() {
	}

	/** full constructor */
	public AlchemyConstant(Integer alchemyMaxExp,
			Integer friendAlchemyMaxMoney, Integer alchemyRewardMoney,
			Integer friendAlchemyRewardMoney, Integer alchemyTransferRewardMoney) {
		this.alchemyMaxExp = alchemyMaxExp;
		this.friendAlchemyMaxMoney = friendAlchemyMaxMoney;
		this.alchemyRewardMoney = alchemyRewardMoney;
		this.friendAlchemyRewardMoney = friendAlchemyRewardMoney;
		this.alchemyTransferRewardMoney = alchemyTransferRewardMoney;
	}

	// Property accessors

	public Integer getAlchemyLevel() {
		return this.alchemyLevel;
	}

	public void setAlchemyLevel(Integer alchemyLevel) {
		this.alchemyLevel = alchemyLevel;
	}

	public Integer getAlchemyMaxExp() {
		return this.alchemyMaxExp;
	}

	public void setAlchemyMaxExp(Integer alchemyMaxExp) {
		this.alchemyMaxExp = alchemyMaxExp;
	}

	public Integer getFriendAlchemyMaxMoney() {
		return this.friendAlchemyMaxMoney;
	}

	public void setFriendAlchemyMaxMoney(Integer friendAlchemyMaxMoney) {
		this.friendAlchemyMaxMoney = friendAlchemyMaxMoney;
	}

	public Integer getAlchemyRewardMoney() {
		return this.alchemyRewardMoney;
	}

	public void setAlchemyRewardMoney(Integer alchemyRewardMoney) {
		this.alchemyRewardMoney = alchemyRewardMoney;
	}

	public Integer getFriendAlchemyRewardMoney() {
		return this.friendAlchemyRewardMoney;
	}

	public void setFriendAlchemyRewardMoney(Integer friendAlchemyRewardMoney) {
		this.friendAlchemyRewardMoney = friendAlchemyRewardMoney;
	}

	public Integer getAlchemyTransferRewardMoney() {
		return this.alchemyTransferRewardMoney;
	}

	public void setAlchemyTransferRewardMoney(Integer alchemyTransferRewardMoney) {
		this.alchemyTransferRewardMoney = alchemyTransferRewardMoney;
	}

}