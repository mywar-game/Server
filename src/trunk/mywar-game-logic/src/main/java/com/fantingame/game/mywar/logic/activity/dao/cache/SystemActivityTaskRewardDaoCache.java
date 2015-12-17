package com.fantingame.game.mywar.logic.activity.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.activity.model.SystemActivityTaskReward;

public class SystemActivityTaskRewardDaoCache extends StaticDataDaoBaseT<Integer, SystemActivityTaskReward> {

	@Override
	protected Integer getCacheKey(SystemActivityTaskReward v) {
		return v.getActivityTaskRewardId();
	}
	
	public SystemActivityTaskReward getSystemActivityTaskReward(int activityTaskRewardId) {
		return super.getValue(activityTaskRewardId);
	}
	
	public List<SystemActivityTaskReward> getSystemActivityTaskRewardList() {
		return super.getValueList();
	}

}
