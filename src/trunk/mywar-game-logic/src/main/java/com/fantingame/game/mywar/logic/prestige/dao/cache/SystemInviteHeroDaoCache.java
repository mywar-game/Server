package com.fantingame.game.mywar.logic.prestige.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.prestige.model.SystemInviteHero;
import com.google.common.collect.Lists;

/**
 * 声望邀请英雄缓存
 * 
 * @author yezp
 */
public class SystemInviteHeroDaoCache extends StaticDataDaoBaseT<Integer, SystemInviteHero> {

	@Override
	protected Integer getCacheKey(SystemInviteHero v) {
		return v.getSystemHeroId();
	}

	public SystemInviteHero getSystemInviteHero(int systemHeroId) {
		return super.getValue(systemHeroId);
	}

	public List<SystemInviteHero> getSystemInviteHeroList(int camp) {
		List<SystemInviteHero> list = Lists.newArrayList();
		List<SystemInviteHero> heroList = super.getValueList();
		
		for (SystemInviteHero hero : heroList) {
			if (hero.getCamp() == camp)
				list.add(hero);
		}
		
		return list;
	}
	
}

