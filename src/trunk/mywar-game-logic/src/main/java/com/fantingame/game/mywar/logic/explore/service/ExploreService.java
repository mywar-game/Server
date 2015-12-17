package com.fantingame.game.mywar.logic.explore.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.RandomUtils;
import com.fantingame.game.msgbody.client.explore.ExploreAction_autoRefreshRes;
import com.fantingame.game.msgbody.client.explore.ExploreAction_exploreRes;
import com.fantingame.game.msgbody.client.explore.ExploreAction_refreshMapRes;
import com.fantingame.game.msgbody.client.explore.UserExploreInfoBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.mywar.logic.activity.constant.ActivityTaskConstant;
import com.fantingame.game.mywar.logic.activity.service.ActivityService;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.explore.dao.UserExploreInfoDao;
import com.fantingame.game.mywar.logic.explore.dao.cache.SystemExploreExchangeDaoCache;
import com.fantingame.game.mywar.logic.explore.dao.cache.SystemExploreMapDaoCache;
import com.fantingame.game.mywar.logic.explore.dao.cache.SystemExploreRewardDaoCache;
import com.fantingame.game.mywar.logic.explore.model.SystemExploreExchange;
import com.fantingame.game.mywar.logic.explore.model.SystemExploreMap;
import com.fantingame.game.mywar.logic.explore.model.SystemExploreReward;
import com.fantingame.game.mywar.logic.explore.model.UserExploreInfo;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;

/**
 * 探索
 * 
 * @author yezp
 */
public class ExploreService {
	
	@Autowired
	private UserExploreInfoDao userExploreInfoDao;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private SystemExploreMapDaoCache systemExploreMapDaoCache;
	@Autowired
	private SystemExploreExchangeDaoCache systemExploreExchangeDaoCache;
	@Autowired
	private SystemExploreRewardDaoCache systemExploreRewardDaoCache;
	@Autowired
	private UserService userService;
	@Autowired
	private GoodsDealService goodsDealService;
	@Autowired
	private HeroService heroService;
	@Autowired
	private ActivityService activityService;
	
	/**
	 * 获取用户探索信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserExploreInfoBO getUserExploreInfo(String userId) {
		User user = this.userService.getByUserId(userId);
		int openLevel = this.configDataDao.getInt(ConfigKey.explore_open_level, 5);
		
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "未到探索开放等级");
		
		Date now = new Date();
		UserExploreInfoBO infoBO = null;
		int freeTimes = this.configDataDao.getInt(ConfigKey.free_explore_times, 5);
		UserExploreInfo userExploreInfo = this.userExploreInfoDao.getUserExploreInfo(userId);
		if (userExploreInfo == null) {
			SystemExploreMap systemExploreMap = randomExploreMap(user.getCamp());			
			userExploreInfo = new UserExploreInfo();
			userExploreInfo.setUserId(userId);
			userExploreInfo.setIntegral(0);
			userExploreInfo.setMapId(systemExploreMap.getMapId());
			userExploreInfo.setExploreTimes(freeTimes);
			userExploreInfo.setCreatedTime(now);
			userExploreInfo.setUpdatedTime(now);
			
			this.userExploreInfoDao.addUserExploreInfo(userExploreInfo);
			return createUserExploreInfoBO(userExploreInfo.getMapId(), userExploreInfo.getIntegral(), userExploreInfo.getExploreTimes());			
		}
		
		// 第二天要刷新
		if (!DateUtils.isSameDay(userExploreInfo.getUpdatedTime(), now)) {			
			SystemExploreMap systemExploreMap = randomExploreMap(user.getCamp());
			this.userExploreInfoDao.updateUserExploreInfo(userId, systemExploreMap.getMapId(), freeTimes, now);
			return createUserExploreInfoBO(systemExploreMap.getMapId(), userExploreInfo.getIntegral(), freeTimes);
		}
		
		infoBO = createUserExploreInfoBO(userExploreInfo.getMapId(), userExploreInfo.getIntegral(), userExploreInfo.getExploreTimes());	
		return infoBO;
	}

	/**
	 * 午夜推送用户探索信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserExploreInfoBO pushExploreInfo(String userId) {
		User user = this.userService.getByUserId(userId);
		int openLevel = this.configDataDao.getInt(ConfigKey.explore_open_level, 5);
		
		if (user.getLevel() < openLevel)
			return null;
		
		Date now = new Date();
		int freeTimes = this.configDataDao.getInt(ConfigKey.free_explore_times, 5);
		UserExploreInfo userExploreInfo = this.userExploreInfoDao.getUserExploreInfo(userId);
		if (userExploreInfo == null) {
			return null;			
		}
		
		// 第二天要刷新
		if (!DateUtils.isSameDay(userExploreInfo.getUpdatedTime(), now)) {
			SystemExploreMap systemExploreMap = randomExploreMap(user.getCamp());
			this.userExploreInfoDao.updateUserExploreInfo(userId, systemExploreMap.getMapId(), freeTimes, now);
			return createUserExploreInfoBO(systemExploreMap.getMapId(), userExploreInfo.getIntegral(), freeTimes);
		}
		
		return null;
	}
	
	private UserExploreInfoBO createUserExploreInfoBO(int mapId, int integral, int exploreTimes) {
		UserExploreInfoBO infoBO = new UserExploreInfoBO();
		infoBO.setMapId(mapId);
		infoBO.setIntegral(integral);
		infoBO.setExploreTimes(exploreTimes);
		
		return infoBO;
	}
	
	/**
	 * 随机刷新地图
	 * 
	 * @return
	 */
	private SystemExploreMap randomExploreMap(int camp) {
		List<SystemExploreMap> exploreMapList = this.systemExploreMapDaoCache.getSystemExploreMapList();
		if (exploreMapList == null || exploreMapList.size() == 0)
			throw new ServiceException(SystemConstant.FAIL_CODE, "数据错误，没有探索的地图信息");
		
		int random = RandomUtils.getRandomNum(1, 10000);		
		// 每次刷新与上次不同
//		if (mapId != -1) {
//			SystemExploreMap exploreMap = this.systemExploreMapDaoCache.getSystemExploreMap(mapId);
//			while (true) {
//				if (!(exploreMap.getLowerNum() >= random && exploreMap.getUpperNum() <= random))
//					break;
//				
//				random = RandomUtils.getRandomNum(1, 10000);
//			}
//		}
		
		// 3 为公共阵营
		for (SystemExploreMap map : exploreMapList) {
			if (map.getLowerNum() <= random && map.getUpperNum() >= random
					&& (map.getCamp() == 3 || camp == map.getCamp())) 
				return map;
		}
		LogSystem.debug("概率没配置？？random:" + random);
		return null;
	}
	
