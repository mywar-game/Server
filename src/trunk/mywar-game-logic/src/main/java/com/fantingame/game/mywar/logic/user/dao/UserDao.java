package com.fantingame.game.mywar.logic.user.dao;

import java.util.Date;
import java.util.List;

import com.fantingame.game.mywar.logic.user.model.User;

public interface UserDao {
	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public User getByUserId(String userId);
	
	/**
	 * 获取用户信息
	 * 
	 * @param ftId
	 * @return
	 */
	public User getByFtId(String ftId);
	
	/**
	 * 获取所有用户的id
	 * 
	 * @return
	 */
	public List<String> getAllUserIds();

	/**
	 * 校验是否存在用户名
	 * 
	 * @param name
	 * @return true 存在 false不存在
	 */
	public boolean isExitName(String name);
	
	/**
	 * 
	 * 
	 * @param level
	 * @return
	 */
	public List<User> getJoinBattleUserList(int level, int camp);

	/**
	 * 添加用户
	 * 
	 * @param role
	 * @return
	 */
	public boolean add(User role);

	/**
	 * 减钱
	 * 
	 * @param userId
	 * @param reduceAmount
	 * @return
	 */
	public boolean reduceMoney(String userId, int reduceAmount);

	/**
	 * 加钱
	 * 
	 * @param userId
	 * @param addAmount
	 * @return
	 */
	public boolean addMoney(String userId, int addAmount);

	/**
	 * 加金币
	 * 
	 * @param userId
	 * @param addAmount
	 * @return
	 */
	public boolean addGold(String userId, int addAmount);

	/**
	 * 减金币
	 * 
	 * @param userId
	 * @param reduceAmount
	 * @return
	 */
	public boolean reduceGold(String userId, int reduceAmount);

	/**
	 * 添加职业经验
	 * 
	 * @param userId
	 * @param addAmount
	 * @return
	 */
	public boolean addJobExp(String userId, int addAmount);
	
	/**
	 * 减少职业经验
	 * 
	 * @param userId
	 * @param reduceAmount
	 * @return
	 */
	public boolean reduceJobExp(String userId, int reduceAmount);
	
	/**
	 * 更新用户的等级及经验
	 * 
	 * @param userId
	 * @param level
	 * @param exp
	 * @return
	 */
	public boolean updateLevelAndExp(String userId, int level, int exp);
	
	/**
	 * 更改名称
	 * 
	 * @param userId
	 * @param name
	 * @return
	 */
	public boolean updateUserName(String userId, String name);

	/**
	 * 加体力
	 * 
	 * @param userId
	 * @param addAmount
	 * @return
	 */
	public boolean addPower(String userId, int addAmount, Date addTime);

	/**
	 * 减耐力
	 * 
	 * @param userId
	 * @param reduceAmount
	 * @return
	 */
	public boolean reducePower(String userId, int reduceAmount);

	/**
	 * 加活力
	 * 
	 * @param userId
	 * @param addAmount
	 * @return
	 */
	public boolean addActivity(String userId, int addAmount, Date addTime);

	/**
	 * 减活力
	 * 
	 * @param userId
	 * @param reduceAmount
	 * @return
	 */
	public boolean reduceActivity(String userId, int reduceAmount);

	/**
	 * 加声望
	 * 
	 * @param userId
	 * @param addAmount
	 * @return
	 */
	public boolean addPrestigeAndPrestigeLevel(String userId, int prestige,
			int prestigeLevel);

	/**
	 * 减声望
	 * 
	 * @param userId
	 * @param reduceAmount
	 * @return
	 */
	public boolean reducePrestige(String userId, int reduceAmount);

	/**
	 * 加荣誉
	 * 
	 * @param userId
	 * @param addAmount
	 * @return
	 */
	public boolean addHonour(String userId, int addAmount);

	/**
	 * 减荣誉
	 * 
	 * @param userId
	 * @param reduceAmount
	 * @return
	 */
	public boolean reduceHonour(String userId, int reduceAmount);

	/**
	 * 更改VIP等级
	 * 
	 * @param userId
	 * @param VIPLevel
	 * @param vipExpiredTime
	 * @return
	 */
	public boolean updateVIPLevel(String userId, int VIPLevel);
	
	/**
	 * 根据名字查找用户
	 * 
	 * @param name
	 * @return
	 */
	public User getByUserName(String name);
	
	/**
	 * 获取时间段内注册的用户
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<String> getBetweenRegTimeList(Date startTime, Date endTime);
	
	/**
	 * 根据等级浮动查找用户
	 * 
	 * @param minLevel
	 * @param maxLevel
	 * @return
	 */
	public List<User> getUserListByFloatLevel(int minLevel, int maxLevel);
	
	/**
	 * 根据条件获取用户的排行榜
	 * 
	 * @param columnName
	 * @param limit
	 * @return
	 */
	public List<User> getUserRank(String columnName, int limit);
	
	/**
	 * 获取用户的排名
	 * 
	 * @param userId
	 * @param columnName
	 * @return
	 */
	public int getUserRank(int value, String columnName);
}
