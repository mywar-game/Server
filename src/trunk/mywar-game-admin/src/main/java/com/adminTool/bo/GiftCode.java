package com.adminTool.bo;

import java.sql.Timestamp;

/**
 * 礼包码
 * 
 * @author yezp
 */
public class GiftCode implements java.io.Serializable {

	private String code;
	private int giftBagId;
	private Timestamp createdTime;
	private Timestamp effectiveTime;
	private Timestamp loseTime;
	private String giftbagName;

	public GiftCode() {
	}

	public GiftCode(int giftBagId, Timestamp createdTime,
			Timestamp effectiveTime, Timestamp loseTime) {
		this.giftBagId = giftBagId;
		this.createdTime = createdTime;
		this.effectiveTime = effectiveTime;
		this.loseTime = loseTime;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getGiftBagId() {
		return giftBagId;
	}

	public void setGiftBagId(int giftBagId) {
		this.giftBagId = giftBagId;
	}

	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Timestamp effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Timestamp getLoseTime() {
		return loseTime;
	}

	public void setLoseTime(Timestamp loseTime) {
		this.loseTime = loseTime;
	}

	public String getGiftbagName() {
		return giftbagName;
	}

	public void setGiftbagName(String giftbagName) {
		this.giftbagName = giftbagName;
	}

}
