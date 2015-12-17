package com.fantingame.game.mywar.logic.life.dao.cache;

import java.util.ArrayList;
import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseListT;
import com.fantingame.game.mywar.logic.life.model.SystemLifeReward;

public class SystemLifeRewardDaoCache extends StaticDataDaoBaseListT<Integer, SystemLifeReward> {

	@Override
	protected Integer getCacheKey(SystemLifeReward v) {
		return v.getCategory();
	}

	public List<SystemLifeReward> getSystemLifeRewardList(int category, int quality, int level) {
		List<SystemLifeReward> rewardList = super.getValue(category);
		List<SystemLifeReward> list = new ArrayList<SystemLifeReward>();
		
		for (SystemLifeReward reward : rewardList) {
			if (reward.getQuality() == quality && 
					(reward.getMinLevel() <= level && reward.getMaxLevel() >= level))
				list.add(reward);
		}
		
		return list;
	}

}
