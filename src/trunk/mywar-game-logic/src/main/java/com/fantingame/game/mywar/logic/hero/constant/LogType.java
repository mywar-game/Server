package com.fantingame.game.mywar.logic.hero.constant;

/**
 * 日志常量
 * 
 * @author Administrator
 *
 */
public class LogType {
	// 添加英雄
	public static final int HERO_ADD = 1;
	// 英雄加经验，升级
	public static final int HERO_UPGRADE = 2;
	// 英雄被遣散
	public static final int HERO_BE_DISBAND = 3;
	// 英雄升星成功
	public static final int HERO_PROMOTE_STAR_SUCCESS = 4;
	// 英雄升星失败
	public static final int HERO_PROMOTE_STAR_FAIL = 5;
	// 英雄传承增加星数
	public static final int HERO_INHERIT_ADD = 6;
	// 英雄传承减少星数
	public static final int HERO_INHERIT_REDUCE = 7;
	
	// 添加技能
	public static final int SKILL_ADD = 1;
	// 技能升级
	public static final int SKILL_UPGRADE = 2;

	// 装备获取
	public static final int EQUIP_ADD = 1;
	// 装备穿戴
	public static final int EQUIP_WEAR = 2;
	// 装备卸下
	public static final int EQUIP_UNWEAR = 3;
	// 装备镶嵌
	public static final int EQUIP_FILL_IN = 4;
	// 装备附魔
	public static final int EQUIP_MAGIC = 5;
	
	// 宝石获取
	public static final int GEMSTONE_ADD = 1;
	// 宝石的分解
	public static final int GEMSTONE_RESOLVE = 2;
	// 宝石镶嵌
	public static final int GEMSTONE_FILL_IN = 3;
	
	//  战斗日志类型 type
	// 关卡
	public static final int BATTLE_TYPE_SCENCE = 1;
	// 采集
	public static final int BATTLE_TYPE_COLLECT = 2;
	// 采集
	public static final int BATTLE_TYPE_COLLECT_AND_FORCES = 3;
	// PK争霸赛
	public static final int BATTLE_TYPE_PK = 3;
}
