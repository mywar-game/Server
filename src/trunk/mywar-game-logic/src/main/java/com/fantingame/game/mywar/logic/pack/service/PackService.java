package com.fantingame.game.mywar.logic.pack.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.pack.PackAction_abandonToolRes;
import com.fantingame.game.msgbody.client.pack.PackAction_extendPackRes;
import com.fantingame.game.msgbody.client.pack.PackAction_extendStorehouseRes;
import com.fantingame.game.msgbody.client.pack.PackAction_storehouseInOrOutRes;
import com.fantingame.game.msgbody.client.pack.UserPackBO;
import com.fantingame.game.msgbody.client.pack.UserPackExtendBO;
import com.fantingame.game.msgbody.client.tool.UserToolBO;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.equip.model.UserEquip;
import com.fantingame.game.mywar.logic.equip.service.EquipService;
import com.fantingame.game.mywar.logic.gemstone.model.UserGemstone;
import com.fantingame.game.mywar.logic.gemstone.service.GemstoneService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.pack.dao.cache.UserBackPackDaoCacheImpl;
import com.fantingame.game.mywar.logic.pack.dao.cache.UserBackPackExtendsDaoCache;
import com.fantingame.game.mywar.logic.pack.model.UserBackPack;
import com.fantingame.game.mywar.logic.pack.model.UserBackPackExtend;
import com.fantingame.game.mywar.logic.tool.constant.ToolType;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.model.UserExtInfo;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.collect.Lists;

public class PackService {
    @Autowired
	private UserBackPackDaoCacheImpl userBackPackDaoCacheImpl;
    @Autowired
    private UserBackPackExtendsDaoCache userBackPackExtendsDaoCache;
    @Autowired
    private ToolService toolService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfigDataDaoCacheImpl configDateDao;
    @Autowired
    private GoodsDealService goodsDealService;
    @Autowired
    private EquipService equipService;
    @Autowired
    private GemstoneService gemstoneService;
    
    //同样的道具行囊，不需要替换
    public static final int THE_SAME_TOOLID = 2001;
    public PackAction_extendPackRes userBackPackChangePos(String userId,int toolId,int targetPos){
    	PackAction_extendPackRes res = new PackAction_extendPackRes();
		SystemTool systemTool = toolService.getSystemTool(toolId);
		if(systemTool.getType() != ToolType.BACK_PACK){
			throw new ServiceException(SystemConstant.FAIL_CODE, "此道具不是行囊，让飞哥改下类型，type="+systemTool.getType()+",toolId="+toolId);
		}
    	//查询位置上是否有
    	UserBackPackExtend userBackPackExtend = userBackPackExtendsDaoCache.getUserBackPackExtendByPos(userId, targetPos);
    	if(userBackPackExtend!=null){
    		//要替换的位置和使用的道具id一样 则不处理
    		if(userBackPackExtend.getToolId()==toolId){
    			throw new ServiceException(THE_SAME_TOOLID, "此道具不是行囊，让飞哥改下类型，type="+systemTool.getType()+",toolId="+toolId);
    		}
    		int beReplaceToolId = userBackPackExtend.getToolId();
            if(toolService.reduceTool(userId, toolId, 1, GoodsUseType.REDUCE_PACKGE_EXTEND)){
            	if(userBackPackExtendsDaoCache.updateUserBackPackExtend(userId, targetPos, toolId)){
            		//res.setTargetPos(targetPos);
            		//res.setToolId(toolId);
            	}
            	//res.setReduceOneToolId(toolId);
            	if(toolService.addTool(userId, beReplaceToolId, 1, GoodsUseType.ADD_PACKGE_EXTEND_RETURN)){
            		//res.setAddOneToolId(beReplaceToolId);
            	}
            }
    	}else{
    		userBackPackExtend = new UserBackPackExtend();
    		userBackPackExtend.setUserId(userId);
    		userBackPackExtend.setPos(targetPos);
    		userBackPackExtend.setToolId(toolId);
    		userBackPackExtend.setCreatedTime(new Date());
    		if(toolService.reduceTool(userId, toolId, 1, GoodsUseType.REDUCE_PACKGE_EXTEND)){
    			//res.setReduceOneToolId(toolId);
    			if(userBackPackExtendsDaoCache.addUserBackPackExtend(userBackPackExtend)){
            		//res.setTargetPos(targetPos);
            		//res.setToolId(toolId);
    			}
    		}
    	}
    	return res;
    }
    /**
     * 获取背包扩展信息
     * @param userId
     * @return
     */
    public List<UserPackExtendBO> getUserPackExtendBOList(String userId){
    	List<UserBackPackExtend> list = userBackPackExtendsDaoCache.getUserBackPackExtendList(userId);
    	List<UserPackExtendBO> result = Lists.newArrayList();
    	if(list!=null&&list.size()>0){
    		for(UserBackPackExtend backPackExtend:list){
    			result.add(createUserPackExtendBO(backPackExtend));
    		}
    	}
    	return result;
    }
    
