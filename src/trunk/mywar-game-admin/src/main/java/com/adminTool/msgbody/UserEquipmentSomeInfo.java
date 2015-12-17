package com.adminTool.msgbody;

public class UserEquipmentSomeInfo {

	/**
	 * 用户装备ID(唯一ID)
	 */
	private String userEquipId;

	/**
	 * 系统装备ID
	 */
	private int equipId;

	/**
	 * 穿戴的武将ID
	 */
	private String userHeroId;

	/**
	 * 装备等级
	 */
	private int equipLevel;

	/**
	 * 装备名字
	 */
	private String equipName;

	/**
	 * 装备类型
	 */
	private int equipType;

	/**
	 * 生命值
	 */
	private int life;

	/**
	 * 物攻值
	 */
	private int physicsAttack;

	/**
	 * 物防
	 */
	private int physicsDefense;

	/**
	 * 出售价格
	 */
	private int price;

	/**
	 * 强化成功概率
	 */
	private int upgradetRate;

	public String getUserEquipId() {
		return userEquipId;
	}

	public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}

	public int getEquipId() {
		return equipId;
	}

	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}

	public String getUserHeroId() {
		return userHeroId;
	}

	public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}

	public int getEquipLevel() {
		return equipLevel;
	}

	public void setEquipLevel(int equipLevel) {
		this.equipLevel = equipLevel;
	}

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public int getEquipType() {
		return equipType;
	}

	public void setEquipType(int equipType) {
		this.equipType = equipType;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getPhysicsAttack() {
		return physicsAttack;
	}

	public void setPhysicsAttack(int physicsAttack) {
		this.physicsAttack = physicsAttack;
	}

	public int getPhysicsDefense() {
		return physicsDefense;
	}

	public void setPhysicsDefense(int physicsDefense) {
		this.physicsDefense = physicsDefense;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getUpgradetRate() {
		return upgradetRate;
	}

	public void setUpgradetRate(int upgradetRate) {
		this.upgradetRate = upgradetRate;
	}

}
