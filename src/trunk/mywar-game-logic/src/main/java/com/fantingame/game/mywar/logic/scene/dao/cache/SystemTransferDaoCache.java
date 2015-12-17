package com.fantingame.game.mywar.logic.scene.dao.cache;



import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.scene.model.SystemTransfer;

public class SystemTransferDaoCache extends StaticDataDaoBaseT<Integer,SystemTransfer>{

	@Override
	protected Integer getCacheKey(SystemTransfer v) {
		return v.getTransferId();
	}
    
	public SystemTransfer getSystemTransfer(int transferId){
		return super.getValue(transferId);
	}
}
