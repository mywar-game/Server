package com.fantingame.game.mywar.logic.pawnshop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.pawnshop.PawnshopAction_buyInRes;
import com.fantingame.game.msgbody.client.pawnshop.PawnshopAction_getPawnshopInfoRes;
import com.fantingame.game.msgbody.client.pawnshop.PawnshopAction_sellRes;
import com.fantingame.game.msgbody.client.pawnshop.UserPawnshopBO;
import com.fantingame.game.msgbody.client.tool.UserToolBO;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.pawnshop.dao.UserPawnshopInfoDao;
import com.fantingame.game.mywar.logic.pawnshop.dao.cache.SystemPawnshopDaoCache;
import com.fantingame.game.mywar.logic.pawnshop.model.SystemPawnshop;
import com.fantingame.game.mywar.logic.pawnshop.model.UserPawnshopInfo;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;

/**
 * 当铺逻辑层
 * 
 * @author yezp
 */
public class PawnshopService {

	@Autowired
	private SystemPawnshopDaoCache systemPawnshopDaoCache;
	@Autowired
	private UserPawnshopInfoDao userPawnshopInfoDao;
	@Autowired
	private UserService userService;
	@Autowired
	private GoodsDealService goodsDealService;
	@Autowired
	private ToolService toolService;
	
	/**
	 * 获取当铺信息
	 * 
	 * @param userId
	 * @return
	 */
	public PawnshopAction_getPawnshopInfoRes getPawnshopInfo(String userId, int camp) {
		User user = userService.getByUserId(userId);
		if (user.getCamp() != camp)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不对，camp[" + camp + "],user.camp[" + user.getCamp() + "]");
		
		List<SystemPawnshop> shopList = this.systemPawnshopDaoCache.getAllList();
		List<UserPawnshopBO> userPawnshopBOList = new ArrayList<UserPawnshopBO>();
		
		PawnshopAction_getPawnshopInfoRes res = new PawnshopAction_getPawnshopInfoRes();
		List<UserPawnshopInfo> userPawnshopInfoList = this.userPawnshopInfoDao.getUserPawnshopInfoList(user.getCamp());
		
		// 初始化
		if (userPawnshopInfoList == null || userPawnshopInfoList.size() == 0) {
			userPawnshopInfoList = new ArrayList<UserPawnshopInfo>();
			
			for (SystemPawnshop systemPawnshop : shopList) {
				UserPawnshopInfo userPawnshopInfo = createUserPawnshop(user.getCamp(), systemPawnshop.getMallId(), 0);
				userPawnshopInfoList.add(userPawnshopInfo);
			
				UserPawnshopBO bo = createUserPawnshopBO(systemPawnshop.getMallId(), 90, 0);
				userPawnshopBOList.add(bo);
			}
			
			this.userPawnshopInfoDao.add(camp, userPawnshopInfoList);
			res.setUserPawnshopList(userPawnshopBOList);
			return res;
		}
		
		// 可能是数值改变了，mallId与toolId还是需要一一对应
		if (userPawnshopInfoList.size() != shopList.size()) {
			Map<Integer, UserPawnshopInfo> map = getMallIdMap(userPawnshopInfoList);
			List<UserPawnshopInfo> addList = new ArrayList<UserPawnshopInfo>();
			for (SystemPawnshop systemPawnshop : shopList) {
				UserPawnshopBO bo = null;
				int mallId = systemPawnshop.getMallId();
				
				if (map.containsKey(mallId)) {
					bo = createUserPawnshopBO(mallId, 90, map.get(mallId).getNum());
				} else {
					UserPawnshopInfo userPawnshopInfo = createUserPawnshop(user.getCamp(), systemPawnshop.getMallId(), 0);
					addList.add(userPawnshopInfo);
				
					bo = createUserPawnshopBO(systemPawnshop.getMallId(), 90, 0);
				}
			 
				userPawnshopBOList.add(bo);
			}
		
			if (addList.size() > 0) {
				this.userPawnshopInfoDao.add(camp, addList);
			}			
			res.setUserPawnshopList(userPawnshopBOList);
			return res;
		}
		
		for (UserPawnshopInfo info : userPawnshopInfoList) {
			UserPawnshopBO bo = createUserPawnshopBO(info.getMallId(), 90, info.getNum());
			userPawnshopBOList.add(bo);
		}
		
		res.setUserPawnshopList(userPawnshopBOList);
		return res;
	}
	
	private UserPawnshopBO createUserPawnshopBO(int mallId, int floatValue, int num) {
		UserPawnshopBO bo = new UserPawnshopBO();
		bo.setMallId(mallId);
		bo.setFloatValue(floatValue);
		bo.setNum(num);
		
		return bo;
	}
	
	private UserPawnshopInfo createUserPawnshop(int camp, int mallId, int num) {
		Date now = new Date();		
		
		UserPawnshopInfo userPawnshopInfo = new UserPawnshopInfo();
		userPawnshopInfo.setCamp(camp);
		userPawnshopInfo.setMallId(mallId);
		userPawnshopInfo.setNum(num);
		userPawnshopInfo.setCreatedTime(now);
		userPawnshopInfo.setUpdatedTime(now);
		return userPawnshopInfo;
	}
	
