package com.fantingame.game.mywar.logic.tool.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.tool.model.SystemMagicReelAttr;

public class SystemMagicReelAttrDaoCache extends StaticDataDaoBaseT<Integer, SystemMagicReelAttr> {

	@Override
	protected Integer getCacheKey(SystemMagicReelAttr v) {
		return v.getReelId();
	}
	
	public SystemMagicReelAttr getSystemMagicReelAttr(int reelId) {
		return super.getValue(reelId);
	}

}
