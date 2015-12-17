package com.fantingame.game.mywar.logic.mall.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.mall.model.SystemMall;

public class SystemMallDaoCache extends StaticDataDaoBaseT<Integer, SystemMall> {

	@Override
	protected Integer getCacheKey(SystemMall v) {
		return v.getMallId();
	}

	public SystemMall getSystemMall(int mallId) {
		return super.getValue(mallId);
	}
	
}
