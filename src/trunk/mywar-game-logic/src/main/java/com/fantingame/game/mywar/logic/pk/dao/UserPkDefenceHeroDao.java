package com.fantingame.game.mywar.logic.pk.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.pk.model.UserPkDefenceHero;

public interface UserPkDefenceHeroDao {

	/**
	 * 获取用户竞技场防守列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserPkDefenceHero> getUserPkDefenceHeroList(String userId);
	
	/**
	 * 下阵用户防守阵容
	 * 
	 * @param userId
	 * @param userHeroId
	 * @return
	 */
	public boolean deleteUserPkDefenceHero(String userId);
	
	/**
	 * 添加用户防守阵容
	 * 
	 * @param userPkDefenceHero
	 * @return
	 */
	public boolean addUserPkDefenceHero(List<UserPkDefenceHero> defenceList);
}
