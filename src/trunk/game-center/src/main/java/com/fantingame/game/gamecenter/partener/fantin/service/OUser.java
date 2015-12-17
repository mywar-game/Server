package com.fantingame.game.gamecenter.partener.fantin.service;

public class OUser {
	// 绑定编号
	private long id;
	// 宜搜ID
	private long ea_id;
	// 状态
	private String status;
	// 网络类别
	private Integer net_id;
	// 昵称
	private String nick_name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEa_id() {
		return ea_id;
	}

	public void setEa_id(long ea_id) {
		this.ea_id = ea_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getNet_id() {
		return net_id;
	}

	public void setNet_id(Integer net_id) {
		this.net_id = net_id;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
}
