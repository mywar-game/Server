package com.fantingame.game.mywar.logic.user.dao;

import com.fantingame.game.mywar.logic.user.model.UserMapInfo;

public interface UserMapInfoDao {

	/**
	 * 获取用户地图信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserMapInfo getUserMapInfo(String userId);
	
	/**
	 * 添加用户地图开启信息
	 * 
	 * @param userMapInfo
	 * @return
	 */
	public boolean addUserMapInfo(UserMapInfo userMapInfo);
	
	/**
	 * 更新用户开启地图信息
	 * 
	 * @param userId
	 * @param maps
	 * @return
	 */
	public boolean updateUserMapInfo(String userId, String maps);
}
