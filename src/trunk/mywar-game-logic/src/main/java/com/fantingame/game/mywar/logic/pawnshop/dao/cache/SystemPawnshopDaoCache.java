package com.fantingame.game.mywar.logic.pawnshop.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.pawnshop.model.SystemPawnshop;

public class SystemPawnshopDaoCache extends StaticDataDaoBaseT<Integer, SystemPawnshop> {

	@Override
	protected Integer getCacheKey(SystemPawnshop v) {
		return v.getMallId();
	}
	
	public SystemPawnshop getSystemPawnshop(int id) {
		return super.getValue(id);
	}

	public List<SystemPawnshop> getAllList() {
		return super.getValueList();
	}
	
}
