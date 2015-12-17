package com.log.bo;

import java.sql.Timestamp;

/**
 * BattleLog entity. @author MyEclipse Persistence Tools
 */
public class BattleLog implements java.io.Serializable {

	// Fields

	private Long battleId;
	private String attackUserId;
	private String defenderUserId;
	private Integer type;
	private Timestamp battleBeginTime;
	private Timestamp battleEndTime;
	private Integer winnerSide;
	private String userReward;
	private String heroReward;
	private String redBattleInfo;
	private String blueBattleInfo;
	private String userSyncTimes;
	private String redRole;
	private String blueRole;
	private Integer setTime;
	private Integer isException;
	private String guildBattleId;
	private String wrongPostionTime;
    private Integer time;

	// Constructors

	/** default constructor */
	public BattleLog() {
	}

	/** minimal constructor */
	public BattleLog(String attackUserId, String defenderUserId, Integer type,
			Timestamp battleBeginTime, Timestamp battleEndTime,
			Integer winnerSide) {
		this.attackUserId = attackUserId;
		this.defenderUserId = defenderUserId;
		this.type = type;
		this.battleBeginTime = battleBeginTime;
		this.battleEndTime = battleEndTime;
		this.winnerSide = winnerSide;
	}

	/** full constructor */
	public BattleLog(String attackUserId, String defenderUserId, Integer type,
			Timestamp battleBeginTime, Timestamp battleEndTime,
			Integer winnerSide, String userReward, String heroReward,
			String redBattleInfo, String blueBattleInfo, String userSyncTimes,
			String redRole, String blueRole, Integer setTime,
			Integer isException, String guildBattleId, String wrongPostionTime) {
		this.attackUserId = attackUserId;
		this.defenderUserId = defenderUserId;
		this.type = type;
		this.battleBeginTime = battleBeginTime;
		this.battleEndTime = battleEndTime;
		this.winnerSide = winnerSide;
		this.userReward = userReward;
		this.heroReward = heroReward;
		this.redBattleInfo = redBattleInfo;
		this.blueBattleInfo = blueBattleInfo;
		this.userSyncTimes = userSyncTimes;
		this.redRole = redRole;
		this.blueRole = blueRole;
		this.setTime = setTime;
		this.isException = isException;
		this.guildBattleId = guildBattleId;
		this.wrongPostionTime = wrongPostionTime;
	}

	// Property accessors

	public Long getBattleId() {
		return this.battleId;
	}

	public void setBattleId(Long battleId) {
		this.battleId = battleId;
	}

	public String getAttackUserId() {
		return this.attackUserId;
	}

	public void setAttackUserId(String attackUserId) {
		this.attackUserId = attackUserId;
	}

	public String getDefenderUserId() {
		return this.defenderUserId;
	}

	public void setDefenderUserId(String defenderUserId) {
		this.defenderUserId = defenderUserId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getBattleBeginTime() {
		return this.battleBeginTime;
	}

	public void setBattleBeginTime(Timestamp battleBeginTime) {
		this.battleBeginTime = battleBeginTime;
	}

	public Timestamp getBattleEndTime() {
		return this.battleEndTime;
	}

	public void setBattleEndTime(Timestamp battleEndTime) {
		this.battleEndTime = battleEndTime;
	}

	public Integer getWinnerSide() {
		return this.winnerSide;
	}

	public void setWinnerSide(Integer winnerSide) {
		this.winnerSide = winnerSide;
	}

	public String getUserReward() {
		return this.userReward;
	}

	public void setUserReward(String userReward) {
		this.userReward = userReward;
	}

	public String getHeroReward() {
		return this.heroReward;
	}

	public void setHeroReward(String heroReward) {
		this.heroReward = heroReward;
	}

	public String getRedBattleInfo() {
		return this.redBattleInfo;
	}

	public void setRedBattleInfo(String redBattleInfo) {
		this.redBattleInfo = redBattleInfo;
	}

	public String getBlueBattleInfo() {
		return this.blueBattleInfo;
	}

	public void setBlueBattleInfo(String blueBattleInfo) {
		this.blueBattleInfo = blueBattleInfo;
	}

	public String getUserSyncTimes() {
		return this.userSyncTimes;
	}

	public void setUserSyncTimes(String userSyncTimes) {
		this.userSyncTimes = userSyncTimes;
	}

	public String getRedRole() {
		return this.redRole;
	}

	public void setRedRole(String redRole) {
		this.redRole = redRole;
	}

	public String getBlueRole() {
		return this.blueRole;
	}

	public void setBlueRole(String blueRole) {
		this.blueRole = blueRole;
	}

	public Integer getSetTime() {
		return this.setTime;
	}

	public void setSetTime(Integer setTime) {
		this.setTime = setTime;
	}

	public Integer getIsException() {
		return this.isException;
	}

	public void setIsException(Integer isException) {
		this.isException = isException;
	}

	public String getGuildBattleId() {
		return this.guildBattleId;
	}

	public void setGuildBattleId(String guildBattleId) {
		this.guildBattleId = guildBattleId;
	}

	public String getWrongPostionTime() {
		return this.wrongPostionTime;
	}

	public void setWrongPostionTime(String wrongPostionTime) {
		this.wrongPostionTime = wrongPostionTime;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}