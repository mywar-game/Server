package com.fantingame.game.mywar.logic.legion.dao;

import com.fantingame.game.mywar.logic.legion.model.UserInvestInfo;

public interface UserInvestInfoDao {

	/**
	 * 获取用户公会投资的信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserInvestInfo getUserInvestInfo(String userId);
	
}
