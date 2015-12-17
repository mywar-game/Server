package com.fantingame.game.mywar.logic.prestige.dao.cache;


import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.prestige.model.SystemPrestigeRewards;

public class SystemPrestigeRewardsDaoCache extends StaticDataDaoBaseT<Integer, SystemPrestigeRewards> {
    
	public SystemPrestigeRewards getPrestigeRewardsById(int id){
		return super.getValue(id);
	}
	
	@Override
	protected Integer getCacheKey(SystemPrestigeRewards v) {
		return v.getId();
	}

}
