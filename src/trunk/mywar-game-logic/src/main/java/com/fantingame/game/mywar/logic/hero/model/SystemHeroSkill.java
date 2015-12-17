package com.fantingame.game.mywar.logic.hero.model;

public class SystemHeroSkill {
	private int systemHeroSkillId;
	private int color;
	private int skillLevel;
	private int skillId;
	private int exp;
	private int needCrystal;

	public int getSystemHeroSkillId() {
		return systemHeroSkillId;
	}

	public void setSystemHeroSkillId(int systemHeroSkillId) {
		this.systemHeroSkillId = systemHeroSkillId;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getNeedCrystal() {
		return needCrystal;
	}

	public void setNeedCrystal(int needCrystal) {
		this.needCrystal = needCrystal;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
