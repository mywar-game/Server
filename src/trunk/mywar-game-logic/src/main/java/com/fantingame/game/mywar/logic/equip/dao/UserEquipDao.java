package com.fantingame.game.mywar.logic.equip.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.equip.model.UserEquip;

public interface UserEquipDao {
	
	 /**
     * 获取用户装备列表
     * 
     * @param userId
     * @return
     */
	public List< UserEquip> getUserEquipList(String userId);

	/**
	 * 获取英雄身上的装备
	 * 
	 * @param userId
	 * @param userHeroId
	 * @return
	 */
	public List<UserEquip> getUserEquipList(String userId, String userHeroId);
	
	/**
	 * 获取用户装备
	 * 
	 * @param userId
	 * @param userEquipId
	 * @return
	 */
	public UserEquip getUserEquip(String userId, String userEquipId);
	
	/**
	 * 添加装备
	 * 
	 * @param userEquip
	 * @return
	 */
	public boolean addEquip(UserEquip userEquip);
	
	/**
	 * 穿戴装备
	 * 
	 * @param userId
	 * @param userEquipId
	 * @param userHeroId
	 * @param pos
	 * @return
	 */
	public boolean changeEquipUserHeroId(String userId, String userEquipId, String userHeroId, int pos);
	
	/**
	 * 获取英雄某位置的装备
	 * 
	 * @param userId
	 * @param userHeroId
	 * @param pos
	 * @return
	 */
	public UserEquip getUserEquip(String userId, String userHeroId, int pos);
	
	/**
	 * 删除用户装备
	 * 
	 * @param userId
	 * @param userEquipId
	 * @return
	 */
	public boolean deleteUserEquip(String userId, String userEquipId);
	
	/**
	 * 获取用户装备占用格子数
	 * 
	 * @param userId
	 * @param type 0 背包中  1 仓库中
	 * @return
	 */
	public int getUserEquipCount(String userId, int type);
	
	/**
	 * 获取用户装备列表-在背包内的
	 * 
	 * @param userId
	 * @param equipId
	 * @return
	 */
	public List<UserEquip> getUserEquipList(String userId, int equipId);
	
	/**
	 * 装备附魔
	 * 
	 * @param userId
	 * @param userEquipId
	 * @param equipMagicAttr
	 * @return
	 */
	public boolean equipMagic(String userId, String userEquipId, String equipMagicAttr);

	/**
	 * 仓库取出、存入装备
	 * 
	 * @param userId
	 * @param userEquipId
	 * @param storehouseNum
	 * @return
	 */
	public boolean storehouseInOrOut(String userId, String userEquipId, int storehouseNum);
}
