package com.fantingame.game.mywar.logic.legion.model;

import java.util.Date;

public class LegionInfo {

	private String legionId;

	private String legionName;

	private String notice;

	private String declaration;

	private int level;

	/**
	 * 战斗力
	 */
	private int power;

	private int exp;

	private String legionLeader;

	private Date createdTime;

	private Date updatedTime;

	public String getLegionId() {
		return legionId;
	}

	public void setLegionId(String legionId) {
		this.legionId = legionId;
	}

	public String getLegionName() {
		return legionName;
	}

	public void setLegionName(String legionName) {
		this.legionName = legionName;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getLegionLeader() {
		return legionLeader;
	}

	public void setLegionLeader(String legionLeader) {
		this.legionLeader = legionLeader;
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

}
