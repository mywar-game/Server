package com.fantingame.game.mywar.logic.forces.dao.cache;


import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.forces.model.SystemForcesMonster;


public class SystemForcesMonsterDaoCache extends StaticDataDaoBaseT<String, SystemForcesMonster>{

	@Override
	protected String getCacheKey(SystemForcesMonster v) {
		return v.getForcesId() + "_" + v.getForcesType();
	}

	public SystemForcesMonster getSystemForcesMonster(int forcesId, int forcesType) {
		return super.getValue(forcesId + "_" + forcesType);
	}
  
}
