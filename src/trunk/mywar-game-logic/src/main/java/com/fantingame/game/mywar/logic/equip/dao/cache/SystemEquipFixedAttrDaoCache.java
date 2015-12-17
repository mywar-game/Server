package com.fantingame.game.mywar.logic.equip.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.equip.model.SystemEquipFixedAttr;

public class SystemEquipFixedAttrDaoCache extends StaticDataDaoBaseT<Integer, SystemEquipFixedAttr> {

	@Override
	protected Integer getCacheKey(SystemEquipFixedAttr v) {
		return v.getEquipId();
	}

	public SystemEquipFixedAttr getSystemEquipFixedAttr(int equipId) {
		return super.getValue(equipId);
	}
	
}
