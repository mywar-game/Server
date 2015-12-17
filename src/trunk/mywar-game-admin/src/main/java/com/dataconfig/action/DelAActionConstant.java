package com.dataconfig.action;

import com.dataconfig.bo.AActionConstant;
import com.dataconfig.service.AActionConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelAActionConstant extends ALDAdminActionSupport implements ModelDriven<AActionConstant> {

	private static final long serialVersionUID = 3429299547159232889L;
	
	private AActionConstant actionConstant = new AActionConstant();
	
	public void executeDel() {
		AActionConstantService actionConstantService = ServiceCacheFactory.getServiceCache().getService(AActionConstantService.class);
		actionConstantService.delAActionConstant(actionConstant.getActionId());
	}
	
	@Override
	public AActionConstant getModel() {
		// TODO Auto-generated method stub
		return actionConstant;
	}

	public AActionConstant getActionConstant() {
		return actionConstant;
	}

	public void setActionConstant(AActionConstant actionConstant) {
		this.actionConstant = actionConstant;
	}
}
