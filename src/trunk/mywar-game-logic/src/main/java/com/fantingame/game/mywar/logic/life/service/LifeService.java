package com.fantingame.game.mywar.logic.life.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.plugin.IAppPlugin;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.model.SystemConfigData;
import com.fantingame.game.common.utils.RandomUtils;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.life.LifeAction_cancelHangupRes;
import com.fantingame.game.msgbody.client.life.LifeAction_createLifeBuilderRes;
import com.fantingame.game.msgbody.client.life.LifeAction_getHangupRewardListRes;
import com.fantingame.game.msgbody.client.life.LifeAction_getUserLifeInfoRes;
import com.fantingame.game.msgbody.client.life.LifeAction_hangupRes;
import com.fantingame.game.msgbody.client.life.LifeAction_reCreateLifeBuilderRes;
import com.fantingame.game.msgbody.client.life.LifeAction_receiveRewardRes;
import com.fantingame.game.msgbody.client.life.UserLifeInfoBO;
import com.fantingame.game.msgbody.notify.life.Life_pushWeatherInfoNotify;
import com.fantingame.game.msgbody.notify.life.WeatherInfoBO;
import com.fantingame.game.mywar.logic.chat.constant.ChatConstant;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.friend.model.UserFriendInfo;
import com.fantingame.game.mywar.logic.friend.service.FriendService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.hero.model.SystemHero;
import com.fantingame.game.mywar.logic.hero.model.UserHero;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.life.dao.UserHangupInfoDao;
import com.fantingame.game.mywar.logic.life.dao.UserLifeInfoDao;
import com.fantingame.game.mywar.logic.life.dao.WeatherInfoDao;
import com.fantingame.game.mywar.logic.life.dao.cache.SystemLifeConfigDaoCache;
import com.fantingame.game.mywar.logic.life.dao.cache.SystemLifeRewardDaoCache;
import com.fantingame.game.mywar.logic.life.dao.cache.SystemWeatherEffectDaoCache;
import com.fantingame.game.mywar.logic.life.dao.cache.SystemWeatherMapDaoCache;
import com.fantingame.game.mywar.logic.life.model.SystemLifeConfig;
import com.fantingame.game.mywar.logic.life.model.SystemLifeReward;
import com.fantingame.game.mywar.logic.life.model.SystemWeatherEffect;
import com.fantingame.game.mywar.logic.life.model.SystemWeatherMap;
import com.fantingame.game.mywar.logic.life.model.UserHangupInfo;
import com.fantingame.game.mywar.logic.life.model.UserLifeInfo;
import com.fantingame.game.mywar.logic.life.model.WeatherInfo;
import com.fantingame.game.mywar.logic.task.service.UserTaskService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.room.RestrictionsRule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class LifeService implements IAppPlugin {

	@Autowired
	private UserService userService;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private UserLifeInfoDao userLifeInfoDao;
	@Autowired
	private UserHangupInfoDao userHangupInfoDao;
	@Autowired
	private HeroService heroService;
	@Autowired
	private SystemLifeRewardDaoCache systemLifeRewardDaoCache;
	@Autowired
	private FriendService friendService;
	@Autowired
	private GoodsDealService goodsDealService;
	@Autowired
	private SystemLifeConfigDaoCache systemLifeConfigDaoCache;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private SystemWeatherMapDaoCache systemWeatherMapDaoCache;
	@Autowired
	private SystemWeatherEffectDaoCache systemWeatherEffectDaoCache;
	@Autowired
	private WeatherInfoDao weatherInfoDao;
	
	// 类别 1 挖矿 2 花圃 3 钓鱼
	private static final int CATEGORY_MINE = 1;
	private static final int CATEGORY_FLOWER = 2;
	private static final int CATEGORY_FISH = 3;
	
	// 0 未创建 1 未挂机 2 正在挂机
	private static final int NOT_CREATED = 0;
	private static final int NOT_HANGUP = 1;
	private static final int HANGUPING = 2;
	
	/**
	 * 获取用户生活技能相关信息
	 * 
	 * @param userId
	 * @return
	 */
	public LifeAction_getUserLifeInfoRes getUserLifeInfo(String userId) {
		User user = this.userService.getByUserId(userId);
		int openLevel = this.configDataDao.getInt(ConfigKey.life_open_level, 10);		
		
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "未到开放生活技能等级");		
		
		LifeAction_getUserLifeInfoRes res = new LifeAction_getUserLifeInfoRes();		
		List<UserLifeInfoBO> list = new ArrayList<UserLifeInfoBO>();
		for (int i = 1; i <= 3; i++) {
			UserLifeInfo userLifeInfo = this.userLifeInfoDao.getUserLifeInfo(userId, i);
			UserLifeInfoBO infoBO = new UserLifeInfoBO();
			infoBO.setUserId(userId);
			infoBO.setCategory(i);
			
			// 尚未建造
			if (userLifeInfo == null) {				
				infoBO.setStatus(NOT_CREATED);
				infoBO.setDrop(new CommonGoodsBeanBO());
				list.add(infoBO);
				
				continue;
			} 
			
			// 没有在挂机
			UserHangupInfo userHangupInfo = this.userHangupInfoDao.getUserHangupInfo(userId, i);
			if (userHangupInfo == null) {
				infoBO.setStatus(NOT_HANGUP);
				infoBO.setDrop(new CommonGoodsBeanBO());
				list.add(infoBO);
				
				continue;
			} 
			
			infoBO.setCategory(userHangupInfo.getCategory());
			infoBO.setStatus(HANGUPING);
			infoBO.setUserHeroId1(userHangupInfo.getUserHeroIdI());
			infoBO.setUserHeroId2(userHangupInfo.getUserHeroIdIi());
			infoBO.setUserHeroId3(userHangupInfo.getUserHeroIdIii());
					
			if (StringUtils.isNotBlank(userHangupInfo.getUserFriendId())) {
				infoBO.setUserFriendId(userHangupInfo.getUserFriendId());
				User userFriend = this.userService.getByUserId(userHangupInfo.getUserFriendId());
				infoBO.setUserFriendName(userFriend.getName());
				UserHeroBO userHeroBO = this.heroService.getUserTeamLeader(userHangupInfo.getUserFriendId());
				infoBO.setFriendSystemHeroId(userHeroBO.getSystemHeroId());
			}
					
			long hangupTime = configDataDao.getInt(ConfigKey.hangup_time, 8) * 60 * 1000;
			long endTime = userHangupInfo.getCreatedTime().getTime() + hangupTime;
			Date now = new Date();
					
			infoBO.setRemainderTime(endTime - now.getTime());
			if (now.getTime() >= endTime) 
				infoBO.setRemainderTime(0l);
					
			// 计算时间，几个小时
			int times = 0;
			if (now.getTime() < endTime) {
				times = (int) ((now.getTime() - userHangupInfo.getUpdatedTime().getTime()) / (60 * 1000));
			} else {
				times = (int) (((endTime + 1) - userHangupInfo.getUpdatedTime().getTime()) / (60 * 1000));
			}
			
			// 已经计算过收益就不再计算收益
			if (times <= 0) {
				CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
				drop.setGoodsList(GoodsHelper.parseDropGoods(userHangupInfo.getRewards()));
				infoBO.setDrop(drop);				
				list.add(infoBO);
				
				continue;
			}
					
			List<GoodsBeanBO> rewardList = calculateRewards(userHangupInfo, now, times);		
			String totalRewards = userHangupInfo.getRewards();
							
			Date updatedTime = new Date((userHangupInfo.getUpdatedTime().getTime() + times * 60 * 1000));
			CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
			if (StringUtils.isNotBlank(totalRewards)) {
				// 原有的奖励加上刚计算出来的奖励
				List<GoodsBeanBO> tempGoodsList = GoodsHelper.parseDropGoods(totalRewards);
				tempGoodsList.addAll(rewardList);

				// 道具去重
				List<GoodsBeanBO> goodsList = goodsSort(tempGoodsList);
				totalRewards = "";
				for (GoodsBeanBO good : goodsList) {
					if (StringUtils.isBlank(totalRewards)) {
						totalRewards = good.getGoodsType() + "," + good.getGoodsId() + "," + good.getGoodsNum();
					} else {
						totalRewards = totalRewards + "|" + good.getGoodsType() + "," + good.getGoodsId() + "," + good.getGoodsNum();
					}
				}								
				drop.setGoodsList(goodsList);
			} else {
				drop.setGoodsList(rewardList);
							
				for (GoodsBeanBO good : rewardList) {
					if (StringUtils.isBlank(totalRewards)) {
						totalRewards = good.getGoodsType() + "," + good.getGoodsId() + "," + good.getGoodsNum();
					} else {
						totalRewards = totalRewards + "|" + good.getGoodsType() + "," + good.getGoodsId() + "," + good.getGoodsNum();
					}
				}
			}					
							
			infoBO.setDrop(drop);
			this.userHangupInfoDao.updateUserHangupInfo(userId, userHangupInfo.getCategory(), updatedTime, totalRewards);				
			list.add(infoBO);
		}		
		
		res.setUserLifeInfoBOList(list);
		return res;
	}	
	
	/**
	 * 计算玩家当前收益
	 * 
	 * @param info
	 * @param now
	 * @return
	 */
	private List<GoodsBeanBO> calculateRewards(UserHangupInfo info, Date now, int times) {
		if (info.getUserHeroIdI() == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有英雄在干苦力？？userId[" + info.getUserId() + "]");
		
		List<SystemLifeReward> list = new ArrayList<SystemLifeReward>();
		list.addAll(getSystemLifeRewardList(info.getCategory(), info.getUserId(), info.getUserHeroIdI()));
		
		if (StringUtils.isNotBlank(info.getUserHeroIdIi()))
			list.addAll(getSystemLifeRewardList(info.getCategory(), info.getUserId(), info.getUserHeroIdIi()));
		
		if (StringUtils.isNotBlank(info.getUserHeroIdIii()))
			list.addAll(getSystemLifeRewardList(info.getCategory(), info.getUserId(), info.getUserHeroIdIii()));
		
		if (StringUtils.isNotBlank(info.getUserFriendId())) {
			UserHeroBO userFriendHeroBO = this.heroService.getUserTeamLeader(info.getUserFriendId());
			list.addAll(getSystemLifeRewardList(info.getCategory(), info.getUserFriendId(), userFriendHeroBO.getUserHeroId()));		
		}			
		
		List<GoodsBeanBO> goodsList = Lists.newArrayList();			
		for (SystemLifeReward systemLifeReward : list) {
			int toolNum = RandomUtils.getRandomNum(systemLifeReward.getMinNum(), systemLifeReward.getMaxNum());
			String goodsIds = systemLifeReward.getToolType() + "," + systemLifeReward.getToolId() + "," + toolNum * times;
				
			goodsList.addAll(GoodsHelper.parseDropGoods(goodsIds));
		}
		
		// 对道具进行去重
		goodsList = goodsSort(goodsList);		
		return goodsList;
	}

	private List<GoodsBeanBO> goodsSort(List<GoodsBeanBO> list) {
		// 对道具进行去重
		Map<String, GoodsBeanBO> goodsMap = Maps.newConcurrentMap();
		for (GoodsBeanBO good : list) {
			String key = good.getGoodsType() + "_" + good.getGoodsId();
			if (goodsMap.containsKey(key)) {
				GoodsBeanBO temp = goodsMap.get(key);
				temp.setGoodsNum(temp.getGoodsNum() + good.getGoodsNum());
			} else {
				goodsMap.put(key, good);
			}								
		}							
					
		List<GoodsBeanBO> goodsList = Lists.newArrayList();
		for (GoodsBeanBO good : goodsMap.values()) {
			goodsList.add(good);
		}
		return goodsList;
	}
	
	private List<SystemLifeReward> getSystemLifeRewardList(int category, String userId, String userHeroId) {
		UserHeroBO userHeroBO = heroService.getUserHeroBO(userId, userHeroId);
		SystemHero systemHero = heroService.getSystemHero(userHeroBO.getSystemHeroId());
		int quality = systemHero.getHeroColor();
		int level = userHeroBO.getLevel();
		
		List<SystemLifeReward> list = systemLifeRewardDaoCache.getSystemLifeRewardList(category, quality, level);
		if (list == null || list.size() == 0)
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有奖励哦！！！！数据没有配置？category=[" 
					+ category + "],quality=[" + quality + "],level=[" + level + "]");
		
		List<SystemLifeReward> returnList = new ArrayList<SystemLifeReward>();
		int random = RandomUtils.getRandomNum(1, 10000);
		for (SystemLifeReward reward : list) {
			if (reward.getLowerNum() <= random && reward.getUpperNum() >= random)
				returnList.add(reward);
		}
	
		if (returnList == null || returnList.size() == 0)
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有奖励哦！！！！数据没有配置？category=[" 
					+ category + "],quality=[" + quality + "],level=[" + level + "]");
		return returnList;
	}
	
	// 建造矿场花费类型 1 钻石 2 金币
	// private static final int CREATE_TYPE_MONEY = 1;
	// private static final int CREATE_TYPE_GOLD = 2;
	
	private static final int HAS_CREATED = 2001;
	private static final int A_OR_B = 2002;
	/**
	 * 建造建筑
	 * 
	 * @param userId
	 * @param category
	 * @param type
	 * @return
	 */
	public LifeAction_createLifeBuilderRes createLifeBuilder(String userId, int category) {
		if (category != CATEGORY_MINE && category != CATEGORY_FLOWER && category != CATEGORY_FISH)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不对，category = " + category);
		
		UserLifeInfo userLifeInfo = null;
		userLifeInfo = this.userLifeInfoDao.getUserLifeInfo(userId, category);
		if (userLifeInfo != null)
			throw new ServiceException(HAS_CREATED, "已经建造了该建筑");
		
		if (category != CATEGORY_FISH) {			
			// TODO 矿场与花圃只能二选一
			if (category == CATEGORY_MINE) {
				userLifeInfo = this.userLifeInfoDao.getUserLifeInfo(userId, CATEGORY_FLOWER);
			} else {
				userLifeInfo = this.userLifeInfoDao.getUserLifeInfo(userId, CATEGORY_MINE);
			}
			
			if (userLifeInfo != null)
				throw new ServiceException(A_OR_B, "花圃与矿场只能二选一");
		}
		
		UserLifeInfoBO infoBO = createBuilder(userId, category);		
		LifeAction_createLifeBuilderRes res = new LifeAction_createLifeBuilderRes();
		res.setUserLifeInfoBO(infoBO);
		return res;
	}
	
	private static final int IS_HANGUPING = 2002;
	/**
	 * 重新建造
	 * 
	 * @param userId
	 * @param category
	 * @param type
	 * @return
	 */
	public LifeAction_reCreateLifeBuilderRes reCreateLifeBuilder(String userId, int category) {
		if (category != CATEGORY_MINE && category != CATEGORY_FLOWER)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不对，category = " + category);
		
		UserLifeInfo userLifeInfo = this.userLifeInfoDao.getUserLifeInfo(userId, category);
		if (userLifeInfo != null)
			throw new ServiceException(HAS_CREATED, "新建的建筑已存在？？，category = " + category);
		
		if (category == CATEGORY_MINE) {
			UserHangupInfo hangupInfo = this.userHangupInfoDao.getUserHangupInfo(userId, CATEGORY_FLOWER);
			if (hangupInfo != null)
				throw new ServiceException(IS_HANGUPING, "重建的建筑挂机中。");
			
			// 删除原有的建筑
			this.userLifeInfoDao.deleteUserLifeInfo(userId, CATEGORY_FLOWER);
		} else {
			UserHangupInfo hangupInfo = this.userHangupInfoDao.getUserHangupInfo(userId, CATEGORY_MINE);
			if (hangupInfo != null)
				throw new ServiceException(IS_HANGUPING, "重建的建筑挂机中。");
			
			// 删除原有的建筑
			this.userLifeInfoDao.deleteUserLifeInfo(userId, CATEGORY_MINE);
		}
		
		UserLifeInfoBO infoBO = createBuilder(userId, category);		
		LifeAction_reCreateLifeBuilderRes res = new LifeAction_reCreateLifeBuilderRes();
		res.setUserLifeInfoBO(infoBO);
		return res;
	}

	/**
	 * 扣钱，建立建筑
	 * 
	 * @param userId
	 * @param category
	 * @param type
	 * @return
	 */
	private UserLifeInfoBO createBuilder(String userId, int category) {
		// 扣钱
		User user = this.userService.getByUserId(userId);
		SystemLifeConfig systemLifeConfig = this.systemLifeConfigDaoCache.getSystemLifeConfig(category);
		if (systemLifeConfig == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "数据有问题");
		
		if (systemLifeConfig.getMoney() > 0) {
			int money = systemLifeConfig.getMoney();
			if (user.getMoney() < money)
				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足。");
		
			if (!this.userService.reduceMoney(userId, money, GoodsUseType.REDUCE_CREATE_LIFE_BUILDER))
				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足。");	
		}		
					
		if (systemLifeConfig.getGold() > 0){
			int gold = systemLifeConfig.getGold();
			if (user.getGold() < gold)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足。");
					
			if (!this.userService.reduceGold(userId, gold, GoodsUseType.REDUCE_CREATE_LIFE_BUILDER))
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足。");
		}
				
		UserLifeInfo userLifeInfo = new UserLifeInfo();
		userLifeInfo.setUserId(userId);
		userLifeInfo.setCategory(category);
		userLifeInfo.setCreatedTime(new Date());
		userLifeInfo.setUpdatedTime(new Date());
		this.userLifeInfoDao.addUserLifeInfo(userLifeInfo);
				
		UserLifeInfoBO infoBO = new UserLifeInfoBO();
		infoBO.setUserId(userId);
		infoBO.setCategory(category);
		infoBO.setStatus(NOT_HANGUP);
		infoBO.setDrop(new CommonGoodsBeanBO());
		
		return infoBO;
	}

	private static final int HAS_NOT_CREATE = 2002;
	private static final int NOT_YOUR_FRIEND = 2003;
	private static final int HERO_HANGUPING = 2004;
	private static final int HERO_BE_DISBAND = 2005;
	private static final int HERO_IN_BATTLE = 2006;
	/**
	 * 开始挂机
	 * 
	 * @param userId
	 * @param category
	 * @param userHeroIdList
	 * @param userFriendId
	 * @return
	 */
	public LifeAction_hangupRes hangup(String userId, int category, List<String> userHeroIdList, String userFriendId) {
		if (category != CATEGORY_MINE && category != CATEGORY_FLOWER && category != CATEGORY_FISH)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不对，category = " + category);
		
		if (StringUtils.isBlank(userFriendId) && (userHeroIdList == null || userHeroIdList.size() == 0 || userHeroIdList.size() > 3))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不对，挂机英雄userHeroIdList为空或者英雄太多");
		
		User user = this.userService.getByUserId(userId);
		int needVipLevel = this.configDataDao.getInt(ConfigKey.hangup_num_four_vip_level, 6);
		if (userHeroIdList != null && userHeroIdList.size() == 3 && user.getVipLevel() < needVipLevel) {
			throw new ServiceException(SystemErrorCode.VIP_LEVEL_NOT_ENOUGH, "vip等级不足");
		}
		
		UserLifeInfo userLifeInfo = this.userLifeInfoDao.getUserLifeInfo(userId, category);
		if (userLifeInfo == null)
			throw new ServiceException(HAS_NOT_CREATE, "该建筑未建造。");
		
		Map<String, UserHangupInfo> userHeroIdMap = getHangupUserHeroIdMap(userId);
		Map<String, UserHero> userHeroInBattle = heroService.getBattleUserHeroMap(userId);
		if (userHeroIdList != null) {
			Map<String, String> map = Maps.newConcurrentMap();
			for (String userHeroId : userHeroIdList) {
				if (map.containsKey(userHeroId))
					throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，重复的英雄挂机");
				
				if (!this.heroService.isInBackbag(userId, userHeroId))
					throw new ServiceException(HERO_BE_DISBAND, "该英雄不在背包里");
				
				if (userHeroIdMap.containsKey(userHeroId))
					throw new ServiceException(HERO_HANGUPING, "该英雄已经在挂机，userHeroId = " + userHeroId);
				
				if (userHeroInBattle.containsKey(userHeroId)) 
					throw new ServiceException(HERO_IN_BATTLE, "这个英雄在阵上");
				
				map.put(userHeroId, userHeroId);
			}
		}
		
		UserHangupInfo hangupInfo = this.userHangupInfoDao.getUserHangupInfo(userId, category);
		if (hangupInfo != null)
			throw new ServiceException(IS_HANGUPING, "正在挂机中");
				
		hangupInfo = new UserHangupInfo();
		hangupInfo.setUserId(userId);
		hangupInfo.setCategory(category);		
		hangupInfo.setCreatedTime(new Date());
		hangupInfo.setUpdatedTime(new Date());
		
		if (StringUtils.isNotBlank(userFriendId)) {
			UserFriendInfo userFriendInfo = this.friendService.getUserFriendInfo(userId, userFriendId);
			if (userFriendInfo == null)
				throw new ServiceException(NOT_YOUR_FRIEND, "这个家伙不是你的好友");	
			
			UserHeroBO userFriendHeroBO = this.heroService.getUserTeamLeader(userFriendId);
			getSystemLifeRewardList(category, userFriendId, userFriendHeroBO.getUserHeroId());
			hangupInfo.setUserFriendId(userFriendId);
		}
		
		if (userHeroIdList != null) {
			if (userHeroIdList.size() == 1) {
				hangupInfo.setUserHeroIdI(userHeroIdList.get(0));
				getSystemLifeRewardList(category, userId, userHeroIdList.get(0));
			} else if (userHeroIdList.size() == 2) {
				hangupInfo.setUserHeroIdI(userHeroIdList.get(0));
				hangupInfo.setUserHeroIdIi(userHeroIdList.get(1));
				
				getSystemLifeRewardList(category, userId, userHeroIdList.get(0));
				getSystemLifeRewardList(category, userId, userHeroIdList.get(1));
			} else {
				hangupInfo.setUserHeroIdI(userHeroIdList.get(0));
				hangupInfo.setUserHeroIdIi(userHeroIdList.get(1));
				hangupInfo.setUserHeroIdIii(userHeroIdList.get(2));
				
				getSystemLifeRewardList(category, userId, userHeroIdList.get(0));
				getSystemLifeRewardList(category, userId, userHeroIdList.get(1));
				getSystemLifeRewardList(category, userId, userHeroIdList.get(2));
			}
		}
		this.userHangupInfoDao.addUserHangupInfo(hangupInfo);
		UserLifeInfoBO userLifeInfoBO = createUserLifeInfoBO(hangupInfo, HANGUPING, true);
		
		LifeAction_hangupRes res = new LifeAction_hangupRes();
		res.setUserLifeInfoBO(userLifeInfoBO);
		return res;
	}
	
	/**
	 * 获取已挂机的英雄idMap
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, UserHangupInfo> getHangupUserHeroIdMap(String userId) {
		Map<String, UserHangupInfo> map = Maps.newConcurrentMap();
		List<UserHangupInfo> hangupInfoList = this.userHangupInfoDao.getUserHangupInfoList(userId);
		for (UserHangupInfo info : hangupInfoList) {
			if (StringUtils.isNotBlank(info.getUserHeroIdI()))
				map.put(info.getUserHeroIdI(), info);
			
			if (StringUtils.isNotBlank(info.getUserHeroIdIi()))
				map.put(info.getUserHeroIdIi(), info);
			
			if (StringUtils.isNotBlank(info.getUserHeroIdIii()))
				map.put(info.getUserHeroIdIii(), info);
		}
	
		return map;
	}
	
	private UserLifeInfoBO createUserLifeInfoBO(UserHangupInfo userHangupInfo, int status, boolean fristAdd) {
		UserLifeInfoBO infoBO = new UserLifeInfoBO();
		infoBO.setUserId(userHangupInfo.getUserId());		
		infoBO.setCategory(userHangupInfo.getCategory());
		infoBO.setStatus(status);
		infoBO.setUserHeroId1(userHangupInfo.getUserHeroIdI());
		
		// 客户端说空的时候发0
		if (StringUtils.isNotBlank(userHangupInfo.getUserHeroIdIi())) {
			infoBO.setUserHeroId2(userHangupInfo.getUserHeroIdIi());
		} else {
			infoBO.setUserHeroId2("0");
		}
		
		if (StringUtils.isNotBlank(userHangupInfo.getUserHeroIdIii())) {
			infoBO.setUserHeroId3(userHangupInfo.getUserHeroIdIii());
		} else {
			infoBO.setUserHeroId3("0");
		}
		
		if (StringUtils.isNotBlank(userHangupInfo.getUserFriendId())) {
			infoBO.setUserFriendId(userHangupInfo.getUserFriendId());
			User userFriend = this.userService.getByUserId(userHangupInfo.getUserFriendId());
			infoBO.setUserFriendName(userFriend.getName());
			UserHeroBO userHeroBO = this.heroService.getUserTeamLeader(userHangupInfo.getUserFriendId());
			infoBO.setFriendSystemHeroId(userHeroBO.getSystemHeroId());
		} else {
			infoBO.setUserFriendId("0");
		}
		
		long hangupTime = configDataDao.getInt(ConfigKey.hangup_time, 8) * 60 * 1000;
		long endTime = userHangupInfo.getCreatedTime().getTime() + hangupTime;
		Date now = new Date();
		
		infoBO.setRemainderTime(endTime - now.getTime());
		if (now.getTime() >= endTime) 
			infoBO.setRemainderTime(0l);		
		
		if (fristAdd) {
			infoBO.setDrop(new CommonGoodsBeanBO());	
		} else {
			// 计算时间，几个小时
			int times = (int) ((endTime - userHangupInfo.getUpdatedTime().getTime()) / (60 * 1000));
			List<GoodsBeanBO> goodsList = calculateRewards(userHangupInfo, now, times);
			CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
			drop.setGoodsList(goodsList);
			infoBO.setDrop(drop);	
		}			
		return infoBO;
	}
	
	private static final int HAS_NOT_HANGUP = 2001;
	private static final int HAS_NOT_REWARD = 2002;
	/**
	 * 领取挂机奖励
	 * 
	 * @param userId
	 * @param category
	 * @return
	 */
	public LifeAction_receiveRewardRes receiveReward(String userId, int category) {
		if (category != CATEGORY_MINE && category != CATEGORY_FLOWER && category != CATEGORY_FISH)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不对，category = " + category);
		
		UserHangupInfo userHangupInfo = this.userHangupInfoDao.getUserHangupInfo(userId, category);
		if (userHangupInfo == null)
			throw new ServiceException(HAS_NOT_HANGUP, "没有在挂机");
		
		Date now = new Date();
		long hangupTime = this.configDataDao.getInt(ConfigKey.hangup_time, 8) * 60 * 1000;
		long endTime = userHangupInfo.getCreatedTime().getTime() + hangupTime;
		
		CommonGoodsBeanBO goodsBeanBO = new CommonGoodsBeanBO();
		// 计算时间，几个小时
		int times = 0;
		if (now.getTime() < endTime) {
			times = (int) ((now.getTime() - userHangupInfo.getUpdatedTime().getTime()) / (60 * 1000));
		} else {
			times = (int) (((endTime + 1) - userHangupInfo.getUpdatedTime().getTime()) / (60 * 1000));
		}
		
		List<GoodsBeanBO> goodslist = Lists.newArrayList();
		if (times > 0) {
			goodslist = calculateRewards(userHangupInfo, now, times);
		}
		
		if (userHangupInfo.getRewards() != null && userHangupInfo.getRewards().length() > 0) {
			List<GoodsBeanBO> list = GoodsHelper.parseDropGoods(userHangupInfo.getRewards());
			goodslist.addAll(list);
			goodslist = goodsSort(goodslist);
		}
		
		if (goodslist.size() == 0)
			throw new ServiceException(HAS_NOT_REWARD, "尚无奖励");
		
		// 检查背包
		this.userService.checkBag(userId, goodslist);
		
		UserLifeInfoBO userLifeInfoBO = new UserLifeInfoBO();
		userLifeInfoBO.setCategory(category);
		userLifeInfoBO.setUserId(userId);
		userLifeInfoBO.setDrop(new CommonGoodsBeanBO());
		
		// 更新或删除挂机信息
		if (now.getTime() >= endTime) {
			this.userHangupInfoDao.deleteUserHangupInfo(userId, category);
			userLifeInfoBO.setStatus(NOT_HANGUP);
			userLifeInfoBO.setRemainderTime((long) 0);
		} else {
			Date updatedTime = new Date((userHangupInfo.getUpdatedTime().getTime() + times * 60 * 1000));
			this.userHangupInfoDao.updateUserHangupInfo(userId, category, updatedTime, "");
			userLifeInfoBO.setStatus(HANGUPING);
			userLifeInfoBO.setRemainderTime(endTime - now.getTime());
		}		
				
		goodsBeanBO = goodsDealService.sendGoods(userId, goodslist, GoodsUseType.ADD_BY_LIFE_SKILL);
		LifeAction_receiveRewardRes res = new LifeAction_receiveRewardRes();
		res.setDrop(goodsBeanBO);
		res.setUserLifeInfoBO(userLifeInfoBO);
		
		return res;
	}
	
	/**
	 * 取消挂机
	 * 
	 * @param userId
	 * @param category
	 * @return
	 */
	public LifeAction_cancelHangupRes cancelHangup(String userId, int category) {
		if (category != CATEGORY_MINE && category != CATEGORY_FLOWER && category != CATEGORY_FISH)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不对，category = " + category);
		
		UserHangupInfo userHangupInfo = this.userHangupInfoDao.getUserHangupInfo(userId, category);
		if (userHangupInfo == null)
			throw new ServiceException(HAS_NOT_HANGUP, "亲，你没有在挂机哦！");
		
		this.userHangupInfoDao.deleteUserHangupInfo(userId, category);
		UserLifeInfoBO infoBO = new UserLifeInfoBO();
		infoBO.setUserId(userId);
		infoBO.setCategory(category);
		infoBO.setStatus(NOT_HANGUP);
		infoBO.setDrop(new CommonGoodsBeanBO());
		
		LifeAction_cancelHangupRes res = new LifeAction_cancelHangupRes();
		res.setUserLifeInfoBO(infoBO);
		return res;
	}

	/**
	 * 无用接口
	 * 
	 * @param userId
	 * @param category
	 * @param userHeroId
	 * @param userFriendId
	 * @return
	 */
	public LifeAction_getHangupRewardListRes getHangupRewardList(String userId, int category, String userHeroId, String userFriendId) {
		if (category != CATEGORY_MINE && category != CATEGORY_FLOWER && category != CATEGORY_FISH)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不对，category = " + category);
		
		UserHangupInfo userHangupInfo = this.userHangupInfoDao.getUserHangupInfo(userId, category);
		if (userHangupInfo != null)
			throw new ServiceException(IS_HANGUPING, "亲，你正在在挂机哦！");
		
		if ((StringUtils.isNotBlank(userHeroId) && StringUtils.isNotBlank(userFriendId)) 
				|| (StringUtils.isBlank(userHeroId) && StringUtils.isBlank(userFriendId)))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "只能二选一......或者都空了");
		
		List<SystemLifeReward> rewardList = null;
		if (StringUtils.isNotBlank(userHeroId)) {
			rewardList = getSystemLifeRewardList(category, userId, userHeroId);
		} else {
			UserHeroBO userHeroBO = this.heroService.getUserTeamLeader(userFriendId);
			rewardList = getSystemLifeRewardList(category, userFriendId, userHeroBO.getUserHeroId());
		}
		
		List<GoodsBeanBO> goodsList = Lists.newArrayList();
		if (rewardList != null && rewardList.size() > 0) {
			for (SystemLifeReward systemLifeReward : rewardList) {
				String goodsIds = systemLifeReward.getToolType() + "," + systemLifeReward.getToolId() + "," + systemLifeReward.getLowerNum();
				goodsList.addAll(GoodsHelper.parseDropGoods(goodsIds));
			}
		}
		
		LifeAction_getHangupRewardListRes res = new LifeAction_getHangupRewardListRes();
		res.setUserLifeRewardList(goodsList);
		return res;
	}
	
	private final static int TYPE_THUNDER = 1;
	private final static int TYPE_RAIN = 2;
	// private final static int TYPE_SNOW = 3;
	private final static int TYPE_ASSEMBLE = 4;
	
	private int CACHE_MAPID = -1;
	/**
	 * 更新天气信息 
	 */
	public void updateWeather() {
		WeatherInfo info = this.weatherInfoDao.getWeatherInfo();
		if (info != null)
			this.weatherInfoDao.deleteWeather(info.getMapId());
		
		List<SystemWeatherMap> mapList = this.systemWeatherMapDaoCache.getSystemWeatherMapList();
		List<SystemWeatherEffect> effectList = this.systemWeatherEffectDaoCache.getSystemWeatherEffectList();
		if (mapList.size() == 0 || effectList.size() == 0)
			return;
		
		int randomIndex = RandomUtils.getRandomNum(0, mapList.size() - 1);
		SystemWeatherMap weatherMap = mapList.get(randomIndex);
		info = new WeatherInfo();
		
		CACHE_MAPID = weatherMap.getMapId();
		int index = RandomUtils.getRandomNum(0, effectList.size() - 1);
		SystemWeatherEffect effect = effectList.get(index);
		info.setMapId(weatherMap.getMapId());
		info.setType(getWeatherType(weatherMap.getType()));
		info.setGroupId(effect.getGroupId());
		info.setCreatedTime(new Date());
		info.setContinueTime(getWeatherContinueTime());
		
		this.weatherInfoDao.addWeatherInfo(info);
		// TODO 推送
		Life_pushWeatherInfoNotify notify = new Life_pushWeatherInfoNotify();
		notify.setWeatherInfo(createWeatherInfoBO(info));
		
		RestrictionsRule rule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);
    	MsgDispatchCenter.disPatchUserRoomMsg("Life_pushWeatherInfo", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, notify, rule);
	}
	
	/**
	 * 获取当前天气信息
	 * 
	 * @return
	 */
	public WeatherInfoBO getCurrentWeatherInfoBO() {
		if (CACHE_MAPID == -1)
			return new WeatherInfoBO();
		
		WeatherInfo info = this.weatherInfoDao.getWeatherInfo(CACHE_MAPID);
		return createWeatherInfoBO(info);
	}
	
	private WeatherInfoBO createWeatherInfoBO(WeatherInfo info) {
		WeatherInfoBO infoBO = new WeatherInfoBO();
		infoBO.setMapId(info.getMapId());
		infoBO.setType(info.getType());
		infoBO.setGroupId(info.getGroupId());
		infoBO.setEndTime(info.getCreatedTime().getTime() + info.getContinueTime() * 60 * 1000);
		
		return infoBO;
	}
	 
	/**
	 * 获取天气类型
	 * 
	 * @param type
	 * @return
	 */
	private int getWeatherType(int type) {
		if (type != TYPE_ASSEMBLE)
			return type;
		
		return RandomUtils.getRandomNum(TYPE_THUNDER, TYPE_RAIN);
	}
	
	/**
	 * 获取天气持续时间
	 * 
	 * @return
	 */
	private int getWeatherContinueTime() {
		SystemConfigData configData = configDataDao.get(ConfigKey.weather_play_min_max_time);
		int minTime = 10;
		int maxTime = 30;
		if (configData != null) {
			String str = configData.getConfigValue();
			String[] strArr = str.split("-");
			
			if (strArr.length == 2) {
				minTime = Integer.parseInt(strArr[0]);
				maxTime = Integer.parseInt(strArr[1]);
			}			
		}
		
		return RandomUtils.getRandomNum(minTime, maxTime);		
	}

	@Override
	public void startup() throws Exception {
		Date now = new Date();
		WeatherInfo info = this.weatherInfoDao.getWeatherInfo();
		
		// 每两小时更新一次
		if (info == null || now.getTime() >= (info.getCreatedTime().getTime() + 2 * 3600 * 1000))
			updateWeather();
		else 
			CACHE_MAPID = info.getMapId();
	}

	@Override
	public void shutdown() throws Exception {
	}

	@Override
	public int cpOrder() {
		return 0;
	}
	
}
