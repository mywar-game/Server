package com.adminTool.constant;

/**
 * @author hzy
 * 2012-7-19
 */
public final class AdminToolCMDCode {
	
	/**  */
	private AdminToolCMDCode() {
	}
	
	/** 请求刷新缓存cmdcode*/
	public static final int REFLASH_CACHE = 6001;
	
	/**************************************************     游戏管理员工具60xx      ******************************************************************/
	
	/** 查看用户信息cmdCode **/
	public static final int GET_USER_INFO = 6002;
	
	/** 查看用户建筑cmdCode **/
	public static final int GET_USER_BUILDING = 6003;
	
	/** 查看用户英雄cmdCode **/
	public static final int GET_USER_HERO = 6004;
	
	/** 查看用户装备cmdCode **/
	public static final int GET_USER_EQUIPMENT = 6005;
	
	/** 查看用户道具cmdCode **/
	public static final int GET_USER_TREASURE = 6006;
	
	/** 发送系统邮件cmdCode **/
	public static final int SEND_SYSTEM_MAIL = 6007;
	
	/** 发放装备、道具 **/
	public static final int ISSUE_SOMETHING_TO_USER = 6008;
	
	/** 获取全局总览信息 */
	public static final int GET_GLOBAL_MESSAGE = 6009;
	
	/** 增加用户金币 */
	public static final int ADD_USER_GOLDEN = 6010;

	/** 获得在线玩家数 */
	public static final int GET_ONLINE_USER_AMOUNT = 6011;
	
	/** 改变玩家账号类型 */
	public static final int CHANGE_USER_TYPE = 6012;
	
	/** 获得在线玩家信息 */
	public static final int GET_ONLINE_USER_INFO = 6013;
	
	/**生成高级测试账号**/
	public static final int GENERATE_TEST_ACCOUNT = 6014;
	
	/**生成区域地图**/
	public static final int GENERATE_REGIONAL_MAP = 6015;
	
	/** 修改玩家密码 **/
	public static final int MODIFY_USER_PASSWORD = 6016;
	
	/** 修改玩家密码 **/
	public static final int GET_HUGE_DIAMOND_USER_LIST = 6017;
	
	/** 修改玩家装备技能 **/
	public static final int MODIFY_USER_EQUIPMENT_SKILL = 6018;
	
	/** 查看玩家科技信息 **/
	public static final int GET_USER_TECHNOLOGY_INFO = 6019;
	
	/** 修改玩家任务 **/
	public static final int UPDATE_USER_MISSION = 6020;
	
	/** 查看玩家排行快照 **/
	public static final int GET_USER_RANK_SNAPSHOT = 6021;
	
	/** 查看task执行信息 **/
	public static final int TASK_MANAGETQUERY_TASK = 6022;
	
	/** 查看task执行次数 **/
	public static final int MSG_MANAGETQUERY_TASK = 6023;
	
	/** 查看MemCache状态 **/
	public static final int MEM_CACHE_INFO = 6024;
	
	/** 修改玩家竞技场积分和排行 **/
	public static final int MODIFY_USER_ARENA_SCORE_AND_RANK = 6025;
	
	/**memcache使用记录**/
	public static final int MEMCACHE_USE_RECORD = 6026;

	
	/** 获得某些用户的信息 **/
	public static final int GET_SOME_USER_INFO = 6027;
	
	/** 修改用户的某些数据 **/
	public static final int MODIFY_USER_SOME_DATA = 6028;
	
	/**************************************************     统计61xx      ******************************************************************/
	
	/** 所有军团及军团成员 **/
	public static final int ALL_GUILD_GUILDMEMBERS = 6101;
	
	/** 获得游戏现有金币数 **/
	public static final int GET_DIAMOND_AMOUNT = 6102;
	
	/**************************************************     日志62xx      ******************************************************************/

	
	/** 找回玩家英雄 **/
	public static final int GET_HERO_BACK = 6202;
	
	/** 找回玩家装备 **/
	public static final int GET_EQUIPMENT_BACK = 6203;
	
	/**关服前操作**/
	public static final int SERVER_OPERATE_BEFORE_SHUTDOWN = 6204;
	
	/** 玩家禁言或者封号的操作 **/
	public static final int TREATMENT_USER_CHAT = 6205;
	
	/** 查询战斗服务器战斗信息 **/
	public static final int QUERY_BATTLE_SERVER_BATTLE_INFO = 6206;
	/** 查询排位赛服务器战斗信息**/
	public static final int QUERY_QUALIFY_SERVER_BATTLE_INFO = 6207;
	
	
	/** 修改玩家任务 **/
	public static final int UPDATE_USER_LEVEL = 6208;
	
	

}
