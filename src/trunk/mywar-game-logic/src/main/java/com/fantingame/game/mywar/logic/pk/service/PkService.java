package com.fantingame.game.mywar.logic.pk.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.plugin.IAppPlugin;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.model.SystemConfigData;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.RandomUtils;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.pk.PkAction_endPkFightRes;
import com.fantingame.game.msgbody.client.pk.PkAction_enterPkRes;
import com.fantingame.game.msgbody.client.pk.PkAction_getPkFightLogRes;
import com.fantingame.game.msgbody.client.pk.PkAction_getUserPkInfoRes;
import com.fantingame.game.msgbody.client.pk.PkAction_refreshChallengerRes;
import com.fantingame.game.msgbody.client.pk.PkAction_startPkFightRes;
import com.fantingame.game.msgbody.client.pk.PkChallengerBO;
import com.fantingame.game.msgbody.client.pk.PkFightLogBO;
import com.fantingame.game.msgbody.client.pk.PkMallBO;
import com.fantingame.game.msgbody.client.pk.PkRankBO;
import com.fantingame.game.msgbody.client.pk.UserPkInfoBO;
import com.fantingame.game.mywar.logic.achievement.check.IAchievementChecker;
import com.fantingame.game.mywar.logic.achievement.constant.AchievementConstant;
import com.fantingame.game.mywar.logic.achievement.service.AchievementService;
import com.fantingame.game.mywar.logic.activity.constant.ActivityTaskConstant;
import com.fantingame.game.mywar.logic.activity.service.ActivityService;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.equip.service.EquipService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.hero.model.UserHero;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.mail.constant.SystemMailConstant;
import com.fantingame.game.mywar.logic.mail.service.MailService;
import com.fantingame.game.mywar.logic.pk.bean.UserPkBattleBean;
import com.fantingame.game.mywar.logic.pk.dao.UserPkDefenceHeroDao;
import com.fantingame.game.mywar.logic.pk.dao.UserPkInfoDao;
import com.fantingame.game.mywar.logic.pk.dao.UserPkLogDao;
import com.fantingame.game.mywar.logic.pk.dao.UserPkMallLogDao;
import com.fantingame.game.mywar.logic.pk.dao.cache.SystemHonourExchangeDaoCache;
import com.fantingame.game.mywar.logic.pk.dao.cache.SystemPkRankRewardDaoCache;
import com.fantingame.game.mywar.logic.pk.model.SystemHonourExchange;
import com.fantingame.game.mywar.logic.pk.model.SystemPkRankReward;
import com.fantingame.game.mywar.logic.pk.model.UserPkDefenceHero;
import com.fantingame.game.mywar.logic.pk.model.UserPkInfo;
import com.fantingame.game.mywar.logic.pk.model.UserPkLog;
import com.fantingame.game.mywar.logic.pk.model.UserPkMallLog;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.model.UserExtInfo;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 竞技场相关业务逻辑
 * 
 * @author yezp
 */
public class PkService implements IAppPlugin, ModuleEventHandler {

	@Autowired
	private UserPkInfoDao userPkInfoDao;
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private HeroService heroService;
	@Autowired
	private UserPkDefenceHeroDao userPkDefenceHeroDao;
	@Autowired
	private EquipService equipService;
	@Autowired
	private UserPkLogDao userPkLogDao;
	@Autowired
	private UserPkMallLogDao userPkMallLogDao;
	@Autowired
	private SystemHonourExchangeDaoCache systemHonourExchangeDao;
	@Autowired
	private SystemPkRankRewardDaoCache systemPkRankRewardDao;
	@Autowired
	private GoodsDealService goodsDealService;
	@Autowired
	private MailService mailService;
	@Autowired
	private AchievementService achievementService;
	@Autowired
	private ActivityService activityService;
	
	private final static int IS_RESET = 1;
	private final static int IS_NOT_RESET = 0;
	/**
	 * 第一次进入竞技场
	 * 
	 * @param userId
	 * @return
	 */
	public PkAction_enterPkRes enterPk(String userId) {
		User user = this.userService.getByUserId(userId);
		int openLevel = this.configDataDao.getInt(ConfigKey.pk_open_level, 20);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "未到竞技场开放等级");
		
		PkAction_enterPkRes res = new PkAction_enterPkRes();		
		UserPkInfo userPkInfo = userPkInfoDao.getUserPkInfo(userId);		
		if (userPkInfo == null) {
			UserPkInfo lastInfo = this.userPkInfoDao.getLastUserPkInfo();
			userPkInfo = new UserPkInfo();
			userPkInfo.setUserId(userId);
			userPkInfo.setHasChallengeTimes(0);
			userPkInfo.setBuyChallengeTimes(0);
			userPkInfo.setIsReset(IS_NOT_RESET);
			userPkInfo.setCreatedTime(new Date());
			userPkInfo.setUpdatedTime(new Date());
			
			if (lastInfo == null) {
				userPkInfo.setRank(1);
				userPkInfo.setHighestRank(1);
			} else {				
				userPkInfo.setRank(lastInfo.getRank() + 1);
				userPkInfo.setHighestRank(lastInfo.getRank() + 1);
			}			
			
			this.userPkInfoDao.addUserPkInfo(userPkInfo);
			UserPkInfoBO userPkInfoBO = createUserPkInfoBO(userPkInfo);
			
			res.setUserPkInfoBO(userPkInfoBO);
			res.setUserDefenceHeroList(new ArrayList<String>());
			List<PkChallengerBO> challengerList = findChallengerList(userPkInfo);
			res.setUserPkList(challengerList);
			return res;
		}
		
		UserPkInfoBO userPkInfoBO = getUserPkInfoBO(userPkInfo);
		List<PkChallengerBO> challengerList = findChallengerList(userPkInfo);
		List<String> userDefenceHeroList = getUserDefenceHeroList(userId);
		
