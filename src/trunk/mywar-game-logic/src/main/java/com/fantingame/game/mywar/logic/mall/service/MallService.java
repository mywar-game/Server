package com.fantingame.game.mywar.logic.mall.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.model.SystemConfigData;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.IDGenerator;
import com.fantingame.game.common.utils.RandomUtils;
import com.fantingame.game.log.service.LogService;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.mall.MallAction_buyMysteriousMallRes;
import com.fantingame.game.msgbody.client.mall.UserBuyBackInfoBO;
import com.fantingame.game.msgbody.client.mall.UserMysteriousMallBO;
import com.fantingame.game.msgbody.client.tool.UserToolBO;
import com.fantingame.game.msgbody.notify.mall.Mall_pushMysteriousMallNotify;
import com.fantingame.game.mywar.logic.chat.constant.ChatConstant;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.equip.model.SystemEquip;
import com.fantingame.game.mywar.logic.equip.model.UserEquip;
import com.fantingame.game.mywar.logic.equip.service.EquipService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.mall.dao.UserBuyBackInfoDao;
import com.fantingame.game.mywar.logic.mall.dao.UserMysteriousMallLogDao;
import com.fantingame.game.mywar.logic.mall.dao.UserMysteriousMallMapDao;
import com.fantingame.game.mywar.logic.mall.dao.cache.SystemEquipToolMallDaoCache;
import com.fantingame.game.mywar.logic.mall.dao.cache.SystemMysteriousMallDaoCache;
import com.fantingame.game.mywar.logic.mall.model.SystemEquipToolMall;
import com.fantingame.game.mywar.logic.mall.model.SystemMysteriousMall;
import com.fantingame.game.mywar.logic.mall.model.SystemMysteriousMap;
import com.fantingame.game.mywar.logic.mall.model.UserBuyBackInfo;
import com.fantingame.game.mywar.logic.mall.model.UserMysteriousMallLog;
import com.fantingame.game.mywar.logic.mall.model.UserMysteriousMallMap;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.room.RestrictionsRule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MallService {

	@Autowired
	private UserService userService;
	@Autowired
	private GoodsDealService goodsDealService;
	@Autowired
	private LogService logService;
	@Autowired
	private ToolService toolService;
	@Autowired
	private EquipService equipService;
	@Autowired
	private UserBuyBackInfoDao userBuyBackInfoDao;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private SystemEquipToolMallDaoCache systemEquipToolMallDaoCache;
//	@Autowired
//	private SystemMysteriousMapDaoCache systemMysteriousMapDaoCache;
	@Autowired
	private SystemMysteriousMallDaoCache systemMysteriousMallDaoCache;
	@Autowired
	private UserMysteriousMallMapDao userMysteriousMallMapDao;
	@Autowired
	private UserMysteriousMallLogDao userMysteriousMallLogDao;
	
	// 花费的类型 1 钻石 2 金币
	private static final int COST_TYPE_MONEY = 1;
	private static final int COST_TYPE_GOLD = 2;
	
	/**
	 * 商品买入
	 * 
	 * @param userId
	 * @param mallId
	 * @return
	 */
	public CommonGoodsBeanBO buyIn(String userId, int mallId) {
		User user = userService.getByUserId(userId);
		SystemEquipToolMall systemEquipTool = this.systemEquipToolMallDaoCache.getSystemEquipToolMall(mallId);
		if (systemEquipTool == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "数据有误？？获取不到这个数据哦！mallId:" + mallId);
		
		// 道具
		if (systemEquipTool.getToolType() != GoodsType.equip.intValue) {
			SystemTool systemTool = this.toolService.getSystemTool(systemEquipTool.getToolId());
			if (systemTool == null)
				throw new ServiceException(SystemConstant.FAIL_CODE, "数据有误？？获取不到这个数据哦！toolId:" + systemEquipTool.getToolId());
			
			String goodsIds = systemTool.getType() + "," + systemTool.getToolId() + "," + systemEquipTool.getToolNum();
			List<GoodsBeanBO> goodsList = getGoodsBeanBOList(user, goodsIds, systemTool.getMoneyType(), systemTool.getPrice());			
			CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, goodsList, GoodsUseType.ADD_BUYIN_MALL);
			return drop;			
		}
		
		// 装备
		SystemEquip systemEquip = this.equipService.getSystemEquip(systemEquipTool.getToolId());
		if (systemEquip == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "数据有误？？获取不到这个数据哦！equipId:" + systemEquipTool.getToolId());
		
		String goodsIds = GoodsType.equip.intValue + "," + systemEquip.getEquipId() + ",1";		
		List<GoodsBeanBO> goodsList = getGoodsBeanBOList(user, goodsIds, systemEquip.getMoneyType(), systemEquip.getPrice());			
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, goodsList, GoodsUseType.ADD_BUYIN_MALL);
		return drop;
	}

	private List<GoodsBeanBO> getGoodsBeanBOList(User user, String goodsIds, int moneyType, int price) {
		List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(goodsIds);
		userService.checkBag(user.getUserId(), goodsList);
		
		// 扣钻石		
		if (moneyType == COST_TYPE_MONEY) {
			if (user.getMoney() < price)
				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
			
			if (!this.userService.reduceMoney(user.getUserId(), price, GoodsUseType.REDUCE_BUYIN_MALL))
				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		} else {
			if (user.getGold() < price)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
			
			if (!this.userService.reduceGold(user.getUserId(), price, GoodsUseType.REDUCE_BUYIN_MALL))
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		}
		
		return goodsList;
	}
	
	private static final int HERO_WEARING = 2001;
	/**
	 * 出售
	 * 
	 * @param userId
	 * @param toolType
	 * @param toolId
	 * @return
	 */
	public CommonGoodsBeanBO sell(String userId, int toolType, int toolId, int toolNum, String userEquipId) {
		if (toolNum <= 0)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，数量小于或等于0");

		float discount = this.configDataDao.getInt(ConfigKey.equip_mall_discount, 25) / 100f;
		String reduceTool = toolType + "," + toolId + ",-" + toolNum;
		List<GoodsBeanBO> list = GoodsHelper.parseDropGoods(reduceTool);
		CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
		
		// 出售道具
		if (toolType != GoodsType.equip.intValue) {
			SystemTool systemTool = this.toolService.getSystemTool(toolId);
			if (systemTool == null)
				throw new ServiceException(SystemConstant.FAIL_CODE, "数据有误？？获取不到这个数据哦！toolId:" + toolId);
			
			UserToolBO userToolBO = this.toolService.getUserToolBO(userId, toolId);
			if (userToolBO == null || userToolBO.getToolNum() < toolNum)
				throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");
			
			int totalMoney = (int) Math.round(systemTool.getPrice() * discount * toolNum);
			if (totalMoney == 0)
				totalMoney = systemTool.getPrice() * toolNum;
			
			// 扣道具
			if (!this.toolService.reduceTool(userId, toolId, toolNum, GoodsUseType.REDUCE_BY_SELL))
				throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");			
			
			UserBuyBackInfo userBuyBackInfo = createUserBuyBackInfo(userId, toolType, toolId, toolNum, null);
			this.userBuyBackInfoDao.addUserBuyBackInfo(userBuyBackInfo);
			
			// 加钱
			if (systemTool.getMoneyType() == COST_TYPE_MONEY) {
				String goods = GoodsType.MONEY.intValue + ",0," + totalMoney;
				drop = goodsDealService.sendGoods(userId, goods, GoodsUseType.ADD_BY_SELL);				
			} else {
				String goods = GoodsType.GOLD.intValue + ",0," + totalMoney;
				drop = goodsDealService.sendGoods(userId, goods, GoodsUseType.ADD_BY_SELL);
			}
			
			drop.getGoodsList().addAll(list);
			return drop;
		}
		
		// 出售装备
		if (StringUtils.isBlank(userEquipId))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，userEquipId is null");
		
		UserEquip userEquip = this.equipService.getUserEquip(userId, userEquipId);
		if (userEquip == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "数据有误？？获取不到这个数据哦！equipId:" + toolId + ",userEquipId:" + userEquipId);
		
		if (StringUtils.isNotBlank(userEquip.getUserHeroId()))
			throw new ServiceException(HERO_WEARING, "有英雄在穿哦！！");
		
		SystemEquip systemEquip = this.equipService.getSystemEquip(toolId);		
		int totalMoney = (int) Math.round(systemEquip.getPrice() * discount);
		if (totalMoney == 0)
			totalMoney = systemEquip.getPrice() * toolNum;
		
		// 扣道具
		if (!this.equipService.reduceEquip(userId, userEquipId, GoodsUseType.REDUCE_BY_SELL, null))
			throw new ServiceException(SystemConstant.FAIL_CODE, "出售装备失败");			
		
		UserBuyBackInfo userBuyBackInfo = createUserBuyBackInfo(userId, toolType, toolId, toolNum, userEquip);
		this.userBuyBackInfoDao.addUserBuyBackInfo(userBuyBackInfo);
		
		// 加钱
		if (systemEquip.getMoneyType() == COST_TYPE_MONEY) {
			String goods = GoodsType.MONEY.intValue + ",0," + totalMoney;
			drop = goodsDealService.sendGoods(userId, goods, GoodsUseType.ADD_BY_SELL);
		} else {
			String goods = GoodsType.GOLD.intValue + ",0," + totalMoney;
			drop = goodsDealService.sendGoods(userId, goods, GoodsUseType.ADD_BY_SELL);
		}
		
		return drop;
	}
	
	private UserBuyBackInfo createUserBuyBackInfo(String userId, int toolType, int toolId, int toolNum, UserEquip userEquip) {
		UserBuyBackInfo userBuyBackInfo = new UserBuyBackInfo();
		userBuyBackInfo.setBuyBackId(IDGenerator.getID());
		userBuyBackInfo.setUserId(userId);
		
		userBuyBackInfo.setToolType(toolType);
		userBuyBackInfo.setToolId(toolId);
		userBuyBackInfo.setToolNum(toolNum);
		userBuyBackInfo.setCreatedTime(new Date());
		userBuyBackInfo.setUpdatedTime(new Date());
		
		if (userEquip != null) {
			userBuyBackInfo.setUserEquipId(userEquip.getUserEquipId());
			userBuyBackInfo.setEquipMainAttr(userEquip.getEquipMainAttr());
			userBuyBackInfo.setEquipSecondaryAttr(userEquip.getEquipSecondaryAttr());
		}
		
		return userBuyBackInfo;
	}
	
	/**
	 * 获取用户回购信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserBuyBackInfoBO> getBuyBackList(String userId) {
		Date now = new Date();
		Date endTime = new Date(now.getTime() - 3600 * 2 * 1000);
		this.userBuyBackInfoDao.deleteUserBuyBackInfo(userId, endTime);
		
		List<UserBuyBackInfo> infoList = this.userBuyBackInfoDao.getUserBuyBackInfoList(userId);
		List<UserBuyBackInfo> sortList = sortBuyBackListByUpdatedTime(infoList);
		
		List<UserBuyBackInfoBO> list = Lists.newArrayList();
		if (sortList != null && sortList.size() > 0) {
			for (UserBuyBackInfo info : sortList) {
				list.add(createUserBuyBackInfoBO(info));
			}
		}
		
		return list;
	}
	
	/**
	 * 根据出售时间排序
	 * 
	 * @param list
	 * @return
	 */
	private List<UserBuyBackInfo> sortBuyBackListByUpdatedTime(List<UserBuyBackInfo> list) {
		List<UserBuyBackInfo> collection = Lists.newArrayList();
		collection.addAll(list);
		
		Collections.sort(collection, new Comparator<UserBuyBackInfo>() {
			@Override
			public int compare(UserBuyBackInfo infoI, UserBuyBackInfo infoX) {
				if (infoI.getUpdatedTime().getTime() > infoX.getUpdatedTime().getTime())
					return -1;
				else if (infoI.getUpdatedTime().getTime() < infoX.getUpdatedTime().getTime())
					return 1;
				return 0;
			}			
		});
		return collection;
	}
	
	private UserBuyBackInfoBO createUserBuyBackInfoBO(UserBuyBackInfo info) {
		UserBuyBackInfoBO infoBO = new UserBuyBackInfoBO();
		infoBO.setBuyBackId(info.getBuyBackId());
		infoBO.setToolId(info.getToolId());
		infoBO.setToolType(info.getToolType());
		infoBO.setToolNum(info.getToolNum());
		if (info.getEquipMainAttr() != null && info.getEquipMainAttr().length() > 0) {
			infoBO.setEquipMainAtrr(info.getEquipMainAttr() + ",stamina");
		}		
		infoBO.setEquipSecondaryAttr(info.getEquipSecondaryAttr());
		
		return infoBO;
	}
	
	/**
	 * 回购物品
	 * 
	 * @param userId
	 * @param buyBackId
	 * @return
	 */
	public CommonGoodsBeanBO buyBack(String userId, String buyBackId) {
		UserBuyBackInfo info = this.userBuyBackInfoDao.getUserBuyBackInfo(userId, buyBackId);
		if (info == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有这个回购信息....buBackId:" + buyBackId);
		
		User user = this.userService.getByUserId(userId);
		float discount = this.configDataDao.getInt(ConfigKey.equip_mall_discount, 25) / 100f;
		// 道具
		if (info.getToolType() != GoodsType.equip.intValue) {
			SystemTool systemTool = this.toolService.getSystemTool(info.getToolId());
			int totalMoney = Math.round(systemTool.getPrice() * info.getToolNum() * discount);
							
			String goodsIds = systemTool.getType() + "," + systemTool.getToolId() + "," + info.getToolNum();
			List<GoodsBeanBO> goodsList = getGoodsBeanBOList(user, goodsIds, systemTool.getMoneyType(), totalMoney);				
			this.userBuyBackInfoDao.deleteUserBuyBackInfo(info.getUserId(), info.getBuyBackId());			
			
			CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, goodsList, GoodsUseType.ADD_BY_BUYBACK);
			return drop;			
		}
		
		SystemEquip systemEquip = this.equipService.getSystemEquip(info.getToolId());
		int totalMoney = Math.round(systemEquip.getPrice() * info.getToolNum() * discount);
		
		String goodsIds = GoodsType.equip.intValue + "," + systemEquip.getEquipId() + "," + info.getToolNum();
		getGoodsBeanBOList(user, goodsIds, systemEquip.getMoneyType(), totalMoney);				
		this.userBuyBackInfoDao.deleteUserBuyBackInfo(info.getUserId(), info.getBuyBackId());	
		
		CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
		// 返回装备
		this.equipService.buyBack(info, GoodsUseType.ADD_BY_BUYBACK, drop);
		return drop;
	}
	
	/**
	 * 刷新...神秘商店 
	 * 
	 * TODO 这个接口不再使用
	 */
	public void refreshMysteriousMall() {
//		List<SystemMysteriousMap> mapList = this.systemMysteriousMapDaoCache.getAllList();
		List<SystemMysteriousMap> mapList =  Lists.newArrayList();
		if (mapList == null || mapList.size() == 0)
			throw new ServiceException(SystemConstant.FAIL_CODE, "刷新神秘商店，没有神秘商店的设置，why??");
		
		SystemMysteriousMap map = null;
		int random = RandomUtils.getRandomNum(1, 10000);
		for (SystemMysteriousMap mysteriousMap : mapList) {
			if (random >= mysteriousMap.getLowerNum() && random <= mysteriousMap.getUpperNum()) {
				map = mysteriousMap;
				break;
			}
		}
		
		if (map == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "刷新神秘商店，没有神秘商店的设置，why??");
		
		UserMysteriousMallMap oldMap = this.userMysteriousMallMapDao.getUserMysteriousMallMap();
		if (oldMap != null)
			this.userMysteriousMallMapDao.delete(oldMap.getMapId());
		
		UserMysteriousMallMap userMysteriousMallMap = new UserMysteriousMallMap();
		userMysteriousMallMap.setMapId(map.getMapId());
		userMysteriousMallMap.setCreatedTime(new Date());
		userMysteriousMallMap.setUpdatedTime(new Date());
		this.userMysteriousMallMapDao.save(userMysteriousMallMap);
		
		Mall_pushMysteriousMallNotify notify = new Mall_pushMysteriousMallNotify();
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);		
		MsgDispatchCenter.disPatchUserLowerRoomMsg("Mall_pushMysteriousMall", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, notify, restrictionsRule);		
	}
	
	/**
	 * 获取神秘商店相关信息
	 * 
	 * @return
	 */
	public UserMysteriousMallMap getUserMysteriousMallMap() {
		UserMysteriousMallMap userMysteriousMallMap = this.userMysteriousMallMapDao.getUserMysteriousMallMap();
		return userMysteriousMallMap;
	}
	
	private final static int MYSTERIOUS_MALL_NULL = 2001;
	/**
	 * 获取神秘商店的商品信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserMysteriousMallBO> getMysteriousMallInfo(String userId){
		UserMysteriousMallMap userMysteriousMallMap = this.userMysteriousMallMapDao.getUserMysteriousMallMap();
		if (userMysteriousMallMap == null)
			throw new ServiceException(MYSTERIOUS_MALL_NULL, "神秘商店还没刷新出来.....");
		
		User user = this.userService.getByUserId(userId);
		List<UserMysteriousMallLog> mallLogList = this.userMysteriousMallLogDao.getUserMysteriousMallLogList(userId);
		
		if (mallLogList == null || mallLogList.size() == 0) {
			List<SystemMysteriousMall> mallList = this.systemMysteriousMallDaoCache.getSystemMysteriousMallList(user.getLevel());
			if (mallList.size() == 0)
				throw new ServiceException(SystemConstant.FAIL_CODE, "神秘商店没有相关的商品？？ level = " + user.getLevel());
			
			mallLogList = createUserMysteriousMallLogList(userId, mallList);
			this.userMysteriousMallLogDao.addUserMysteriousLogList(mallLogList);
			return createUserMysteriousMallBOList(mallLogList);
		}
		
		UserMysteriousMallLog mallLog = mallLogList.get(0);
		// 根据时间刷新购买次数
		if (isNeedUpdate(mallLog.getUpdatedTime())) {
			List<SystemMysteriousMall> mallList = this.systemMysteriousMallDaoCache.getSystemMysteriousMallList(user.getLevel());
			if (mallList.size() == 0)
				throw new ServiceException(SystemConstant.FAIL_CODE, "神秘商店没有相关的商品？？ level = " + user.getLevel());
			
			mallLogList = createUserMysteriousMallLogList(userId, mallList);
			this.userMysteriousMallLogDao.deleteUserMysteriousLogList(userId);
			this.userMysteriousMallLogDao.addUserMysteriousLogList(mallLogList);
			return createUserMysteriousMallBOList(mallLogList);
		}
		
		// 需要判断等级内可购买的物品
		Map<Integer, SystemMysteriousMall> map = this.systemMysteriousMallDaoCache.getSystemMysteriousMallMap(user.getLevel());
		Map<Integer, Integer> logMap = Maps.newConcurrentMap();
		for (UserMysteriousMallLog userMallLog : mallLogList) {
			// 不在等级内的删掉
			if (!map.containsKey(userMallLog.getMallId())) {		
				this.userMysteriousMallLogDao.deleteUserMysteriousLog(userId, userMallLog.getMallId());
			} else {
				logMap.put(userMallLog.getMallId(), userMallLog.getMallId());
			}
		}
		
		// 将升级后可购买的商品加入日志
		for (SystemMysteriousMall mall : map.values()) {
			if (!logMap.containsKey(mall.getMallId())) {
				UserMysteriousMallLog userMysteriousMallLog = createUserMysteriousMallLog(userId, mall);
				this.userMysteriousMallLogDao.addUserMysteriousLog(userMysteriousMallLog);				
			}
		}
		
		mallLogList = this.userMysteriousMallLogDao.getUserMysteriousMallLogList(userId);
		return createUserMysteriousMallBOList(mallLogList);
	}
	
	private boolean isNeedUpdate(Date updatedTime) {
		SystemConfigData configData = this.configDataDao.get(ConfigKey.mysterious_mall_refresh_time);
		String firstTime = "12:00";
		String secondTime = "20:00";
		
		if (configData != null) {
			String timeStr = configData.getConfigValue();
			String[] timeArr = timeStr.split("|");
			if (timeArr.length == 2) {
				firstTime = timeArr[0];
				secondTime = timeArr[1];
			}
		}
		
		Date now = new Date();
		Date yesterdayDate = DateUtils.str2Date(DateUtils.getYesterdayDate(new Date()) + " " + firstTime + ":00");
		Date firstDate = DateUtils.str2Date(DateUtils.getDate() + " " + firstTime + ":00");
		Date secondDate = DateUtils.str2Date(DateUtils.getDate() + " " + secondTime + ":00");
		
		if (updatedTime.before(yesterdayDate))
			return true;
		
		// 更新时间 12点前
		if ((updatedTime.before(firstDate) && updatedTime.after(yesterdayDate)) && now.before(firstDate))
			return false;
		
		if ((updatedTime.before(firstDate) && updatedTime.after(yesterdayDate)) && now.after(firstDate))
			return true;
		
		// 更新时间 12-20
		if ((updatedTime.after(firstDate) && updatedTime.before(secondDate)) && now.before(secondDate))
			return false;
		
		if ((updatedTime.after(firstDate) && updatedTime.before(secondDate)) && now.after(secondDate))
			return true;
		
		return false;
	}
	
	private List<UserMysteriousMallBO> createUserMysteriousMallBOList(List<UserMysteriousMallLog> mallLogList) {
		List<UserMysteriousMallBO> mallBOList = Lists.newArrayList();
		for (UserMysteriousMallLog mallLog : mallLogList) {
			UserMysteriousMallBO mallBO = new UserMysteriousMallBO();
			mallBO.setMallId(mallLog.getMallId());
			mallBO.setBuyStatus(mallLog.getBuyTimes());
			
			mallBOList.add(mallBO);
		}
	
		return mallBOList;
	}
	
	private List<UserMysteriousMallLog> createUserMysteriousMallLogList(String userId, List<SystemMysteriousMall> mallList) {
		List<UserMysteriousMallLog> logList = Lists.newArrayList();
		for (SystemMysteriousMall mysteriousMall : mallList) {			
			logList.add(createUserMysteriousMallLog(userId, mysteriousMall));
		}
		
		return logList;
	}
	
	private UserMysteriousMallLog createUserMysteriousMallLog(String userId, SystemMysteriousMall mall) {
		UserMysteriousMallLog mallLog = new UserMysteriousMallLog();
		mallLog.setBuyTimes(0);
		mallLog.setMallId(mall.getMallId());
		mallLog.setUserId(userId);
		mallLog.setCreatedTime(new Date());
		mallLog.setUpdatedTime(new Date());
		
		return mallLog;
	}
	
	private final static int BUY_TIME_NOT_ENOUGH = 2001;
	/**
	 * 购买神秘商店的商品
	 * 
	 * @param userId
	 * @param mallId
	 * @return
	 */
	public MallAction_buyMysteriousMallRes buyMysteriousMall(String userId, int mallId) {
		User user = this.userService.getByUserId(userId);
		SystemMysteriousMall mysteriousMall = this.systemMysteriousMallDaoCache.getSystemMysteriousMall(mallId);
		if (mysteriousMall == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有这个静态数据，mallId=" + mallId);
		
		UserMysteriousMallLog userMallLog = this.userMysteriousMallLogDao.getUserMysteriousMallLog(userId, mallId);
		if (userMallLog == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "一定是数值改变了，之前没有该商品，mallId= " + mallId);
		
		if (mysteriousMall.getCostType() == COST_TYPE_MONEY && user.getMoney() < mysteriousMall.getAmount())
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		if (mysteriousMall.getCostType() == COST_TYPE_GOLD && user.getGold() < mysteriousMall.getAmount())
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		// TODO 判断是否需要刷新
		if (isNeedUpdate(userMallLog.getUpdatedTime())) {
			List<SystemMysteriousMall> mallList = this.systemMysteriousMallDaoCache.getSystemMysteriousMallList(user.getLevel());
			if (mallList.size() == 0)
				throw new ServiceException(SystemConstant.FAIL_CODE, "神秘商店没有相关的商品？？ level = " + user.getLevel());
					
			List<UserMysteriousMallLog> mallLogList = createUserMysteriousMallLogList(userId, mallList);
			this.userMysteriousMallLogDao.deleteUserMysteriousLogList(userId);
			this.userMysteriousMallLogDao.addUserMysteriousLogList(mallLogList);
		}
		
		userMallLog = this.userMysteriousMallLogDao.getUserMysteriousMallLog(userId, mallId);
		if (userMallLog.getBuyTimes() > 0)
			throw new ServiceException(BUY_TIME_NOT_ENOUGH, "神秘商店该商品购买次数不足，mallId=" + mallId);
		
		// 背包检查
		String goodsIds = mysteriousMall.getToolType() + "," + mysteriousMall.getToolId() + "," + mysteriousMall.getToolNum();
		List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(goodsIds);
		this.userService.checkBag(userId, goodsList);
		
		if (mysteriousMall.getCostType() == COST_TYPE_MONEY && user.getMoney() < mysteriousMall.getAmount() 
				&& this.userService.reduceMoney(userId, mysteriousMall.getAmount(), GoodsUseType.REDUCE_BUY_MYSTERIOUS_MALL))
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		if (mysteriousMall.getCostType() == COST_TYPE_GOLD && user.getGold() < mysteriousMall.getAmount()
				&& this.userService.reduceGold(userId, mysteriousMall.getAmount(), GoodsUseType.REDUCE_BUY_MYSTERIOUS_MALL))
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		CommonGoodsBeanBO drop = this.goodsDealService.sendGoods(userId, goodsList, GoodsUseType.ADD_BUY_MYSTERIOUS_MALL);		
		user = this.userService.getByUserId(userId);
		MallAction_buyMysteriousMallRes res = new MallAction_buyMysteriousMallRes();
		res.setDrop(drop);
		res.setMoney(user.getMoney());
		res.setGold(user.getGold());
		return res;
	}
	
	/**
	 * 用户商城日志
	 * 
	 * @param userId
	 * @param userLevel
	 * @param toolId
	 * @param buyNum
	 * @param cost
	 * @param costCopperNum
	 * @param creatTime
	 */
	public void userMallLog(String userId, int userLevel, int toolId, int buyNum, int cost, int costCopperNum, Date creatTime) {
		String time = DateUtils.getTimeStr(creatTime);
		String sql = "INSERT INTO user_mall_log(USER_ID,USER_LEVEL,TREASURE_ID,BUY_NUM,COST,COSTCOPPER,TIME) " + "VALUES('" + userId + "'," + userLevel + "," + toolId + "," + buyNum + "," + cost + "," + costCopperNum
				+ ",'" + time + "')";

		logService.synInsertLog(sql);
	}
	
}
