package com.fantingame.game.mywar.logic.tool.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.tool.model.UserTool;

public interface UserToolDao {
	/**
	 * 获取用户道具
	 * 
	 * @param userId
	 * @param toolId
	 * @return
	 */
	public UserTool get(String userId, int toolId);

	/**
	 * 获取用户 道具数量
	 * 
	 * @param userId
	 * @return
	 */
	public int getUserToolNum(String userId, int toolId);

	/**
	 * 花费道具
	 * 
	 * @param userId
	 * @param toolId
	 * @param num
	 * @return
	 */
	public boolean reduceUserTool(String userId, int toolId, int num);

	/**
	 * 添加道具
	 * 
	 * @param userId
	 * @param toolId
	 * @param num
	 * @return
	 */
	public boolean addUserTool(String userId, int toolId, int num, int storehouseNum);

	/**
	 * 获取用户道具列表
	 * 
	 * @return
	 */
	public List<UserTool> getList(String userId);

	/**
	 * 查询玩家道具数量
	 * 
	 * @param userId
	 * @return
	 */
	public int getUserToolCount(String userId);

	/**
	 * 道具的存取
	 * 
	 * @param userId
	 * @param toolId
	 * @param toolNum
	 * @param storehouseNum
	 * @return
	 */
	public boolean storehouseInOrOut(String userId, int toolId, int toolNum, int storehouseNum);
}
