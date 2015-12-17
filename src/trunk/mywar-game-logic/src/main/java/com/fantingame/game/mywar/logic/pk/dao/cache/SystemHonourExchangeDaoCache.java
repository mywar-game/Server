package com.fantingame.game.mywar.logic.pk.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.pk.model.SystemHonourExchange;

public class SystemHonourExchangeDaoCache extends StaticDataDaoBaseT<Integer, SystemHonourExchange> {

	@Override
	protected Integer getCacheKey(SystemHonourExchange v) {
		return v.getMallId();
	}
	
	public SystemHonourExchange getSystemHonourExchange(int mallId) {
		return super.getValue(mallId);
	}
	
	public List<SystemHonourExchange> getAllList() {
		return super.getValueList();
	}

}
