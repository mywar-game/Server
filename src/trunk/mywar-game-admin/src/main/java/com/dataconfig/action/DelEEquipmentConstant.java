package com.dataconfig.action;

import com.dataconfig.bo.EEquipmentConstant;
import com.dataconfig.service.EEquipmentConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelEEquipmentConstant extends ALDAdminActionSupport implements ModelDriven<EEquipmentConstant> {

	private static final long serialVersionUID = -7308719750482994258L;

	private EEquipmentConstant eequipmentConstant = new EEquipmentConstant(); 
	
	public void executeDel() {
		EEquipmentConstantService eEquipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
		eEquipmentConstantService.delOneEEquipmentConstant(eequipmentConstant.getEquipmentId());
	}
	
	@Override
	public EEquipmentConstant getModel() {
		return eequipmentConstant;
	}

	public EEquipmentConstant getEequipmentConstant() {
		return eequipmentConstant;
	}

	public void setEequipmentConstant(EEquipmentConstant eequipmentConstant) {
		this.eequipmentConstant = eequipmentConstant;
	}
}
