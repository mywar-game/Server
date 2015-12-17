package com.fantingame.game.mywar.logic.forces.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseListT;
import com.fantingame.game.mywar.logic.forces.model.SystemForcesDropTool;

public class SystemForcesDropToolDaoCache extends StaticDataDaoBaseListT<String,SystemForcesDropTool>{
    
    public List<SystemForcesDropTool> getForcesDropList(int forcesId, int forcesType){
    	return super.getValue(forcesId + "_" + forcesType);
    }
    
	@Override
	protected String getCacheKey(SystemForcesDropTool v) {
		return v.getForcesId()+"_"+v.getForcesType();
	}
}
