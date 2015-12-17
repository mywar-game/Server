package com.fantingame.game.mywar.logic.prestige.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.prestige.InviteHeroBO;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.prestige.dao.cache.SystemInviteHeroDaoCache;
import com.fantingame.game.mywar.logic.prestige.dao.cache.SystemPrestigeLevelDaoCache;
import com.fantingame.game.mywar.logic.prestige.dao.cache.SystemPrestigeRewardsDaoCache;
import com.fantingame.game.mywar.logic.prestige.dao.cache.UserPrestigeRewardLogDaoCache;
import com.fantingame.game.mywar.logic.prestige.model.SystemInviteHero;
import com.fantingame.game.mywar.logic.prestige.model.SystemPrestigeRewards;
import com.fantingame.game.mywar.logic.prestige.model.UserPrestigeRewardLog;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class PrestigeService {
    @Autowired
	private SystemPrestigeLevelDaoCache systemPrestigeLevelDaoCache;
    @Autowired
    private SystemPrestigeRewardsDaoCache systemPrestigeRewardsDaoCache;
    @Autowired
    private UserService userService;
    @Autowired
    private UserPrestigeRewardLogDaoCache userPrestigeRewardLogDao;
    @Autowired
    private SystemInviteHeroDaoCache systemInviteHeroDaoCache;
    @Autowired
    private GoodsDealService goodsDealService;
    @Autowired
    private HeroService heroService;
    
    /**
     * 加声望
     * @param userId
     * @param addAmount
     * @param drop
     */
    public void addPrestige(String userId,int addAmount,int goodsUseType,CommonGoodsBeanBO drop){
    	User user = userService.getByUserId(userId);
		if (user == null) {
			String message = "更新用户声望失败,用户不存在.userId[" + userId + "], addAmount[" + addAmount + "]";
			throw new ServiceException(SystemConstant.FAIL_CODE, message);
		}
		//如果到了最高等级  则不再加经验
		if(user.getPrestigeLevel()>=systemPrestigeLevelDaoCache.getMaxLevel()){
			LogSystem.info("userId="+userId+",用户已经到达声望最高等级 不再加声望");
			return;
		}
//		SystemPrestigeLevel systemPrestigeLevel = this.systemPrestigeLevelDaoCache.getPrestigeLevel(addAmount+user.getPrestige());
		//int leveAddCount = 0;
//		if(systemPrestigeLevel.getLevel()>user.getPrestigeLevel()){
//			leveAddCount = systemPrestigeLevel.getLevel()-user.getPrestigeLevel();
//		}
	    //修改经验和等级
//		boolean success = this.userService.addPrestigeAndPrestigeLevel(userId, addAmount, leveAddCount, goodsUseType, drop);
//		if(!success){
//			throw new ServiceException(SystemConstant.FAIL_CODE, "加声望失败！");
//		}
    }
    
    private static final int LEVEL_LIMIT = 2001;
    private static final int HAS_REWARD = 2002;
    /**
     * 领取声望奖励
     * @param userId
     * @param id
     * @return
     */
    public CommonGoodsBeanBO rewardPrestige(String userId,int id){
    	User user = userService.getByUserId(userId);
    	SystemPrestigeRewards rewards = systemPrestigeRewardsDaoCache.getPrestigeRewardsById(id);
    	if(rewards==null){
    		throw new ServiceException(SystemConstant.FAIL_CODE, "id="+id+",不存在该id的配置");
    	}
    	//判断是否达到领取等级
    	if(user.getPrestigeLevel()<rewards.getLevel()){
    		throw new ServiceException(LEVEL_LIMIT, "声望等级不足");
    	}
    	//判断是否已经领取过了
    	if(userPrestigeRewardLogDao.isReward(userId, id)){
    		throw new ServiceException(HAS_REWARD, "已经领取过了");
    	}
    	UserPrestigeRewardLog log = new UserPrestigeRewardLog();
    	log.setUserId(userId);
    	log.setId(id);
    	log.setCreatedTime(new Date());
    	//给奖励
    	if(userPrestigeRewardLogDao.addUserPrestigeRewardLog(log)){
        	return goodsDealService.sendGoods(userId, GoodsType.Hero.intValue+","+rewards.getRewards()+",1", GoodsUseType.ADD_PRESTIGE_REWARDS);
    	}else{
    		ServiceException e = new ServiceException(SystemConstant.FAIL_CODE, "添加日志异常！！");
    		LogSystem.error(e, "");
    		throw e;
    	}
    }
    
    /**
     * 获取用户已领取的声望奖励id列表  用户登录总推送
     * @param userId
     * @return
     */
    public List<Integer> getUserHaveGetRewardsIds(String userId){
    	List<Integer> result = Lists.newArrayList();
    	List<UserPrestigeRewardLog> list = userPrestigeRewardLogDao.getUserPrestigeRewardLogList(userId);
    	if(list!=null&&list.size()>0){
    		for(UserPrestigeRewardLog rewardLog:list){
    			result.add(rewardLog.getId());
    		}
    	}
    	return result;
    }
    
    /**
     * 查看用户的酒馆信息
     * 
     * @param userId
     * @return
     */
    public List<InviteHeroBO> getInviteHeroInfo(String userId) {
    	List<InviteHeroBO> list = Lists.newArrayList();
    	User user = this.userService.getByUserId(userId);
    	
    	List<SystemInviteHero> inviteList = this.systemInviteHeroDaoCache.getSystemInviteHeroList(user.getCamp());
    	Map<String, String> tempMap = Maps.newConcurrentMap();
    	
    	Map<String, String> nameMap = this.heroService.getUserHeroNameMap(userId);
    	for (SystemInviteHero hero : inviteList) {
    		InviteHeroBO heroBO = new InviteHeroBO();
    		heroBO.setSystemHeroId(hero.getSystemHeroId());
    		String heroName = this.heroService.getNewHeroName(userId, hero.getSystemHeroId(), nameMap, tempMap);
    		heroBO.setHeroName(heroName);
    		tempMap.put(heroName, heroName);
    		
    		list.add(heroBO);
    	}    	
    	
    	return list;
    }
    
    // 英雄状态 1 在背包  0 在酒馆
    // private static final int HERO_STATUS_1 = 1;
    // 不可邀请 0, 1 可邀请
    // private static final int INVITE_STATUS_0 = 0;
    
   //  private static final int PRESTIGE_NOT_ENOUGH = 2001;
    // private static final int HERO_HAS_BACKPACK = 2002;
    // private static final int PRESTIGE_LEVEL_NOT_ENOUGH = 2003;
    // private static final int CAN_NOT_BE_INVITE = 2005;    

    private static final int CAMP_NOT_MATE = 2004;
    /**
     * 声望招募英雄
     * 
     * @param userId
     * @param systemHeroId
     * @return
     */
    public Map<String, Object> inviteHero(String userId, int systemHeroId, String heroName) {
    	User user = userService.getByUserId(userId);
    	SystemInviteHero systemInviteHero = this.systemInviteHeroDaoCache.getSystemInviteHero(systemHeroId);
    	
    	if (systemInviteHero == null) 
    		throw new ServiceException(SystemConstant.FAIL_CODE, "没有配置该英雄？？userId[" + userId + "],systemHeroId[" + systemHeroId + "]");    	

    	if (systemInviteHero.getCamp() != 0 && user.getCamp() != systemInviteHero.getCamp())
    		throw new ServiceException(CAMP_NOT_MATE, "阵营不匹配");  	
    	
    	// 背包检查
    	List<GoodsBeanBO> list = new ArrayList<GoodsBeanBO>();
    	GoodsBeanBO goodsBeanBO = new GoodsBeanBO();
    	goodsBeanBO.setGoodsId(systemInviteHero.getSystemHeroId());
    	goodsBeanBO.setGoodsType(GoodsType.Hero.intValue);
    	goodsBeanBO.setGoodsNum(1);
    	list.add(goodsBeanBO);
    	this.userService.checkBag(userId, list);

    	Map<String, Object> map = new HashMap<String, Object>();    	
    	// 各种XX条件判断    	
    	if (user.getGold() < systemInviteHero.getNeedGold())
    		throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
    	
    	if (user.getLevel() < systemInviteHero.getLevel())
    		throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足");
    	
    	// 扣金币
    	if (!this.userService.reduceGold(userId, systemInviteHero.getNeedGold(), GoodsUseType.REDUCE_PRESTIGE_INVITE)) 
    		throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
    	
    	CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
    	this.heroService.addHero(userId, systemHeroId, false, heroName, GoodsUseType.ADD_PRESTIGE_INVITE, drop);
    	
    	user = this.userService.getByUserId(userId);    	
    	map.put("dr", drop);
    	map.put("gd", user.getGold());
    	map.put("mon", user.getMoney());
    	return map;
    }
}
