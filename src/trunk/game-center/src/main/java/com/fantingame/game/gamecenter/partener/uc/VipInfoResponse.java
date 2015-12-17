package com.fantingame.game.gamecenter.partener.uc;

public class VipInfoResponse {

	long id;
	VipInfoResponseState state;
	VipInfoResponseData data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public VipInfoResponseState getState() {
		return state;
	}

	public void setState(VipInfoResponseState state) {
		this.state = state;
	}

	public VipInfoResponseData getData() {
		return data;
	}

	public void setData(VipInfoResponseData data) {
		this.data = data;
	}

	public class VipInfoResponseState {
		int code;
		String msg;

		public int getCode() {
			return this.code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMsg() {
			return this.msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public class VipInfoResponseData {
		int status;

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

	}

}
