package com.fantingame.game.mywar.logic.hero.model;

/**
 * 团队技能配置表
 * 
 * @author yezp
 */
public class SystemHeroSkillConfig {

	private int systemHeroSkillId;
	private int level;
	private int vipLevel;
	private int money;
	private int gold;

	public int getSystemHeroSkillId() {
		return systemHeroSkillId;
	}

	public void setSystemHeroSkillId(int systemHeroSkillId) {
		this.systemHeroSkillId = systemHeroSkillId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

}
