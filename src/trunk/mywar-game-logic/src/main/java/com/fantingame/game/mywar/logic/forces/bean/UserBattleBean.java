package com.fantingame.game.mywar.logic.forces.bean;

import java.util.Date;
import java.util.List;

import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;

/**
 * 用户关卡战斗临时对象
 * 
 * @author Administrator
 *
 */
public class UserBattleBean {
	private int forcesId;
	private int forcesType;
	private int mapId;
	private Date attackTime;
	// 用于采集时，是否随机到野怪
	private boolean isFightAgain;
	// 战斗的掉落
	private List<GoodsBeanBO> drop;

	/**
	 * 0 还未触发随机事件 1 已经触发过了
	 */
	private int hasRandom;

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

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public Date getAttackTime() {
		return attackTime;
	}

	public void setAttackTime(Date attackTime) {
		this.attackTime = attackTime;
	}

	public int getHasRandom() {
		return hasRandom;
	}

	public void setHasRandom(int hasRandom) {
		this.hasRandom = hasRandom;
	}

	public List<GoodsBeanBO> getDrop() {
		return drop;
	}

	public void setDrop(List<GoodsBeanBO> drop) {
		this.drop = drop;
	}

	public boolean isFightAgain() {
		return isFightAgain;
	}

	public void setFightAgain(boolean isFightAgain) {
		this.isFightAgain = isFightAgain;
	}
}
