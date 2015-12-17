package com.fantingame.game.mywar.logic.boss.bean;

import java.util.Date;

import com.fantingame.game.mywar.logic.scene.bean.Position;

public class UserBossBean {

	private String userId;

	private String userName;

	// 位置
	private Position position;

	// 英雄模型id
	private Integer heroId;

	/**
	 * 房间
	 */
	private WorldBossRoom room;

	/**
	 * 0 挂掉了 1 活着   2  已复活
	 */
	private int status;

	/**
	 * 死亡时间
	 */
	private Date dieTime;

	/**
	 * 是否为房主
	 */
	private boolean isRoomOwner;

	// 状态 true 已经进入 并可以收发消息了 false还没有进入完毕
	public volatile boolean isEntered;

	/**
	 * 对boss造成的伤害值
	 */
	private int hurt;

	/**
	 * 治疗量
	 */
	private int treatment;

	/**
	 * 承受攻击量
	 */
	private int beHit;

	public UserBossBean(String userId, String userName, int heroId, int status) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.heroId = heroId;
		this.status = status;
		this.isRoomOwner = false;
		position = new Position();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public WorldBossRoom getRoom() {
		return room;
	}

	public void setRoom(WorldBossRoom room) {
		this.room = room;
	}

	public Integer getHeroId() {
		return heroId;
	}

	public void setHeroId(Integer heroId) {
		this.heroId = heroId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Date getDieTime() {
		return dieTime;
	}

	public void setDieTime(Date dieTime) {
		this.dieTime = dieTime;
	}

	public boolean isRoomOwner() {
		return isRoomOwner;
	}

	public void setRoomOwner(boolean isRoomOwner) {
		this.isRoomOwner = isRoomOwner;
	}

	public boolean isEntered() {
		return isEntered;
	}

	public void setEntered(boolean isEntered) {
		this.isEntered = isEntered;
	}

	public int getHurt() {
		return hurt;
	}

	public void setHurt(int hurt) {
		this.hurt = hurt;
	}

	public int getTreatment() {
		return treatment;
	}

	public void setTreatment(int treatment) {
		this.treatment = treatment;
	}

	public int getBeHit() {
		return beHit;
	}

	public void setBeHit(int beHit) {
		this.beHit = beHit;
	}

	public int getRoomOwner() {
		if (isRoomOwner)
			return 1;

		return 0;
	}

}
