package com.fantingame.game.mywar.logic.hero.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemHero;
import com.google.common.collect.Lists;

public class SystemHeroDaoCache extends StaticDataDaoBaseT<Integer, SystemHero> {
	
	/**
	 * 获取系统英雄信息
	 * 
	 * @param systemHeroId
	 * @return
	 */
	public SystemHero getSystemHero(int systemHeroId) {
		return super.getValue(systemHeroId);
	}

	@Override
	protected Integer getCacheKey(SystemHero v) {
		return v.getSystemHeroId();
	}
	
	/**
	 * 获取同阵营、同职业的系统英雄
	 * 
	 * @param careerId
	 * @param camp
	 * @return
	 */
	public List<SystemHero> getSameCareerHero(int careerId, int camp) {
		List<SystemHero> systemHeroList = super.getValueList();
		List<SystemHero> list = Lists.newArrayList();
		for (SystemHero hero : systemHeroList) {
			if (hero.getCareerId() == careerId && hero.getNationId() == camp)
				list.add(hero);
		}
		
		return list;
	}
	
	/**
	 * 根据详细的职业id获取英雄
	 * 
	 * @param detailedCareerId
	 * @return
	 */
	public SystemHero getSystemHeroByDetailCareerId(int detailedCareerId) {
		List<SystemHero> systemHeroList = super.getValueList();
		for (SystemHero hero : systemHeroList) {
			if (hero.getDetailedCareerId() == detailedCareerId)
				return hero;
		}
		
		return null;
	}
}
