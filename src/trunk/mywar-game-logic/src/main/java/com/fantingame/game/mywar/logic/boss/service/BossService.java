package com.fantingame.game.mywar.logic.boss.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.plugin.IAppPlugin;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.model.SystemConfigData;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.RandomUtils;
import com.fantingame.game.msgbody.client.boss.BossAction_attackBossInfoRes;
import com.fantingame.game.msgbody.client.boss.BossAction_startAttackBossRes;
import com.fantingame.game.msgbody.client.boss.EffectBO;
import com.fantingame.game.msgbody.client.boss.UserAttactBossInfoBO;
import com.fantingame.game.msgbody.client.boss.UserBossInfoBO;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.notify.boss.Boss_pushUserAttackInfoNotify;
import com.fantingame.game.msgbody.notify.boss.Boss_pushWorldBossCurrentLifeNotify;
import com.fantingame.game.msgbody.notify.boss.Boss_pushWorldBossDieNotify;
import com.fantingame.game.msgbody.notify.boss.Boss_pushWorldBossInfoNotify;
import com.fantingame.game.msgbody.notify.boss.Boss_pushWorldBossRankNotify;
import com.fantingame.game.msgbody.notify.boss.UserBossDataBO;
import com.fantingame.game.msgbody.notify.boss.UserBossRankBO;
import com.fantingame.game.msgbody.notify.boss.WorldBossInfoBO;
import com.fantingame.game.mywar.logic.boss.bean.UserBossBean;
import com.fantingame.game.mywar.logic.boss.bean.WorldBossInfo;
import com.fantingame.game.mywar.logic.boss.bean.WorldBossRoom;
import com.fantingame.game.mywar.logic.boss.dao.cache.SystemBossMapDaoCache;
import com.fantingame.game.mywar.logic.boss.model.SystemBossMap;
import com.fantingame.game.mywar.logic.chat.constant.ChatConstant;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.equip.service.EquipService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.hero.model.SystemHero;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroAttribute;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.message.constant.MessageType;
import com.fantingame.game.mywar.logic.message.service.MessageService;
import com.fantingame.game.mywar.logic.rank.dao.RankLogDao;
import com.fantingame.game.mywar.logic.rank.model.RankLog;
import com.fantingame.game.mywar.logic.scene.model.SystemScene;
import com.fantingame.game.mywar.logic.scene.service.SceneService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.room.RestrictionsRule;
import com.fantingame.game.server.room.RoomManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class BossService implements IAppPlugin, ModuleEventHandler {

	@Autowired
	private SystemBossMapDaoCache systemBossMapDao;
	@Autowired
	private HeroService heroService;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private MessageService messageService;
	@Autowired
	private SceneService sceneService;	
	@Autowired
	private UserService userService;
	@Autowired 
	private EquipService equipService;
	@Autowired
	private RankLogDao rankLogDao;
	
	private WorldBossInfo boss;
	private Map<String, UserBossBean> userBossMap = Maps.newConcurrentMap();
	private Map<String, WorldBossRoom> roomMap = Maps.newConcurrentMap();
	
	private final static int RANK_SIZE = 10;
	
	@SuppressWarnings("unused")
	private String firstAttackUserId = "";
	@SuppressWarnings("unused")
	private String lastestKillBossUserId = "";
	/**
	 * 伤害排行榜
	 */
	//private UserBossBean[] hurtBossRank = new UserBossBean[RANK_SIZE];
	/**
	 * 治疗量排行榜
	 */
	//private UserBossBean[] treatmentRank = new UserBossBean[RANK_SIZE];
	/**
	 * 承受伤害量
	 */
	//private UserBossBean[] beHitRank = new UserBossBean[RANK_SIZE];
	
	private final static int IS_LIVE = 1;
	private final static int HAS_DIE = 0;
	private final static int RELIVE = 2;
	private final static int LEAVE = 3;
	
	private final static int BOSS_NOT_BORN = 2001;
	private final static int BOSS_HAS_NOT_OPEN = 2002;
	private final static int HAS_NOT_RELIVE = 2003;
	/**
	 * 开始攻打boss
	 * 
	 * @param userId
	 * @param mapId
	 */
	public BossAction_startAttackBossRes startAttackBoss(String userId, int mapId, int x, int y) {
		if (boss == null)
			throw new ServiceException(BOSS_NOT_BORN, "boss还未诞生......time:" + System.currentTimeMillis());
		
		if (boss.getMapId() != mapId)
			throw new ServiceException(SystemConstant.FAIL_CODE, "攻打的boss地图不一致，boss.mapId=" + boss.getMapId() + ", mapId=" + mapId);
		
		if (!isBossOpen())
			throw new ServiceException(BOSS_HAS_NOT_OPEN, "还没有到攻打boss的时候.....");
		
		BossAction_startAttackBossRes res = new BossAction_startAttackBossRes();
		UserHeroBO userHero = this.heroService.getUserTeamLeader(userId);
		UserBossBean userBossBean = userBossMap.get(userId);
		if (userBossBean != null) {
			if (userBossBean.getStatus() == IS_LIVE) 
				throw new ServiceException(SystemConstant.FAIL_CODE, "还没挂掉丫......");
				
			// TODO 判断下复活时间
			int waitingTime = 5 * 1000;
//			int waitingTime = this.configDataDao.getInt(ConfigKey.boss_user_relive_need_time, 60) * 1000;
			Date now = new Date();
			if (userBossBean.getStatus() == HAS_DIE && now.getTime() - userBossBean.getDieTime().getTime() < waitingTime)
				throw new ServiceException(HAS_NOT_RELIVE, "还没复活呢！！！");			
			
			userBossBean.setStatus(IS_LIVE);
			WorldBossRoom room = getWorldBossRoom(userBossBean);
			
			List<UserEquipBO> equipList = equipService.getUserHeroEquipList(userId, userHero.getUserHeroId());
			room.addUser(userBossBean, x, y, userHero, equipList);
			
			// 加载其他用户
			res.setUserDataList(createUserBossDataBOList(userId, userBossBean.getRoom().getAllUserData()));
			userBossBean.isEntered = true;
			res.setIsRoomOwner(userBossBean.getRoomOwner());
			return res;
		}		
		
		User user = this.userService.getByUserId(userId);
		userBossBean = new UserBossBean(userId, user.getName(), userHero.getSystemHeroId(), IS_LIVE);
		WorldBossRoom room = getWorldBossRoom(userBossBean);
		
		// 把自己加进去，推送给别人
		List<UserEquipBO> equipList = equipService.getUserHeroEquipList(userId, userHero.getUserHeroId());
		room.addUser(userBossBean, x, y, userHero, equipList);
		
		// 加载其他用户
		res.setUserDataList(createUserBossDataBOList(userId, userBossBean.getRoom().getAllUserData()));
		userBossBean.isEntered = true;
		userBossMap.put(userId, userBossBean);
		res.setIsRoomOwner(userBossBean.getRoomOwner());
		return res;
	}	
	
	/**
	 *  是否开启了boss战
	 * 
	 * @return
	 */
	private boolean isBossOpen() {
		if (boss == null)
			return false;
		
		if (boss.getBossStatus() == BOSS_OPEN)
			return true;
			
		return false;
	}
	
	/**
	 * 攻击Boss信息
	 * 
	 * @param userId
	 * @param infoList
	 * @return
	 */
	public BossAction_attackBossInfoRes attackBossInfo(String userId, List<EffectBO> effectList, int skillId, String userIdStr) {
		if (effectList == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "攻击数据为空？？？？为何？");
		
		UserBossBean roomOwner = userBossMap.get(userId);
		if (roomOwner == null)
			throw new ServiceException(SystemErrorCode.DATA_NULL, "为什么是空的？？这货没有攻打过世界boss丫！userId=" + userId);
		
		BossAction_attackBossInfoRes res = new BossAction_attackBossInfoRes();
		WorldBossRoom room = roomOwner.getRoom();
		if (!room.getHouseOwner().equals(userId) && !roomOwner.isRoomOwner()) {
			res.setCurrentLife(boss.getLife());
			LogSystem.warn("这货不是房主，userId=" + userId + ",房主id：" + room.getHouseOwner());
			return res;
		}
			
		boolean isNeedChangeRoomOwner = false;		
		for (EffectBO effect : effectList) {
			List<UserAttactBossInfoBO> infoList = effect.getAttactList();
			for (UserAttactBossInfoBO infoBO : infoList) {
				UserBossBean userBean = this.userBossMap.get(infoBO.getUserId());
				if (userBean == null || !userBean.isEntered)
					continue;
			
				// TODO 对用户的攻击数据判断    
				userBean.setHurt(infoBO.getHurt());
				userBean.setTreatment(infoBO.getTreatment());
				userBean.setBeHit(infoBO.getBeHit());
			
				// 需要判断是否在该回合挂掉了
				userBean.setStatus(infoBO.getStatus());
				if (userBean.getStatus() == HAS_DIE) {
					userBean.isEntered = false;
					userBean.setDieTime(new Date());
					infoBO.setDieTime(userBean.getDieTime().getTime());
				
					userBean.getRoom().leave(infoBO.getUserId());
					if (userBean.isRoomOwner())
						isNeedChangeRoomOwner = true;
				}
			
				userBean.getPosition().x = infoBO.getX();
				userBean.getPosition().y = infoBO.getY();
			
				// 扣除boss血量
				if (infoBO.getHurt() > 0)
					reduceBossLife(userBean.getUserId(), userBean.getUserName(), infoBO.getHurt());			
			}
		}
		
		// 更换房主
		if (isNeedChangeRoomOwner)
			room.changeRoomOwner();
		
		// 广播给其他成员		
		Boss_pushUserAttackInfoNotify notify = new Boss_pushUserAttackInfoNotify();
		notify.setEffectList(effectList);
		notify.setSkillId(skillId);
		notify.setUserIdStr(userIdStr);
		
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);
		restrictionsRule.addUser(userId);
		MsgDispatchCenter.disPatchUserRoomMsg("Boss_pushUserAttackInfo", roomOwner.getRoom().getRoomId(), notify, restrictionsRule);		
		res.setCurrentLife(boss.getLife());
		return res;
	}
	
    private boolean reduceBossLife(String userId, String userName, int hurtNum) {
    	if (boss.getLife() > 0) {
    		long leftLife = boss.getLife() - hurtNum;
    		if (leftLife < 0) {
    			leftLife = 0 ;
    		}
    		
    		//设置 最后攻击者姓名
    		if (leftLife == 0) {
    			this.lastestKillBossUserId = userId;
    			LogSystem.info("boss被打死了，打死他的人为" + userName);
    		}
    		
    		boss.setLife(leftLife);    		
    		//添加攻击消息    		
    		return true;
    	}
    	return false;
    }
	
	private List<UserBossDataBO> createUserBossDataBOList(String userId,  Collection<UserBossBean> beanList) {
		List<UserBossDataBO> list = Lists.newArrayList();
		if (beanList == null)
			return null;
		
		for (UserBossBean userBossBean : beanList) {
//			// 过滤掉自己
//			if (userBossBean.getUserId().equals(userId))
//				continue;
			
			list.add(createUserBossDataBO(userBossBean));				
		}	
		
		return list;
	}
	
	private UserBossDataBO createUserBossDataBO(UserBossBean userBossBean) {
		UserBossDataBO userBossDataBO = new UserBossDataBO();
		userBossDataBO.setUserId(userBossBean.getUserId());
		userBossDataBO.setUserName(userBossBean.getUserName());
		userBossDataBO.setPosX(userBossBean.getPosition().x);
		userBossDataBO.setPosY(userBossBean.getPosition().y);
		
		UserHeroBO userHero = this.heroService.getUserTeamLeader(userBossBean.getUserId());
		List<UserEquipBO> equipList = equipService.getUserHeroEquipList(userBossBean.getUserId(), userHero.getUserHeroId());
		userBossDataBO.setUserHeroBO(userHero);
		userBossDataBO.setEquipList(equipList);
		
		return userBossDataBO;
	}
	
	// 每个房间最大人数
	private final static int MAX_ROOM_PERSON_NUM = 20;
	private WorldBossRoom getWorldBossRoom(UserBossBean userBossBean) {
		if (roomMap.size() == 0) {
			// 创建房间
			WorldBossRoom room = new WorldBossRoom();
			room.setRoomId("wb_" + roomMap.size());
			
			RoomManager.getInstatnce().addRoom(room.getRoomId(), room);
			firstAttackUserId = userBossBean.getUserId();
			roomMap.put(room.getRoomId(), room);
			return room;
		}
	
		// 优先进入之前的同一个房间
		if (userBossBean.getRoom() != null) {
			WorldBossRoom room = roomMap.get(userBossBean.getRoom().getRoomId());			
			if (room.getUserIds().size() < MAX_ROOM_PERSON_NUM)				
				return room;
		}
		
		// 加入一个房间
		WorldBossRoom bossRoom = null;
		for (WorldBossRoom room : roomMap.values()) {
			if (room.getUserIds().size() < MAX_ROOM_PERSON_NUM) {							
				bossRoom = room;
				break;
			}
		}
		
		// 创建房间
		if (bossRoom == null) {
			bossRoom = new WorldBossRoom();
			bossRoom.setRoomId("wb_" + roomMap.size());
			
			RoomManager.getInstatnce().addRoom(bossRoom.getRoomId(), bossRoom);
			roomMap.put(bossRoom.getRoomId(), bossRoom);
		}
		
		return bossRoom;
	}
	
	private final static int BOSS_OPEN = 1;
	private final static int BOSS_CLOSE = 0;
	/**
	 * boss的诞生 
	 */
	public void bossBorn() {
		List<SystemBossMap> bossMapList = this.systemBossMapDao.getSystemBossMapList();
		if (bossMapList == null || bossMapList.size() == 0)
			throw new ServiceException(SystemConstant.FAIL_CODE, "数值有问题，没有配置世界BOSS的诞生....");
		
		SystemBossMap todayBoss = null;
		int rand = RandomUtils.getRandomNum(1, 10000);
		for (SystemBossMap bossMap : bossMapList) {
			if (bossMap.getLowerNum() <= rand && rand <= bossMap.getUpperNum())
				todayBoss = bossMap;
		}
		
		if (todayBoss == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "世界Boss数值配置的有问题。");
		
		SystemHero systemHero = heroService.getSystemHero(todayBoss.getSystemHeroId());
		SystemHeroAttribute systemHeroAttribute = heroService.getSystemHeroAttribute(todayBoss.getSystemHeroId());
		if (systemHero == null || systemHeroAttribute == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有世界boss的数据？？？mapId=" + todayBoss.getMapId() + ", systemHeroId=" + todayBoss.getSystemHeroId());
		
		this.boss = new WorldBossInfo();
		boss.setMaxLife(systemHeroAttribute.getHp());
		boss.setLife(systemHeroAttribute.getHp());
		boss.setContinueTimes(30 * 60 * 1000);
		boss.setSystemHeroId(todayBoss.getSystemHeroId());
		boss.setBossLevel(1);
		boss.setMapId(todayBoss.getMapId());
		
		SystemConfigData configData = this.configDataDao.get(ConfigKey.boss_start_attack_time);
		// Date now = new Date();
		String timeStr = "21:00";
		
		if (configData != null)
			timeStr = configData.getConfigValue();
		
//		Date startTime = DateUtils.str2Date(DateUtils.getDate() + " " + timeStr + ":00");
		Date startTime = new Date();
		boss.setOpenTime(startTime);
		boss.setBossStatus(BOSS_OPEN);
		
		// 开始推送世界Boss战已开启，跑马灯
		Boss_pushWorldBossInfoNotify notify = new Boss_pushWorldBossInfoNotify();
		WorldBossInfoBO infoBO = createBossInfoBO();
		notify.setWorldBossInfoBO(infoBO);
		
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);		
		MsgDispatchCenter.disPatchUserRoomMsg("Boss_pushWorldBossInfo", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, notify, restrictionsRule);
		
		SystemScene systemScene = sceneService.getSystemScene(boss.getMapId());
		Map<String, String> params = Maps.newConcurrentMap();
		params.put("heroName", systemHero.getHeroName());
		params.put("mapName", systemScene.getSceneName());
		params.put("time", timeStr);
		messageService.sendMsg(params, "All", MessageType.MESSAGE_TYPE_BOSS_COMING_SOON);		
		return;
	}

	private WorldBossInfoBO createBossInfoBO() {
		WorldBossInfoBO infoBO = new WorldBossInfoBO();
		infoBO.setMapId(boss.getMapId());
		infoBO.setBossLevel(boss.getBossLevel());
		infoBO.setOpenTime(boss.getOpenTime().getTime());
		infoBO.setContinueTimes((long) boss.getContinueTimes());
		infoBO.setMaxLife(boss.getMaxLife());
		infoBO.setCurrentLife(boss.getLife());		
		
		return infoBO;
	}

	/**
	 * 获取世界boss相关信息
	 * 
	 * @return
	 */
	public WorldBossInfoBO getWorldBossInfo() {
		if (boss == null)
			return null;
		
		return createBossInfoBO();
	}
	
	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		// TODO Boss战开始后，房主掉线怎么办？？
		if(handlerType.equals(ModuleEventConstant.USER_LOGINOUT_EVENT)){
			if (!isBossOpen())
				return;
				
        	String userId = baseModuleEvent.getStringValue("userId", "");        
        	UserBossBean userBossBean = userBossMap.get(userId);
        	if (userBossBean == null)
        		return;
        		
        	if (userBossBean.getStatus() == LEAVE)
        		return;
        	
        	userBossBean.setStatus(HAS_DIE);
        	userBossBean.setDieTime(new Date());
        	userBossBean.setEntered(false);
        	
        	leave(userBossBean);       	
        	LogSystem.warn("userId[" + userId + "]，登出游戏，设置其为死亡，并退出房间.");
        }
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

	@Override
	public void startup() throws Exception {
		// 检测boss状态的线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						checkWorldBossStatus();						
						
						Thread.sleep(1000 * 2);
					} catch (Exception e) {
						LogSystem.error(e, "检测boss开启信息.....失败？？");
					}	
				}
			}			
		}).start();		
		
		// 推送boss当前血量的线程
		new Thread(new Runnable() {			
			@Override
			public void run() {
				while (true) {
					try {
						pushBossLife();
						
						Thread.sleep(1000 * 2);
					} catch (Exception e) {
						LogSystem.error(e, "推送boss当前血量线程.....失败？？");
					}
				}
			}
		}).start();
		
		// 推送当前排行榜线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {				
					try {
						pushRankChange();
						
						Thread.sleep(1000 * 5);
					} catch (Exception e) {
						LogSystem.error(e, e.getMessage() + " 推送世界boss战的排行榜线程.....失败？");
					}
				}
			}
		}).start();		
	}
	
	private List<UserBossBean> getUserBossBeanList() {
		Collection<UserBossBean> collection = this.userBossMap.values();
		List<UserBossBean> list = Lists.newArrayList();
		for (UserBossBean userBossBean : collection) {
			if (userBossBean.getHurt() > 0 || userBossBean.getTreatment() > 0 
					|| userBossBean.getBeHit() > 0)
				list.add(userBossBean);
		}
		
		return list;
	}
	
	private final static int RANK_TYPE_HURT = 1;
	private final static int RANK_TYPE_TREATMENT = 2;
	private final static int RANK_TYPE_BEHIT = 3;
	/**
	 * 推送boss战排行榜
	 */
	private void pushRankChange() {
		if (!this.isBossOpen())
			return;
		
		List<UserBossBean> list = getUserBossBeanList();		
		// 伤害排行榜
		List<UserBossBean> hurtRankList = sortUserHurtRank(list);
		Boss_pushWorldBossRankNotify notify = new Boss_pushWorldBossRankNotify();
		List<UserBossRankBO> rankList = createUserBossRankList(hurtRankList, RANK_TYPE_HURT);
		notify.setHurtRankList(rankList);
		
		// 治疗量排行榜
		List<UserBossBean> treatmentRankList = sortTreatmentRank(list);
		rankList = createUserBossRankList(treatmentRankList, RANK_TYPE_TREATMENT);
		notify.setTreamentRankList(rankList);
		
		// 承受伤害量排行榜
		List<UserBossBean> beHitRankList = sortBeHitRank(list);
		rankList = createUserBossRankList(beHitRankList, RANK_TYPE_BEHIT);
		notify.setBeHitRankList(rankList);
		
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);		
		MsgDispatchCenter.disPatchUserLowerRoomMsg("Boss_pushWorldBossRank", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, notify, restrictionsRule);
	}
	
	private List<UserBossRankBO> createUserBossRankList(List<UserBossBean> beanList, int type) {
		List<UserBossRankBO> rankList = Lists.newArrayList();
		int rank = 1;
		for (UserBossBean userBossBean : beanList) {
			UserBossRankBO rankBo = new UserBossRankBO();
			rankBo.setRank(rank);
			rankBo.setUserName(userBossBean.getUserName());
			if (type == RANK_TYPE_HURT) {
				rankBo.setValue((long) userBossBean.getHurt());
			} else if (type == RANK_TYPE_TREATMENT) {
				rankBo.setValue((long) userBossBean.getTreatment());
			} else {
				rankBo.setValue((long) userBossBean.getBeHit());
			}
			
			rankList.add(rankBo);
			if (rankList.size() >= RANK_SIZE)
				break;
			
			rank++;
		}
		
		return rankList;
	}
	
	private List<UserBossBean> sortBeHitRank(List<UserBossBean> list) {
		List<UserBossBean> userList = Lists.newArrayList();
		userList.addAll(list);
		Collections.sort(userList, new Comparator<UserBossBean>() {
			@Override
			public int compare(UserBossBean o1, UserBossBean o2) {
				if (o1.getBeHit() > o2.getBeHit())
					return -1;
				else if (o1.getBeHit() < o2.getBeHit())
					return 1;
				return 0;
			}
		});
		
		return userList;
	}
	
	private List<UserBossBean> sortTreatmentRank(List<UserBossBean> list) {
		List<UserBossBean> userList = Lists.newArrayList();
		userList.addAll(list);
		Collections.sort(userList, new Comparator<UserBossBean>() {
			@Override
			public int compare(UserBossBean o1, UserBossBean o2) {
				if (o1.getTreatment() > o2.getTreatment())
					return -1;
				else if (o1.getTreatment() < o2.getTreatment())
					return 1;
				return 0;
			}
		});
		
		return userList;
	}
	
	private List<UserBossBean> sortUserHurtRank(List<UserBossBean> collection) {
		List<UserBossBean> userList = Lists.newArrayList();
		userList.addAll(collection);
		Collections.sort(userList, new Comparator<UserBossBean>() {
			@Override
			public int compare(UserBossBean o1, UserBossBean o2) {
				if (o1.getHurt() > o2.getHurt())
					return -1;
				else if (o1.getHurt() < o2.getHurt())
					return 1;
				return 0;
			}
		});
		
		return userList;
	}
	
	/**
	 * 推送boss当前血量 
	 */
	private void pushBossLife() {
		if (!this.isBossOpen())
			return;
		
		Boss_pushWorldBossCurrentLifeNotify notify = new Boss_pushWorldBossCurrentLifeNotify();
		notify.setCurrentLife(boss.getLife());
		
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);		
		MsgDispatchCenter.disPatchUserRoomMsg("Boss_pushWorldBossCurrentLife", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, notify, restrictionsRule);
	}
	
	/**
	 * 检测世界boss的开启状态
	 */
	private void checkWorldBossStatus() {
		if (boss == null)
			return;
		
		if (this.boss.getBossStatus() == BOSS_OPEN && (!isInOpenTime() || boss.getLife() == 0)) {
			boss.setBossStatus(BOSS_CLOSE);
			worldBossEnd();
		}	
	}
	
	/**
	 * TODO boss活动结束
	 */
	private void worldBossEnd() {
		//发送消息先
    	sendWorldBossClose();    	
    	
    	// TODO 开始发奖励
    	

    	// 存储下排行榜
    	saveRank();   	
    	
    	// 将boss相关信息置空
    	firstAttackUserId = "";
    	lastestKillBossUserId = "";
    	this.boss = null;
    	this.userBossMap.clear();
    	this.roomMap.clear();
	} 
	
	private final static String WORLD_BOSS_HURT_RANK_LOG_KEY = "world_boss_hurt_rank_log_key";
	private final static String WORLD_BOSS_TREATMENT_RANK_LOG_KEY = "world_boss_treatment_rank_log_key";
	private final static String WORLD_BOSS_BEHIT_RANK_LOG_KEY = "world_boss_behit_rank_log_key";
	private void saveRank() {
		List<UserBossBean> userBossList = this.getUserBossBeanList();
		List<RankLog> rankLogList = Lists.newArrayList();
		
    	List<UserBossBean> hurtList = sortUserHurtRank(userBossList);    	
    	List<UserBossRankBO> hurtRankList = createUserBossRankList(hurtList, RANK_TYPE_HURT);
    	RankLog rankLog = createRankLog(hurtRankList, WORLD_BOSS_HURT_RANK_LOG_KEY);
    	rankLogList.add(rankLog);
    	
    	List<UserBossBean> treatmentList = sortTreatmentRank(userBossList);
    	List<UserBossRankBO> treatmentRankList = createUserBossRankList(treatmentList, RANK_TYPE_TREATMENT);
    	rankLog = createRankLog(treatmentRankList, WORLD_BOSS_TREATMENT_RANK_LOG_KEY);
    	rankLogList.add(rankLog);
    	
    	List<UserBossBean> behitList = sortBeHitRank(userBossList);
    	List<UserBossRankBO> behitRankList = createUserBossRankList(behitList, RANK_TYPE_BEHIT);
    	rankLog = createRankLog(behitRankList, WORLD_BOSS_BEHIT_RANK_LOG_KEY);
    	rankLogList.add(rankLog);
    	this.rankLogDao.addList(rankLogList);
	}
	
	private RankLog createRankLog(List<UserBossRankBO> rankList, String key) {
    	Date now = new Date();
		RankLog rankLog = new RankLog();
		
    	rankLog.setRankKey(key);
    	rankLog.setDate(DateUtils.getDate(now));
    	rankLog.setCreatedTime(now);
    	rankLog.setRankData(JSON.toJSONString(rankList));
    	return rankLog;
	}
	
	private void sendWorldBossClose() {
		Boss_pushWorldBossDieNotify notify = new Boss_pushWorldBossDieNotify();
		if (boss.getLife() == 0)
			notify.setStatus(HAS_DIE);
		else 
			notify.setStatus(1);
		
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);		
		MsgDispatchCenter.disPatchUserRoomMsg("Boss_pushWorldBossDie", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, notify, restrictionsRule);
	}
	
	private boolean isInOpenTime() {		
		if (boss == null || boss.getBossStatus() == BOSS_CLOSE) 
			return false;
		
		Date now = new Date();
		Date startTime = boss.getOpenTime();
		Date endTime = new Date(boss.getOpenTime().getTime() + boss.getContinueTimes());
		if (now.before(endTime) && now.after(startTime))
			return true;
		
		return false;
	}

	private final static int YOU_NOT_DIE = 2001;
	/**
	 * 复活
	 * 
	 * @param userId
	 * @return
	 */
	public int relive(String userId) {
		if (!isInOpenTime())
			throw new ServiceException(BOSS_HAS_NOT_OPEN, "Boss战已结束.....无需复活");
		
		UserBossBean userBean = this.userBossMap.get(userId);
		if (userBean.getStatus() != HAS_DIE)
			throw new ServiceException(YOU_NOT_DIE, "你都还没死......复活个蛋丫");		
		
		User user = this.userService.getByUserId(userId);
		int cost = this.configDataDao.getInt(ConfigKey.boss_user_relive_cost, 50);
		
		if (user.getMoney() < cost)
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		if (!userService.reduceMoney(userId, cost, GoodsUseType.REDUCE_BOSS_USER_RELIVE))
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
			
		userBean.setStatus(RELIVE);
		return user.getMoney();
	}
	
	/**
	 * 离开战场
	 * 
	 * @param userId
	 */
	public void userLeave(String userId) {
		if (!isInOpenTime())
			return;
		
		UserBossBean userBean = this.userBossMap.get(userId);
		if (userBean == null)
			return;
		
		if (userBean.getStatus() == IS_LIVE) {
			userBean.setStatus(LEAVE);
			userBean.setEntered(false);
			
			leave(userBean);			
		}
		return;
	}
	
	private void leave(UserBossBean userBean) {
		String userId = userBean.getUserId();
		
		// 离开房间
		WorldBossRoom room = userBean.getRoom();
		room.leave(userId);        	
		if (userBean.isRoomOwner())
			room.changeRoomOwner();
					
		userBean.setRoomOwner(false);		
	}
	
	/**
	 * 获取用户boss战的信息，总推时用
	 * 
	 * @param userId
	 * @return
	 */
	public UserBossInfoBO getUserBossInfo(String userId) {
		UserBossInfoBO infoBO = new UserBossInfoBO();
		if (boss == null)
			return infoBO;
		
		UserBossBean bean = this.userBossMap.get(userId);
		if (bean == null)
			return infoBO;
		
		infoBO.setDieTime(bean.getDieTime().getTime());
		infoBO.setStatus(bean.getStatus());
		
		return infoBO;
	}
	
	@Override
	public void shutdown() throws Exception {
	}

	@Override
	public int cpOrder() {
		return 0;
	}
	
}
