package com.log.constant;

/**
 * 战斗类型
 */
public final class BattleLogType {
	
	/**  */
	private BattleLogType() {
	}

	/** pve */
	public static final int BATTLE_LOG_P_V_E = 1;

	/** ppve */
	public static final int BATTLE_LOG_PP_V_E = 2;

	/** pvp */
	public static final int BATTLE_LOG_P_V_P = 3;

	/** ppvpp */
	public static final int BATTLE_LOG_PP_V_PP = 4;

	/** 掠夺战 */
	public static final int BATTLE_LOG_PL_V_PL = 5;

	/** 跨区域野地战 */
	public static final int BATTLE_LOG_PKL_V_PKL = 6;

	/** 驱逐战 */
	public static final int BATTLE_LOG_EXPEL_V_EXPEL = 7;

	/** 城市保卫战 */
	public static final int BATTLE_LOG_CP_V_CP = 8;
	
	/** pvp */
	public static final int BATTLE_LOG_PWS_PWS = 9;
	
	/** 军团战 */
	public static final int GUILD_BATTLE = 10;

	/** 天梯排位赛 */
	public static final int LP_V_LP = 11;

	/** 12.新增pvp争夺资源车战斗类型 **/
	public static final int P_V_P_R = 12;
	
	/** 13.pvp决斗 **/
	public static final int P_V_P_D = 13;
	
}
