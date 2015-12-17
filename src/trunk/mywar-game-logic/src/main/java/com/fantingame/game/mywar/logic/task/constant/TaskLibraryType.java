package com.fantingame.game.mywar.logic.task.constant;

public class TaskLibraryType {
//	1	杀掉关卡中的某个怪物	sceneId 场景id  mapId 地图id forcesId：关卡id；monsterId：怪物ID
//	2	找人	sceneId 场景id  mapId 地图ID；npcId npcID
//	3	采集	sceneId 场景id  mapId 地图ID forcesId:关卡id
//	4	通关某个副本	sceneId 场景id  mapId 地图id forcesId：关卡id
//	5	用户到达指定等级	lv：目标等级
//	6	获得指定英雄	heroId:英雄模型ID
//	7	充值	dailyChargeNum:当日充值指定数量
//	8	累计充值	allChargeNum:累计充值数量
//	9	VIP等级	vipLv:到达指定vip等级
//  10  完成一个日常或是什么任务 taskType taskId
//  11  获取任务道具 toolId:1001,toolType:4
//  12  通关任意副本
	
     public static final int KILL_MONSTER = 1 ;
     
     public static final int FIND_NPC = 2;
     
     public static final int COLLECT = 3;
     
     public static final int CROSS_FORCES = 4;
     
     public static final int USER_LEVEL_UP = 5;
     
     public static final int GET_HERO = 6;
     
     public static final int DAILY_CHARGE_NUM = 7;
     
     public static final int ALL_CHARGE_NUM = 8;
     
     public static final int VIP_LEVEL = 9;
     
     public static final int FINISH_ANOTHER_TASK = 10;
     
     public static final int GET_TASK_TOOL = 11;
     
     public static final int CROSS_ANY_FORCES = 12;
}
