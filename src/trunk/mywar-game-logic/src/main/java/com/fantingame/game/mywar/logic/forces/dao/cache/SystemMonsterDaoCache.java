package com.fantingame.game.mywar.logic.forces.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.forces.model.SystemMonster;

public class SystemMonsterDaoCache extends StaticDataDaoBaseT<Integer,SystemMonster>{
    
    public SystemMonster getSystemMonster(int monsterId){
    	return super.getValue(monsterId);
    }
	@Override
	protected Integer getCacheKey(SystemMonster v) {
		return v.getSystemMonsterId();
	}

}
