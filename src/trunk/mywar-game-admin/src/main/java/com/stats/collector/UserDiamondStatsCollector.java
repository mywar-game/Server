package com.stats.collector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adminTool.service.UserService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.constant.UserGoldLogCategory;
import com.log.service.UserGoldLogService;
import com.log.service.UserLoginLogService;
import com.stats.bo.UserDiamondStats;
import com.stats.common.GoodUseType;
import com.stats.service.UserDiamondStatsService;

/**
 * 玩家钻石统计Collector
 * @author hzy
 * 2012-4-20
 */
public class UserDiamondStatsCollector implements Collector {
	
	@Override
	public void execute(Date date) {
		LogSystem.info("玩家钻石统计 Collector开始");
		UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		//获得map
		Map<Integer, Integer> map1 = userGoldLogService.findEveryTypeAmount(UserGoldLogCategory.RECEIVE,dates);
		Map<Integer, Integer> map2 = userGoldLogService.findEveryTypeAmount(UserGoldLogCategory.CONSUME, dates);
		List<Object> tempActiveUserIds = userLoginLogService.findDayActiveUserId(dates);
		UserDiamondStats stats = new UserDiamondStats();
		stats.setSysNum(CustomerContextHolder.getSystemNum());
		stats.setTime(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
		
		/** 产出 **/
		stats.setAddTask(map1.get(GoodUseType.ADD_TASK) == null ? 0 : map1.get(GoodUseType.ADD_TASK));
		stats.setAddCreatRole(map1.get(GoodUseType.ADD_CREAT_ROLE) == null ? 0 : map1.get(GoodUseType.ADD_CREAT_ROLE));
		stats.setAddAttackMonster(map1.get(GoodUseType.ADD_ATTACK_MONSTER) == null ? 0 : map1.get(GoodUseType.ADD_ATTACK_MONSTER));
		stats.setAddMoneyPay(map1.get(GoodUseType.ADD_MONEY_PAY) == null ? 0 : map1.get(GoodUseType.ADD_MONEY_PAY));
		stats.setAddMoneyReturn(map1.get(GoodUseType.ADD_MONEY_RETURN) == null ? 0 : map1.get(GoodUseType.ADD_MONEY_RETURN));
		stats.setAddMoneyDouble(map1.get(GoodUseType.ADD_MONEY_DOUBLE) == null ? 0 : map1.get(GoodUseType.ADD_MONEY_DOUBLE));
//		stats.setAddAutoResumePower(map1.get(GoodUseType.add_));
		stats.setAddOpenGiftBox(map1.get(GoodUseType.ADD_OPEN_GIFT_BOX) == null ? 0 : map1.get(GoodUseType.ADD_OPEN_GIFT_BOX));
		stats.setAddPrestigeRewards(map1.get(GoodUseType.ADD_PRESTIGE_REWARDS) == null ? 0 : map1.get(GoodUseType.ADD_PRESTIGE_REWARDS));
//		stats.setAddPackgeExtendReturn(map1.get(GoodUseType.add_p));
		stats.setAddStudySkill(map1.get(GoodUseType.ADD_STUDY_SKILL) == null ? 0 : map1.get(GoodUseType.ADD_STUDY_SKILL));
		stats.setAddUpgradeSkillBook(map1.get(GoodUseType.ADD_UPGRADE_SKILL_BOOK) == null ? 0 : map1.get(GoodUseType.ADD_UPGRADE_SKILL_BOOK));
		stats.setAddByAdmin(map1.get(GoodUseType.ADD_BY_ADMIN) == null ? 0 : map1.get(GoodUseType.ADD_BY_ADMIN));
		stats.setAddByReceiveGiftcode(map1.get(GoodUseType.ADD_BY_RECEIVE_GIFTCODE) == null ? 0 : map1.get(GoodUseType.ADD_BY_RECEIVE_GIFTCODE));
		stats.setAddPrestigeInvite(map1.get(GoodUseType.ADD_PRESTIGE_INVITE) == null ? 0 : map1.get(GoodUseType.ADD_PRESTIGE_INVITE));
		stats.setAddExploreIntegralExchange(map1.get(GoodUseType.ADD_EXPLORE_INTEGRAL_EXCHANGE) == null ? 0 : map1.get(GoodUseType.ADD_EXPLORE_INTEGRAL_EXCHANGE));
		stats.setAddByExplore(map1.get(GoodUseType.ADD_BY_EXPLORE) == null ? 0 : map1.get(GoodUseType.ADD_BY_EXPLORE));
		stats.setAddByEmail(map1.get(GoodUseType.ADD_BY_EMAIL) == null ? 0 : map1.get(GoodUseType.ADD_BY_EMAIL));
		stats.setAddBuyFromPawnshop(map1.get(GoodUseType.ADD_BUY_FROM_PAWNSHOP) == null ? 0 : map1.get(GoodUseType.ADD_BUY_FROM_PAWNSHOP));
		stats.setAddSellToPawnshop(map1.get(GoodUseType.ADD_SELL_TO_PAWNSHOP) == null ? 0 : map1.get(GoodUseType.ADD_SELL_TO_PAWNSHOP));
		stats.setAddByLifeSkill(map1.get(GoodUseType.ADD_BY_LIFE_SKILL) == null ? 0 : map1.get(GoodUseType.ADD_BY_LIFE_SKILL));
		stats.setBattleLuckyEvent(map1.get(GoodUseType.BATTLE_LUCKY_EVENT) == null ? 0 : map1.get(GoodUseType.BATTLE_LUCKY_EVENT));
		stats.setAddByCollect(map1.get(GoodUseType.ADD_BY_COLLECT) == null ? 0 : map1.get(GoodUseType.ADD_BY_COLLECT));
		stats.setAddByCollectAndAttackMonster(map1.get(GoodUseType.ADD_BY_COLLECT_AND_ATTACK_MONSTER) == null ? 0 : map1.get(GoodUseType.ADD_BY_COLLECT_AND_ATTACK_MONSTER));
		stats.setAddBuyinMall(map1.get(GoodUseType.ADD_BUYIN_MALL) == null ? 0 : map1.get(GoodUseType.ADD_BUYIN_MALL));
		stats.setAddBySell(map1.get(GoodUseType.ADD_BY_SELL) == null ? 0 : map1.get(GoodUseType.ADD_BY_SELL));
		stats.setAddByBuyback(map1.get(GoodUseType.ADD_BY_BUYBACK) == null ? 0 : map1.get(GoodUseType.ADD_BY_BUYBACK));
		
		/*** 消耗 **/
		stats.setReduceAttackMonster(map2.get(GoodUseType.REDUCE_ATTACK_MONSTER) == null ? 0 : map2.get(GoodUseType.REDUCE_ATTACK_MONSTER));
		stats.setReduceOpenGiftBox(map2.get(GoodUseType.REDUCE_OPEN_GIFT_BOX) == null ? 0 : map2.get(GoodUseType.REDUCE_OPEN_GIFT_BOX));
		stats.setReducePackgeExtend(map2.get(GoodUseType.REDUCE_PACKGE_EXTEND) == null ? 0 : map2.get(GoodUseType.REDUCE_PACKGE_EXTEND));
		stats.setReduceStudySkill(map2.get(GoodUseType.REDUCE_STUDY_SKILL) == null ? 0 : map2.get(GoodUseType.REDUCE_STUDY_SKILL));
		stats.setReduceUpgradeLeaderSkill(map2.get(GoodUseType.REDUCE_UPGRADE_LEADER_SKILL) == null ? 0 : map2.get(GoodUseType.REDUCE_UPGRADE_LEADER_SKILL));
		stats.setReducePrestigeInvite(map2.get(GoodUseType.REDUCE_PRESTIGE_INVITE) == null ? 0 : map2.get(GoodUseType.REDUCE_PRESTIGE_INVITE));
		stats.setReduceExploreRefresh(map2.get(GoodUseType.REDUCE_EXPLORE_REFRESH) == null ? 0 : map2.get(GoodUseType.REDUCE_EXPLORE_REFRESH));
		stats.setReduceExploreAutoRefresh(map2.get(GoodUseType.REDUCE_EXPLORE_AUTO_REFRESH) == null ? 0 : map2.get(GoodUseType.REDUCE_EXPLORE_AUTO_REFRESH));
		stats.setReducePawnshop(map2.get(GoodUseType.REDUCE_PAWNSHOP) == null ? 0 : map2.get(GoodUseType.REDUCE_PAWNSHOP));
		stats.setReduceSellPawnshop(map2.get(GoodUseType.REDUCE_SELL_PAWNSHOP) == null ? 0 : map2.get(GoodUseType.REDUCE_SELL_PAWNSHOP));
		stats.setReduceCreateLifeBuilder(map2.get(GoodUseType.REDUCE_CREATE_LIFE_BUILDER) == null ? 0 : map2.get(GoodUseType.REDUCE_CREATE_LIFE_BUILDER));
		stats.setReduceOneclickRefreshTask(map2.get(GoodUseType.REDUCE_ONECLICK_REFRESH_TASK) == null ? 0 : map2.get(GoodUseType.REDUCE_ONECLICK_REFRESH_TASK));
		stats.setReduceRefreshFiveTask(map2.get(GoodUseType.REDUCE_REFRESH_FIVE_TASK) == null ? 0 : map2.get(GoodUseType.REDUCE_REFRESH_FIVE_TASK));
		stats.setReduceForcesRelive(map2.get(GoodUseType.REDUCE_FORCES_RELIVE) == null ? 0 : map2.get(GoodUseType.REDUCE_FORCES_RELIVE));
		stats.setReduceBuyinMall(map2.get(GoodUseType.REDUCE_BUYIN_MALL) == null ? 0 : map2.get(GoodUseType.REDUCE_BUYIN_MALL));
		stats.setReduceBySell(map2.get(GoodUseType.REDUCE_BY_SELL) == null ? 0 : map2.get(GoodUseType.REDUCE_BY_SELL));
		stats.setReduceByBuyback(map2.get(GoodUseType.REDUCE_BY_BUYBACK) == null ? 0 : map2.get(GoodUseType.REDUCE_BY_BUYBACK));
		stats.setReduceRecallHero(map2.get(GoodUseType.REDUCE_RECALL_HERO) == null ? 0 : map2.get(GoodUseType.REDUCE_RECALL_HERO));
		
		// 添加钻石采集
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		List<String> userIds = new ArrayList<String>();
		for (Object obj : tempActiveUserIds) {
			userIds.add((String) obj);
		}
		List<Object> leftDiamondObj = userService.findLeftDiamond();
		long leftDiamond = 0;
		if (leftDiamondObj != null && leftDiamondObj.size() != 0) {
			leftDiamond = Long.parseLong(((Object) leftDiamondObj.get(0)).toString());
		}
		stats.setLeftDiamond(leftDiamond);
		
		List<Object> leftDiamondObj2 = userService.findLeftDiamond(userIds);
		long leftDiamond2 = 0;
		if (leftDiamondObj2 != null && leftDiamondObj2.size() != 0) {
			leftDiamond2 = Long.parseLong(((Object) leftDiamondObj2.get(0)).toString());
		}
		stats.setActivityDiamond(leftDiamond2);
		
		UserDiamondStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserDiamondStatsService.class);
		statsService.save(stats);
		LogSystem.info("玩家钻石统计 Collector完毕");
		//测试数据是否正确
		try {
//			userGoldLogService.test(dates);
		} catch (Exception e) {
			LogSystem.error(e, "");
		}
	}

}
