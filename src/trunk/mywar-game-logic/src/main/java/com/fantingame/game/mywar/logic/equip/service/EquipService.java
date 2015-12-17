package com.fantingame.game.mywar.logic.equip.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.NumberUtils;

import com.alibaba.fastjson.JSON;
import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.IDGenerator;
import com.fantingame.game.common.utils.RandomUtils;
import com.fantingame.game.log.service.LogService;
import com.fantingame.game.msgbody.client.equip.EquipAction_equipForgeRes;
import com.fantingame.game.msgbody.client.equip.EquipAction_equipMagicRes;
import com.fantingame.game.msgbody.client.equip.EquipAction_equipRecycleRes;
import com.fantingame.game.msgbody.client.equip.EquipAction_unWearEquipRes;
import com.fantingame.game.msgbody.client.equip.EquipAction_wearEquipRes;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import com.fantingame.game.msgbody.client.gemstone.UserGemstoneBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.tool.UserToolBO;
import com.fantingame.game.mywar.logic.achievement.check.IAchievementChecker;
import com.fantingame.game.mywar.logic.achievement.constant.AchievementConstant;
import com.fantingame.game.mywar.logic.achievement.service.AchievementService;
import com.fantingame.game.mywar.logic.activity.constant.ActivityTaskConstant;
import com.fantingame.game.mywar.logic.activity.service.ActivityService;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.equip.bean.ToolOperationBean;
import com.fantingame.game.mywar.logic.equip.contant.EquipConstant;
import com.fantingame.game.mywar.logic.equip.dao.UserEquipDao;
import com.fantingame.game.mywar.logic.equip.dao.cache.SystemEquipAttrDaoCache;
import com.fantingame.game.mywar.logic.equip.dao.cache.SystemEquipDaoCache;
import com.fantingame.game.mywar.logic.equip.dao.cache.SystemEquipFixedAttrDaoCache;
import com.fantingame.game.mywar.logic.equip.dao.cache.SystemEquipForgeDaoCache;
import com.fantingame.game.mywar.logic.equip.dao.cache.SystemEquipMagicDaoCache;
import com.fantingame.game.mywar.logic.equip.model.SystemEquip;
import com.fantingame.game.mywar.logic.equip.model.SystemEquipFixedAttr;
import com.fantingame.game.mywar.logic.equip.model.SystemEquipMagic;
import com.fantingame.game.mywar.logic.equip.model.SystemToolForge;
import com.fantingame.game.mywar.logic.equip.model.UserEquip;
import com.fantingame.game.mywar.logic.gemstone.model.Attribute;
import com.fantingame.game.mywar.logic.gemstone.model.UserGemstone;
import com.fantingame.game.mywar.logic.gemstone.service.GemstoneService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.hero.constant.LogType;
import com.fantingame.game.mywar.logic.hero.model.SystemHero;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.mall.model.UserBuyBackInfo;
import com.fantingame.game.mywar.logic.task.check.ITaskHitChecker;
import com.fantingame.game.mywar.logic.task.constant.TaskLibraryType;
import com.fantingame.game.mywar.logic.task.service.UserTaskService;
import com.fantingame.game.mywar.logic.tool.model.SystemMagicReelAttr;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class EquipService implements ModuleEventHandler {

	@Autowired
	private SystemEquipDaoCache systemEquipDaoCache;
	@Autowired
	private SystemEquipAttrDaoCache systemEquipAttrDaoCache;
	@Autowired
	private UserEquipDao userEquipDao;
	@Autowired
	private UserService userService;
	@Autowired
	private HeroService heroService;
	@Autowired
    private LogService logService;
	@Autowired
	private SystemEquipFixedAttrDaoCache systemEquipFixedAttrDaoCache;
	@Autowired
	private SystemEquipForgeDaoCache systemEquipForgeDaoCache;
	@Autowired
	private GoodsDealService goodsDealService;
	@Autowired
	private ToolService toolService;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private GemstoneService gemstoneService;
	@Autowired
	private SystemEquipMagicDaoCache systemEquipMagicDaoCache;
	@Autowired
	private AchievementService achievementService;
	@Autowired
	private ActivityService activityService;
	
	/**
	 * 装备锻造
	 */
	private Map<String, ToolOperationBean> forgeMap = Maps.newConcurrentMap();	
	/**
	 * 装备回收
	 */
	private Map<String, ToolOperationBean> recycleMap = Maps.newConcurrentMap();
	/**
	 * 装备附魔
	 */
	private Map<String, ToolOperationBean> magicMap = Maps.newConcurrentMap();
	
	/**
	 * 获取用户装备列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserEquipBO> getUserEquipList(String userId) {
		List<UserEquip> userEquipList = this.userEquipDao.getUserEquipList(userId);
		List<UserEquipBO> userEquipBOList = new ArrayList<UserEquipBO>();
		
		for (UserEquip userEquip : userEquipList) {
			userEquipBOList.add(createUserEquipBO(userEquip));
		}
		
		return userEquipBOList;
	}
	
	private UserEquipBO createUserEquipBO(UserEquip userEquip) {
		UserEquipBO userEquipBO = new UserEquipBO();
		userEquipBO.setUserId(userEquip.getUserId());
		userEquipBO.setUserHeroId(userEquip.getUserHeroId());
		userEquipBO.setUserEquipId(userEquip.getUserEquipId());
		if (userEquip.getEquipMainAttr() != null && userEquip.getEquipMainAttr().length() > 0) {
			userEquipBO.setEquipMainAttr(userEquip.getEquipMainAttr() + ",stamina");
		} else if (userEquip.getEquipMainAttr() != null) {
			userEquipBO.setEquipMainAttr(userEquip.getEquipMainAttr());
		}
		userEquipBO.setEquipSecondaryAttr(userEquip.getEquipSecondaryAttr());
		userEquipBO.setMagicEquipAttr(userEquip.getMagicEquipAttr());
		userEquipBO.setEquipId(userEquip.getEquipId());
		userEquipBO.setPos(userEquip.getPos());
		userEquipBO.setHoleNum(userEquip.getHoleNum());
		userEquipBO.setStorehouseNum(userEquip.getStorehouseNum());
		
		return userEquipBO;
	}
	
	private static final int EQUIP_NOT_EXIST = 2001;
	private static final int POS_IS_WRONG = 2002;
	private static final int CAREER_IS_WRONG = 2003;
	private static final int WEARING_DOUBLE_EQUIP = 2004;
	/**
	 * 穿戴神器
	 * 
	 * @param userId
	 * @param userEquipId
	 * @param userHeroId
	 * @param targetUserHeroId
	 * @return
	 */
	public EquipAction_wearEquipRes wearEquip(String userId, String userEquipId, String userHeroId, int pos) {
		if (StringUtils.isBlank(userEquipId) || StringUtils.isBlank(userHeroId))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，传入参数为空");
		
		UserEquip userEquip = this.userEquipDao.getUserEquip(userId, userEquipId);
		if (userEquip == null)
			throw new ServiceException(EQUIP_NOT_EXIST, "用户装备不存在,userId[" + userId + "],userEquipId[" + userEquipId + "]");		
		
		SystemEquip systemEquip = this.systemEquipDaoCache.getSystemEquip(userEquip.getEquipId());
		UserHeroBO userHeroBO = this.heroService.getUserHeroBO(userId, userHeroId);
		
		if (systemEquip == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "系统装备不存在？？？数据表没配？equipId=" + userEquip.getEquipId());
		
		if (userHeroBO.getLevel() < systemEquip.getNeedLevel())
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "英雄等级不足");
		
		// 所需职业判断
		if (StringUtils.isNotBlank(systemEquip.getNeedCareer()) && !systemEquip.getNeedCareer().equals("0")) {
			String[] careerArr = systemEquip.getNeedCareer().split(",");
			Map<Integer, Integer> careerMap = Maps.newConcurrentMap();
			for (String career : careerArr) {
				careerMap.put(Integer.parseInt(career), Integer.parseInt(career));
			}
			
			SystemHero systemHero = this.heroService.getSystemHero(userHeroBO.getSystemHeroId());
			if (!careerMap.containsKey(systemHero.getCareerId()))
				throw new ServiceException(CAREER_IS_WRONG, "装备穿戴职业不符,heroCareer=" + systemHero.getCareerId() 
						+ "equipNeedCareer=" + systemEquip.getNeedCareer());			
		}
		
		// 可穿戴位置map
		Map<Integer, Integer> map = Maps.newConcurrentMap();
		String[] posArr = systemEquip.getPos().split(",");
		for (String equipPos : posArr) {
			map.put(Integer.parseInt(equipPos), Integer.parseInt(equipPos));
		}
		
		// 副手的装备可以穿戴在主手位置
		if (pos != 0) {
			if (!map.containsKey(pos))
				throw new ServiceException(POS_IS_WRONG, "穿戴的位置不正确");
		}
		
		List<UserEquipBO> userEquipBOList = Lists.newArrayList();
		// 单独处理主手与副手位的问题
		if (map.size() > 1) {
			// 没有传位置时，穿戴单手武器，直接穿戴在主手位
			if (pos == 0)
				pos = 1;
			
			// 判断主手位是否穿戴双手剑
			if (pos == EquipConstant.POS_8) {
				UserEquip mainUserEquip = this.userEquipDao.getUserEquip(userId, userHeroId, EquipConstant.POS_1);
				if (mainUserEquip != null){
					SystemEquip mainEquip = this.systemEquipDaoCache.getSystemEquip(mainUserEquip.getEquipId());
					if (mainEquip.getPos().equals("1"))
						throw new ServiceException(WEARING_DOUBLE_EQUIP, "主手位穿戴着双手剑");
				}
			}
			
			// 将原来的卸下
			UserEquipBO beReplaceUserEquipBO = unWearEquip(userId, userHeroId, pos);
			if (beReplaceUserEquipBO != null)
				userEquipBOList.add(beReplaceUserEquipBO);
		} else {
			// 穿戴 双手装备  若穿戴两件单手武器，则将这两件卸下
			if (Integer.parseInt(systemEquip.getPos()) == EquipConstant.POS_1) {
				UserEquip firstUserEquip = this.userEquipDao.getUserEquip(userId, userHeroId, EquipConstant.POS_1);
				UserEquip eightUserEquip = this.userEquipDao.getUserEquip(userId, userHeroId, EquipConstant.POS_8);
				if (firstUserEquip != null && eightUserEquip != null) {
					String goodsIds = GoodsType.equip.intValue + "," + firstUserEquip.getEquipId() + ",1|" + GoodsType.equip.intValue + "," + eightUserEquip.getEquipId() + ",1";
					List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(goodsIds);
					this.userService.checkBag(userId, goodsList);
				}				
				
				pos = EquipConstant.POS_1;
				UserEquipBO beReplaceUserEquipBO = unWearEquip(userId, userHeroId, EquipConstant.POS_1);
				if (beReplaceUserEquipBO != null)
					userEquipBOList.add(beReplaceUserEquipBO);
				
				beReplaceUserEquipBO = unWearEquip(userId, userHeroId, EquipConstant.POS_8);
				if (beReplaceUserEquipBO != null)
					userEquipBOList.add(beReplaceUserEquipBO);
			} else {
				pos = Integer.parseInt(systemEquip.getPos());
				
				// 将原来的卸下
				UserEquipBO beReplaceUserEquipBO = unWearEquip(userId, userHeroId, pos);
				if (beReplaceUserEquipBO != null)
					userEquipBOList.add(beReplaceUserEquipBO);
			}
		}
		
		this.userEquipDao.changeEquipUserHeroId(userId, userEquipId, userHeroId, pos);
		// userEquip = this.userEquipDao.getUserEquip(userId, userEquipId);
		userEquipBOList.add(createUserEquipBO(userEquip));
		
		// 更新英雄装等
		heroService.updateHeroEffective(userId, userHeroId);
		userEquipMentOperatorLog(userId, userEquipId, systemEquip.getEquipId(), userHeroId, systemEquip.getLevel(), LogType.EQUIP_WEAR, new Date());
		EquipAction_wearEquipRes res = new EquipAction_wearEquipRes();
		res.setUserEquipBOList(userEquipBOList);
		return res;
	}
	
	/**
	 * 卸下装备
	 * 
	 * @param userId
	 * @param userHeroId
	 * @param pos
	 * @return
	 */
	private UserEquipBO unWearEquip(String userId, String userHeroId, int pos) {		
		UserEquip beReplaceEquip = this.userEquipDao.getUserEquip(userId, userHeroId, pos);
		if (beReplaceEquip != null) {
			this.userEquipDao.changeEquipUserHeroId(userId, beReplaceEquip.getUserEquipId(), "", pos);
			beReplaceEquip = this.userEquipDao.getUserEquip(userId, beReplaceEquip.getUserEquipId());
			SystemEquip systemEquip = this.systemEquipDaoCache.getSystemEquip(beReplaceEquip.getEquipId());
			
			userEquipMentOperatorLog(userId, beReplaceEquip.getUserEquipId(), systemEquip.getEquipId(), userHeroId, systemEquip.getLevel(), LogType.EQUIP_UNWEAR, new Date());
			return createUserEquipBO(beReplaceEquip);
		}
		
		return null;
	}
	
	/**
	 * 添加装备
	 * 
	 * @param userId
	 * @param goodsId
	 * @param isFristAdd
	 * @param goodsUseType
	 * @param drop
	 * @return
	 */
	public boolean addEquip(String userId, final int equipId, boolean isFristAdd, int goodsUseType, CommonGoodsBeanBO drop) {
		final SystemEquip systemEquip = systemEquipDaoCache.getSystemEquip(equipId);
		if(systemEquip == null)
    		throw new ServiceException(SystemConstant.FAIL_CODE, "找不到装备数据" + equipId);
    	
    	UserEquip userEquip = new UserEquip();
    	String userEquipMentId = IDGenerator.getID();
		userEquip.setUserEquipId(userEquipMentId);
		
		Date now = new Date();
		String[] posArr = systemEquip.getPos().split(",");
		
		// 单手的装备初始化为8号位
		if (posArr.length >= 2) {
			userEquip.setPos(8);
		} else {
			userEquip.setPos(Integer.parseInt(systemEquip.getPos()));
		}
		userEquip.setUserId(userId);
		userEquip.setEquipId(equipId);
		userEquip.setCreatedTime(now);
		userEquip.setUpdatedTime(now);
		userEquip.setStorehouseNum(0);
		
		SystemEquipFixedAttr fixedAttr = this.systemEquipFixedAttrDaoCache.getSystemEquipFixedAttr(equipId);
		// 随机主属性与次级属性
		EquipConstant equipContants = EquipConstant.instance();
		if (fixedAttr != null) {
			// 主属性
			if (StringUtils.isNotBlank(fixedAttr.getMainAttr()))
				userEquip.setEquipMainAttr(fixedAttr.getMainAttr());
			else 
				userEquip.setEquipMainAttr(randomEquipMainAttr(systemEquip, equipContants));
			
			// 次级属性
			if (StringUtils.isNotBlank(fixedAttr.getSecondaryAttr()))
				userEquip.setEquipSecondaryAttr(fixedAttr.getSecondaryAttr());
			else 
				userEquip.setEquipSecondaryAttr(randomEquipSecondaryAttr(systemEquip, equipContants));			
		} else {			
			userEquip.setEquipMainAttr(randomEquipMainAttr(systemEquip, equipContants));
			userEquip.setEquipSecondaryAttr(randomEquipSecondaryAttr(systemEquip, equipContants));
		}
		
		// 随机装备的镶孔概率 暂定50%
		if (systemEquip.getQuality() >= EquipConstant.QUALITY_BLUE) {
			int random = RandomUtils.getRandomNum(1, 10000);
			if (random <= 5000)
				userEquip.setHoleNum(1);		
		}
		
		boolean success = this.userEquipDao.addEquip(userEquip);		
    	//掉落包装
    	if(drop!=null){
    		GoodsHelper.addCommonDropUserEquipBO(drop, createUserEquipBO(userEquip));
    	}
    	userEquipMentOperatorLog(userId, userEquipMentId, equipId, "", systemEquip.getLevel(), LogType.EQUIP_ADD, now);
    	userTaskService.updateTaskFinish(userId, 1, new ITaskHitChecker() {			
			@Override
			public Map<String, Object> isHit(int systemTaskId, int taskLibrary,
					Map<String, String> params) {
				Map<String, Object> returnMap = Maps.newConcurrentMap();
				returnMap.put("rt", false);
				returnMap.put("tm", 1);
				
				if (taskLibrary == TaskLibraryType.COLLECT) {
					int toolType = NumberUtils.parseNumber(params.get("toolType"), Integer.class);
					int toolId = NumberUtils.parseNumber(params.get("toolId"), Integer.class);
					Preconditions.checkNotNull(toolType, "toolType不能为空");
					Preconditions.checkNotNull(toolId, "toolId不能为空");
				
					if (toolType == GoodsType.equip.intValue && toolId == systemEquip.getEquipId()) {
						returnMap.put("rt", true);
						return returnMap;
					}					
				}
				
				return returnMap;
			}
		});
    	
    	achievementService.updateAchievementFinish(userId, 1, AchievementConstant.TYPE_EQUIP, new IAchievementChecker() {
			@Override
			public boolean isHit(int achievementId, Map<String, String> params) {
				boolean isHit = false;
				
				if (params.containsKey("color")) {
					int color = Integer.parseInt(params.get("color"));
					if (systemEquip.getQuality() == color)
						isHit = true;
				} else if (params.containsKey("toolType") && params.containsKey("toolId")) {
					int toolType = Integer.parseInt(params.get("toolType"));
					int toolId = Integer.parseInt(params.get("toolId"));
					
					if (toolType == GoodsType.equip.intValue && toolId == equipId) 
						isHit = true;
				}				
				
				return isHit;
			}
    	});
    	
    	return success;
	}
	
	/**
	 * 随机装备主属性
	 * 
	 * @param systemEquip
	 * @return
	 */
	private String randomEquipMainAttr(SystemEquip systemEquip, EquipConstant equipContants) {
		String attr = "";		
		
		// 主属性
		int random = RandomUtils.getRandomNum(1, equipContants.getMainAttrMap().size());
		attr = equipContants.getMainAttrMap().get(random);
		
		if (StringUtils.isBlank(attr))
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有随机到相应的属性值？？");
		
		return attr;
	}
	
	/**
	 * 随机次级属性
	 * 
	 * @param systemEquip
	 * @return
	 */
	private String randomEquipSecondaryAttr(SystemEquip systemEquip, EquipConstant equipContants) {
		String attr = "";
		
		// 绿色装备 0 次级属性, 蓝色 1 紫 2-3
		if (systemEquip.getQuality() == EquipConstant.QUALITY_GREEN 
				&& systemEquip.getQuality() == EquipConstant.QUALITY_WHITE)
			return attr;
				
		if (systemEquip.getQuality() == EquipConstant.QUALITY_BLUE) {
			int random = RandomUtils.getRandomNum(1, equipContants.getSecondaryMap().size());
			attr = equipContants.getSecondaryMap().get(random);
		}
				
		if (systemEquip.getQuality() == EquipConstant.QUALITY_PURPLE) {
			int loop = RandomUtils.getRandomNum(2, 3);
			List<String> list = Lists.newArrayList();
			for (String str : equipContants.getSecondaryMap().values()) {
				list.add(str);
			}
					
			for (int i = 1; i <= loop; i++) {
				int random = RandomUtils.getRandomNum(1, list.size());
				if (attr.length() == 0) {
					attr = list.get(random - 1);
				} else {
					attr = attr + "," + list.get(random - 1);
				}				
						
				list.remove(random - 1);
			}			
		}
		
		return attr;
	}
	
	/**
	 * 卸下装备
	 * 
	 * @param userId
	 * @param userEquipId
	 * @return
	 */
	public EquipAction_unWearEquipRes unWearEquip(String userId, String userEquipId) {
		if (StringUtils.isBlank(userEquipId))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，传入参数为空");
		
		UserEquip userEquip = this.userEquipDao.getUserEquip(userId, userEquipId);
		if (userEquip == null)
			throw new ServiceException(EQUIP_NOT_EXIST, "用户装备不存在,userId[" + userId + "],userEquipId[" + userEquipId + "]");
		
		List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(GoodsType.equip.intValue + "," + userEquip.getEquipId() + ",1");
		this.userService.checkBag(userId, goodsList);		
		String userHeroId = userEquip.getUserHeroId();
		this.userEquipDao.changeEquipUserHeroId(userId, userEquip.getUserEquipId(), "", userEquip.getPos());
		// userEquip = this.userEquipDao.getUserEquip(userId, userEquip.getUserEquipId());
		
		if (StringUtils.isNotBlank(userHeroId))
			this.heroService.updateHeroEffective(userId, userHeroId);
		
		UserEquipBO userEquipBO = createUserEquipBO(userEquip);
		EquipAction_unWearEquipRes res = new EquipAction_unWearEquipRes();
		res.setUserEquipBO(userEquipBO);
		return res;
	}
	
	public List<UserEquipBO> getTeamLeaderEquipList(String userId) {
		UserHeroBO userHeroBO = heroService.getUserTeamLeader(userId);
		return getUserHeroEquipList(userId, userHeroBO.getUserHeroId());
	}
	
	/**
	 * 获取英雄身上的装备列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserEquipBO> getUserHeroEquipList(String userId, String userHeroId) {		
		List<UserEquip> list = this.userEquipDao.getUserEquipList(userId, userHeroId);
		List<UserEquipBO> userEquipBOList = Lists.newArrayList();
		for (UserEquip userEquip : list) {
			UserEquipBO userEquipBO = createUserEquipBO(userEquip);
			userEquipBOList.add(userEquipBO);
		}
		
		return userEquipBOList;
	}
	
	/**
	 * 计算用户的装等
	 * 
	 * @param userHero
	 * @return
	 */
	public int caculateUserHeroEquipLevel(String userId, String userHeroId) {
		//TODO 装等公式：装备等级 * 5 + 装备品质 * 5
		int totalEffective = 0;
		List<UserEquipBO> userEquipList = getUserHeroEquipList(userId, userHeroId);
		for (UserEquipBO userEquipBO : userEquipList) {			
			totalEffective = totalEffective + caculateEquipLevel(userEquipBO.getEquipId());
		}
				
		return totalEffective;
	}
	
	private int caculateEquipLevel(int equipId) {
		SystemEquip systemEquip = this.systemEquipDaoCache.getSystemEquip(equipId);
		return systemEquip.getLevel() * 5 + systemEquip.getQuality() * 5;
	}
	
	/**
	 * 获取系统装备
	 * 
	 * @param equipId
	 * @return
	 */
	public SystemEquip getSystemEquip(int equipId) {
		return this.systemEquipDaoCache.getSystemEquip(equipId);
	}
	
	/**
	 * 获取用户装备
	 * 
	 * @param userId
	 * @param userEquip
	 * @return
	 */
	public UserEquip getUserEquip(String userId, String userEquipId) {
		return this.userEquipDao.getUserEquip(userId, userEquipId);
	}
	
	private static final int HERO_WEARING = 2001;
	/**
	 * 减少装备
	 * 
	 * @param userId
	 * @param equipId
	 * @param goodsUseType
	 * @param drop
	 */
	public boolean reduceEquip(String userId, String userEquipId, int goodsUseType, CommonGoodsBeanBO drop) {
		UserEquip userEquip = this.userEquipDao.getUserEquip(userId, userEquipId);
		if (userEquip == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "数据有误？？获取不到这个数据哦！userEquipId:" + userEquipId);
		
		if (StringUtils.isNotBlank(userEquip.getUserHeroId()))
			throw new ServiceException(HERO_WEARING, "有英雄在穿哦！！");
		
		List<UserGemstone> stoneList = this.gemstoneService.getUserGemstoneListInEquip(userId, userEquipId);
		if (stoneList != null && stoneList.size() > 0) {
			for (UserGemstone stone : stoneList) {
				this.gemstoneService.reduceUserGemstone(userId, stone.getUserGemstoneId(), goodsUseType, drop);
			}
		}
		
		final SystemEquip systemEquip = this.systemEquipDaoCache.getSystemEquip(userEquip.getEquipId());
		boolean success = this.userEquipDao.deleteUserEquip(userId, userEquipId);
		if (success)
			userEquipMentOperatorLog(userId, userEquipId, userEquip.getEquipId(), "", systemEquip.getEquipId(), goodsUseType, new Date());
		
		userTaskService.updateTaskFinish(userId, -1, new ITaskHitChecker() {			
			@Override
			public Map<String, Object> isHit(int systemTaskId, int taskLibrary,
					Map<String, String> params) {
				Map<String, Object> returnMap = Maps.newConcurrentMap();
				returnMap.put("rt", false);
				returnMap.put("tm", -1);
				
				if (taskLibrary == TaskLibraryType.COLLECT) {
					int toolType = NumberUtils.parseNumber(params.get("toolType"), Integer.class);
					int toolId = NumberUtils.parseNumber(params.get("toolId"), Integer.class);
					Preconditions.checkNotNull(toolType, "toolType不能为空");
					Preconditions.checkNotNull(toolId, "toolId不能为空");
				
					if (toolType == GoodsType.equip.intValue && toolId == systemEquip.getEquipId()) {
						returnMap.put("rt", true);
						return returnMap;
					}					
				}
				
				return returnMap;
			}
		});
		
		return success;
	}
	
	/**
	 * 回购装备
	 * 
	 * @param userBuyBackInfo
	 * @param goodsUseType
	 * @param drop
	 * @return
	 */
	public boolean buyBack(UserBuyBackInfo info, int goodsUseType, CommonGoodsBeanBO drop) {
		UserEquip userEquip = this.userEquipDao.getUserEquip(info.getUserId(), info.getUserEquipId());
		if (userEquip != null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "数据有误？为什么还有这个数据！userEquipId:" + info.getUserEquipId());
		
		int equipId = info.getToolId();
		String userId = info.getUserId();
		
		SystemEquip systemEquip = systemEquipDaoCache.getSystemEquip(equipId);
		if(systemEquip == null)
    		throw new ServiceException(SystemConstant.FAIL_CODE, "找不到装备数据,equipId:" + equipId);
    	
    	userEquip = new UserEquip();
		userEquip.setUserEquipId(info.getUserEquipId());
		
		Date now = new Date();
		String[] posArr = systemEquip.getPos().split(",");
		
		// 单手的装备初始化为8号位
		if (posArr.length >= 2) {
			userEquip.setPos(8);
		} else {
			userEquip.setPos(Integer.parseInt(systemEquip.getPos()));
		}
		userEquip.setUserId(userId);
		userEquip.setEquipId(equipId);
		userEquip.setCreatedTime(now);
		userEquip.setUpdatedTime(now);
		
		userEquip.setEquipMainAttr(info.getEquipMainAttr());
		userEquip.setEquipSecondaryAttr(info.getEquipSecondaryAttr());
		
		boolean success = this.userEquipDao.addEquip(userEquip);
		
    	//掉落包装
    	if(drop!=null){
    		GoodsHelper.addCommonDropUserEquipBO(drop, createUserEquipBO(userEquip));
    	}
    	userEquipMentOperatorLog(userId, info.getUserEquipId(), equipId, "", systemEquip.getLevel(), goodsUseType, now);
    	return success;
	}
	
	/**
	 * 获取用户装备占用格子数
	 * 
	 * @param userId
	 * @param type 
	 * @return
	 */
	public int getUserEquipCount(String userId, int type) {
		return this.userEquipDao.getUserEquipCount(userId, type);
	}
	
	/**
	 * 装备操作日志
	 * 
	 * @param userId
	 * @param userEquipMentId
	 * @param equipMentId
	 * @param userHeroId
	 * @param equipMentLevel
	 * @param type
	 * @param operatorTime
	 */
	public void userEquipMentOperatorLog(String userId, String userEquipMentId, int equipMentId, String userHeroId, int equipMentLevel, int type, Date operatorTime) {
		String time = DateUtils.getTimeStr(operatorTime);
		String now = DateUtils.getDateForLog();
		String sql = "INSERT INTO user_equipment_log_" + now + "(USER_ID, USER_EQUIPMENT_ID, EQUIPMENT_ID, USER_HERO_ID, EQUIPMENT_LEVEL, TYPE, OPERATION_TIME) VALUES" + "('" + userId + "','" + userEquipMentId + "',"
				+ equipMentId + ",'" + userHeroId + "'," + equipMentLevel + "," + type + ",'" + time + "')";
		logService.unSynInsertLog(sql);
	}
	
	/**
	 * 这是个未穿戴的装备，并且在背包内
	 * 
	 * @param userId
	 * @param equipId
	 * @return
	 */
	public List<UserEquip> getUnWearEquipList(String userId, int equipId) {
		return this.userEquipDao.getUserEquipList(userId, equipId);
	}
	
	/**
	 * 操作开始，取消结束
	 */
	private final static int OPERATION_START = 1;
	private final static int OPERATION_CANCEL = 2;
	// private final static int OPERATION_END = 3;
	
	// 1 锻造 2 回收
	private final static int TOOL_FORGE = 1;
	private final static int TOOLRECYCLE = 2;
	
	private final static int HAS_NOT_START_FORGE = 2001;
	/**
	 * 锻造
	 * 
	 * @param userId
	 * @param toolType
	 * @param toolId
	 * @return
	 */
	public EquipAction_equipForgeRes toolForge(String userId, int forgeType, int toolType, int toolId, int status, String material, int num) {
		int openLevel = this.configDataDao.getInt(ConfigKey.equip_forge_open_level, 10);
		User user = this.userService.getByUserId(userId);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足");
		
		ToolOperationBean bean = forgeMap.remove(userId);
		if (bean == null && status != OPERATION_START)
			throw new ServiceException(HAS_NOT_START_FORGE, "还没开始锻造装备呀！！userId = " + userId);
		
		if (bean != null && status == OPERATION_START)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "已经开始锻造了丫，为什么又请求开始锻造呢！！userId = " + userId);
		
		EquipAction_equipForgeRes res = new EquipAction_equipForgeRes();
		res.setDrop(new CommonGoodsBeanBO());
		res.setGoodsList(new ArrayList<GoodsBeanBO>());
		res.setUserEquipIdList(new ArrayList<String>());
		// 取消操作，真是犯贱
		if (status == OPERATION_CANCEL) {
			return res;
		}
		
		Date now = new Date();
		// 开始锻造
		if (status == OPERATION_START) {
			SystemToolForge systemToolForge = null;
			if (toolType == GoodsType.equip.intValue) {
				systemToolForge = this.systemEquipForgeDaoCache.getSystemToolForge(TOOL_FORGE, toolType, toolId);
			} else {
				systemToolForge = this.systemEquipForgeDaoCache.getSystemToolForge(forgeType, material);
			}
			
			if (systemToolForge == null)
				throw new ServiceException(SystemConstant.FAIL_CODE, "锻造，数据没有？？toolType = " + toolType + ", toolId = " + toolId
						+ "material=" + material);
		
			if (user.getLevel() < systemToolForge.getNeedLevel())
				throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足...");			
			
			// 判断用户是否有足够的材料
			List<GoodsBeanBO> costList = GoodsHelper.parseDropGoods(systemToolForge.getMaterial());
			if (toolType != GoodsType.equip.intValue) {
				// 如果不是一对一就会有问题！！
				for (GoodsBeanBO bo : costList) {
					bo.setGoodsNum(num);
				}
			}
			
			for (GoodsBeanBO bo : costList) {
				if (bo.getGoodsType() == GoodsType.tool.intValue) {
					UserToolBO userTool = this.toolService.getUserToolBO(userId, bo.getGoodsId());
					if (userTool == null || userTool.getToolNum() < bo.getGoodsNum())
						throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");
				} else if (bo.getGoodsType() == GoodsType.equip.intValue) {
					List<UserEquip> userEquipList = getUnWearEquipList(userId, bo.getGoodsId());
					if (userEquipList == null || userEquipList.size() < bo.getGoodsNum())
						throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "装备不足,toolType=" + bo.getGoodsType() + ",toolId=" + bo.getGoodsId());
				}							
			}		
		
			int cost = this.configDataDao.getInt(ConfigKey.equip_forge_cost, 100);
			int costGold = cost;
			if (toolType == GoodsType.equip.intValue) {
				SystemEquip systemEquip = this.systemEquipDaoCache.getSystemEquip(systemToolForge.getToolId());
				costGold = (systemEquip.getLevel() * 5 + systemEquip.getQuality() * 5 ) * cost;
			} else {
				SystemTool systemTool = this.toolService.getSystemTool(systemToolForge.getToolId());
				costGold = (systemTool.getLevel() * 5 + systemTool.getColor() * 5 ) * cost * num;
			}
			
			if (user.getGold() < costGold)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
			// 获得的道具
			String goods = systemToolForge.getToolType() + "," + systemToolForge.getToolId() + "," + systemToolForge.getNum() * num;
			
			List<GoodsBeanBO> gainList = GoodsHelper.parseDropGoods(goods);		
			bean = new ToolOperationBean();
			bean.setUserId(userId);
			bean.setCreatedTime(now);
			bean.setCostGold(costGold);
			bean.setCostList(costList);
			bean.setGainList(gainList);
			forgeMap.put(userId, bean);
			
			return res;
		}
		
		int cdTime = this.configDataDao.getInt(ConfigKey.equip_stone_operation_cd_time, 2) * 1000;
		if (now.getTime() < bean.getCreatedTime().getTime() + cdTime)
			throw new ServiceException(SystemErrorCode.IN_CD_TIME, "cd时间都未到，你请求个蛋丫！！！userId=" + userId);
		
		// 扣除所需金币
		if (!this.userService.reduceGold(userId, bean.getCostGold(), GoodsUseType.REDUCE_TOOL_FORGE))
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		// 扣除所需材料
		List<GoodsBeanBO> costList = Lists.newArrayList();
		List<String> userEquipIdList = Lists.newArrayList();
		for (GoodsBeanBO bo : bean.getCostList()) {
			if (bo.getGoodsType() == GoodsType.tool.intValue) {
				this.toolService.reduceTool(userId, bo.getGoodsId(), bo.getGoodsNum(), GoodsUseType.REDUCE_TOOL_FORGE);
				costList.add(bo);
			} else if (bo.getGoodsType() == GoodsType.equip.intValue) {
				List<UserEquip> userEquipList = getUnWearEquipList(userId, bo.getGoodsId());
				if (userEquipList == null || userEquipList.size() < bo.getGoodsNum())
					throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "装备不足,toolType=" + bo.getGoodsType() + ",toolId=" + bo.getGoodsId());
								
				int equipNum = 1;
				for (UserEquip equip : userEquipList) {
					this.reduceEquip(userId, equip.getUserEquipId(), GoodsUseType.REDUCE_TOOL_FORGE, null);
					userEquipIdList.add(equip.getUserEquipId());
					
					if (equipNum >= bo.getGoodsNum())
						break;
					equipNum++;
				}
			}							
		}
		
		// 发放装备
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, bean.getGainList(), GoodsUseType.ADD_TOOL_FOEGE);		
		res.setDrop(drop);
		
		if (toolType == GoodsType.equip.intValue)
			this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_EQUIP_FORGE, 1);
		
		user = this.userService.getByUserId(userId);
		res.setUserEquipIdList(userEquipIdList);
		res.setGoodsList(costList);
		res.setMoney(user.getMoney());
		res.setGold(user.getGold());
		return res;
	}
	
	private final static int HAS_NOT_THIS_EQUIP = 2002;
	private final static int HAS_NOT_START_RECYCLE = 2003;
	/**
	 * 装备回收
	 * 
	 * @param userId
	 * @param userEquipId
	 * @return
	 */
	public EquipAction_equipRecycleRes equipRecycle(String userId, int toolType, int toolId, String userEquipId, int status) {
		int openLevel = this.configDataDao.getInt(ConfigKey.equip_forge_open_level, 10);
		User user = this.userService.getByUserId(userId);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足");
		
		ToolOperationBean bean = recycleMap.remove(userId);
		if (bean == null && status != OPERATION_START)
			throw new ServiceException(HAS_NOT_START_RECYCLE, "还没开始回收装备呀！！userId = " + userId);
		
		if (bean != null && status == OPERATION_START)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "已经开始回收了丫，为什么又请求开始回收呢！！userId = " + userId);
		
		EquipAction_equipRecycleRes res = new EquipAction_equipRecycleRes();
		res.setDrop(new CommonGoodsBeanBO());
		res.setUserEquipId("");
		// 取消操作，真是犯贱
		if (status == OPERATION_CANCEL) {
			return res;
		}
		
		// 开始分解装备
		if (status == OPERATION_START) {
			SystemToolForge systemToolForge = null;
			
			// 装备	
			UserEquip userEquip = this.userEquipDao.getUserEquip(userId, userEquipId);	
			if (userEquip == null)
				throw new ServiceException(HAS_NOT_THIS_EQUIP, "数据有误？？用户没有这个装备哦！userEquipId:" + userEquipId);
				
			if (StringUtils.isNotBlank(userEquip.getUserHeroId()))
				throw new ServiceException(HERO_WEARING, "有英雄在穿哦！！");
			
			systemToolForge = this.systemEquipForgeDaoCache.getSystemToolForge(TOOLRECYCLE, toolType, userEquip.getEquipId());		
			if (systemToolForge == null)
				throw new ServiceException(SystemConstant.FAIL_CODE, "回收，数据没有？？toolType = " + toolType + ", toolId = " + userEquip.getEquipId());
			
			if (user.getLevel() < systemToolForge.getNeedLevel())
				throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足...");
			
			int cost = this.configDataDao.getInt(ConfigKey.equip_recycle_cost, 100);
			SystemEquip systemEquip = this.systemEquipDaoCache.getSystemEquip(systemToolForge.getToolId());
			int costGold = (systemEquip.getLevel() * 5 + systemEquip.getQuality() * 5 ) * cost;
				
			List<GoodsBeanBO> gainList = GoodsHelper.parseDropGoods(systemToolForge.getMaterial());
			this.userService.checkBag(userId, gainList);		
			
			if (user.getGold() < costGold)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
			
			bean = new ToolOperationBean();
			bean.setCostGold(costGold);
			bean.setGainList(gainList);
			bean.setUserId(userId);
			bean.setCreatedTime(new Date());
			recycleMap.put(userId, bean);
			
			return res;
		}
		
		int cdTime = this.configDataDao.getInt(ConfigKey.equip_stone_operation_cd_time, 2) * 1000;
		Date now = new Date();
		if (now.getTime() < bean.getCreatedTime().getTime() + cdTime)
			throw new ServiceException(SystemErrorCode.IN_CD_TIME, "cd时间都未到，你请求个蛋丫！！！userId=" + userId);
		
		if (!this.userService.reduceGold(userId, bean.getCostGold(), GoodsUseType.REDUCE_TOOL_FORGE))
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		// 扣除装备
		reduceEquip(userId, userEquipId, GoodsUseType.REDUCE_TOOL_FORGE, null);
		
		// 发放道具
		CommonGoodsBeanBO drop = this.goodsDealService.sendGoods(userId, bean.getGainList(), GoodsUseType.ADD_TOOL_RECYCLE);
		user = this.userService.getByUserId(userId);
		res.setDrop(drop);
		res.setUserEquipId(userEquipId);
		res.setGold(user.getGold());
		return res;
	}
	
	private final static int CAN_NOT_FILL_IN = 2001;
	private final static int NOT_ENOUGH_HOLENUM = 2002;
	/**
	 * 装备镶嵌宝石\换宝石、取下宝石
	 * 
	 * @param userId
	 * @param userEquipId
	 * @param userGemstoneId
	 * @param pos
	 * @return
	 */
	public List<UserGemstoneBO> equipFillInGemstone(String userId, String userEquipId, String userGemstoneId, int pos) {
		UserEquip userEquip = this.userEquipDao.getUserEquip(userId, userEquipId);
		if (userEquip == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "用户装备不存在，userEquipId=" + userEquipId);
		
		UserGemstone userGemstone = this.gemstoneService.getUserGemstone(userId, userGemstoneId);
		if (userGemstone == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "用户的宝石不存在，userGemstoneId=" + userGemstoneId);
		
		List<UserGemstoneBO> list = Lists.newArrayList();
		// 同一个装备同一个位置，还操作个毛丫
		if (userGemstone.getUserEquipId() != null && userGemstone.getUserEquipId().equals(userEquipId) && userGemstone.getPos() == pos)
			return list;
		
		if (userEquip.getHoleNum() <= 0)
			throw new ServiceException(CAN_NOT_FILL_IN, "该装备不能镶嵌的丫");		
		
		// 镶嵌宝石
		if (pos > 0) {
			List<UserGemstone> gemstoneList = this.gemstoneService.getUserGemstoneListInEquip(userId, userEquipId);
			if (pos > userEquip.getHoleNum())
				throw new ServiceException(NOT_ENOUGH_HOLENUM, "该装备没有可镶嵌的位置了,pos = " + pos + ", holeNum=" + userEquip.getHoleNum());
			
			UserGemstone targetUserGemstone = null;
			for (UserGemstone stone : gemstoneList) {
				if (stone.getPos() == pos) {
					targetUserGemstone = stone;
					break;
				}
			}
			
			if (targetUserGemstone != null ) {
				// 同一个装备内，两个宝石位置互换
				if (userGemstone.getUserEquipId() != null && userGemstone.getUserEquipId().equals(targetUserGemstone.getUserEquipId()) && userGemstone.getPos() > 0) {
					UserGemstoneBO userGemstoneBO = gemstoneService.fillInEquip(userId, targetUserGemstone.getUserGemstoneId(), userEquipId, userGemstone.getPos());
					list.add(userGemstoneBO);
				} else {
					// 将原来的宝石取下
					UserGemstoneBO userGemstoneBO = gemstoneService.fillInEquip(userId, targetUserGemstone.getUserGemstoneId(), "", 0);
					list.add(userGemstoneBO);
				}			
			}			
			
			// 镶嵌到该位置
			UserGemstoneBO userGemstoneBO = gemstoneService.fillInEquip(userId, userGemstoneId, userEquipId, pos);
			list.add(userGemstoneBO);
		} else {
			List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(GoodsType.Gemstone.intValue + "," + userGemstone.getGemstoneId() + ",1");
			this.userService.checkBag(userId, goodsList);			
			// 取下宝石
			UserGemstoneBO userGemstoneBO = gemstoneService.fillInEquip(userId, userGemstoneId, "", pos);
			list.add(userGemstoneBO);
		}
		
		SystemEquip systemEquip = this.systemEquipDaoCache.getSystemEquip(userEquip.getEquipId());
		userEquipMentOperatorLog(userId, userEquipId, userEquip.getEquipId(), userEquip.getUserHeroId(), systemEquip.getLevel(), LogType.EQUIP_ADD, new Date());
		return list;
	}
	
	private static final int HAS_NOT_START_MAGIC = 2001;
	/**
	 * 装备附魔
	 * 
	 * @param userId
	 * @param userEquipId
	 * @param reelId
	 * @return
	 */
	public EquipAction_equipMagicRes equipMagic(String userId, String userEquipId, int reelId, int status) {
		int openLevel = this.configDataDao.getInt(ConfigKey.equip_magic_open_level, 25);
		User user = this.userService.getByUserId(userId);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足");
		
		ToolOperationBean bean = magicMap.remove(userId);
		if (bean == null && status != OPERATION_START)
			throw new ServiceException(HAS_NOT_START_MAGIC, "还没开始装备附魔呀！！userId = " + userId);
		
		if (bean != null && status == OPERATION_START)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "已经开始附魔了丫，为什么又请求开始附魔呢！！userId = " + userId + ",status=" + status);
		
		EquipAction_equipMagicRes res = new EquipAction_equipMagicRes();
		res.setGoodsList(new ArrayList<GoodsBeanBO>());
		res.setUserEquipBO(new UserEquipBO());
		// 取消操作，真是犯贱
		if (status == OPERATION_CANCEL) {
			return res;
		}
		
		UserEquip userEquip = this.userEquipDao.getUserEquip(userId, userEquipId);
		if (userEquip == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "用户没有该装备，参数传错了？userEquipId=" + userEquipId);

		SystemEquip systemEquip = this.systemEquipDaoCache.getSystemEquip(userEquip.getEquipId());
		SystemTool systemTool = this.toolService.getSystemTool(reelId);
		SystemMagicReelAttr reelAttr = this.toolService.getSystemMagicReelAttr(reelId);
		if (reelAttr == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "该卷轴没有对应的属性？？reelId=" + reelId);

		Date now = new Date();
		// 开始附魔
		if (status == OPERATION_START) {
			UserToolBO userToolBO = toolService.getUserToolBO(userId, reelId);
			if (userToolBO == null || userToolBO.getToolNum() <= 0)
				throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");
			
			SystemEquipMagic systemEquipMagic = this.systemEquipMagicDaoCache.getSystemEquipMagic(reelId);
			if (systemEquipMagic == null)
				throw new ServiceException(SystemConstant.FAIL_CODE, "该卷轴没有对应的附魔配方？？reelId=" + reelId);
			
			List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(systemEquipMagic.getMaterial());
			for (GoodsBeanBO bo : goodsList) {
				UserToolBO userTool = this.toolService.getUserToolBO(userId, bo.getGoodsId());
				if (userTool == null || userTool.getToolNum() < bo.getGoodsNum())
					throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");			
			}		
			
			// TODO 暂定的公式
			int cost = this.configDataDao.getInt(ConfigKey.equip_magic_cost, 100);
			int costGold = systemTool.getLevel() * systemTool.getColor() * systemEquip.getLevel()  * cost;
			if (user.getGold() < costGold)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
			
			// 扣除所需材料  也要扣除掉附魔卷轴
			GoodsBeanBO reel = new GoodsBeanBO();
			reel.setGoodsId(reelId);
			reel.setGoodsNum(1);
			reel.setGoodsType(GoodsType.tool.intValue);			
			goodsList.add(reel);
			
			bean = new ToolOperationBean();
			bean.setCostList(goodsList);
			bean.setCostGold(costGold);
			bean.setCreatedTime(now);
			bean.setUserId(userId);
			magicMap.put(userId, bean);
			
			return res;
		}
		
		int cdTime = this.configDataDao.getInt(ConfigKey.equip_stone_operation_cd_time, 2) * 1000;
		if (now.getTime() < bean.getCreatedTime().getTime() + cdTime)
			throw new ServiceException(SystemErrorCode.IN_CD_TIME, "cd时间都未到，你请求个蛋丫！！！userId=" + userId);
		
		if (!this.userService.reduceGold(userId, bean.getCostGold(), GoodsUseType.REDUCE_EQUIP_MAGIC))
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		// 扣除附魔所需的材料
		goodsDealService.reduceGoods(userId, bean.getCostList(), GoodsUseType.REDUCE_EQUIP_MAGIC);		
		// 开始附魔属性
		int attrNum = systemTool.getColor() - 1;
		
		EquipConstant equipContants = EquipConstant.instance();
		List<Attribute> list = Lists.newArrayList();
		for (Integer key : equipContants.getMagicAttrMap().keySet()) {
			Attribute attr = new Attribute();
			attr.setAttr(equipContants.getMagicAttrMap().get(key));
			attr.setValue(key);
			list.add(attr);
		}
		
		List<Attribute> attrList = Lists.newArrayList();
		for (int i = 1; i <= attrNum; i++) {
			int random = RandomUtils.getRandomNum(1, list.size());
			Attribute randomAttr = list.get(random - 1);
						
			attrList.add(getEquipMagicAttr(reelAttr, randomAttr));
			list.remove(random - 1);
		}
		
		String equipMagicAttr = JSON.toJSONString(attrList);
		this.userEquipDao.equipMagic(userId, userEquipId, equipMagicAttr);
		
		userEquip = this.userEquipDao.getUserEquip(userId, userEquipId);
		UserEquipBO bo = createUserEquipBO(userEquip);		
		userEquipMentOperatorLog(userId, userEquipId, userEquip.getEquipId(), userEquip.getUserHeroId(), systemEquip.getLevel(), LogType.EQUIP_ADD, new Date());		
		
		this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_EQUIP_MAGIC, 1);
		user = this.userService.getByUserId(userId);
		res.setGold(user.getGold());
		res.setUserEquipBO(bo);
		res.setGoodsList(bean.getCostList());
		return res;
	}
	
	private Attribute getEquipMagicAttr(SystemMagicReelAttr reelAttr, Attribute randomAttr) {
		Attribute attribute = new Attribute();
		attribute.setAttr(randomAttr.getAttr());
		
		int value = 0;
		switch (randomAttr.getValue()) {
			case 1: {
				value = RandomUtils.getRandomNum(reelAttr.getAttackPowerMin(), reelAttr.getAttackPowerMax());
				break;
			}
			case 2: {
				value = RandomUtils.getRandomNum(reelAttr.getMagicPowerMin(), reelAttr.getMagicPowerMax());
				break;
			}
			case 3: {
				value = RandomUtils.getRandomNum(reelAttr.getPhyCritMin(), reelAttr.getPhyCritMax());
				break;
			}
			case 4: {
				value = RandomUtils.getRandomNum(reelAttr.getDodgeMin(), reelAttr.getDodgeMax());
				break;
			}
			case 5: {
				value = RandomUtils.getRandomNum(reelAttr.getSpeedUpMin(), reelAttr.getSpeedUpMax());
				break;
			}
			case 6: {
				value = RandomUtils.getRandomNum(reelAttr.getHpMin(), reelAttr.getHpMax());
				break;
			}
			default :
				break;
		}		
		
		attribute.setValue(value);
		return attribute;
	}

	/**
	 * 仓库存入，取出装备
	 * 
	 * @param userId
	 * @param userEquipId
	 * @param storehouseNum
	 * @return
	 */
	public boolean storehouseInOrOut(String userId, String userEquipId, int storehouseNum) {
		return this.userEquipDao.storehouseInOrOut(userId, userEquipId, storehouseNum);
	}
	
	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		if(handlerType.equals(ModuleEventConstant.USER_LOGINOUT_EVENT)){
			String userId = baseModuleEvent.getStringValue("userId", "");
			forgeMap.remove(userId);
			magicMap.remove(userId);
			recycleMap.remove(userId);			
			// LogSystem.warn("userId[" + userId + "]，登出游戏，装备锻造中移出该用户");
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
	
}
