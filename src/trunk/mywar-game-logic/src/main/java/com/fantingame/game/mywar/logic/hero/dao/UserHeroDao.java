package com.fantingame.game.mywar.logic.hero.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.hero.model.UserHero;

public interface UserHeroDao {
    /**
     * 获取用户英雄列表
     * @param userId
     * @return
     */
	public List<UserHero> getUserHeroList(String userId);
	/**
	 * 添加英雄
	 * @param userHero
	 * @return
	 */
	public boolean addUserHero(UserHero userHero);
	/**
	 * 批量添加英雄
	 * @param userHero
	 * @return
	 */
	public boolean addBatchUserHero(String userId,List<UserHero> userHero);
	/**
	 * 更新英雄常量id
	 * @param userId
	 * @param userHeroId
	 * @param systemHeroId
	 * @return
	 */
	public boolean updateHeroSystemHeroId(String userId,String userHeroId,int systemHeroId);
	/**
	 * 更新英雄经验及等级
	 * @param userId
	 * @param userHeroId
	 * @param exp
	 * @param level
	 * @return
	 */
	public boolean updateHeroExpAndLevel(String userId,String userHeroId,int exp,int level);
	/**
	 * 获取用户英雄对象
	 * @param userId
	 * @param userHeroId
	 * @return
	 */
	public UserHero getUserHero(String userId, String userHeroId);
	
	/**
	 * 根据英雄系统id获取用户英雄对象
	 * 
	 * @param userId
	 * @param systemHeroId
	 * @return
	 */
	public UserHero getUserHero(String userId, int systemHeroId);
	
	/**
	 * 更新英雄战斗力
	 * @param userId
	 * @param userHeroId
	 * @param effective
	 * @return
	 */
	public boolean updateHeroEffective(String userId,String userHeroId,int effective);
	/**
	 * 根据位置获取英雄信息
	 * @param userId
	 * @param pos
	 * @return
	 */
	public UserHero getUserHeroByPos(String userId,int pos);
	/**
	 * 修改上阵位置
	 * @param userId
	 * @param userHeroId
	 * @param pos
	 * @return
	 */
	public boolean changePos(String userId, String userHeroId, int pos);
    /**
     * 修改系统英雄常量唯一id
     * @param userId
     * @param userHeroId
     * @param systemHeroId
     * @return
     */
	public boolean changeSystemHeroId(String userId, String userHeroId, int systemHeroId);
	
	/**
	 * 获取用户英雄个数
	 * 
	 * @param userId
	 * @param status 0 不在背包  1 在背包
	 * @return
	 */
	public int getUserHeroCount(String userId, int status);
	
	/**
	 * 获取用户队长
	 * 
	 * @param userId
	 * @param teamLeader
	 * @return
	 */
	public UserHero getTeamLeaderHero(String userId, int teamLeader);
	
	/**
	 * 更改用户队长
	 * @param userId
	 * @param userHeroId
	 * @param teamLeader
	 * @return
	 */
	public boolean changeTeamLeader(String userId, String userHeroId, int teamLeader);
	
	/**
	 * 遣散英雄/回收
	 * 
	 * @param userId
	 * @param userHeroId
	 * @param status
	 * @return
	 */
	public boolean updateHeroStatus(String userId, String userHeroId, int status);
	
	/**
	 * 更新英雄的星数
	 * 
	 * @param userId
	 * @param userHeroId
	 * @param star
	 * @return
	 */
	public boolean updateHeroStar(String userId, String userHeroId, int star);
	
}
