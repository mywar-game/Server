package com.fantingame.game.mywar.logic.equip.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.mywar.logic.gemstone.model.SystemGemstoneForge;
import com.google.common.collect.Maps;

public class ToolOperationBean {

	private String userId;

	private Date createdTime;
	/**
	 * 花费的金币
	 */
	private int costGold;

	/**
	 * 消耗的道具
	 */
	private List<GoodsBeanBO> costList;

	/**
	 * 获得的道具
	 */
	private List<GoodsBeanBO> gainList;

	/**
	 * 宝石分解专用
	 */
	private Map<String, SystemGemstoneForge> gemstoneForgeMap = Maps
			.newConcurrentMap();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getCostGold() {
		return costGold;
	}

	public void setCostGold(int costGold) {
		this.costGold = costGold;
	}

	public List<GoodsBeanBO> getCostList() {
		return costList;
	}

	public void setCostList(List<GoodsBeanBO> costList) {
		this.costList = costList;
	}

	public List<GoodsBeanBO> getGainList() {
		return gainList;
	}

	public void setGainList(List<GoodsBeanBO> gainList) {
		this.gainList = gainList;
	}

	public Map<String, SystemGemstoneForge> getGemstoneForgeMap() {
		return gemstoneForgeMap;
	}

	public void setGemstoneForgeMap(
			Map<String, SystemGemstoneForge> gemstoneForgeMap) {
		this.gemstoneForgeMap = gemstoneForgeMap;
	}

}
