package com.fantingame.game.mywar.logic.pk.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.pk.model.UserPkLog;

public interface UserPkLogDao {

	/**
	 * 获取用户战斗日志
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserPkLog> getUserPkLogList(String userId);

	/**
	 * 添加挑战日志
	 * 
	 * @param userPkLog
	 * @return
	 */
	public boolean addUserPkLog(UserPkLog userPkLog);

}
