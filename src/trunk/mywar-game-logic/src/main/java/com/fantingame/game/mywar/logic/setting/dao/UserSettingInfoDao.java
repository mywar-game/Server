package com.fantingame.game.mywar.logic.setting.dao;

import com.fantingame.game.mywar.logic.setting.model.UserSettingInfo;

public interface UserSettingInfoDao {
	
	/**
	 * 获取用户设置信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserSettingInfo getUserSettingInfo(String userId);
	
	/**
	 * 添加用户设置信息
	 * 
	 * @param userSettingInfo
	 * @return
	 */
	public boolean addUserSettingInfo(UserSettingInfo userSettingInfo);
	
	/**
	 * 更新用户设置信息
	 * 
	 * @param userId
	 * @param num
	 * @return
	 */
	public boolean updateUserSettingInfo(String userId, int num);
	
	/**
	 * 更新用户的提示
	 * 
	 * @param userId
	 * @param tips
	 * @return
	 */
	public boolean updateUserDailyTips(String userId, String tips);
}