		res.setUserPkInfoBO(userPkInfoBO);
		res.setUserPkList(challengerList);
		res.setUserDefenceHeroList(userDefenceHeroList);
		return res;
	}
	
	/**
	 * 获取用户竞技场信息
	 * 
	 * @param userPkInfo
	 * @return
	 */
	private UserPkInfoBO getUserPkInfoBO(UserPkInfo userPkInfo) {
		// 不是同一天刷新数据
		if (!DateUtils.isSameDay(new Date(), userPkInfo.getUpdatedTime())) {
			this.userPkInfoDao.updateUserPkInfo(userPkInfo.getUserId(), 0, 0, IS_NOT_RESET);
			userPkInfo = this.userPkInfoDao.getUserPkInfo(userPkInfo.getUserId());
		}
		
		UserPkInfoBO userPkInfoBO = createUserPkInfoBO(userPkInfo);
		return userPkInfoBO;
	}
	
	/**
	 * 获取用户防守阵容
	 * 
	 * @param userId
	 * @return
	 */
	private List<String> getUserDefenceHeroList(String userId) {
		List<String> list = Lists.newArrayList();
		List<UserPkDefenceHero> defenceHeroList = this.userPkDefenceHeroDao.getUserPkDefenceHeroList(userId);
		for (UserPkDefenceHero defenceHero : defenceHeroList) {
			list.add(defenceHero.getUserHeroId());
		}
		
		return list;
	}
	
	public Map<String, String> getPkDefenceHeroMap(String userId) {
		Map<String, String> map = Maps.newConcurrentMap();
		List<UserPkDefenceHero> defenceHeroList = this.userPkDefenceHeroDao.getUserPkDefenceHeroList(userId);
		for (UserPkDefenceHero defenceHero : defenceHeroList) {
			map.put(defenceHero.getUserHeroId(), defenceHero.getUserHeroId());
		}
		
		return map;
	}
	
	/**
	 * 查找可挑战者
	 * 
	 * @param user
	 * @return
	 */
	private List<PkChallengerBO> findChallengerList(UserPkInfo userPkInfo) {
		List<PkChallengerBO> challengerList = Lists.newArrayList();
		Map<String, Integer> map = getRankFloat(userPkInfo.getRank());
		int minRank = map.get("min");
		int maxRank = map.get("max");
		
		List<UserPkInfo> userPkInfoList = this.userPkInfoDao.findChallengerList(minRank, maxRank, userPkInfo.getUserId());
		if (userPkInfoList == null || userPkInfoList.size() == 0)
			return challengerList;
		
		List<Integer> indexList = getChallengerIndex(userPkInfoList.size());	
		for (int i = 0; i < indexList.size(); i++) {
			UserPkInfo pkUserInfo = userPkInfoList.get(indexList.get(i));
			User challengerUser = this.userService.getByUserId(pkUserInfo.getUserId());
			PkChallengerBO pkChallengerBO = new PkChallengerBO();
			
			// 构造假数据
			if (challengerUser == null) {
				UserExtInfo userExtInfo = this.userService.getUserExtInfo(userPkInfo.getUserId());
				UserHeroBO userHeroBO = this.heroService.getUserTeamLeader(userPkInfo.getUserId());
				
				pkChallengerBO.setUserId(pkUserInfo.getUserId());
				pkChallengerBO.setChallengerName("NPC-" + i);
				pkChallengerBO.setPower(userExtInfo.getEffective());
				pkChallengerBO.setSystemHeroId(userHeroBO.getSystemHeroId());
				pkChallengerBO.setRank(pkUserInfo.getRank());
			} else {			
				int power = getDefencePower(pkUserInfo.getUserId());				
				UserHeroBO userHeroBO = this.heroService.getUserTeamLeader(pkUserInfo.getUserId());
			
				pkChallengerBO.setUserId(pkUserInfo.getUserId());
				pkChallengerBO.setChallengerName(challengerUser.getName());
				pkChallengerBO.setPower(power);
				pkChallengerBO.setSystemHeroId(userHeroBO.getSystemHeroId());
				pkChallengerBO.setRank(pkUserInfo.getRank());
			}
			
			challengerList.add(pkChallengerBO);
		}
		
		return challengerList;
	}
	
	private int getDefencePower(String userId) {
		List<UserPkDefenceHero> defenceList = this.userPkDefenceHeroDao.getUserPkDefenceHeroList(userId);
		if (defenceList == null || defenceList.size() == 0) {
			UserExtInfo userExtInfo = this.userService.getUserExtInfo(userId);
			
			return userExtInfo.getEffective();
		}
		
		int defencePower = 0;
		for (UserPkDefenceHero defenceHero : defenceList) {
			UserHeroBO userHeroBO = this.heroService.getUserHeroBO(userId, defenceHero.getUserHeroId());
			defencePower = defencePower + userHeroBO.getEffective();
		}
		
		return defencePower;
	}
	
	private List<Integer> getChallengerIndex(int size) {
		List<Integer> list = Lists.newArrayList();
		int randomIndex = 0;
		
		int count = size <= 3 ? 3 : size;
		if (count <= 3) {
			for (int i = 0; i < count; i++) {
				list.add(i);
			}
			
			return list;
		}
		
		int internal = size / 3;
		int remainder = size % 3;
		int start = randomIndex;
		for (int i = 0; i < 3; i++) {
			if (i < 2) 
				randomIndex = RandomUtils.getRandomNum(start, (start + internal - 1));
			else 
				randomIndex = RandomUtils.getRandomNum(start, (start + internal + remainder - 1));
			
			// System.out.println("------======" + randomIndex);
			list.add(randomIndex);
			start = start + internal;
		}
		
		return list;		
	}
	
	/**
	 * 获取可挑战用户的浮动排名
	 * 
	 * @param rank
	 * @return
	 */
	private Map<String, Integer> getRankFloat(int rank) {
		int minRank = rank;
		int maxRank = 1;
		
		if (rank > 2000) {
			minRank = rank + 500;
			maxRank = rank - 1000;
		} else if (rank > 500 && rank <= 2000) {
			minRank = rank + 250;
			maxRank = rank - 500;
		} else if (rank > 200 && rank <= 500) {
			minRank = rank + 50;
			maxRank = rank - 100;
		} else if (rank > 100 && rank <= 200) {
			minRank = rank + 25;
			maxRank = rank - 50;
		} else if (rank > 50 && rank <= 100) {
			minRank = rank + 10;
			maxRank = rank - 20;
		} else {
			minRank = rank + 5;
			maxRank = rank - 10;
		}		
		
		Map<String, Integer> map = Maps.newConcurrentMap();
		map.put("min", minRank);
		map.put("max", maxRank);		
		return map;
	}
	
	/**
	 * 创建用户竞技场信息
	 * 
	 * @param userPkInfo
	 * @return
	 */
	private UserPkInfoBO createUserPkInfoBO(UserPkInfo userPkInfo) {
		UserPkInfoBO userPkInfoBO = new UserPkInfoBO();

		int challengeTimes = this.configDataDao.getInt(ConfigKey.pk_challenge_times, 5);
		int remainderTimes = challengeTimes - userPkInfo.getHasChallengeTimes() + userPkInfo.getBuyChallengeTimes();
		if (remainderTimes < 0)
			userPkInfoBO.setChallengeTimes(0);
		else 
			userPkInfoBO.setChallengeTimes(remainderTimes);
		userPkInfoBO.setRank(userPkInfo.getRank());
		
		// 挑战之后需要等待10分钟
		long needWaitingTime = this.configDataDao.getInt(ConfigKey.pk_after_waiting_time, 10) * 60 * 1000;
		Date now = new Date();
		
		// 判断是否已重置
		if (userPkInfo.getIsReset() == IS_RESET) {
			userPkInfoBO.setRemainderTime(0l);				
		} else {
			if (userPkInfo.getHasChallengeTimes() <= 0)
				userPkInfoBO.setRemainderTime(0l);
			else if (now.getTime() - userPkInfo.getUpdatedTime().getTime() >= needWaitingTime) {
				userPkInfoBO.setRemainderTime(0l);
			} else {
				userPkInfoBO.setRemainderTime((userPkInfo.getUpdatedTime().getTime() + needWaitingTime) - now.getTime());
			}
		}
		
		return userPkInfoBO;
	}
	
	/**
	 * 获取用户竞技场信息
	 * 
	 * @param userId
	 * @return
	 */
	public PkAction_getUserPkInfoRes getUserPkInfo(String userId) {
		User user = this.userService.getByUserId(userId);
		int openLevel = this.configDataDao.getInt(ConfigKey.pk_open_level, 20);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "未到竞技场开放等级");
		
		UserPkInfo userPkInfo = this.userPkInfoDao.getUserPkInfo(userId);
		if (userPkInfo == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "为什么会是空的？？是不是应该走首次进入竞技场的接口？？");
		
		
		PkAction_getUserPkInfoRes res = new PkAction_getUserPkInfoRes();
		UserPkInfoBO userPkInfoBO = getUserPkInfoBO(userPkInfo);
		List<PkChallengerBO> challengerList = findChallengerList(userPkInfo);
		List<String> userDefenceHeroList = getUserDefenceHeroList(userId);
		
		res.setUserPkInfoBO(userPkInfoBO);
		res.setUserPkList(challengerList);
		res.setUserDefenceHeroList(userDefenceHeroList);
		return res;
	}
	
	public UserPkInfo getUserPkInfoByUserId(String userId) {
		return this.userPkInfoDao.getUserPkInfo(userId);
	}
	
	private final int NUM_OF_FULL = 2002;
	/**
	 * 上阵/下阵
	 * 
	 * @param userId
	 * @param userHeroId
	 * @param type
	 */
	public void changePos(String userId, List<String> userHeroIdList) {
		if (userHeroIdList == null || userHeroIdList.size() == 0)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，userHeroIdList为空");
				
		if (userHeroIdList.size() > 5)
			throw new ServiceException(NUM_OF_FULL, "上阵人数已满, size=" + userHeroIdList.size());
			
		//先全部下阵，再上阵 
		this.userPkDefenceHeroDao.deleteUserPkDefenceHero(userId);
		
		Map<String, String> map = Maps.newConcurrentMap();
		List<UserPkDefenceHero> defenceList = Lists.newArrayList();
		for (String userHeroId : userHeroIdList) {
			if (map.containsKey(userHeroId))
				throw new ServiceException(SystemErrorCode.PARAM_ERROR, "竞技场调整阵容，上阵了重复的英雄");
			
			UserHeroBO userHeroBO = this.heroService.getUserHeroBO(userId, userHeroId);
			if (userHeroBO == null)
				throw new ServiceException(SystemConstant.FAIL_CODE, "没有该英雄，userId=" + userId + ", userHeroId=" + userHeroId);
			
			UserPkDefenceHero defenceHero = new UserPkDefenceHero();
			defenceHero.setUserId(userId);
			defenceHero.setUserHeroId(userHeroId);
			defenceHero.setCreatedTime(new Date());
			defenceHero.setUpdatedTime(new Date());
			
			defenceList.add(defenceHero);
			map.put(userHeroId, userHeroId);
		}
		this.userPkDefenceHeroDao.addUserPkDefenceHero(defenceList);					
	}
	
	/**
	 * 换一批
	 * 
	 * @param userId
	 * @return
	 */
	public PkAction_refreshChallengerRes refreshChallenger(String userId) {
		UserPkInfo userPkInfo = this.userPkInfoDao.getUserPkInfo(userId);		
		List<PkChallengerBO> challengerList = findChallengerList(userPkInfo);
		
		PkAction_refreshChallengerRes res = new PkAction_refreshChallengerRes();
		res.setUserPkList(challengerList);
		return res;
	}
	
	public final static int NO_NEED_RESET = 2001;
	public final static int HAS_RESET = 2002;
	/**
	 * 重置挑战等待时间
	 * 
	 * @param userId
	 */
	public void resetWaitingTime(String userId) {
		UserPkInfo userPkInfo = this.userPkInfoDao.getUserPkInfo(userId);
		if (userPkInfo.getHasChallengeTimes() <= 0)
			throw new ServiceException(NO_NEED_RESET, "都还没挑战，重置个毛丫....");		
		
		if (userPkInfo.getIsReset() == IS_RESET)
			throw new ServiceException(HAS_RESET, "已经重置过了");
		
		// 挑战之后需要等待10分钟
		long needWaitingTime = this.configDataDao.getInt(ConfigKey.pk_after_waiting_time, 10) * 60 * 1000;
		Date now = new Date();
		
		if (now.getTime() - userPkInfo.getUpdatedTime().getTime() >= needWaitingTime)
			throw new ServiceException(NO_NEED_RESET, "CD时间都过了，无需重置");
		
		int cost = this.configDataDao.getInt(ConfigKey.pk_reset_waiting_time_cost, 50);
		User user = this.userService.getByUserId(userId);
		
		if (user.getMoney() < cost) 
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		if (!this.userService.reduceMoney(userId, cost, GoodsUseType.REDUCE_RESET_WAITINGTIME))
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		this.userPkInfoDao.resetUserWaitingTime(userId, IS_RESET);		
	}
	
	/**
	 * 查看排行榜
	 * 
	 * @return
	 */
	public List<PkRankBO> getPkRankList() {
		List<PkRankBO> rankList = Lists.newArrayList();
		List<UserPkInfo> infoList = this.userPkInfoDao.getRankList();
		for (UserPkInfo userPkInfo : infoList) {
			PkRankBO rankBO = new PkRankBO();
			String userId = userPkInfo.getUserId();
			
			User user = this.userService.getByUserId(userId);
			List<Integer> defenceHeroList = Lists.newArrayList();
			int totalPower = 0;
			
			// 假数据
			if (user == null) {
				rankBO.setUserName("NPC-" + userPkInfo.getRank());
				rankBO.setLevel(15);
				rankBO.setSystemHeroId(10002);
				
				defenceHeroList.add(10002);
				rankBO.setDefenceHeroList(defenceHeroList);
			} else {
				rankBO.setUserName(user.getName());
				rankBO.setLevel(user.getLevel());
				
				UserHeroBO userHeroBO = this.heroService.getUserTeamLeader(userId);
				rankBO.setSystemHeroId(userHeroBO.getSystemHeroId());
				
				List<UserPkDefenceHero> UserDefenceHeroList = this.userPkDefenceHeroDao.getUserPkDefenceHeroList(userId);
				for (UserPkDefenceHero defenceHero : UserDefenceHeroList) {
					UserHeroBO heroBO = this.heroService.getUserHeroBO(userId, defenceHero.getUserHeroId());
					defenceHeroList.add(heroBO.getSystemHeroId());
					totalPower = totalPower + heroBO.getEffective();
				}
				
				rankBO.setDefenceHeroList(defenceHeroList);
			}			
			
			rankBO.setRank(userPkInfo.getRank());
			// TODO 军团
			rankBO.setLegionName("");
			rankBO.setDefencePower(totalPower);
			rankList.add(rankBO);
		}
		
		return rankList;
	}
	
	private Map<String, UserPkBattleBean> inBattleUser = Maps.newConcurrentMap();
	// 战斗只有99秒
	private final static int Battle_TAG_PASS_TIME = 99 * 1000;
	private final static int NO_CHALLENGE_TIMES = 2001;
	private final static int IN_WAITING_TIME = 2002;
	/**
	 * 开始挑战玩家
	 * 
	 * @param userId
	 * @param targetUserId
	 */
	public PkAction_startPkFightRes startPkFight(String userId, String targetUserId) {
		UserPkInfo userPkInfo = this.userPkInfoDao.getUserPkInfo(userId);
		// 不是同一天刷新数据
		if (!DateUtils.isSameDay(new Date(), userPkInfo.getUpdatedTime())) {
			this.userPkInfoDao.updateUserPkInfo(userPkInfo.getUserId(), 0, 0, IS_NOT_RESET);
			userPkInfo = this.userPkInfoDao.getUserPkInfo(userPkInfo.getUserId());
		}
		
		int freeChallengeTimes = this.configDataDao.getInt(ConfigKey.pk_challenge_times, 5);
		int costChallengeTimes = this.configDataDao.getInt(ConfigKey.pk_cost_challenge_times, 5);
		
		int hasChallengeTimes = userPkInfo.getHasChallengeTimes();
		int buyChallengeTimes = userPkInfo.getBuyChallengeTimes();
		// 判断是否已购买次数
		if (buyChallengeTimes <= 0) {
			if (hasChallengeTimes >= freeChallengeTimes)
				throw new ServiceException(NO_CHALLENGE_TIMES, "已经没有挑战次数。");
		} else {
			if (hasChallengeTimes >= (freeChallengeTimes + buyChallengeTimes) 
					|| (hasChallengeTimes >= (freeChallengeTimes + costChallengeTimes)))
				throw new ServiceException(NO_CHALLENGE_TIMES, "已经没有挑战次数。");
		}
		
		if (inBattleUser.containsKey(userId)) {
			Date preBattleTime = inBattleUser.get(userId).getAttackTime();
    		if(System.currentTimeMillis() - preBattleTime.getTime() <= Battle_TAG_PASS_TIME){
    			 ServiceException e =  new ServiceException(SystemConstant.FAIL_CODE, "已在战斗中！不要重复战斗,userId=" + userId);
    			 LogSystem.error(e, "已在战斗中！不要重复战斗,userId=" + userId);
    			 throw e;
    		}
			LogSystem.error(new RuntimeException(), "已在战斗中！这是为什么呢！,userId=" + userId);
		}

		// 挑战之后需要等待10分钟
		long needWaitingTime = this.configDataDao.getInt(ConfigKey.pk_after_waiting_time, 10) * 60 * 1000;
		Date now = new Date();
				
		if (userPkInfo.getIsReset() == IS_NOT_RESET && (userPkInfo.getHasChallengeTimes() > 0 && now.getTime() - userPkInfo.getUpdatedTime().getTime() < needWaitingTime))
			throw new ServiceException(IN_WAITING_TIME, "未到挑战时间，请等待.....");
		
		// 返回防守方的英雄以及装备列表
		List<UserPkDefenceHero> defenceList = this.userPkDefenceHeroDao.getUserPkDefenceHeroList(targetUserId);
		List<UserHeroBO> defenceHeroList = Lists.newArrayList();
		List<UserEquipBO> defenceEquipList = Lists.newArrayList();

		int defencePower = 0;
		if (defenceList == null || defenceList.size() == 0) {
			User targetUser = this.userService.getByUserId(targetUserId);
			if (targetUser == null) {
				getUserBattleInfo(defenceHeroList, defenceEquipList, userId);
			} else {
				getUserBattleInfo(defenceHeroList, defenceEquipList, targetUserId);
			}
		}else {
			for (UserPkDefenceHero defenceHero : defenceList) {
				UserHeroBO userHeroBO = this.heroService.getUserHeroBO(targetUserId, defenceHero.getUserHeroId());
				List<UserEquipBO> userEquipList = this.equipService.getUserHeroEquipList(targetUserId, defenceHero.getUserHeroId());
				defencePower = defencePower + userHeroBO.getEffective();
			
				defenceHeroList.add(userHeroBO);
				defenceEquipList.addAll(userEquipList);
			}
		}
		
		int attackPower = 0;
		List<UserHero> userHeroList = this.heroService.getBattleUserHero(userId);
		for (UserHero userHero : userHeroList) {
			attackPower = attackPower + userHero.getEffective();
		}
		
		UserPkBattleBean bean = new UserPkBattleBean();
		bean.setTargetUserId(targetUserId);
		bean.setDefencePower(defencePower);
		bean.setAttackPower(attackPower);
		bean.setAttackTime(now);
		inBattleUser.put(userId, bean);
		
		PkAction_startPkFightRes res = new PkAction_startPkFightRes();
		res.setUserHeroList(defenceHeroList);
		res.setUserEquipList(defenceEquipList);
		return res;
	}
	
	private int getUserBattleInfo(List<UserHeroBO> defenceHeroList, List<UserEquipBO> defenceEquipList, String userId) {
		int defencePower = 0;
		List<UserHeroBO> list = this.heroService.getBattleUserHeroBO(userId);
		for (UserHeroBO userHeroBO : list) {
			List<UserEquipBO> userEquipList = this.equipService.getUserHeroEquipList(userId, userHeroBO.getUserHeroId());
			defencePower = defencePower + userHeroBO.getEffective();
			defenceEquipList.addAll(userEquipList);
		}			
		
		defenceHeroList.addAll(list);		
		return defencePower;
	}
	
	// 1 排名奖励   2 历史排名奖励
	private final static int REWARD_TYPE_RANK = 1;
	private final static int REWARD_TYPE_HIGHTEST_RANK = 2;
	private final static int END_ATTACK_TIME_AVALIAD = 2001;
	/**
	 * 结束挑战
	 * 
	 * @param userId
	 * @param flag
	 * @return
	 */
	public PkAction_endPkFightRes endPkFight(String userId, int flag) {
		final UserPkBattleBean userPkBattleBean = inBattleUser.remove(userId);
		if (userPkBattleBean == null) {
			ServiceException e = new ServiceException(SystemConstant.FAIL_CODE, "不存在用户挑战玩家的记录！！");
			 LogSystem.warn("不存在用户挑战玩家的记录！！外挂？？userId="+userId);
			 throw e;
		}
		
		//时间校验，起码也要个3秒吧？？
		Date now = new Date();
		if (now.getTime() - userPkBattleBean.getAttackTime().getTime() < 3 * 1000)
			throw new ServiceException(END_ATTACK_TIME_AVALIAD, "时间校验失败！");
		
		// 更新挑战次数，记录挑战日志，发放点奖励
		UserPkInfo userPkInfo = this.userPkInfoDao.getUserPkInfo(userId);
		final UserPkInfo targetPkInfo = this.userPkInfoDao.getUserPkInfo(userPkBattleBean.getTargetUserId());

		int rank = userPkInfo.getRank();
		int targetRank = targetPkInfo.getRank();
		
		int hasChallengeTimes = userPkInfo.getHasChallengeTimes();
		PkAction_endPkFightRes res = new PkAction_endPkFightRes();
		CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
		res.setOldRank(rank);
		
		// 挑战失败
		if (flag <= 0) {
			this.userPkInfoDao.updateUserPkInfo(userId, hasChallengeTimes + 1, IS_NOT_RESET, userPkBattleBean.getAttackTime());
			// TODO 奖励 团队经验、英雄经验、荣誉点
			List<GoodsBeanBO> goods = GoodsHelper.parseDropGoods(GoodsType.honour.intValue + ",0," + userPkBattleBean.getAttackPower() / 100);
			drop = goodsDealService.sendGoods(userId, goods, GoodsUseType.ADD_PK_CHALLENGE_REWARD);
			
		} else {
			// 挑战比自己排名高的才更新排名  反过来的
			if (targetPkInfo.getRank() < userPkInfo.getRank()) {
				int highestRank = userPkInfo.getHighestRank();
				if (targetPkInfo.getRank() > highestRank) {
					highestRank = targetPkInfo.getRank();
					// TODO 历史新高排名奖励
					SystemPkRankReward rankReward = this.systemPkRankRewardDao.getSystemPkRankReward(REWARD_TYPE_HIGHTEST_RANK, highestRank);					
					mailService.send(userId, null, SystemMailConstant.PK_HIGHEST_RANK_MAILID, rankReward.getRewards());
				}
				
				this.userPkInfoDao.changeRank(userId, userPkBattleBean.getTargetUserId(), hasChallengeTimes + 1, IS_NOT_RESET, highestRank, userPkBattleBean.getAttackTime());
				achievementService.updateAchievementFinish(userId, 1, AchievementConstant.TYPE_LIFE, new IAchievementChecker() {
					@Override
					public boolean isHit(int achievementId, Map<String, String> params) {
						boolean isHit = false;
						if (params.containsKey("pkRank")) {
							int pkRank = Integer.parseInt(params.get("pkRank"));
							if (targetPkInfo.getRank() <= pkRank)
								isHit = true;							
						}
						
						return isHit;
					}
				});
				
			} else {			
				this.userPkInfoDao.updateUserPkInfo(userId, hasChallengeTimes + 1, IS_NOT_RESET, userPkBattleBean.getAttackTime());
			}
			
			// TODO 奖励
			List<GoodsBeanBO> goods = GoodsHelper.parseDropGoods(GoodsType.honour.intValue + ",0," + userPkBattleBean.getDefencePower() / 100);
			drop = goodsDealService.sendGoods(userId, goods, GoodsUseType.ADD_PK_CHALLENGE_REWARD);
		}
		
		addPkBattleLog(userId, userPkBattleBean.getTargetUserId(), flag, rank, targetRank);
		
		userPkInfo = this.userPkInfoDao.getUserPkInfo(userId);
		this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_CHALLENGE_PK, 1);
		res.setRank(userPkInfo.getRank());
		res.setDrop(drop);
		return res;
	}
	
	/**
	 * 记录挑战日志
	 * 
	 * @param userId
	 * @param targetUserId
	 * @param flag
	 * @return
	 */
	private boolean addPkBattleLog(String userId, String targetUserId, int flag, int rank, int targetRank) {
		UserPkLog log = new UserPkLog();
		log.setUserId(userId);
		log.setTargetUserId(targetUserId);
		log.setFlag(flag);
		
		User user = this.userService.getByUserId(userId);
		User targetUser = this.userService.getByUserId(targetUserId);
		
		log.setLevel(user.getLevel());
		log.setRank(rank);
		log.setTargetRank(targetRank);
		
		UserHeroBO userHeroBO = this.heroService.getUserTeamLeader(userId);
		log.setSystemHeroId(userHeroBO.getSystemHeroId());
		
		// 假数据
		if (targetUser == null) {
			log.setTargetLevel(user.getLevel());
			log.setTargetHeroId(userHeroBO.getSystemHeroId());			
		} else {		
			log.setTargetLevel(targetUser.getLevel());
			userHeroBO = this.heroService.getUserTeamLeader(targetUserId);
			log.setTargetHeroId(userHeroBO.getSystemHeroId());
		}
		
		log.setCreatedTime(new Date());		
		return this.userPkLogDao.addUserPkLog(log);
	}
	
	/**
	 * 获取战斗日志
	 * 
	 * @param userId
	 * @return
	 */
	public PkAction_getPkFightLogRes getPkFightLog(String userId) {
		List<PkFightLogBO> logList = Lists.newArrayList();
		// 只需要七天以内的
		Date endTime = DateUtils.addDays(new Date(), -7);
		
		List<UserPkLog> pkLogList = this.userPkLogDao.getUserPkLogList(userId);		
		List<UserPkLog> sortList = sortByFightTime(pkLogList);
		
		for (UserPkLog log : sortList) {
			if (log.getCreatedTime().before(endTime))
				continue;
			
			PkFightLogBO logBO = new PkFightLogBO();	
			logBO.setFightTime(log.getCreatedTime().getTime());
			if (log.getUserId().equals(userId)) {
				logBO.setLevel(log.getTargetLevel());
				logBO.setSystemHeroId(log.getTargetHeroId());		
				logBO.setFlag(log.getFlag());
				
				User targetUser = this.userService.getByUserId(log.getTargetUserId());
				if (targetUser == null)
					logBO.setTargetUserName("NPC-" + log.getTargetRank());
				else 
					logBO.setTargetUserName(targetUser.getName());
				
				logBO.setRank(log.getRank());
				logBO.setChangeRank(0);
				if (log.getFlag() > 0 && log.getRank() > log.getTargetRank()) 
					logBO.setChangeRank(log.getRank() - log.getTargetRank());				
			} else {
				// 被挑战
				logBO.setLevel(log.getLevel());
				logBO.setSystemHeroId(log.getSystemHeroId());
				
				User user = this.userService.getByUserId(log.getUserId());
				logBO.setTargetUserName(user.getName());
				logBO.setChangeRank(0);
								
				// 只有自己的排名比对方高，才会互换名次
				if (log.getFlag() > 0) {
					if (log.getRank() > log.getTargetRank())
						logBO.setChangeRank(log.getRank() - log.getTargetRank());
					logBO.setFlag(-1);
				} else {			
					logBO.setFlag(1);
					logBO.setRank(log.getTargetRank());
				}
			}
			
			if (logList.size() >= 100) 
				break;
				
			logList.add(logBO);
		}
		
		PkAction_getPkFightLogRes res = new PkAction_getPkFightLogRes();
		res.setLogList(logList);
		return res;
	}
	
	/**
	 * 根据挑战时间排序
	 * 
	 * @param logList
	 * @return
	 */
	private List<UserPkLog> sortByFightTime(List<UserPkLog> logList) {
		List<UserPkLog> collection = new ArrayList<UserPkLog>();
		collection.addAll(logList);
		
		Collections.sort(collection, new Comparator<UserPkLog>() {
			@Override
			public int compare(UserPkLog logI, UserPkLog logX) {				
				if (logI.getCreatedTime().getTime() > logX.getCreatedTime().getTime())
					return -1;
				else if (logI.getCreatedTime().getTime() < logX.getCreatedTime().getTime())
					return 1;
				return 0;
			}			
		});
		return collection;
	}

	/**
	 * 获取用户荣誉兑换奖励信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<PkMallBO> getUserPkMallInfo(String userId) {
		Date now = new Date();		
		List<UserPkMallLog> logList = this.userPkMallLogDao.getUserPkMallLogList(userId);
		List<PkMallBO> mallList = Lists.newArrayList();
		if (logList == null || logList.size() == 0) {
			List<SystemHonourExchange> honourList = this.systemHonourExchangeDao.getAllList();
			logList = Lists.newArrayList();
			for (SystemHonourExchange honour : honourList) {
				UserPkMallLog mallLog = new UserPkMallLog();
				mallLog.setMallId(honour.getMallId());
				mallLog.setDayBuyNum(0);
				mallLog.setTotalBuyNum(0);
				mallLog.setUserId(userId);
				mallLog.setCreatedTime(now);
				mallLog.setUpdatedTime(now);
				logList.add(mallLog);
				
				PkMallBO mallBO = createPkMallBO(mallLog);
				mallList.add(mallBO);
			}			
			this.userPkMallLogDao.addList(logList);
			
			return mallList;
		}
		
		// 是否需要刷新用户的兑换日志
		UserPkMallLog mallLog = logList.get(0);
		if (isNeedUpdate(mallLog)) {
			this.userPkMallLogDao.updateAllList(userId);
			logList = this.userPkMallLogDao.getUserPkMallLogList(userId);
		} 
		
		for (UserPkMallLog log : logList) {
			mallList.add(createPkMallBO(log));
		}		
		return mallList;
	}
	
	/**
	 * 判断是否需要刷新商店
	 * 
	 * @param userPkMallLog
	 * @return
	 */
	private boolean isNeedUpdate(UserPkMallLog userPkMallLog) {
		SystemConfigData configData = this.configDataDao.get(ConfigKey.pk_honour_mall_refresh_time);
		Date now = new Date();
		String timeStr = "24:00";
		
		if (configData != null)
			timeStr = configData.getConfigValue();
		
		Date startTime = DateUtils.str2Date(DateUtils.getYesterdayDate(now) + " " + timeStr + ":00");
		Date endTime = DateUtils.str2Date(DateUtils.getDate() + " " + timeStr + ":00");
		if (startTime.getTime() <= userPkMallLog.getUpdatedTime().getTime() 
				&& userPkMallLog.getUpdatedTime().getTime() <= endTime.getTime()) {
			return false;
		}
		
		return true;
	}
	
	private PkMallBO createPkMallBO(UserPkMallLog userPkMallLog) {
		PkMallBO mallBO = new PkMallBO();
		mallBO.setMallId(userPkMallLog.getMallId());
		mallBO.setDayBuyNum(userPkMallLog.getDayBuyNum());
		mallBO.setTotalBuyNum(userPkMallLog.getTotalBuyNum());
		
		return mallBO;
	}
	
	private final int NO_EXCHANGE_TIMES = 2001;
	/**
	 * 兑换商品
	 * 
	 * @param userId
	 * @param mallId
	 * @return
	 */
	public Map<String, Object> exchange(String userId, int mallId) {
		User user = this.userService.getByUserId(userId);
		SystemHonourExchange honourExchange = this.systemHonourExchangeDao.getSystemHonourExchange(mallId);
		if (honourExchange == null) 
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有相关兑换数据？？mallId=" + mallId);
		
		// 是否需要刷新用户的兑换日志
		List<UserPkMallLog> logList = this.userPkMallLogDao.getUserPkMallLogList(userId);
		if (logList == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "为什么会空？？userId=" + userId + ", mallId=" + mallId);	
		
		UserPkMallLog mallLog = logList.get(0);
		if (isNeedUpdate(mallLog))
			this.userPkMallLogDao.updateAllList(userId);
		
		mallLog = this.userPkMallLogDao.getUserPkMallLog(userId, mallId);		
		if (honourExchange.getDayBuyNum() != -1 && mallLog.getDayBuyNum() >= honourExchange.getDayBuyNum()) {
			throw new ServiceException(NO_EXCHANGE_TIMES, "该商品没有兑换次数,mallId=" + mallId);
		} else if (honourExchange.getTotalBuyNum() != -1 && mallLog.getTotalBuyNum() >= honourExchange.getTotalBuyNum()) {
			throw new ServiceException(NO_EXCHANGE_TIMES, "该商品没有兑换次数,mallId=" + mallId);			
		}
		
		// 背包检查
		String goodsIds = honourExchange.getToolType() + "," + honourExchange.getToolId() + "," + honourExchange.getToolNum();
		List<GoodsBeanBO> goodList = GoodsHelper.parseDropGoods(goodsIds);
		this.userService.checkBagLimit(userId, goodList);
		
		if (user.getHonour() < honourExchange.getNeedHonour())
			throw new ServiceException(SystemErrorCode.HONOUR_NOT_ENOUGH, "荣誉点不足");
		
		// 扣除荣誉点
		if (!userService.reduceHonour(userId, honourExchange.getNeedHonour(), GoodsUseType.REDUCE_PK_MALL_EXCHANGE))
			throw new ServiceException(SystemErrorCode.HONOUR_NOT_ENOUGH, "荣誉点不足");
		
		// 更新已购买
		if (honourExchange.getDayBuyNum() != -1) {
			this.userPkMallLogDao.update(userId, mallId, mallLog.getDayBuyNum() + 1, mallLog.getTotalBuyNum());
		} else {
			this.userPkMallLogDao.update(userId, mallId, mallLog.getDayBuyNum(), mallLog.getTotalBuyNum() + 1);
		}	
		
		// 发放奖励
		Map<String, Object> map = Maps.newConcurrentMap();
		map.put("dr", this.goodsDealService.sendGoods(userId, goodList, GoodsUseType.ADD_PK_MALL_EXCHANGE));
		map.put("hon", this.userService.getByUserId(userId).getHonour());
		return map;
	}
	
	/**
	 * 刷新商品购买
	 */
	public void refreshMall(String userId) {
//		int canRefreshTimes = this.configDataDao.getInt(ConfigKey.pk_mall_refresh_times, 3);
//		UserPkMallRefreshInfo refreshInfo = this.userPkMallRefreshInfoDao.getUserPkMallRefreshInfo(userId);
//		
//		if (refreshInfo.getRefreshTimes() >= canRefreshTimes)
//			throw new ServiceException(NO_REFRESH_TIME, "没有刷新次数");
//		
//		// TODO 刷新花费可能是公式，现在没有公式
//		User user = this.userService.getByUserId(userId);
//		int refreshCost = this.configDataDao.getInt(ConfigKey.pk_mall_refresh_cost, 50);
//		if (user.getHonour() < refreshCost)
//			throw new ServiceException(SystemErrorCode.HONOUR_NOT_ENOUGH, "荣誉点不足");
//		
//		// 扣除荣誉点
//		if (!userService.reduceHonour(userId, refreshCost, GoodsUseType.REDUCE_PK_MALL_REFRESH))
//			throw new ServiceException(SystemErrorCode.HONOUR_NOT_ENOUGH, "荣誉点不足");
//		
//		// 刷新商品购买状态，添加刷新次数
//		this.userPkMallRefreshInfoDao.update(userId, refreshInfo.getRefreshTimes() + 1);
//		this.userPkMallLogDao.update(userId, HAS_NOT_BUY);
	}
	
	private final int BUY_CHALLENGE_TIME_NOT_ENOUGH = 2001;
	private final int HAS_FREE_CHALLENGE_TIMES = 2002;
	private final int HAS_CHALLENGE_TIMES = 2003;
	/**
	 * 购买挑战次数
	 * 
	 * @param userId
	 */
	public void buyChallengeTimes(String userId) {
		UserPkInfo userPkInfo = this.userPkInfoDao.getUserPkInfo(userId);
		int canBuyTimes = this.configDataDao.getInt(ConfigKey.pk_cost_challenge_times, 5);
		
		if (userPkInfo.getBuyChallengeTimes() >= canBuyTimes)
			throw new ServiceException(BUY_CHALLENGE_TIME_NOT_ENOUGH, "已经没有购买次数");
		
		int freeTimes = this.configDataDao.getInt(ConfigKey.pk_challenge_times, 5);
		if (userPkInfo.getHasChallengeTimes() < freeTimes)
			throw new ServiceException(HAS_FREE_CHALLENGE_TIMES, "还有免费挑战次数");
		
		if (userPkInfo.getHasChallengeTimes() < freeTimes + userPkInfo.getBuyChallengeTimes())
			throw new ServiceException(HAS_CHALLENGE_TIMES, "还有可以挑战次数");
		
		// TODO 花费钻石 公式？？
		int cost = this.configDataDao.getInt(ConfigKey.pk_buy_challenge_time_cost, 50);
		cost = (userPkInfo.getBuyChallengeTimes() + 1) * cost;
		
		// 扣钱
		User user = this.userService.getByUserId(userId);
		if (user.getMoney() < cost)
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		if (!this.userService.reduceMoney(userId, cost, GoodsUseType.REDUCE_PK_BUY_CHALLENGE_TIMES))
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		// 更新购买的挑战次数
		this.userPkInfoDao.updateUserPkInfoBuyChallengeTimes(userId, userPkInfo.getBuyChallengeTimes() + 1);
	}
	
	/**
	 * 发放排名奖励
	 */
	public void sendRankReward() {
		List<UserPkInfo> infoList = this.userPkInfoDao.getRankList();
		if (infoList == null || infoList.size() == 0)
			return;
		
		List<String> paramList = Lists.newArrayList();
		for (UserPkInfo info : infoList) {
			SystemPkRankReward reward = this.systemPkRankRewardDao.getSystemPkRankReward(REWARD_TYPE_RANK, info.getRank());
			if (reward == null)
				continue;
			
			paramList.clear();
			paramList.add(Integer.toString(info.getRank()));
			mailService.send(info.getUserId(), paramList, SystemMailConstant.PK_RANK_REWARD_MAILID, reward.getRewards());			
		}		
	}

	/**
	 * 初始化假数据 
	 */
	@Override
	public void startup() throws Exception {
		int count = this.userPkInfoDao.getUserPkInfoCount();
		if (count == 0) {
			// 构造假数据
//			List<UserPkInfo> infoList = Lists.newArrayList();
//			for (int i = 1; i <= 1000; i++) {
//				UserPkInfo userPkInfo = new UserPkInfo();
//				userPkInfo.setUserId(IDGenerator.getID());
//				userPkInfo.setHasChallengeTimes(0);
//				userPkInfo.setBuyChallengeTimes(0);
//				userPkInfo.setIsReset(IS_NOT_RESET);
//				userPkInfo.setCreatedTime(new Date());
//				userPkInfo.setUpdatedTime(new Date());
//				userPkInfo.setRank(i);
//				userPkInfo.setHighestRank(i);
//				
//				infoList.add(userPkInfo);
//			}
//			
//			this.userPkInfoDao.addAll(infoList);
		}
	}

	@Override
	public void shutdown() throws Exception {
	}

	@Override
	public int cpOrder() {
		return 0;
	}

	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		if(handlerType.equals(ModuleEventConstant.USER_LOGINOUT_EVENT)){
        	String userId = baseModuleEvent.getStringValue("userId", "");
        	UserPkBattleBean bean = inBattleUser.remove(userId);
        	if (bean != null)
        		updateOfflinePkLog(bean, userId);
        	
        	LogSystem.warn("userId[" + userId + "]，登出游戏，inBattleUser中移出该用户.");
        }	
	}
	
	/**
	 * 用户掉线的时候扣掉他挑战的次数
	 * 
	 * @param userPkBattleBean
	 * @param userId
	 */
	private void updateOfflinePkLog(UserPkBattleBean userPkBattleBean, String userId) {
		// 更新挑战次数，记录挑战日志，发放点奖励
		UserPkInfo userPkInfo = this.userPkInfoDao.getUserPkInfo(userId);
		UserPkInfo targetPkInfo = this.userPkInfoDao.getUserPkInfo(userPkBattleBean.getTargetUserId());

		int rank = userPkInfo.getRank();
		int targetRank = targetPkInfo.getRank();
				
		int hasChallengeTimes = userPkInfo.getHasChallengeTimes();
		// 挑战失败
		this.userPkInfoDao.updateUserPkInfo(userId, hasChallengeTimes + 1, IS_NOT_RESET, userPkBattleBean.getAttackTime());			
		addPkBattleLog(userId, userPkBattleBean.getTargetUserId(), 0, rank, targetRank);
	}

	@Override
	public List<String> getHandlerType() {
		List<String> list = Lists.newArrayList();
		list.add(ModuleEventConstant.USER_LOGINOUT_EVENT);
		return list;
	}

	@Override
	public int order() {
		return 0;
	}
}
