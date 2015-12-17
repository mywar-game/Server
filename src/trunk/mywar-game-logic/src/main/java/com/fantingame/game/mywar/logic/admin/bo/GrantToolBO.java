package com.fantingame.game.mywar.logic.admin.bo;

import java.util.List;

import com.fantingame.game.mywar.logic.user.model.User;

public class GrantToolBO {

	private List<User> list;
	private String failPlayId;

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public String getFailPlayId() {
		return failPlayId;
	}

	public void setFailPlayId(String failPlayId) {
		this.failPlayId = failPlayId;
	}

}
