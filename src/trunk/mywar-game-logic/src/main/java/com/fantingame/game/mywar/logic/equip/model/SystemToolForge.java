package com.fantingame.game.mywar.logic.equip.model;

/**
 * 系统道具、装备锻造/回收
 * 
 * @author yezp
 */
public class SystemToolForge {

	/**
	 * 类型 1 锻造 2 回收
	 */
	private int type;

	private int toolType;

	private int toolId;

	private int num;

	private int needLevel;

	private String material;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getToolType() {
		return toolType;
	}

	public void setToolType(int toolType) {
		this.toolType = toolType;
	}

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getNeedLevel() {
		return needLevel;
	}

	public void setNeedLevel(int needLevel) {
		this.needLevel = needLevel;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

}
