package com.fantingame.game.mywar.logic.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventManager;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.dao.RuntimeDataDao;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.model.RuntimeData;
import com.fantingame.game.common.model.SystemConfigData;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.cuser.model.UserMapper;
import com.fantingame.game.cuser.service.UserMapperService;
import com.fantingame.game.log.service.LogService;
import com.fantingame.game.msgbody.client.activity.SystemActivityBO;
import com.fantingame.game.msgbody.client.boss.UserBossInfoBO;
import com.fantingame.game.msgbody.client.explore.UserExploreInfoBO;
import com.fantingame.game.msgbody.client.gemstone.UserGemstoneBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.hero.UserHeroSkillBO;
import com.fantingame.game.msgbody.client.tool.UserToolBO;
import com.fantingame.game.msgbody.client.user.UserAction_changeUserNameRes;
import com.fantingame.game.msgbody.notify.boss.WorldBossInfoBO;
import com.fantingame.game.msgbody.notify.explore.User_midNightPushNotify;
import com.fantingame.game.msgbody.notify.life.WeatherInfoBO;
import com.fantingame.game.msgbody.notify.task.UserTaskBO;
import com.fantingame.game.msgbody.notify.user.UserBO;
import com.fantingame.game.msgbody.notify.user.UserPropertiesBO;
import com.fantingame.game.msgbody.notify.user.User_pushNotify;
import com.fantingame.game.msgbody.notify.user.User_pushUserPropertiesNotify;
import com.fantingame.game.mywar.logic.achievement.check.IAchievementChecker;
import com.fantingame.game.mywar.logic.achievement.constant.AchievementConstant;
import com.fantingame.game.mywar.logic.achievement.service.AchievementService;
import com.fantingame.game.mywar.logic.activity.service.ActivityService;
import com.fantingame.game.mywar.logic.boss.service.BossService;
import com.fantingame.game.mywar.logic.charge.dao.PaymentLogDao;
import com.fantingame.game.mywar.logic.charge.model.PaymentLog;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.equip.model.UserEquip;
import com.fantingame.game.mywar.logic.equip.service.EquipService;
import com.fantingame.game.mywar.logic.explore.service.ExploreService;
import com.fantingame.game.mywar.logic.gemstone.model.UserGemstone;
import com.fantingame.game.mywar.logic.gemstone.service.GemstoneService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.hero.model.UserHero;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.life.service.LifeService;
import com.fantingame.game.mywar.logic.mail.service.MailService;
import com.fantingame.game.mywar.logic.mall.service.MallService;
import com.fantingame.game.mywar.logic.pack.service.PackService;
import com.fantingame.game.mywar.logic.pk.model.UserPkInfo;
import com.fantingame.game.mywar.logic.pk.service.PkService;
import com.fantingame.game.mywar.logic.prestige.service.PrestigeService;
import com.fantingame.game.mywar.logic.scene.service.SceneService;
import com.fantingame.game.mywar.logic.setting.model.UserSettingInfo;
import com.fantingame.game.mywar.logic.setting.service.SettingService;
import com.fantingame.game.mywar.logic.task.check.ITaskHitChecker;
import com.fantingame.game.mywar.logic.task.constant.TaskLibraryType;
import com.fantingame.game.mywar.logic.task.service.UserTaskService;
import com.fantingame.game.mywar.logic.tool.constant.ToolConstant;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.bean.UserObject;
import com.fantingame.game.mywar.logic.user.constant.UserPushProperties;
import com.fantingame.game.mywar.logic.user.dao.UserDao;
import com.fantingame.game.mywar.logic.user.dao.UserMapInfoDao;
import com.fantingame.game.mywar.logic.user.dao.cache.SystemTeamExpDaoCache;
import com.fantingame.game.mywar.logic.user.dao.cache.UserExtInfoDaoCacheImpl;
import com.fantingame.game.mywar.logic.user.dao.mysql.UserOnlineLogDaoMysql;
import com.fantingame.game.mywar.logic.user.model.SystemTeamExp;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.model.UserExtInfo;
import com.fantingame.game.mywar.logic.user.model.UserMapInfo;
import com.fantingame.game.mywar.logic.user.model.UserOnlineLog;
import com.fantingame.game.mywar.logic.user.util.RoleUtils;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapperService userMapperService;
    @Autowired
    private RuntimeDataDao runtimeDataDao;
    @Autowired
    private LogService logService;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private HeroService heroService;
    @Autowired
    private SystemTeamExpDaoCache systemTeamExpDaoCache;
	@Autowired
	private PaymentLogDao paymentLogDao;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private UserExtInfoDaoCacheImpl userExtInfoDaoCacheImpl;
	@Autowired
	private ToolService toolService;
	@Autowired
	private SceneService sceneService;
	@Autowired
	private PrestigeService prestigeService;
	@Autowired
	private PackService packService;
	@Autowired
	private ExploreService exploreService;
	@Autowired
	private EquipService equipService;
	@Autowired
	private MailService mailService;
	@Autowired
	private UserOnlineLogDaoMysql userOnlineLogDao;
	@Autowired
	private UserMapInfoDao userMapInfoDao;
	@Autowired
	private PkService pkService;
	@Autowired
	private SettingService settingService;
	@Autowired
	private BossService bossService;
	@Autowired
	private MallService mallService;
	@Autowired
	private GemstoneService gemstoneService;
	@Autowired
	private AchievementService achievementService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private LifeService lifeService;
	
	//在线用户缓存
	private Map<String, UserObject> onLineUserIdMap = new ConcurrentHashMap<String, UserObject>();
	
    //登陆还没有创角
    public static final int NOT_CTEATED_ROLE = 2001;
    //登陆 被封号了
    public static final int USER_IS_BAND = 2002;
    /**
	 * 用户已经存在
	*/
	public final static int CREATE_USER_NAME_EXIST = 2001;
	/**
	 * 用户已经存在
	 */
	public final static int CREATE_USER_EXIST = 2003;
	/**
	 * 用户名非法
	 */
	public final static int CREATE_USERNAME_INVAILD = 2002;
	
	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public User getByUserId(String userId){
		return userDao.getByUserId(userId);
	}
	
	/**
	 * 根据游戏id获取用户
	 * 
	 * @param ftId
	 * @return
	 */
	public User getByFtId(String ftId) {
		return userDao.getByFtId(ftId);
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user){
		return userDao.add(user);
	}
	
	/**
	 * 获取在线用户对象
	 * 
	 * @param userId
	 * @return
	 */
	public UserObject getAllOnlineUserObject(String userId) {
		return onLineUserIdMap.get(userId);
	}
	
	/**
	 * 获取所有在线的用户id列表
	 * 
	 * @return
	 */
	public Set<String> getAllOnlineUserId(){
		return onLineUserIdMap.keySet();
	}
	
	/**
	 * 判断用户是否在线
	 * 
	 * @param userId
	 * @return
	 */
	public boolean isOnline(String userId) {
		return onLineUserIdMap.containsKey(userId);
	}
	
	/**
	 * 返回所有用户的ids
	 * 
	 * @return
	 */
	public List<String> getAllUserIds() {
		return userDao.getAllUserIds();
	}
	
   /**
    * 用户登录
    * @param userId
    * @param userIp
    */
   public void userLogin(String userId,String userIp) {
	   User role = userDao.getByUserId(userId);
	   if(role==null){
		   throw new ServiceException(NOT_CTEATED_ROLE, "还未创角，userId="+userId+",ip="+userIp);
	   }
	   
	   /*
		* 判断用户是否已经被封号
		* 如果还在封号时间内，返回异常，
		*/
	   Date dueTime = role.getDueTime();
	   Date now = new Date();
	   if (dueTime != null && now.before(dueTime)) { 
		   throw new ServiceException(USER_IS_BAND, "用户已经被封号.userId[" + userId + "]");
	   }
	   
	   // 体力及活力恢复
	   // this.checkPowerAdd(userId, false);
	   // this.checkActivityAdd(userId, false);
	   settingService.refreshDailyTips(userId);
	   
	   // 分发用户登录事件
	   ModuleEventBase eventBase = new ModuleEventBase();
	   eventBase.addStringValue("userId", userId);
	   ModuleEventManager.getInstance().dispatchEvent(ModuleEventConstant.USER_LOGIN_EVENT, eventBase);
	   MsgDispatchCenter.disPatchUserMsg("User_push", userId, getRolePushInfo(userId));
	   
	   //放入在线列表中
	   UserMapper userMapper = this.userMapperService.getUserMapperByUserId(userId);
	   UserObject userObject = new UserObject();
	   userObject.setCamp(role.getCamp());
	   userObject.setPartnerId(userMapper.getPartnerId());
	   onLineUserIdMap.put(userId, userObject);
	   //写登陆日志
	   userLoginLog(userId,role.getLevel(), userIp, new Date());
   }
   
   /**
    * 获取用户登录后需要推送的信息
    * @param userId
    * @return
    */
   private User_pushNotify getRolePushInfo(String userId){
	   User_pushNotify pushNotify = new User_pushNotify();
	   User role = userDao.getByUserId(userId);
	   UserExtInfo extInfo = userExtInfoDaoCacheImpl.getUserExtInfo(userId);
	   //用户对象
	   pushNotify.setUserBO(creatUserBO(role,extInfo));
	   //任务列表
	   List<UserTaskBO> userTaskList = userTaskService.getUserTaskList(userId, UserTaskService.TASK_STATUS_ALL);
	   pushNotify.setUserTaskList(userTaskList);
	   //英雄列表
	   List<UserHeroBO> userHeroList = heroService.getUserHeroBOList(userId);
	   pushNotify.setUserHeroList(userHeroList);
	   // 用户宝石列表
	   List<UserGemstoneBO> userGemstoneList = gemstoneService.getUserGemstoneBOList(userId);
	   pushNotify.setUserGemstoneList(userGemstoneList);
	   //技能列表
	   List<UserHeroSkillBO> userHeroSkillList = heroService.getUserHeroSkillBOList(userId);
	   pushNotify.setUserHeroSkillList(userHeroSkillList);
	   //道具列表
	   pushNotify.setUserToolList(toolService.getUserToolList(userId));
	   //装备列表
	   pushNotify.setUserEquipList(equipService.getUserEquipList(userId));
	   //已开启场景id列表
	   pushNotify.setUserOpenSceneIdList(sceneService.getOpenUserSceneIdList(userId));
	   //已领取声望奖励id列表
	   pushNotify.setUserPrestigeRewardIdList(prestigeService.getUserHaveGetRewardsIds(userId));
	   //用户背包扩展信息
	   // pushNotify.setUserPackExtendList(packService.getUserPackExtendBOList(userId));
	   
	   // 系统活动列表
	   List<SystemActivityBO> systemActivityList = this.activityService.getAllList(0);
	   pushNotify.setSystemActivityList(systemActivityList);
	   // 每月签到状态
	   int loginRewardStatus30 = this.activityService.checkLoginRewardStatus30(userId);
	   pushNotify.setLoginRewardStatus30(loginRewardStatus30);
	   
	   // 是否新邮件
	   boolean isNewEmail = mailService.hasNewMail(userId);
	   if (isNewEmail) {
		   pushNotify.setMailStatus(1);
	   } else {
		   pushNotify.setMailStatus(0);
	   }
	   
	   pushNotify.setRecordGuideStep(extInfo.getRecordGuideStep());
	   UserMapInfo userMapInfo = this.userMapInfoDao.getUserMapInfo(userId);
	   if (userMapInfo == null) {
		   pushNotify.setOpenMaps("");
	   } else {
		   pushNotify.setOpenMaps(userMapInfo.getMaps());
	   }
	   
	   UserPkInfo userPkInfo = this.pkService.getUserPkInfoByUserId(userId);
	   if (userPkInfo == null) {
		   pushNotify.setPkRank(-1);
	   } else {
		   pushNotify.setPkRank(userPkInfo.getRank());
	   }
	   
	   UserSettingInfo info = this.settingService.getUserSettingInfo(userId);
	   if (info == null) {
		   pushNotify.setDisplayNum(0);
		   pushNotify.setTips("");
	   } else {
		   pushNotify.setDisplayNum(info.getDisplayNum());
		   pushNotify.setTips(info.getDailyTips());
	   }   
	   
	   // 服务器当前时间
	   pushNotify.setCurrentTime(new Date().getTime());
	   
	   // 推送世界boss信息
	   WorldBossInfoBO bossInfoBO = bossService.getWorldBossInfo();
	   if (bossInfoBO == null)
		   bossInfoBO = new WorldBossInfoBO();
	   pushNotify.setBossInfoBO(bossInfoBO);
	   
	   // 获取用户boss的信息
	   UserBossInfoBO userBossInfo = bossService.getUserBossInfo(userId);
	   pushNotify.setUserBossInfo(userBossInfo);
	   
	   // 获取当前天气信息
	   WeatherInfoBO weatherInfoBO = this.lifeService.getCurrentWeatherInfoBO();
	   pushNotify.setWeatherInfo(weatherInfoBO);
	   
	   //系统配置对象
	   List<SystemConfigData> list = configDataDao.getAllConfig();
	   Map<String,String> systemConfigDataMap = Maps.newHashMap();
	   for(SystemConfigData configData:list){
		   if(configData.getIsSendClient()!=0){
			   systemConfigDataMap.put(configData.getConfigKey(), configData.getConfigValue());
		   }
	   }
	   pushNotify.setSystemConfig(systemConfigDataMap);
	   return pushNotify;
   }

   /**
    * 午夜推送
    * 
    * @param userId
    * @return
    */
   public User_midNightPushNotify midnightPushNotify(String userId) {
	   User_midNightPushNotify push = new User_midNightPushNotify();
	   // 探索
	   UserExploreInfoBO userExploreInfoBO = this.exploreService.pushExploreInfo(userId);
	   if (userExploreInfoBO == null)
		   userExploreInfoBO = new UserExploreInfoBO();
	   
	   push.setUserExploreInfoBO(userExploreInfoBO);
	   push.setSystemTime(new Date().getTime());
	   return push;
   } 
   
   /**
    * 创建客户端对象
    * @param user
    * @return
    */
   public UserBO creatUserBO(User user, UserExtInfo extInfo){
	   UserBO userBO = new UserBO();
//	   userBO.setActivity(user.getActivity());
	   // long activityAddInterval = configDataDao.getInt(ConfigKey.activity_add_inteval, 6)*60 * 1000;
	   // userBO.setActivityResumTime(user.getActivityResumTime().getTime()+activityAddInterval);
	   userBO.setExp(user.getExp());
	   userBO.setFtId((int)user.getFtId());
	   userBO.setLevel(user.getLevel());
	   userBO.setMoney(user.getMoney());
	   userBO.setGold(user.getGold());
	   userBO.setCamp(user.getCamp());
	   userBO.setPower(user.getPower());
	   // long pwerAddInterval = configDataDao.getInt(ConfigKey.power_add_inteval, 6)*60 * 1000;
	   // userBO.setPowerResumTime(user.getPowerResumTime().getTime()+pwerAddInterval);
	   userBO.setRegTime(user.getRegTime().getTime());
	   userBO.setRoleName(user.getName());
	   userBO.setUserId(user.getUserId());
	   userBO.setVipLevel(user.getVipLevel());
	   userBO.setHonour(user.getHonour());
//	   userBO.setPrestige(user.getPrestige());
//	   userBO.setPrestigeLevel(user.getPrestigeLevel());
	   userBO.setVipExp(user.getVipExp());
	   userBO.setEffective(extInfo.getEffective());
	   userBO.setAllOnLineTime(extInfo.getAllOnlineTime());
	   userBO.setPrePosition(extInfo.getPrePosition());
	   userBO.setPackExtendTimes(extInfo.getPackExtendTimes());
	   userBO.setStorehouseExtendTimes(extInfo.getStorehouseExtendTimes());
	   userBO.setJobExp(user.getJobExp());
	   LogSystem.debug(user.getName()+"用户登录成功出生点在"+extInfo.getPrePosition());
	   return userBO;
   }
   
   /**
    * 创建角色
    * @param userId
    * @param roleName
    * @param roleId
    */
   public void reg(String userId,String roleName,int roleId,String ip){
	   RoleUtils.isValidUsername(roleName);
	   UserMapper userMapper = userMapperService.getUserMapperByUserId(userId);
	   if (userMapper == null) {
		   LogSystem.warn("用户创建失败,usermapper表没记录.userId[" + userId + "], roleId[" + roleId + "], roleName[" + roleName + "]");
		   throw new ServiceException(SystemConstant.FAIL_CODE, "用户创建失败.userId[" + userId + "]");
	   }
	   
	   User roler = this.userDao.getByUserId(userId);
	   if (roler != null) {
		   throw new ServiceException(CREATE_USER_EXIST, "用 户已经存在.userId[" + userId + "]");
	   }
	   
	   if(userDao.isExitName(roleName)){
		   throw new ServiceException(CREATE_USER_NAME_EXIST, "用 户名已经存在.roleName[" + roleName + "]");
	   }
	   
	   Date now = new Date();
	   User user = new User();
	   user.setExp(0);
	   user.setLevel(1);
	   user.setMoney(0);
	   user.setName(roleName);
	   user.setRegTime(now);
	   user.setUserId(userId);
	   user.setCamp(roleId);
	   user.setVipLevel(0);
	   user.setPower(100);
	   user.setActivity(100);
	   user.setActivityResumTime(now);
	   user.setPowerResumTime(now);
	   user.setPrestigeLevel(1);
	   user.setJobExp(0);
	   long ftId = getFtid(userMapper.getServerId());
	   user.setFtId(ftId);
	   user.setDueTime(null);
	   userDao.add(user);
	   
	   UserExtInfo extInfo = new UserExtInfo();
	   extInfo.setCreatedTime(now);
	   extInfo.setUserId(userId);
	   extInfo.setPrePosition(roleId+"001,1,2");
	   userExtInfoDaoCacheImpl.addUserExtInfo(extInfo);
		
	   //分发用户创建角色事件
	   ModuleEventBase baseModuleEvent = new ModuleEventBase();
	   baseModuleEvent.addStringValue("userId", userId);
	   baseModuleEvent.addIntValue("nationId", roleId);
	   ModuleEventManager.getInstance().dispatchEvent(ModuleEventConstant.AFTER_USER_CREAT_ROLE_EVENT, baseModuleEvent);
	   userCreatRoleLog(userId, roleName, roleName, 1, new Date(), ftId,roleId, ip,"mac","imei","idfa");
	   //TODO 这里需要计算一次战斗力
	   //执行登陆操作
	   userLogin(userId,ip);
   }
   
   /**
    * 记录新手引导步骤
    * 
    * @param userId
    * @param guideStep
    */
   public boolean recordGuideStep(String userId, int guideStep) {
	   UserExtInfo info = this.userExtInfoDaoCacheImpl.getUserExtInfo(userId);
	   String oldStep = info.getRecordGuideStep();
	   
	   String newStep = "";
	   if (StringUtils.isBlank(oldStep)) {
		   newStep = String.valueOf(guideStep);
	   } else {
		   String[] steps = oldStep.split(",");
		   for (String step : steps) {
			   if (Integer.parseInt(step) == guideStep)
				   throw new ServiceException(SystemConstant.FAIL_CODE, "怎么发送一样的引导步奏？？guideStep = " + guideStep);
		   }
		   
		   newStep = oldStep + "," + guideStep;
	   }
	   
	   return this.userExtInfoDaoCacheImpl.recordGuideStep(userId, newStep, guideStep);
   }

   /**
    * 记录打点信息
    * 
    * @param userId
    * @param actionId
    * @param ip
    */
   public void recordActionLog(String userId, int actionId, String ip) {
	   User user = this.getByUserId(userId);
	   this.userActionLog(userId, user.getLevel(), new Date(), ip, actionId);	   
   }
   
   /**
    * 加钱
    * @param userId
    * @param addAmount
    * @param goodsUseType
    * @return
    */
   public boolean addMoney(String userId, final int addAmount, int goodsUseType){
	   if(userDao.addMoney(userId, addAmount)){
		   User user = userDao.getByUserId(userId);
		   userGoldLog(userId, user.getLevel(), 1, goodsUseType, addAmount, user.getMoney(), new Date());
		   
		   this.achievementService.updateAchievementFinish(userId, addAmount, AchievementConstant.TYPE_LIFE, new IAchievementChecker() {
			   @Override
			   public boolean isHit(int achievementId, Map<String, String> params) {
				   boolean isHit = false;
				   
				   if (params.containsKey("toolType")) {
					   int toolType = Integer.parseInt(params.get("toolType"));
					   
					   if (toolType == GoodsType.MONEY.intValue) {
						   isHit = true;
					   }
				   }				   
				   return isHit;
			   }
		   });
		   
		   return true;
	   }
	   return false;
   }
   
   /**
    * 减钱
    * @param userId
    * @param reduceAmount
    * @param goodsUseType
    * @return
    */
   public boolean reduceMoney(String userId,int reduceAmount,int goodsUseType){
	   if(userDao.reduceMoney(userId, reduceAmount)){
		   User user = userDao.getByUserId(userId);
		   userGoldLog(userId, user.getLevel(), 2, goodsUseType, reduceAmount, user.getMoney(), new Date());
		   return true;
	   }
	   return false;
   }
   
   /**
    * 加金币
    * @param userId
    * @param addAmount
    * @param goodsUseType
    * @return
    */
   public boolean addGold(String userId, final int addAmount, int goodsUseType) {
	   if (userDao.addGold(userId, addAmount)) {
		   User user = userDao.getByUserId(userId);
		   userResourceLog(userId, GoodsType.GOLD.intValue, goodsUseType, addAmount, user.getGold(), new Date());
		   
		   this.achievementService.updateAchievementFinish(userId, addAmount, AchievementConstant.TYPE_LIFE, new IAchievementChecker() {
			   @Override
			   public boolean isHit(int achievementId, Map<String, String> params) {
				   boolean isHit = false;
				   
				   if (params.containsKey("toolType")) {
					   int toolType = Integer.parseInt(params.get("toolType"));
					   
					   if (toolType == GoodsType.GOLD.intValue) {
						   isHit = true;
					   }
				   }				   
				   return isHit;
			   }
		   });
		   
		   return true;
	   }
	   
	   return false;
   }
   
   /**
    * 减金币
    * @param userId
    * @param reduceAmount
    * @param goodsUseType
    * @return
    */
   public boolean reduceGold(String userId,int reduceAmount,int goodsUseType){
	   if(userDao.reduceGold(userId, reduceAmount)){
		   User user = userDao.getByUserId(userId);
		   userResourceLog(userId, GoodsType.GOLD.intValue, goodsUseType, -reduceAmount, user.getGold(), new Date());
		   return true;
	   }
	   return false;
   }
   
   /**
    * 添加职业经验
    * 
    * @param userId
    * @param addAmount
    * @param goodsUserType
    * @return
    */
   public boolean addJobExp(String userId, int addAmount, boolean isBack, int goodsUseType) {
	   User user = this.userDao.getByUserId(userId);
	   // 只有退回的职业经验才能爆表
	   if (!isBack) {
		   int maxJobExp = this.configDataDao.getInt(ConfigKey.user_job_exp_max, 9999);
		   if (user.getJobExp() >= maxJobExp)
			   return true;
		   
		   int totalJobExp = user.getJobExp() + addAmount;
		   if (totalJobExp >= maxJobExp)
			   addAmount = maxJobExp - user.getJobExp();
	   }
	   
	   if (this.userDao.addJobExp(userId, addAmount)) {
		   userResourceLog(userId, GoodsType.JobExp.intValue, goodsUseType, addAmount, user.getJobExp(), new Date());
		   return true;
	   }
	   
	   return false;   
   }
   
   /**
    * 扣除职业经验
    * 
    * @param userId
    * @return
    */
   public boolean reduceJobExp(String userId, int reduceAmount, int goodsUseType) {
	   if (userDao.reduceJobExp(userId, reduceAmount)) {
		   User user = userDao.getByUserId(userId);
		   userResourceLog(userId, GoodsType.JobExp.intValue, goodsUseType, -reduceAmount, user.getJobExp(), new Date());
		   
		   return true;
	   }
	   
	   return false;
   }
   
   /**
    * 修改用户名
    * 
    * @param userId
    * @param name
    * @return
    */
   	public UserAction_changeUserNameRes changeUserName(String userId, String name) {
   		RoleUtils.isValidUsername(name);   	
   		if(userDao.isExitName(name)){
   			throw new ServiceException(CREATE_USER_NAME_EXIST, "用 户名已经存在.roleName[" + name + "]");
   		}

   		UserAction_changeUserNameRes res = new UserAction_changeUserNameRes();
   		// 优先使用改名卡
   		UserToolBO userTool = this.toolService.getUserToolBO(userId, ToolConstant.CHANGE_USER_NAME_CARD);
   		if (userTool != null && userTool.getToolNum() > 0) {
   			if (!this.toolService.reduceTool(userId, ToolConstant.CHANGE_USER_NAME_CARD, 1, GoodsUseType.REDUCE_CHANGE_NAME))
   				throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "用户道具不足");
   			
   			res.setToolId(ToolConstant.CHANGE_USER_NAME_CARD);
   		} else {
   			User user = this.userDao.getByUserId(userId);
   			int cost = this.configDataDao.getInt(ConfigKey.change_user_name_coat, 100);
   			if (user.getMoney() < cost)
   				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "用户钻石不足");
   			
   			if (!reduceMoney(userId, cost, GoodsUseType.REDUCE_CHANGE_NAME))
   				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "用户钻石不足");
   		}
   		
   		this.userDao.updateUserName(userId, name);
   		updateUserLogName(userId, name);   	
   		
   		User user = this.userDao.getByUserId(userId);
   		res.setName(name);
   		res.setMoney(user.getMoney());
   		return res;
   }
   
   /**
    * 更新声望及声望等级
    * @param userId
    * @param addPrestige
    * @param addLevel
    * @param goodsUseType
    * @param drop
    * @return
    */
