package com.log.constant; 

public enum UserTreasureLogType {
	
	MALL_BUY_TREASUR, //商城购买道具
	BATTLE_REWARD_TREASURE, //战斗获得道具
	TASK_REWARD_TREASURE, //任务获得道具
	NPC_REWARD_TREASURE, //npc收获获得道具
	EQUIPMENT_STMELTING_ADD, //装备熔炼增加
	EQUIPMENT_STMELTING_DESC, //装备熔炼扣减
	FRIEND_COLLECTION, //好友一键收取
	MAIL_REWARD, //邮件收取奖励	
	MALL_USE_TREASURE; //商城使用道具
	
	public static String getUserTreasureLogType(UserTreasureLogType type) {
		switch (type) {
			case MALL_BUY_TREASUR:
				return "MALL_BUY_TREASUR"; 
			case BATTLE_REWARD_TREASURE:
				return "BATTLE_REWARD_TREASURE"; 
			case TASK_REWARD_TREASURE:
				return "TASK_REWARD_TREASURE"; 
			case NPC_REWARD_TREASURE:
				return "NPC_REWARD_TREASURE"; 
			case EQUIPMENT_STMELTING_ADD:
				return "USER_EQUIPMENT_STMELTING_ADD"; 
			case EQUIPMENT_STMELTING_DESC:
				return "USER_EQUIPMENT_STMELTING_DESC"; 
			case FRIEND_COLLECTION:
				return "FRIEND_COLLECTION"; 
			case MAIL_REWARD:
				return "MAIL_REWARD"; 
			case MALL_USE_TREASURE:
				return "MALL_USE_TREASURE"; 
		}
		return null; 
	}
	
}
