package com.adminTool.bo;

import java.sql.Timestamp;

/**
 * 充值配置
 * 
 * @author Administrator
 *
 */
public class SystemGoldSet {

	private Integer systemGoldSetId;
	private String partnerId;
	private Integer money;
	private Integer gold;
	private Integer type;
	private Timestamp startTime;
	private Timestamp endTime;
	private String title;
	private String description;

	public SystemGoldSet() {
		super();
	}

	public SystemGoldSet(Integer systemGoldSetId, Integer money, Integer gold,
			Integer type, Timestamp startTime, Timestamp endTime, String title,
			String description) {
		super();
		this.systemGoldSetId = systemGoldSetId;
		this.money = money;
		this.gold = gold;
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.description = description;
	}

	public Integer getSystemGoldSetId() {
		return systemGoldSetId;
	}

	public void setSystemGoldSetId(Integer systemGoldSetId) {
		this.systemGoldSetId = systemGoldSetId;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
