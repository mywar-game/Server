package com.fantingame.game.mywar.logic.achievement.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.achievement.model.SystemAchievement;

public class SystemAchievementDaoCache extends StaticDataDaoBaseT<Integer, SystemAchievement> {

	@Override
	protected Integer getCacheKey(SystemAchievement v) {
		return v.getAchievementId();
	}

	public SystemAchievement getSystemAchievement(int achievementId) {
		return super.getValue(achievementId);
	}

	public List<SystemAchievement> getSystemAchievementList() {
		return super.getValueList();
	}
	
}
