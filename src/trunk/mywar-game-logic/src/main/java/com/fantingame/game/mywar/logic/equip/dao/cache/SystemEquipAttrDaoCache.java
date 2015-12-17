package com.fantingame.game.mywar.logic.equip.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.equip.model.SystemEquipAttr;

public class SystemEquipAttrDaoCache extends StaticDataDaoBaseT<Integer, SystemEquipAttr> {

	@Override
	protected Integer getCacheKey(SystemEquipAttr v) {
		return v.getEquipId();
	}

	public SystemEquipAttr getSystemEquipAttr(int equipId) {
		return super.getValue(equipId);
	}
	
}
