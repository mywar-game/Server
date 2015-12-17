package com.fantingame.game.mywar.logic.legion.dao.cache;

import java.util.Collection;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.legion.model.SystemLegionLevel;

public class SystemLegionLevelDaoCache extends StaticDataDaoBaseT<Integer, SystemLegionLevel> {

	private int maxLevel;
	
	@Override
	protected Integer getCacheKey(SystemLegionLevel v) {
		return v.getLevel();
	}

	public SystemLegionLevel getSystemLegionLevel(int level) {
		return super.getValue(level);
	}
	
	public int getMaxLevel() {
		return maxLevel;
	}
	
	public SystemLegionLevel getSystemLegionLevelByExp(int exp) {
		SystemLegionLevel result = null;
		for(SystemLegionLevel systemLegionLevel : super.values()){
			if(result == null) {
				if(systemLegionLevel.getExp() <= exp)
					result = systemLegionLevel;
				
			} else {
				if (systemLegionLevel.getExp() <= exp && systemLegionLevel.getLevel() > result.getLevel())
					result = systemLegionLevel;
			}
		}
		
		return result;
	}
	
	@Override
	public void startup() throws Exception {
		super.startup();
		Collection<SystemLegionLevel> c = super.values();
		for(SystemLegionLevel systemLegionLevel : c){
			if(systemLegionLevel.getLevel() > maxLevel){
				maxLevel = systemLegionLevel.getLevel();
			}
		}		
	}
	
}
