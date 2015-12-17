package com.dataconfig.constant;

public class MMissionConditionConstant {
	//任务模块id
	public static final int MISSION_MODELID = 1000;
	//更新任务消息cmdCode
	public static final int UPDATE_MISSION_NOTICE = 11000;
	//奖励物品类型---兵种
	public static final int PRIZE_GOODS_TYPE_ARMY = 1;
	//建筑任务类型
	public static final int MISSION_TYPE_BUILDING = 1;
	//战役任务类型
	public static final int MISSION_TYPE_BATTLE = 2;
	//生产任务类型
	public static final int MISSION_TYPE_PRODUCE = 3;
	//交互任务类型
	public static final int MISSION_TYPE_COMMUNICATION = 4;
	
	/**任务条件中的目标类型**/
	//建造建筑
	public static final int TARGET_TYPE_BUILD_BUILDING = 1;
	//建造兵种
	public static final int TARGET_TYPE_BUILD_ARMY = 2;
	//人口
	public static final int TARGET_TYPE_PEOPLE = 3;
	//与某类NPC战斗
	public static final int TARGET_TYPE_BATTLE_WITH_NPC_TYPE = 4;
	//与某个npc战斗
	public static final int TARGET_TYPE_BATTLE_WITH_NPC = 5;
	//一场战斗类型（1攻击战，2防御战）
	public static final int TARGET_TYPE_BATTLE_TYPE = 6;
	//放置兵种
	public static final int TARGET_TYPE_PUT_ARMY = 7;
	//放置建筑
	public static final int TARGET_TYPE_PUT_BUILDING = 8;
	//产业类型建筑 生产资源或者农作物
	public static final int TARGET_TYPE_PUT_PRODUCE = 9;
	//收集物资(收租)(0为从侵略领地收取, 产业类的targetNum为物资ID，其他收租发建筑物编号 原油发10001，金币发10002，木材发10003  主矿发10004  铀块发10005 铝块发10006  铜块发10007) 
	public static final int TARGET_TYPE_RETAILS = 10;
	//签订合约（3原油合约 4矿石合约 2伐木合约 ）
	public static final int TARGET_TYPE_COMMEND = 11;
	//大使馆中立
	public static final int TARGET_TYPE_PEACE = 12;
	//售卖主矿（targetNum为1时售卖主矿）
	public static final int TARGET_TYPE_SALE_MAIN_ORE = 13;
	//科技升级(targetNum为兵种小类型)
	public static final int TARGET_TYPE_TECHNOLOGY_UPGRADE = 14;
	//完成科技升级(targetNum为兵种小类型) 
	public static final int TARGET_TYPE_FINISH_TECHNOLOGY_UPGRADE = 15;
	//使用有科技的某种兵
	public static final int TARGET_TYPE_USE_TECHNOLOGY_ARMY = 16;
	//拜访邻居(targetNum默认传1) 
	public static final int TARGET_TYPE_VISIT_FRIEND = 17;
	//新增邻居(targetNum默认传1) 
	public static final int TARGET_TYPE_ADD_FRIEND = 18;
	//加速朋友建筑（targetNum为1）
	public static final int TARGET_TYPE_SPEED_FRIEND_BUILDING = 19;
	//驱逐（1为驱逐自己城市的 2为驱逐好友城市的）
	public static final int TARGET_TYPE_PUSH_ENEMY = 20;
	//侵略（targetNum0没有限制 1用步兵侵略 2用装甲部队侵略 3用空军侵略）
	public static final int TARGET_TYPE_ATTACK = 21;
	//照顾邻居住宅（targetNum为1））
	public static final int TARGET_TYPE_CARE = 22;
	
	/**目标中的targetNum类型**/
	//类型为人口时的targetNum
	public static final int TARGETNUM_FOR_PEOPLE = 0;
	//某类NPC战斗时 步兵 坦克 飞机
	public static final int TARGETNUM_BUBING = 1;
	public static final int TARGETNUM_TANKE = 2;
	public static final int TARGETNUM_FEIJI = 3;
	//战斗的类型分别targetNum
	public static final int TARGETNUM_ATTACK = 1;
	public static final int TARGETNUM_DEFEND = 2;
	//收租时的targetNum
	public static final int TARGETNUM_OIL = 10001;
	public static final int TARGETNUM_GOLD = 10002;
	public static final int TARGETNUM_WOOD = 10003;
	public static final int TARGETNUM_MAINORE = 10004;
	public static final int TARGETNUM_YOU = 10005;
	public static final int TARGETNUM_LV = 10006;
	public static final int TARGETNUM_TONG = 10007;
	//签订合约时的targetNum
	public static final int TARGETNUM_OIL_HEYUE = 1;
	public static final int TARGETNUM_KUANG_HEYUE = 2;
	public static final int TARGETNUM_WOOD_HEYUE = 3;
	public static final int TARGETNUM_KO_HEYUE = 4;
	
	//任务正常完成
	public static final int MISSION_FINISH_NORMAL = 1;
	//任务花钱完成
	public static final int MISSION_FINISH_USEMONEY = 2;
	//任务花钱完成需要的数量
	public static final int MISSION_FINISH_NEEDMONEY = 30;
	
	
}
