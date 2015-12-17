package com.fantingame.game.gamecenter.util;

/**
 * 分表相关方法
 * 
 * @author CJ
 * 
 */
public class TableUtils {

	/**
	 * 获取银币日志表
	 * 
	 * @param userId
	 * @return
	 */
	public static String getCopperUseLogTable(String userId) {
		long index = getTableCode(userId) % 128;
		return "user_copper_use_log_" + index;
	}

	/**
	 * 获取道具使用日志表
	 * 
	 * @param userId
	 * @return
	 */
	public static String getToolUseLogTable(String userId) {
		long index = getTableCode(userId) % 128;
		return "user_tool_use_log_" + index;
	}

	public static String getHeroLogTable(String userId) {
		long index = getTableCode(userId) % 128;
		index = 0;
		return "user_hero_log_" + index;
	}

	/**
	 * 获取在线日志表名
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserOnlineLogTable(String userId) {
		long index = getTableCode(userId) % 128;
		return "user_online_log_" + index;
	}

	public static long getTableCode(String str) {
		long h = 0;
		char val[] = str.toCharArray();
		for (int i = 0; i < val.length; i++) {
			h = 31 + h + val[i];
		}
		return Math.abs((long) h);
	}

	public static String getUserTowerFloorTable(String userId) {
		long index = getTableCode(userId) % 128;
		return "user_tower_floor_" + index;
	}

	public static String getUserTowerTable(String userId) {
		long index = getTableCode(userId) % 128;
		return "user_tower_" + index;
	}

	public static String getTableName(String userId, String tableNamePrex, int tableCount) {
		long index = getTableCode(userId) % tableCount;
		return tableNamePrex + "_" + index;
	}

	public static String getUserMailTable(String userId) {
		long index = getTableCode(userId) % 128;
		return "user_mail_" + index;
	}

	public static void main(String[] args) {
		System.out.println(getTableName("a2cf9b63e2e148ea866edd5c1db2122e","user_equip",256));
//		System.out.println(getTableName("be7c8cc11a0c42b1bda3e3243d37b2f4","user_hero",128));
//		System.out.println(getTableName("95d527d1bbe24003803f81a3e870c74e","user_hero",128));
//		System.out.println(getTableName("7e1752d872814ae59f10d118b6cdcb8b","user_hero",128));
		System.out.println(getUserMailTable("5b682917bba04a96a2231bd5d19846e6"));
	}
}
