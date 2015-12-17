package com.fantingame.game.mywar.logic.forces.model;

public class SystemForcesMonster {
	private int forcesId;
	private String monsterId;
	private int forcesType;
	private int attackLimitTimes;
	private int collectCdTime;

	public int getForcesId() {
		return forcesId;
	}

	public void setForcesId(int forcesId) {
		this.forcesId = forcesId;
	}

	public int getForcesType() {
		return forcesType;
	}

	public void setForcesType(int forcesType) {
		this.forcesType = forcesType;
	}

	public String getMonsterId() {
		return monsterId;
	}

	public void setMonsterId(String monsterId) {
		this.monsterId = monsterId;
	}

	public int getAttackLimitTimes() {
		return attackLimitTimes;
	}

	public void setAttackLimitTimes(int attackLimitTimes) {
		this.attackLimitTimes = attackLimitTimes;
	}

	public int getCollectCdTime() {
		return collectCdTime;
	}

	public void setCollectCdTime(int collectCdTime) {
		this.collectCdTime = collectCdTime;
	}

}
