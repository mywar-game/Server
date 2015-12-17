package com.fantingame.game.mywar.logic.forces.model;

/**
 * 关卡表
 * 
 * @author Administrator
 *
 */
public class SystemForces {
	private int forcesId;
	private int preForcesId;
	private int bigForcesId;
	private int forcesCategory;
	private String imgId;
	private String forcesName;
	private String forcesDesc;
	private int needResourceNum;
	private int attackPeriod;
	private String resId;
	private int collectionTime;
	private int mapId;
	private int x;
	private int y;

	public int getForcesId() {
		return forcesId;
	}

	public void setForcesId(int forcesId) {
		this.forcesId = forcesId;
	}

	public int getPreForcesId() {
		return preForcesId;
	}

	public void setPreForcesId(int preForcesId) {
		this.preForcesId = preForcesId;
	}

	public int getBigForcesId() {
		return bigForcesId;
	}

	public void setBigForcesId(int bigForcesId) {
		this.bigForcesId = bigForcesId;
	}

	public int getForcesCategory() {
		return forcesCategory;
	}

	public void setForcesCategory(int forcesCategory) {
		this.forcesCategory = forcesCategory;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getForcesName() {
		return forcesName;
	}

	public void setForcesName(String forcesName) {
		this.forcesName = forcesName;
	}

	public String getForcesDesc() {
		return forcesDesc;
	}

	public void setForcesDesc(String forcesDesc) {
		this.forcesDesc = forcesDesc;
	}

	public int getAttackPeriod() {
		return attackPeriod;
	}

	public void setAttackPeriod(int attackPeriod) {
		this.attackPeriod = attackPeriod;
	}

	public int getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(int collectionTime) {
		this.collectionTime = collectionTime;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public int getNeedResourceNum() {
		return needResourceNum;
	}

	public void setNeedResourceNum(int needResourceNum) {
		this.needResourceNum = needResourceNum;
	}
}
