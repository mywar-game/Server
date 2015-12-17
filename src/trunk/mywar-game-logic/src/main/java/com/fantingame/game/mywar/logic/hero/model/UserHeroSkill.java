package com.fantingame.game.mywar.logic.hero.model;

import java.util.Date;

public class UserHeroSkill {
	private String userHeroSkillId;
	private String userId;
	private int systemHeroSkillId;
	private int skillLevel;
	private int skillExp;
	private int pos;
	private Date createdTime;
	private Date updatedTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUserHeroSkillId() {
		return userHeroSkillId;
	}

	public void setUserHeroSkillId(String userHeroSkillId) {
		this.userHeroSkillId = userHeroSkillId;
	}

	public int getSkillExp() {
		return skillExp;
	}

	public void setSkillExp(int skillExp) {
		this.skillExp = skillExp;
	}
}
