package com.fantingame.game.gamecenter.dao;

import java.util.List;

import com.fantingame.game.gamecenter.model.UserInfo;

public interface UserInfoDao {

	/**
	 * 获取用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public UserInfo getUserInfo(String partnerUserId, String serverId);
	
	/**
	 * 保存信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public boolean save(UserInfo userInfo);
	
	/**
	 * 更新用户登陆信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public boolean updateUserInfo(UserInfo userInfo);
	
	/**
	 * 获取用户登陆服务器的信息
	 * 
	 * @param partnerUserId
	 * @return
	 */
	public List<UserInfo> getUserInfoList(String partnerUserId);
}
