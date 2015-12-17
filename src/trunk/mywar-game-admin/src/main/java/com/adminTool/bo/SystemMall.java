package com.adminTool.bo;

public class SystemMall {

	public SystemMall(int mallId, String tag, String name, String description,
			int toolType, int toolId, int toolNum, int moneyType, int amount,
			int imgId, int dailyMaxNum, int maxNum, int needVipLevel,
			int seeVipLevel) {
		super();
		this.mallId = mallId;
		this.tag = tag;
		this.name = name;
		this.description = description;
		this.toolType = toolType;
		this.toolId = toolId;
		this.toolNum = toolNum;
		this.moneyType = moneyType;
		this.amount = amount;
		this.imgId = imgId;
		this.dailyMaxNum = dailyMaxNum;
		this.maxNum = maxNum;
		this.needVipLevel = needVipLevel;
		this.seeVipLevel = seeVipLevel;
	}

	private int mallId;
	private String tag;
	private String name;
	private String description;
	private int toolType;
	private int toolId;
	private int toolNum;
	private int moneyType;
	private int amount;
	private int imgId;
	private int dailyMaxNum;
	private int maxNum;
	private int needVipLevel;
	private int seeVipLevel;
	
	public int getMallId() {
		return mallId;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public int getToolNum() {
		return toolNum;
	}

	public void setToolNum(int toolNum) {
		this.toolNum = toolNum;
	}

	public int getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(int moneyType) {
		this.moneyType = moneyType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public int getDailyMaxNum() {
		return dailyMaxNum;
	}

	public void setDailyMaxNum(int dailyMaxNum) {
		this.dailyMaxNum = dailyMaxNum;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public int getNeedVipLevel() {
		return needVipLevel;
	}

	public void setNeedVipLevel(int needVipLevel) {
		this.needVipLevel = needVipLevel;
	}

	public int getSeeVipLevel() {
		return seeVipLevel;
	}

	public void setSeeVipLevel(int seeVipLevel) {
		this.seeVipLevel = seeVipLevel;
	}

	public SystemMall() {}
	
}
