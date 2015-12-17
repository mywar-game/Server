package com.system.constant;

public class ServerConstant {
	/** 主服务器起始系统编号 **/
	public static final int GAME_SERVER_BEGIN_NUMBER = 10001;
	/** 聊天服务器起始系统编号 **/
	public static final int CHAT_SERVER_BEGIN_NUMBER = 20001;
	/** 战斗服务器起始系统编号 **/
	public static final int BATTLE_SERVER_BEGIN_NUMBER = 30001;
	/** 排位赛服务器起始系统编号 **/
	public static final int ORDER_SERVER_BEGIN_NUMBER = 40001;
	/** 主服务器起始系统编号 **/
	public static final String GAME_SERVER_NAME = "游";
	/** 聊天服务器起始系统编号 **/
	public static final String CHAT_SERVER_NAME = "聊";
	/** 战斗服务器起始系统编号 **/
	public static final String BATTLE_SERVER_NAME = "战";
	/** 排位赛服务器起始系统编号 **/
	public static final String ORDER_SERVER_NAME = "排";
	/** 连接符 **/
	public static final String CONNECT = "-";
	/** 默认游戏服务器端口号 **/
	public static final int GAME_SERVER_PORT = 12368;
	/** 默认游戏服务器web端口号 **/
	public static final int GAME_SERVER_WEB_PORT = 80;
	/** 主服务器 **/
	public static final int GAME_SERVER_TYPE = 1;
	/** 聊天服务器 **/
	public static final int CHAT_SERVER_TYPE = 2;
	/** 战斗服务器 **/
	public static final int BATTLE_SERVER_TYPE = 3;
	/** 排位赛服务器 **/
	public static final int ORDER_SERVER_TYPE = 4;
	/** 礼包数据库key起始 **/
	public static final int GIFT_DBKEY_BEGIN_NUMBER = 1000;

	/**
	 * 服务器状态开启: 1
	 */
	public static final int SERVER_STATUS_OPEN = 1;

	/**
	 * 服务器状态关闭: 1
	 */
	public static final int SERVER_STATUS_CLOSE = 0;
	
	
	/**命令正在执行**/
	public static final int EXECUTING = 1;
	/**命令执行成功**/
	public static final int EXECUTE_SUC = 2;
	/**命令执行失败**/
	public static final int EXECUTE_FAIL = 3;
	
}
