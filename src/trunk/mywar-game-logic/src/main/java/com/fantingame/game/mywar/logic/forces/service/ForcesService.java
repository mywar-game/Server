package com.fantingame.game.mywar.logic.forces.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.NumberUtils;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.RandomUtils;
import com.fantingame.game.log.service.LogService;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import com.fantingame.game.msgbody.client.forces.ForcesAction_attackRes;
import com.fantingame.game.msgbody.client.forces.ForcesAction_openBattleBoxRes;
import com.fantingame.game.msgbody.client.forces.ForcesAction_reliveRes;
import com.fantingame.game.msgbody.client.forces.UserForcesBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.mywar.logic.achievement.check.IAchievementChecker;
import com.fantingame.game.mywar.logic.achievement.constant.AchievementConstant;
import com.fantingame.game.mywar.logic.achievement.service.AchievementService;
import com.fantingame.game.mywar.logic.activity.constant.ActivityTaskConstant;
import com.fantingame.game.mywar.logic.activity.service.ActivityService;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.equip.service.EquipService;
import com.fantingame.game.mywar.logic.forces.bean.UserBattleBean;
import com.fantingame.game.mywar.logic.forces.dao.UserForcesDao;
import com.fantingame.game.mywar.logic.forces.dao.cache.SystemBigForcesDaoCache;
import com.fantingame.game.mywar.logic.forces.dao.cache.SystemForcesDaoCache;
import com.fantingame.game.mywar.logic.forces.dao.cache.SystemForcesDropToolDaoCache;
import com.fantingame.game.mywar.logic.forces.dao.cache.SystemForcesMonsterDaoCache;
import com.fantingame.game.mywar.logic.forces.dao.cache.SystemMonsterDaoCache;
import com.fantingame.game.mywar.logic.forces.model.SystemBigForces;
import com.fantingame.game.mywar.logic.forces.model.SystemForces;
import com.fantingame.game.mywar.logic.forces.model.SystemForcesDropTool;
import com.fantingame.game.mywar.logic.forces.model.SystemForcesMonster;
import com.fantingame.game.mywar.logic.forces.model.UserForces;
import com.fantingame.game.mywar.logic.friend.service.FriendService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.hero.constant.LogType;
import com.fantingame.game.mywar.logic.hero.model.UserHero;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.life.model.UserHangupInfo;
import com.fantingame.game.mywar.logic.life.service.LifeService;
import com.fantingame.game.mywar.logic.lucky.service.LuckyService;
import com.fantingame.game.mywar.logic.task.check.ITaskHitChecker;
import com.fantingame.game.mywar.logic.task.constant.TaskLibraryType;
import com.fantingame.game.mywar.logic.task.service.UserTaskService;
import com.fantingame.game.mywar.logic.tool.constant.ToolType;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.constant.UserConstant;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ForcesService implements ModuleEventHandler{
	@Autowired
	private SystemForcesDaoCache systemForcesDaoCache;
	@Autowired
	private SystemForcesDropToolDaoCache systemForcesDropToolDaoCache;
	@Autowired
	private SystemForcesMonsterDaoCache systemForcesMonsterDaoCache;
	@Autowired
	private SystemMonsterDaoCache systemMonsterDaoCache;
	@Autowired
	private UserForcesDao userForcesDao;
	@Autowired
	private UserService userSerivce;
	@Autowired
	private GoodsDealService goodsDealService;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private SystemBigForcesDaoCache systemBigForcesDaoCache;
	@Autowired
	private HeroService heroService;
	@Autowired 
	private LifeService lifeService;
	@Autowired
	private EquipService equipService;
	@Autowired
	private ToolService toolService;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private LuckyService luckyService;
	@Autowired
	private LogService logService;
	@Autowired
	private FriendService friendService;
	@Autowired
	private AchievementService achievementService;
	@Autowired
	private ActivityService activityService;
	
	//怪物
	public static final int FORCE_category_MONSTER  = 1;
	//boss
	public static final int FORCE_category_BOSS  = 2;
	//采集农场类型
	public static final int FORCE_category_FARM  = 3;
	// 渔场
	public static final int FORCE_category_FISH = 4;
	// 矿石
	public static final int FORCE_category_MINE = 5;
	
	//简单
	public static final int FORCE_TYPE_SIMPLE = 1;
	//精英
	public static final int FORCE_TYPE_ELIENT = 2;
	//困难
	// public static final int FORCE_TYPE_DIFFICULT = 3;
	//史诗
	public static final int FORCE_TYPE_SHISHI = 3;
	//战斗标识过期时间 5分钟
	public static final long Battle_TAG_PASS_TIME = 5*60*1000; 
	//关卡战斗至少需要的时间未3秒
	public static final int BATTLE_LIMIT_TIME = 3;
	private Map<String, UserBattleBean> inBattleUser = Maps.newConcurrentMap();
	//攻打副本  等级不足
	public static final int ATTACK_FORCES_LEVEL_LIMIT = 2001;
	//体力不足  攻打的时候体力不足
	// public static final int ATTACK_FORCES_POWER_LIMIT = 2002;
	//活力不足 采集的时候
	// public static final int ATTACK_FORCES_ACTIVITY_LIMIT = 2003;
	//次数不足
	public static final int ATTACK_FORCES_TIMES_LIMIT = 2004;
	// 尚未通关上一关卡
	public static final int ATTACK_FORCES_NOT_PASS_PRE = 2005;
	// 有部分上阵英雄正在挂机
	public static final int HERO_IN_HANGUP = 2006;
	//采集时间没到
	public static final int END_ATTACK_TIME_AVALIAD = 2001;
	
	private static final int NOT_ATTACK = 0;	
	private static final int HAS_ATTACK = 1;
	
	/**
	 * 获取某个地图下的用户关卡信息 （暂时没有使用）
	 * @param userId
	 * @param mapId
	 * @return
	 */
	public List<UserForcesBO> getUserForcesBOAtMap(String userId, int mapId) {
		List<UserForces> list = userForcesDao.getUserForcesListByMapId(userId, mapId);
		Map<Integer, UserForces> userForcesMap = Maps.newConcurrentMap();
		if (list != null && list.size() > 0) {
			for (UserForces userForces : list) {
				userForcesMap.put(userForces.getForcesId(), userForces);
			}
		}
		
		List<SystemForces> systemForcesList = systemForcesDaoCache.getSystemForcesList(mapId);		
		List<UserForcesBO> result = Lists.newArrayList();
		if (systemForcesList != null && systemForcesList.size() > 0) {
			for (SystemForces systemForces : systemForcesList) {
				if (userForcesMap.containsKey(systemForces.getForcesId())) {
					UserForcesBO userForcesBO = creatUserForcesBO(userForcesMap.get(systemForces.getForcesId()), systemForces);
					result.add(userForcesBO);
				} else {
					// 未攻打的关卡状态为 -1
					UserForcesBO userForcesBO = creatUserForcesBO(userId, systemForces, NOT_ATTACK, FORCE_TYPE_SIMPLE);
					result.add(userForcesBO);
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取副本下的关卡信息
	 * 
	 * @param userId
	 * @param bigForcesId
	 * @return
	 */
	public List<UserForcesBO> getCopyForcesInfo(String userId, int mapId, int bigForcesId) {
		List<UserForces> userForcesList = this.userForcesDao.getUserForcesListByBigForcesId(userId, mapId, bigForcesId);
		List<UserForcesBO> userForcesBOList = Lists.newArrayList();
		User user = this.userSerivce.getByUserId(userId);		

		// 判断开放等级
		SystemBigForces bigForces = systemBigForcesDaoCache.getSystemBigForces(bigForcesId);
		if (user.getLevel() < bigForces.getLimitLevel())
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "未到开放等级");
		
		// 开启该副本的关卡
		if (userForcesList == null || userForcesList.size() == 0) {
			SystemForces systemForces = systemForcesDaoCache.getFirstCopySystemForces(mapId, bigForcesId, FORCE_category_BOSS);
			UserForces userForces = createUserForces(userId, systemForces, FORCE_TYPE_SIMPLE);
			this.userForcesDao.addUserForces(userForces);
			
			UserForcesBO bo = creatUserForcesBO(userId, systemForces, NOT_ATTACK, FORCE_TYPE_SIMPLE);
			userForcesBOList.add(bo);
			return userForcesBOList;
		}
		
		Map<Integer, SystemForces> systemForcesMap = Maps.newConcurrentMap();
		List<SystemForces> systemForcesList = systemForcesDaoCache.getSystemForcesList(mapId, bigForcesId);	
		if (systemForcesList != null && systemForcesList.size() > 0) {
			for (SystemForces systemForces : systemForcesList) {				
				systemForcesMap.put(systemForces.getForcesId(), systemForces);
			}
		}
		
		List<UserForcesBO> result = Lists.newArrayList();
		if (userForcesList != null && userForcesList.size() > 0) {
			for (UserForces userForces : userForcesList) {
				SystemForces systemForces = systemForcesMap.get(userForces.getForcesId());
				if (systemForces != null) {
					UserForcesBO userForcesBO = creatUserForcesBO(userForces, systemForces);
					result.add(userForcesBO);
				}				
			}
		}
		
		return result;
	}
	
	private UserForcesBO creatUserForcesBO(String userId, SystemForces systemForces, int status, int forcesType) {
		UserForcesBO userForcesBO = new UserForcesBO();
		userForcesBO.setForcesId(systemForces.getForcesId());
		userForcesBO.setMapId(systemForces.getMapId());
		userForcesBO.setForcesType(forcesType);
		userForcesBO.setStatus(status);
		userForcesBO.setBigForcesId(systemForces.getBigForcesId());
		userForcesBO.setTimes(0);
		userForcesBO.setUserId(userId);
		return userForcesBO;
	}
	
	private UserForcesBO creatUserForcesBO(UserForces userForces, SystemForces systemForces) {
		UserForcesBO userForcesBO = new UserForcesBO();
		userForcesBO.setForcesId(userForces.getForcesId());
		userForcesBO.setMapId(userForces.getMapId());
		userForcesBO.setStatus(userForces.getStatus());
		userForcesBO.setBigForcesId(userForces.getBigForcesId());
		userForcesBO.setForcesType(userForces.getForcesType());
		if(isInTheSamePeriod(userForces.getUpdatedTime(), systemForces.getAttackPeriod())){
			userForcesBO.setTimes(userForces.getTimes());
		}else{
			userForcesBO.setTimes(0);
			this.userForcesDao.updateForcesTimes(userForces.getUserId(), userForces.getMapId(), userForces.getForcesId(), 0, userForces.getForcesType());
		}
		userForcesBO.setUserId(userForces.getUserId());
		return userForcesBO;
	}
	
	/**
	 * 开始关卡事件
	 * @param userId
	 * @param mapId
	 * @param forcesId
	 * @param forcesType
	 */
    public ForcesAction_attackRes startAttackForces(String userId, int mapId, int forcesId, int forcesType, String userFriendId) {
    	if(forcesType != FORCE_TYPE_SIMPLE && forcesType != FORCE_TYPE_ELIENT && forcesType != FORCE_TYPE_SHISHI) {
  			 throw  new ServiceException(SystemErrorCode.PARAM_ERROR, "攻打难度有误，只能传 1 或 2 或3或4 传的是" + forcesType);
    	}
    	
    	Date now = new Date();
    	if(inBattleUser.containsKey(userId)){
    		Date preBattleTime = inBattleUser.get(userId).getAttackTime();
    		if(System.currentTimeMillis() - preBattleTime.getTime() < Battle_TAG_PASS_TIME){
    			 ServiceException e =  new ServiceException(SystemConstant.FAIL_CODE, "已在战斗中！不要重复战斗,userId="+userId+",mapId="+mapId+",forcesId="+forcesId+",forcesType="+forcesType);
    			 LogSystem.error(e, "已在战斗中！不要重复战斗,userId="+userId+",mapId="+mapId+",forcesId="+forcesId+",forcesType="+forcesType);
    			 throw e;
    		}
			LogSystem.error(new RuntimeException(), "已在战斗中！这是为什么呢！,userId="+userId+",mapId="+mapId+",forcesId="+forcesId+",forcesType="+forcesType);
    	}
    	
    	SystemForces systemForces = systemForcesDaoCache.getSystemForces(forcesId);
    	User user = userSerivce.getByUserId(userId); 
    	
    	// 若为副本
    	if (systemForces.getForcesCategory() == FORCE_category_BOSS) {
    		SystemBigForces systemBigForces = systemBigForcesDaoCache.getSystemBigForces(systemForces.getBigForcesId());
    		
    		//判断等级是否足够
        	if(user.getLevel() < systemBigForces.getLimitLevel()){
        		throw new ServiceException(ATTACK_FORCES_LEVEL_LIMIT, "等级不足,userId="+userId+",mapId="+mapId+",forcesId="+forcesId
        				+",forcesType="+forcesType+",needLevel="+systemBigForces.getLimitLevel()+",userLevel="+user.getLevel());
        	}    	
        	
        	UserForces preUserForces = userForcesDao.getUserForces(userId, mapId, systemForces.getPreForcesId(), forcesType);
    		// 攻打状态 0 可攻打  1 通过了
    		if (preUserForces != null && preUserForces.getStatus() == 0) 
    			throw new ServiceException(ATTACK_FORCES_NOT_PASS_PRE, "上一个关卡都未通过。");
    	}  	
    	
    	// 次数是否足够
    	UserForces userForces = userForcesDao.getUserForces(userId, mapId, forcesId, forcesType);
    	// 难度越级挑战？？
    	if (systemForces.getForcesCategory() == FORCE_category_BOSS && userForces == null) {
    		String message = "用户关卡数据为空？？？是不是越级挑战呢？？userId:" + userId + ",forcesId:" + forcesId + ",forcesType:" + forcesType;
    		ServiceException e =  new ServiceException(SystemConstant.FAIL_CODE, message);
  			 LogSystem.warn(message);
  			 throw e;
    	}    		
    	
		if(userForces != null && isInTheSamePeriod(userForces.getUpdatedTime(), systemForces.getAttackPeriod())){//如果在同一个周期
			SystemForcesMonster monster = this.systemForcesMonsterDaoCache.getSystemForcesMonster(forcesId, forcesType);
			
			// 小怪不判断攻打次数
			if (systemForces.getForcesCategory() == FORCE_category_BOSS && monster.getAttackLimitTimes() > 0 
					 && userForces.getTimes() >= monster.getAttackLimitTimes()){
      			 throw  new ServiceException(ATTACK_FORCES_TIMES_LIMIT, "次数不足,userId="+userId+",mapId="+mapId+",forcesId="+forcesId+",forcesType="+forcesType+",canAttackTimes="+monster.getAttackLimitTimes()+",haveAttackTimes"+userForces.getTimes());
			}			
		}
		
		Map<String, UserHangupInfo> map = this.lifeService.getHangupUserHeroIdMap(userId);
		List<UserHero> userHeroList = this.heroService.getBattleUserHero(userId);
		for (UserHero userHero : userHeroList) {
			if (map.containsKey(userHero.getUserHeroId()))
				throw new ServiceException(HERO_IN_HANGUP, "有部分上阵英雄在挂机中");
		}
		
		// 背包检查
		List<GoodsBeanBO> dropList = getForcesDropList(userId, user.getLevel(), forcesId, forcesType);
		this.userSerivce.checkBag(userId, dropList);		
		
		if (userForces == null) {
			userForces = createUserForces(userId, systemForces, forcesType);
			this.userForcesDao.addUserForces(userForces);
		} 
    	
    	UserBattleBean userBattleBean = new UserBattleBean();
    	userBattleBean.setAttackTime(now);
    	userBattleBean.setForcesId(forcesId);
    	userBattleBean.setForcesType(forcesType);
    	userBattleBean.setMapId(mapId);
    	userBattleBean.setHasRandom(0);
    	userBattleBean.setDrop(dropList);
    	inBattleUser.put(userId, userBattleBean);
    	
    	List<UserEquipBO> userEquipList = null;
    	if (systemForces.getForcesCategory() == FORCE_category_BOSS && StringUtils.isNotBlank(userFriendId)) {
    		userEquipList = equipService.getTeamLeaderEquipList(userFriendId);
    		friendService.addInviteUser(userId, userFriendId);
    		this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_INVITE_FRIEND, 1);
    	}
    	
    	ForcesAction_attackRes res = new ForcesAction_attackRes();
    	res.setUserEquipList(userEquipList);
    	res.setGoodsList(dropList);
    	return res;
    }
    
    private UserForces createUserForces(String userId, SystemForces systemForces, int forcesType) {
    	UserForces userForces = new UserForces();
		userForces.setCreatedTime(new Date());
		userForces.setForcesId(systemForces.getForcesId());
		userForces.setMapId(systemForces.getMapId());
		userForces.setBigForcesId(systemForces.getBigForcesId());
		userForces.setStatus(0);
		userForces.setTimes(0);
		userForces.setForcesType(forcesType);
		userForces.setUpdatedTime(new Date());
		userForces.setUserId(userId);
    
		return userForces;
    }
    
    /**
     * 战斗结算
     * @param userId
     * @param flag   -1 输  0 平局  1赢
     * @return
     */
    public CommonGoodsBeanBO endAttackForces(String userId, int flag){
    	final UserBattleBean userBattleBean = inBattleUser.get(userId);
    	Date now = new Date();
    	if (userBattleBean == null) {
    		 ServiceException e = new ServiceException(SystemConstant.FAIL_CODE, "不存在用户打关卡的记录！！");
			 LogSystem.warn("不存在用户打关卡的记录！！外挂？？userId="+userId);
			 throw e;
    	}

    	User user = this.userSerivce.getByUserId(userId);
    	if (flag <= 0) {
    		userBattleLog(userId, user.getLevel(), String.valueOf(userBattleBean.getForcesId()), LogType.BATTLE_TYPE_SCENCE, flag, new Date());
    		return new CommonGoodsBeanBO();
    	}
    	
    	final SystemForces systemForces = systemForcesDaoCache.getSystemForces(userBattleBean.getForcesId());
    	int limitTime = BATTLE_LIMIT_TIME;
		
		//时间校验
		if (now.getTime() - userBattleBean.getAttackTime().getTime() < limitTime * 1000) {
			throw new ServiceException(END_ATTACK_TIME_AVALIAD, "时间校验失败！");
		}
		
		//更新为已打过
    	UserForces userForces = userForcesDao.getUserForces(userId, userBattleBean.getMapId(), userBattleBean.getForcesId(), userBattleBean.getForcesType());
    	// 难度越级挑战？？
    	if (userForces == null) {
    		String message = "用户关卡数据为空？？？是不是越级挑战呢？？userId:" + userId + ",forcesId:" + userBattleBean.getForcesId() + ",forcesType:" + userBattleBean.getForcesType();
    		ServiceException e =  new ServiceException(SystemConstant.FAIL_CODE, message);
  			 LogSystem.warn(message);
  			 throw e;
    	}    
    	
    	// 策划说某个关卡要给用户送英雄
    	CommonGoodsBeanBO heroBO = null;    
    	if (userBattleBean.getForcesType() == FORCE_TYPE_SIMPLE && user.getCamp() == UserConstant.ALLIANCE) {
    		// 联盟
    		int persentForcesId = this.configDataDao.getInt(ConfigKey.alliance_persend_hero_forces_id, 4);
    		if (userForces.getForcesId() == persentForcesId && userForces.getStatus() == NOT_ATTACK) {
    			int heroId = this.configDataDao.getInt(ConfigKey.alliance_persend_hero_id, 10010);
    			heroBO = goodsDealService.sendGoods(userId, GoodsType.Hero.intValue + "," + heroId + ",1", GoodsUseType.ADD_ATTACK_MONSTER);
    		}    		
    	} else if (userBattleBean.getForcesType() == FORCE_TYPE_SIMPLE && user.getCamp() == UserConstant.HORDE) {
    		// 部落
    		int persentForcesId = this.configDataDao.getInt(ConfigKey.horde_persend_hero_forces_id, 106);
    		if (userForces.getForcesId() == persentForcesId && userForces.getStatus() == NOT_ATTACK) {
    			int heroId = this.configDataDao.getInt(ConfigKey.horde_persend_hero_id, 20030);
    			heroBO = goodsDealService.sendGoods(userId, GoodsType.Hero.intValue + "," + heroId + ",1", GoodsUseType.ADD_ATTACK_MONSTER);
    		}
    	} 
    	
    	// 更新攻打的次数、状态
    	int updateTimes = userForces.getTimes() + 1;
    	userForcesDao.updateUserForcesStatus(userId, userBattleBean.getMapId(), userBattleBean.getForcesId(), HAS_ATTACK, updateTimes, userBattleBean.getForcesType());  
    	
    	// 若为副本则开启下一关卡
    	if (systemForces.getForcesCategory() == FORCE_category_BOSS) {
    		int forcesType = userBattleBean.getForcesType();
    		SystemForces nextSystemForces = systemForcesDaoCache.getNextSystemForces(userBattleBean.getForcesId());
    		// TODO 暂时只开放了两个等级
    		if (nextSystemForces == null && userForces.getForcesType() < FORCE_TYPE_SHISHI) {
    			// 开启下一困难模式
    			nextSystemForces = systemForcesDaoCache.getFirstCopySystemForces(systemForces.getMapId(), systemForces.getBigForcesId(), FORCE_category_BOSS);
    			forcesType = userForces.getForcesType() + 1;
    		}    		
    		UserForces nextUserForces = userForcesDao.getUserForces(userId, userBattleBean.getMapId(), nextSystemForces.getForcesId(), forcesType);
    		
    		// 开启下一关卡
    		if (nextSystemForces != null && nextUserForces == null) {
    			userForces = createUserForces(userId, nextSystemForces, forcesType);
        		userForcesDao.addUserForces(userForces);
    		}     		
    	}
    	
		final int systemForcesId = systemForces.getForcesId();
		//提交任务
		userTaskService.updateTaskFinish(userId, 1, new ITaskHitChecker() {			
			@Override
			public Map<String, Object> isHit(int systemTaskId, int taskLibrary,
					Map<String, String> params) {
				Map<String, Object> returnMap = Maps.newConcurrentMap();
				returnMap.put("rt", false);
				returnMap.put("tm", 1);
				
				if (taskLibrary==TaskLibraryType.CROSS_FORCES) {
					int forcesId = NumberUtils.parseNumber(params.get("forcesId"), Integer.class);
					Preconditions.checkNotNull(forcesId, "forcesId不能为空");
	                if(systemForcesId == forcesId) {
	                	returnMap.put("rt", true);
	                	return returnMap;
	                }
				} else if (taskLibrary == TaskLibraryType.KILL_MONSTER) {
					Integer forcesId = NumberUtils.parseNumber(params.get("forcesId"), Integer.class);
					String monsterId = params.get("monsterId");
					if (systemForcesId != forcesId)
						return returnMap;
					
					int times = isInForces(forcesId, monsterId, userBattleBean.getForcesType());
					returnMap.put("rt", true);
					returnMap.put("tm", times);
					return returnMap;					
				} else if (taskLibrary == TaskLibraryType.CROSS_ANY_FORCES) {
					// 通关任意副本
					if (systemForces.getForcesCategory() == FORCE_category_BOSS) {
						returnMap.put("rt", true);
					}
				}				
				
				return returnMap;
			}
		});
		userBattleBean.setHasRandom(1);
		
		// 通关副本
		achievementService.updateAchievementFinish(userId, 1, AchievementConstant.TYPE_FORCES, new IAchievementChecker(){
			@Override
			public boolean isHit(int achievementId, Map<String, String> params) {
				boolean isHit = false;
				
				if (params.containsKey("forcesId") && params.containsKey("forcesType")) {
					int forcesId = Integer.parseInt(params.get("forcesId"));
					int forcesType = Integer.parseInt(params.get("forcesType"));
					
					if (userBattleBean.getForcesType() == forcesType && systemForcesId == forcesId) {
						isHit = true;
					}					
				}
				
				return isHit;
			}
		});
		
		//发奖
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, userBattleBean.getDrop(), GoodsUseType.ADD_ATTACK_MONSTER);;
		if (heroBO != null) {
			if (drop.getHeroList() == null) 
				drop.setHeroList(heroBO.getHeroList());
			else 
				drop.getHeroList().addAll(heroBO.getHeroList());
		}
		
		if (userBattleBean.getForcesType() == FORCE_TYPE_SIMPLE) {
			this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_FORCE_TYPE_SIMPLE, 1);
		} else if (userBattleBean.getForcesType() == FORCE_TYPE_ELIENT) {
			this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_FORCE_TYPE_ELIENT, 1);
		} else if (userBattleBean.getForcesType() == FORCE_TYPE_SHISHI) {
			this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_FORCE_TYPE_SHISHI, 1);
		}
		
		if (systemForces.getForcesCategory() == FORCE_category_MONSTER)
			this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_KILL_FORCE_MONSTER, 1);
		
		userBattleLog(userId, user.getLevel(), String.valueOf(userBattleBean.getForcesId()), LogType.BATTLE_TYPE_SCENCE, flag, new Date());
    	return drop;
    }
    
    /**
     * 获取奖励
     * 
     * @param userId
     * @param userLevel
     * @param forcesId
     * @param forcesType
     * @return
     */
    private List<GoodsBeanBO> getForcesDropList(String userId, int userLevel, int forcesId, int forcesType) {
    	List<SystemForcesDropTool> dropList = systemForcesDropToolDaoCache.getForcesDropList(forcesId, forcesType);
    	if(dropList != null){
    		List<GoodsBeanBO> list = Lists.newArrayList();
    		for(SystemForcesDropTool systemForcesDropTool : dropList){
        		int rand = new Random().nextInt(10000);
    			if(rand >= systemForcesDropTool.getLowerNum() && rand <= systemForcesDropTool.getUpperNum() 
    					&& userLevel >= systemForcesDropTool.getMinLevel() && userLevel <= systemForcesDropTool.getMaxLevel()) {
    				int toolId = systemForcesDropTool.getToolId();
    				int toolType = systemForcesDropTool.getToolType();
    				
    				int toolNum = systemForcesDropTool.getMinNum();
    				if (systemForcesDropTool.getMinNum() != systemForcesDropTool.getMaxNum())
    					toolNum = RandomUtils.getRandomNum(systemForcesDropTool.getMinNum(), systemForcesDropTool.getMaxNum());
    				
    				if (toolType != GoodsType.tool.intValue) {
    					list.add(GoodsHelper.parseDropGood(toolType, toolId, toolNum));
    					continue;
    				}
    				
    				SystemTool systemTool = this.toolService.getSystemTool(toolId);
    				if (systemTool.getType() == ToolType.GIFT_BOX) {
    					list.add(GoodsHelper.parseDropGood(toolType, toolId, toolNum));
    					continue;
    				}

    				// 开一个掉落宝箱
    				int keyId = toolService.getGiftBoxKey(systemTool.getToolId());
    				if (keyId != 0) {
    					list.add(GoodsHelper.parseDropGood(toolType, toolId, toolNum));
    					continue;
    				}
    					
    				List<GoodsBeanBO> beanList = this.toolService.openDropGiftBox(userId, systemForcesDropTool.getToolId());
    				if (beanList != null && beanList.size() != 0)
    					list.addAll(beanList);
    			}
    		}
    		
    		if (list.size() > 0){
    			return list;
    		}
    	} else {
    		LogSystem.warn("没有配置掉落，forcesId="+forcesId+",forcesType="+forcesType);
    	}
    	
    	return Lists.newArrayList();
    }
    
    /**
     * 查看是否在同一个周期内
     * @param before
     * @param period
     * @return
     */
    private boolean isInTheSamePeriod(Date before,int period){
    	Date nowTemp = DateUtils.getDateAtMidnight(new Date());
    	Date beforeTemp = DateUtils.getDateAtMidnight(before);
    	int diff = DateUtils.getDayDiff(beforeTemp, nowTemp);
    	if(diff >= period){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    private static final int NOT_IN_BATTLE = 2001;
    private static final int HAS_RANDOM = 2002;
    /**
     * 判断是否在战斗中
     * 
     * @param userId
     * @return
     */
    public boolean isBattling(String userId) {
    	if(inBattleUser.containsKey(userId)){
    		Date preBattleTime = inBattleUser.get(userId).getAttackTime();
    		if (System.currentTimeMillis() - preBattleTime.getTime() >= Battle_TAG_PASS_TIME)
    			throw new ServiceException(NOT_IN_BATTLE, "没有在战斗中");
    		
    		if(inBattleUser.get(userId).getHasRandom() != 0)
    			throw new ServiceException(HAS_RANDOM, "已经随机过了");
    		
    		inBattleUser.get(userId).setHasRandom(1);
    		return true;    		
    	}
    	
    	return false;
    }

	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
        if(handlerType.equals(ModuleEventConstant.USER_LOGINOUT_EVENT)){
        	String userId = baseModuleEvent.getStringValue("userId", "");
        	inBattleUser.remove(userId);
        	LogSystem.warn("userId[" + userId + "]，登出游戏，inBattleUser中移出该用户.");
        }	
	}
	
	@Override
	public List<String> getHandlerType() {
		List<String> list = Lists.newArrayList();
		list.add(ModuleEventConstant.USER_LOGINOUT_EVENT);
		return list;
	}
	
	/**
	 * 判断怪物是否在该部队中
	 * 
	 * @param forcesId
	 * @param monsterId
	 * @return
	 */
	public int isInForces(int forcesId, String monsterId, int forcesType) {
		SystemForcesMonster systemForcesMonster = this.systemForcesMonsterDaoCache.getSystemForcesMonster(forcesId, forcesType);
		Map<String, Integer> monsterIdMap = getMonsterIdMap(systemForcesMonster.getMonsterId());
		if (monsterIdMap.containsKey(monsterId))
			return monsterIdMap.get(monsterId);
		
		return 0;
	}
	
	private Map<String, Integer> getMonsterIdMap(String monsterIds) {
		String[] monsterArr = monsterIds.split("\\|");
		Map<String, Integer> map = Maps.newConcurrentMap();
		for (String str : monsterArr) {
			String[] monsterIdArr = str.split(",");
			for (String monsterId : monsterIdArr) {
				if (map.containsKey(monsterId)) {
					int times = map.get(monsterId);
					map.put(monsterId, times + 1);
				} else {
					map.put(monsterId, 1);
				}
			}
		}
		
		return map;
	}
	
	private static final int HERO_NOT_IN_BATTLE = 2001;
	/**
	 * 复活
	 * 
	 * @param userId
	 * @param userHeroId
	 */
	public ForcesAction_reliveRes relive(String userId, String userHeroId) {
		if (StringUtils.isBlank(userHeroId)) 
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，userHeroId is blank");
	
		UserHeroBO userHeroBO = this.heroService.getUserHeroBO(userId, userHeroId);
		if (userHeroBO == null)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，userHeroBO is null");
		
		if (userHeroBO.getPos() <= 0)
			throw new ServiceException(HERO_NOT_IN_BATTLE, "该英雄没有在阵上......");
	
		// 扣金币
		User user = this.userSerivce.getByUserId(userId);
		int costGold = this.configDataDao.getInt(ConfigKey.relive_cost_gold, 5000);
		if (costGold > 0) {
			if (user.getGold() < costGold)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
			
			if (!this.userSerivce.reduceGold(userId, costGold, GoodsUseType.REDUCE_FORCES_RELIVE))
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		}
		
		// 扣钻石
		int costMoney = this.configDataDao.getInt(ConfigKey.relive_cost_diamond, 50);
		if (costMoney > 0) {
			if (user.getMoney() < costMoney) 
				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
			
			if (!this.userSerivce.reduceMoney(userId, costMoney, GoodsUseType.REDUCE_FORCES_RELIVE))
				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		}
		
		ForcesAction_reliveRes res = new ForcesAction_reliveRes();
		user = this.userSerivce.getByUserId(userId);
		res.setGold(user.getGold());
		res.setMoney(user.getMoney());
		return res;
	}
	
	private UserForcesBO creatUserCollectBO(UserForces userForces, SystemForces systemForces) {
		UserForcesBO userForcesBO = new UserForcesBO();
		userForcesBO.setForcesId(userForces.getForcesId());
		userForcesBO.setMapId(userForces.getMapId());
		userForcesBO.setStatus(userForces.getStatus());
		userForcesBO.setBigForcesId(userForces.getBigForcesId());
		userForcesBO.setForcesType(userForces.getForcesType());
		userForcesBO.setTimes(userForces.getTimes());
		
		userForcesBO.setUserId(userForces.getUserId());
		return userForcesBO;
	}
	
	/**
	 * 获取该地图的采集点的信息
	 * 
	 * @param userId
	 * @param mapId
	 * @return
	 */
	public List<UserForcesBO> getMapCollectionInfo(String userId, int mapId) {
		List<SystemForces> systemForcesList = this.systemForcesDaoCache.getCollectListByMapId(mapId, FORCE_category_FARM);
		List<SystemForces> fishList = this.systemForcesDaoCache.getCollectListByMapId(mapId, FORCE_category_FISH);
		List<SystemForces> mineList = this.systemForcesDaoCache.getCollectListByMapId(mapId, FORCE_category_MINE);
		
		List<UserForcesBO> userForcesBOList = Lists.newArrayList();
		if (systemForcesList == null && fishList == null && mineList == null)
			return userForcesBOList;
		
		Date now = new Date();
		systemForcesList.addAll(fishList);
		systemForcesList.addAll(mineList);
		
		Map<Integer, UserForces> userForcesMap = this.userForcesDao.getUserForcesMapByMapId(userId, mapId);
		for (SystemForces systemForces : systemForcesList) {
			if (userForcesMap.containsKey(systemForces.getForcesId())) {
				UserForces userForces = userForcesMap.get(systemForces.getForcesId());
				SystemForcesMonster systemForcesMonster = this.systemForcesMonsterDaoCache.getSystemForcesMonster(userForces.getForcesId(), FORCE_TYPE_SIMPLE);
				long hasCdTime = now.getTime() - userForces.getUpdatedTime().getTime();
				// cd时间
				long cdTime = systemForcesMonster.getCollectCdTime() * 60 * 1000;
				
				UserForcesBO userForcesBO = null;
				// 更新次数
				if (userForces.getTimes() >= systemForcesMonster.getAttackLimitTimes() && hasCdTime > cdTime) {
					this.userForcesDao.updateForcesTimes(userId, mapId, systemForces.getForcesId(), 0, FORCE_TYPE_SIMPLE);
					userForcesBO = creatUserCollectBO(userForces, systemForces);
				} else if (userForces.getTimes() < systemForcesMonster.getAttackLimitTimes()) {
					userForcesBO = creatUserCollectBO(userForces, systemForces);
				}
				
				if (userForcesBO != null)
					userForcesBOList.add(userForcesBO);				
			} else {
				UserForcesBO userForcesBO = creatUserForcesBO(userId, systemForces, 0, FORCE_TYPE_SIMPLE);
				userForcesBOList.add(userForcesBO);	
			}				
		}		
		
		return userForcesBOList;
	}
	
	/**
	 * 开始采集关卡
	 * 
	 * @param userId
	 * @param mapId
	 * @param forcesId
	 */
	public void startCollect(String userId, int mapId, int forcesId) {  
		if(inBattleUser.containsKey(userId)){
			Date preBattleTime = inBattleUser.get(userId).getAttackTime();
			if(System.currentTimeMillis()-preBattleTime.getTime() < Battle_TAG_PASS_TIME){
				ServiceException e =  new ServiceException(SystemConstant.FAIL_CODE, "已在战斗中！不要重复战斗,userId="+userId+",mapId="+mapId+",forcesId="+forcesId);
				LogSystem.error(e, "已在战斗中！不要重复战斗,userId="+userId+",mapId="+mapId+",forcesId="+forcesId);
   			 	throw e;
			}
   		
			LogSystem.error(new RuntimeException(), "已在战斗中！这是为什么呢！,userId="+userId+",mapId="+mapId+",forcesId="+forcesId);
		}
   	
		SystemForces systemForces = systemForcesDaoCache.getSystemForces(forcesId);
		if (systemForces.getForcesCategory() != FORCE_category_FARM && systemForces.getForcesCategory() !=  FORCE_category_FISH
				&& systemForces.getForcesCategory() !=  FORCE_category_MINE)
			throw new ServiceException(SystemConstant.FAIL_CODE, "接口请求错了吧.....");
		
		User user = userSerivce.getByUserId(userId);    	
		// 次数是否足够
		UserForces userForces = userForcesDao.getUserForces(userId, mapId, forcesId, FORCE_TYPE_SIMPLE);
		if (userForces != null) {//判断cd
			SystemForcesMonster monster = this.systemForcesMonsterDaoCache.getSystemForcesMonster(forcesId, FORCE_TYPE_SIMPLE);
			
			// 小怪不判断攻打次数
			if (monster.getAttackLimitTimes() > 0 && userForces.getTimes() >= monster.getAttackLimitTimes()){
				throw  new ServiceException(ATTACK_FORCES_TIMES_LIMIT, "次数不足,userId="+userId+",mapId="+mapId+",forcesId="+forcesId+",canAttackTimes="+monster.getAttackLimitTimes()+",haveAttackTimes"+userForces.getTimes());
			}			
		}
		
		Map<String, UserHangupInfo> map = this.lifeService.getHangupUserHeroIdMap(userId);
		List<UserHero> userHeroList = this.heroService.getBattleUserHero(userId);
		for (UserHero userHero : userHeroList) {
			if (map.containsKey(userHero.getUserHeroId()))
				throw new ServiceException(HERO_IN_HANGUP, "有部分上阵英雄在挂机中");
		}
		
		// 背包检查
		List<GoodsBeanBO> dropList = getForcesDropList(userId, user.getLevel(), forcesId, FORCE_TYPE_SIMPLE);
//		if (dropList == null || dropList.size() == 0)
//			throw new ServiceException(SystemConstant.FAIL_CODE, "采集没有奖励......forcesId[" + forcesId + "]");
		
		this.userSerivce.checkBag(userId, dropList);		
		
		if (userForces == null) {
			userForces = createUserForces(userId, systemForces, FORCE_TYPE_SIMPLE);
			this.userForcesDao.addUserForces(userForces);
		}
		
		Date now = new Date();
		UserBattleBean userBattleBean = new UserBattleBean();
    	userBattleBean.setAttackTime(now);
    	userBattleBean.setForcesId(forcesId);
    	userBattleBean.setForcesType(FORCE_TYPE_SIMPLE);
    	userBattleBean.setMapId(mapId);
    	userBattleBean.setHasRandom(0);
    	userBattleBean.setDrop(dropList);
    	inBattleUser.put(userId, userBattleBean);
	}
	
	/**
	 * 取消采集
	 * 
	 * @param userId
	 */
	public void cancelCollect(String userId) {
		UserBattleBean userBattleBean = inBattleUser.remove(userId);
		if (userBattleBean == null) {
   		 ServiceException e = new ServiceException(SystemConstant.FAIL_CODE, "不存在用户采集的记录！！");
			 LogSystem.warn("不存在用户采集的记录！！外挂？？userId="+userId);
			 throw e;
		}		
	}
	
	//private static final int NEED_FIGHT_AGAIN = 2002;
	/**
	 * 结束采集
	 * 
	 * @param userId
	 * @param mapId
	 * @param forcesId
	 * @return
	 */
	public Map<String, Object> endCollect(String userId, int mapId, int forcesId) {
		final UserBattleBean userBattleBean = inBattleUser.get(userId);
    	Date now = new Date();
    	if (userBattleBean == null) {
    		 ServiceException e = new ServiceException(SystemConstant.FAIL_CODE, "不存在用户采集的记录！！");
			 LogSystem.warn("不存在用户采集的记录！！外挂？？userId="+userId);
			 throw e;
    	}
    	
    	final SystemForces systemForces = systemForcesDaoCache.getSystemForces(userBattleBean.getForcesId());
    	if (systemForces.getForcesCategory() != FORCE_category_FARM && systemForces.getForcesCategory() !=  FORCE_category_FISH
    			&& systemForces.getForcesCategory() !=  FORCE_category_MINE) 
    		throw new ServiceException(SystemConstant.FAIL_CODE, "接口请求错了吧.....");
    	
    	int limitTime = systemForces.getCollectionTime();		
		//时间校验
		if (now.getTime() - userBattleBean.getAttackTime().getTime() < limitTime * 1000) {
			throw new ServiceException(END_ATTACK_TIME_AVALIAD, "时间校验失败！");
		}
		
		//更新为已打过
    	UserForces userForces = userForcesDao.getUserForces(userId, userBattleBean.getMapId(), userBattleBean.getForcesId(), userBattleBean.getForcesType());
    	if (userForces == null) {
    		String message = "用户关卡数据为空？？？userId:" + userId + ",forcesId:" + userBattleBean.getForcesId() + ",forcesType:" + userBattleBean.getForcesType();
    		ServiceException e =  new ServiceException(SystemConstant.FAIL_CODE, message);
  			LogSystem.warn(message);
  			throw e;
    	}
    	
    	// 更新攻打的次数、状态
    	int updateTimes = userForces.getTimes() + 1;
    	userForcesDao.updateUserForcesStatus(userId, userBattleBean.getMapId(), userBattleBean.getForcesId(), HAS_ATTACK, updateTimes, userBattleBean.getForcesType());  
    	
    	Map<String, Object> map = Maps.newConcurrentMap();
    	map.put("isFightAgain", 0);
    	if (!userBattleBean.isFightAgain()) {
    		List<GoodsBeanBO> goodsList = luckyService.getCollectFight(userId);
    		userBattleBean.setHasRandom(1);
    		
    		if (goodsList != null && goodsList.size() > 0) {
    			userBattleBean.setFightAgain(true);
    			userBattleBean.getDrop().addAll(goodsList);
    			if (userSerivce.checkBagLimit(userId, userBattleBean.getDrop())) {
    				map.put("isFightAgain", 1);
    				map.put("drop", new CommonGoodsBeanBO());
    				return map; 
    			}
    		} else {
    			inBattleUser.remove(userId);
    		}   		
    	}
    	
		//发奖
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, userBattleBean.getDrop(), GoodsUseType.ADD_BY_COLLECT);
		map.put("drop", drop);		
		updateUserActivityTask(userId, systemForces.getForcesCategory());
		
		User user = this.userSerivce.getByUserId(userId);
		userBattleLog(userId, user.getLevel(), String.valueOf(userBattleBean.getForcesId()), LogType.BATTLE_TYPE_COLLECT, 1, new Date());
    	return map;
	}
	
	private void updateUserActivityTask(String userId, int forcesCategory) {
		if (forcesCategory == FORCE_category_FARM) {
			this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_COLLECT_GRASS, 1);
		} else if (forcesCategory == FORCE_category_FISH) {
			this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_COLLECT_FISH, 1);
		} else if (forcesCategory == FORCE_category_MINE) {
			this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_COLLECT_MINE, 1);
		}
	}
	
	/**
	 * 获取采集，打怪的奖励
	 * 
	 * @param userId
	 * @param mapId
	 * @param forcesId
	 * @return
	 */
	public CommonGoodsBeanBO getCollectFightReward(String userId, int mapId, int forcesId, int flag) {
		final UserBattleBean userBattleBean = inBattleUser.remove(userId);
		if (userBattleBean == null) {
			ServiceException e = new ServiceException(SystemConstant.FAIL_CODE, "不存在用户采集的记录！！");
			LogSystem.warn("不存在用户采集的记录！！外挂？？userId="+userId);
			throw e;
		}
		
		User user = this.userSerivce.getByUserId(userId);
		if (flag <= 0) {
			userBattleLog(userId, user.getLevel(), String.valueOf(userBattleBean.getForcesId()), LogType.BATTLE_TYPE_COLLECT, flag, new Date());
    		return new CommonGoodsBeanBO();
    	}
		
		if (!userBattleBean.isFightAgain()) {
			ServiceException e = new ServiceException(SystemConstant.FAIL_CODE, "采集没有遇到野怪，为什么到这个接口！！");
			LogSystem.warn("采集没有遇到野怪，为什么到这个接口！！外挂？？userId="+userId);
			throw e;
		}
		
		//发奖
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, userBattleBean.getDrop(), GoodsUseType.ADD_BY_COLLECT_AND_ATTACK_MONSTER);	
		userBattleLog(userId, user.getLevel(), String.valueOf(userBattleBean.getForcesId()), LogType.BATTLE_TYPE_COLLECT_AND_FORCES, flag, new Date());
		
		SystemForces systemForces = systemForcesDaoCache.getSystemForces(userBattleBean.getForcesId());
		updateUserActivityTask(userId, systemForces.getForcesCategory());
		return drop;
	}
	
	public final static int STATUS_OPEN_BOX = 2;
	public final static int FORCE_CATEGORY_NOT_BOSS = 2001;
	public final static int HAS_NO_CHANCE = 2002;
	/**
	 * 开启战斗宝箱
	 * 
	 * @param userId
	 * @return
	 */
	public ForcesAction_openBattleBoxRes openBattleBox(String userId, int status) {
		final UserBattleBean userBattleBean = inBattleUser.remove(userId);
		ForcesAction_openBattleBoxRes res = new ForcesAction_openBattleBoxRes();
		
		res.setDrop(new CommonGoodsBeanBO());
		if (status != STATUS_OPEN_BOX)
			return res;
		
		if (userBattleBean == null) {
			ServiceException e = new ServiceException(HAS_NO_CHANCE, "不存在用户攻打关卡记录！！已经点过一次了，不要再点了？？战斗翻牌");
			LogSystem.warn("不存在用户打关卡的记录！！已经点过一次了，不要再点了？？userId=" + userId);
			throw e;
		}
		
		SystemForces systemForces = this.systemForcesDaoCache.getSystemForces(userBattleBean.getForcesId());
		if (systemForces.getForcesCategory() != FORCE_category_BOSS)
			throw new ServiceException(FORCE_CATEGORY_NOT_BOSS, "只有副本的关卡才有开宝箱哦！！");
		
		User user = this.userSerivce.getByUserId(userId);
		int cost = configDataDao.getInt(ConfigKey.battle_open_box_cost, 10);
		
		if (user.getMoney() <= cost)
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		List<SystemForcesDropTool> toolList = this.systemForcesDropToolDaoCache.getForcesDropList(userBattleBean.getForcesId(), userBattleBean.getForcesType());
		List<SystemForcesDropTool> randomList = Lists.newArrayList();
		for (SystemForcesDropTool tool : toolList) {
			if (tool.getToolType() == GoodsType.Exp.intValue || tool.getToolType() == GoodsType.HeroExp.intValue)
				continue;
			
			if (user.getLevel() < tool.getMinLevel() || user.getLevel() > tool.getMaxLevel())
				continue;
			
			randomList.add(tool);
		}
		
		if (randomList.size() == 0)
			return res;
		
		List<GoodsBeanBO> goodsList = Lists.newArrayList();
		int random = RandomUtils.getRandomNum(0, randomList.size() - 1);
		SystemForcesDropTool dropTool = randomList.get(random);
		if (dropTool.getToolType() == GoodsType.tool.intValue) {
			SystemTool systemTool = this.toolService.getSystemTool(dropTool.getToolId());
			
			if (systemTool.getType() == ToolType.GIFT_BOX) {
				int keyId = toolService.getGiftBoxKey(dropTool.getToolId());
				// 开一个掉落宝箱
				if (keyId == 0) {
					List<GoodsBeanBO> beanList = this.toolService.openDropGiftBox(userId, dropTool.getToolId());
					if (beanList != null && beanList.size() != 0)
						goodsList.addAll(beanList);
				}
			} else {
				int toolNum = dropTool.getMinNum();
				if (dropTool.getMinNum() != dropTool.getMaxNum())
					toolNum = RandomUtils.getRandomNum(dropTool.getMinNum(), dropTool.getMaxNum());
				
				goodsList.add(GoodsHelper.parseDropGood(dropTool.getToolType(), dropTool.getToolId(), toolNum));
			}
			
		} else {
			int toolNum = dropTool.getMinNum();
			if (dropTool.getMinNum() != dropTool.getMaxNum())
				toolNum = RandomUtils.getRandomNum(dropTool.getMinNum(), dropTool.getMaxNum());
			
			goodsList.add(GoodsHelper.parseDropGood(dropTool.getToolType(), dropTool.getToolId(), toolNum));
		}
		
		// 背包检查
		this.userSerivce.checkBag(userId, goodsList);
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, goodsList, GoodsUseType.ADD_OPEN_BATTLE_BOX);
		res.setDrop(drop);
		return res;
	}
	
	public void userBattleLog(String userId, int userLevel, String targetId, int type, int flag, Date creatTime) {
		String time = DateUtils.getTimeStr(creatTime);
		String now = DateUtils.getDateForLog();
		String sql = "INSERT INTO user_battle_report_" + now + "(USER_ID, USER_LEVEL, TARGET_ID, TYPE, FLAG, CREAT_TIME) " + "VALUES('" + userId + "'," + userLevel + ",'" + targetId + "'," + type + "," + flag + ",'" + time
				+ "')";
		
		logService.unSynInsertLog(sql);
	}
	
	@Override
	public int order() {
		return 2;
	}
}