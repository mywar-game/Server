package com.fantingame.game.mywar.logic.achievement.check;

import java.util.Map;

public interface IAchievementChecker {

	/**
	 * 成就是否命中
	 * 
	 * @param achievementId
	 * @param params
	 * @return
	 */
	public boolean isHit(int achievementId, Map<String, String> params);
	
}
