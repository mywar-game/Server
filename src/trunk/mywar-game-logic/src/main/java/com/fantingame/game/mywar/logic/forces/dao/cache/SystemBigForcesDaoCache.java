package com.fantingame.game.mywar.logic.forces.dao.cache;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.forces.model.SystemBigForces;

public class SystemBigForcesDaoCache extends StaticDataDaoBaseT<Integer, SystemBigForces> {

	@Override
	protected Integer getCacheKey(SystemBigForces v) {
		return v.getBigForcesId();
	}

	/**
	 * 获取系统大关卡
	 * 
	 * @param bigForcesId
	 * @return
	 */
	public SystemBigForces getSystemBigForces(int bigForcesId) {
		return super.getValue(bigForcesId);
	}
	
}