    private UserPackExtendBO createUserPackExtendBO(UserBackPackExtend userBackPackExtend){
    	UserPackExtendBO bo = new UserPackExtendBO();
    	bo.setToolId(userBackPackExtend.getToolId());
        bo.setPos(userBackPackExtend.getPos());
        return bo;
    }
    /**
     * 获取用户背包列表对象
     * @param userId
     * @return
     */
    public List<UserPackBO> getUserPackBOList(String userId){
    	List<UserBackPack> list = userBackPackDaoCacheImpl.getPackGoodsList(userId);
    	List<UserPackBO> result = Lists.newArrayList();
    	for(UserBackPack userBackPack:list){
    		result.add(creatUserPackBO(userBackPack));
    	}
    	return result;
    }
    
    private static final int HAS_MAX_PACKNUM = 2001;
    private static final int EXTEND_PACKNUM_LIMIT = 2002;
    /**
     * 扩展用户背包，新的案子推翻了旧封建制度
     * 
     * @param userId
     * @param extendNum
     * @return
     */
    public CommonGoodsBeanBO extendPack(String userId, int extendNum) {
    	User user = this.userService.getByUserId(userId);
    	UserExtInfo userExtInfo = this.userService.getUserExtInfo(userId);
    	
    	int maxExtendTimes = this.configDateDao.getInt(ConfigKey.pack_max_extend_times, 48);
    	if (userExtInfo.getPackExtendTimes() >= maxExtendTimes)
    		throw new ServiceException(HAS_MAX_PACKNUM, "你的扩展背包已满");
    	
    	if ((userExtInfo.getPackExtendTimes() + extendNum) > maxExtendTimes)
    		throw new ServiceException(EXTEND_PACKNUM_LIMIT, "扩展后，背包超出限制");
    	
    	int totalDiamond = 0;
    	for (int i = 1; i <= extendNum; i++) {
    		totalDiamond = totalDiamond + ((userExtInfo.getPackExtendTimes() + i) * 10);
    	}
    	
    	if (user.getMoney() < totalDiamond)
    		throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
    	
    	if (!this.userService.reduceMoney(userId, totalDiamond, GoodsUseType.REDUCE_PACKGE_EXTEND))
    		throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
    	
    	CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, GoodsType.packExtendTimes.intValue + ",0," + extendNum, GoodsUseType.REDUCE_PACKGE_EXTEND);
    	List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(GoodsType.MONEY.intValue + ",0,-" + totalDiamond);
    	drop.getGoodsList().addAll(goodsList);
    	return drop;
    }
    
    private UserPackBO creatUserPackBO(UserBackPack userBackPack){
    	UserPackBO target = new UserPackBO();
    	BeanUtils.copyProperties(userBackPack, target);
    	return target;
    }
    
    /**
     * 放弃道具
     * 
     * @param userId
     * @param toolType
     * @param toolId
     * @param toolNum
     * @param userEquipId
     * @param userGemstoneId
     */
    public PackAction_abandonToolRes abandonTool(String userId, int toolType, int toolId, int toolNum, String userEquipId, String userGemstoneId) {    	
    	if (toolType == GoodsType.equip.intValue) {
    		if (StringUtils.isBlank(userEquipId))
    			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "用户装备id未传入");
    		
    		this.equipService.reduceEquip(userId, userEquipId, GoodsUseType.REDUCE_ABANDON_TOOL, null);
    	} else if (toolType == GoodsType.Gemstone.intValue) {
    		if (StringUtils.isBlank(userGemstoneId))
    			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "用户宝石id未传入");
    		
    		this.gemstoneService.reduceUserGemstone(userId, userGemstoneId, GoodsUseType.REDUCE_ABANDON_TOOL, null);
    	} else {
    		this.toolService.reduceTool(userId, toolId, toolNum, GoodsUseType.REDUCE_ABANDON_TOOL);
    	}
    	
    	PackAction_abandonToolRes res = new PackAction_abandonToolRes();
    	res.setToolId(toolId);
    	res.setToolType(toolType);
    	res.setToolNum(toolNum);
    	res.setUserEquipId(userEquipId);
    	res.setUserGemstoneId(userGemstoneId);
    	
    	return res;
    }
    
    private final static int STOREHOUSE_IN = 1;
    private final static int STOREHOUSE_OUT = 0;
    /**
     * 存入仓库
     * 
     * @param userId
     * @param toolType
     * @param toolId
     * @param toolNum
     * @param userEquipId
     * @param userGemstoneId
     * @return
     */
    public PackAction_storehouseInOrOutRes storehouseInOrOut(String userId, int type, int toolType, int toolId, int toolNum, String userEquipId, String userGemstoneId) {
    	if (type != STOREHOUSE_IN && type != STOREHOUSE_OUT)
    		throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不正确，type=" + type);
    	
    	List<GoodsBeanBO> goodsList = Lists.newArrayList();
    	GoodsBeanBO goodBeanBO = GoodsHelper.parseDropGood(toolType, toolId, toolNum);
    	goodsList.add(goodBeanBO);
    	
    	if (type == STOREHOUSE_IN) {
			// 先检查仓库背包是否已满
			this.userService.checkStorehouse(userId, goodsList);    			
		} else {
			// 检测背包
			this.userService.checkBagUseInStorehouseOut(userId, goodsList);    			
		}
    	
    	PackAction_storehouseInOrOutRes res = new PackAction_storehouseInOrOutRes();
    	res.setType(type);
    	res.setToolType(toolType);
    	res.setToolId(toolId);
    	res.setToolNum(toolNum);
    	res.setUserEquipId(userEquipId);
    	res.setUserGemstoneId(userGemstoneId);
    	
    	if (toolType == GoodsType.equip.intValue) {
    		if (StringUtils.isBlank(userEquipId))
    			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "用户装备id未传入");
    		
    		UserEquip userEquip = this.equipService.getUserEquip(userId, userEquipId);
    		if (userEquip == null)
    			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "装备已经不存在？？？userEquipId=" + userEquipId);
    		
    		if (userEquip.getStorehouseNum() == type) 
    			return res;
    		
    		this.equipService.storehouseInOrOut(userId, userEquipId, type);
    	} else if (toolType == GoodsType.Gemstone.intValue) {
    		if (StringUtils.isBlank(userGemstoneId))
    			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "用户宝石id未传入");
    		
    		UserGemstone userGemstone = this.gemstoneService.getUserGemstone(userId, userGemstoneId);
    		if (userGemstone == null)
    			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "宝石已经不存在？？？userGemstoneId=" + userGemstoneId);
    		
    		if (userGemstone.getStorehouseNum() == type) 
    			return res;
    		
    		this.gemstoneService.storehouseInOrOut(userId, userGemstoneId, type);
    	} else {    		
    		UserToolBO userTool = this.toolService.getUserToolBO(userId, toolId);
    		int storehouseNum = 0;    		
    		
    		if (type == STOREHOUSE_IN) {
    			if (userTool == null || userTool.getToolNum() < toolNum)
    				throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");    			
    			
    			storehouseNum = userTool.getStorehouseNum() + toolNum;
    			toolNum = userTool.getToolNum() - toolNum;
    		} else {
    			if (userTool == null || userTool.getStorehouseNum() < toolNum)
    				throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "仓库道具不足");
    			
    			storehouseNum = userTool.getStorehouseNum() - toolNum;
    			toolNum = userTool.getToolNum() + toolNum;
    		}    		
    		
    		this.toolService.storehouseInOrOut(userId, toolId, toolNum, storehouseNum);
    	}    	
    	
    	return res;
    }
    
    /**
     * 仓库扩展
     * 
     * @param userId
     * @param extendNum
     * @return
     */
    public PackAction_extendStorehouseRes extendStorehouse(String userId, int extendNum) {
    	User user = this.userService.getByUserId(userId);
    	UserExtInfo userExtInfo = this.userService.getUserExtInfo(userId);
    	
    	int maxExtendTimes = this.configDateDao.getInt(ConfigKey.storehouse_max_num, 80);
    	if (userExtInfo.getStorehouseExtendTimes() >= maxExtendTimes)
    		throw new ServiceException(HAS_MAX_PACKNUM, "你的扩展仓库已满");
    	
    	if ((userExtInfo.getStorehouseExtendTimes() + extendNum) > maxExtendTimes)
    		throw new ServiceException(EXTEND_PACKNUM_LIMIT, "扩展后，背包超出限制");
    	
    	int totalDiamond = 0;
    	for (int i = 1; i <= extendNum; i++) {
    		totalDiamond = totalDiamond + ((userExtInfo.getStorehouseExtendTimes() + i) * 10);
    	}
    	
    	if (user.getMoney() < totalDiamond)
    		throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
    	
    	if (!this.userService.reduceMoney(userId, totalDiamond, GoodsUseType.REDUCE_STOREHOUSE_EXTEND))
    		throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
    	
    	// 更新仓库背包格子
    	this.userService.extendStorehouseNum(userId, extendNum);    	
    	user = this.userService.getByUserId(userId);
    	PackAction_extendStorehouseRes res = new PackAction_extendStorehouseRes();
    	res.setExtendNum(extendNum);
    	res.setMoney(user.getMoney());
    	return res;
    }
}
