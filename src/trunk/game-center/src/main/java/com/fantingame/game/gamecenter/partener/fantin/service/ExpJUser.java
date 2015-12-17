package com.fantingame.game.gamecenter.partener.fantin.service;

/**
 * 扩展JUser对象, 该对象提供给一键注册接口使用,以返回用户密码
 * 
 * @author jay
 * @since 2012.11.26
 * @version 1.0
 *
 */
public class ExpJUser extends JUser{
	
	/**
	 * 用户密码
	 */
	private String passwd;

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	

}
