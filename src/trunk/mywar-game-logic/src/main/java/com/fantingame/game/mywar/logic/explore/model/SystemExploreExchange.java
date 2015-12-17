package com.fantingame.game.mywar.logic.explore.model;

/**
 * 探索积分兑换
 * 
 * @author yezp
 */
public class SystemExploreExchange {

	private int systemHeroId;
	
	private int camp;

	private int needIntegral;

	private int needLevel;

	private int needVipLevel;

	public int getSystemHeroId() {
		return systemHeroId;
	}

	public void setSystemHeroId(int systemHeroId) {
		this.systemHeroId = systemHeroId;
	}

	public int getCamp() {
		return camp;
	}

	public void setCamp(int camp) {
		this.camp = camp;
	}

	public int getNeedIntegral() {
		return needIntegral;
	}

	public void setNeedIntegral(int needIntegral) {
		this.needIntegral = needIntegral;
	}

	public int getNeedLevel() {
		return needLevel;
	}

	public void setNeedLevel(int needLevel) {
		this.needLevel = needLevel;
	}

	public int getNeedVipLevel() {
		return needVipLevel;
	}

	public void setNeedVipLevel(int needVipLevel) {
		this.needVipLevel = needVipLevel;
	}

}
