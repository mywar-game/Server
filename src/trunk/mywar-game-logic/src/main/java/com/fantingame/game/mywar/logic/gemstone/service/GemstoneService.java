package com.fantingame.game.mywar.logic.gemstone.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.NumberUtils;

import com.alibaba.fastjson.JSON;
import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.IDGenerator;
import com.fantingame.game.common.utils.RandomUtils;
import com.fantingame.game.log.service.LogService;
import com.fantingame.game.msgbody.client.gemstone.GemstoneAction_gemstoneForgeRes;
import com.fantingame.game.msgbody.client.gemstone.GemstoneAction_gemstoneResolveRes;
import com.fantingame.game.msgbody.client.gemstone.GemstoneAction_gemstoneUpgradeRes;
import com.fantingame.game.msgbody.client.gemstone.UserGemstoneBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
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
import com.fantingame.game.mywar.logic.gemstone.dao.UserGemstoneDao;
import com.fantingame.game.mywar.logic.gemstone.dao.cache.SystemGemstoneAttrDaoCache;
import com.fantingame.game.mywar.logic.gemstone.dao.cache.SystemGemstoneDaoCache;
import com.fantingame.game.mywar.logic.gemstone.dao.cache.SystemGemstoneForgeDaoCache;
import com.fantingame.game.mywar.logic.gemstone.dao.cache.SystemGemstoneUpgradeDaoCache;
import com.fantingame.game.mywar.logic.gemstone.model.Attribute;
import com.fantingame.game.mywar.logic.gemstone.model.SystemGemstone;
import com.fantingame.game.mywar.logic.gemstone.model.SystemGemstoneAttr;
import com.fantingame.game.mywar.logic.gemstone.model.SystemGemstoneForge;
import com.fantingame.game.mywar.logic.gemstone.model.SystemGemstoneUpgrade;
import com.fantingame.game.mywar.logic.gemstone.model.UserGemstone;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.hero.constant.LogType;
import com.fantingame.game.mywar.logic.task.check.ITaskHitChecker;
import com.fantingame.game.mywar.logic.task.constant.TaskLibraryType;
import com.fantingame.game.mywar.logic.task.service.UserTaskService;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class GemstoneService implements ModuleEventHandler{

	@Autowired
	private UserGemstoneDao userGemstoneDao;
	@Autowired
	private SystemGemstoneDaoCache systemGemstoneDaoCache;
	@Autowired
	private SystemGemstoneAttrDaoCache systemGemstoneAttrDaoCache;
	@Autowired
	private SystemGemstoneForgeDaoCache systemGemstoneForgeDaoCache;
	@Autowired
	private UserService userService;
	@Autowired
	private ToolService toolService;
	@Autowired
	private GoodsDealService goodsDealService;
	@Autowired
	private SystemGemstoneUpgradeDaoCache systemGemstoneUpgradeDaoCache;
	@Autowired
	private LogService logService;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private AchievementService achievementService;
	@Autowired
	private ActivityService activityService;
	
	/**
	 * 道具合成
	 */
	private Map<String, ToolOperationBean> forgeMap = Maps.newConcurrentMap();	
	/**
	 * 宝石升级
	 */
	private Map<String, ToolOperationBean> upgradeMap = Maps.newConcurrentMap();
	/**
	 * 分解宝石
	 */
	private Map<String, ToolOperationBean> resolveMap = Maps.newConcurrentMap();
	
	/**
	 * 添加宝石
	 * 
	 * @param userId
	 * @param gemstoneId
	 * @param isFristAdd
	 * @param goodsUseType
	 * @param drop
	 * @return
	 */
	public boolean addGemstone(String userId, final int gemstoneId, boolean isFristAdd, int goodsUseType, CommonGoodsBeanBO drop) {
		final SystemGemstone systemGemstone = this.systemGemstoneDaoCache.getSystemGemstone(gemstoneId);
		if (systemGemstone == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "找不到宝石数据,gemstoneId=" + gemstoneId);
		
		UserGemstone userGemstone = new UserGemstone();
		String userGemstoneId = IDGenerator.getID();
		userGemstone.setUserGemstoneId(userGemstoneId);
		
		Date now = new Date();
		userGemstone.setUserId(userId);
		userGemstone.setCreatedTime(now);
		userGemstone.setUpdatedTime(now);
		userGemstone.setGemstoneId(gemstoneId);
		userGemstone.setStorehouseNum(0);
		
		List<SystemGemstoneAttr> attrList = getSystemGemstoneAttrList(systemGemstone);
		if (attrList == null || attrList.size() == 0)
			throw new ServiceException(SystemConstant.FAIL_CODE, "找不到宝石属性数据,gemstoneId=" + gemstoneId);
		
		Map<String, Attribute> gemstoneAttrMap = Maps.newConcurrentMap();
		for (SystemGemstoneAttr attr : attrList) {
			int attrValue = RandomUtils.getRandomNum(attr.getLowerNum(), attr.getUpperNum());			
			if (gemstoneAttrMap.containsKey(attr.getAttr())) {
				Attribute gemstoneAttr = gemstoneAttrMap.get(attr.getAttr());
				gemstoneAttr.setValue(gemstoneAttr.getValue() + attrValue);
			} else {
				Attribute gemstoneAttr = new Attribute();
				gemstoneAttr.setAttr(attr.getAttr());
				gemstoneAttr.setValue(attrValue);
				
				gemstoneAttrMap.put(attr.getAttr(), gemstoneAttr);
			}
		}
		
		userGemstone.setGemstoneAttr(JSON.toJSONString(gemstoneAttrMap.values()));
		userGemstoneOperatorLog(userId, userGemstoneId, gemstoneId, "", systemGemstone.getLevel(), goodsUseType, now);
		
		if (drop != null) 
			GoodsHelper.addCommonDropUserGemstoneBO(drop, createUserGemstoneBO(userGemstone));
		
		boolean success = this.userGemstoneDao.addUserGemstone(userGemstone);		
		userTaskService.updateTaskFinish(userId, 1, new ITaskHitChecker() {
			@Override
			public Map<String, Object> isHit(int systemTaskId, int taskLibrary, Map<String, String> params) {
				Map<String, Object> returnMap = Maps.newConcurrentMap();
				returnMap.put("rt", false);
				returnMap.put("tm", 1);
				
				if (taskLibrary == TaskLibraryType.COLLECT) {
					int toolType = NumberUtils.parseNumber(params.get("toolType"), Integer.class);
					int toolId = NumberUtils.parseNumber(params.get("toolId"), Integer.class);
					Preconditions.checkNotNull(toolType, "toolType不能为空");
					Preconditions.checkNotNull(toolId, "toolId不能为空");
				
					if (toolType == GoodsType.Gemstone.intValue && toolId == systemGemstone.getGemstoneId()) {
						returnMap.put("rt", true);
						return returnMap;
					}					
				}
				return returnMap;
			}
		});
		
		// 成就
        achievementService.updateAchievementFinish(userId, 1, AchievementConstant.TYPE_LIFE, new IAchievementChecker() {
			@Override
			public boolean isHit(int achievementId, Map<String, String> params) {
				boolean isHit = false;
				if (params.containsKey("toolType") && params.containsKey("toolId")) {
					int toolType = Integer.parseInt(params.get("toolType"));
					int achievementToolId = Integer.parseInt(params.get("toolId"));
					
					if (toolType == GoodsType.Gemstone.intValue && achievementToolId == gemstoneId)
						isHit = true;
				}
				
				return isHit;
			}            	
        });
		
		return success;
	}
	
	private List<SystemGemstoneAttr> getSystemGemstoneAttrList(SystemGemstone systemGemstone) {
		List<SystemGemstone> stoneList = this.systemGemstoneDaoCache.getSystemGemstoneList(systemGemstone.getModelId(), systemGemstone.getLevel());
		List<SystemGemstoneAttr> allAttrList = Lists.newArrayList();
		for (SystemGemstone gemstone : stoneList) {
			List<SystemGemstoneAttr> attrList = this.systemGemstoneAttrDaoCache.getSystemGemstoneAttrList(gemstone.getGemstoneId());
			allAttrList.addAll(attrList);
		}
		
		return allAttrList;
	}
	
	/**
	 * 操作开始，取消结束
	 */
	private final static int OPERATION_START = 1;
	private final static int OPERATION_CANCEL = 2;
	private final static int OPERATION_END = 3;
	
	private final static int GEMSTONE_FORGE = 1;
	private final static int GEMSTONE_RESOLVE = 2;
	
	private final static int HAS_NOT_START_FORGE = 2001;
	/**
	 * 宝石合成
	 * 
	 * @param userId
	 * @param gemstoneId
	 * @return
	 */
	public GemstoneAction_gemstoneForgeRes gemstoneForge(String userId, int forgeType, int toolId, int toolType, int status, String material, int num) {
		int openLevel = this.configDataDao.getInt(ConfigKey.gemstone_open_level, 30);
		User user = this.userService.getByUserId(userId);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足");
		
		if (status != OPERATION_START && status != OPERATION_CANCEL && status != OPERATION_END)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误 ,status = " + status);
		
		ToolOperationBean bean = forgeMap.remove(userId);
		if (bean == null && status != OPERATION_START)
			throw new ServiceException(HAS_NOT_START_FORGE, "还没开始宝石合成呀！！userId = " + userId);
		
		if (bean != null && status == OPERATION_START)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "已经开始合成了丫，为什么又请求开始合成呢！！userId = " + userId);
		
		// 取消操作，真是犯贱
		GemstoneAction_gemstoneForgeRes res = new GemstoneAction_gemstoneForgeRes();
		res.setDrop(new CommonGoodsBeanBO());
		res.setGoodsList(new ArrayList<GoodsBeanBO>());
		res.setUserGemstoneIdList(new ArrayList<String>());
		res.setStatus(status);
		if (status == OPERATION_CANCEL) {
			return res;
		}
		
		if (status == OPERATION_START) {
			SystemGemstoneForge systemGemstoneForge = null;
			if (toolType == GoodsType.Gemstone.intValue) {
				systemGemstoneForge = this.systemGemstoneForgeDaoCache.getSystemGemstoneForge(GEMSTONE_FORGE, toolType, toolId);
			} else {
				systemGemstoneForge = this.systemGemstoneForgeDaoCache.getSystemGemstoneForge(forgeType, material);
			}
			
			if (systemGemstoneForge == null)
				throw new ServiceException(SystemConstant.FAIL_CODE, "宝石合成，没有该数据，gemstoneId=" + toolId 
						+ ",toolType=" + toolType + ", material = " + material);
		
			if (user.getLevel() < systemGemstoneForge.getNeedLevel())
				throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足...");
			
			// 判断用户是否有足够的材料
			List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(systemGemstoneForge.getMaterial());
			if (toolType != GoodsType.Gemstone.intValue) {
				// 如果不是一对一就会有问题！！
				for (GoodsBeanBO bo : goodsList) {
					bo.setGoodsNum(num);
				}
			}
			
			for (GoodsBeanBO bo : goodsList) {
				if (bo.getGoodsType() == GoodsType.tool.intValue) {			
					UserToolBO userTool = this.toolService.getUserToolBO(userId, bo.getGoodsId());
					if (userTool == null || userTool.getToolNum() < bo.getGoodsNum())
						throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");	
				} else if (bo.getGoodsType() == GoodsType.Gemstone.intValue) {
					List<UserGemstone> list = this.userGemstoneDao.getUserGemstoneList(userId, bo.getGoodsId());
					if (list == null || list.size() < bo.getGoodsNum())
						throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");	
				}
			}	
			
			// TODO 公式要修改的
			int cost = configDataDao.getInt(ConfigKey.gemstone_forge_cost, 100);
			int costGold = cost;
			
			if (toolType == GoodsType.Gemstone.intValue) {
				SystemGemstone systemGemstone = this.systemGemstoneDaoCache.getSystemGemstone(toolId);
				costGold = (systemGemstone.getQuality() * 5 +  systemGemstone.getLevel() * 5) * cost;
			} else {
				SystemTool systemTool = this.toolService.getSystemTool(toolId);
				costGold = (systemTool.getColor() * 5 +  systemTool.getLevel() * 5) * cost * num;
			}
			
			if (user.getGold() < costGold)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
			
			String goods = toolType + "," + toolId + "," + systemGemstoneForge.getNum() * num;
			List<GoodsBeanBO> gainList = GoodsHelper.parseDropGoods(goods);
			
			bean = new ToolOperationBean();
			bean.setUserId(userId);
			bean.setCostGold(costGold);
			bean.setCostList(goodsList);
			bean.setGainList(gainList);
			bean.setCreatedTime(new Date());
			forgeMap.put(userId, bean);
			
			return res;
		}
		
		// 时间校验
		int cdTime = this.configDataDao.getInt(ConfigKey.equip_stone_operation_cd_time, 2) * 1000;
		Date now = new Date();
		if (now.getTime() < bean.getCreatedTime().getTime() + cdTime)
			throw new ServiceException(SystemErrorCode.IN_CD_TIME, "cd时间都未到，你请求个蛋丫！！！userId=" + userId);
		
		if (!this.userService.reduceGold(userId, bean.getCostGold(), GoodsUseType.REDUCE_GEMSTONE_FOEGE))
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		List<String> userGemstoneIdList = Lists.newArrayList();
		// 扣除所需材料
		for (GoodsBeanBO bo : bean.getCostList()) {
			if (bo.getGoodsType() == GoodsType.tool.intValue) {			
				this.toolService.reduceTool(userId, bo.getGoodsId(), bo.getGoodsNum(), GoodsUseType.REDUCE_GEMSTONE_FOEGE);	
			} else if (bo.getGoodsType() == GoodsType.Gemstone.intValue) {
				List<UserGemstone> list = this.userGemstoneDao.getUserGemstoneList(userId, bo.getGoodsId());
				for (int i = 0; i < bo.getGoodsNum(); i++) {
					UserGemstone stone = list.get(i);
					reduceUserGemstone(userId, stone.getUserGemstoneId(), GoodsUseType.REDUCE_GEMSTONE_FOEGE, null);
					userGemstoneIdList.add(stone.getUserGemstoneId());
				}				
			}
		}
				
		// 发放道具
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, bean.getGainList(), GoodsUseType.ADD_GEMSTONE_FOEGE);		
		
		user = this.userService.getByUserId(userId);		
		res.setDrop(drop);
		res.setUserGemstoneIdList(userGemstoneIdList);
		res.setGoodsList(bean.getCostList());
		res.setMoney(user.getMoney());
		res.setGold(user.getGold());
		return res;
	}
	
	private final static int FILL_IN_EQUIP = 2001; 
	private final static int HAS_NOT_START_RESOLVE = 2002;
	/**
	 * 宝石分解
	 * 
	 * @param userId
	 * @param userGemstoneId
	 * @return
	 */
	public GemstoneAction_gemstoneResolveRes gemstoneResolve(String userId, List<String> userGemstoneIdList, int status) {
		int openLevel = this.configDataDao.getInt(ConfigKey.gemstone_open_level, 30);
		User user = this.userService.getByUserId(userId);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足");
		
		ToolOperationBean bean = resolveMap.remove(userId);
		if (bean == null && status != OPERATION_START)
			throw new ServiceException(HAS_NOT_START_RESOLVE, "还没开始宝石分解呀！！userId = " + userId);
		
		if (bean != null && status == OPERATION_START)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "已经开始分解了丫，为什么又请求开始分解呢！！userId = " + userId);
		
		// 取消操作，真是犯贱
		GemstoneAction_gemstoneResolveRes res = new GemstoneAction_gemstoneResolveRes();
		res.setDrop(new CommonGoodsBeanBO());
		res.setUserGemstoneIdList(new ArrayList<String>());
		res.setStatus(status);
		if (status == OPERATION_CANCEL) {
			return res;
		}		
		
		if (userGemstoneIdList == null || userGemstoneIdList.size() <= 0)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误,userGemstoneIdList = null");
		
		// 开始分解
		Date now = new Date();
		if (status == OPERATION_START) {
			List<GoodsBeanBO> allList = Lists.newArrayList();
			int cost = this.configDataDao.getInt(ConfigKey.gemstone_resolve_cost, 100);
			int costGold = 0;
			
			Map<String, SystemGemstoneForge> gemstoneForgeMap = Maps.newConcurrentMap();
			Map<String, String> map = Maps.newConcurrentMap();
			for (String userGemstoneId : userGemstoneIdList) {
				if (map.containsKey(userGemstoneId))
					throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，传入相同的用户宝石id");
				
				UserGemstone userGemstone = this.userGemstoneDao.getUserGemstone(userId, userGemstoneId);			
				if (userGemstone == null)
					throw new ServiceException(SystemConstant.FAIL_CODE, "该用户没有这个宝石，userGemstoneId=" + userGemstoneId);
				
				if (StringUtils.isNotBlank(userGemstone.getUserEquipId()))
					throw new ServiceException(FILL_IN_EQUIP, "该宝石正在被镶嵌，不能分解，userEquipId=" + userGemstone.getUserEquipId());
				
				SystemGemstoneForge systemGemstoneForge = this.systemGemstoneForgeDaoCache.getSystemGemstoneForge(GEMSTONE_RESOLVE, GoodsType.Gemstone.intValue, userGemstone.getGemstoneId());
				if (systemGemstoneForge == null)
					throw new ServiceException(SystemConstant.FAIL_CODE, "宝石分解，没有该数据，gemstoneId=" + userGemstone.getGemstoneId());
				
				if (user.getLevel() < systemGemstoneForge.getNeedLevel())
					throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足...");
				
				List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(systemGemstoneForge.getMaterial());
				allList.addAll(goodsList);
				
				// TODO 公式又是TM的待定
				SystemGemstone systemGemstone = this.systemGemstoneDaoCache.getSystemGemstone(userGemstone.getGemstoneId());
				costGold = costGold + (systemGemstone.getLevel() * 5 + systemGemstone.getQuality() * 5) * cost;
				gemstoneForgeMap.put(userGemstoneId, systemGemstoneForge);
				map.put(userGemstoneId, userGemstoneId);
			}		
			this.userService.checkBag(userId, allList);
			
			if (user.getGold() < costGold)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
			bean = new ToolOperationBean();
			bean.setCostGold(costGold);
			bean.setCostList(allList);
			bean.setCreatedTime(now);
			bean.setUserId(userId);
			bean.setGemstoneForgeMap(gemstoneForgeMap);
			resolveMap.put(userId, bean);
		
			return res;
		} 
		
		int cdTime = this.configDataDao.getInt(ConfigKey.equip_stone_operation_cd_time, 2) * 1000;
		if (now.getTime() < bean.getCreatedTime().getTime() + cdTime)
			throw new ServiceException(SystemErrorCode.IN_CD_TIME, "cd时间都未到，你请求个蛋丫！！！userId=" + userId);
		
		if (!this.userService.reduceGold(userId, bean.getCostGold(), GoodsUseType.REDUCE_GEMSTONE_RESOLVE))
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		Map<String, SystemGemstoneForge> gemstoneForgeMap = bean.getGemstoneForgeMap();
		CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
		for (String userGemstoneId : userGemstoneIdList) {
			// 扣除宝石
			reduceUserGemstone(userId, userGemstoneId, GoodsUseType.REDUCE_GEMSTONE_RESOLVE, null);
			
			// 发放道具
			SystemGemstoneForge systemGemstoneForge = gemstoneForgeMap.get(userGemstoneId);
			CommonGoodsBeanBO goodsBeanBO = goodsDealService.sendGoods(userId, GoodsHelper.parseDropGoods(systemGemstoneForge.getMaterial()), GoodsUseType.ADD_GEMSTONE_RESOLVE);
			
			if (drop.getGoodsList() == null) {
				drop.setGoodsList(goodsBeanBO.getGoodsList());
			} else {
				drop.getGoodsList().addAll(goodsBeanBO.getGoodsList());
			}
			
			if (drop.getGemstoneList() == null) {
				drop.setGemstoneList(goodsBeanBO.getGemstoneList());
			} else {
				drop.getGemstoneList().addAll(goodsBeanBO.getGemstoneList());
			}
		}
		
		res.setDrop(drop);		
		res.setUserGemstoneIdList(userGemstoneIdList);
		user = this.userService.getByUserId(userId);
		res.setGold(user.getGold());
		res.setMoney(user.getMoney());
		return res;
	} 
	
	/**
	 * 删除宝石
	 * 
	 * @param userId
	 * @param userGemstoneId
	 * @param goodsUseType
	 * @param drop
	 * @return
	 */
	public boolean reduceUserGemstone(String userId, String userGemstoneId, int goodsUseType, CommonGoodsBeanBO drop) {
		UserGemstone userGemstone = this.userGemstoneDao.getUserGemstone(userId, userGemstoneId);
		if (userGemstone == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "该用户没有这个宝石，userGemstoneId=" + userGemstoneId);
		
//		if (StringUtils.isNotBlank(userGemstone.getUserEquipId()))
//			throw new ServiceException(FILL_IN_EQUIP, "该宝石正在被镶嵌，不能删除，userEquipId=" + userGemstone.getUserEquipId());
		
		final SystemGemstone systemGemstone = this.systemGemstoneDaoCache.getSystemGemstone(userGemstone.getGemstoneId());
		userGemstoneOperatorLog(userId, userGemstoneId, userGemstone.getGemstoneId(), userGemstone.getUserEquipId(), systemGemstone.getLevel(), goodsUseType, new Date());
		boolean success = this.userGemstoneDao.deleteUserGemstone(userId, userGemstoneId);	
		
		userTaskService.updateTaskFinish(userId, 1, new ITaskHitChecker() {
			@Override
			public Map<String, Object> isHit(int systemTaskId, int taskLibrary, Map<String, String> params) {
				Map<String, Object> returnMap = Maps.newConcurrentMap();
				returnMap.put("rt", false);
				returnMap.put("tm", -1);
				
				if (taskLibrary == TaskLibraryType.COLLECT) {
					int toolType = NumberUtils.parseNumber(params.get("toolType"), Integer.class);
					int toolId = NumberUtils.parseNumber(params.get("toolId"), Integer.class);
					Preconditions.checkNotNull(toolType, "toolType不能为空");
					Preconditions.checkNotNull(toolId, "toolId不能为空");
				
					if (toolType == GoodsType.Gemstone.intValue && toolId == systemGemstone.getGemstoneId()) {
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
	 * 获取用户宝石列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserGemstoneBO> getUserGemstoneBOList(String userId) {
		List<UserGemstoneBO> list = Lists.newArrayList();
		List<UserGemstone> userGemstoneList = this.userGemstoneDao.getUserGemstoneList(userId);
		for (UserGemstone userGemstone : userGemstoneList) {
			list.add(createUserGemstoneBO(userGemstone));
		}
		
		return list;
	}
	
	public UserGemstoneBO createUserGemstoneBO(UserGemstone userGemstone) {
		UserGemstoneBO bo = new UserGemstoneBO();
		bo.setUserGemstoneId(userGemstone.getUserGemstoneId());
		bo.setGemstoneId(userGemstone.getGemstoneId());
		bo.setUserEquipId(userGemstone.getUserEquipId());
		bo.setAttr(userGemstone.getGemstoneAttr());
		bo.setPos(userGemstone.getPos());
		bo.setStorehouseNum(userGemstone.getStorehouseNum());
		
		return bo;
	}
	
	public UserGemstone getUserGemstone(String userId, String userGemstoneId) {
		return this.userGemstoneDao.getUserGemstone(userId, userGemstoneId);
	}

	/**
	 *  获取镶嵌在该装备上的所有宝石
	 * 
	 * @param userId
	 * @param userEquipId
	 * @return
	 */
	public List<UserGemstone> getUserGemstoneListInEquip(String userId, String userEquipId) {
		return this.userGemstoneDao.getUserGemstoneListInEquip(userId, userEquipId);
	}
	
	/**
	 * 获取用户宝石列表 
	 * 
	 * @param userId
	 * @param status 0 在背包中   1 镶嵌在装备
	 * @return
	 */
	public List<UserGemstoneBO> getUserGemstoneBOList(String userId, int status) {
		List<UserGemstoneBO> inEquipList = Lists.newArrayList();
		List<UserGemstoneBO> inBagList = Lists.newArrayList();
		
		List<UserGemstone> userGemstoneList = this.userGemstoneDao.getUserGemstoneList(userId);
		for (UserGemstone userGemstone : userGemstoneList) {
			if (StringUtils.isBlank(userGemstone.getUserEquipId()) || userGemstone.getPos() == 0) {
				inBagList.add(createUserGemstoneBO(userGemstone));
			} else {
				inEquipList.add(createUserGemstoneBO(userGemstone));
			}
		}
		
		if (status == 0)
			return inBagList;
		
		return inEquipList;
	}
	
	/**
	 * 装备镶嵌
	 * 
	 * @param userId
	 * @param userGemstoneId
	 * @param userEquipId
	 * @param pos
	 * @return
	 */
	public UserGemstoneBO fillInEquip(String userId, String userGemstoneId, String userEquipId, int pos) {
		this.userGemstoneDao.fillInEquip(userId, userGemstoneId, userEquipId, pos);
		
		UserGemstone userGemstone = this.userGemstoneDao.getUserGemstone(userId, userGemstoneId);
		SystemGemstone systemGemstone = this.systemGemstoneDaoCache.getSystemGemstone(userGemstone.getGemstoneId());
		userGemstoneOperatorLog(userId, userGemstoneId, userGemstone.getGemstoneId(), userGemstone.getUserEquipId(), systemGemstone.getLevel(), LogType.GEMSTONE_FILL_IN, new Date());
		return createUserGemstoneBO(userGemstone);
	}
	
	private final static int MAX_LEVEL = 100;
	private final static int HAS_UPGRADE_MAX_LEVEL = 2001;
	private final static int HAS_NOT_START_UPGRADE = 2002;
	/**
	 * 宝石升级
	 * 
	 * @param userId
	 * @param userGemstoneId
	 * @return
	 */
	public GemstoneAction_gemstoneUpgradeRes gemstoneUpgrade(String userId, String userGemstoneId, int status) {
		int openLevel = this.configDataDao.getInt(ConfigKey.gemstone_open_level, 30);
		User user = this.userService.getByUserId(userId);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足");
		
		ToolOperationBean bean = upgradeMap.remove(userId);
		if (bean == null && status != OPERATION_START)
			throw new ServiceException(HAS_NOT_START_UPGRADE, "还没开始宝石升级呀！！userId = " + userId);
		
		if (bean != null && status == OPERATION_START)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "已经开始升级了丫，为什么又请求开始升级呢！！userId = " + userId);
		
		// 取消操作，真是犯贱
		GemstoneAction_gemstoneUpgradeRes res = new GemstoneAction_gemstoneUpgradeRes();
		res.setGoodsList(new ArrayList<GoodsBeanBO>());
		res.setUserGemstoneIdList(new ArrayList<String>());
		res.setUserGemstoneBO(new UserGemstoneBO());
		res.setStatus(status);
		if (status == OPERATION_CANCEL) {
			return res;
		}		
		
		UserGemstone userGemstone = this.userGemstoneDao.getUserGemstone(userId, userGemstoneId);
		if (userGemstone == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "该用户没有这个宝石，userGemstoneId=" + userGemstoneId);
		
		SystemGemstone systemGemstone = this.systemGemstoneDaoCache.getSystemGemstone(userGemstone.getGemstoneId());
		if (systemGemstone.getLevel() >= MAX_LEVEL)
			throw new ServiceException(HAS_UPGRADE_MAX_LEVEL, "已经达到顶级");
		
		SystemGemstoneUpgrade gemstoneUpgrade = this.systemGemstoneUpgradeDaoCache.getSystemGemstoneUpgrade(userGemstone.getGemstoneId());
		if (gemstoneUpgrade == null) 
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有这个宝石升级数据，gemstoneId=" + userGemstone.getGemstoneId());
		
		SystemGemstone upgradeGemstone = this.systemGemstoneDaoCache.getSystemGemstone(gemstoneUpgrade.getUpgradeGemstoneId());
		if (upgradeGemstone == null) 
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有这个系统宝石升级数据，gemstoneId=" + gemstoneUpgrade.getUpgradeGemstoneId());		
		
		Date now = new Date();
		if (status == OPERATION_START) {
			// 判断升级道具是否足够
			List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(gemstoneUpgrade.getNeedMaterial());
			for (GoodsBeanBO bo : goodsList) {
				if (bo.getGoodsType() == GoodsType.tool.intValue) {			
					UserToolBO userTool = this.toolService.getUserToolBO(userId, bo.getGoodsId());
					if (userTool == null || userTool.getToolNum() < bo.getGoodsNum())
						throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");	
				} else if (bo.getGoodsType() == GoodsType.Gemstone.intValue) {
					List<UserGemstone> list = this.userGemstoneDao.getUserGemstoneList(userId, bo.getGoodsId());
					if (list == null || list.size() < bo.getGoodsNum())
						throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");	
				}
			}
			
			// TODO 扣除金币
			int cost = this.configDataDao.getInt(ConfigKey.gemstone_upgrade_cost, 100);
			int costGold = (systemGemstone.getQuality() * 5 + systemGemstone.getLevel() * 5) * cost;
			if (user.getGold() < costGold)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
			
			bean = new ToolOperationBean();
			bean.setCostGold(costGold);
			bean.setCostList(goodsList);
			bean.setCreatedTime(now);
			upgradeMap.put(userId, bean);
			
			return res;
		}
		
		int cdTime = this.configDataDao.getInt(ConfigKey.equip_stone_operation_cd_time, 2) * 1000;
		if (now.getTime() < bean.getCreatedTime().getTime() + cdTime)
			throw new ServiceException(SystemErrorCode.IN_CD_TIME, "cd时间都未到，你请求个蛋丫！！！userId=" + userId);
		
		if (!this.userService.reduceGold(userId, bean.getCostGold(), GoodsUseType.REDUCE_GEMSTONE_UPGRADE))
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		// 扣除升级道具
		List<String> userGemstoneIdList = Lists.newArrayList();
		// 扣除所需材料
		for (GoodsBeanBO bo : bean.getCostList()) {
			if (bo.getGoodsType() == GoodsType.tool.intValue) {			
				this.toolService.reduceTool(userId, bo.getGoodsId(), bo.getGoodsNum(), GoodsUseType.REDUCE_GEMSTONE_FOEGE);	
			} else if (bo.getGoodsType() == GoodsType.Gemstone.intValue) {
				List<UserGemstone> list = this.userGemstoneDao.getUserGemstoneList(userId, bo.getGoodsId());
				for (int i = 0; i < bo.getGoodsNum(); i++) {
					UserGemstone stone = list.get(i);
					reduceUserGemstone(userId, stone.getUserGemstoneId(), GoodsUseType.REDUCE_GEMSTONE_FOEGE, null);
					userGemstoneIdList.add(stone.getUserGemstoneId());
				}				
			}
		}
		
		// 增加相关属性
		List<Attribute> gemstoneAttrList = (List<Attribute>) JSON.parseArray(userGemstone.getGemstoneAttr(), Attribute.class);
		Map<String, Attribute> map = Maps.newConcurrentMap();
		for (Attribute gemstoneAttr : gemstoneAttrList) {
			map.put(gemstoneAttr.getAttr(), gemstoneAttr);
		}
		
		List<SystemGemstoneAttr> systemAttrList = this.systemGemstoneAttrDaoCache.getSystemGemstoneAttrList(upgradeGemstone.getGemstoneId());
		for (SystemGemstoneAttr attr :systemAttrList) {
			int value = RandomUtils.getRandomNum(attr.getLowerNum(), attr.getUpperNum());
			if (map.containsKey(attr.getAttr())) {
				Attribute gemstoneAttr = map.get(attr.getAttr());
				gemstoneAttr.setValue(gemstoneAttr.getValue() + value);
			} else {
				Attribute gemstoneAttr = new Attribute();
				gemstoneAttr.setAttr(attr.getAttr());
				gemstoneAttr.setValue(value);
				
				map.put(attr.getAttr(), gemstoneAttr);
			}		
		}
		
		String attrValue = JSON.toJSONString(map.values());		
		this.userGemstoneDao.upgradeUserGemstone(userId, userGemstoneId, gemstoneUpgrade.getUpgradeGemstoneId(), attrValue);
		
		userGemstone = this.userGemstoneDao.getUserGemstone(userId, userGemstoneId);	
		res.setUserGemstoneBO(createUserGemstoneBO(userGemstone));
		
		userGemstoneOperatorLog(userId, userGemstoneId, userGemstone.getGemstoneId(), userGemstone.getUserEquipId(), systemGemstone.getLevel(), LogType.GEMSTONE_FILL_IN, new Date());
		user = this.userService.getByUserId(userId);
		res.setUserGemstoneIdList(userGemstoneIdList);
		res.setGoodsList(bean.getCostList());
		res.setGold(user.getGold());
		res.setMoney(user.getMoney());
		
		this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_UPGRADE_GEMSTONE, 1);
		return res;
	}
	
	/**
	 * 获取宝石所占格子数
	 * 
	 * @param userId
	 * @param type 0 背包中  1 仓库中
	 * @return
	 */
	public int getUserGemstoneCount(String userId, int type) {
		return this.userGemstoneDao.getUserGemstoneCount(userId, type);
	}
	
	/**
	 * 获取未镶嵌的某个宝石-在背包内的
	 * 
	 * @param userId
	 * @param gemstoneId
	 * @return
	 */
	public List<UserGemstone> getUnFillGemstoneList(String userId, int gemstoneId) {
		return this.userGemstoneDao.getUnFillGemstoneList(userId, gemstoneId);
	}
	
	/**
	 * 仓库存入，取出宝石
	 * 
	 * @param userId
	 * @param userGemstoneId
	 * @param storehouseNum
	 * @return
	 */
	public boolean storehouseInOrOut(String userId, String userGemstoneId, int storehouseNum) {
		return this.userGemstoneDao.storehouseInOrOut(userId, userGemstoneId, storehouseNum);
	}
	
	/**
	 * 用户宝石操作日志
	 * 
	 * @param userId
	 * @param userGemstoneId
	 * @param gemstoneId
	 * @param userHeroId
	 * @param gemstoneLevel
	 * @param type
	 * @param operatorTime
	 */
	public void userGemstoneOperatorLog(String userId, String userGemstoneId, int gemstoneId, String userEquipId, int gemstoneLevel, int type, Date operatorTime) {
		String time = DateUtils.getTimeStr(operatorTime);
		String now = DateUtils.getDateForLog();
		String sql = "INSERT INTO user_gemstone_log_" + now + "(USER_ID, USER_GEMSTONE_ID, GEMSTONE_ID, USER_EQUIP_ID, GEMSTONE_LEVEL, TYPE, OPERATION_TIME) VALUES" + "('" + userId + "','" + userGemstoneId + "',"
				+ gemstoneId + ",'" + userEquipId + "'," + gemstoneLevel + "," + type + ",'" + time + "')";
		logService.unSynInsertLog(sql);
	}

	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		if(handlerType.equals(ModuleEventConstant.USER_LOGINOUT_EVENT)){
			String userId = baseModuleEvent.getStringValue("userId", "");
			forgeMap.remove(userId);
			upgradeMap.remove(userId);
			resolveMap.remove(userId);
			
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
