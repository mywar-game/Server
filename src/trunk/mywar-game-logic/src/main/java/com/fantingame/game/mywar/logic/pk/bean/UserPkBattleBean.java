package com.fantingame.game.mywar.logic.pk.bean;

import java.util.Date;

public class UserPkBattleBean {

	private String targetUserId;

	private Date attackTime;

	/**
	 * 进攻方装等
	 */
	private int attackPower;

	/**
	 * 防守方装等
	 */
	private int defencePower;

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getDefencePower() {
		return defencePower;
	}

	public void setDefencePower(int defencePower) {
		this.defencePower = defencePower;
	}

	public Date getAttackTime() {
		return attackTime;
	}

	public void setAttackTime(Date attackTime) {
		this.attackTime = attackTime;
	}

}
