package com.fantingame.game.mywar.logic.tool.model;

import java.io.Serializable;

/**
 * 系统道具表
 * 
 * @author mengchao
 * 
 */
public class SystemTool implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 道具ID
	 */
	private int toolId;

	/**
	 * 类型
	 */
	private int type;
	/**
	 * 道具品质
	 */
	private int color;

	private int level;

	/**
	 * 道具名称
	 */
	private String name;
	/**
	 * 背包校验类型 0占背包格子 1不占背包格子
	 */
	private int checkType;
	/**
	 * 道具描述
	 */
	private String description;
	/**
	 * 道具价格
	 */
	private int price;

	/**
	 * 是否可出售
	 */
	private int isSell;

	/**
	 * 出售的货币类型
	 */
	private int moneyType;

	/**
	 * 道具图标
	 */
	private String imgId;

	/** 叠加上限 **/
	private int overlapMax;

	/** 打开宝箱需要达到等级 **/
	private int openBoxNeedLevel;
	/** 数量 加的行囊数量 ******/
	private int num;

	public int getOpenBoxNeedLevel() {
		return openBoxNeedLevel;
	}

	public void setOpenBoxNeedLevel(int openBoxNeedLevel) {
		this.openBoxNeedLevel = openBoxNeedLevel;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public int getOverlapMax() {
		return overlapMax;
	}

	public void setOverlapMax(int overlapMax) {
		this.overlapMax = overlapMax;
	}

	public int getCheckType() {
		return checkType;
	}

	public void setCheckType(int checkType) {
		this.checkType = checkType;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
}
