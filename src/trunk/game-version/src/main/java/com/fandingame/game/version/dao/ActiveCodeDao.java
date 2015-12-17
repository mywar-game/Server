package com.fandingame.game.version.dao;

import com.fandingame.game.version.model.GameServerStatus;
import com.fandingame.game.version.model.LoginServerStatus;


public interface ActiveCodeDao {
	/**
	 * 用户是否已经激活
	 * 
	 * @param uuid
	 * @param partnerId 
	 * @return
	 */
	public boolean isActive(String uuid, String partnerId);
	/**
	 * 激活用户
	 * 
	 * @param uuid
	 * @param code
	 * @return
	 */
	public boolean active(String uuid, String code, String partnerId);
	/**
	 * imei是否在白名单中
	 * @param imei
	 * @return
	 */
	public boolean isInBlackList(String imei,String mac,String ip,int type);
	/**
	 * 获取登录服务器状态
	 * @return
	 */
	public LoginServerStatus getLoginServerBean();
	/**
	 * 游戏服务器状态
	 * @param serverId
	 * @return
	 */
	public GameServerStatus getGameServerBean(String serverId);
}
