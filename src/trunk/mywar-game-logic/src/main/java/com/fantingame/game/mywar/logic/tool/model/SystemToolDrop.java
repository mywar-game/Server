package com.fantingame.game.mywar.logic.tool.model;

public class SystemToolDrop {
    private int toolId;
	private int dropToolType;

	private int dropToolId;

	private int dropToolNum;

	private int upperNum;

	private int lowerNum;
	/**
	 * 掉落需要的最低等级
	 */
	private int minLevel;
	/**
	 * 掉落需要的最高等级
	 */
	private int maxLevel;
	
	public int getDropToolType() {
		return dropToolType;
	}

	public void setDropToolType(int dropToolType) {
		this.dropToolType = dropToolType;
	}

	public int getDropToolId() {
		return dropToolId;
	}

	public void setDropToolId(int dropToolId) {
		this.dropToolId = dropToolId;
	}

	public int getDropToolNum() {
		return dropToolNum;
	}

	public void setDropToolNum(int dropToolNum) {
		this.dropToolNum = dropToolNum;
	}

	public int getUpperNum() {
		return upperNum;
	}

	public void setUpperNum(int upperNum) {
		this.upperNum = upperNum;
	}

	public int getLowerNum() {
		return lowerNum;
	}

	public void setLowerNum(int lowerNum) {
		this.lowerNum = lowerNum;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	
	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}
}
