package com.fantingame.game.mywar.logic.equip.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.equip.model.SystemEquipMagic;

public class SystemEquipMagicDaoCache extends StaticDataDaoBaseT<Integer, SystemEquipMagic> {

	@Override
	protected Integer getCacheKey(SystemEquipMagic v) {
		return v.getReelId();
	}
	
	public SystemEquipMagic getSystemEquipMagic(int reelId){
		return super.getValue(reelId);
	}

}
