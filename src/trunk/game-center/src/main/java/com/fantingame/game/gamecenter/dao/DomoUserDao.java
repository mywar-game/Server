package com.fantingame.game.gamecenter.dao;


import com.fantingame.game.gamecenter.model.DomoUser;



public interface DomoUserDao {
	/**
	 * 根据serverId获取公告
	 * @param serverId
	 * @return
	 */
	public boolean add(DomoUser domoUser);
	
	/**
	 * 根据serverId获取公告
	 * @param serverId
	 * @return
	 */
	public DomoUser get(String mac,String ifa);
	
	public void update(String mac,String ifa,int active,String appVersion,String ip,String userId);


}
