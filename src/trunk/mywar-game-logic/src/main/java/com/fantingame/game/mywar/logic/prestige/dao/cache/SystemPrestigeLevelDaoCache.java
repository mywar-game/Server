package com.fantingame.game.mywar.logic.prestige.dao.cache;

import java.util.Collection;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.prestige.model.SystemPrestigeLevel;

public class SystemPrestigeLevelDaoCache extends
		StaticDataDaoBaseT<Integer, SystemPrestigeLevel> {
	private int maxLevel;

	public SystemPrestigeLevel getPrestigeLevel(int exp) {
		SystemPrestigeLevel result = null;
		for (SystemPrestigeLevel systemPrestigeLevel : super.values()) {
			if (result == null) {
				if (systemPrestigeLevel.getExp() <= exp) {
					result = systemPrestigeLevel;
				}
			} else {
				if (systemPrestigeLevel.getExp() <= exp
						&& systemPrestigeLevel.getLevel() > result.getLevel()) {
					result = systemPrestigeLevel;
				}
			}
		}
		return result;
	}

	public SystemPrestigeLevel getLevel(int level) {
		return super.getValue(level);
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	@Override
	protected Integer getCacheKey(SystemPrestigeLevel v) {
		return v.getLevel();
	}

	@Override
	public void startup() throws Exception {
		super.startup();
		Collection<SystemPrestigeLevel> c = super.values();
		for (SystemPrestigeLevel systemPrestigeLevel : c) {
			if (systemPrestigeLevel.getLevel() > maxLevel) {
				maxLevel = systemPrestigeLevel.getLevel();
			}
		}
	}
}
