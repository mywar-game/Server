package com.fantingame.game.gamecenter.partener.fantin.service;


/**
 * 鉴权返回结果
 * 
 * @author jay
 * @since 2013.01.27
 * @version 1.0
 *
 */
public class AuthBean implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4782288831244671067L;

	private EucToken token;
	/*U信息*/
	private EucUCookie u;
	/*是否登录的注册方式*/
	private boolean isRegist;
	/*返回ESID*/
	private String esid;
	/*返回的用户实体*/
	private JUser user;
	
	public EucUCookie getU() {
		return u;
	}

	public void setU(EucUCookie u) {
		this.u = u;
	}

	public boolean isRegist() {
		return isRegist;
	}

	public void setRegist(boolean isRegist) {
		this.isRegist = isRegist;
	}

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}

	/**
	 * 构造函数
	 * 
	 * @param jUser
	 * @param token
	 * @param esid
	 * @param isRegist
	 *     注册登录标识
	 */
	public AuthBean(EucToken token,JUser user,String esid,boolean isRegist){
		this.token = token;
		this.user = user;
		this.esid = esid;
		this.isRegist = isRegist;
	}
	
	
	/**
	 * 构造函数
	 * 
	 * @param jUser
	 * @param token
	 * @param esid
	 * @param isRegist
	 *     注册登录标识
	 */
	public AuthBean(EucToken token,JUser user,EucUCookie u,String esid,boolean isRegist){
		this.token = token;
		this.user = user;
		this.u = u;
		this.esid = esid;
		this.isRegist = isRegist;
	}

	/**
	 * 通行证
	 * 
	 * @param token
	 */
	public EucToken getToken() {
		return token;
	}
	
	public void setToken(EucToken token) {
		this.token = token;
	}

	/**
	 * 用户信息
	 */
	public JUser getUser() {
		return user;
	}

	public void setUser(JUser user) {
		this.user = user;
	}
}
