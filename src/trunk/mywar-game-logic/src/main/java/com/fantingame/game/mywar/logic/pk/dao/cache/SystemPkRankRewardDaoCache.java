package com.fantingame.game.mywar.logic.pk.dao.cache;

import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.pk.model.SystemPkRankReward;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SystemPkRankRewardDaoCache extends StaticDataDaoBaseT<String, SystemPkRankReward> {

	@Override
	protected String getCacheKey(SystemPkRankReward v) {
		return v.getType() + "_" + v.getMinRank() + "_" + v.getMaxRank();
	}

	private Map<Integer, List<SystemPkRankReward>> typeMap = Maps.newConcurrentMap();	
	public List<SystemPkRankReward> getSystemPkRankRewardList(int type) {
		if (typeMap.size() == 0)
			init();
		
		if (typeMap.containsKey(type))
			return typeMap.get(type);
			
		List<SystemPkRankReward> rewardList = super.getValueList();
		for (SystemPkRankReward reward : rewardList) {
			if (typeMap.containsKey(reward.getType())) {
				typeMap.get(reward.getType()).add(reward);
			} else {
				List<SystemPkRankReward> list = Lists.newArrayList();
				list.add(reward);
				typeMap.put(reward.getType(), list);
			}
		}
	
		return typeMap.get(type);
	}
	
	/**
	 * 根据类型和排名获取相关奖励
	 * 
	 * @param type
	 * @param rank
	 * @return
	 */
	public SystemPkRankReward getSystemPkRankReward(int type, int rank) {
		if (typeMap.size() == 0)
			init();
		
		List<SystemPkRankReward> list = typeMap.get(type);
		for (SystemPkRankReward reward : list) {
			// 1 为大  10000 为小
			if (reward.getMinRank() >= rank && rank >= reward.getMaxRank())
				return reward;
		}
		
		return null;
	}
	
	public void init() {
		List<SystemPkRankReward> rewardList = super.getValueList();
		for (SystemPkRankReward reward : rewardList) {
			if (typeMap.containsKey(reward.getType())) {
				typeMap.get(reward.getType()).add(reward);
			} else {
				List<SystemPkRankReward> list = Lists.newArrayList();
				list.add(reward);
				typeMap.put(reward.getType(), list);
			}
		}
	}
	
	@Override
	public void reload() {
		super.reload();
		init();
	}
}
