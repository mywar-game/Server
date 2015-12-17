package com.adminTool.bo;

import java.util.Date;

/**
 * 礼包领取日志
 * 
 * @author yezp
 */
public class GiftCodeLog implements java.io.Serializable {

	private String code;
	private String giftBagId;
	private String userId;
	private String serverId;
	private String partenerId;
	private String qn;
	private Date createdTime;

	public GiftCodeLog() {
	}

	public GiftCodeLog(String code, String userId, String serverId,
			String partenerId, Date createdTime) {
		this.code = code;
		this.userId = userId;
		this.serverId = serverId;
		this.partenerId = partenerId;
		this.createdTime = createdTime;
	}

	public GiftCodeLog(String code, String userId, String serverId,
			String partenerId, String qn, Date createdTime) {
		this.code = code;
		this.userId = userId;
		this.serverId = serverId;
		this.partenerId = partenerId;
		this.qn = qn;
		this.createdTime = createdTime;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGiftBagId() {
		return giftBagId;
	}

	public void setGiftBagId(String giftBagId) {
		this.giftBagId = giftBagId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getPartenerId() {
		return this.partenerId;
	}

	public void setPartenerId(String partenerId) {
		this.partenerId = partenerId;
	}

	public String getQn() {
		return this.qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
