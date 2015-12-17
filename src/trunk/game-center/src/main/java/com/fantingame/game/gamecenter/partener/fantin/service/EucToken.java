package com.fantingame.game.gamecenter.partener.fantin.service;

/**
 * 票据对象
 * 
 * @author damon
 * @since 2012.07.03
 * @version 1.0
 *
 */
public class EucToken implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8328276849391350854L;
	
	public String token;	
	/**COOKIE域*/
	private String domain;
	/**COOKIE路径*/
	private String path;
	/**失效时间*/
	private int age;
	
	public EucToken(String token){
		this.token = token;
	}
	
	public EucToken(){
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
