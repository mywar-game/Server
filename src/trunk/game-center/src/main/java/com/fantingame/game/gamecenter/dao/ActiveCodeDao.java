package com.fantingame.game.gamecenter.dao;

import java.util.List;

import com.fantingame.game.gamecenter.model.ActiveCode;
import com.fantingame.game.gamecenter.model.GameServerStatus;
import com.fantingame.game.gamecenter.model.LoginServerStatus;

public interface ActiveCodeDao {
	
	/**
	 * 添加激活码
	 * 
	 * @param activeList
	 */
	public void addActive(List<ActiveCode> activeList);	
	
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
	
	public boolean addActive(String uuid, String code, String partnerId);
	
	public List<ActiveCode> getAllActiveList();
}
