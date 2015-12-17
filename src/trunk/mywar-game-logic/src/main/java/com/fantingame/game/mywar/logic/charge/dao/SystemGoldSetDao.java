package com.fantingame.game.mywar.logic.charge.dao;


import java.util.List;

import com.fantingame.game.mywar.logic.charge.model.SystemGoldSet;


public interface SystemGoldSetDao {

	/**
	 * 获取列表
	 * 
	 * @param type
	 * @return
	 */
	public List<SystemGoldSet> getList();

	/**
	 * 获取单个
	 * 
	 * @param amount
	 * @return
	 */
	public SystemGoldSet getByPayAmount(double amount, String partnerId);

}