	private final static int INTEGRAL_NOT_ENOUGH = 2001;
	private final static int HAS_THE_SAME_HERO = 2002;
	private final static int CAMP_NOT_SAME = 2003;
	/**
	 * 兑换英雄
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public Map<String, Object> exchange(String userId, int systemHeroId) {
		if (systemHeroId == 0)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，谢犊子又不传参数过来");
		
		Map<String, Object> map = new HashMap<String, Object>();
		User user = this.userService.getByUserId(userId);
			
		SystemExploreExchange exploreExchange = this.systemExploreExchangeDaoCache.getSystemExploreExchange(systemHeroId);
		if (exploreExchange == null)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "没有该英雄，数据问题？systemHeroId[" + systemHeroId + "]"); 
		
		Map<Integer, Integer> userSystemHeroIdMap = this.heroService.getUserSystemHeroIdMap(userId);
		if (userSystemHeroIdMap.containsKey(exploreExchange.getSystemHeroId()))
			throw new ServiceException(HAS_THE_SAME_HERO, "你已拥有该英雄");
		
		// 背包检查
		List<GoodsBeanBO> list = new ArrayList<GoodsBeanBO>();
		GoodsBeanBO goodsBeanBO = new GoodsBeanBO();
		goodsBeanBO.setGoodsId(exploreExchange.getSystemHeroId());
		goodsBeanBO.setGoodsType(GoodsType.Hero.intValue);
		goodsBeanBO.setGoodsNum(1);
		list.add(goodsBeanBO);
		this.userService.checkBag(userId, list);
		
		// 条件判断	
		if (user.getCamp() != exploreExchange.getCamp())
			throw new ServiceException(CAMP_NOT_SAME, "阵营不符");
			
		if (user.getVipLevel() < exploreExchange.getNeedVipLevel())
			throw new ServiceException(SystemErrorCode.VIP_LEVEL_NOT_ENOUGH, "Vip等级不足");
		
		if (user.getLevel() < exploreExchange.getNeedLevel())
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足");		
		
		UserExploreInfo userExploreInfo = this.userExploreInfoDao.getUserExploreInfo(userId);
		if (userExploreInfo.getIntegral() < exploreExchange.getNeedIntegral())
			throw new ServiceException(INTEGRAL_NOT_ENOUGH, "探索积分不足");
		
		// 扣除用户的探索积分
		if (!this.userExploreInfoDao.reduceIntegral(userId, exploreExchange.getNeedIntegral())) {
			throw new ServiceException(INTEGRAL_NOT_ENOUGH, "探索积分不足");
		}
		
		// 发奖励
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, GoodsType.Hero.intValue + "," + exploreExchange.getSystemHeroId() + ",1", GoodsUseType.ADD_EXPLORE_INTEGRAL_EXCHANGE);
		userExploreInfo = this.userExploreInfoDao.getUserExploreInfo(userId);
		map.put("dr", drop);
		map.put("integral", userExploreInfo.getIntegral());
		
		return map;
	}
	
	/**
	 * 刷新地图
	 * 
	 * @param userId
	 * @return
	 */
	public ExploreAction_refreshMapRes refreshMap(String userId) {
		UserExploreInfo info = this.userExploreInfoDao.getUserExploreInfo(userId);
		if (info.getExploreTimes() <= 0)
			throw new ServiceException(EXPLORE_NO_TIMES, "探索次数已用完");
		
		User user = this.userService.getByUserId(userId);
		int cost =this.configDataDao.getInt(ConfigKey.explore_refresh_cost, 10);
		SystemExploreMap exploreMap = randomExploreMap(user.getCamp());
		
		if (user.getMoney() < cost)
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		if (!this.userService.reduceMoney(userId, cost, GoodsUseType.REDUCE_EXPLORE_REFRESH))
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		int score = this.configDataDao.getInt(ConfigKey.refresh_map_get_score, 10);		
		boolean success = this.userExploreInfoDao.updateExploreMap(userId, exploreMap.getMapId(), score);
		if (!success)
			throw new ServiceException(SystemConstant.FAIL_CODE, "userId[" + userId + "]更新探索地图失败");
		
		user = this.userService.getByUserId(userId);
		ExploreAction_refreshMapRes res = new ExploreAction_refreshMapRes();
		res.setMapId(exploreMap.getMapId());
		res.setIntegral(info.getIntegral());
		res.setMoney(user.getMoney());
		return res;
	}
	
