package com.dataconfig.constant;

public class MissionConstant {
	//任务模块id
	public static final int MISSION_MODELID = 1000;
	/**主线任务**/
	public static final int MISSION_TYPE_MAIN = 1;
	/**支线任务**/
	public static final int MISSION_TYPE_BRANCH = 2;
	/**日常任务**/
	public static final int MISSION_TYPE_DAY = 3;
	
	
	/**任务奖励类型--资源**/
	public static final int MISSION_REWARD_CATEGORY_RESOURCE = 1;
	/**任务奖励类型--装备**/
	public static final int MISSION_REWARD_CATEGORY_EQUIPMENT = 2;
	/**任务奖励类型--道具**/
	public static final int MISSION_REWARD_CATEGORY_TREASURE = 3;
	/**奖励类型为资源时1代表金钱**/
	public static final int MISSION_REWARD_RESOURCE_ID_GOLD = 1;
	/**奖励类型为资源时2代表矿石**/
	public static final int MISSION_REWARD_RESOURCE_ID_ORE = 2;
	/**奖励类型为资源时3代表粮食**/
	public static final int MISSION_REWARD_RESOURCE_ID_FOOD = 3;
	/**奖励类型为资源时4代表钻石**/
	public static final int MISSION_REWARD_RESOURCE_ID_MONEY = 4;
	/**奖励类型为资源时5代表声望**/
	public static final int MISSION_REWARD_RESOURCE_ID_RENOW = 5;
	
	
	/**任务奖励物品类型--固定奖励**/
	public static final int MISSION_REWARD_TYPE_FIXED = 1;
	/**任务奖励物品类型--可选奖励**/
	public static final int MISSION_REWARD_TYPE_CHOICE = 2;
	
	/**任务完成状态**/
	public static final int MISSION_COMPLETE = 2;
	/**任务未完成状态**/
	public static final int MISSION_NORMAL = 1;
	
	public static final String MISSION_OVER = "-1";
	
	/**任务更新的广播消息体**/
	public static final int MISSION_UPDATE_NOTICE = 19999;
	
	
	/**任务完成条件--通关一个pve关卡**/
	public static final int MISSION_TYPE_PVE = 1;
}
