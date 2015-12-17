package com.fantingame.game.mywar.logic.equip.model;

/**
 * 系统装备
 * 
 * @author yezp
 */
public class SystemEquip {

	private int equipId;

	private String name;

	/**
	 * 品质：1 优秀（绿）、2 精良（蓝）、3 史诗（紫）、4 传说（橙）
	 */
	private int quality;

	/**
	 * 装备等级
	 */
	private int level;

	/**
	 * 装备类型（1 攻击 2 防具）
	 */
	private int equipType;

	private int price;

	/**
	 * 是否可出售
	 */
	private int isSell;

	/**
	 * 出售的货币类型
	 */
	private int moneyType;

	private String imgId;

	private String needCareer;

	private int needLevel;

	/**
	 * 位置（1 主手 2 手套 3 胸甲 4 头盔 5 肩甲 6 裤子 7 靴子 8 副手）
	 */
	private String pos;

	private int equipSkillId;

	public int getEquipId() {
		return equipId;
	}

	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}

	public int getEquipType() {
		return equipType;
	}

	public void setEquipType(int equipType) {
		this.equipType = equipType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getIsSell() {
		return isSell;
	}

	public void setIsSell(int isSell) {
		this.isSell = isSell;
	}

	public int getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(int moneyType) {
		this.moneyType = moneyType;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getNeedCareer() {
		return needCareer;
	}

	public void setNeedCareer(String needCareer) {
		this.needCareer = needCareer;
	}

	public int getNeedLevel() {
		return needLevel;
	}

	public void setNeedLevel(int needLevel) {
		this.needLevel = needLevel;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public int getEquipSkillId() {
		return equipSkillId;
	}

	public void setEquipSkillId(int equipSkillId) {
		this.equipSkillId = equipSkillId;
	}

}
