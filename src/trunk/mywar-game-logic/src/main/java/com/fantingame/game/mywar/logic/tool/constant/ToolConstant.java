package com.fantingame.game.mywar.logic.tool.constant;

import java.util.Map;

import com.google.common.collect.Maps;

public class ToolConstant {

	private static ToolConstant ins;

	public static ToolConstant instance() {
		synchronized (ToolConstant.class) {
			if (ins == null)
				ins = new ToolConstant();
		}

		return ins;
	}

	/**
	 * 改名卡
	 */
	public final static int CHANGE_USER_NAME_CARD = 1363;
	
	/**
	 * 魂能回退
	 */
	public final static int RETURN_JOB_EXP_TOOL_ID = 4009;
	
	public final static int JUNIOR_LUCKY_STONE_ID = 1369;
	public final static int MIDDLE_LUCKY_STONE_ID = 1370;
	public final static int SENIOR_LUCKY_STONE_ID = 1371;
	
	/**
	 * 小喇叭
	 */
	public final static int SEND_MSG_TOOL_ID = 1372;

	private Map<Integer, Integer> luckyStoneMap = Maps.newConcurrentMap();

	public ToolConstant() {
		luckyStoneMap.put(JUNIOR_LUCKY_STONE_ID, JUNIOR_LUCKY_STONE_ID);
		luckyStoneMap.put(MIDDLE_LUCKY_STONE_ID, MIDDLE_LUCKY_STONE_ID);
		luckyStoneMap.put(SENIOR_LUCKY_STONE_ID, SENIOR_LUCKY_STONE_ID);
	}

	public Map<Integer, Integer> getLuckyStoneMap() {
		return luckyStoneMap;
	}

	public void setLuckyStoneMap(Map<Integer, Integer> luckyStoneMap) {
		this.luckyStoneMap = luckyStoneMap;
	}

}
