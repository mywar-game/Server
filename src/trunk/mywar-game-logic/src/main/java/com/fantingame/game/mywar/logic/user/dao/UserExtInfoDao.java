package com.fantingame.game.mywar.logic.user.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.user.model.UserExtInfo;

public interface UserExtInfoDao {
	/**
	 * 获取扩展信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserExtInfo getUserExtInfo(String userId);

	/**
	 * 添加用户扩展信息
	 * 
	 * @param userExtInfo
	 * @return
	 */
	public boolean addUserExtInfo(UserExtInfo userExtInfo);

	/**
	 * 总充值现实货币的数量
	 */
	public boolean addAllChargMoney(String userId, int addAmount);

	/**
	 * 添加在线时长
	 * 
	 * @param seconds
	 * @return
	 */
	public boolean addOnLineTime(String userId, int addSeconds);

	/**
	 * 添加背包扩展次数
	 * 
	 * @param addTimes
	 * @return
	 */
	public boolean addPackExtendTimes(String userId, int addTimes);

	/**
	 * 上次所处场景位置
	 * 
	 * @param userId
	 * @param prePosition
	 * @return
	 */
	public boolean updatePrePosition(String userId, String prePosition);

	/**
	 * 最新的战斗力
	 * 
	 * @param userId
	 * @param newEffective
	 * @return
	 */
	public boolean updateEffective(String userId, int newEffective);

	/**
	 * 记录新手引导
	 * 
	 * @param userId
	 * @param recordGuideStep
	 * @param guideStep
	 * @return
	 */
	public boolean recordGuideStep(String userId, String recordGuideStep,
			int guideStep);

	/**
	 * 获取战斗力排行榜
	 * 
	 * @param limit
	 * @return
	 */
	public List<UserExtInfo> getUserEffectiveRankList(int limit);
	
	/**
	 * 获取用户战斗力排行榜
	 * 
	 * @param effective
	 * @return
	 */
	public int getUserEffectiveRank(int effective);
	
	/**
	 * 扩展仓库背包格子
	 * 
	 * @param userId
	 * @param extendNum
	 * @return
	 */
	public boolean extendStorehouseNum(String userId, int extendNum);
}
