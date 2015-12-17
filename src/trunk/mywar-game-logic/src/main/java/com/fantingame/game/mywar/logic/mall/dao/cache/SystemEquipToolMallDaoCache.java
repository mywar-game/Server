package com.fantingame.game.mywar.logic.mall.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.mall.model.SystemEquipToolMall;

public class SystemEquipToolMallDaoCache extends StaticDataDaoBaseT<Integer, SystemEquipToolMall> {

	@Override
	protected Integer getCacheKey(SystemEquipToolMall v) {
		return v.getMallId();
	}

	public SystemEquipToolMall getSystemEquipToolMall(int mallId) {
		return super.getValue(mallId);
	}
	
}
