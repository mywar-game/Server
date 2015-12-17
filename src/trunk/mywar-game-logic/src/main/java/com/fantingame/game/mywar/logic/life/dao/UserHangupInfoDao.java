package com.fantingame.game.mywar.logic.life.dao;

import java.util.Date;
import java.util.List;

import com.fantingame.game.mywar.logic.life.model.UserHangupInfo;

public interface UserHangupInfoDao {

	/**
	 * 获取用户挂机信息列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserHangupInfo> getUserHangupInfoList(String userId);
	
	/**
	 * 获取用户挂机信息
	 * 
	 * @param userId
	 * @param category
	 * @return
	 */
	public UserHangupInfo getUserHangupInfo(String userId, int category);
	
	/**
	 * 添加用户挂机信息
	 * 
	 * @param userHangupInfo
	 * @return
	 */
	public boolean addUserHangupInfo(UserHangupInfo userHangupInfo);
	
	/**
	 * 删除用户挂机信息
	 * 
	 * @param userId
	 * @param category
	 * @return
	 */
	public boolean deleteUserHangupInfo(String userId, int category);
	
	/**
	 * 更新用户挂机信息
	 * 
	 * @param userId
	 * @param category
	 * @param now
	 * @return
	 */
	public boolean updateUserHangupInfo(String userId, int category, Date updatedTime, String rewards);
}
