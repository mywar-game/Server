package com.fantingame.game.mywar.logic.goods.constant;

/**
 * 游戏内物品定义的类型
 * 
 * @author Administrator
 *
 */
public enum GoodsType {
	tool(1, "道具"), 
	equip(2, "装备"), 
	GOLD(3, "金币"), // 金币
	MONEY(4, "钱"), // 钱
	Exp(5, "团队经验"), // 经验
	Hero(7, "英雄"), // 英雄 
	JobExp(8, "职业经验"), 
	honour(9, "荣誉"), 
	packExtendTimes(10, "背包扩展次数"), 
	Level(11, "用户等级"),  
	HeroExp(13, "英雄经验"),
	Gemstone(14, "宝石"),
	HeroSkill(20, "英雄技能");

	public int intValue;// int值
	public String remark;// 注释

	private GoodsType(int intValue, String remark) {
		this.intValue = intValue;
		this.remark = remark;
	}
}
