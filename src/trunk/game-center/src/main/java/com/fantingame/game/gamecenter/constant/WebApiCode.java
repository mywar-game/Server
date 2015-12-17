package com.fantingame.game.gamecenter.constant;


public class WebApiCode {
	/**
	 * 成功
	 */
	public static final int SUCCESS = 1000;
	/**
	 * 未知错误
	 */
	public static final int UNKNOWN_ERROR = 2000;
	/**
	 * 参数错误
	 */
	public static final int PARAM_ERROR = 2001;
	/**
	 * 校验签名错误
	 */
	public static final int SIGN_CHECK_ERROR = 2002;
	/**
	 * 登录验证失败
	 */
	public static final int LOGIN_VALID_FAILD = 2003;
	/**
	 * 游戏服务器登录失败
	 */
	public static final int LOGIN_GAME_FAILD = 2004;

	/**
	 * 版本失效，无法升级以及登陆
	 */
	public static final int VERSION_EXPIRE = 2005;

	/**
	 * 登录服务器关闭
	 */
	public static final int LOGIN_SERVER_CLOSE = 2006;
	/**
	 * 游戏服务器关闭
	 */
	public static final int GAME_SERVER_CLOSE = 2007;

	/**
	 * 激活码无效
	 */
	public static final int ACTIVE_FAILD = 2005;


}
