package com.fantingame.game.mywar.logic.explore.dao.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseListT;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.mywar.logic.explore.model.SystemExploreReward;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;

/**
 * 探索奖励
 * 
 * @author yezp
 */
public class SystemExploreRewardDaoCache extends StaticDataDaoBaseListT<Integer, SystemExploreReward> {

	private Map<Integer, Integer> mapAndSystemHeroId = new HashMap<Integer, Integer>();
	private Map<Integer, List<GoodsBeanBO>> goodsMap = new HashMap<Integer, List<GoodsBeanBO>>();
	private List<SystemExploreReward> exploreRewardList = new ArrayList<SystemExploreReward>();
	
	@Override
	protected Integer getCacheKey(SystemExploreReward v) {
		return v.getMapId();
	}
	
	public List<SystemExploreReward> getSystemExploreRewardList(int mapId) {
		return super.getValue(mapId);
	}

	public List<GoodsBeanBO> getSystemExploreGoodsBeanBOList(int mapId) {
		if (goodsMap.containsKey(mapId))
			return goodsMap.get(mapId);
		
		for (SystemExploreReward reward : exploreRewardList) {
			if (goodsMap.containsKey(reward.getMapId())) {
				goodsMap.get(reward.getMapId()).addAll(GoodsHelper.parseDropGoods(reward.getRewards()));
			} else {
				goodsMap.put(reward.getMapId(), GoodsHelper.parseDropGoods(reward.getRewards()));
			}
		}
		return goodsMap.get(mapId);
	}
	
	public Map<Integer, Integer> getMapAndSystemHeroId() {
		if (exploreRewardList.size() != 0 && mapAndSystemHeroId.size() != 0)
			return mapAndSystemHeroId;
		
		getAllList();
		List<GoodsBeanBO> list = new ArrayList<GoodsBeanBO>();
		for (SystemExploreReward exploreReward : exploreRewardList) {
			if (exploreReward.getRewards() == null || exploreReward.getRewards().equals(""))
				continue;
			
			list.addAll(GoodsHelper.parseDropGoods(exploreReward.getRewards()));
		}
		
		for (GoodsBeanBO good : list) {
			if (good.getGoodsType() != GoodsType.Hero.intValue)
				continue;
			
			mapAndSystemHeroId.put(good.getGoodsId(), good.getGoodsId());
		}		
		return mapAndSystemHeroId;
	}
	
	@Override
	public void reload() {
		super.reload();
		mapAndSystemHeroId.clear();
		getAllList();		
		goodsMap.clear();
	}
	
	private List<SystemExploreReward> getAllList() {
		exploreRewardList.clear();
		return exploreRewardList = super.getAllDBData();
	}
}
