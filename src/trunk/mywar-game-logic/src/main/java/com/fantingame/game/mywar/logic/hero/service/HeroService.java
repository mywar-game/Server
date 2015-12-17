package com.fantingame.game.mywar.logic.hero.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.mina.core.RuntimeIoException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.IDGenerator;
import com.fantingame.game.common.utils.RandomUtils;
import com.fantingame.game.log.service.LogService;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import com.fantingame.game.msgbody.client.gemstone.UserGemstoneBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.hero.HeroAction_careerClearRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_changeTeamLeaderRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_getUserBattleInfoRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_heroInheritRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_heroPromoteRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_promoteHeroStarRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_returnJobExpRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_studyLeaderSkillRes;
import com.fantingame.game.msgbody.client.hero.HeroAction_upgradeLeaderSkillRes;
import com.fantingame.game.msgbody.client.hero.SkillToolBO;
import com.fantingame.game.msgbody.client.hero.UserCareerInfoBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.hero.UserHeroSkillBO;
import com.fantingame.game.msgbody.client.hero.UserHeroSkillPosChangeBO;
import com.fantingame.game.msgbody.client.tool.UserToolBO;
import com.fantingame.game.msgbody.notify.user.UserBO;
import com.fantingame.game.mywar.logic.activity.constant.ActivityTaskConstant;
import com.fantingame.game.mywar.logic.activity.service.ActivityService;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.equip.model.UserEquip;
import com.fantingame.game.mywar.logic.equip.service.EquipService;
import com.fantingame.game.mywar.logic.gemstone.service.GemstoneService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.hero.constant.HeroConstant;
import com.fantingame.game.mywar.logic.hero.constant.HeroStandType;
import com.fantingame.game.mywar.logic.hero.constant.LogType;
import com.fantingame.game.mywar.logic.hero.dao.SystemSkillLevelDao;
import com.fantingame.game.mywar.logic.hero.dao.UserCareerInfoDao;
import com.fantingame.game.mywar.logic.hero.dao.UserHeroSkillDao;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemCareerClearConfigDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemCareerClearDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemHeroAtributeDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemHeroCareerAddDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemHeroDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemHeroInheritDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemHeroLevelDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemHeroNameDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemHeroPromoteDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemHeroPromoteStarDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemHeroSkillConfigDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemHeroSkillDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.SystemSkillLevelMaxDaoCache;
import com.fantingame.game.mywar.logic.hero.dao.cache.UserHeroDaoCacheImpl;
import com.fantingame.game.mywar.logic.hero.model.SystemCareerClear;
import com.fantingame.game.mywar.logic.hero.model.SystemCareerClearConfig;
import com.fantingame.game.mywar.logic.hero.model.SystemHero;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroAttribute;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroInherit;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroLevel;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroPromote;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroPromoteStar;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroSkill;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroSkillConfig;
import com.fantingame.game.mywar.logic.hero.model.SystemSkillLevel;
import com.fantingame.game.mywar.logic.hero.model.SystemSkillLevelMax;
import com.fantingame.game.mywar.logic.hero.model.UserCareerInfo;
import com.fantingame.game.mywar.logic.hero.model.UserHero;
import com.fantingame.game.mywar.logic.hero.model.UserHeroSkill;
import com.fantingame.game.mywar.logic.life.model.UserHangupInfo;
import com.fantingame.game.mywar.logic.life.service.LifeService;
import com.fantingame.game.mywar.logic.lucky.service.LuckyService;
import com.fantingame.game.mywar.logic.pk.service.PkService;
import com.fantingame.game.mywar.logic.task.check.ITaskHitChecker;
import com.fantingame.game.mywar.logic.task.constant.TaskLibraryType;
import com.fantingame.game.mywar.logic.task.service.UserTaskService;
import com.fantingame.game.mywar.logic.tool.constant.ToolConstant;
import com.fantingame.game.mywar.logic.tool.constant.ToolType;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.model.UserExtInfo;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class HeroService implements ModuleEventHandler{
    @Autowired
	private UserHeroDaoCacheImpl userHeroDao;
    @Autowired
    private UserHeroSkillDao userHeroSkillDao;
    @Autowired
    private SystemHeroDaoCache systemHeroDaoCache;
    @Autowired
    private SystemHeroNameDaoCache systemHeroNameDaoCache;
    @Autowired
    private SystemHeroPromoteDaoCache systemHeroPromoteDaoCache;
    @Autowired
    private SystemCareerClearConfigDaoCache systemCareerClearConfigDaoCache;
    @Autowired
    private SystemCareerClearDaoCache systemCareerClearDaoCache;
    @Autowired
    private SystemHeroAtributeDaoCache systemHeroAtributeDaoCache;
    @Autowired
    private SystemHeroCareerAddDaoCache systemHeroCareerAddDaoCache;
    @Autowired
    private SystemHeroSkillDaoCache systemHeroSkillDaoCache;
    @Autowired
    private ConfigDataDaoCacheImpl configDataDao;
    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;
    @Autowired
    private ToolService toolService;
    @Autowired
    private SystemSkillLevelDao systemSkillLevelDao;
    @Autowired 
    private SystemSkillLevelMaxDaoCache systemSkillLevelMaxDaoCache;
    @Autowired
    private SystemHeroSkillConfigDaoCache systemHeroSkillConfigDaoCache;
    @Autowired
    private SystemHeroLevelDaoCache systemHeroLevelDaoCache;
    @Autowired
    private LuckyService luckyService;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private EquipService equipService;
    @Autowired
    private LifeService lifeService;
    @Autowired
    private SystemHeroPromoteStarDaoCache systemHeroPromoteStarDaoCache;
    @Autowired
    private SystemHeroInheritDaoCache systemHeroInheritDaoCache;
    @Autowired
    private GoodsDealService goodsDealService;
    @Autowired
    private GemstoneService gemstoneService;
    @Autowired
    private PkService pkService;
    @Autowired
    private UserCareerInfoDao userCareerInfoDao;
    @Autowired
    private ActivityService activityService;
    
    // 1 队长  0 非队长
    private static final int IS_TEAMLEADER = 1;
    private static final int NOT_TEAMLEADER = 0;
    
    /**
     * 添加英雄
     * @param userId
     * @param systemHeroId
     * @param isFirstAdd
     */
    public void addHero(String userId, final int systemHeroId, boolean isFirstAdd, String heroName, int goodsUseType, CommonGoodsBeanBO drop){
    	SystemHero systemHero = systemHeroDaoCache.getSystemHero(systemHeroId);
    	if(systemHero==null){
    		throw new ServiceException(SystemConstant.FAIL_CODE, "找不到英雄数据"+systemHeroId);
    	}
    	//初始化英雄
    	UserHero userHero = new UserHero();
    	userHero.setCreatedTime(new Date());
    	userHero.setExp(0);
    	userHero.setLevel(1);
    	userHero.setUserId(userId);
    	userHero.setUserHeroId(IDGenerator.getID());
    	if(isFirstAdd){
    		userHero.setPos(1);
    		userHero.setIsScene(1);
    	}else{
    		userHero.setPos(0);
    		userHero.setIsScene(0);
    	}
    	userHero.setStar(1);
    	userHero.setStatus(HERO_STATUS_1);
    	userHero.setSystemHeroId(systemHeroId);
    	userHero.setUpdatedTime(new Date());
    	
    	if (StringUtils.isBlank(heroName)) {
    		// TODO 读名字库，每个用户的英雄名称不重复，若是探索得到的npc英雄则用英雄表格的名字
    		HeroConstant heroConstant = HeroConstant.instance();

    		// 判断是否为特殊的英雄
    		if (heroConstant.getBaseCareerMap().containsKey(systemHero.getNationId())) {
    			Map<String, String> nameMap = getUserHeroNameMap(userId);
    			userHero.setHeroName(getNewHeroName(userId, systemHeroId, nameMap, new HashMap<String, String>()));
    		} else {
    			userHero.setHeroName(systemHero.getHeroName());
    		}   		
    	} else {
    		userHero.setHeroName(heroName);
    	}    	
    	
//    	List<UserHeroSkill> skillList = Lists.newArrayList();
    	//初始化需要获得的技能
//    	if(systemHero.getSkill01()!=0){
//    		skillList.add(initUserHeroSkill(userId,systemHero.getSkill01(),1,userHero.getUserHeroId(),11));
//    	}
//    	if(systemHero.getSkill02()!=0){
//    		skillList.add(initUserHeroSkill(userId,systemHero.getSkill02(),1,userHero.getUserHeroId(),12));
//    	}
//    	if(systemHero.getSkill03()!=0){
//    		skillList.add(initUserHeroSkill(userId,systemHero.getSkill03(),1,userHero.getUserHeroId(),13));
//    	}
//    	if(systemHero.getSkill04()!=0){
//    		skillList.add(initUserHeroSkill(userId,systemHero.getSkill04(),1,userHero.getUserHeroId(),14));
//    	}
//    	if(systemHero.getObjectSkill01()!=0){
//    		skillList.add(initUserHeroSkill(userId,systemHero.getObjectSkill01(),1,userHero.getUserHeroId(),21));
//    	}
//    	if(systemHero.getObjectSkill02()!=0){
//    		skillList.add(initUserHeroSkill(userId,systemHero.getObjectSkill02(),1,userHero.getUserHeroId(),22));
//    	}
//    	if(systemHero.getObjectSkill03()!=0){
//    		skillList.add(initUserHeroSkill(userId,systemHero.getObjectSkill03(),1,userHero.getUserHeroId(),23));
//    	}
//    	if(systemHero.getObjectSkill04()!=0){
//    		skillList.add(initUserHeroSkill(userId,systemHero.getObjectSkill04(),1,userHero.getUserHeroId(),24));
//    	}
    	
    	//英雄添加日志
    	userHeroOperatorLog(userId, userHero.getUserHeroId(), userHero.getSystemHeroId(), LogType.HERO_ADD, goodsUseType, userHero.getExp(), userHero.getLevel(), userHero.getPos(), 0, new Date(), systemHero.getHeroName());
//    	if(skillList.size()>0){
//        	userHeroSkillDao.addUserHeroSkillList(userId, skillList);
//    	}
    	//添加英雄
    	//设置英雄战斗力
    	userHero.setEffective(equipService.caculateUserHeroEquipLevel(userHero.getUserId(), userHero.getUserHeroId()));
    	userHeroDao.addUserHero(userHero);
    	//掉落包装
    	if(drop!=null){
    		GoodsHelper.addCommonDropUserHeroBO(drop, createUserHeroBO(userHero));
    		//技能设置
//    		for(UserHeroSkill userHeroSkill:skillList){
//    			GoodsHelper.addCommonDropUserHeroSkillBO(drop, creatUserHeroSkillBO(userHeroSkill));
//    		}
    	}
    	
    	userTaskService.updateTaskFinish(userId, 1, new ITaskHitChecker() {
			@Override
			public Map<String, Object> isHit(int systemTaskId, int taskLibrary,
					Map<String, String> params) {
				Map<String, Object> returnMap = Maps.newConcurrentMap();
				returnMap.put("rt", false);
				returnMap.put("tm", 1);
				
				if(taskLibrary == TaskLibraryType.GET_HERO){
					String heroId = params.get("heroId");
					if (Integer.parseInt(heroId) == systemHeroId) {
						returnMap.put("rt", true);
						return returnMap;					
					}
				}
				
				return returnMap;
			}
		});
    	
//    	for(UserHeroSkill userHeroSkill:skillList){
//    	  //写技能获得日志
//    	  userHeroSkillOperatorLog(userId, userHeroSkill.getUserHeroSkillId(), userHeroSkill.getSystemHeroSkillId(), LogType.SKILL_ADD, goodsUseType, userHeroSkill.getSkillExp(), userHeroSkill.getSkillLevel(), userHeroSkill.getPos(), 0,userHero.getUserHeroId(), new Date());
//    	}
    }
    
    /**
     * 获取在场景中的英雄模型id
     * @param userId
     * @return
     */
    public int getInSceneHeroId(String userId){
    	List<UserHero> list = userHeroDao.getUserHeroList(userId);
    	for(UserHero userHero:list){
    		if(userHero.getIsScene()==1){
    			SystemHero systemHero = systemHeroDaoCache.getSystemHero(userHero.getSystemHeroId());
    			return systemHero.getHeroId();
    		}
    	}
		LogSystem.error(new RuntimeIoException("找不到玩家在场景中的英雄！！这种事情也能发生？数据出异常了？"), "");
    	return 0;
    }
    
    /**
     * 添加团长技能
     * 
     * @param userId
     * @param systemHeroSkillId
     * @param skillLevel
     */
    public UserHeroSkill addHeroSkill(String userId, int systemHeroSkillId, int skillLevel, int goodsUseType, CommonGoodsBeanBO drop){
    	if(systemHeroSkillId == 0){
    		return null;
    	}
    	UserHeroSkill userHeroSkill = initUserHeroSkill(userId, systemHeroSkillId, skillLevel, 0);
    	userHeroSkillDao.addUserHeroSkill(userHeroSkill);
    	//写添加日志
    	userHeroSkillOperatorLog(userId, userHeroSkill.getUserHeroSkillId(), systemHeroSkillId, LogType.SKILL_ADD, goodsUseType, userHeroSkill.getSkillExp(), userHeroSkill.getSkillLevel(), userHeroSkill.getPos(), 0, "",new Date());
    	//掉落包装
    	if(drop!=null){
    		//技能设置
    		GoodsHelper.addCommonDropUserHeroSkillBO(drop, creatUserHeroSkillBO(userHeroSkill));
    	}
    	
    	return userHeroSkill;
    }    
    
    /**
     * 初始化英雄技能列表
     * @param userId
     * @param systemHeroSkillId
     * @param skillLevel
     * @param userHeroId
     * @param pos
     * @return
     */
    private UserHeroSkill initUserHeroSkill(String userId, int systemHeroSkillId, int skillLevel, int pos) {
    	SystemHeroSkill systemHeroSkill = systemHeroSkillDaoCache.getSystemHeroSkill(systemHeroSkillId, skillLevel);
    	if(systemHeroSkill==null){
    		throw new ServiceException(SystemConstant.FAIL_CODE, "不存在的技能常量id.systemHeroSkillId="+systemHeroSkillId+",skillLevel="+skillLevel);
    	}
    	UserHeroSkill heroSkill = new UserHeroSkill();
    	heroSkill.setCreatedTime(new Date());
    	heroSkill.setPos(pos);
    	heroSkill.setSkillLevel(skillLevel);
    	heroSkill.setSystemHeroSkillId(systemHeroSkillId);
    	heroSkill.setUpdatedTime(new Date());
//    	heroSkill.setUserHeroId(userHeroId);
    	heroSkill.setUserHeroSkillId(IDGenerator.getID());
    	heroSkill.setUserId(userId);
    	heroSkill.setSkillExp(0);
    	return heroSkill;
    }
    
    /**
     * 获取用户英雄列表
     * @param userId
     * @return
     */
    public List<UserHeroBO> getUserHeroBOList(String userId){
    	List<UserHero> list = userHeroDao.getUserHeroList(userId);
    	List<UserHeroBO> result = Lists.newArrayList();
    	Map<String, UserHero> battleMap = getBattleUserHeroMap(userId);		
		Map<String, UserHangupInfo> hangupInfoMap = lifeService.getHangupUserHeroIdMap(userId);
		
    	for(UserHero userHero : list){
    		result.add(createUserHeroBO(userHero, battleMap, hangupInfoMap));
    	}
    	return result;
    }
    
    /**
     * 查询单个客户端英雄对象
     * @param userId
     * @param userHeroId
     * @return
     */
    public UserHeroBO getUserHeroBO(String userId,String userHeroId){
    	UserHero userHero = userHeroDao.getUserHero(userId, userHeroId);
    	if (userHero == null)
    		return null;
    	
    	return createUserHeroBO(userHero);
    }
    
    /**
     * 根据英雄系统id获取用户英雄
     * 
     * @param userId
     * @param systemHeroId
     * @return
     */
    public UserHeroBO getUserHeroBO(String userId, int systemHeroId) {
    	UserHero userHero = userHeroDao.getUserHero(userId, systemHeroId);
    	if (userHero == null)
    		return null;
    	
    	return createUserHeroBO(userHero);
    }
    
    /**
     * 获取已上阵的英雄
     * 
     * @param userId
     * @return
     */
    public List<UserHero> getBattleUserHero(String userId) {
    	return userHeroDao.getUserHeroListInBattleOrNotInBattle(userId, 1);
    }
    
    public List<UserHeroBO> getBattleUserHeroBO(String userId) {
    	List<UserHeroBO> list = Lists.newArrayList();
    	List<UserHero> heroList = userHeroDao.getUserHeroListInBattleOrNotInBattle(userId, 1);
    	Map<String, UserHero> battleMap = getBattleUserHeroMap(userId);		
		Map<String, UserHangupInfo> hangupInfoMap = lifeService.getHangupUserHeroIdMap(userId);
    	
    	for (UserHero userHero : heroList) {
    		list.add(createUserHeroBO(userHero, battleMap, hangupInfoMap));
    	}
    	
    	return list;
    }
    
    /**
     * 创建客户端用的英雄对象
     * @param userHero
     * @return
     */
    private UserHeroBO createUserHeroBO(UserHero userHero){
    	UserHeroBO userHeroBO = new UserHeroBO();
    	userHeroBO.setExp(userHero.getExp());
    	userHeroBO.setIsScene(userHero.getIsScene());
    	userHeroBO.setLevel(userHero.getLevel());
    	userHeroBO.setPos(userHero.getPos());
    	userHeroBO.setSystemHeroId(userHero.getSystemHeroId());
    	userHeroBO.setUserHeroId(userHero.getUserHeroId());
    	userHeroBO.setUserId(userHero.getUserId());
    	userHeroBO.setIsTeamLeader(userHero.getIsScene());
    	userHeroBO.setEffective(userHero.getEffective());
    	userHeroBO.setStatus(getHeroStatus(userHero.getUserId(), userHero.getUserHeroId()));
    	userHeroBO.setStar(userHero.getStar());
    	userHeroBO.setHeroName(userHero.getHeroName());
    	return userHeroBO;
    }
    
    /**
     * 创建客户端用的英雄对象
     * @param userHero
     * @return
     */
    private UserHeroBO createUserHeroBO(UserHero userHero, Map<String, UserHero> heroMap, Map<String, UserHangupInfo> hangupInfoMap){
    	UserHeroBO userHeroBO = new UserHeroBO();
    	userHeroBO.setExp(userHero.getExp());
    	userHeroBO.setIsScene(userHero.getIsScene());
    	userHeroBO.setLevel(userHero.getLevel());
    	userHeroBO.setPos(userHero.getPos());
    	userHeroBO.setSystemHeroId(userHero.getSystemHeroId());
    	userHeroBO.setUserHeroId(userHero.getUserHeroId());
    	userHeroBO.setUserId(userHero.getUserId());
    	userHeroBO.setIsTeamLeader(userHero.getIsScene());
    	userHeroBO.setEffective(userHero.getEffective());
    	userHeroBO.setStatus(getHeroStatus(userHero, heroMap, hangupInfoMap));
    	userHeroBO.setStar(userHero.getStar());
    	userHeroBO.setHeroName(userHero.getHeroName());
    	return userHeroBO;
    }
    
    private static final int NOT_THIS_HERO = 2001;
    private static final int HAS_BE_TEAMLEADER = 2002;
    /**
     * 更换队长
     * 
     * @param userId
     * @param userHeroId
     * @return
     */
    public HeroAction_changeTeamLeaderRes changeTeamLeader(String userId, String userHeroId) {
    	if (StringUtils.isBlank(userHeroId))
    		throw new ServiceException(SystemErrorCode.PARAM_ERROR, "没有传参数");
    	
    	UserHero userHero = this.userHeroDao.getTeamLeaderHero(userId, IS_TEAMLEADER);
    	if (userHero == null)
    		throw new ServiceException(NOT_THIS_HERO, "没有这个英雄, userId = " + userId + ", userHeroId = " + userHeroId);
    	
    	if (userHero.getUserHeroId().equals(userHeroId))
    		throw new ServiceException(HAS_BE_TEAMLEADER, "这个王八蛋已经是队长了");
    		
    	this.userHeroDao.changeTeamLeader(userId, userHero.getUserHeroId(), NOT_TEAMLEADER);
    	this.userHeroDao.changeTeamLeader(userId, userHeroId, IS_TEAMLEADER);
    	
    	HeroAction_changeTeamLeaderRes res = new HeroAction_changeTeamLeaderRes();
    	return res;
    }
    
    /**
     * 获取用户队长英雄
     * 
     * @param userId
     * @return
     */
    public UserHeroBO getUserTeamLeader(String userId) {
    	UserHero userHero = this.userHeroDao.getTeamLeaderHero(userId, IS_TEAMLEADER);
    	if (userHero == null)
    		return null;
    	
    	return createUserHeroBO(userHero);
    }
    
    /**
     * 获取英雄技能信息列表
     * @param userId
     * @return
     */
    public List<UserHeroSkillBO> getUserHeroSkillBOList(String userId){
    	List<UserHeroSkill> list = userHeroSkillDao.getUserHeroSkillList(userId);
    	List<UserHeroSkillBO> result = Lists.newArrayList();
    	for(UserHeroSkill userHeroSkill:list){
    		result.add(creatUserHeroSkillBO(userHeroSkill));
    	}
    	return result;
    }
    
    private UserHeroSkillBO creatUserHeroSkillBO(UserHeroSkill userHeroSkill){
    	UserHeroSkillBO userHeroSkillBO = new UserHeroSkillBO();
    	userHeroSkillBO.setPos(userHeroSkill.getPos());
    	userHeroSkillBO.setSkillLevel(userHeroSkill.getSkillLevel());
    	userHeroSkillBO.setSystemHeroSkillId(userHeroSkill.getSystemHeroSkillId());
    	// userHeroSkillBO.setUserHeroId(userHeroSkill.getUserHeroId());
    	userHeroSkillBO.setUserHeroSkillId(userHeroSkill.getUserHeroSkillId());
    	userHeroSkillBO.setUserId(userHeroSkill.getUserId());
    	userHeroSkillBO.setSkillExp(userHeroSkill.getSkillExp());
    	return userHeroSkillBO;
    }
    
	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		if(ModuleEventConstant.AFTER_USER_CREAT_ROLE_EVENT.equals(handlerType)){
			String userId = baseModuleEvent.getStringValue("userId", "");
			
			// 势力id 1 联盟 2 部落 3 公共阵营
			int nationId = baseModuleEvent.getIntValue("nationId", 0);
			int systemHeroId = nationId;
			if (nationId == 1) {
				systemHeroId = configDataDao.getInt(ConfigKey.init_alliance_system_hero_id, 1);
			} else if (nationId == 2) {
				systemHeroId = configDataDao.getInt(ConfigKey.init_horde_system_hero_id, 2);
			} else if (nationId == 3) {
				systemHeroId = configDataDao.getInt(ConfigKey.init_common_system_hero_id, 3);
			}
			
			// TODO 添加第一个角色
			addHero(userId, systemHeroId, true, "xx", GoodsUseType.ADD_CREAT_ROLE, null);
		    // 走新手流程，学习团长技能
//			int[] array = configDataDaoCacheImpl.getIntArray(ConfigKey.init_team_system_skill_id, new int[]{1});
//			for(int i=0;i<array.length;i++){
//				addHeroSkill(userId, array[i], 1,GoodsUseType.ADD_CREAT_ROLE,null);
//			}
		}
	}
	
	/**
	 * 更新用户上阵英雄的经验及等级
	 * 
	 * @param userId
	 * @param addAmount
	 * @param goodsUseType
	 * @param drop
	 * @return
	 */
	public boolean updateHeroExpAndLevel(String userId, int addAmount, int goodsUseType, CommonGoodsBeanBO drop) {
		List<UserHero> userHeroList = this.getBattleUserHero(userId);
		User user = this.userService.getByUserId(userId);
		
		for (UserHero userHero : userHeroList) {
			SystemHero systemHero = this.systemHeroDaoCache.getSystemHero(userHero.getSystemHeroId());
			if (userHero.getLevel() >= systemHero.getMaxLevel() || userHero.getLevel() > user.getLevel())
				continue;
			
			SystemHeroLevel systemHeroLevel = this.systemHeroLevelDaoCache.getSystemHeroLevel(systemHero.getHeroColor(), userHero.getExp() + addAmount);
			boolean isLevelUp = false;
			if (systemHeroLevel.getLevel() > userHero.getLevel() && systemHeroLevel.getLevel() < user.getLevel()) {
				isLevelUp = true;
			}
			
			// 达到最高等级
			int level = systemHeroLevel.getLevel();
			int tempAmount = addAmount;
			if (systemHeroLevel.getLevel() == systemHero.getMaxLevel()) {
				tempAmount = systemHeroLevel.getExp() - userHero.getExp();
			} else {			
				// 英雄等级可以升级到和团队一致，但经验只减少一
				if (systemHeroLevel.getLevel() >= user.getLevel()) {
					level = user.getLevel();
					SystemHeroLevel heroLevel = this.systemHeroLevelDaoCache.getSystemHeroLevelByLevel(systemHero.getHeroColor(), level + 1);
				
					if (userHero.getExp() + addAmount >= heroLevel.getExp()) {
						tempAmount = heroLevel.getExp() - userHero.getExp() - 1;
					}				
				}			
			}
			if (tempAmount == 0)
				continue;
			
			// 修改经验和等级
			boolean success = this.userHeroDao.updateHeroExpAndLevel(userId, userHero.getUserHeroId(), userHero.getExp() + tempAmount, level);
			// 英雄操作日志
			if (success) {
				userHeroOperatorLog(userId, userHero.getUserHeroId(), userHero.getSystemHeroId(), LogType.HERO_UPGRADE, goodsUseType, userHero.getExp() + addAmount, systemHeroLevel.getLevel(), userHero.getPos(), addAmount, new Date(), systemHero.getHeroName());
			}			
			
			if (success || isLevelUp) {				
				userHero = this.userHeroDao.getUserHero(userId, userHero.getUserHeroId());
				GoodsHelper.addCommonDropUserHeroBO(drop, createUserHeroBO(userHero));
				
				if (drop.getGoodsList() == null) {
					drop.setGoodsList(GoodsHelper.parseDropGoods(GoodsType.HeroExp.intValue + ",0," + addAmount));
				} else {
					drop.getGoodsList().addAll(GoodsHelper.parseDropGoods(GoodsType.HeroExp.intValue + ",0," + addAmount));
				}				
			}
		}
		
		return true;
	}
	
	/**
	 * 判断是否在阵上
	 * 
	 * @param userId
	 * @param userHeroId
	 * @return
	 */
	private boolean isInBattleList(String userId, String userHeroId) {
		UserHero userHero = this.userHeroDao.getUserHero(userId, userHeroId);
		return userHero.getPos() > 0 ? true:false;
	}
	
	/**
	 * 更新英雄战斗力
	 * @param userHero
	 * @return
	 */
	public boolean updateHeroEffective(String userId, String userHeroId) {
		int effective = equipService.caculateUserHeroEquipLevel(userId, userHeroId);
		boolean success = userHeroDao.updateHeroEffective(userId, userHeroId, effective);
		
		// 判断是否在阵上
		if (isInBattleList(userId, userHeroId))
			userService.updateEffective(userId);
		return success;
	}
	
	@Override
	public List<String> getHandlerType() {
		List<String> list = Lists.newArrayList();
		list.add(ModuleEventConstant.AFTER_USER_CREAT_ROLE_EVENT);
		return list;
	}
	
	@Override
	public int order() {
		return 0;
	}
	
	/**
	 * 英雄操作日志
	 * @param userId
	 * @param userHeroId
	 * @param systemHeroId
	 * @param type
	 * @param goodsUseType
	 * @param exp
	 * @param level
	 * @param pos
	 * @param expNum
	 * @param opratorTime
	 * @param heroName
	 */
	public void userHeroOperatorLog(String userId, String userHeroId, int systemHeroId, int type,int goodsUseType,long exp, int level, int pos, int expNum, Date opratorTime, String heroName) {
		String time = DateUtils.getTimeStr(opratorTime);
		String now = DateUtils.getDateForLog();
		String sql = "INSERT INTO user_hero_log_" + now + "(USER_ID,USER_HERO_ID,HERO_ID,TYPE,SMALL_TPYE,EXP,LEVEL,POS,EXP_NUM,OPERATION_TIME,HERO_NAME) VALUES" + "('" + userId + "','" + userHeroId + "'," + systemHeroId + "," + type
				+ ","+goodsUseType+"," + exp + "," + level + "," + pos + "," + expNum + ",'" + time + "','" + heroName + "')";
		logService.unSynInsertLog(sql);
	}
	
	/**
	 * 技能操作日志
	 * @param userId
	 * @param userHeroSkillId
	 * @param systemHeroSkillId
	 * @param type
	 * @param goodsUseType
	 * @param skillExp
	 * @param level
	 * @param pos
	 * @param skillExpNum
	 * @param userHeroId
	 * @param opratorTime
	 */
	public void userHeroSkillOperatorLog(String userId, String userHeroSkillId, int systemHeroSkillId, int type,int goodsUseType,long skillExp, int level, int pos, int skillExpNum,String userHeroId, Date opratorTime) {
		String time = DateUtils.getTimeStr(opratorTime);
		String now = DateUtils.getDateForLog();
		String sql = "INSERT INTO user_hero_skill_log_" + now + "(USER_ID,USER_HERO_SKILL_ID,SYSTEM_HERO_SKILL_ID,TYPE,SMALL_TPYE,EXP,LEVEL,POS,EXP_NUM,USER_HERO_ID,OPERATION_TIME) VALUES" + "('" + userId + "','" + userHeroSkillId + "'," + systemHeroSkillId + "," + type
				+ ","+goodsUseType+"," + skillExp + "," + level + "," + pos + "," + skillExpNum + ",'"+userHeroId+"','" + time + "')";
		logService.unSynInsertLog(sql);
	}
	
	/**
	 * 判断有没有相同的武将在阵上
	 * 
	 * @param userId
	 * @param userHeroId
	 */
	@SuppressWarnings("unused")
	private void checkSameHeroOnEmbattle(String userId, String userHeroId,
			int pos) {

		UserHero userHero = userHeroDao.getUserHero(userId, userHeroId);
		SystemHero sourceHero = systemHeroDaoCache.getSystemHero(userHero.getSystemHeroId());
		List<UserHero> userHeroList = userHeroDao.getUserHeroListInBattleOrNotInBattle(userId, 1);
        int i=0;
		for (UserHero bo : userHeroList) {
			SystemHero systemHero = systemHeroDaoCache.getSystemHero(bo.getSystemHeroId());
			if (!userHero.getUserHeroId().equals(bo.getUserHeroId())
					&& systemHero.getStandId() == sourceHero.getStandId()
					&& bo.getPos() != pos) {// 如果是同一人物开将，且不是换掉它，则是不允许的(如:同一类肉盾型英雄不能超过3个)
				i++;
			}
		}
		if(i>=3){
			throw new ServiceException(CHANGE_POS_SAME_HERO_EXIST, "同类型英雄不能同时上阵超过3个");
		}
	}
	
	/**
	 * 判断上场武将个数有没有超过限制
	 * 
	 * @param userId
	 */
	private void checkBattleHeroLimit(String userId) {

		User user = this.userService.getByUserId(userId);
		int battleNum =userService.getBattleNum(user.getLevel());
		if (battleNum == 0) {
			throw new ServiceException(SystemConstant.FAIL_CODE,
					"数据异常，团队等级配置数据不存在.level[" + user.getLevel() + "]");
		}
		// 用户当前上场武将个数
		int userBattleCount = this.userHeroDao.getUserHeroListInBattleOrNotInBattle(userId, 1).size();
		// 人数已经达到上阵限制
		if (userBattleCount >= battleNum) {
			throw new ServiceException(CHANGE_POS_HERO_NUM_LIMIT,
					"布阵失败，上场武将个数超过限制.userBattleCount[" + userBattleCount + "]");
		}

	}
	
	/**
	 * 判断在阵上的武将是不是至少有一个
	 * 
	 * @param userId
	 */
	private void checkBattleHeroIsZero(String userId) {
		// 用户当前上场武将个数
		int userBattleCount = this.userHeroDao.getUserHeroListInBattleOrNotInBattle(userId, 1).size();
		// 下阵失败，至少有一个英雄在阵上
		if (userBattleCount <= 1) {
			throw new ServiceException(CHANGE_POS_HERO_NUM_IS_ZERO,
					"下阵失败，至少有一个英雄在阵上.userBattleCount[" + userBattleCount
							+ "]");
		}
	}
	// 同类型英雄不能同时上阵超过3个
	private static final int CHANGE_POS_SAME_HERO_EXIST = 2001;
	// 人数已经达到上阵限制
	private static final int CHANGE_POS_HERO_NUM_LIMIT = 2002;
	// 至少有一个英雄在阵上
	private static final int CHANGE_POS_HERO_NUM_IS_ZERO = 2003;
	// 队长不能下阵
	private static final int CHANGE_POS_HERO_TEAMLEADER_LIMIT = 2004;
	// 英雄已经被遣散了
	private static final int HERO_BE_DISBAND = 2005;
	//1-3号位置分别只能上阵 肉盾、 近战或远程输出 、 奶 
	// private static final int CHANGE_POS_HERO_STAND_ONLY_MT = 2004;
	// private static final int CHANGE_POS_HERO_STAND_ONLY_ACTTACK = 2005;
	// private static final int CHANGE_POS_HERO_STAND_ONLY_MILK = 2006;
	/**
	 * 上阵 下阵
	 * @param userId
	 * @param userHeroId
	 * @param pos
	 * @param handle
	 * @return
	 */
	public void changePos(String userId, String userHeroId, int pos, ModuleEventHandler handler) {
		LogSystem.debug("修改武将战斗站位.userId[" + userId + "], userHeroId[" + userHeroId
				+ "], pos[" + pos + "]");

		UserHero userHero = userHeroDao.getUserHero(userId, userHeroId);
		int oldPos = userHero.getPos();
		if (userHero.getStatus() == HERO_STATUS_0)
			throw new ServiceException(HERO_BE_DISBAND, "该英雄在酒馆中耶......userId=" + userId + ", userHeroId=" + userHeroId);		

		// 被替换的英雄
		UserHero targetUserHero = userHeroDao.getUserHeroByPos(userId, pos);
		if (pos != 0) {// 上阵			
			// 原来不在阵上，判断人数有没有超
			if (pos > 0 && oldPos == 0 && targetUserHero == null) {
				this.checkBattleHeroLimit(userId);
			}
			// 判断是不是有相同的武将在阵上
			// this.checkSameHeroOnEmbattle(userId, userHeroId, pos);
		} else {
			// 下阵，一定要最少有一个武将在阵上
			this.checkBattleHeroIsZero(userId);

			// TODO 1号位为队长位，队长只能跟换不能下阵
			if (oldPos == 1 && targetUserHero == null) 
				throw new ServiceException(CHANGE_POS_HERO_TEAMLEADER_LIMIT, "队长不能下阵");
		}
		// 修改的位置等于自己的位置的话,直接返回true
		if (oldPos == pos) {
			LogSystem.warn("修改英雄位置，修改的位置等于自己的位置？怎么会出现这个呢！！");
			return;
		}
		//1-3号位置分别只能上阵  MT  近战或远程输出   奶
//		SystemHero systemHero = systemHeroDaoCache.getSystemHero(userHero.getSystemHeroId());
//		if(pos==1){
//			if(systemHero.getStandId()!=1){
//				throw new ServiceException(CHANGE_POS_HERO_STAND_ONLY_MT, "1号位置只能上MT");
//			}
//		}else if(pos==2){
//			if(systemHero.getStandId()!=2&&systemHero.getStandId()!=3){
//				throw new ServiceException(CHANGE_POS_HERO_STAND_ONLY_ACTTACK, "2号位置只能上近战或远程输出");
//			}
//		}else if(pos==3){
//			if(systemHero.getStandId()!=4){
//				throw new ServiceException(CHANGE_POS_HERO_STAND_ONLY_MILK, "3号位置只能上治疗");
//			}
//		}
		// 先下阵
		if (targetUserHero != null) {
			if (targetUserHero.getIsScene() == IS_TEAMLEADER)
				this.userHeroDao.changeTeamLeader(userId, targetUserHero.getUserHeroId(), NOT_TEAMLEADER);
			
			if (oldPos > 0) {// 如果武将原来是阵上的，则是两个人换位置					
				boolean result = this.userHeroDao.changePos(userId, targetUserHero.getUserHeroId(), oldPos);
				if (!result)
					throw new ServiceException(SystemConstant.FAIL_CODE, "修改失败");	
				
				// 原来是队长位置的先取消队长
				if (oldPos == 1) {
					this.userHeroDao.changeTeamLeader(userId, userHeroId, NOT_TEAMLEADER);
					this.userHeroDao.changeTeamLeader(userId, targetUserHero.getUserHeroId(), IS_TEAMLEADER);
				}
			} else {
				boolean result = this.userHeroDao.changePos(userId, targetUserHero.getUserHeroId(), 0);
				if (!result)
					throw new ServiceException(SystemConstant.FAIL_CODE, "修改失败");
			}			
			
			ModuleEventBase  targetHeroUpdateEvent = new ModuleEventBase();
			targetHeroUpdateEvent.addStringValue("userId", userId);
			targetHeroUpdateEvent.addStringValue("userHeroId", targetUserHero.getUserHeroId());
			handler.handler("hero", targetHeroUpdateEvent);
		}

		boolean success = this.userHeroDao.changePos(userId, userHeroId, pos);
		if (!success) {
			throw new ServiceException(SystemConstant.FAIL_CODE, "修改失败");
		}
		
		if (pos == 1)
			this.userHeroDao.changeTeamLeader(userId, userHeroId, IS_TEAMLEADER);

		// 更新装等
		userService.updateEffective(userId);
		
		ModuleEventBase heroUpdateEvent = new ModuleEventBase();
		heroUpdateEvent.addStringValue("userId", userId);
		heroUpdateEvent.addStringValue("userHeroId", userHeroId);
		handler.handler("hero", heroUpdateEvent);
	}
	
	/**
	 * 是否在背包中 
	 * 
	 * @param userId
	 * @param userHeroId
	 * @return
	 */
	public boolean isInBackbag(String userId, String userHeroId) {
		UserHero userHero = this.userHeroDao.getUserHero(userId, userHeroId);
		if (userHero.getStatus() == HERO_STATUS_1) 
			return true;
		
		return false;
	}
	
	
	/**
	 * 技能上阵
	 * @param userId
	 * @param userHeroSkillId
	 * @param targetPos
	 * @return
	 */
	public List<UserHeroSkillPosChangeBO> changeSkillPos(String userId, String userHeroSkillId, int targetPos){
		List<UserHeroSkillPosChangeBO> result = Lists.newArrayList();
		//校验userHeroSkillId是否存在
		List<UserHeroSkill> userHeroSkillList = userHeroSkillDao.getUserHeroSkillList(userId);
		UserHeroSkill userHeroSkill = null;
		UserHeroSkill targetUserHeroSkill = null;
		for (UserHeroSkill skill:userHeroSkillList) {
			if (skill.getUserHeroSkillId().equals(userHeroSkillId)) {
				userHeroSkill = skill;
			}
			//0为下阵  
			if(targetPos != 0 && skill.getPos() == targetPos){
					targetUserHeroSkill = skill;
			}
		}
		
		if (userHeroSkill==null) {
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有找到技能！！userHeroSkillId="+userHeroSkillId);
		}
		//如果是上阵  则判断位置是否合理
		if (targetPos!=0) {
			SystemHeroSkill systemHeroSkill = systemHeroSkillDaoCache.getSystemHeroSkill(userHeroSkill.getSystemHeroSkillId(), userHeroSkill.getSkillLevel());
			if (systemHeroSkill==null) {
				LogSystem.warn("没有找到技能常量数据！！systemHeroSkillId="+userHeroSkill.getSystemHeroSkillId());
				throw new ServiceException(SystemConstant.FAIL_CODE, "没有找到技能常量数据！！systemHeroSkillId="+userHeroSkill.getSystemHeroSkillId());
			}
			//判断技能位置是否正确 
//			if(systemHeroSkill.getSkillType() != HeroSkillType.SKILL_TYPE_POSITION.length){
//				LogSystem.warn("技能常量数据异常！！systemHeroSkillId="+userHeroSkill.getSystemHeroSkillId()+",类型配置异常--skillType="+systemHeroSkill.getSkillType());
//				throw new ServiceException(SystemConstant.FAIL_CODE, "技能常量数据异常！！systemHeroSkillId="+userHeroSkill.getSystemHeroSkillId()+",skillType="+systemHeroSkill.getSkillType());
//			}
//			int minPosition = systemHeroSkill.getSkillType() * 10 + 1;
//			int maxPosition = systemHeroSkill.getSkillType() * 10 + HeroSkillType.SKILL_TYPE_POSITION[systemHeroSkill.getSkillType() - 1][1];
//			if(targetPos>maxPosition||targetPos<minPosition){
//				LogSystem.warn("位置异常！！targetPos="+targetPos+",minPos="+minPosition+",maxPos="+maxPosition);
//				throw new ServiceException(SystemConstant.FAIL_CODE, "位置异常！！targetPos="+targetPos+",minPos="+minPosition+",maxPos="+maxPosition);
//			}
		}
		
		if (targetUserHeroSkill!=null) {
			if (targetUserHeroSkill.getUserHeroSkillId().equals(userHeroSkill.getUserHeroSkillId())) {
				LogSystem.warn("没有修改，因为技能本身就在那个位置上.userId="+userId);
				return result;
			}
			
			//下阵目标技能
			boolean success = userHeroSkillDao.updateUserHeroSkillPos(userId, targetUserHeroSkill.getUserHeroSkillId(), 0);
			if (success) {
				UserHeroSkillPosChangeBO changeBO = new UserHeroSkillPosChangeBO();
				changeBO.setUserHeroSkillId(targetUserHeroSkill.getUserHeroSkillId());
				changeBO.setPos(0);
				result.add(changeBO);
			}
		}
		//上阵或下阵技能
		boolean success = userHeroSkillDao.updateUserHeroSkillPos(userId, userHeroSkill.getUserHeroSkillId(), targetPos);
		if (success) {
			UserHeroSkillPosChangeBO changeBO = new UserHeroSkillPosChangeBO();
			changeBO.setUserHeroSkillId(userHeroSkill.getUserHeroSkillId());
			changeBO.setPos(targetPos);
			result.add(changeBO);
		}
		return result;
	}
	
	/**
	 * 自动上阵
	 * @param userId
	 * @param handler
	 */
	public void autoAmenb(String userId,ModuleEventHandler handler){
		User user = userService.getByUserId(userId);
		int maxBattleNum = userService.getBattleNum(user.getLevel());
		List<UserHero> all = getTheSameLimitStandAllList(userId);
		LogSystem.debug("自动上阵，总体参与到分配中的英雄个数为:" + all.size());
		for(int i=1; i <= maxBattleNum; i++) {
			UserHero oldUserHero = userHeroDao.getUserHeroByPos(userId, i);
			UserHero targetUserHero = getMaxEffective(i, all);
			//没有找到的话 说明已经没有英雄了
			if(targetUserHero == null){
				break;
			}
			int oldHeroPos = -1;
			int newHeroPos = -1;
			if(oldUserHero != null) {
				//比较2个即将互换的英雄是否为同一个英雄  如果是 则不处理
				if(!targetUserHero.getUserHeroId().equals(oldUserHero.getUserHeroId())) {
                    //下阵老英雄
					oldHeroPos = 0;
					newHeroPos = i;
				}
			} else {
				newHeroPos = i;
			}
			//更新位置
			//老英雄处理
			if(oldHeroPos != -1) {
				LogSystem.debug("自动上阵["+oldUserHero.getUserHeroId()+",从pos="+oldUserHero.getPos()+",变成"+0+"]");
				userHeroDao.changePos(userId, oldUserHero.getUserHeroId(), 0);
				
				ModuleEventBase heroUpdateEvent = new ModuleEventBase();
				heroUpdateEvent.addStringValue("userId", userId);
				heroUpdateEvent.addStringValue("userHeroId", oldUserHero.getUserHeroId());
				handler.handler("hero", heroUpdateEvent);				
			}
			
			//新英雄处理
			if(newHeroPos != -1) {
				LogSystem.debug("自动上阵["+targetUserHero.getUserHeroId()+",从pos="+targetUserHero.getPos()+",变成"+i+"]");
				userHeroDao.changePos(userId, targetUserHero.getUserHeroId(), i);
				
				ModuleEventBase heroUpdateEventNew = new ModuleEventBase();
				heroUpdateEventNew.addStringValue("userId", userId);
				heroUpdateEventNew.addStringValue("userHeroId", targetUserHero.getUserHeroId());
				handler.handler("hero", heroUpdateEventNew);
			}
			
			//移除掉list中已分配了位置的对象
			all.remove(targetUserHero);
		}
		
		// 更新装等
		userService.updateEffective(userId);
	} 
	/**
	 * 保障同一个standid不会超过3个
	 * @param userId
	 * @return
	 */
	private List<UserHero> getTheSameLimitStandAllList(String userId){
		List<UserHero> allTemp = userHeroDao.getUserHeroList(userId);
		List<UserHero> result = Lists.newArrayList();
		//按战斗力进行排序
		Collections.sort(allTemp, new Comparator<UserHero>() {
			@Override
			public int compare(UserHero o1, UserHero o2) {
				if(o1.getEffective()>o2.getEffective()){
					return -1;
				}else if(o1.getEffective()<o2.getEffective()){
					return 1;
				}
				return 0;
			}
		});
		
		Map<Integer,Integer> theSameStandTypeContains = Maps.newHashMap();
		for (UserHero userHero : allTemp) {
			SystemHero systemHero = systemHeroDaoCache.getSystemHero(userHero.getSystemHeroId());
			Integer count = theSameStandTypeContains.get(systemHero.getStandId());
			if (count==null) {
				count=1;
			} else {
				count= count+1;
			}
			
			if (count<=3) {
				result.add(userHero);
			} else {
				continue;
			}
			theSameStandTypeContains.put(systemHero.getStandId(), count);
		}
		return result;
	}
	/**
	 * 获取某个位置上最合适的英雄对象
	 * @param pos
	 * @param list
	 * @return
	 */
	private UserHero getMaxEffective(int pos,List<UserHero> list){
		if (pos>3) {
			if (list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		}
		
		for (UserHero userHero : list) {
			SystemHero systemHero = systemHeroDaoCache.getSystemHero(userHero.getSystemHeroId());
			if (pos == 1) {
				if (systemHero.getStandId() == HeroStandType.MT) {
					return userHero;
				}
			} else if(pos == 2) {
				if (systemHero.getStandId() == HeroStandType.YC || systemHero.getStandId() == HeroStandType.JZ) {
					return userHero;
				}
			} else if(pos == 3) {
				if (systemHero.getStandId() == HeroStandType.NAI) {
					return userHero;
				}
			}
		}
		LogSystem.error(new ServiceException(SystemConstant.FAIL_CODE, "列表中没有找到,这个是不应该出现的 pos="+pos+",size="+list.size()), "");
		return null;
	}
	
	// 已经学习了这个团长技能
	private static final int HAS_STUDY_TEAM_SKILL = 2001;
	// 用户等级不够
	private static final int USER_LEVEL_NOT_ENOUGH = 2002;
	// 用户vip等级不足
	private static final int USER_VIP_LEVEL_NOT_ENOUGH = 2003; 	
	/**
	 * 学习团长技能
	 * 
	 * @param userId
	 * @param toolId
	 */
	public HeroAction_studyLeaderSkillRes studyLeaderSkill(String userId, int systemHeroSkillId) {
		User user = this.userService.getByUserId(userId);
		int openLevel = this.configDataDao.getInt(ConfigKey.team_skill_open_level, 6);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足，暂未开放");
		
		SystemHeroSkill skill = this.systemHeroSkillDaoCache.getSystemHeroSkill(systemHeroSkillId, 1);		
		if (skill == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "找不到对应的英雄技能" + systemHeroSkillId + " skillLevel = 1");
		
		UserHeroSkill userHeroSkill = this.userHeroSkillDao.getUserHeroSkill(userId, skill.getSystemHeroSkillId());
		if (userHeroSkill != null)
			throw new ServiceException(HAS_STUDY_TEAM_SKILL, "用户" + userId + "已经学习了该技能书");		
		
		SystemHeroSkillConfig systemHeroSkillConfig = systemHeroSkillConfigDaoCache.getSystemHeroSkillConfig(systemHeroSkillId);
		if (systemHeroSkillConfig == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有团队技能配置，[" + systemHeroSkillId + "]");
		
		// 团队技能开启条件 判断
		if (user.getLevel() < systemHeroSkillConfig.getLevel())
			throw new ServiceException(USER_LEVEL_NOT_ENOUGH, "玩家等级不足");
		
		if (user.getVipExp() < systemHeroSkillConfig.getVipLevel()) 
			throw new ServiceException(USER_VIP_LEVEL_NOT_ENOUGH, "玩家VIP等级不足");
		
		if (user.getMoney() < systemHeroSkillConfig.getMoney())
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "玩家钻石不足");
		
		if (user.getGold() < systemHeroSkillConfig.getGold())
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "玩家金币不足");
		
		// 扣除相应的钻石或金币
		if (systemHeroSkillConfig.getMoney() > 0) {
			boolean success = this.userService.reduceMoney(userId, systemHeroSkillConfig.getMoney(), GoodsUseType.REDUCE_STUDY_SKILL);
			if (!success)
				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "玩家钻石不足");
		}
		
		if (systemHeroSkillConfig.getGold() > 0) {
			boolean success = this.userService.reduceGold(userId, systemHeroSkillConfig.getGold(), GoodsUseType.REDUCE_STUDY_SKILL);
			if (!success)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "玩家金币不足");
		}
		
		// 学习技能
		user = this.userService.getByUserId(userId);
		userHeroSkill = addHeroSkill(userId, skill.getSystemHeroSkillId(), skill.getSkillLevel(), GoodsUseType.ADD_STUDY_SKILL, null);
		UserHeroSkillBO userHeroSkillBO = creatUserHeroSkillBO(userHeroSkill);		
		
		this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_TEAM_SKILL, 1);
		HeroAction_studyLeaderSkillRes res = new HeroAction_studyLeaderSkillRes();
		res.setUserHeroSkillBO(userHeroSkillBO);
		res.setGold(user.getGold());
		res.setMoney(user.getMoney());
		return res;
	}
	
	// 未学习该技能
	private static final int HAS_NOT_STUDY_THIS_SKILL = 2001;
	// 用户没有该技能书道具
	private static final int HAS_NO_SKILL_TOOL = 2002;
	// 团长技能已经升级到顶级
	private static final int SKILL_LEVEL_IS_TOP = 2004;
	/**
	 * 升级团长技能
	 * 
	 * @param userId
	 * @param toolIdList
	 */
	public HeroAction_upgradeLeaderSkillRes upgradeLeaderSkill(String userId, String userHeroSkillId, List<SkillToolBO> toolList) {
		if (toolList == null)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，toolList = null");
		
		User user = this.userService.getByUserId(userId);
		int openLevel = this.configDataDao.getInt(ConfigKey.team_skill_open_level, 6);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足，暂未开放");
		
		UserHeroSkill userHeroSkill = this.userHeroSkillDao.getUserHeroSkill(userId, userHeroSkillId);
		if (userHeroSkill == null)
			throw new ServiceException(HAS_NOT_STUDY_THIS_SKILL, "用户[" + userId + "]未学习该技能，userHeroSkillId[" + userHeroSkillId + "]");		
		
		SystemHeroSkill systemHeroSkill = this.systemHeroSkillDaoCache.getSystemHeroSkill(userHeroSkill.getSystemHeroSkillId(), userHeroSkill.getSkillLevel());
		if (systemHeroSkill == null) {
			String message = "没有团长技能配置,systemHeroSkillId[" + userHeroSkill.getSystemHeroSkillId() + "]" + ",skillLevel[" + userHeroSkill.getSkillLevel() + "]";
			throw new ServiceException(SystemConstant.FAIL_CODE, message); 
		}
		
		int color = systemHeroSkill.getColor();
		SystemSkillLevelMax levelMax = systemSkillLevelMaxDaoCache.getSystemSkillLevelMax(color);
		if (levelMax == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "数据配置错误？color[" + color + "]");
		
		int maxLevel = levelMax.getMax();
		if (userHeroSkill.getSkillLevel() >= maxLevel)
			throw new ServiceException(SKILL_LEVEL_IS_TOP, "该技能已升级到顶级");
		
		// 获取单位消耗金币
		SystemSkillLevel currentSkillLevel = this.systemSkillLevelDao.getSkillExp(userHeroSkill.getSkillLevel(), color);
		int gold = currentSkillLevel.getGold();
		
		// 计算能获取的经验、所需消耗的金币
		int totalExp = 0;
		int totalGold = 0;
		for (SkillToolBO skillTool : toolList) {
			UserToolBO userToolBO = this.toolService.getUserToolBO(userId, skillTool.getToolId());
			if (userToolBO == null)
				throw new ServiceException(HAS_NO_SKILL_TOOL, "用户" + userId + "没有该技能书，toolId[" + skillTool.getToolId() + "]");
			
			if (userToolBO.getToolNum() < skillTool.getToolNum()) {
				String message = "使用技能书失败，用户道具不足.userId[" + userId + "], toolId[" + skillTool.getToolId() + "], toolNum[" + skillTool.getToolNum() + "]";
				throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, message); 
			}
			
			SystemTool systemTool = toolService.getSystemTool(skillTool.getToolId());
			if (systemTool != null && systemTool.getType() != ToolType.TEAM_SKILL_BOOK)
				throw new ServiceException(SystemErrorCode.PARAM_ERROR, "道具Id[" + skillTool.getToolId() + "]，道具类型[" + systemTool.getType() + "]不是技能书");
			
			totalExp = totalExp + systemTool.getNum() * skillTool.getToolNum();
			totalGold = totalGold + gold * skillTool.getToolNum();
		}
		
		// TODO 看看能否中彩蛋
		boolean isDouble = luckyService.isSkillExpDouble();
		if (isDouble)
			totalExp = totalExp * 2;
		
		// 升级团长技能
		totalExp = userHeroSkill.getSkillExp() + totalExp;		
		SystemSkillLevel systemSkillLevel = this.systemSkillLevelDao.getSystemSkillLevel(totalExp, systemHeroSkill.getColor());
		
		int level = userHeroSkill.getSkillLevel();
		if (systemSkillLevel != null)
			level = systemSkillLevel.getLevel();
		
		if (level >= maxLevel) {
			level = maxLevel;
			systemSkillLevel = this.systemSkillLevelDao.getSkillExp(level, color);
			totalExp = systemSkillLevel.getExp();
		}		
		
		if (user.getGold() < totalGold)
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		// 吃技能书，减道具
		for (SkillToolBO skillTool : toolList) {
			boolean success = this.toolService.reduceTool(userId, skillTool.getToolId(), skillTool.getToolNum(), GoodsUseType.REDUCE_UPGRADE_LEADER_SKILL);
			if (!success) {
				String message = "使用技能书失败，用户道具不足.userId[" + userId + "], toolId[" + skillTool.getToolId() + "], toolNum[" + skillTool.getToolNum() + "]";
				throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, message);
			}
		}		
		
		// 扣除消耗的金币		
		boolean success = this.userService.reduceGold(userId, totalGold, GoodsUseType.REDUCE_UPGRADE_LEADER_SKILL);
		if (!success) {
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		}
		
		// 更新团长技能 不能在service 修改缓存的值
		success = userHeroSkillDao.updateUserHeroSkill(userId, userHeroSkill.getUserHeroSkillId(), level, totalExp);
		if (!success) 
			throw new ServiceException(SystemConstant.FAIL_CODE, "更新团长技能失败 userId[" + userId + ",userHeroSkillId[" + userHeroSkill.getUserHeroSkillId() + "]"); 
		
    	// 写添加日志
    	userHeroSkillOperatorLog(userId, userHeroSkill.getUserHeroSkillId(), userHeroSkill.getSystemHeroSkillId(), LogType.SKILL_UPGRADE, GoodsUseType.ADD_UPGRADE_SKILL_BOOK, userHeroSkill.getSkillExp(), userHeroSkill.getSkillLevel(), userHeroSkill.getPos(), 0, "", new Date());
    	this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_TEAM_SKILL, 1);
    	
    	// 返回
    	user = this.userService.getByUserId(userId);
    	HeroAction_upgradeLeaderSkillRes res = new HeroAction_upgradeLeaderSkillRes();
    	res.setUserHeroSkillId(userHeroSkillId);
    	res.setSkillLevel(level);
    	res.setSkillExp(totalExp);
    	res.setGold(user.getGold());
    	res.setSkillToolBOList(toolList);
    	return res;
	}
	
	// 2  英雄在阵上  3 正在挖矿  4 正在修花圃  5 正在钓鱼
	private final static int HERO_IN_BATTLE = 2;
	private final static int HERO_IN_MINING = 3;
	private final static int HERO_IN_FLOWER = 4;
	private final static int HERO_IN_FINING = 5;
	
	// 类别 1 挖矿 2 花圃 3 钓鱼
	private static final int CATEGORY_MINE = 1;
	private static final int CATEGORY_FLOWER = 2;
	private static final int CATEGORY_FISH = 3;
	private int getHeroStatus(String userId, String userHeroId) {
		Map<String, UserHero> battleMap = getBattleUserHeroMap(userId);
		Map<String, UserHangupInfo> hangupInfoMap = lifeService.getHangupUserHeroIdMap(userId);
		
		UserHero userHero = this.userHeroDao.getUserHero(userId, userHeroId);
		return getHeroStatus(userHero, battleMap, hangupInfoMap);
	}
	
	private int getHeroStatus(UserHero userHero, Map<String, UserHero> battleMap, Map<String, UserHangupInfo> hangupInfoMap) {
		if (battleMap.containsKey(userHero.getUserHeroId()))
			return HERO_IN_BATTLE;
		
		if (hangupInfoMap.containsKey(userHero.getUserHeroId())) {
			UserHangupInfo hangupInfo = hangupInfoMap.get(userHero.getUserHeroId());
			if (hangupInfo.getCategory() == CATEGORY_MINE)
				return HERO_IN_MINING;
			
			if (hangupInfo.getCategory() == CATEGORY_FLOWER) 
				return HERO_IN_FLOWER;
			
			if (hangupInfo.getCategory() == CATEGORY_FISH)
				return HERO_IN_FINING;
		}			
		
		return userHero.getStatus();
	}
	
	/**
	 * 获取用户英雄个数
	 *  
	 * @param userId
	 * @param status 0 不在背包  1 在背包
	 * @return
	 */
	public int getUserHeroCount(String userId, int status) {
		return this.userHeroDao.getUserHeroCount(userId, status);
	}
	
	/**
	 * 获取用户拥有的系统英雄map
	 * 
	 * @param userId
	 * @return
	 */
	public Map<Integer, Integer> getUserSystemHeroIdMap(String userId) {
		List<UserHero> userHeroList = this.userHeroDao.getUserHeroList(userId);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (UserHero userHero : userHeroList) {
			map.put(userHero.getSystemHeroId(), userHero.getSystemHeroId());
		}		
		
		return map;
	}
	
	/**
	 * 获取系统英雄
	 * 
	 * @param systemHeroId
	 * @return
	 */
	public SystemHero getSystemHero(int systemHeroId) {
		return this.systemHeroDaoCache.getSystemHero(systemHeroId);
	}
	
	public SystemHeroAttribute getSystemHeroAttribute(int systemHeroId) {
		return this.systemHeroAtributeDaoCache.getSystemHeroAttribute(systemHeroId);
	}
	
	/**
	 * 在酒馆中
	 */
	private static final int HERO_STATUS_0 = 0;
	/**
	 * 在背包中
	 */
	private static final int HERO_STATUS_1 = 1;
	private static final int HERO_IN_TAVERN = 2001;
	private static final int TEAMLEADER_CAN_NOT_BE_DISBAND = 2002;
	private static final int HERO_IN_HANGUP = 2004;
	private static final int HERO_IN_BATTLE_ERROR = 2005;
	private static final int HERO_IN_PK_DEFENCE = 2006;
	/**
	 * 遣散英雄
	 * 
	 * @param userId
	 * @param serHeroId
	 */
	public void disband(String userId, String userHeroId) {
		UserHero userHero = this.userHeroDao.getUserHero(userId, userHeroId);
		if (userHero == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有这个英雄，userId[" + userId + "],userHeroId[" + userHeroId + "]");
		
		if (userHero.getStatus() == HERO_STATUS_0)
			throw new ServiceException(HERO_IN_TAVERN, "英雄已在酒馆中,userId[" + userId + "],userHeroId[" + userHeroId + "]");
		
		if (userHero.getIsScene() == IS_TEAMLEADER)
			throw new ServiceException(TEAMLEADER_CAN_NOT_BE_DISBAND, "队长不能被遣散。");
		
		Map<String, UserHangupInfo> map = this.lifeService.getHangupUserHeroIdMap(userId);
		if (map.containsKey(userHero.getUserHeroId()))
			throw new ServiceException(HERO_IN_HANGUP, "该英雄正在挂机");
		
		if (userHero.getPos() > 0)
			throw new ServiceException(HERO_IN_BATTLE_ERROR, "该英雄正在阵上");
		
		Map<String, String> pkMap = pkService.getPkDefenceHeroMap(userId);
		if (pkMap.containsKey(userHeroId))
			throw new ServiceException(HERO_IN_PK_DEFENCE, "该英雄正在竞技场的防守阵容上");
		
		this.userHeroDao.updateHeroStatus(userId, userHeroId, HERO_STATUS_0);
		SystemHero systemHero = this.systemHeroDaoCache.getSystemHero(userHero.getSystemHeroId());
		userHeroOperatorLog(userId, userHero.getUserHeroId(), userHero.getSystemHeroId(), LogType.HERO_ADD, LogType.HERO_BE_DISBAND, userHero.getExp(), userHero.getLevel(), userHero.getPos(), userHero.getExp(), new Date(), systemHero.getHeroName());
	}

	/**
	 * 召回/遣散英雄
	 * 
	 * @param userId
	 * @param userHeroId
	 * @param status
	 * @return
	 */
	public boolean updateHeroStatus(String userId, String userHeroId, int status) {
		return this.userHeroDao.updateHeroStatus(userId, userHeroId, status);
	}
	
	/**
	 * 查看阵容
	 * 
	 * @param userId
	 * @return
	 */
	public HeroAction_getUserBattleInfoRes getUserBattleInfo(String userId) {
		HeroAction_getUserBattleInfoRes res = new HeroAction_getUserBattleInfoRes();
		List<UserHero> heroList = this.getBattleUserHero(userId);
		
		List<UserHeroBO> userHeroList = Lists.newArrayList();
		List<UserEquipBO> userEquipList = Lists.newArrayList();
		Map<String, UserHero> battleMap = getBattleUserHeroMap(userId);		
		Map<String, UserHangupInfo> hangupInfoMap = lifeService.getHangupUserHeroIdMap(userId);
		
		for (UserHero userHero : heroList) {
			UserHeroBO userHeroBO = createUserHeroBO(userHero, battleMap, hangupInfoMap);
			userHeroList.add(userHeroBO);
			
			List<UserEquipBO> equipList = this.equipService.getUserHeroEquipList(userId, userHeroBO.getUserHeroId());
			userEquipList.addAll(equipList);
		}
		
		List<UserGemstoneBO> userGemstoneList = gemstoneService.getUserGemstoneBOList(userId, 1);
		
		User user = this.userService.getByUserId(userId);
		UserExtInfo extInfo = this.userService.getUserExtInfo(userId);
		UserBO userBO = this.userService.creatUserBO(user, extInfo);
		res.setUserEquipList(userEquipList);
		res.setUserHeroList(userHeroList);
		res.setUserGemstoneList(userGemstoneList);
		res.setUser(userBO);
		return res;
	}
	
	/**
	 * 战斗中的英雄map
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, UserHero> getBattleUserHeroMap(String userId) {
		List<UserHero> list = getBattleUserHero(userId);
		Map<String, UserHero> map = Maps.newConcurrentMap();
		for (UserHero userHero : list) {
			map.put(userHero.getUserHeroId(), userHero);
		}
		
		return map;
	}
	
	private final static int MAX_STAR_LEVEL = 10;
	private final static int PROMOTE_TYPE_MATERIAL = 1;
	private final static int PROMOTE_TYPE_TOOL = 2;
	
	private final static int STAR_LEVEL_HAS_MAX = 2001;
	private final static int IS_NOT_LUCKY_STONE = 2002;
	/**
	 * 升星
	 * 
	 * @param userId
	 * @param type
	 * @return
	 */
	public HeroAction_promoteHeroStarRes promoteHeroStar(String userId, int type, String userHeroId, GoodsBeanBO goodBean) {
		User user = this.userService.getByUserId(userId);
		int openLevel = this.configDataDao.getInt(ConfigKey.hero_promote_star_open_level, 13);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "未开英雄升星等级，userLevel=" + user.getLevel());
		
		if (type != PROMOTE_TYPE_MATERIAL && type != PROMOTE_TYPE_TOOL)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不正确，type=" + type);
		
		UserHero userHero = this.userHeroDao.getUserHero(userId, userHeroId);
		if (userHero.getStar() >= MAX_STAR_LEVEL)
			throw new ServiceException(STAR_LEVEL_HAS_MAX, "该英雄星数都爆了.........");
		
		SystemHero systemHero = this.systemHeroDaoCache.getSystemHero(userHero.getSystemHeroId());
		SystemHeroPromoteStar promoteStar = systemHeroPromoteStarDaoCache.getSystemHeroPromoteStar(type, userHero.getStar());
		if (promoteStar == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有这个升星的数据，Why？？ type = " + type + ", star = " + userHero.getStar());
		
		// 公式
		int cost = this.configDataDao.getInt(ConfigKey.hero_promote_star_cost, 100);
		int gold = userHero.getLevel() * userHero.getStar() * systemHero.getHeroColor() * cost;		
		
		if (user.getGold() < gold)
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(promoteStar.getNeedMaterial());
		for (GoodsBeanBO good : goodsList) {
			if (good.getGoodsType() == GoodsType.tool.intValue) {
				UserToolBO userToolBO = this.toolService.getUserToolBO(userId, good.getGoodsId());
				if (userToolBO == null || userToolBO.getToolNum() < good.getGoodsNum())
					throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足,toolType=" + good.getGoodsType() + ",toolId=" + good.getGoodsId());
			} else if (good.getGoodsType() == GoodsType.equip.intValue) {
				List<UserEquip> equipList = this.equipService.getUnWearEquipList(userId, good.getGoodsId());
				if (equipList == null || equipList.size() < good.getGoodsNum())
					throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "装备不足,toolType=" + good.getGoodsType() + ",toolId=" + good.getGoodsId());
			}
		}
		
		// 概率
		int upperNum = promoteStar.getUpperNum();
		// 道具升星需要看几率  TODO 幸运石的道具id
		if (type == PROMOTE_TYPE_TOOL) {
			if (goodBean != null && goodBean.getGoodsNum() > 0) {
				if (!(goodBean.getGoodsType() == GoodsType.tool.intValue && toolService.isLuckyStone(goodBean.getGoodsId())))
					throw new ServiceException(IS_NOT_LUCKY_STONE, "这货不是幸运石，toolType=" + goodBean.getGoodsType() + ", toolId=" + goodBean.getGoodsId());
				
				SystemTool systemTool = this.toolService.getSystemTool(goodBean.getGoodsId());
				upperNum = upperNum + systemTool.getNum() * goodBean.getGoodsNum();
			}
		
			this.toolService.reduceTool(userId, goodBean.getGoodsId(), goodBean.getGoodsNum(), GoodsUseType.REDUCE_BY_PROMOTE_HERO_STAR);
		}
		
		// 扣除道具
		goodsDealService.reduceGoods(userId, goodsList, GoodsUseType.REDUCE_BY_PROMOTE_HERO_STAR);
		int random = RandomUtils.getRandomNum(1, 10000);
		boolean success = false;
		if (random >= promoteStar.getLowerNum() && random <= upperNum)
			success = true;

		int star = userHero.getStar();
		if (success) {
			this.userHeroDao.updateHeroStar(userId, userHeroId, star + 1);
			
			userHeroOperatorLog(userId, userHero.getUserHeroId(), userHero.getSystemHeroId(), LogType.HERO_PROMOTE_STAR_SUCCESS, GoodsUseType.ADD_PROMOTE_HERO_STAR, star + 1, userHero.getLevel(), userHero.getPos(), 1, new Date(), systemHero.getHeroName());
		} else {
			userHeroOperatorLog(userId, userHero.getUserHeroId(), userHero.getSystemHeroId(), LogType.HERO_PROMOTE_STAR_FAIL, GoodsUseType.ADD_PROMOTE_HERO_STAR, star, userHero.getLevel(), userHero.getPos(), 0, new Date(), systemHero.getHeroName());
		}
			
		user = this.userService.getByUserId(userId);
		userHero = this.userHeroDao.getUserHero(userId, userHeroId);
		
		HeroAction_promoteHeroStarRes res = new HeroAction_promoteHeroStarRes();
		res.setGoodsList(goodsList);
		res.setUserHeroBO(createUserHeroBO(userHero));
		res.setGold(user.getGold());
		res.setMoney(user.getMoney());
		return res;
	}
	
	private final static int STAR_MUST_BIGGER = 2001;
	/**
	 * 英雄传承
	 * 
	 * @param userId
	 * @param userHeroId
	 * @param targetUserHeroId
	 * @return
	 */
	public HeroAction_heroInheritRes heroInherit(String userId, String userHeroId, String targetUserHeroId) {
		UserHero userHero = this.userHeroDao.getUserHero(userId, userHeroId);
		UserHero targetUserHero = this.userHeroDao.getUserHero(userId, targetUserHeroId);

		SystemHero systemHero = this.systemHeroDaoCache.getSystemHero(userHero.getSystemHeroId());
		SystemHero targetSystemHero = this.systemHeroDaoCache.getSystemHero(targetUserHero.getSystemHeroId());
		
		if (userHero == null || targetUserHero == null)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，没有这个英雄，userHeroId=" + userHeroId + ", targetUserHeroId=" + targetUserHeroId);
		
		if (userHero.getStar() <= targetUserHero.getStar())
			throw new ServiceException(STAR_MUST_BIGGER, "传承者的星数必须大于被传承者");
		
		SystemHeroInherit heroInherit = this.systemHeroInheritDaoCache.getSystemHeroInherit(userHero.getStar());
		if (heroInherit == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有这个英雄传承的数据，Why？？  star = " + userHero.getStar());
		
		// 公式
		int cost = this.configDataDao.getInt(ConfigKey.hero_inherit_cost, 100);
		int gold = userHero.getLevel() * userHero.getStar() * systemHero.getHeroColor() * cost;
		
		User user = this.userService.getByUserId(userId);
		if (user.getGold() < gold)
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
			
		List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(heroInherit.getNeedMaterial());
		for (GoodsBeanBO goodBean : goodsList) {
			if (goodBean.getGoodsType() == GoodsType.tool.intValue) {
				UserToolBO userToolBO = this.toolService.getUserToolBO(userId, goodBean.getGoodsId());
				if (userToolBO == null || userToolBO.getToolNum() < goodBean.getGoodsNum())
					throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足,toolType=" + goodBean.getGoodsType() + ",toolId=" + goodBean.getGoodsId());
			} else if (goodBean.getGoodsType() == GoodsType.equip.intValue) {
				List<UserEquip> equipList = this.equipService.getUnWearEquipList(userId, goodBean.getGoodsId());
				if (equipList == null || equipList.size() < goodBean.getGoodsNum())
					throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "装备不足,toolType=" + goodBean.getGoodsType() + ",toolId=" + goodBean.getGoodsId());
			}
		}
		
		goodsDealService.reduceGoods(userId, goodsList, GoodsUseType.REDECE_HERO_INHERIT);
		
		int star = userHero.getStar();
		int targetHeroStar = targetUserHero.getStar();
		// 修改星数		
		if (this.userHeroDao.updateHeroStar(userId, userHeroId, 1)) {
			userHeroOperatorLog(userId, userHero.getUserHeroId(), userHero.getSystemHeroId(), LogType.HERO_INHERIT_REDUCE, 
					GoodsUseType.REDECE_HERO_INHERIT, 1, userHero.getLevel(), userHero.getPos(), -(star - 1), new Date(), systemHero.getHeroName());
		}
		
		if (this.userHeroDao.updateHeroStar(userId, targetUserHeroId, star)) {
			userHeroOperatorLog(userId, targetUserHero.getUserHeroId(), targetUserHero.getSystemHeroId(), LogType.HERO_INHERIT_ADD, 
					GoodsUseType.ADD_HERO_INHERIT, star, targetUserHero.getLevel(), targetUserHero.getPos(), star - targetHeroStar, new Date(), targetSystemHero.getHeroName());
		}
		
		user = this.userService.getByUserId(userId);
		HeroAction_heroInheritRes res = new HeroAction_heroInheritRes();
		List<UserHeroBO> userHeroList = Lists.newArrayList();
		userHeroList.add(createUserHeroBO(this.userHeroDao.getUserHero(userId, userHeroId)));
		userHeroList.add(createUserHeroBO(this.userHeroDao.getUserHero(userId, targetUserHeroId)));
		
		res.setGoodsList(goodsList);
		res.setGold(user.getGold());
		res.setMoney(user.getMoney());
		res.setUserHeroList(userHeroList);
		return res;
	}
	
	public Map<String, String> getUserHeroNameMap(String userId) {
		List<UserHero> userHeroList = this.userHeroDao.getUserHeroList(userId);
		Map<String, String> map = Maps.newConcurrentMap();
		
		for (UserHero userHero : userHeroList) {
			map.put(userHero.getHeroName(), userHero.getHeroName());
		}
		
		return map;
	}
	
	// 0 女 1 男
	/**
	 * 获取新英雄的名称
	 * 
	 * @param userId
	 * @param tempMap
	 * @return
	 */
	public String getNewHeroName(String userId, int systemHeroId, Map<String, String> nameMap, Map<String, String> tempMap) {
		SystemHero systemHero = this.systemHeroDaoCache.getSystemHero(systemHeroId);
		Map<Integer, List<String>> map = this.systemHeroNameDaoCache.getHeroNameMap();
		
		String heroName = "";		
		List<String> nameList = map.get(systemHero.getSexId());
		
		while (true) {
			heroName = "";
			int randomIndex = RandomUtils.getRandomNum(0, nameList.size() - 1);
			heroName = nameList.get(randomIndex);
			
			if (!nameMap.containsKey(heroName) && !tempMap.containsKey(heroName))
				break;
		}
		
		return heroName;
	}
	
	/**
	 * 获取用户职业解锁信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserCareerInfoBO> getUserCareerClearInfo(String userId) {
		List<UserCareerInfo> infoList = userCareerInfoDao.getUserCareerInfoList(userId);
		User user = this.userService.getByUserId(userId);
		List<SystemCareerClear> systemCareerClearList = this.systemCareerClearDaoCache.getAllList();
		if (systemCareerClearList == null || systemCareerClearList.size() == 0)
			throw new ServiceException(SystemErrorCode.DATA_NULL, "没有职业解锁的数据？？");
		
		// TODO 防止后续增加新的职业
		List<SystemCareerClear> systemList = Lists.newArrayList();
		for (SystemCareerClear career : systemCareerClearList) {
			SystemHero systemHero = this.systemHeroDaoCache.getSystemHeroByDetailCareerId(career.getDetailedCareerId());
			if (systemHero.getNationId() != user.getCamp())
				continue;
			
			systemList.add(career);
		}
		
		if (infoList == null || infoList.size() != systemList.size()) {
			List<UserCareerInfo> addList = Lists.newArrayList();			
			for (SystemCareerClear career : systemList) {				
				UserCareerInfo info = new UserCareerInfo();
				info.setUserId(userId);
				info.setLevel(0);
				info.setDetailedCareerId(career.getDetailedCareerId());
				info.setCreatedTime(new Date());
				info.setUpdatedTime(new Date());
				
				addList.add(info);
			}
			
			this.userCareerInfoDao.addList(userId, addList);
			infoList = userCareerInfoDao.getUserCareerInfoList(userId);
		}		
		
		List<UserCareerInfoBO> infoBOList = Lists.newArrayList();
		for (UserCareerInfo info : infoList) {
			infoBOList.add(createUserCareerInfoBO(info));
		}
		
		return infoBOList;
	}
	
	private UserCareerInfoBO createUserCareerInfoBO(UserCareerInfo userCareerInfo) {
		UserCareerInfoBO infoBO = new UserCareerInfoBO();
		infoBO.setDetailedCareerId(userCareerInfo.getDetailedCareerId());
		infoBO.setLevel(userCareerInfo.getLevel());
		
		return infoBO;
	}
	
	/**
	 * 职业解锁检查
	 */
	private void checkCareerClear(String userId, SystemHero systemHero) {
		List<SystemHero> systemHeroList = this.systemHeroDaoCache.getSameCareerHero(systemHero.getCareerId(), systemHero.getNationId());
		for (SystemHero hero : systemHeroList) {
			// 基础职业没有解锁的数据
			if (hero.getHeroColor() < systemHero.getHeroColor() && hero.getHeroColor() != 1) {
				UserCareerInfo info = this.userCareerInfoDao.getUserCareerInfo(userId, hero.getDetailedCareerId());
				if (info == null)
					continue;
				
				SystemCareerClear careerClear = this.systemCareerClearDaoCache.getSystemCareerClear(hero.getDetailedCareerId());
				if (info.getLevel() < careerClear.getLevel()) 
					throw new ServiceException(CLEAR_OVER_LEVEL, "不能越级解锁呀？？userId= " + userId + "systemheroId=" + systemHero.getSystemHeroId());
			}
		}	
	}
	
	private final static int CLEAR_HAS_DONE = 2001;
	private final static int CLEAR_OVER_LEVEL = 2002;
	/**
	 * 职业解锁
	 * 
	 * @param userId
	 * @param systemHeroId
	 * @return
	 */
	public HeroAction_careerClearRes careerClear(String userId, int detailedCareerId) {
		User user = this.userService.getByUserId(userId);		
		SystemHero systemHero = this.systemHeroDaoCache.getSystemHeroByDetailCareerId(detailedCareerId);
		if (systemHero == null)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "没有这个英雄？？detailedCareerId=" + detailedCareerId);
		
		SystemCareerClearConfig config = this.systemCareerClearConfigDaoCache.getSystemCareerClearConfig(systemHero.getCareerId());
		if (config == null)
			throw new ServiceException(SystemErrorCode.DATA_NULL, "职业解锁配置表，木有数据？");
			
		if (config.getLevel() > user.getLevel())
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "用户等级不足，未到职业开放等级，careerId=" + systemHero.getCareerId());
		
		SystemCareerClear careerClear = this.systemCareerClearDaoCache.getSystemCareerClear(detailedCareerId);
		if (careerClear == null)
			throw new ServiceException(SystemErrorCode.DATA_NULL, "职业解锁表，没有配置数据哦！！");
		
		// 不能越级解锁
		checkCareerClear(userId, systemHero);
		UserCareerInfo userCareerInfo = this.userCareerInfoDao.getUserCareerInfo(userId, detailedCareerId);
		if (userCareerInfo.getLevel() >= careerClear.getLevel())
			throw new ServiceException(CLEAR_HAS_DONE, "该职业已经解锁完毕，还解锁个蛋丫！");
		
		int needJobExp = careerClear.getJobExp() / careerClear.getLevel();
		if (user.getJobExp() < needJobExp)
			throw new ServiceException(SystemErrorCode.JOBEXP_NOT_ENOUGH, "用户职业经验不足");
		
		if (!this.userService.reduceJobExp(userId, needJobExp, GoodsUseType.REDUCE_CAREER_CLEAR))
			throw new ServiceException(SystemErrorCode.JOBEXP_NOT_ENOUGH, "用户职业经验不足");
		
		// 增加层数
		int jobLevel = userCareerInfo.getLevel() + 1;
		this.userCareerInfoDao.updateUserCareerInfo(userId, detailedCareerId, jobLevel);
		userCareerInfo = this.userCareerInfoDao.getUserCareerInfo(userId, detailedCareerId);
		user = this.userService.getByUserId(userId);
		
		this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_CLEAR_CAREER, 1);
		HeroAction_careerClearRes res = new HeroAction_careerClearRes();
		res.setJobExp(user.getJobExp());
		res.setUserCareerInfo(createUserCareerInfoBO(userCareerInfo));
		return res;
	}
	
	private final static int NOT_CLEAR = 2001;
	private final static int CAN_NOT_RETURN = 2002;
	/**
	 * 回退职业经验
	 * 
	 * @param userId
	 * @param systemHeroId
	 * @return
	 */
	public HeroAction_returnJobExpRes returnJobExp(String userId, int detailedCareerId) {
		UserCareerInfo userCareerInfo = this.userCareerInfoDao.getUserCareerInfo(userId, detailedCareerId);
		if (userCareerInfo == null)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "用户没有这个职业的数据？userId=" + userId 
					+ ",detailedCareerId=" + detailedCareerId);
		
		if (userCareerInfo.getLevel() <= 0)
			throw new ServiceException(NOT_CLEAR, "都没解锁，回退个毛丫");
		
		SystemCareerClear careerClear = this.systemCareerClearDaoCache.getSystemCareerClear(detailedCareerId);
		if (careerClear == null)
			throw new ServiceException(SystemErrorCode.DATA_NULL, "职业解锁表，没有配置数据哦！！");
		
		if (userCareerInfo.getLevel() >= careerClear.getLevel())
			throw new ServiceException(CAN_NOT_RETURN, "解锁完毕的，不能再回退了");
		
		UserToolBO userTool = this.toolService.getUserToolBO(userId, ToolConstant.RETURN_JOB_EXP_TOOL_ID);
		if (userTool == null || userTool.getToolNum() <= 0)
			throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");
		
		int addJobExp = userCareerInfo.getLevel() * (careerClear.getJobExp() / careerClear.getLevel());	
		// 先更新层数，再返回职业经验
		this.userCareerInfoDao.updateUserCareerInfo(userId, detailedCareerId, 0);
		this.userService.addJobExp(userId, addJobExp, true, GoodsUseType.ADD_RETURN_JOB_EXP);
		
		HeroAction_returnJobExpRes res = new HeroAction_returnJobExpRes();
		userCareerInfo = this.userCareerInfoDao.getUserCareerInfo(userId, detailedCareerId);
		User user = this.userService.getByUserId(userId);
		
		res.setJobExp(user.getJobExp());
		res.setUserCareerInfo(createUserCareerInfoBO(userCareerInfo));
		res.setTool(GoodsHelper.parseDropGood(GoodsType.tool.intValue, ToolConstant.RETURN_JOB_EXP_TOOL_ID, 1));
		return res;
	}
	
	private final static int HAS_NOT_CLEAR = 2001;
	/**
	 * 英雄进阶
	 * 
	 * @param userId
	 * @param userHeroId
	 * @param systemHeroId
	 * @return
	 */
	public HeroAction_heroPromoteRes heroPromote(String userId, String userHeroId, int proSystemHeroId) {
		UserHero userHero = this.userHeroDao.getUserHero(userId, userHeroId);
		SystemHero systemHero = this.systemHeroDaoCache.getSystemHero(proSystemHeroId);
		UserCareerInfo info = this.userCareerInfoDao.getUserCareerInfo(userId, systemHero.getDetailedCareerId());		
		
		SystemCareerClear careerClear = this.systemCareerClearDaoCache.getSystemCareerClear(systemHero.getDetailedCareerId());
		if (careerClear == null)
			throw new ServiceException(SystemErrorCode.DATA_NULL, "职业解锁表，没有配置数据哦！！systemHeroId=" + proSystemHeroId);
		
		if (info.getLevel() < careerClear.getLevel())
			throw new ServiceException(HAS_NOT_CLEAR, "该职业还尚未解锁哦！");
		
		SystemHeroPromote systemHeroPromote = this.systemHeroPromoteDaoCache.getSystemHeroPromote(userHero.getSystemHeroId(), proSystemHeroId);
		if (systemHeroPromote == null)
			throw new ServiceException(SystemErrorCode.DATA_NULL, "英雄进阶表，没有配置数据哦！！systemHeroId=" + userHero.getSystemHeroId() 
					+ ", proSystemHeroId=" + proSystemHeroId);
		
		if (userHero.getLevel() < systemHeroPromote.getHeroLevel())
			throw new ServiceException(SystemErrorCode.HERO_LEVEL_NOT_ENOUGH, "用户英雄等级不足");
		
		// 扣除用户的材料
		List<GoodsBeanBO> toolList = Lists.newArrayList();
		List<String> userEquipIdList = Lists.newArrayList();
		List<String> userGemstoneIdList = Lists.newArrayList();
		
		// 检查用户的材料是否足够
		if (StringUtils.isNotBlank(systemHeroPromote.getMaterial())) {
			List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(systemHeroPromote.getMaterial());
			this.userService.checkUserToolIsEnough(userId, goodsList);			
		
			this.userService.reduceUserTool(userId, goodsList, GoodsUseType.REDUCE_HERO_PROMOTE, toolList, userEquipIdList, userGemstoneIdList);
		}
		
		// 给用户替换系统英雄
		this.userHeroDao.updateHeroSystemHeroId(userId, userHeroId, proSystemHeroId);
		userHero = this.userHeroDao.getUserHero(userId, userHeroId);
		
		HeroAction_heroPromoteRes res = new HeroAction_heroPromoteRes();
		res.setUserHeroBO(createUserHeroBO(userHero));
		res.setToolList(toolList);
		res.setUserEquipIdList(userEquipIdList);
		res.setUserGemstoneIdList(userGemstoneIdList);
		return res;
	}
}
