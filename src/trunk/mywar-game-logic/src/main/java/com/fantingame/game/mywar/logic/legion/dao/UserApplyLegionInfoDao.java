package com.fantingame.game.mywar.logic.legion.dao;

import java.util.Date;
import java.util.List;

import com.fantingame.game.mywar.logic.legion.model.UserApplyLegionInfo;

public interface UserApplyLegionInfoDao {

	/**
	 * 获取用户申请军团列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserApplyLegionInfo> getUserApplyLegionInfoList(String userId);
	
	/**
	 * 添加申请军团信息
	 * 
	 * @param userApplyLegionInfo
	 * @return
	 */
	public boolean addUserApplyLegionInfo(UserApplyLegionInfo userApplyLegionInfo);
	
	/**
	 * 获取用户申请信息
	 * 
	 * @param userId
	 * @param legionId
	 * @return
	 */
	public UserApplyLegionInfo getUserApplyLegionInfo(String userId, String legionId);
	
	/**
	 * 删除申请
	 * 
	 * @param userId
	 * @param legionId
	 * @return
	 */
	public boolean deleteUserApplyLegionInfo(String userId, String legionId);
	
	/**
	 * 删除过期的申请信息
	 * 
	 * @param userId
	 * @param endTime
	 * @return
	 */
	public boolean deleteUserApplyLegionInfo(String userId, Date endTime);
	
	/**
	 * 获取同一个军团的申请列表(未过期的)
	 * 
	 * @param legionId
	 * @param endTime
	 * @return
	 */
	public List<UserApplyLegionInfo> getUserApplyLegionInfoList(String legionId, Date endTime);

	/**
	 * 删除用户的申请信息
	 * 
	 * @param legionId
	 * @return
	 */
	public boolean deleteUserApplyLegionInfo(String legionId, List<UserApplyLegionInfo> applyList);
	
	/**
	 * 根据军团id获取所有的申请列表
	 * 
	 * @param legionId
	 * @return
	 */
	public List<UserApplyLegionInfo> getListByLegionId(String legionId);
	
	/**
	 * 删除用户的所有申请
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteUserApplyLegionInfo(String userId);
	
	/**
	 * 获取用户过期的申请
	 * 
	 * @param userId
	 * @param endTime
	 * @return
	 */
	public List<UserApplyLegionInfo> getUserApplyLegionInfoListByUserId(String userId, Date endTime);
}
