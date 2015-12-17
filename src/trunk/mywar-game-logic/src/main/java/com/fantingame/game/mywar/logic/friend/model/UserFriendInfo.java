package com.fantingame.game.mywar.logic.friend.model;

import java.util.Date;

/**
 * 用户好友信息
 * 
 * @author yezp
 */
public class UserFriendInfo {

	private int id;

	private String userId;

	private String userFriendId;

	private int status;

	private Date createdTime;

	private Date updatedTime;

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

	public String getUserFriendId() {
		return userFriendId;
	}

	public void setUserFriendId(String userFriendId) {
		this.userFriendId = userFriendId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

}
