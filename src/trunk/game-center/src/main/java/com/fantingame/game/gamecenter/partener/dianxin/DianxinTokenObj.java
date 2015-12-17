package com.fantingame.game.gamecenter.partener.dianxin;

/**
 * 授权码兑换令牌
 * @author Administrator
 *
 */
public class DianxinTokenObj {

	private String access_token;// 访问令牌
	private String token_type;// 令牌类型，目前只有“Bearer”
	private String refresh_token;// 刷新令牌
	private String expires_in;// 访问令牌过期时间
	private String re_expires_in;// 刷新令牌过期时间
	private String scope;// 授权范围
	private String user_id;// 爱游戏用户Id
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getRe_expires_in() {
		return re_expires_in;
	}
	public void setRe_expires_in(String re_expires_in) {
		this.re_expires_in = re_expires_in;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
