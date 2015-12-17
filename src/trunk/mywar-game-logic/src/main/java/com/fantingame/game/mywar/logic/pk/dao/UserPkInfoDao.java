package com.fantingame.game.mywar.logic.pk.dao;

import java.util.Date;
import java.util.List;

import com.fantingame.game.mywar.logic.pk.model.UserPkInfo;

public interface UserPkInfoDao {

	/**
	 * 获取用户竞技场信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserPkInfo getUserPkInfo(String userId);
	
	/**
	 * 获取竞技场最后一名用户信息
	 * 
	 * @return
	 */
	public UserPkInfo getLastUserPkInfo();
	
	/**
	 * 添加用户竞技场信息
	 * 
	 * @param userPkInfo
	 * @return
	 */
	public boolean addUserPkInfo(UserPkInfo userPkInfo);
	
	/**
	 * 更新用户竞技场信息
	 * 
	 * @param userId
	 * @param challengeTimes
	 * @return
	 */
	public boolean updateUserPkInfo(String userId, int challengeTimes, int buyChallengeTimes, int isReset);
	
	/**
	 * 查找可挑战者列表
	 * 
	 * @param minRank
	 * @param maxRank
	 * @param userId
	 * @return
	 */
	public List<UserPkInfo> findChallengerList(int minRank, int maxRank, String userId);
	
	/**
	 * 重置挑战等待时间
	 * 
	 * @param userId
	 * @param isReset
	 * @return
	 */
	public boolean resetUserWaitingTime(String userId, int isReset);
	
	/**
	 * 获取排行榜
	 * 
	 * @return
	 */
	public List<UserPkInfo> getRankList();
	
	/**
	 * 更新挑战次数
	 * 
	 * @param userId
	 * @param challengeTimes
	 * @return
	 */
	public boolean updateUserPkInfo(String userId, int hasChallengeTimes, int isReset, Date attackTime);
	
	/**
	 * 更换排名
	 * 
	 * @param userId
	 * @param targetUserId
	 * @param hasChallengeTimes
	 * @param highestRank
	 * @return
	 */
	public boolean changeRank(String userId, String targetUserId, int hasChallengeTimes, int isReset, int highestRank, Date attackTime);
	
	/**
	 * 更新购买次数
	 * 
	 * @param userId
	 * @param buyChallengeTimes
	 * @return
	 */
	public boolean updateUserPkInfoBuyChallengeTimes(String userId, int buyChallengeTimes);
	
	/**
	 * 查看竞技场人数
	 * 
	 * @return
	 */
	public int getUserPkInfoCount();
	
	/**
	 * 添加Pk列表
	 * 
	 * @param infoList
	 * @return
	 */
	public boolean addAll(List<UserPkInfo> infoList);
}
