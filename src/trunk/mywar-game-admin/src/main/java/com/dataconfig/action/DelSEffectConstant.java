package com.dataconfig.action;

import com.dataconfig.bo.SEffectConstant;
import com.dataconfig.service.SEffectConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelSEffectConstant extends ALDAdminActionSupport implements ModelDriven<SEffectConstant> {

	private static final long serialVersionUID = 1L;
	
	private SEffectConstant seffectConstant = new SEffectConstant();
	
	public void executeDel() {
		SEffectConstantService sEffectConstantService = ServiceCacheFactory.getServiceCache().getService(SEffectConstantService.class);
		sEffectConstantService.delOneSEffectConstant(seffectConstant.getEffectId());
	}
	
	@Override
	public SEffectConstant getModel() {
		return seffectConstant;
	}

	public SEffectConstant getSeffectConstant() {
		return seffectConstant;
	}

	public void setSeffectConstant(SEffectConstant seffectConstant) {
		this.seffectConstant = seffectConstant;
	}
}
