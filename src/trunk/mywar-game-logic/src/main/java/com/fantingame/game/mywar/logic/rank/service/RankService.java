package com.fantingame.game.mywar.logic.rank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.fandingame.game.framework.plugin.IAppPlugin;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.rank.RankAction_getUserRankInfoRes;
import com.fantingame.game.msgbody.client.user.UserRankBO;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.rank.dao.RankLogDao;
import com.fantingame.game.mywar.logic.rank.model.RankLog;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.model.UserExtInfo;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.google.common.collect.Lists;

public class RankService implements IAppPlugin {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RankLogDao rankLogDao;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
    private HeroService heroService;
	
	// 用户等级排行榜
	private final static String USER_LEVEL_RANK = "user_level_rank";
	// 用户总战斗力排行榜
	private final static String USER_EFFECTIVE_RANK = "user_effective_rank";
	// 用户金币排行榜
	private final static String USER_GOLD_RANK = "user_gold_rank";
		
	/**
	 * 创建、更新排行榜
	*/
	public void updateUserRank() {
		// 用户等级排行榜
		int rankMax = this.configDataDao.getInt(ConfigKey.rank_max_person, 30);
		createUserRank(USER_LEVEL_RANK, rankMax);
			
		// 用户金币排行榜
		createUserRank(USER_GOLD_RANK, rankMax);
		
		// 用户战斗力排行榜
		List<RankLog> effectiveRankList = this.rankLogDao.getRankLogList(USER_EFFECTIVE_RANK); 
		if (effectiveRankList == null || effectiveRankList.size() == 0) {
			createUserEffectiveRank(rankMax);
		} else {
			Date now = new Date();
			RankLog rankLog = effectiveRankList.get(0);
			if (now.getTime() - rankLog.getCreatedTime().getTime() > 3600* 1000)
				createUserEffectiveRank(rankMax);
		}		
	}
	
	private void createUserEffectiveRank(int rankMax) {
//		List<UserExtInfo> extInfoList = this.userService.getUserEffectiveRankList(rankMax);
//		if (extInfoList == null || extInfoList.size() == 0)
//			return;
//		
//		List<UserRankBO> userRankList = Lists.newArrayList();
//		int rank = 1;
//		for (UserExtInfo info : extInfoList) {
//			User user = this.userService.getByUserId(info.getUserId());
//			userRankList.add(createUserRankBO(user, info.getEffective(), rank));
//			rank++;
//		}
//		
//		RankLog rankLog = createRankLog(userRankList, USER_EFFECTIVE_RANK);
//		this.rankLogDao.deleteRankLog(USER_EFFECTIVE_RANK);
//		this.rankLogDao.addRankLog(rankLog);
	}
		
	/**
	 * 用户金币/等级排名
	 */
	private void createUserRank(String rankKey, int rankMax) {
		Date now = new Date();
		List<User> userList = null;
		
		List<RankLog> rankList = this.rankLogDao.getRankLogList(rankKey);		
		if (rankList == null || rankList.size() == 0) {
			if (rankKey == USER_GOLD_RANK)
				userList = this.userService.getUserRank("gold", rankMax);	
			else 
				userList = this.userService.getUserRank("exp", rankMax);
		} else {
			RankLog rankLog = rankList.get(0);
			if (now.getTime() - rankLog.getCreatedTime().getTime() >= 3600 * 1000) {
				if (rankKey == USER_GOLD_RANK)
					userList = this.userService.getUserRank("gold", rankMax);
				else
					userList = this.userService.getUserRank("exp", rankMax);
			}
		}
		
		if (userList == null || userList.size() == 0)
			return;
			
		List<UserRankBO> userRankList = Lists.newArrayList();
		int rank = 1;
		for (User user : userList) {
			if (rankKey == USER_GOLD_RANK)
				userRankList.add(createUserRankBO(user, user.getGold(), rank));
			else 
				userRankList.add(createUserRankBO(user, user.getLevel(), rank));
			
			rank++;
		}
		
		if (rankKey == USER_GOLD_RANK) {
			RankLog rankLog = createRankLog(userRankList, USER_GOLD_RANK);
			this.rankLogDao.deleteRankLog(USER_GOLD_RANK);
			this.rankLogDao.addRankLog(rankLog);
		} else {
			RankLog rankLog = createRankLog(userRankList, USER_LEVEL_RANK);
			this.rankLogDao.deleteRankLog(USER_LEVEL_RANK);
			this.rankLogDao.addRankLog(rankLog);
		}
	}
		
