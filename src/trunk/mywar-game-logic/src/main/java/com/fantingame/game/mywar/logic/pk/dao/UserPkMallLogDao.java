package com.fantingame.game.mywar.logic.pk.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.pk.model.UserPkMallLog;

public interface UserPkMallLogDao {

	/**
	 * 获取用户兑换奖励日志列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserPkMallLog> getUserPkMallLogList(String userId);
	
	/**
	 * 批量添加兑换日志
	 * 
	 * @param logList
	 * @return
	 */
	public boolean addList(List<UserPkMallLog> logList);
	
	/**
	 * 更新日志列表
	 * 
	 * @param userId
	 * @return
	 */
	public boolean updateAllList(String userId);
	
	/**
	 * 获取用户兑换商品的日志
	 * 
	 * @param userId
	 * @param mallId
	 * @return
	 */
	public UserPkMallLog getUserPkMallLog(String userId, int mallId);
	
	/**
	 * 更新已购买兑换日志
	 * 
	 * @param userId
	 * @param mallId
	 * @param hasBuy
	 * @return
	 */
	public boolean update(String userId, int mallId, int isBuy, int hasBuyTimes);
}
