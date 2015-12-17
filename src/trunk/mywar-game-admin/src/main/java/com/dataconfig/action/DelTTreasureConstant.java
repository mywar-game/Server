package com.dataconfig.action;

import com.dataconfig.bo.TTreasureConstant;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelTTreasureConstant extends ALDAdminActionSupport implements ModelDriven<TTreasureConstant> {

	/** **/
	private static final long serialVersionUID = -2459499394892155809L;

	/** **/
	private TTreasureConstant treasureConstant = new TTreasureConstant();
	
	public void executeDel() {
		TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
		treasureConstantService.delTTreasureConstant(treasureConstant.getTreasureId());
	}
	
	@Override
	public TTreasureConstant getModel() {
		// TODO Auto-generated method stub
		return treasureConstant;
	}

	public TTreasureConstant getTreasureConstant() {
		return treasureConstant;
	}

	public void setTreasureConstant(TTreasureConstant treasureConstant) {
		this.treasureConstant = treasureConstant;
	}
}
