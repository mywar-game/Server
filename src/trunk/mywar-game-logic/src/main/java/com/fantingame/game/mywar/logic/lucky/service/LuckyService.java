package com.fantingame.game.mywar.logic.lucky.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.utils.RandomUtils;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.mywar.logic.forces.service.ForcesService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.lucky.constant.LuckyEvent;
import com.fantingame.game.mywar.logic.lucky.dao.cache.SystemRandomRewardDaoCache;
import com.fantingame.game.mywar.logic.lucky.model.SystemRandomReward;
import com.fantingame.game.mywar.logic.tool.constant.ToolType;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.google.common.collect.Lists;

public class LuckyService {

	@Autowired
	private ForcesService forcesService;
	@Autowired
	private SystemRandomRewardDaoCache systemRandomRewardDaoCache;
	@Autowired
	private GoodsDealService goodDealService;
	@Autowired
	private UserService userService;
	@Autowired
	private ToolService toolService;
	
	/**
	 * 触发战斗的随机事件
	 * 
	 * @param userId
	 * @return
	 */
	public CommonGoodsBeanBO getBattleLuckyReward(String userId) {
		boolean isBattling = forcesService.isBattling(userId);
		CommonGoodsBeanBO drop = new CommonGoodsBeanBO();
		if (!isBattling) 
			return drop;
		
		int random = RandomUtils.getRandomNum(1, 10000);
		List<SystemRandomReward> rewardList = this.systemRandomRewardDaoCache.getSystemRandomReward(LuckyEvent.fight_event);
		if (rewardList != null && rewardList.size() > 0) {
			for (SystemRandomReward reward : rewardList) {
				if (reward.getLowerNum() <= random && reward.getMaxNum() >= random) {
					int num = RandomUtils.getRandomNum(reward.getMinNum(), reward.getMaxNum());
					drop.setGoodsList(GoodsHelper.parseDropGoods(reward.getToolType() + "," + reward.getToolId() + "," + num));
				}
			}
		}		
		
		if (drop.getGoodsList().size() > 0 && userService.checkBagLimit(userId, drop.getGoodsList()))
			return goodDealService.sendGoods(userId, drop.getGoodsList(), GoodsUseType.BATTLE_LUCKY_EVENT);
		
		return new CommonGoodsBeanBO();
	}
	
	/**
	 * 团长技能是否double
	 * 
	 * @return
	 */
	public boolean isSkillExpDouble() {
		List<SystemRandomReward> rewardList = this.systemRandomRewardDaoCache.getSystemRandomReward(1002);
		int random = RandomUtils.getRandomNum(1, 10000);
		if (rewardList != null && rewardList.size() > 0) {
			for (SystemRandomReward reward : rewardList) {
				if (reward.getLowerNum() <= random && reward.getMaxNum() >= random)
					return true;
			}
		}		
			
		return false;
	}
	
	/**
	 * 获取采集的怪物
	 * 
	 * @return
	 */
	public List<GoodsBeanBO> getCollectFight(String userId) {
		List<SystemRandomReward> rewardList = this.systemRandomRewardDaoCache.getSystemRandomReward(LuckyEvent.collection_event);
		int random = RandomUtils.getRandomNum(1, 10000);
		
		List<GoodsBeanBO> goodsList = null;
		if (rewardList != null && rewardList.size() > 0) {
			for (SystemRandomReward reward : rewardList) {
				if (reward.getLowerNum() <= random && reward.getUpperNum() >= random) {
					int num = RandomUtils.getRandomNum(reward.getMinNum(), reward.getMaxNum());
					goodsList = GoodsHelper.parseDropGoods(reward.getToolType() + "," + reward.getToolId() + "," + num);
					break;
				}
			}
		}		
		
		if (goodsList != null) {
			List<GoodsBeanBO> list = Lists.newArrayList();
			for (GoodsBeanBO good : goodsList) {
				if (good.getGoodsType() != GoodsType.tool.intValue)
					list.add(good);				
				
				SystemTool systemTool = this.toolService.getSystemTool(good.getGoodsId());
				if (systemTool.getType() != ToolType.GIFT_BOX)
					list.add(good);
				
				// 开一个掉落宝箱
				int keyId = toolService.getGiftBoxKey(good.getGoodsId());				
				if (keyId != 0) 
					list.add(good);
				
				List<GoodsBeanBO> beanList = this.toolService.openDropGiftBox(userId, good.getGoodsId());
				if (beanList != null && beanList.size() != 0)
					list.addAll(beanList);
			}
			
			return list;
		}
		
		return null;
	}
}