//   public boolean addPrestigeAndPrestigeLevel(String userId,int addPrestige,int addLevel,int goodsUseType,CommonGoodsBeanBO drop){
//	   if(userDao.addPrestigeAndPrestigeLevel(userId, addPrestige, addLevel)){
//		   User user = userDao.getByUserId(userId);
//		   userResourceLog(userId, GoodsType.prestige.intValue, goodsUseType, addPrestige, user.getPrestige(), new Date());
//		   if(drop!=null){
//				GoodsHelper.addCommonDropGoodsBean(drop, GoodsType.prestige.intValue, 0, addPrestige);
//				if(addLevel>0){
//					GoodsHelper.addCommonDropGoodsBean(drop, GoodsType.PrestigeLevel.intValue, 0, addLevel);
//				}
//		   }
//		   return true;
//	   }
//	   return false;
//   }
   
   /**
    * 减声望
    * @param userId
    * @param reduceAmount
    * @param goodsUseType
    * @return
    */
//   public boolean reducePrestige(String userId,int reduceAmount,int goodsUseType){
//	   if(userDao.reducePrestige(userId,reduceAmount)){
//		   User user = userDao.getByUserId(userId);
//		   userResourceLog(userId, GoodsType.prestige.intValue, goodsUseType, -reduceAmount, user.getPrestige(), new Date());
//		   return true;
//	   }
//	   return false;
//   }
   
   /**
    * 加荣誉点
    * 
    * @param userId
    * @param addAmount
    * @param addTime
    * @param goodsUseType
    * @return
    */
   public boolean addHonour(String userId,int addAmount,int goodsUseType){
	   if(userDao.addHonour(userId, addAmount)){
		   User user = userDao.getByUserId(userId);
		   userResourceLog(userId, GoodsType.honour.intValue, goodsUseType, addAmount, user.getHonour(), new Date());
		   return true;
	   }
	   return false;
   }
   
   /**
    * 减荣誉点
    * 
    * @param userId
    * @param reduceAmount
    * @param goodsUseType
    * @return
    */
   public boolean reduceHonour(String userId,int reduceAmount,int goodsUseType){
	   if(userDao.reduceHonour(userId,reduceAmount)){
		   User user = userDao.getByUserId(userId);
		   userResourceLog(userId, GoodsType.honour.intValue, goodsUseType, -reduceAmount, user.getHonour(), new Date());
		   return true;
	   }
	   return false;
   }
   
   
   
   /**
    * 添加总充值数
    * @param userId
    * @param addAmount
    * @return
    */
   public boolean addAllChargeMoney(String userId,int addAmount){
	  return this.userExtInfoDaoCacheImpl.addAllChargMoney(userId, addAmount);
   }
   
   /**
    * 更新战斗力
    * @param userId
    * @param effective
    * @return
    */
   private boolean updateEffective(String userId,int effective){
	   return this.userExtInfoDaoCacheImpl.updateEffective(userId, effective);
   }
   
   /**
    * 更新战斗力
    * 
    * @param userId
    * @return
    */
   	public void updateEffective(String userId) {
   		List<UserHero> userHeroList = heroService.getBattleUserHero(userId);
   		int totalEffective = 0;
   		for (UserHero userHero : userHeroList) {
   			totalEffective = totalEffective + userHero.getEffective();
   		}
   		
   		updateEffective(userId, totalEffective);
   		
   		final int nowEffective = totalEffective;
   		// 成就
   		this.achievementService.updateAchievementFinish(userId, 1, AchievementConstant.TYPE_LIFE, new IAchievementChecker() {
   			@Override
   			public boolean isHit(int achievementId, Map<String, String> params) {
   				boolean isHit = false;
   				if (params.containsKey("effective")) {
   					int effective = Integer.parseInt(params.get("effective"));
   					if (nowEffective >= effective)
   						isHit = true;
   				}
   				
   				return isHit;
   			}
   		});
   	}
   
   /**
    * 获取用户扩展信息
    * 
    * @param userId
    * @return
    */
   public UserExtInfo getUserExtInfo(String userId) {
	   return userExtInfoDaoCacheImpl.getUserExtInfo(userId);
   }
   
   /**
    * 增加背包扩展次数
    * @param userId
    * @param addTimes
    * @return
    */
   public boolean addPackExtendTimes(String userId,int addTimes,int goodsUseType,CommonGoodsBeanBO drop){
	   int maxExtendTimes = configDataDao.getInt(ConfigKey.pack_max_extend_times, 48);
	   UserExtInfo extInfo = userExtInfoDaoCacheImpl.getUserExtInfo(userId);
	   if(extInfo.getPackExtendTimes() + addTimes > maxExtendTimes){
		   addTimes = maxExtendTimes-extInfo.getPackExtendTimes();
	   }
	   if(this.userExtInfoDaoCacheImpl.addPackExtendTimes(userId, addTimes)){
		   userResourceLog(userId, GoodsType.packExtendTimes.intValue, goodsUseType, addTimes, extInfo.getPackExtendTimes(), new Date());
		   if(drop != null){
			   GoodsHelper.addCommonDropGoodsBean(drop, GoodsType.packExtendTimes.intValue, 0, addTimes);
		   }
		   return true;
	   }
	   return false;
   }
   
   /**
    * 获取总充值数量
    * @param userId
    * @return
    */
   public double allChargeAmount(String userId){
	   UserExtInfo userExtInfo = userExtInfoDaoCacheImpl.getUserExtInfo(userId);
	   return userExtInfo.getAllChargeMoney();
   }
   
   /**
    * 战斗前可上阵的陌生人
    * 
    * @param userId
    * @return
    */
   public List<User> getJoinBattleUserList(String userId, int camp) {
	   User user = this.getByUserId(userId);
	   return this.userDao.getJoinBattleUserList(user.getLevel(), camp);
   }
   
	/**
	 * 根据战斗力排序
	 * 
	 * @param list
	 * @return
	 */
	public List<User> sortRankByEffective(List<User> list) {
		List<User> collection = new ArrayList<User>();
		collection.addAll(list);
		
		Collections.sort(collection, new Comparator<User>() {
			@Override
			public int compare(User userI, User userX) {
				UserExtInfo userExtInfoI = getUserExtInfo(userI.getUserId());
				UserExtInfo userExtInfoX = getUserExtInfo(userX.getUserId());
				
				if (userExtInfoI.getEffective() > userExtInfoX.getEffective())
					return -1;
				else if (userExtInfoI.getEffective() < userExtInfoX.getEffective())
					return 1;
				return 0;
			}			
		});
		return collection;
	}
   
   /**
    * 修改经验及等级
    * 
    * @param userId
    * @param addAmount
    * @param goodsUseType
    * @return
    */
   public boolean updateExpAndLevel(String userId, int addAmount, int goodsUseType, CommonGoodsBeanBO drop){
        User user = userDao.getByUserId(userId);
		if (user == null) {
			String message = "更新用户经验失败,用户不存在.userId[" + userId + "], exp[" + addAmount + "]";
			throw new ServiceException(SystemConstant.FAIL_CODE, message);
		}
		//如果到了最高等级  则不再加经验
		if(user.getLevel()>=systemTeamExpDaoCache.getMaxLevel()){
			LogSystem.info("userId="+userId+",用户已经到达最高等级 不再加经验");
			return false;
		}
	    final SystemTeamExp systemTeamExp = this.systemTeamExpDaoCache.getUserLevel(user.getExp() + addAmount);
		boolean isLevelUp = false;
		int leveAddCount = 0;
		if(systemTeamExp.getSystemTeamLevel() > user.getLevel()) {
			leveAddCount = systemTeamExp.getSystemTeamLevel() - user.getLevel();
			isLevelUp = true;
		}
	    //修改经验和等级
		boolean success = this.userDao.updateLevelAndExp(userId, systemTeamExp.getSystemTeamLevel(), addAmount);
		//经验添加日志
		if(success){ 
			userResourceLog(userId, GoodsType.Exp.intValue, goodsUseType, addAmount, user.getExp(), new Date());
		}
	    if(success&&isLevelUp){
	    	//升级日志
			userLevelUpLog(new Date(), userId, systemTeamExp.getSystemTeamLevel());
	    }
	    if(success){
			GoodsHelper.addCommonDropGoodsBean(drop, GoodsType.Exp.intValue, 0, addAmount);
	    }
	    if(isLevelUp){
			GoodsHelper.addCommonDropGoodsBean(drop, GoodsType.Level.intValue, 0, leveAddCount);
			final int addLevel = leveAddCount;
			userTaskService.updateTaskFinish(userId, addLevel, new ITaskHitChecker() {
				@Override
				public Map<String, Object> isHit(int systemTaskId, int taskLibrary, Map<String, String> params) {
					Map<String, Object> returnMap = Maps.newConcurrentMap();
					returnMap.put("rt", false);
					returnMap.put("tm", addLevel);
					
					if (taskLibrary == TaskLibraryType.USER_LEVEL_UP) {
						int level = Integer.parseInt(params.get("lv"));
						if (systemTeamExp.getSystemTeamLevel() >= level) {
							returnMap.put("rt", true);
							return returnMap;
						}
					}
					
					return returnMap;
				}				
			});
			
			// 成就
			achievementService.updateAchievementFinish(userId, addLevel, AchievementConstant.TYPE_PERSON, new IAchievementChecker() {
				@Override
				public boolean isHit(int achievementId, Map<String, String> params) {
					boolean isHit = false;
					
					if (params.containsKey("level")) {
						isHit = true;
					}
					
					return isHit;
				}
			});
	    }
	    return success;
   }
   
   /**
    * 记录用户开启的地图信息
    * 
    * @param userId
    * @param mapId
    */
   public void recordOpenMap(String userId, final int mapId) {
	   UserMapInfo userMapInfo = this.userMapInfoDao.getUserMapInfo(userId);
	   if (userMapInfo == null) {
		   userMapInfo = new UserMapInfo();
		   userMapInfo.setUserId(userId);
		   userMapInfo.setMaps(mapId + "");
		   userMapInfo.setCreatedTime(new Date());
		   userMapInfo.setUpdatedTime(new Date());
		   
		   this.userMapInfoDao.addUserMapInfo(userMapInfo);		   
		   return;
	   }
	   
	   String maps = userMapInfo.getMaps();
	   if (maps.length() > 0) {
		   String[] mapArr = maps.split(",");
		   for (String map : mapArr) {
			   if (Integer.parseInt(map) == mapId)
				   throw new ServiceException(SystemConstant.FAIL_CODE, "mapId重复啦......mapId:" + mapId);
		   }
		   
		   maps = maps + "," + mapId;
	   } else {
		   maps = mapId + "";
	   }
	   
	   this.achievementService.updateAchievementFinish(userId, 1, AchievementConstant.TYPE_LIFE, new IAchievementChecker() {
		   @Override
		   public boolean isHit(int achievementId, Map<String, String> params) {
			   boolean isHit = false;
			   
			   if (params.containsKey("mapId")) {
				   int mapIdParam = Integer.parseInt(params.get("mapId"));
				   
				   if (mapIdParam == mapId) {
					   isHit = true;
				   }
			   }				   
			   return isHit;
		   }
	   });
	   
	   this.userMapInfoDao.updateUserMapInfo(userId, maps);
   }
   
   /**
    * 获取时间段内注册的用户
    * 
    * @param startTime
    * @param endTime
    * @return
    */
   public List<String> getBetweenRegTimeList(Date startTime, Date endTime) {
	   return this.userDao.getBetweenRegTimeList(startTime, endTime);
   }
   
   /**
    * 判断用户是否时间段内登陆过
    * 
    * @param userId
    * @param time
    * @return
    */
   public boolean isLogin(String userId, Date time) {
	   String startTime = (DateUtils.getDate(time) + " 00:00:00");
	   String endTime = (DateUtils.getDate(time) + " 23:59:59");
	   UserOnlineLog log = this.userOnlineLogDao.isLogin(userId, startTime, endTime);
	   if (log != null) {
			return true;
		}
	   
	   return false;
   }
   
   /**
    * 获取时间段内登陆的在线用户
    * 
    * @param time
    * @return
    */
   public List<String> getLoginTimeUserIdList(Date time) {
	   String startTime = (DateUtils.getDate(time) + " 00:00:00");
	   String endTime = (DateUtils.getDate(time) + " 23:59:59");
	   
	   List<String> userIdList = Lists.newArrayList();
	   for (String userId : onLineUserIdMap.keySet()) {
		   UserOnlineLog log = this.userOnlineLogDao.isLogin(userId, startTime, endTime);
		   if (log != null) {
			   userIdList.add(log.getUserId()); 
		   }
	   }
	   
	   return userIdList;
   }
   
   /**
    * 时间段内
    * 
    * @param userId
    * @param time
    * @return
    */
   public int getPaymentTotalByTime(String userId, Date time) {
	   Date startTime = DateUtils.getDateAtMidnight(time);
	   Date endTime = DateUtils.getDateAtMidnight(DateUtils.addDays(time, 1));

	   return this.paymentLogDao.getPaymentTotalByTime(userId, startTime, endTime);	   
   }
   
   /**
    * 获取时间段内充值过的用户
    * 
    * @param time
    * @return
    */
   public List<PaymentLog> getHasPaymentLogList(Date time) {
	   Date startTime = DateUtils.getDateAtMidnight(time);
	   Date endTime = DateUtils.getDateAtMidnight(DateUtils.addDays(time, 1));
	   
	   return this.paymentLogDao.getPaymentLogList(startTime, endTime);
   }
   
   /**
    * 获取时间段内充值过的用户id
    * 
    * @param time
    * @return
    */
   public List<String> getHasPaymentUserIdList(Date time) {
	   List<PaymentLog> list = getHasPaymentLogList(time);
	   List<String> userIdList = Lists.newArrayList();
	   if (list != null && list.size() > 0) {
		   for (PaymentLog log : list) {
			   userIdList.add(log.getUserId());
		   }
	   }
	   
	   return userIdList;
   }
   
   /**
    * 更新保存用户上次登出所处的场景位置
    * @param userId
    * @param prePosition
    * @return
    */
   public boolean updatePrePosition(String userId,String prePosition){
	   return userExtInfoDaoCacheImpl.updatePrePosition(userId, prePosition);
   }
   
