package com.fantingame.game.mywar.logic.lucky.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseListT;
import com.fantingame.game.mywar.logic.lucky.model.SystemRandomReward;

public class SystemRandomRewardDaoCache extends StaticDataDaoBaseListT<Integer, SystemRandomReward> {

	@Override
	protected Integer getCacheKey(SystemRandomReward v) {
		return v.getEventId();
	}
	
	public List<SystemRandomReward> getSystemRandomReward(int eventId) {
		return super.getValue(eventId);
	}

}
