package com.fantingame.game.mywar.logic.rank.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.rank.model.RankLog;

public interface RankLogDao {

	/**
	 * 获取排名日志
	 * 
	 * @param rankKey
	 * @return
	 */
	public List<RankLog> getRankLogList(String rankKey);
	
	/**
	 * 添加日志列表
	 * 
	 * @param rankList
	 * @return
	 */
	public boolean addList(List<RankLog> rankList);
	
	/**
	 * 删除排名记录
	 * 
	 * @param key
	 * @return
	 */
	public boolean deleteRankLog(String key);
	
	/**
	 * 添加排名信息
	 * 
	 * @param rankLog
	 * @return
	 */
	public boolean addRankLog(RankLog rankLog);
}
