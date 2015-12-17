package com.fantingame.game.mywar.logic.equip.model;

import java.util.Date;

/**
 * 用户装备
 * 
 * @author yezp
 */
public class UserEquip {

	private String userEquipId;

	private String userId;

	private String userHeroId;

	private int equipId;

	private int pos;

	private String equipMainAttr;

	private String equipSecondaryAttr;

	private String magicEquipAttr;

	private int holeNum;

	/**
	 * 仓库数量
	 */
	private int storehouseNum;

	private Date createdTime;

	private Date updatedTime;

	public String getUserEquipId() {
		return userEquipId;
	}

	public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserHeroId() {
		return userHeroId;
	}

	public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}

	public int getEquipId() {
		return equipId;
	}

	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public String getEquipMainAttr() {
		return equipMainAttr;
	}

	public void setEquipMainAttr(String equipMainAttr) {
		this.equipMainAttr = equipMainAttr;
	}

	public String getEquipSecondaryAttr() {
		return equipSecondaryAttr;
	}

	public void setEquipSecondaryAttr(String equipSecondaryAttr) {
		this.equipSecondaryAttr = equipSecondaryAttr;
	}

	public String getMagicEquipAttr() {
		return magicEquipAttr;
	}

	public void setMagicEquipAttr(String magicEquipAttr) {
		this.magicEquipAttr = magicEquipAttr;
	}

	public int getHoleNum() {
		return holeNum;
	}

	public void setHoleNum(int holeNum) {
		this.holeNum = holeNum;
	}

	public int getStorehouseNum() {
		return storehouseNum;
	}

	public void setStorehouseNum(int storehouseNum) {
		this.storehouseNum = storehouseNum;
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
