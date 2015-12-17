package com.fantingame.game.mywar.logic.goods.constant;

public class GoodsUseType {
	/*****增加类型***************************************/
    //任务添加
	public static final int ADD_TASK = 1001;
	//注册赠送
	public static final int ADD_CREAT_ROLE = 1002; 
	//打关卡获得
	public static final int ADD_ATTACK_MONSTER = 1003;
	//充值添加
	public static final int ADD_MONEY_PAY = 1004;
	//充值返还
	public static final int ADD_MONEY_RETURN = 1005;
	//首冲双倍
	public static final int ADD_MONEY_DOUBLE = 1006;
	//自动恢复体力
	public static final int ADD_AUTO_RESUME_POWER = 1007;
	//自动恢复活力
	public static final int ADD_AUTO_RESUME_ACTIVITY = 1008;	
	//开宝箱获得
	public static final int ADD_OPEN_GIFT_BOX = 1009;
	//声望添加
	public static final int ADD_PRESTIGE_REWARDS = 1010;
	//背包扩展替换返还
	public static final int ADD_PACKGE_EXTEND_RETURN = 1011;
	// 学习技能获得
	public static final int ADD_STUDY_SKILL= 1012;
	// 升级技能书获得 
	public static final int ADD_UPGRADE_SKILL_BOOK = 1013;
	// 后台添加
	public static final int ADD_BY_ADMIN = 1014;
	// 领取礼包码获得
	public static final int ADD_BY_RECEIVE_GIFTCODE = 1015;
	// 声望邀请英雄获得
	public static final int ADD_PRESTIGE_INVITE = 1016;
	// 探索积分兑换
	public static final int ADD_EXPLORE_INTEGRAL_EXCHANGE = 1017;
	// 探索获得
	public static final int ADD_BY_EXPLORE = 1018;
	// 领取邮件获得
	public static final int ADD_BY_EMAIL = 1019;
	// 从当铺中买入
	public static final int ADD_BUY_FROM_PAWNSHOP = 1020;
	// 卖给当铺得到
	public static final int ADD_SELL_TO_PAWNSHOP = 1021;
	// 从生活技能中得到
	public static final int ADD_BY_LIFE_SKILL = 1022;
	// 战斗彩蛋获得
	public static final int BATTLE_LUCKY_EVENT = 1023;
	// 采集获得奖励
	public static final int ADD_BY_COLLECT = 1024;
	// 采集并遇到野怪获得
	public static final int ADD_BY_COLLECT_AND_ATTACK_MONSTER = 1025;
	// 购买获得
	public static final int ADD_BUYIN_MALL = 1026;
	// 装备道具出售获得
	public static final int ADD_BY_SELL = 1027;
	// 回购得到
	public static final int ADD_BY_BUYBACK = 1028;
	// 荣誉点兑换添加
	public static final int ADD_PK_MALL_EXCHANGE = 1029;
	// 竞技场挑战之后获得奖励
	public static final int ADD_PK_CHALLENGE_REWARD = 1030;
	// 通过锻造得到
	public static final int ADD_TOOL_FOEGE = 1031;
	// 回收得到 
	public static final int ADD_TOOL_RECYCLE = 1032;
	// 英雄升星得到
	public static final int ADD_PROMOTE_HERO_STAR = 1033;
	// 传承增加
	public static final int ADD_HERO_INHERIT = 1034;
	// 购买神秘商品获得
	public static final int ADD_BUY_MYSTERIOUS_MALL = 1035;
	// 宝石合成获得
	public static final int ADD_GEMSTONE_FOEGE = 1036;
	// 宝石分解获得
	public static final int ADD_GEMSTONE_RESOLVE = 1037;
	// 打开战斗宝箱获得
	public static final int ADD_OPEN_BATTLE_BOX = 1038;
	// 领取成就奖励获得
	public static final int ADD_RECEIVE_ACHIEVEMENT_REWARD = 1039;
	// 每月签到获得
	public static final int ADD_LOGIN_REWARD_30 = 1040;
	// 职业经验回退添加
	public static final int ADD_RETURN_JOB_EXP = 1041;
	// 领取活跃度奖励添加
	public static final int ADD_ACTIVITY_TASK = 1042;
	/********减少类型**********************************/
	//打关卡减少
	public static final int REDUCE_ATTACK_MONSTER = 2001;
	//开宝箱消耗
	public static final int REDUCE_OPEN_GIFT_BOX = 2002;
	//背包扩展减道具
	public static final int REDUCE_PACKGE_EXTEND = 2003;
	//学习团长技能
	public static final int REDUCE_STUDY_SKILL = 2004;
	// 升级团长技能
	public static final int REDUCE_UPGRADE_LEADER_SKILL = 2005;
	// 声望邀请英雄
	public static final int REDUCE_PRESTIGE_INVITE = 2006;
	// 刷新探索地图
	public static final int REDUCE_EXPLORE_REFRESH = 2007;
	// 自动刷新地图
	public static final int REDUCE_EXPLORE_AUTO_REFRESH = 2008;
	// 当铺买入
	public static final int REDUCE_PAWNSHOP = 2009;
	// 卖出当铺减少
	public static final int REDUCE_SELL_PAWNSHOP = 2010;
	// 建造生活技能建筑
	public static final int REDUCE_CREATE_LIFE_BUILDER = 2011;
	// 一键刷新日常任务花费
	public static final int REDUCE_ONECLICK_REFRESH_TASK = 2012;
	// 刷新五星日常任务花费
	public static final int REDUCE_REFRESH_FIVE_TASK = 2013;
	// 副本复活减少
	public static final int REDUCE_FORCES_RELIVE = 2014;
	// 购买商品减少
	public static final int REDUCE_BUYIN_MALL = 2015;
	// 出售装备减少
	public static final int REDUCE_BY_SELL = 2016;
	// 回购装备减少
	public static final int REDUCE_BY_BUYBACK = 2017;
	// 召回英雄
	public static final int REDUCE_RECALL_HERO = 2018;
	// 重置挑战等待时间的消耗
	public static final int REDUCE_RESET_WAITINGTIME = 2019;
	// 兑换荣誉商店消耗荣誉点
	public static final int REDUCE_PK_MALL_EXCHANGE = 2020;
	// 刷新荣誉商店消耗荣誉点
	public static final int REDUCE_PK_MALL_REFRESH = 2021;
	// 购买挑战次数消耗钻石
	public static final int REDUCE_PK_BUY_CHALLENGE_TIMES = 2022;
	// 锻造减少
	public static final int REDUCE_TOOL_FORGE = 2023;
	// 回收减少
	public static final int REDUCE_TOOL_RECYCLE = 2024;
	// BOSS战复活消耗
	public static final int REDUCE_BOSS_USER_RELIVE  = 2025;
	// 任务收集的消耗
	public static final int REDUCE_TASK_COLLECT = 2026;
	// 英雄升星减少
	public static final int REDUCE_BY_PROMOTE_HERO_STAR = 2027;
	// 英雄传承
	public static final int REDECE_HERO_INHERIT = 2028;
	// 购买神秘商品消耗
	public static final int REDUCE_BUY_MYSTERIOUS_MALL = 2029;
	// 宝石合成减少
	public static final int REDUCE_GEMSTONE_FOEGE = 2030;
	// 宝石分解
	public static final int REDUCE_GEMSTONE_RESOLVE = 2031;
	// 升级宝石获得
	public static final int REDUCE_GEMSTONE_UPGRADE = 2032;
	// 装备附魔消耗
	public static final int REDUCE_EQUIP_MAGIC = 2033;
	// 丢弃道具消耗
	public static final int REDUCE_ABANDON_TOOL = 2034;
	// 职业解锁减少
	public static final int REDUCE_CAREER_CLEAR = 2035;
	// 英雄进阶消耗
	public static final int REDUCE_HERO_PROMOTE = 2036;
	// 更改用户名称消耗
	public static final int REDUCE_CHANGE_NAME = 2037;
	// 仓库扩展消耗
	public static final int REDUCE_STOREHOUSE_EXTEND = 2038;
	// 创建公会消耗
	public static final int REDUCE_LEGION_CREATE = 2039;
	// 发送跑马灯消耗
	public static final int REDUCE_SEND_MESSGE = 2040;
	// 用户捐献消耗
	public static final int REDUCE_USER_LEGION_INVEST = 2041;
}