//   public void updateVipLevel(String userId) {
//		User user = getByUserId(userId);
//		int payAmount = this.paymentLogDao.getPaymentTotal(userId);
//		SystemVipLevel systemVipLevel = this.systemVipLevelDao.getBuyMoney(payAmount);
//		final int vipLevel = systemVipLevel.getVipLevel();
//       if(user.getVipLevel()!=vipLevel){
//	   		this.userDao.updateVIPLevel(userId, vipLevel);
//	   		// 插入vip升级日志
//	   		logService.userVipLevelLog(userId, vipLevel, new Date());
//       }
//	}
   
	private synchronized long getFtid(String serverId) {
		int id = 1;
		String key = RoleUtils.getIdSaveKey(serverId);
		RuntimeData runtimeData = runtimeDataDao.get(key);
		if (runtimeData == null) {
			runtimeData = new RuntimeData();
			runtimeData.setValueKey(key);
			runtimeData.setValue(id);
			runtimeData.setCreatedTime(new Date());
			this.runtimeDataDao.add(runtimeData);
		} else {
			id = runtimeData.getValue() + 1;
			runtimeDataDao.inc(key);
		}
		return getFtIdEnd(serverId,id);
	}
	
	private  long getFtIdEnd(String serverId, int id) {
		String ind = serverId.replaceAll("[a-zA-Z]+", "");
		String sid = String.valueOf(id);
		String s = ind + "000000".substring(0, 6 - sid.length()) + sid;
		return Long.valueOf(s);
	}
	
   /**
    * 用户登出
    * @param userId
    */
   public void userLogout(String userId,String userIp,int liveSeconds){
	   User user = userDao.getByUserId(userId);
	   if(user==null){
		   return;
	   }
	   //累积在线时长
	   userExtInfoDaoCacheImpl.addOnLineTime(userId, liveSeconds);
	   //分发用户登出事件
	   ModuleEventBase eventBase = new ModuleEventBase();
	   eventBase.addStringValue("userId", userId);
	   ModuleEventManager.getInstance().dispatchEvent(ModuleEventConstant.USER_LOGINOUT_EVENT, eventBase);
	   //移除在线用户记录
	   onLineUserIdMap.remove(userId);
	   
	   //写登出日志
	   int liveMinutes = liveSeconds/60;
	   this.userLogOutLog(userId, userIp, new Date(), liveMinutes);
   }
   
   /**
    * 获取背包大小
    * 
    * @param userId
    * @return
    */
   public int getPackLimit(String userId){
	   UserExtInfo userExtInfo = userExtInfoDaoCacheImpl.getUserExtInfo(userId);
	   int initPack = configDataDao.getInt(ConfigKey.pack_init_num, 20);
	   // int increasePack = configDataDao.getInt(ConfigKey.pack_extend_increase_num, 48);
	   
//	   return initPack + increasePack * userExtInfo.getPackExtendTimes();
	   return initPack + userExtInfo.getPackExtendTimes();
   }
   
   /**
    * 获取仓库大小
    * 
    * @param userId
    * @return
    */
   public int getStorehouseLimit(String userId) {
	   UserExtInfo userExtInfo = userExtInfoDaoCacheImpl.getUserExtInfo(userId);
	   int initPack = configDataDao.getInt(ConfigKey.storehouse_init_free_num, 10);
	   
	   return initPack + userExtInfo.getStorehouseExtendTimes();
   }
   
   /**
    * 刷新用户属性信息
    * @param userId
    * @param types
    */
   public void notifyUserProperties(String userId,int ...types){
	   User_pushUserPropertiesNotify notify = new User_pushUserPropertiesNotify();
	   List<UserPropertiesBO> list = Lists.newArrayList();
	   User user = userDao.getByUserId(userId);
	   for(int i=0;i<types.length;i++){
		   UserPropertiesBO propertiesBO = new UserPropertiesBO();
		   propertiesBO.setKey(types[0]);
		   switch(types[0]){
		    case UserPushProperties.MONEY:
		    	propertiesBO.setValue(Long.valueOf(user.getMoney()));
		    	break;
		    case UserPushProperties.ACTIVITY:
		    	propertiesBO.setValue(Long.valueOf(user.getActivity()));
		    	break;
		    case UserPushProperties.NEXT_ACTIVITY_RESUME_TIME:
		 	    long activityAddInterval = configDataDao.getInt(ConfigKey.activity_add_inteval, 6)*60 * 1000;
		    	propertiesBO.setValue(user.getActivityResumTime().getTime()+activityAddInterval);
		    	break;
		    case UserPushProperties.NEXT_POWER_RESUME_TIME:
		    	long powerAddInterval = configDataDao.getInt(ConfigKey.power_add_inteval, 6)*60 * 1000;
		    	propertiesBO.setValue(user.getPowerResumTime().getTime()+powerAddInterval);
		    	break;
		    case UserPushProperties.POWER:
		    	propertiesBO.setValue(Long.valueOf(user.getPower()));
		    	break;
		    case UserPushProperties.VIP_EXP:
		    	propertiesBO.setValue(Long.valueOf(user.getVipExp()));
		    	break;
		    case UserPushProperties.VIP_LEVEL:
		    	propertiesBO.setValue(Long.valueOf(user.getVipLevel()));
		    	break;
		    case UserPushProperties.GOLD:
		    	propertiesBO.setValue(Long.valueOf(user.getGold()));
		    	break;
		    default:
		    	throw new ServiceException(SystemConstant.FAIL_CODE, "不存在的类型");
		   }
		   list.add(propertiesBO);
	   }
	   notify.setUserPropertiesList(list);
	   MsgDispatchCenter.disPatchUserMsg("User_pushUserProperties", userId, notify);
   }
   
   /**
    * 更改日志表的用户名称
    * 
    * @param userId
    * @param name
    * @return
    */
   private void updateUserLogName(String userId, String name) {
	   String sql = "update user set name = name user_name = name where user_id = userId";
	   logService.updateUserLogName(sql);
   }
   
	/**
	 * 创角日志
	 * 
	 * @param userId
	 * @param userName
	 * @param name
	 * @param sex
	 * @param regTime
	 * @param loadId
	 * @param mainRoleId
	 * @param ip
	 * @param mac
	 * @param imei
	 * @param idfa
	 */
	public void userCreatRoleLog(String userId, String userName, String name,
			int sex, Date regTime, long loadId, int mainRoleId, String ip,
			String mac, String imei, String idfa) {
		String time = DateUtils.getTimeStr(regTime);
		userName = userName.replaceAll("\'", "\\\\'");
		name = name.replaceAll("\'", "\\\\'");
		String sql = "INSERT INTO user(USER_ID,USER_NAME,NAME,SEX,LODO_ID,REG_TIME,MAIN_ROLE_ID,REG_IP,REG_MAC,REG_IMEI,REG_IDFA) VALUES('"
				+ userId
				+ "', '"
				+ userName
				+ "', '"
				+ name
				+ "', "
				+ sex
				+ ","
				+ loadId
				+ ", '"
				+ time
				+ "',"
				+ mainRoleId
				+ ""
				+ ",'"
				+ ip + "','" + mac + "','" + imei + "','" + idfa + "'" + ")";
		logService.synInsertLog(sql);
	}

	/**
	 * 登陆日志
	 * 
	 * @param userId
	 * @param level
	 * @param ip
	 * @param loginTime
	 */
	public void userLoginLog(String userId, int level, String ip, Date loginTime) {
		String time = DateUtils.getTimeStr(loginTime);
		String sql = "INSERT INTO user_login_log(USER_ID,LEVEL,IP,LOGIN_TIME) VALUES('"
				+ userId + "'," + level + ",'" + ip + "','" + time + "')";
		logService.unSynInsertLog(sql);
	}

	/**
	 * 登出日志
	 * 
	 * @param userId
	 * @param ip
	 * @param loginOutTime
	 * @param liveMinutes
	 */
	public void userLogOutLog(String userId, String ip, Date loginOutTime,
			int liveMinutes) {
		String time = DateUtils.getTimeStr(loginOutTime);
		String sql = "INSERT INTO user_logout_log(USER_ID,IP,LOGOUT_TIME,LIVE_MINUTES) VALUES('"
				+ userId + "','" + ip + "','" + time + "'," + liveMinutes + ")";
		logService.unSynInsertLog(sql);
	}
	
    /**
     * 升级日志
     * @param levelTime
     * @param userId
     * @param level
     */
	public void userLevelUpLog(Date levelTime, String userId, int level) {
		String time = DateUtils.getTimeStr(levelTime);
		String sql = "INSERT INTO user_levelup_log(TIME,USER_ID,LEVEL) VALUES('" + time + "','" + userId + "'," + level + ")";
		logService.unSynInsertLog(sql);
	}
	
	/**
	 * 人民币日志
	 * @param userId
	 * @param userLevel
	 * @param getOrUse
	 * @param type
	 * @param changeNum
	 * @param nowNum
	 * @param opratorTime
	 */
	public void userGoldLog(String userId, int userLevel, int getOrUse, int type, int changeNum,int nowNum, Date opratorTime) {
		String time = DateUtils.getTimeStr(opratorTime);
		String sql = "INSERT INTO user_gold_log(USER_ID,USER_LEVEL,CATEGORY,TYPE,CHANGE_NUM,NOW_NUM,TIME) VALUES('" + userId + "'," + userLevel + "," + getOrUse + "," + type + "," + changeNum + ","+nowNum+",'" + time + "')";
		logService.synInsertLog(sql);
	}
	
    /**
    * 在线日志
    * @param oprationTime
    * @param userAmount
    * @param userIdStr
    */
	public void userOnlineLog(Date oprationTime, int userAmount, String userIdStr) {
		String time = DateUtils.getTimeStr(oprationTime);
		String sql = "INSERT INTO user_online_log(TIME,ONLINE_AMOUNT,USER_STR) " + "VALUES('" + time + "'," + userAmount + ",'" + userIdStr + "')";
		logService.unSynInsertLog(sql);
	}
	
	/**
	 * 用户行为日志
	 * @param userId
	 * @param userLevel
	 * @param creatTime
	 * @param ip
	 * @param actionId
	 */
	public void userActionLog(String userId, int userLevel, Date creatTime, String ip, int actionId) {
		String time = DateUtils.getTimeStr(creatTime);
		String now = DateUtils.getDateForLog();
		String sql = "INSERT INTO user_action_log_"+now+"(USER_ID,USER_LEVEL,IP,SOURCE,ACTION_ID,TIME) " + "VALUES('" + userId + "'," + userLevel + ",'" + ip + "'," + 0 + "," + actionId + ",'" + time + "')";
		logService.unSynInsertLog(sql);
	}
	
	/**
	 * vip升级日志
	 * @param userId
	 * @param vipLevel
	 * @param creatTime
	 */
	public void userVipLevelLog(String userId, int vipLevel, Date creatTime) {
		String time = DateUtils.getTimeStr(creatTime);
		String sql = "INSERT INTO user_vip_log(USER_ID,VIP_LEVEL,TIME)  VALUES('" + userId + "'," + vipLevel + ",'" + time + "')";
		logService.synInsertLog(sql);
	}
	
	/**
	 * 用户资源日志
	 * @param userId
	 * @param souceType
	 * @param goodsUseType
	 * @param changeNum
	 * @param nowNum
	 * @param opratorTime
	 */
	public void userResourceLog(String userId, int souceType,int goodsUseType, int changeNum,int nowNum, Date opratorTime) {
		String time = DateUtils.getTimeStr(opratorTime);
		String now = DateUtils.getDateForLog();
		String sql = "INSERT INTO user_resource_log_" + now + "(USER_ID,SOURCE_TYPE,OPERATION,CHANGE_NUM,NOW_NUM,CREATE_TIME) VALUES('" + userId + "',"+souceType+",'" + goodsUseType + "'," + changeNum + "," + nowNum + ",'" + time + "')";
		logService.unSynInsertLog(sql);
	}
	
	private void getNumByType(List<GoodsBeanBO> list, TempCheckPackBean bean){
		for (GoodsBeanBO dropToolBO : list) {
			int goodsType = dropToolBO.getGoodsType();
			if (goodsType == GoodsType.tool.intValue)  {
				if (bean.tools.containsKey(dropToolBO.getGoodsId())) {
					Integer now = bean.tools.get(dropToolBO.getGoodsId());
					bean.tools.put(dropToolBO.getGoodsId(), now + dropToolBO.getGoodsNum());
				} else {
					bean.tools.put(dropToolBO.getGoodsId(), dropToolBO.getGoodsNum());
				}
			} else if (goodsType == GoodsType.equip.intValue) {
				bean.equipMent = bean.equipMent + dropToolBO.getGoodsNum();
			} else if (goodsType == GoodsType.Hero.intValue) {
				bean.hero = bean.hero + dropToolBO.getGoodsNum();
			} else if (goodsType == GoodsType.Gemstone.intValue) {
				bean.gemstone = bean.gemstone + dropToolBO.getGoodsNum();
			}
		}
	}
	
