package com.fantingame.game.mywar.logic.mall.dao;

import java.util.Date;
import java.util.List;

import com.fantingame.game.mywar.logic.mall.model.UserBuyBackInfo;

public interface UserBuyBackInfoDao {

	/**
	 * 获取用户回购信息
	 * 
	 * @param buyBackId
	 * @return
	 */
	public List<UserBuyBackInfo> getUserBuyBackInfoList(String userId);

	/**
	 * 添加用户出售信息
	 * 
	 * @param userBuyBackInfo
	 * @return
	 */
	public boolean addUserBuyBackInfo(UserBuyBackInfo userBuyBackInfo);
	
	/**
	 * 获取用户回购信息
	 * 
	 * @param userId
	 * @param buyBackId
	 * @return
	 */
	public UserBuyBackInfo getUserBuyBackInfo(String userId, String buyBackId);
	
	/**
	 * 删除用户回购信息
	 * 
	 * @param userId
	 * @param buyBackId
	 * @return
	 */
	public boolean deleteUserBuyBackInfo(String userId, String buyBackId);
	
	/**
	 * 删除两小时前的回购信息
	 * 
	 * @param userId
	 * @param endTime
	 * @return
	 */
	public boolean deleteUserBuyBackInfo(String userId, Date endTime);
}
