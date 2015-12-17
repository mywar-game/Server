package com.fantingame.game.mywar.logic.legion.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.legion.model.SystemLegionInvest;

public class SystemLegionInvestDaoCache extends StaticDataDaoBaseT<Integer, SystemLegionInvest> {

	@Override
	protected Integer getCacheKey(SystemLegionInvest v) {
		return v.getId();
	}
	
	public SystemLegionInvest getSystemLegionInvest(int id) {
		return super.getValue(id);
	}

}