//	private boolean isTool(int goodsType) {
//		return goodsType == GoodsType.tool.intValue || goodsType ==  GoodsType.GIFT_BOX.intValue 
//				|| goodsType ==  GoodsType.SKILL_BOOK.intValue;
//	}
	
	// 装备、宝石 0 在背包 1 在仓库
	private final static int IN_BAG = 0;
	private final static int IN_STOREHOUSE = 1;
	/**
	 * 检查仓库
	 * 
	 * @param userId
	 * @param list
	 */
	public void checkStorehouse(String userId, List<GoodsBeanBO> list) {
		TempCheckPackBean bean = new TempCheckPackBean();
		getNumByType(list, bean);
		String toolPrintInfo = "";
		if(bean.equipMent == 0 && bean.tools.size() == 0 
				&& bean.gemstone == 0){
			return;
		}
		
		Map<Integer,Integer> addTools = bean.tools;
		int addEquipNum = bean.equipMent;
		int addGemstone = bean.gemstone;
		if(LogSystem.isEnableFor(Level.DEBUG)){
			for(Integer i: addTools.keySet()){
				toolPrintInfo = toolPrintInfo + ",toolId=" + i + ",num=" + addTools.get(i);
			}
			LogSystem.debug("即将检测仓库,添加的装备数量=" + addEquipNum + ",道具map:" + toolPrintInfo + ",宝石数量=" + addGemstone);
		}
		
		//TODO 获取装备、宝石、道具已占用仓库的格子数
		int equipMentNowCount = equipService.getUserEquipCount(userId, IN_STOREHOUSE);		
		int gemstoneCount = gemstoneService.getUserGemstoneCount(userId, IN_STOREHOUSE);
		int totalToolCount = toolService.getStorehouseToolNeedCellNum(userId, addTools);
		int storehouseMax = this.getStorehouseLimit(userId);
			
	    if(storehouseMax < totalToolCount + (equipMentNowCount + addEquipNum) + (gemstoneCount + addGemstone))
	    	throw new ServiceException(SystemErrorCode.STOREHOUSE_IS_FULL, "仓库已满了");
	}
	
	/**
	 * 检测背包
	 * 
	 * @param userId
	 * @param list
	 */
	public void checkBag(String userId, List<GoodsBeanBO> list) {
		TempCheckPackBean bean = new TempCheckPackBean();
		getNumByType(list, bean);
		String toolPrintInfo = "";
		if(bean.equipMent == 0 && bean.tools.size() == 0 
				&& bean.hero == 0 && bean.gemstone == 0){
			return;
		}
		Map<Integer,Integer> addTools = bean.tools;
		int addEquipNum = bean.equipMent;
		if(LogSystem.isEnableFor(Level.DEBUG)){
			for(Integer i:addTools.keySet()){
				toolPrintInfo = toolPrintInfo + ",toolId=" + i + ",num=" + addTools.get(i);
			}
			LogSystem.debug("即将检测背包,装备数量" + addEquipNum + ",道具map" + toolPrintInfo);
		}
		//TODO 获取装备占用背包的格子数
		int equipMentNowCountInPackage = equipService.getUserEquipCount(userId, IN_BAG);
		
		int tooldNowCount = toolService.getToolNeedCellNum(userId, addTools, true);
		int gemstoneCount = gemstoneService.getUserGemstoneCount(userId, IN_BAG);
		int bagMax = this.getPackLimit(userId);
		int heroBagMax = this.configDataDao.getInt(ConfigKey.hero_pack_init_num, 10);		
		
		int totalHeroNum = bean.hero + heroService.getUserHeroCount(userId, 1);
		if (heroBagMax < totalHeroNum)
			throw new ServiceException(SystemErrorCode.HERO_BAG_IS_FULL, "英雄背包已满");
			
		// 只能超过背包一次....
	    if(bagMax <= tooldNowCount + equipMentNowCountInPackage + gemstoneCount){
	    	throw new ServiceException(SystemErrorCode.BAG_IS_FULL, "背包满了");
	    }
	}
	
	/**
	 * 检测背包-仅限用于仓库取出物品
	 * 
	 * @param userId
	 * @param list
	 */
	public void checkBagUseInStorehouseOut(String userId, List<GoodsBeanBO> list) {
		TempCheckPackBean bean = new TempCheckPackBean();
		getNumByType(list, bean);
		String toolPrintInfo = "";
		if(bean.equipMent == 0 && bean.tools.size() == 0 && bean.gemstone == 0)
			return;
		
		Map<Integer,Integer> addTools = bean.tools;
		int addEquipNum = bean.equipMent;
		int addGenstone = bean.gemstone;
		if (LogSystem.isEnableFor(Level.DEBUG)) {
			for(Integer i:addTools.keySet()){
				toolPrintInfo = toolPrintInfo + ",toolId=" + i + ",num=" + addTools.get(i);
			}
			
			LogSystem.debug("物品从仓库取出，即将检测背包,装备数量=" + addEquipNum + ",道具map=" + toolPrintInfo
					+ ",gonstoneNum=" + addGenstone);
		}
		
		int equipMentNowCountInPackage = equipService.getUserEquipCount(userId, IN_BAG);
		int gemstoneCount = gemstoneService.getUserGemstoneCount(userId, IN_BAG);		
		int totalTtoolCount = toolService.getToolNeedCellNum(userId, addTools, false);
		int bagMax = this.getPackLimit(userId);	
		
	    if(bagMax <= totalTtoolCount + (equipMentNowCountInPackage + addEquipNum) + (gemstoneCount + addGenstone)) {
	    	throw new ServiceException(SystemErrorCode.BAG_IS_FULL, "背包满了");
	    }
	}
	
	/**
	 * 背包检查
	 * 
	 * @param userId
	 * @param list
	 * @return
	 */
	public boolean checkBagLimit(String userId, List<GoodsBeanBO> list) {
		try {
			checkBag(userId, list);
		} catch (ServiceException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 根据等级获取用户列表
	 * 
	 * @param minLevel
	 * @param maxLevel
	 * @return
	 */
	public List<User> getUserListByFloatLevel(int minLevel, int maxLevel) {
		return this.userDao.getUserListByFloatLevel(minLevel, maxLevel);
	}
	
	/**
	 * 修改用户等级，仅限于后台工具使用
	 * 
	 * @param userId
	 * @param level
	 * @return
	 */
	public boolean adminToolUpdateUserLevel(String userId, int level) {
		User user = this.getByUserId(userId);		
		boolean success = false;
		if (user != null) {
			success = this.userDao.updateLevelAndExp(userId, level, user.getExp());
		}
		
		return success;
	} 
	
	/**
	 * 获取开启的英雄格子数
	 * @param userLevel
	 * @return
	 */
	public int getBattleNum(int userLevel){
		SystemTeamExp exp =  systemTeamExpDaoCache.get(userLevel);
		if(exp!=null){
			return exp.getBattleNum();
		}
		return 0;
	}
	
	public User getByUserName(String name) {
		return this.userDao.getByUserName(name);
	}

	/**
	 * 检查用户道具是否足够
	 * 3005,3010,3011
	 * @param goodsList
	 */
	public void checkUserToolIsEnough(String userId, List<GoodsBeanBO> goodsList) {
		for (GoodsBeanBO goodsBeanBO : goodsList) {
			int toolType = goodsBeanBO.getGoodsType();
			int toolId = goodsBeanBO.getGoodsId();
			int toolNum = goodsBeanBO.getGoodsNum();
			
			if (toolType == GoodsType.equip.intValue) {
				List<UserEquip> equipList = this.equipService.getUnWearEquipList(userId, toolId);
				if (equipList.size() < toolNum)
					throw new ServiceException(SystemErrorCode.EQUIP_NOT_ENOUGH, "装备不足？？userId = " + userId  
							+ ",toolType=" + toolType + ",toolId=" + toolId + ",toolNum=" + toolNum);
			} else if (toolType == GoodsType.Gemstone.intValue) {
				List<UserGemstone> stoneList = this.gemstoneService.getUnFillGemstoneList(userId, toolId);
				if (stoneList.size() < toolNum) {
					throw new ServiceException(SystemErrorCode.GEMSTONE_NOT_ENOUGH, "用户宝石不足？？userId = " + userId  
							+ ",toolType=" + toolType + ",toolId=" + toolId + ",toolNum=" + toolNum);
				}
			} else {
				UserToolBO userTool = this.toolService.getUserToolBO(userId, toolId);
				if (userTool == null || userTool.getToolNum() < toolNum)
					throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "用户道具不足,userId=" + userId 
							+ ",toolType=" + toolType + ",toolId=" + toolId + ",toolNum=" + toolNum);				
			}			
		}
	}
	
	/**
	 * 扣除用户的各种道具
	 * 
	 * @param goodsList
	 */
	public void reduceUserTool(String userId, List<GoodsBeanBO> goodsList, int goodsUseType, List<GoodsBeanBO> toolList, 
			List<String> userEquipIdList, List<String> userGemstoneIdList) {
		for (GoodsBeanBO goodsBeanBO : goodsList) {
			int toolType = goodsBeanBO.getGoodsType();
			int toolId = goodsBeanBO.getGoodsId();
			int toolNum = goodsBeanBO.getGoodsNum();
			
			if (toolType == GoodsType.equip.intValue) {
				List<UserEquip> equipList = this.equipService.getUnWearEquipList(userId, toolId);
				int size = 1;
				
				for (UserEquip userEquip : equipList) {
					this.equipService.reduceEquip(userId, userEquip.getUserEquipId(), goodsUseType, null);
					userEquipIdList.add(userEquip.getUserEquipId());
					
					if (size >= toolNum)
						break;
					size++;
				}
			} else if (toolType == GoodsType.Gemstone.intValue) {
				List<UserGemstone> stoneList = this.gemstoneService.getUnFillGemstoneList(userId, toolId);
				int size = 1;
				
				for (UserGemstone userGemstone : stoneList) {
					this.gemstoneService.reduceUserGemstone(userId, userGemstone.getUserGemstoneId(), goodsUseType, null);
					
					if (size >= toolNum)
						break;
					size++;
				}
			} else {
				this.toolService.reduceTool(userId, toolId, toolNum, goodsUseType);
				toolList.add(goodsBeanBO);
			}			
		}
	}
	
	/**
	 * 扩展仓库背包
	 * 
	 * @param userId
	 * @param extendNum
	 * @return
	 */
	public boolean extendStorehouseNum(String userId, int extendNum) {
		return this.userExtInfoDaoCacheImpl.extendStorehouseNum(userId, extendNum);
	}
	
	public List<User> getUserRank(String columnName, int limit) {
		return this.userDao.getUserRank(columnName, limit);
	}
	
	public int getUserRank(int value, String columnName) {
		return this.userDao.getUserRank(value, columnName);
	}
	
	public List<UserExtInfo> getUserEffectiveRankList(int limit) {
		return this.userExtInfoDaoCacheImpl.getUserEffectiveRankList(limit);
	}
	
	public int getUserEffectiveRank(int effective) {
		return this.userExtInfoDaoCacheImpl.getUserEffectiveRank(effective);
	}
	
}

class TempCheckPackBean{
	public int equipMent;
	public int hero;
	public int gemstone;
	public Map<Integer,Integer> tools = new HashMap<Integer,Integer>();

}