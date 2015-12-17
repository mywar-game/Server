package com.fantingame.game.mywar.logic.gemstone.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.gemstone.model.UserGemstone;

public interface UserGemstoneDao {

	/**
	 * 获取用户宝石列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserGemstone> getUserGemstoneList(String userId);
	
	/**
	 * 添加用户宝石
	 * 
	 * @param userGemstone
	 * @return
	 */
	public boolean addUserGemstone(UserGemstone userGemstone);
	
	/**
	 * 获取用户宝石
	 * 
	 * @param userId
	 * @param userGemstoneId
	 * @return
	 */
	public UserGemstone getUserGemstone(String userId, String userGemstoneId);
	
	/**
	 * 删除用户宝石
	 * 
	 * @param userId
	 * @param userGemstoneId
	 * @return
	 */
	public boolean deleteUserGemstone(String userId, String userGemstoneId);
	
	/**
	 * 获取用户镶嵌在某个装备上的宝石列表
	 * 
	 * @param userId
	 * @param userEquipId
	 * @return
	 */
	public List<UserGemstone> getUserGemstoneListInEquip(String userId, String userEquipId);
	
	/**
	 * 镶嵌宝石
	 * 
	 * @param userId
	 * @param userGemstoneId
	 * @param userEquipId
	 * @param pos
	 * @return
	 */
	public boolean fillInEquip(String userId, String userGemstoneId, String userEquipId, int pos);
	
	/**
	 * 获取未镶嵌的宝石列表
	 * 
	 * @param userId
	 * @param gemstoneId
	 * @return
	 */
	public List<UserGemstone> getUserGemstoneList(String userId, int gemstoneId);
	
	/**
	 * 升级宝石
	 * 
	 * @param userId
	 * @param userGemstoneId
	 * @param attr
	 * @return
	 */
	public boolean upgradeUserGemstone(String userId, String userGemstoneId, int genstoneId, String attr);
	
	/**
	 * 获取宝石所占格子数
	 * 
	 * @param userId
	 * @param type 0 背包中  1 仓库中
	 * @return
	 */
	public int getUserGemstoneCount(String userId, int type);
	
	/**
	 * 获取未镶嵌的某类宝石-在背包内的
	 * 
	 * @param userId
	 * @param gemstoneId
	 * @return
	 */
	public List<UserGemstone> getUnFillGemstoneList(String userId, int gemstoneId);
	
	/**
	 * 仓库取出、存入宝石
	 * 
	 * @param userId
	 * @param userGemstoneId
	 * @param storehouseNum
	 * @return
	 */
	public boolean storehouseInOrOut(String userId, String userGemstoneId, int storehouseNum);
}
