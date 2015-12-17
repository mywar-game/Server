package com.system.bo;

/**
 * 登录服务器状态
 * 
 * @author yezp
 */
public class LoginServerStatus implements java.io.Serializable {

	private int status;
	private String notice;

	public LoginServerStatus() {
	}

	public LoginServerStatus(int status, String notice) {
		this.status = status;
		this.notice = notice;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

}