	private final static int EXPLORE_NO_TIMES =2001;
	/**
	 * 探索
	 * 
	 * @param userId
	 * @param type
	 * @return
	 */
	public ExploreAction_exploreRes explore(String userId, int type) {
		User user = this.userService.getByUserId(userId);
		UserExploreInfo info = this.userExploreInfoDao.getUserExploreInfo(userId);
		int freeTimes = this.configDataDao.getInt(ConfigKey.free_explore_times, 5);
		
		Date now = new Date();
		if (!DateUtils.isSameDay(info.getUpdatedTime(), now)) {
			this.userExploreInfoDao.updateUserExploreInfo(userId, info.getMapId(), freeTimes, now);
			info = this.userExploreInfoDao.getUserExploreInfo(userId);
		}
		
		if (info.getExploreTimes() <= 0)
			throw new ServiceException(EXPLORE_NO_TIMES, "探索次数已用完");
		
		List<SystemExploreReward> list = this.systemExploreRewardDaoCache.getSystemExploreRewardList(info.getMapId());
		SystemExploreReward exploreReward = null;
		for (SystemExploreReward reward : list) {
			if (reward.getType() == type && (user.getLevel() <= reward.getMaxLevel() && user.getLevel() >= reward.getMinLevel())) {
				exploreReward = reward;
				break;
			}				
		}
		
		int remainderExploreTimes = info.getExploreTimes() - 1;
		boolean success = this.userExploreInfoDao.updateUserExploreInfo(userId, info.getMapId(), remainderExploreTimes, now);
		if (!success)
			throw new ServiceException(SystemConstant.FAIL_CODE, "更新用户探索信息失败");
		
		// 策划说：用户一辈子都只能得到相同的一个英雄
		CommonGoodsBeanBO goodsBeanBO = new CommonGoodsBeanBO();
		if (exploreReward != null && exploreReward.getRewards() != null && !exploreReward.getRewards().equals("")) {
			List<GoodsBeanBO> gainGoodsList = gainExploreGoods(userId, exploreReward.getRewards());
			if (gainGoodsList.size() != 0)
				goodsBeanBO = this.goodsDealService.sendGoods(userId, gainGoodsList, GoodsUseType.ADD_BY_EXPLORE);
		}
		
		// 探索完刷新一次 
		SystemExploreMap exploreMap = randomExploreMap(user.getCamp());
		success = this.userExploreInfoDao.updateExploreMap(userId, exploreMap.getMapId(), 0);
		if (!success)
			throw new ServiceException(SystemConstant.FAIL_CODE, "userId[" + userId + "]更新探索地图失败");
		
		this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_EXPLORE, 1);
		ExploreAction_exploreRes res = new ExploreAction_exploreRes();	
		res.setDrop(goodsBeanBO);
		res.setExploreTimes(remainderExploreTimes);
		res.setMapId(exploreMap.getMapId());
		return res;
	}
	
	/**
	 * 获得的探索物品
	 * 
	 * @param userId
	 * @return
	 */
	private List<GoodsBeanBO> gainExploreGoods(String userId, String rewards) {
		List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(rewards);
		List<GoodsBeanBO> gainGoodsList = new ArrayList<GoodsBeanBO>();
		
		for (GoodsBeanBO bo : goodsList) {
			if (bo.getGoodsType() == GoodsType.Hero.intValue) {
				UserHeroBO userHeroBO = this.heroService.getUserHeroBO(userId, bo.getGoodsId());
				if (userHeroBO != null) 
					gainGoodsList.add(bo);				
			}
		}
		
		for (GoodsBeanBO bo : gainGoodsList) {
			goodsList.remove(bo);
		}
		
		return goodsList;
	}

	private final static int STOP = 1;
	private final static int CONTINUE = 0;
	private final static int HAS_ALL_EXPLORE_HERO = 2002;
	/**
	 * 自动刷新
	 * 
	 * @param userId
	 * @return
	 */
	public ExploreAction_autoRefreshRes autoRefresh(String userId) {
		UserExploreInfo info = this.userExploreInfoDao.getUserExploreInfo(userId);
		if (info.getExploreTimes() <= 0)
			throw new ServiceException(EXPLORE_NO_TIMES, "探索次数已用完");
		
		User user = this.userService.getByUserId(userId);
		int cost =this.configDataDao.getInt(ConfigKey.explore_refresh_cost, 10);		
		
		Map<Integer, Integer> userSystemHeroIdMap = this.heroService.getUserSystemHeroIdMap(userId);
		Map<Integer, Integer> exploreSystemHeroIdMap = this.systemExploreRewardDaoCache.getMapAndSystemHeroId();
		
		boolean hasAllHero = true;
		for (Integer systemHeroId : exploreSystemHeroIdMap.keySet()) {
			if (!userSystemHeroIdMap.containsKey(systemHeroId)) {
				hasAllHero = false;
				break;
			}
		}
		
		if (hasAllHero)
			throw new ServiceException(HAS_ALL_EXPLORE_HERO, "这个用户屌爆了，探索的所有都已拥有。");
		
		if (user.getMoney() < cost)
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		SystemExploreMap map = null;
		UserExploreInfo userInfo = this.userExploreInfoDao.getUserExploreInfo(userId);
		int score = this.configDataDao.getInt(ConfigKey.refresh_map_get_score, 10);

		// 扣除刷新的钻石
		if (!this.userService.reduceMoney(userId, cost, GoodsUseType.REDUCE_EXPLORE_AUTO_REFRESH))
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");	
			
		map = randomExploreMap(user.getCamp());
		List<GoodsBeanBO> list = this.systemExploreRewardDaoCache.getSystemExploreGoodsBeanBOList(map.getMapId());			
		boolean getIt = false;
		for (GoodsBeanBO good : list) {
			if (good.getGoodsType() != GoodsType.Hero.intValue)
				continue;
			
//				// 不同阵营的不刷新
//				SystemHero systemHero = this.heroService.getSystemHero(good.getGoodsId());
//				if (systemHero.getNationId() != user.getCamp())
//					continue;
				
			if (!userSystemHeroIdMap.containsKey(good.getGoodsId())) {
				getIt = true;
				break;
			}
		}			

		ExploreAction_autoRefreshRes res = new ExploreAction_autoRefreshRes();
		res.setStop(CONTINUE);
		if (getIt)
			res.setStop(STOP);	
		
		if (!this.userExploreInfoDao.updateExploreMap(userId, map.getMapId(), score))
			throw new ServiceException(SystemConstant.FAIL_CODE, "更新用户探索信息失败");
		
		user = this.userService.getByUserId(userId);
		res.setMapId(map.getMapId());
		res.setCost(cost);
		res.setMon(user.getMoney());
		res.setIntegral(userInfo.getIntegral());
		return res;		
	}
	
}
