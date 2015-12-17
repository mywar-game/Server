package com.fantingame.game.mywar.logic.chat.model;

import java.util.Date;

/**
 * 用户聊天记录
 * 
 * @author yezp
 */
public class UserChatRecord {

	private int id;

	private String userId;

	private String targetUserId;

	/**
	 * 聊天类型 1 世界 2 阵营 3 军团 4 私聊
	 */
	private int type;

	private String content;

	private Date createdTime;

	private Date updatedTime;

	private String legionId;

	private int camp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getLegionId() {
		return legionId;
	}

	public void setLegionId(String legionId) {
		this.legionId = legionId;
	}

	public int getCamp() {
		return camp;
	}

	public void setCamp(int camp) {
		this.camp = camp;
	}

}
