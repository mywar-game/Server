package com.dataconfig.action;

import com.dataconfig.bo.BaPveConstantId;
import com.dataconfig.service.BaPveConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelBaPveConstant extends ALDAdminActionSupport implements ModelDriven<BaPveConstantId> {

	private static final long serialVersionUID = -3656540762547193626L;

	private BaPveConstantId baPveConstantId = new BaPveConstantId();
	
	public void executeDel() {
		BaPveConstantService baPveConstantService = ServiceCacheFactory.getServiceCache().getService(BaPveConstantService.class);
		baPveConstantService.delOneBaPveConstant(baPveConstantId);
	}
	
	@Override
	public BaPveConstantId getModel() {
		return baPveConstantId;
	}

	public void setBaPveConstantId(BaPveConstantId baPveConstantId) {
		this.baPveConstantId = baPveConstantId;
	}

	public BaPveConstantId getBaPveConstantId() {
		return baPveConstantId;
	}
}
