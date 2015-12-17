package com.fantingame.game.mywar.logic.tool.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.NumberUtils;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.log.service.LogService;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.tool.ToolAction_openBoxRes;
import com.fantingame.game.msgbody.client.tool.UserToolBO;
import com.fantingame.game.mywar.logic.achievement.check.IAchievementChecker;
import com.fantingame.game.mywar.logic.achievement.constant.AchievementConstant;
import com.fantingame.game.mywar.logic.achievement.service.AchievementService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.task.check.ITaskHitChecker;
import com.fantingame.game.mywar.logic.task.constant.TaskLibraryType;
import com.fantingame.game.mywar.logic.task.service.UserTaskService;
import com.fantingame.game.mywar.logic.tool.constant.ToolConstant;
import com.fantingame.game.mywar.logic.tool.constant.ToolType;
import com.fantingame.game.mywar.logic.tool.dao.cache.SystemMagicReelAttrDaoCache;
import com.fantingame.game.mywar.logic.tool.dao.cache.SystemToolDaoCacheImpl;
import com.fantingame.game.mywar.logic.tool.dao.cache.SystemToolDropDaoCacheImpl;
import com.fantingame.game.mywar.logic.tool.dao.cache.UserToolDaoCacheImpl;
import com.fantingame.game.mywar.logic.tool.model.SystemMagicReelAttr;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.model.SystemToolDrop;
import com.fantingame.game.mywar.logic.tool.model.UserTool;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public class ToolService{
	
    @Autowired
	private UserToolDaoCacheImpl userToolDaoCacheImpl;
    @Autowired
    private LogService logService;
    @Autowired
    private SystemToolDaoCacheImpl systemToolDaoCacheImpl;
    @Autowired
    private SystemToolDropDaoCacheImpl systemToolDropDaoCacheImpl;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsDealService goodsDealService;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private SystemMagicReelAttrDaoCache systemMagicReelAttrDaoCache;
    @Autowired
    private AchievementService achievementService;
    
	/**
	 * 添加道具
	 * @param userId
	 * @param toolId
	 * @param toolNum
	 * @param goodsUseType
	 * @return
	 */
	public boolean addTool(String userId, final int toolId, final int toolNum, int goodsUseType) {
		LogSystem.debug("增加道具.userId[" + userId + "], toolId[" + toolId + "] toolNum[" + toolNum + "], useType[" + goodsUseType + "]");
		final SystemTool systemTool = systemToolDaoCacheImpl.get(toolId);
		if(systemTool==null){
			ServiceException e = new ServiceException(SystemConstant.FAIL_CODE, "道具不存在，userid="+userId+"toolId="+toolId+",goodsUseType="+goodsUseType);
    		LogSystem.error(e, "道具不存在，toolId="+toolId+",goodsUseType="+goodsUseType);
    		throw e;
		}
		boolean success= this.userToolDaoCacheImpl.addUserTool(userId, toolId, toolNum, 0);
        if(success){
            userTreasureLog(userId, toolId, systemTool.getType(), 1, goodsUseType+"", toolNum, systemTool.getName(), new Date());
            
            // 判断是否或得某项任务道具
            userTaskService.updateTaskFinish(userId, toolNum, new ITaskHitChecker() {
    			@Override
    			public Map<String, Object> isHit(int systemTaskId, int taskLibrary,
    					Map<String, String> params) {
    				Map<String, Object> returnMap = Maps.newConcurrentMap();
    				returnMap.put("rt", false);
    				returnMap.put("tm", toolNum);
    				
    				if (taskLibrary == TaskLibraryType.GET_TASK_TOOL){
    					String paramToolId = params.get("toolId");
    					String paramToolType = params.get("toolType");
    					if (Integer.parseInt(paramToolId) == toolId && Integer.parseInt(paramToolType) == systemTool.getType()) {
    						returnMap.put("rt", true);
    						return returnMap;					
    					}
    				} else if (taskLibrary == TaskLibraryType.COLLECT) {
    					int toolType = NumberUtils.parseNumber(params.get("toolType"), Integer.class);
    					int taskToolId = NumberUtils.parseNumber(params.get("toolId"), Integer.class);
    					Preconditions.checkNotNull(toolType, "toolType不能为空");
    					Preconditions.checkNotNull(toolId, "toolId不能为空");
    					
    					if (toolType == systemTool.getType() && taskToolId == toolId) {
    						returnMap.put("rt", true);
    						return returnMap;
    					}    					
    				}    				
    				return returnMap;
    			}
    		});
            
            // 成就
            achievementService.updateAchievementFinish(userId, toolNum, AchievementConstant.TYPE_LIFE, new IAchievementChecker() {
				@Override
				public boolean isHit(int achievementId, Map<String, String> params) {
					boolean isHit = false;
					if (params.containsKey("toolType") && params.containsKey("toolId")) {
						int toolType = Integer.parseInt(params.get("toolType"));
						int achievementToolId = Integer.parseInt(params.get("toolId"));
						
						if (toolType == systemTool.getType() && achievementToolId == toolId)
							isHit = true;
					}
					
					return isHit;
				}            	
            });
            
            return true;
        }
		return false;
	}
    /**
     * 减少道具
     * @param userId
     * @param toolId
     * @param toolNum
     * @param goodsUseType
     * @return
     */
	public boolean reduceTool(String userId, final int toolId, final int toolNum, int goodsUseType) {
		LogSystem.debug("消费道具.userId[" + userId + "], toolId[" + toolId + "] toolNum[" + toolNum + "], goodsUseType[" + goodsUseType + "]");
		SystemTool systemTool = systemToolDaoCacheImpl.get(toolId);
		if(systemTool==null){
			ServiceException e = new ServiceException(SystemConstant.FAIL_CODE, "道具不存在，userid="+userId+"toolId="+toolId+",goodsUseType="+goodsUseType);
    		LogSystem.error(e, "道具不存在，toolId="+toolId+",goodsUseType="+goodsUseType);
    		throw e;
		}
		boolean success = this.userToolDaoCacheImpl.reduceUserTool(userId, toolId, toolNum);
		if(success){
            userTreasureLog(userId, toolId, systemTool.getType(), 2, goodsUseType+"", toolNum, systemTool.getName(), new Date());
            
            userTaskService.updateTaskFinish(userId, -toolNum, new ITaskHitChecker() {			
    			@Override
    			public Map<String, Object> isHit(int systemTaskId, int taskLibrary,
    					Map<String, String> params) {
    				Map<String, Object> returnMap = Maps.newConcurrentMap();
    				returnMap.put("rt", false);
    				returnMap.put("tm", -toolNum);
    				
    				if (taskLibrary == TaskLibraryType.COLLECT) {
    					int toolType = NumberUtils.parseNumber(params.get("toolType"), Integer.class);
    					int taskToolId = NumberUtils.parseNumber(params.get("toolId"), Integer.class);
    					Preconditions.checkNotNull(toolType, "toolType不能为空");
    					Preconditions.checkNotNull(taskToolId, "toolId不能为空");
    				
    					if (toolType == GoodsType.tool.intValue && taskToolId == toolId) {
    						returnMap.put("rt", true);
    						return returnMap;
    					}					
    				}
    				
    				return returnMap;
    			}
    		});
            
            return true;
		}
		return false;
	}
    /**
     * 获取用户道具列表
     * @param userId
     * @return
     */
	public List<UserToolBO> getUserToolList(String userId) {
		List<UserToolBO> userToolBOList = Lists.newArrayList();
		List<UserTool> userToolList = userToolDaoCacheImpl.getList(userId);
		for (UserTool userTool : userToolList) {
			if(userTool.getToolNum() > 0 || userTool.getStorehouseNum() > 0){
				UserToolBO userToolBO = creatUserToolBO(userTool);
				userToolBOList.add(userToolBO);
			}
		}
		return userToolBOList;
	}
	/**
	 * 创建客户端bean
	 * @param userTool
	 * @return
	 */
	private UserToolBO creatUserToolBO(UserTool userTool){
		UserToolBO userToolBO = new UserToolBO();
		if (userTool == null)
			return null;
		
		userToolBO.setToolId(userTool.getToolId());
		userToolBO.setToolNum(userTool.getToolNum());
		userToolBO.setStorehouseNum(userTool.getStorehouseNum());
		return userToolBO;
	}
	
	/**
	 * 获取仓库内道具与存入的道具总共所需格子总数
	 * 
	 * @param userId
	 * @param addMap
	 * @return
	 */
	public int getStorehouseToolNeedCellNum(String userId, Map<Integer, Integer> addMap) {
		int userToolCount = 0;//当前仓库中道具占用的格子数
		int needAddNum = 0;//本次增加道具操作需要用到的新的格子数
		Map<String, UserTool> map = userToolDaoCacheImpl.getUserToolMap(userId);
		if(map.size() > 0){
			for(UserTool tool : map.values()){
				SystemTool systemTool = this.systemToolDaoCacheImpl.get(tool.getToolId());
				if(systemTool.getCheckType() == 0){
					continue;
				}
				
				int overlayMax = systemTool.getOverlapMax();//道具的叠加上限
				int toolBagNum = getCeilNum(tool.getStorehouseNum(), overlayMax);//道具占用的仓库格子数
				userToolCount += toolBagNum;
			}
		}
		
		for(Integer toolId : addMap.keySet()){
			SystemTool systemTool = this.systemToolDaoCacheImpl.get(toolId);
			if(systemTool.getCheckType() == 0)
				continue;
			
			int overlayMax = systemTool.getOverlapMax();//道具的叠加上限
			if (map.containsKey(toolId + "")) {
				UserTool bo = map.get(toolId + "");
				int toolStorehouseNum = getCeilNum(bo.getStorehouseNum(), overlayMax);//道具已经占用的仓库格子数
				int needNum = getCeilNum(bo.getStorehouseNum() + addMap.get(toolId), overlayMax);//新增后需要的格子数
				if(needNum > toolStorehouseNum){
					needAddNum += needNum - toolStorehouseNum;
				}
			} else {
				int needNum = getCeilNum(addMap.get(toolId), overlayMax);//新增后需要的格子数
				needAddNum += needNum;
			}
		}
		
		return userToolCount + needAddNum;
	}
	
	/**
	 * 新加入map中的道具后 道具所需要占的背包大小
	 * 
	 * @param userId
	 * @param addMap
	 * @param isOver 是否可以超出背包 
	 * @return
	 */
	public int getToolNeedCellNum(String userId, Map<Integer, Integer> addMap, boolean isOver){
		int userToolCount = 0;//当前背包中道具占用的格子数
		int needAddNum = 0;//本次增加道具操作需要用到的新的格子数
		Map<String, UserTool> map = userToolDaoCacheImpl.getUserToolMap(userId);
		if(map.size() > 0){
			for(UserTool tool : map.values()){
				SystemTool systemTool = this.systemToolDaoCacheImpl.get(tool.getToolId());
				if(systemTool.getCheckType()==0){
					continue;
				}
				
				int overlayMax = systemTool.getOverlapMax();//道具的叠加上限
				int toolBagNum = getCeilNum(tool.getToolNum(), overlayMax);//道具占用的背包格子数
				userToolCount += toolBagNum;
			}
		}
		
		// TODO 策划说允许背包超过一次上限
		if (!isOver) {
			for(Integer toolId : addMap.keySet()){
				SystemTool systemTool = this.systemToolDaoCacheImpl.get(toolId);
				if(systemTool.getCheckType() == 0)
					continue;
			
				int overlayMax = systemTool.getOverlapMax();//道具的叠加上限
				if (map.containsKey(toolId + "")) {
					UserTool bo = map.get(toolId + "");
					int toolBagNum = getCeilNum(bo.getToolNum(), overlayMax);//道具已经占用的背包格子数
					int needNum = getCeilNum(bo.getToolNum()+addMap.get(toolId), overlayMax);//新增后需要的格子数
					if(needNum > toolBagNum){
						needAddNum += needNum - toolBagNum;
					}
				} else {
					int needNum = getCeilNum(addMap.get(toolId), overlayMax);//新增后需要的格子数
					needAddNum += needNum;
				}
			}
		}
		
		return userToolCount + needAddNum;
	}
	
	public static int getCeilNum(double d1,double d2){
		return (int)Math.ceil(d1/d2);
	}
    //宝箱不足
    private static final int OPEN_BOX_TOOL_NOT_FIND=2001;
    //等级不足
    private static final int OPEN_BOX_LEVEL_LIMIT = 2002;
    //这不是一个宝箱
    private static final int OPEN_BOX_NOT_BOX = 2003;
    //没有钥匙
    private static final int OPEN_BOX_NO_KEY = 2004;
    /**
     * 打开宝箱
     * @param userId
     * @param toolId
     * @return
     */
	public ToolAction_openBoxRes openGiftBox(String userId, int toolId) {
		UserTool userTool = this.userToolDaoCacheImpl.get(userId, toolId);
		if (userTool == null || userTool.getToolNum() <= 0) {
			String message = "打开宝箱失败，用户道具不足.userId[" + userId + "], toolId[" + toolId + "]";
			throw new ServiceException(OPEN_BOX_TOOL_NOT_FIND, message);
		}
		
		SystemTool systemTool = systemToolDaoCacheImpl.get(toolId);
		
		User user = userService.getByUserId(userId);
		int level = user.getLevel();
		if (level < systemTool.getOpenBoxNeedLevel()) 
			throw new ServiceException(OPEN_BOX_LEVEL_LIMIT, "打开宝箱等级不够");

		if (systemTool.getType()!= ToolType.GIFT_BOX) {
			String message = "打开宝箱失败，这不是一个箱箱.userId[" + userId + "], toolId[" + toolId + "]";
			throw new ServiceException(OPEN_BOX_NOT_BOX, message);
		}
		
		//检测是否需要钥匙
		int keyId = systemToolDaoCacheImpl.getGiftBoxKey(toolId);
		if(keyId!=0){
			//检测是否有钥匙
			UserTool keyTool = this.userToolDaoCacheImpl.get(userId, keyId);
			if(keyTool == null || keyTool.getToolNum() < 1){
				String message = "打开宝箱失败，没有钥匙.userId[" + userId + "], toolId[" + toolId + "]+keyId="+keyId;
//				logger.error(message);
				throw new ServiceException(OPEN_BOX_NO_KEY, message);
			}
		}
		
		List<SystemToolDrop> systemToolDropList = this.systemToolDropDaoCacheImpl.getSystemToolDropList(toolId);
		int rand = new Random().nextInt(100000) + 1;
		List<GoodsBeanBO> dropToolBOListCheck = Lists.newArrayList();
		for (SystemToolDrop systemToolDrop : systemToolDropList) {
			if (systemToolDrop.getLowerNum() <= rand && rand <= systemToolDrop.getUpperNum() && user.getLevel()>=systemToolDrop.getMinLevel() && user.getLevel()<=systemToolDrop.getMaxLevel()) {
				//检测背包
				GoodsBeanBO bo = new GoodsBeanBO();
				bo.setGoodsType(systemToolDrop.getDropToolType());
				bo.setGoodsId(systemToolDrop.getDropToolId());
				bo.setGoodsNum(systemToolDrop.getDropToolNum());
				dropToolBOListCheck.add(bo);
			}
		}
		
		//如果没有随机到东西  这必然是受到了等级的限制  则在列表中随机一个给用户
		if(dropToolBOListCheck.size()==0){
			//排除自身等级没达到的奖品  平均随机一次
			List<SystemToolDrop> tempDrop = new ArrayList<SystemToolDrop>();
			for(SystemToolDrop systemToolDrop : systemToolDropList){
				if(user.getLevel()>=systemToolDrop.getMinLevel() && user.getLevel()<=systemToolDrop.getMaxLevel()){
					tempDrop.add(systemToolDrop);
				}
			}
			
			SystemToolDrop toolDrop = systemToolDropList.get(new Random().nextInt(tempDrop.size()));
			GoodsBeanBO bo = new GoodsBeanBO();
			bo.setGoodsType(toolDrop.getDropToolType());
			bo.setGoodsId(toolDrop.getDropToolId());
			bo.setGoodsNum(toolDrop.getDropToolNum());
			dropToolBOListCheck.add(bo);
		}
		
		//检测背包
		userService.checkBag(userId, dropToolBOListCheck);
		boolean success = reduceTool(userId,toolId, 1, GoodsUseType.REDUCE_OPEN_GIFT_BOX);
		if (!success) {
			String message = "打开宝箱失败，用户道具不足.userId[" + userId + "], toolId[" + toolId + "]";
			throw new ServiceException(OPEN_BOX_TOOL_NOT_FIND, message);
	    }
		
		List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(GoodsType.tool.intValue + "," + toolId + ",1");		
		if(keyId != 0){
			//减宝箱
			if(!this.reduceTool(userId,keyId, 1, GoodsUseType.REDUCE_OPEN_GIFT_BOX)){
				String message = "打开宝箱失败，用户道具不足.userId[" + userId + "], toolId[" + toolId + "]";
			    throw new ServiceException(OPEN_BOX_NO_KEY, message);
			}
			goodsList.addAll(GoodsHelper.parseDropGoods(GoodsType.tool.intValue + "," + keyId + ",1"));
		}	
		 
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, dropToolBOListCheck, GoodsUseType.ADD_OPEN_GIFT_BOX);		
		ToolAction_openBoxRes res = new ToolAction_openBoxRes();
		res.setDrop(drop);
		res.setToolList(goodsList);		
        return res;
	}
	
	/**
	 * 获取
	 * 
	 * @param toolId
	 * @return
	 */
	public int getGiftBoxKey(int toolId) {
		return systemToolDaoCacheImpl.getGiftBoxKey(toolId);
	}
	
	public List<GoodsBeanBO> openDropGiftBox(String userId, int toolId) {
		User user = this.userService.getByUserId(userId);
		
		List<SystemToolDrop> systemToolDropList = this.systemToolDropDaoCacheImpl.getSystemToolDropList(toolId);
		int rand = new Random().nextInt(10000) + 1;
		List<GoodsBeanBO> list = Lists.newArrayList();
		
		if (systemToolDropList == null || systemToolDropList.size() == 0)
			return list;
		
		for (SystemToolDrop systemToolDrop : systemToolDropList) {
			if (systemToolDrop.getLowerNum() <= rand && rand <= systemToolDrop.getUpperNum() && user.getLevel()>=systemToolDrop.getMinLevel() 
					&& user.getLevel() <= systemToolDrop.getMaxLevel()) {
				GoodsBeanBO bo = new GoodsBeanBO();
				bo.setGoodsType(systemToolDrop.getDropToolType());
				bo.setGoodsId(systemToolDrop.getDropToolId());
				bo.setGoodsNum(systemToolDrop.getDropToolNum());
				
				list.add(bo);
			}
		}
		
		return list;
	}
	
	/**
	 * 道具的存取
	 * 
	 * @param userId
	 * @param toolId
	 * @param toolNum
	 * @param storehouseNum
	 * @return
	 */
	public boolean storehouseInOrOut(String userId, int toolId, int toolNum, int storehouseNum) {
		return this.userToolDaoCacheImpl.storehouseInOrOut(userId, toolId, toolNum, storehouseNum);
	}
	
	/**
	 * 写日志
	 * @param userId
	 * @param treasureId
	 * @param treasureType
	 * @param getOrUse
	 * @param oprationType
	 * @param changeNum
	 * @param extInfo
	 * @param oprationTime
	 */
	public void userTreasureLog(String userId, int treasureId, int treasureType, int getOrUse, String oprationType, int changeNum, String extInfo, Date oprationTime) {
		String time = DateUtils.getTimeStr(oprationTime);
		String now = DateUtils.getDateForLog();
		String sql = "INSERT INTO user_treasure_log_" + now + "(USER_ID,TREASURE_ID,TREASURE_TYPE,CATEGORY,OPERATION,CHANGE_NUM,EXTINFO,CREATE_TIME) " + "VALUES('" + userId + "'," + treasureId + "," + treasureType + ","
				+ getOrUse + ",'" + oprationType + "'," + changeNum + ",'" + extInfo + "','" + time + "')";
		logService.unSynInsertLog(sql);
	}
	
	/**
	 * 获取系统道具对象
	 * @param toolId
	 * @return
	 */
	public SystemTool getSystemTool(int toolId){
		return systemToolDaoCacheImpl.get(toolId);
	}
	/**
	 * 获取用户道具对象
	 * @param userId
	 * @param toolId
	 * @return
	 */
	public UserToolBO getUserToolBO(String userId, int toolId){
		return creatUserToolBO(userToolDaoCacheImpl.get(userId, toolId));
	}

	/**
	 * 获取附魔卷轴的属性
	 * 
	 * @param reelId
	 * @return
	 */
	public SystemMagicReelAttr getSystemMagicReelAttr(int reelId) {
		return this.systemMagicReelAttrDaoCache.getSystemMagicReelAttr(reelId);
	}
	
	/**
	 * 判断是否为幸运石
	 * 
	 * @param toolId
	 * @return
	 */
	public boolean isLuckyStone(int toolId) {
		ToolConstant toolConstant = ToolConstant.instance();
		return toolConstant.getLuckyStoneMap().containsKey(toolId);
	}

}
