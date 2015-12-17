package com.fantingame.game.mywar.logic.pawnshop.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.pawnshop.model.UserPawnshopInfo;

public interface UserPawnshopInfoDao {

	/**
	 * 获取阵营当铺信息
	 * 
	 * @param camp
	 * @return
	 */
	public List<UserPawnshopInfo> getUserPawnshopInfoList(int camp);
	
	/**
	 * 获取当铺信息
	 * 
	 * @param camp
	 * @param mallId
	 * @return
	 */
	public UserPawnshopInfo getUserPawnshopInfo(int camp, int mallId);
	
	/**
	 * 更新当铺信息
	 * 
	 * @param userPawnshopInfo
	 * @return
	 */
	public boolean updateUserPawnshopInfo(int camp, int mallId, int num);
	
	/**
	 * 批量添加
	 * 
	 * @param list
	 * @return
	 */
	public boolean add(int camp, List<UserPawnshopInfo> list);
	
}
