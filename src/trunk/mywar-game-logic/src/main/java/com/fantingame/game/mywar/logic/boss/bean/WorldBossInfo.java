package com.fantingame.game.mywar.logic.boss.bean;

import java.util.Date;

public class WorldBossInfo {
	/**
	 * 所在地图
	 */
	private int mapId;
	// boss
	private int systemHeroId;
	/**
	 * boss等级
	 */
	private int bossLevel;
	/**
	 * 最大血量
	 */
	private long maxLife;
	/**
	 * 当前血量
	 */
	private long life;
	/**
	 * 持续时长 
	 */
	private int continueTimes;
	/**
	 * boss开启时间
	 */
	private Date openTime;
	/**
	 * 1 开启  0 关闭
	 */
	private int bossStatus;

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public int getSystemHeroId() {
		return systemHeroId;
	}

	public void setSystemHeroId(int systemHeroId) {
		this.systemHeroId = systemHeroId;
	}

	public int getBossLevel() {
		return bossLevel;
	}

	public void setBossLevel(int bossLevel) {
		this.bossLevel = bossLevel;
	}

	public long getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(long maxLife) {
		this.maxLife = maxLife;
	}

	public long getLife() {
		return life;
	}

	public void setLife(long life) {
		this.life = life;
	}

	public int getContinueTimes() {
		return continueTimes;
	}

	public void setContinueTimes(int continueTimes) {
		this.continueTimes = continueTimes;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public int getBossStatus() {
		return bossStatus;
	}

	public void setBossStatus(int bossStatus) {
		this.bossStatus = bossStatus;
	}

}
