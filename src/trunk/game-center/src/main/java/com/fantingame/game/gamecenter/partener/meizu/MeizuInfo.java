package com.fantingame.game.gamecenter.partener.meizu;

public class MeizuInfo {

	public MeizuInfo() {}
	private int state;
	public Data data;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	} 

	public class Data {
		public Data() {}
		private String userid;
		private String username;
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
	}
}

