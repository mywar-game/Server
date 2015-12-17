package com.fantingame.game.mywar.logic.legion.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.legion.model.UserLegionInfo;

public interface UserLegionInfoDao {

	/**
	 * 获取用户军团信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserLegionInfo getUserLegionInfo(String userId);
	
	/**
	 * 添加用户军团信息
	 * 
	 * @param userLegionInfo
	 * @return
	 */
	public boolean addUserLegionInfo(UserLegionInfo userLegionInfo);
	
	/**
	 * 获取军团当前人数
	 * 
	 * @param legionId
	 * @return
	 */
	public int getLegionCurrentNum(String legionId);
	
	/**
	 * 更新用户军团信息
	 * 
	 * @param status
	 * @param legionId
	 * @return
	 */
	public boolean updateUserlegionInfo(String userId, String legionId, int status, int identity, int contribution);

	/**
	 * 查看军团成员信息
	 * 
	 * @param legionId
	 * @return
	 */
	public List<UserLegionInfo> getUserLegionInfoList(String legionId);
	
	/**
	 * 获取军团当前身份 数量
	 * 
	 * @param legionId
	 * @param identity
	 * @return
	 */
	public int getLegionCurrentIdentityNum(String legionId, int identity);

	/**
	 * 更新用户身份信息
	 * 
	 * @param userId
	 * @param legionId
	 * @param identity
	 * @return
	 */
	public boolean updateUserlegionInfo(String userId, int identity);
	
	/**
	 * 更新用户状态
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public boolean updateUserlegionInfoStatus(String userId, int status);
	
	/**
	 * 更新该军团所有用户的状态
	 * 
	 * @param legionId
	 * @param status
	 * @param userLegionInfoList
	 * @return
	 */
	public boolean updateUserlegionInfoStatus(String legionId, int status, List<UserLegionInfo> userLegionInfoList); 

	/**
	 * 更新用户贡献值
	 * 
	 * @param userId
	 * @param contribution
	 * @return
	 */
	public boolean updateUserContribution(String userId, int contribution);
}