	private Map<Integer, UserPawnshopInfo> getMallIdMap(List<UserPawnshopInfo> userPawnshopInfoList) {
		Map<Integer, UserPawnshopInfo> map = new HashMap<Integer, UserPawnshopInfo>();
		for (UserPawnshopInfo info : userPawnshopInfoList) {
			map.put(info.getMallId(), info);
		}
		
		return map;
	}
	
	private static final int NUM_IS_ZERO = 2001;
	/**
	 * 买入
	 * 
	 * @param userId
	 * @param id
	 * @return
	 */
	public PawnshopAction_buyInRes buyIn(String userId, int mallId, int num, int camp) {
		if (num <= 0)
			throw new ServiceException(NUM_IS_ZERO, "买入的数量小于零");
		
		SystemPawnshop systemPawnshop = this.systemPawnshopDaoCache.getSystemPawnshop(mallId);
		if (systemPawnshop == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "数值没有配置？还是传入mallId有误？mallId = " + mallId);
		
		User user = this.userService.getByUserId(userId);
		if (user.getCamp() != camp)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不对，camp[" + camp + "],user.camp[" + user.getCamp() + "]");
		
		List<GoodsBeanBO> dropList = new ArrayList<GoodsBeanBO>();
		GoodsBeanBO goodsBeanBO = new GoodsBeanBO();
		goodsBeanBO.setGoodsId(systemPawnshop.getToolId());
		goodsBeanBO.setGoodsType(systemPawnshop.getToolType());
		goodsBeanBO.setGoodsNum(systemPawnshop.getToolNum() * num);
		dropList.add(goodsBeanBO);
		this.userService.checkBag(userId, dropList);
		
		// 扣除金币
		int totalGold = systemPawnshop.getPrice() * num;
		if (user.getGold() < totalGold)
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		if (!userService.reduceGold(userId, totalGold, GoodsUseType.REDUCE_PAWNSHOP))
			throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		
		// 减少阵营道具数量
		UserPawnshopInfo userPawnshopInfo = this.userPawnshopInfoDao.getUserPawnshopInfo(user.getCamp(), mallId);
		int remainderNum = userPawnshopInfo.getNum() - num;
		// userPawnshopInfo.setNum(userPawnshopInfo.getNum() - num);
		this.userPawnshopInfoDao.updateUserPawnshopInfo(user.getCamp(), mallId, remainderNum);
		
		// 发货
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, dropList, GoodsUseType.ADD_BUY_FROM_PAWNSHOP);
		PawnshopAction_buyInRes res = new PawnshopAction_buyInRes();
		res.setDrop(drop);
		return res;
	}
	
	private static final int HAS_NOT_THIS_TOOL = 2002;
	/**
	 * 卖出
	 * 
	 * @param userId
	 * @param mallId
	 * @param num
	 * @return
	 */
	public PawnshopAction_sellRes sell(String userId, int mallId, int num, int camp) {
		if (num <= 0)
			throw new ServiceException(NUM_IS_ZERO, "买入的数量小于零");
		
		SystemPawnshop systemPawnshop = this.systemPawnshopDaoCache.getSystemPawnshop(mallId);
		if (systemPawnshop == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "数值没有配置？还是传入mallId有误？mallId = " + mallId);

		User user = this.userService.getByUserId(userId);
		if (user.getCamp() != camp)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不对，camp[" + camp + "],user.camp[" + user.getCamp() + "]");
		
		int totalGold = systemPawnshop.getPrice() * num;
		int floatValue = 90;
		totalGold = totalGold * floatValue / 100;
		
		int totalNum = systemPawnshop.getToolNum() * num;
		UserToolBO userTool = this.toolService.getUserToolBO(userId, systemPawnshop.getToolId());
		
		if (userTool == null)
			throw new ServiceException(HAS_NOT_THIS_TOOL, "用户没有这个道具,userId[" + userId + "],toolId[" + systemPawnshop.getToolId() + "]");
		
		if (userTool.getToolNum() < totalNum)
			throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "用户道具数量不足");
		
		// 扣除用户道具
		if (!this.toolService.reduceTool(userId, systemPawnshop.getToolId(), totalNum, GoodsUseType.REDUCE_SELL_PAWNSHOP))
			throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "用户道具数量不足");
		
		// 添加金币
		if (!this.userService.addGold(userId, totalGold, GoodsUseType.ADD_SELL_TO_PAWNSHOP))
			throw new ServiceException(SystemConstant.FAIL_CODE, "添加金币失败....");
		
		// 增加阵营道具数量
		UserPawnshopInfo userPawnshopInfo = this.userPawnshopInfoDao.getUserPawnshopInfo(user.getCamp(), mallId);
		int remainderNum = userPawnshopInfo.getNum() + totalNum;
		// userPawnshopInfo.setNum(userPawnshopInfo.getNum() + totalNum);
		this.userPawnshopInfoDao.updateUserPawnshopInfo(user.getCamp(), mallId, remainderNum);
			
		PawnshopAction_sellRes res = new PawnshopAction_sellRes();
		CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
		List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(GoodsType.GOLD.intValue + ",0," + totalGold);
		
		goodsList.addAll(GoodsHelper.parseDropGoods(systemPawnshop.getToolType() + "," + userTool.getToolId() + ",-" + totalNum));
		drop.setGoodsList(goodsList);
		res.setDrop(drop);
		return res;
	}
	
}
