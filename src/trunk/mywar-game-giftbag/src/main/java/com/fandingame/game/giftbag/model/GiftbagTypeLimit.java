package com.fandingame.game.giftbag.model;

/**
 * 礼包领取次数限制
 * 
 * @author yezp
 */
@SuppressWarnings("serial")
public class GiftbagTypeLimit implements java.io.Serializable {

	private int id;
	private int giftBagType;
	private String serverIds;
	private int limitTimes;
	private int minLevel;
	private int minVipLevel;

	public int getMinVipLevel() {
		return minVipLevel;
	}

	public void setMinVipLevel(int minVipLevel) {
		this.minVipLevel = minVipLevel;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}

	public GiftbagTypeLimit() {
	}

	public GiftbagTypeLimit(int id, int giftBagType, int limitTimes, int minLevel, int minVipLevel) {
		this.id = id;
		this.giftBagType = giftBagType;
		this.limitTimes = limitTimes;
		this.minLevel = minLevel;
		this.minVipLevel = minVipLevel;
	}

	public GiftbagTypeLimit(int id, int giftBagType, String serverIds,
			int limitTimes) {
		this.id = id;
		this.giftBagType = giftBagType;
		this.serverIds = serverIds;
		this.limitTimes = limitTimes;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGiftBagType() {
		return this.giftBagType;
	}

	public void setGiftBagType(int giftBagType) {
		this.giftBagType = giftBagType;
	}

	public String getServerIds() {
		return this.serverIds;
	}

	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}

	public int getLimitTimes() {
		return this.limitTimes;
	}

	public void setLimitTimes(int limitTimes) {
		this.limitTimes = limitTimes;
	}

}
