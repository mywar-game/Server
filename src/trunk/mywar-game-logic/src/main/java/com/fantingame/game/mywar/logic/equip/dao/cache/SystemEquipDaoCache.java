package com.fantingame.game.mywar.logic.equip.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.equip.model.SystemEquip;

/**
 * 系统装备
 * 
 * @author yezp
 */
public class SystemEquipDaoCache extends StaticDataDaoBaseT<Integer, SystemEquip> {

	@Override
	protected Integer getCacheKey(SystemEquip v) {
		return v.getEquipId();
	}

	public SystemEquip getSystemEquip(int equipId) {
		return super.getValue(equipId);
	}
}
