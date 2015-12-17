package com.fantingame.game.mywar.logic.hero.dao.cache;



import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroAttribute;

public class SystemHeroAtributeDaoCache extends StaticDataDaoBaseT<Integer, SystemHeroAttribute> {
	
	
	public SystemHeroAttribute getSystemHeroAttribute(int systemHeroId){
		return super.getValue(systemHeroId);
	}
	
	@Override
	protected Integer getCacheKey(SystemHeroAttribute v) {
		return v.getSystemHeroId();
	}
}
