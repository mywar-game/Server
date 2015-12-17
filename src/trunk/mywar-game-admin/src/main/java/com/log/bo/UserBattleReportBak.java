package com.log.bo;

/**
 * UserBattleReportBak entity. @author MyEclipse Persistence Tools
 */

public class UserBattleReportBak implements java.io.Serializable {

	// Fields

	private Integer userBattleReportId;
	private String userId;
	private Integer userLevel;
	private String targetId;
	private Short type;
	private Integer flag;
	private String creatTime;

	// Constructors

	/** default constructor */
	public UserBattleReportBak() {
	}

	/** minimal constructor */
	public UserBattleReportBak(String userId, Integer userLevel) {
		this.userId = userId;
		this.userLevel = userLevel;
	}

	/** full constructor */
	public UserBattleReportBak(String userId, Integer userLevel,
			String targetId, Short type, Integer flag, String creatTime) {
		this.userId = userId;
		this.userLevel = userLevel;
		this.targetId = targetId;
		this.type = type;
		this.flag = flag;
		this.creatTime = creatTime;
	}

	// Property accessors

	public Integer getUserBattleReportId() {
		return this.userBattleReportId;
	}

	public void setUserBattleReportId(Integer userBattleReportId) {
		this.userBattleReportId = userBattleReportId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getTargetId() {
		return this.targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

}