	private RankLog createRankLog(List<UserRankBO> rankList, String key) {
	   	Date now = new Date();
		RankLog rankLog = new RankLog();
			
	   	rankLog.setRankKey(key);
	   	rankLog.setDate(DateUtils.getDate(now));
	   	rankLog.setCreatedTime(now);
    	rankLog.setRankData(JSON.toJSONString(rankList));
    	return rankLog;
	}
		
	private UserRankBO createUserRankBO(User user, int value, int rank) {			
		UserRankBO userRankBO = new UserRankBO();
		userRankBO.setRank(rank);
		userRankBO.setUserName(user.getName());
		userRankBO.setValue((long) value);
		userRankBO.setLeginName("");
		userRankBO.setUserId(user.getUserId());
			
		UserHeroBO userHeroBO = this.heroService.getUserTeamLeader(user.getUserId());
		userRankBO.setSystemHeroId(userHeroBO.getSystemHeroId());		
		return userRankBO;
	}

	/**
	 * 获取用户排行榜
	 * 
	 * @param userid
	 * @param type 1 等级 2 战斗力 3 土豪榜
	 * @return
	 */
	public RankAction_getUserRankInfoRes getUserRankInfo(String userId, int type) {
		List<RankLog> rankLogList = null;
		if (type == 1) {
			rankLogList = this.rankLogDao.getRankLogList(USER_LEVEL_RANK);
		} else if (type == 2) {
			rankLogList = this.rankLogDao.getRankLogList(USER_EFFECTIVE_RANK);
		} else {
			rankLogList = this.rankLogDao.getRankLogList(USER_GOLD_RANK);
		}
		
		RankAction_getUserRankInfoRes res = new RankAction_getUserRankInfoRes();
		if (rankLogList == null || rankLogList.size() == 0) {
			res.setRankList(new ArrayList<UserRankBO>());
			res.setSelfRankBO(new UserRankBO());
			return res;
		}
		
		RankLog rankLog = rankLogList.get(0);
		List<UserRankBO> userRankList = JSON.parseArray(rankLog.getRankData(), UserRankBO.class);
		
		UserRankBO selfRankBO = null;
		for (UserRankBO rankBO : userRankList) {
			if (rankBO.getUserId().equals(userId))
				selfRankBO = rankBO;
		}
		
		if (selfRankBO == null) {
			User user = this.userService.getByUserId(userId);			
			if (type == 1) {
				int selfRank = this.userService.getUserRank(user.getExp(), "exp");
				selfRankBO = createUserRankBO(user, user.getLevel(), selfRank);
			} else if (type == 2) {
				UserExtInfo userExInfo = this.userService.getUserExtInfo(userId);
				int selfRank = this.userService.getUserEffectiveRank(userExInfo.getEffective());
				selfRankBO = createUserRankBO(user, userExInfo.getEffective(), selfRank);
			} else {
				int selfRank = this.userService.getUserRank(user.getGold(), "gold");
				selfRankBO = createUserRankBO(user, user.getGold(), selfRank);
			}
		}
		
		res.setRankList(userRankList);
		res.setSelfRankBO(selfRankBO);
		return res;
	}
	
	@Override
	public void startup() throws Exception {
		updateUserRank();
	}

	@Override
	public void shutdown() throws Exception {
	}

	@Override
	public int cpOrder() {
		return 0;
	}

}
